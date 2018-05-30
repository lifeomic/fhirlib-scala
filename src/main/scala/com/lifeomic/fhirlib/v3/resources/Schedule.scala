package com.lifeomic.fhirlib.v3.resources

import com.lifeomic.fhirlib.v3.datatypes._

class Schedule(override val id: Option[String],
               override val contained: Option[List[Resource]],
               override val meta: Option[Meta],
               override val extension: Option[List[Extension]],
               override val identifier: Option[List[Identifier]],
               val active: Option[Boolean],
               val serviceCategory: Option[CodeableConcept],
               val serviceType: Option[List[CodeableConcept]],
               val specialty: Option[List[CodeableConcept]],
               val actor: Option[List[Reference]],
               val planningHorizon: Option[Period],
               val comment: Option[String]) extends Resource("Schedule", id, contained, meta, extension, identifier) {
}
