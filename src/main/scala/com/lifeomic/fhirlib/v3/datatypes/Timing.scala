package com.lifeomic.fhirlib.v3.datatypes

import org.joda.time.{DateTime, LocalTime}

object DayOfWeek extends Enumeration {
    type DayOfWeek = Value
    val mon, tue, wed, thu, fri, sat, sun = Value
}
import DayOfWeek._

object TimingUnit extends Enumeration {
    type TimingUnit = Value
    val s, min, h, d, wk, mo, a = Value
}
import TimingUnit._

class TimingRepeat(val boundsDuration: Option[Quantity],
                   val boundsRange: Option[Range],
                   val boundsPeriod: Option[Period],
                   val count: Option[Int],
                   val countMax: Option[Int],
                   val duration: Option[Double],
                   val durationMax: Option[Double],
                   val durationUnit: Option[TimingUnit],
                   val frequency: Option[Int],
                   val frequencyMax: Option[Int],
                   val period: Option[Double],
                   val periodMax: Option[Double],
                   val periodUnit: Option[TimingUnit],
                   val dayOfWeek: Option[List[DayOfWeek]],
                   val timeOfDay: Option[List[LocalTime]],
                   val when: Option[List[String]],
                   val offset: Option[String])

class Timing(val event: Option[List[DateTime]],
             val repeat: Option[TimingRepeat],
             val code: Option[CodeableConcept]) {

}
