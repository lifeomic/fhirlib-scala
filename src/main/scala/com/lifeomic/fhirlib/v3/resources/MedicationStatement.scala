package com.lifeomic.fhirlib.v3.resources

import org.joda.time.DateTime
import com.lifeomic.fhirlib.v3.datatypes._

object MedicationStatementStatus extends Enumeration {
    type MedicationStatementStatus = Value
    val active, completed, `entered-in-error`, intended, stopped, `on-hold` = Value
}

import MedicationStatementStatus._

object Taken extends Enumeration {
    type Taken = Value
    val y, n, unk, na = Value
}

import Taken._

class MedicationStatement(override val id: Option[String],
                          override val contained: Option[List[Resource]],
                          override val meta: Option[Meta],
                          override val extension: Option[List[Extension]],
                          override val identifier: Option[List[Identifier]],
                          val basedOn: Option[List[Reference]],
                          val partOf: Option[List[Reference]],
                          val context: Option[Reference],
                          val status: Option[String],
                          val category: Option[CodeableConcept],
                          val medicationCodeableConcept: Option[CodeableConcept],
                          val medicationReference: Option[Reference],
                          val effectiveDateTime: Option[DateTime],
                          val effectivePeriod: Option[Period],
                          val dateAsserted: Option[DateTime],
                          val informationSource: Option[Reference],
                          val subject: Option[Reference],
                          val derivedFrom: Option[List[Reference]],
                          val taken: Option[Taken],
                          val reasonNotTaken: Option[List[CodeableConcept]],
                          val reasonCode: Option[List[CodeableConcept]],
                          val reasonReference: Option[List[Reference]],
                          val note: Option[List[Annotation]],
                          val dosage: Option[List[Dosage]]
                         ) extends Resource("MedicationStatement", id, contained, meta, extension, identifier) {

}
