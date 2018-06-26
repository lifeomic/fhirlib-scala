package com.lifeomic.fhirlib.v3.resources

import java.time.LocalDateTime

import com.lifeomic.fhirlib.v3.datatypes._

object MedicationStatus extends Enumeration {
  type MedicationStatus = Value
  val active, inactive, `entered-in-error` = Value
}

class Ingredient(val itemCodeableConcept: Option[CodeableConcept],
                 val itemReference: Option[Reference],
                 val isActive: Option[Boolean],
                 val amount: Option[Ratio])

class PackageContent(val itemCodeableConcept: Option[CodeableConcept],
                     val itemReference: Option[Reference],
                     val amount: Option[Quantity])

class PackageBatch(val lotNumber: Option[String],
                   val expirationDate: Option[LocalDateTime])

class MedicationPackage(val container: Option[CodeableConcept],
                        val content: Option[List[PackageContent]],
                        val batch: Option[List[PackageBatch]])

class Medication(override val id: Option[String],
                 override val contained: Option[List[Resource]],
                 override val meta: Option[Meta],
                 override val extension: Option[List[Extension]],
                 override val identifier: Option[List[Identifier]],
                 val code: Option[CodeableConcept],
                 val status: Option[String],
                 val isBrand: Option[Boolean],
                 val isOverTheCounter: Option[Boolean],
                 val manufacturer: Option[Reference],
                 val form: Option[CodeableConcept],
                 val ingredient: Option[Ingredient],
                 val `package`: Option[MedicationPackage],
                 val image: Option[List[Attachment]]
                ) extends Resource("Medication", id, contained, meta, extension, identifier) {

}
