package com.lifeomic.fhirlib.v3.datatypes

object HumanName_Use extends Enumeration {
    type HumanName_Use = Value
    val usual, official, temp, nickname, anonymous, old, maiden = Value
}
import HumanName_Use._

class HumanName(val use: Option[HumanName_Use],
                val text: Option[String],
                val family: Option[String],
                val given: Option[List[String]],
                val prefix: Option[List[String]],
                val suffix: Option[List[String]],
                val period: Option[Period]) {

    def flatten(): Seq[Any] = {
        Seq(use.orNull, text.orNull, family.orNull, given.map(_.head).orNull, prefix.map(_.head).orNull,
            suffix.map(_.head).orNull, period.map(_.start.orNull).orNull, period.map(_.end.orNull).orNull)
    }

}
