package com.lifeomic.fhirlib.v3.datatypes

import java.net.URI

class Quantity(val value: Option[Double],
               val comparator: Option[String],
               val unit: Option[String],
               val system: Option[URI],
               val code: Option[String]) {
}
