package com.lifeomic.fhirlib.v3.datatypes

import java.net.URI

object Identifier_Use extends Enumeration {
    type Identifier_Use = Value
    val usual, official, temp, secondary = Value
}
import Identifier_Use._

class Identifier(val use: Option[String],
                 val `type`: Option[CodeableConcept],
                 val system: Option[URI],
                 val value: Option[String],
                 val period: Option[Period],
                 val assigner: Option[Reference]) {
}
