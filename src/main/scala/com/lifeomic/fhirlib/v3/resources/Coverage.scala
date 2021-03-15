package com.lifeomic.fhirlib.v3.resources

import java.time.LocalDateTime

import com.lifeomic.fhirlib.v3.datatypes._

object CoverageStatus extends Enumeration {
  type CoveageStatus = Value
  val active, cancelled, draft, `entered-in-error`, unknown = Value
}

class Grouping(val group: Option[String],
               val groupDisplay: Option[String],
               val subGroup: Option[String],
               val subGroupDisplay: Option[String],
               val plan: Option[String],
               val planDisplay: Option[String],
               val subPlan: Option[String],
               val subPlanDisplay: Option[String],
               val `class`: Option[String],
               val classDisplay: Option[String],
               val subClass: Option[String],
               val subClassDisplay: Option[String]
               )

class Coverage(override val id: Option[String],
                override val contained: Option[List[Resource]],
                override val meta: Option[Meta],
                override val extension: Option[List[Extension]],
                override val identifier: Option[List[Identifier]],
                val status: Option[String],
                val `type`: Option[CodeableConcept],
                val policyHolder: Option[Reference],
                val subscriber: Option[Reference],
                val subscriberId: Option[String],
                val beneficiary: Option[Reference],
                val relationship: Option[CodeableConcept],
                val period: Option[Period],
                val payor: Option[List[Reference]],
                val grouping: Option[Grouping],
                val dependent: Option[String],
                val sequence: Option[String],
                val order: Option[Integer],
                val network: Option[String],
                val contract: Option[List[Reference]]
               ) extends Resource("Coverage", id, contained, meta, extension, identifier) {

}
