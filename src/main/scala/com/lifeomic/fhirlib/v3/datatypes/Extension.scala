package com.lifeomic.fhirlib.v3.datatypes


import java.net.URI
import java.time.LocalDateTime

import com.lifeomic.fhirlib.v3.resources.Schedule

class Extension(val url: Option[String],
                val extension: Option[List[Extension]],
                val valueInteger: Option[Int],
                val valueDecimal: Option[Double],
                val valueDateTime: Option[LocalDateTime],
                val valueDate: Option[LocalDateTime],
                val valueInstant: Option[LocalDateTime],
                val valueString: Option[String],
                val valueUri: Option[URI],
                val valueBoolean: Option[Boolean],
                val valueCode: Option[String],
                val valueBase64Binary: Option[String],
                val valueCoding: Option[Coding],
                val valueCodeableConcept: Option[CodeableConcept],
                val valueAttachment: Option[Attachment],
                val valueIdentifier: Option[Identifier],
                val valueQuantity: Option[Quantity],
                val valueRange: Option[Range],
                val valuePeriod: Option[Period],
                val valueRatio: Option[Ratio],
                val valueHumanName: Option[HumanName],
                val valueContactPoint: Option[ContactPoint],
                val valueSchedule: Option[Schedule],
                val valueReference: Option[Reference]) {
}
