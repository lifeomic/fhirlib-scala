package com.lifeomic.fhirlib.v3.resources

import java.time.LocalDateTime

import com.lifeomic.fhirlib.v3.datatypes._

import scala.collection.mutable.ListBuffer

object Specimen_Status extends Enumeration {
  type Specimen_Status = Value
  val available, unavailable, unsatisfactory, `entered-in-error` = Value
}

class Collection(val collector: Option[Reference],
                 val collectedDateTime: Option[LocalDateTime],
                 val collectedPeriod: Option[Period],
                 val quantity: Option[Quantity],
                 val method: Option[CodeableConcept],
                 val bodySite: Option[CodeableConcept])

class Processing(val description: Option[String],
                 val procedure: Option[CodeableConcept],
                 val additive: Option[List[Reference]],
                 val timeDateTime: Option[LocalDateTime],
                 val timePeriod: Option[Period])

class Container(val identifier: Option[List[Identifier]],
                val description: Option[String],
                val `type`: Option[CodeableConcept],
                val capacity: Option[Quantity],
                val specimenQuantity: Option[Quantity],
                val additiveCodeableConcept: Option[CodeableConcept],
                val additiveReference: Option[Reference])

class Specimen(override val id: Option[String],
               override val contained: Option[List[Resource]],
               override val meta: Option[Meta],
               override val extension: Option[List[Extension]],
               override val identifier: Option[List[Identifier]],
               val accessionIdentifier: Option[List[Identifier]],
               val status: Option[String],
               val `type`: Option[CodeableConcept],
               val subject: Option[Reference],
               val receivedTime: Option[LocalDateTime],
               val parent: Option[List[Reference]],
               val request: Option[List[Reference]],
               val collection: Option[Collection],
               val processing: Option[List[Processing]],
               val container: Option[List[Container]],
               val note: Option[List[Annotation]]
              ) extends Resource("Specimen", id, contained, meta, extension, identifier) {

  /**
    *
    * @todo - handle missing [[`type`]] value
    * @todo - handle missing [[Coding]] value
    * @todo - Use stream logic instead of [[ListBuffer]] concatenation
    * @todo - Remove try-catch [[Throwable]]
    *
    * @return
    */
  def getTypeCodings(): List[Coding] = {
    val res = ListBuffer[Coding]()
    try {
      this.`type`.get.coding.get.foreach(coding => {
        res += coding
      })
    } catch {
      case _: Throwable => res.toList
    }
    res.toList
  }
}
