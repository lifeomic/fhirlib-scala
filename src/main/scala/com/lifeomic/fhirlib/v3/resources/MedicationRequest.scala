package com.lifeomic.fhirlib.v3.resources

import org.joda.time.DateTime
import com.lifeomic.fhirlib.v3.datatypes._

object MedicationRequestStatus extends Enumeration {
    type MedicationRequestStatus = Value
    val active, `on-hold`, cancelled, completed, `entered-in-error`, stopped, draft, unknown = Value
}

import MedicationRequestStatus._

object MedicationRequestIntent extends Enumeration {
    type MedicationRequestIntent = Value
    val proposal, plan, order, `instance-order` = Value
}

import MedicationRequestIntent._

object MedicationRequestPriority extends Enumeration {
    type MedicationRequestPriority = Value
    val routine, urgent, stat, asap = Value
}
import MedicationRequestPriority._

class Requester(val agent: Reference,
                val onBehalfOf: Option[Reference])


class DispenseRequest(val validityPeriod: Option[Period],
                      val numberOfRepeatsAllowed: Option[Int],
                      val quantity: Option[Quantity],
                      val expectedSupplyDuration: Option[Quantity],
                      val performer: Option[Reference])

class Substitution(val allowed: Boolean,
                   val reason: Option[CodeableConcept])

class MedicationRequest(override val id: Option[String],
                        override val contained: Option[List[Resource]],
                        override val meta: Option[Meta],
                        override val extension: Option[List[Extension]],
                        override val identifier: Option[List[Identifier]],
                        val definition: Option[List[Reference]],
                        val basedOn: Option[List[Reference]],
                        val groupIdentifier: Option[Identifier],
                        val status: Option[String],
                        val intent: String,
                        val category: Option[CodeableConcept],
                        val priority: Option[String],
                        val medicationCodeableConcept: Option[CodeableConcept],
                        val medicationReference: Option[Reference],
                        val subject: Reference,
                        val context: Option[Reference],
                        val supportingInformation: Option[Reference],
                        val authoredOn: Option[DateTime],
                        val requester: Option[Requester],
                        val recorder: Option[Reference],
                        val reasonCode: Option[List[CodeableConcept]],
                        val reasonReference: Option[List[Reference]],
                        val note: Option[List[Annotation]],
                        val dosageInstruction: Option[List[Dosage]],
                        val dispenseRequest: Option[DispenseRequest],
                        val substitution: Option[Substitution],
                        val priorPrescription: Option[Reference],
                        val detectedIssue: Option[List[Reference]],
                        val eventHistory: Option[List[Reference]]
                       ) extends Resource("MedicationRequest", id, contained, meta, extension, identifier) {

}
