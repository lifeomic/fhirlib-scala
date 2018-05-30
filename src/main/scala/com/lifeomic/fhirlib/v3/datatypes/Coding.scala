package com.lifeomic.fhirlib.v3.datatypes

import java.net.URI

class Coding(val system: Option[URI],
             val version: Option[String],
             val code: Option[String],
             val display: Option[String],
             val userSelected: Option[Boolean]) {
}
