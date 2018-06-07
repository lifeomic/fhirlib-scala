package com.lifeomic.fhirlib.v3.datatypes

class Quantity(val value: Option[Double],
               val comparator: Option[String],
               val unit: Option[String],
               val system: Option[String],
               val code: Option[String]) {
}
