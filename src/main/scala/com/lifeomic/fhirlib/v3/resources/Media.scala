package com.lifeomic.fhirlib.v3.resources

import java.time.LocalDateTime

import com.lifeomic.fhirlib.v3.datatypes._

object MediaType extends Enumeration {
    type MediaType = Value
    val photo, video, audio = Value
}


class Media(
               override val id: Option[String],
               override val contained: Option[List[Resource]],
               override val meta: Option[Meta],
               override val extension: Option[List[Extension]],
               override val identifier: Option[List[Identifier]],
               val basedOn: Option[List[Reference]],
               val `type`: Option[String],
               val subtype: Option[CodeableConcept],
               val view: Option[CodeableConcept],
               val subject: Option[Reference],
               val context: Option[Reference],
               val occurrenceDateTime: Option[LocalDateTime],
               val occurrencePeriod: Option[Period],
               val operator: Option[Reference],
               val reasonCode: Option[List[CodeableConcept]],
               val bodySite: Option[CodeableConcept],
               val device: Option[Reference],
               val height: Option[Int],
               val width: Option[Int],
               val frames: Option[Int],
               val duration: Option[Int],
               val content: Option[Attachment],
               val note: Option[Annotation]
           ) extends Resource("Media", id, contained, meta, extension, identifier)