package com.lifeomic.fhirlib.v3.resources

import com.lifeomic.fhirlib.v3.datatypes._

import org.joda.time.{DateTime, LocalTime}

object ObservationStatus extends Enumeration {
    type ObservationStatus = Value
    val registered, preliminary, `final`, amended,
    corrected, cancelled, `entered-in-error`, unknown = Value
}

import ObservationStatus._

class ReferenceRange(val low: Option[Quantity],
                     val high: Option[Quantity],
                     val `type`: Option[CodeableConcept],
                     val appliesTo: Option[List[CodeableConcept]],
                     val age: Option[Range],
                     val text: Option[String])

object RelatedType extends Enumeration {
    type RelatedType = Value
    val `has-member`, `derived-from`, `sequel-to`, replaces,
    `qualified-by`, `interfered-by` = Value
}

import RelatedType._

class Related(val `type`: Option[String],
              val target: Reference)

class ObservationComponent(val code: CodeableConcept,
                           val valueQuantity: Option[Quantity],
                           val valueCodeableConcept: Option[CodeableConcept],
                           val valueString: Option[String],
                           val valueRange: Option[Range],
                           val valueRatio: Option[Ratio],
                           val valueSampledData: Option[SampledData],
                           val valueAttachment: Option[Attachment],
                           val valueTime: Option[LocalTime],
                           val valueDateTime: Option[DateTime],
                           val valuePeriod: Option[Period],
                           val dataAbsentReason: Option[CodeableConcept],
                           val interpretation: Option[CodeableConcept],
                           val referenceRange: Option[List[ReferenceRange]])

class Observation(override val id: Option[String],
                  override val contained: Option[List[Resource]],
                  override val meta: Option[Meta],
                  override val extension: Option[List[Extension]],
                  override val identifier: Option[List[Identifier]],
                  val basedOn: Option[List[Reference]],
                  val status: String,
                  val category: Option[List[CodeableConcept]],
                  val code: CodeableConcept,
                  val subject: Option[Reference],
                  val context: Option[Reference],
                  val effectiveDateTime: Option[DateTime],
                  val effectivePeriod: Option[Period],
                  val issued: Option[DateTime],
                  val performer: Option[List[Reference]],
                  val valueQuantity: Option[Quantity],
                  val valueCodeableConcept: Option[CodeableConcept],
                  val valueString: Option[String],
                  val valueBoolean: Option[Boolean],
                  val valueRange: Option[Range],
                  val valueRatio: Option[Ratio],
                  val valueSampledData: Option[SampledData],
                  val valueAttachment: Option[Attachment],
                  val valueTime: Option[LocalTime],
                  val valueDateTime: Option[DateTime],
                  val valuePeriod: Option[Period],
                  val dataAbsentReason: Option[CodeableConcept],
                  val interpretation: Option[CodeableConcept],
                  val comment: Option[String],
                  val bodySite: Option[CodeableConcept],
                  val method: Option[CodeableConcept],
                  val specimen: Option[Reference],
                  val device: Option[Reference],
                  val referenceRange: Option[ReferenceRange],
                  val related: Option[List[Related]],
                  val component: Option[List[ObservationComponent]]
                 ) extends Resource("Observation", id, contained, meta, extension, identifier) {

}
