package com.lifeomic.fhirlib.v3.resources

import java.time.LocalDateTime

import com.lifeomic.fhirlib.v3.datatypes._

object ClaimStatus extends Enumeration {
  type ClaimStatus = Value
  val active, cancelled, draft, `entered-in-error`, unknown = Value
}

object ClaimUse extends Enumeration {
  type ClaimUse = Value
  val completed, proposed, exploratory, other, unknown = Value
}

class ClaimRelated(val claim: Option[Reference],
                   val relationship: Option[CodeableConcept],
                   val reference: Option[Identifier]
)

class ClaimPayee(val `type`: Option[CodeableConcept],
                 val resource: Option[Coding],
                 val party: Option[Reference]
)

class ClaimCareTeam(val sequence: Option[Integer],
                    val provider: Option[Reference],
                    val responsible: Option[Boolean],
                    val role: Option[CodeableConcept],
                    val qualification: Option[CodeableConcept]
)

class ClaimInformation(val sequence: Option[Integer],
                       val category: Option[CodeableConcept],
                       val code: Option[CodeableConcept],
                       val timingDate: Option[LocalDateTime],
                       val timingPeriod: Option[Period],
                       val valueString: Option[String],
                       val valueQuantity: Option[Quantity],
                       val valueAttachment: Option[Attachment],
                       val valueReference: Option[Reference],
                       val reason: Option[CodeableConcept]
)

class ClaimDiagnosis(val sequence: Option[Integer],
                     val diagnosisCodeableConcept: Option[CodeableConcept],
                     val diagnosisReference: Option[Reference],
                     val `type`: Option[CodeableConcept],
                     val packageCode: Option[CodeableConcept]
)

class ClaimProcedure(val sequence: Option[Integer],
                     val date: Option[LocalDateTime],
                     val procedureCodeableConcept: Option[CodeableConcept],
                     val procedureReference: Option[Reference]
)

class ClaimInsurance(val sequence: Option[Int],
                     val focal: Option[Boolean],
                     val identifier: Option[Identifier],
                     val coverage: Option[Reference],
                     val businessArrangement: Option[String],
                     val preAuthRef: Option[List[String]],
                     val claimResponse: Option[Reference]
)

class ClaimAccident(val date: Option[LocalDateTime],
                    val `type`: Option[CodeableConcept],
                    val locationAddress: Option[Address],
                    val locationReference: Option[Reference]
)

class ClaimItemSubDetail(val sequence: Option[Integer],
                          val revenue: Option[CodeableConcept],
                          val category: Option[CodeableConcept],
                          val service: Option[CodeableConcept],
                          val modifier: Option[List[CodeableConcept]],
                          val programCode: Option[List[CodeableConcept]],
                          val quantity: Option[Quantity],
                          val unitPrice: Option[Money],
                          val factor: Option[Double],
                          val net: Option[Money],
                          val udi: Option[List[Reference]]
)

class ClaimItemDetail(val sequence: Option[Integer],
                      val revenue: Option[CodeableConcept],
                      val category: Option[CodeableConcept],
                      val service: Option[CodeableConcept],
                      val modifier: Option[List[CodeableConcept]],
                      val programCode: Option[List[CodeableConcept]],
                      val quantity: Option[Quantity],
                      val unitPrice: Option[Money],
                      val factor: Option[Double],
                      val net: Option[Money],
                      val udi: Option[List[Reference]],
                      val subDetail: Option[List[ClaimItemSubDetail]]
)

class ClaimItem(val sequence: Option[Integer],
                val careTeamSequence: Option[List[Integer]],
                val diagnosisSequence: Option[List[Integer]],
                val procedureSequence: Option[List[Integer]],
                val informationSequence: Option[List[Integer]],
                val revenue: Option[CodeableConcept],
                val category: Option[CodeableConcept],
                val service: Option[CodeableConcept],
                val modifier: Option[List[CodeableConcept]],
                val programCode: Option[List[CodeableConcept]],
                val servicedDate: Option[LocalDateTime],
                val servicedPeriod: Option[Period],
                val locationCodeableConcept: Option[CodeableConcept],
                val locationAddress: Option[Address],
                val locationReference: Option[Reference],
                val quantity: Option[Quantity],
                val unitPrice: Option[Money],
                val factor: Option[Double],
                val net: Option[Money],
                val udi: Option[List[Reference]],
                val bodySite: Option[CodeableConcept],
                val subSite: Option[List[CodeableConcept]],
                val encounter: Option[Reference],
                val detail: Option[List[ClaimItemDetail]]
)

class Claim(override val id: Option[String],
            override val contained: Option[List[Resource]],
            override val meta: Option[Meta],
            override val extension: Option[List[Extension]],
            override val identifier: Option[List[Identifier]],
            val status: Option[String],
            val `type`: Option[CodeableConcept],
            val subType: Option[List[CodeableConcept]],
            val use: Option[String],
            val patient: Option[Reference],
            val billablePeriod: Option[Period],
            val created: Option[LocalDateTime],
            val enterer: Option[Reference],
            val insurer: Option[Reference],
            val provider: Option[Reference],
            val organizaiton: Option[Reference],
            val priority: Option[CodeableConcept],
            val fundsReserve: Option[CodeableConcept],
            val related: Option[List[ClaimRelated]],
            val prescription: Option[Reference],
            val originalPrescription: Option[Reference],
            val payee: Option[ClaimPayee],
            val referral: Option[Reference],
            val facility: Option[Reference],
            val careTeam: Option[List[ClaimCareTeam]],
            val information: Option[List[ClaimInformation]],
            val diagnosis: Option[List[ClaimDiagnosis]],
            val procedure: Option[List[ClaimProcedure]],
            val insurance: Option[List[ClaimInsurance]],
            val accident: Option[ClaimAccident],
            val employmentImpacted: Option[Period],
            val hospitalization: Option[Period],
            val item: Option[List[ClaimItem]],
            val total: Option[Money]
           ) extends Resource("Claim", id, contained, meta, extension, identifier) {

}
