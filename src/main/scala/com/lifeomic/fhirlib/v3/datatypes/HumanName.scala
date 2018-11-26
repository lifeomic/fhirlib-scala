package com.lifeomic.fhirlib.v3.datatypes

object HumanName_Use extends Enumeration {
  type HumanName_Use = Value
  val usual, official, temp, nickname, anonymous, old, maiden = Value
}

class HumanName(val use: Option[String],
                val text: Option[String],
                val family: Option[String],
                val given: Option[List[String]],
                val prefix: Option[List[String]],
                val suffix: Option[List[String]],
                val period: Option[Period]) {

}
