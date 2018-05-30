package com.lifeomic.fhirlib.v3.datatypes

object Address_Use extends Enumeration {
    type Address_Use = Value
    val usual, official, temp, nickname, anonymous, old, maiden = Value
}

object Address_Type extends Enumeration {
    type Address_Type = Value
    val postal, physical, temp, nickname, anonymous, old, maiden = Value
}


import Address_Use._
import Address_Type._

class Address(val use: Option[String],
              val `type`: Option[String],
              val text: Option[String],
              val line: Option[List[String]],
              val city: Option[String],
              val district: Option[String],
              val state: Option[String],
              val postalCode: Option[String],
              val country: Option[String],
              val period: Option[Period],
              val extension: Option[List[Extension]]) {

    def getLatitude(): Option[Double] = {
        try {
            extension.get.filter(_.url.toString == "http://hl7.org/fhir/StructureDefinition/geolocation")
                .head.extension.get.filter(_.url.toString == "latitude")
                .head.valueDecimal
        } catch {
            case _ : Throwable => None
        }
    }

    def getLongitude(): Option[Double] = {
        try {
            extension.get.filter(_.url.toString == "http://hl7.org/fhir/StructureDefinition/geolocation")
                .head.extension.get.filter(_.url.toString == "longitude")
                .head.valueDecimal
        } catch {
            case _ : Throwable => None
        }
    }
}
