package com.lifeomic.fhirlib.v3.resources

import java.time.LocalDateTime

import com.lifeomic.fhirlib.v3.datatypes._

object MedicationAdministrationStatus extends Enumeration {
  type MedicationAdministrationStatus = Value
  val `in-progress`, `on-hold`, completed, `entered-in-error`, stopped, unknown = Value
}

class Performer(val actor: Reference,
                val onBehalfOf: Option[Reference])


class MedicationAdministration(override val id: Option[String],
                               override val contained: Option[List[Resource]],
                               override val meta: Option[Meta],
                               override val extension: Option[List[Extension]],
                               override val identifier: Option[List[Identifier]],
                               val definition: Option[List[Reference]],
                               val partOf: Option[List[Reference]],
                               val status: Option[String],
                               val category: Option[CodeableConcept],
                               val medicationCodeableConcept: Option[CodeableConcept],
                               val medicationReference: Option[Reference],
                               val subject: Option[Reference],
                               val context: Option[Reference],
                               val supportingInformation: Option[List[Reference]],
                               val effectiveDateTime: Option[LocalDateTime],
                               val effectivePeriod: Option[Period],
                               val performer: Option[Performer],
                               val notGiven: Option[Boolean],
                               val reasonNotGiven: Option[List[CodeableConcept]],
                               val reasonCode: Option[List[CodeableConcept]],
                               val reasonReference: Option[List[Reference]],
                               val prescription: Option[Reference],
                               val device: Option[List[Reference]],
                               val note: Option[List[Annotation]],
                               val dosage: Option[Dosage],
                               val eventHistory: Option[List[Reference]]
                              ) extends Resource("MedicationAdministration", id, contained, meta, extension, identifier) {

}
