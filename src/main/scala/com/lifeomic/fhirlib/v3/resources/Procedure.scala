package com.lifeomic.fhirlib.v3.resources

import org.joda.time.DateTime
import com.lifeomic.fhirlib.v3.datatypes._

object ProcedureStatus extends Enumeration {
    type ProcedureStatus = Value
    val preparation, `in-progress`, suspended, aborted, completed, `entered-in-error`, unknown = Value
}
import ProcedureStatus._

class ProcedurePerformer(val role: Option[CodeableConcept],
                         val actor: Reference,
                         val onBehalfOf: Option[Reference])

class FocalDevice(val action: Option[CodeableConcept],
                  val manipulated: Reference)

class Procedure(override val id: Option[String],
                override val contained: Option[List[Resource]],
                override val meta: Option[Meta],
                override val extension: Option[List[Extension]],
                override val identifier: Option[List[Identifier]],
                val definition: Option[List[Reference]],
                val basedOn: Option[List[Reference]],
                val partOf: Option[List[Reference]],
                val status: ProcedureStatus,
                val notDone: Option[Boolean],
                val notDoneReason: Option[CodeableConcept],
                val category: Option[CodeableConcept],
                val code: Option[CodeableConcept],
                val subject: Reference,
                val context: Option[Reference],
                val performedDateTime: Option[DateTime],
                val performer: Option[ProcedurePerformer],
                val location: Option[Reference],
                val reasonCode: Option[List[CodeableConcept]],
                val reasonReference: Option[List[Reference]],
                val bodySite: Option[List[CodeableConcept]],
                val outcome: CodeableConcept,
                val report: Option[List[Reference]],
                val complication: Option[List[CodeableConcept]],
                val complicationDetail: Option[List[Reference]],
                val followUp: Option[List[CodeableConcept]],
                val note: Option[List[Annotation]],
                val focalDevice: Option[List[FocalDevice]],
                val usedReference: Option[List[Reference]],
                val usedCode: Option[List[CodeableConcept]]
               ) extends Resource("Procedure", id, contained, meta, extension, identifier) {

}
