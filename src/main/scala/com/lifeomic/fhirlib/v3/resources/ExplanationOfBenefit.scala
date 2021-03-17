package com.lifeomic.fhirlib.v3.resources

import java.time.LocalDateTime

import com.lifeomic.fhirlib.v3.datatypes._

object ExplanationOfBenefitStatus extends Enumeration {
  type ExplanationOfBenefitStatus = Value
  val active, cancelled, draft, `entered-in-error`, unknown = Value
}

class EoBRelated(val claim: Option[Reference],
                 val relationship: Option[CodeableConcept],
                 val reference: Option[Identifier]
)

class EoBPayee(val `type`: Option[CodeableConcept],
               val resourceType: Option[Coding],
               val party: Option[Reference]
)

class EoBCareTeam(val sequence: Option[Integer],
                  val provider: Option[Reference],
                  val responsible: Option[Boolean],
                  val role: Option[CodeableConcept],
                  val qualification: Option[CodeableConcept]
)

class EoBInformation(val sequence: Option[Integer],
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

class EoBDiagnosis(val sequence: Option[Integer],
                   val diagnosisCodeableConcept: Option[CodeableConcept],
                   val diagnosisReference: Option[Reference],
                   val `type`: Option[List[CodeableConcept]],
                   val packageCode: Option[CodeableConcept]
)

class EoBProcedure(val sequence: Option[Integer],
                   val date: Option[LocalDateTime],
                   val procedureCodeableConcept: Option[CodeableConcept],
                   val procedureReference: Option[Reference]
)

class EoBInsurance(val coverage: Option[Reference],
                   val preAuthRef: Option[List[String]]
)

class EoBAccident(val date: Option[LocalDateTime],
                  val `type`: Option[CodeableConcept],
                  val locationAddress: Option[Address],
                  val locationReference: Option[Reference]
)

class EoBAdjudication(val category: Option[CodeableConcept],
                          val reason: Option[CodeableConcept],
                          val amount: Option[Money],
                          val value: Option[Double]
)

class EoBItemSubDetail(val sequence: Option[Integer],
                       val `type`: Option[CodeableConcept],
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
                       val noteNumber: Option[List[Integer]],
                       val adjudication: Option[List[EoBAdjudication]]
)

class EoBItemDetail(val sequence: Option[Integer],
                    val `type`: Option[CodeableConcept],
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
                    val noteNumber: Option[List[Integer]],
                    val adjudication: Option[List[EoBAdjudication]],
                    val subDetail: Option[List[EoBItemSubDetail]]
)

class EoBAddItemDetail(val revenue: Option[CodeableConcept],
                       val category: Option[CodeableConcept],
                       val service: Option[CodeableConcept],
                       val modifier: Option[List[CodeableConcept]],
                       val fee: Option[Money],
                       val noteNumber: Option[List[Integer]],
                       val adjudication: Option[List[EoBAdjudication]]
)


class EoBItem(val sequence: Option[Integer],
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
              val noteNumber: Option[List[Integer]],
              val adjudication: Option[List[EoBAdjudication]],
              val detail: Option[List[EoBItemDetail]]
)

class EoBAddItem(val sequenceLinkId: Option[List[Integer]],
                 val revenue: Option[CodeableConcept],
                 val category: Option[CodeableConcept],
                 val service: Option[CodeableConcept],
                 val modifier: Option[List[CodeableConcept]],
                 val fee: Option[Money],
                 val noteNumber: Option[List[Integer]],
                 val adjudication: Option[List[EoBAdjudication]],
                 val detail: Option[List[EoBAddItemDetail]]
)

class EoBPayment(val `type`: Option[CodeableConcept],
                 val adjustment: Option[Money],
                 val adjustmentReason: Option[CodeableConcept],
                 val date: Option[LocalDateTime],
                 val amount: Option[Money],
                 val identifier: Option[Identifier]
)

class EoBProcessNote(val number: Option[Integer],
                     val `type`: Option[CodeableConcept],
                     val text: Option[String],
                     val language: Option[CodeableConcept]
)

class EoBBenefitBalanceFinancial(val `type`: Option[CodeableConcept],
                                 val allowedUnsignedInt: Option[Integer],
                                 val allowedString: Option[String],
                                 val allowedMoney: Option[Money],
                                 val usedUnsignedInt: Option[Integer],
                                 val usedMoney: Option[Money]
)

class EoBBenefitBalance(val category: Option[CodeableConcept],
                        val subCategory: Option[CodeableConcept],
                        val excluded: Option[Boolean],
                        val name: Option[String],
                        val description: Option[String],
                        val network: Option[CodeableConcept],
                        val unit: Option[CodeableConcept],
                        val term: Option[CodeableConcept],
                        val financial: Option[List[EoBBenefitBalanceFinancial]]
)

class ExplanationOfBenefit(override val id: Option[String],
                override val contained: Option[List[Resource]],
                override val meta: Option[Meta],
                override val extension: Option[List[Extension]],
                override val identifier: Option[List[Identifier]],
                val status: Option[String],
                val `type`: Option[CodeableConcept],
                val subType: Option[List[CodeableConcept]],
                val patient: Option[Reference],
                val billablePeriod: Option[Period],
                val created: Option[LocalDateTime],
                val enterer: Option[Reference],
                val insurer: Option[Reference],
                val provider: Option[Reference],
                val organization: Option[Reference],
                val referral: Option[Reference],
                val facility: Option[Reference],
                val claim: Option[Reference],
                val claimResponse: Option[Reference],
                val outcome: Option[CodeableConcept],
                val disposition: Option[String],
                val related: Option[List[EoBRelated]],
                val prescription: Option[Reference],
                val originalPrescription: Option[Reference],
                val payee: Option[EoBPayee],
                val information: Option[List[EoBInformation]],
                val careTeam: Option[List[EoBCareTeam]],
                val diagnosis: Option[List[EoBDiagnosis]],
                val procedure: Option[List[EoBProcedure]],
                val precedence: Option[Integer],
                val insurance: Option[EoBInsurance],
                val accident: Option[EoBAccident],
                val employmentImpacted: Option[Period],
                val hospitalization: Option[Period],
                val item: Option[List[EoBItem]],
                val addItem: Option[List[EoBAddItem]],
                val totalCost: Option[Money],
                val unallocDeductable: Option[Money],
                val totalBenefit: Option[Money],
                val payment: Option[EoBPayment],
                val form: Option[CodeableConcept],
                val processNote: Option[List[EoBProcessNote]],
                val benefitBalance: Option[List[EoBBenefitBalance]]
               ) extends Resource("ExplanationOfBenefit", id, contained, meta, extension, identifier) {

}
