package com.lifeomic.fhirlib.v3

import java.time.{LocalDateTime, ZoneId, ZonedDateTime}
import java.time.format.{DateTimeFormatter, DateTimeFormatterBuilder}
import java.time.temporal.ChronoField
import java.util.regex.Pattern

object DateUtils {

  private val regex = "(\\d{4}(-\\d{2}(-\\d{2}(T\\d{2}(:\\d{2}(:\\d{2}(.\\d{3}(Z|[\\+|\\-]\\d{2}:\\d{2})?)?)?)?)?)?)?)"

  private val formatters: Array[DateTimeFormatter] = Array(

    new DateTimeFormatterBuilder()
      .appendPattern("yyyy")
      .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
      .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
      .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
      .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
      .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
      .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
      .toFormatter(),
    new DateTimeFormatterBuilder()
      .appendPattern("yyyy-MM")
      .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
      .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
      .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
      .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
      .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
      .toFormatter(),
    new DateTimeFormatterBuilder()
      .appendPattern("yyyy-MM-dd")
      .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
      .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
      .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
      .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
      .toFormatter(),
    new DateTimeFormatterBuilder()
      .appendPattern("yyyy-MM-dd'T'HH")
      .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
      .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
      .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
      .toFormatter(),
    new DateTimeFormatterBuilder()
      .appendPattern("yyyy-MM-dd'T'HH:mm")
      .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
      .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
      .toFormatter(),
    new DateTimeFormatterBuilder()
      .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
      .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
      .appendPattern("[XXXXX]")
      .toFormatter(),
    new DateTimeFormatterBuilder()
      .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
      .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 6, true)
      .appendPattern("[XXXXX]")
      .toFormatter(),
    new DateTimeFormatterBuilder()
      .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
      .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 6, true)
      .appendPattern("[XXXXX]")
      .toFormatter()
  )

  /**
    * Parses a string into a local date time object.
    *
    * Any date times will be stored in UTC.
    *
    * This method follows the specification for the FHIR date time object. See
    * https://www.hl7.org/fhir/datatypes.html#dateTime
    *
    * @param s a string to parse
    * @return a local date time
    */
  def parseLocalDateTime(s: String): Option[LocalDateTime] = {

    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(s)

    if (!matcher.find()) {
      return None
    }

    // scan groups to count non-null matches
    var i: Int = 0
    while (i < matcher.groupCount() && matcher.group(i + 1) != null) {
      i += 1
    }

    if (i < 1 || i > 8) {
      throw new RuntimeException("unable to determine format of datetime")
    }

    val formatter: DateTimeFormatter = formatters(i - 1)

    if (i < 8) {
      Some(LocalDateTime.parse(s, formatter))
    } else {
      Some(ZonedDateTime.parse(s, formatter).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime)
    }
  }

}
