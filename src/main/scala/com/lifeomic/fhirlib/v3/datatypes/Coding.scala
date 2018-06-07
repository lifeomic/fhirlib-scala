package com.lifeomic.fhirlib.v3.datatypes

class Coding(val system: Option[String],
             val version: Option[String],
             val code: Option[String],
             val display: Option[String],
             val userSelected: Option[Boolean]) {
}
