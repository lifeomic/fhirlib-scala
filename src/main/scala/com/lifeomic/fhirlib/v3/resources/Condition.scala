package com.lifeomic.fhirlib.v3.resources

import java.time.LocalDateTime

import com.lifeomic.fhirlib.v3.datatypes._

object ClinicalStatus extends Enumeration {
  type ClinicalStatus = Value
  val active, recurrence, inactive, remission, resolved = Value
}

object VerificationStatus extends Enumeration {
  type VerificationStatus = Value
  val provisional, differential, confirmed, refuted, `entered-in-error`, unknown = Value
}

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
                val clinicalStatus: Option[String],
                val verificationStatus: Option[String],
                val category: Option[List[CodeableConcept]],
                val severity: Option[CodeableConcept],
                val code: Option[CodeableConcept],
                val bodySite: Option[List[CodeableConcept]],
                val subject: Option[Reference],
                val context: Option[Reference],
                val onsetDateTime: Option[LocalDateTime],
                val onsetDate: Option[LocalDateTime],
                val onsetPeriod: Option[Period],
                val onsetRange: Option[Range],
                val onsetString: Option[String],
                val abatementDateTime: Option[LocalDateTime],
                val abatementAge: Option[Quantity],
                val abatementBoolean: Option[Boolean],
                val abatementRange: Option[Range],
                val abatementString: Option[String],
                val assertedDate: Option[LocalDateTime],
                val asserter: Option[Reference],
                val stage: Option[ConditionStage],
                val evidence: Option[List[ConditionEvidence]],
                val note: Option[List[Annotation]]
               ) extends Resource("Condition", id, contained, meta, extension, identifier) {

}
