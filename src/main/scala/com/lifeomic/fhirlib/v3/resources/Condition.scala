package com.lifeomic.fhirlib.v3.resources

import com.lifeomic.fhirlib.v3.datatypes._
import org.joda.time.DateTime

object ClinicalStatus extends Enumeration {
    type ClinicalStatus = Value
    val active, recurrence, inactive, remission, resolved = Value
}
import ClinicalStatus._

object VerificationStatus extends Enumeration {
    type VerificationStatus = Value
    val provisional, differential, confirmed, refuted, `entered-in-error`, unknown = Value
}

import VerificationStatus._

class ConditionEvidence(val code: Option[List[CodeableConcept]],
                        val detail: Option[List[Reference]])

class ConditionStage(val summary: Option[CodeableConcept],
                    val assessment: Option[List[Reference]],
                    val evidence: Option[List[ConditionEvidence]])

class Condition(override val id: Option[String],
                override val contained: Option[List[Resource]],
                override val meta: Option[Meta],
                override val extension: Option[List[Extension]],
                override val identifier: Option[List[Identifier]],
                val clinicalStatus: Option[ClinicalStatus],
                val verificationStatus: Option[VerificationStatus],
                val category: Option[List[CodeableConcept]],
                val severity: Option[CodeableConcept],
                val code: Option[CodeableConcept],
                val bodySite: Option[List[CodeableConcept]],
                val subject: Reference,
                val context: Option[Reference],
                val onsetDateTime: Option[DateTime],
                val onsetDate: Option[DateTime],
                val onsetPeriod: Option[Period],
                val onsetRange: Option[Range],
                val onsetString: Option[String],
                val abatementDateTime: Option[DateTime],
                val abatementAge: Option[Quantity],
                val abatementBoolean: Option[Boolean],
                val abatementRange: Option[Range],
                val abatementString: Option[String],
                val assertedDate: Option[DateTime],
                val asserter: Option[Reference],
                val stage: Option[ConditionStage],
                val evidence: Option[List[ConditionEvidence]],
                val note: Option[List[Annotation]]
               ) extends Resource("Condition", id, contained, meta, extension, identifier) {

}
