package com.lifeomic.fhirlib.v3.datatypes


import java.net.URI

import com.lifeomic.fhirlib.v3.resources.Schedule
import org.joda.time.DateTime

class Extension(val url: Option[URI],
                val extension: Option[List[Extension]],
                val valueInteger: Option[Int],
                val valueDecimal: Option[Double],
                val valueDateTime: Option[DateTime],
                val valueDate: Option[DateTime],
                val valueInstant: Option[DateTime],
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

    def getUrlString(): String = {
        return url.map(_.toString).getOrElse("")
    }
}
