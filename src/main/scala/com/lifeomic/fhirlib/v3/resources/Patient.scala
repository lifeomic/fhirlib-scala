package com.lifeomic.fhirlib.v3.resources

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

import com.lifeomic.fhirlib.v3.datatypes._

import scala.collection.mutable.ListBuffer

object Gender extends Enumeration {
  type Gender = Value
  val male, female, other, unknown = Value
}

import com.lifeomic.fhirlib.v3.resources.Gender._

class PatientContact(val relationship: Option[List[CodeableConcept]],
                     val name: Option[HumanName],
                     val telecom: Option[List[ContactPoint]],
                     val address: Option[Address],
                     val gender: Option[Gender],
                     val organization: Option[Reference],
                     val period: Option[Period]) {

}

class Animal(val species: CodeableConcept,
             val breed: Option[CodeableConcept],
             val genderStatus: Option[CodeableConcept]) {

}

class Communication(val language: CodeableConcept,
                    val preferred: Option[Boolean]) {

}

class Link(val other: Option[Reference],
           val `type`: String) {
}

class Patient(override val id: Option[String],
              override val contained: Option[List[Resource]],
              override val meta: Option[Meta],
              override val extension: Option[List[Extension]],
              override val identifier: Option[List[Identifier]],
              val active: Option[Boolean],
              val name: Option[List[HumanName]],
              val telecom: Option[List[ContactPoint]],
              val gender: Option[Gender],
              val birthDate: Option[LocalDateTime],
              val deceasedBoolean: Option[Boolean],
              val deceasedDateTime: Option[LocalDateTime],
              val address: Option[List[Address]],
              val maritalStatus: Option[CodeableConcept],
              val multipleBirthsBoolean: Option[Boolean],
              val multipleBirthsInteger: Option[Int],
              val photo: Option[List[Attachment]],
              val contact: Option[List[PatientContact]],
              val animal: Option[Animal],
              val communication: Option[List[Communication]],
              val generalPractitioner: Option[List[Reference]],
              val managingOrganization: Option[Reference],
              val link: Option[List[Link]]) extends Resource("Patient", id, contained, meta, extension, identifier) {


  /**
    * Finds the current age.
    *
    * @return [[Some]] [[Int]] representing age in years
    */
  def findCurrentAge(): Option[Int] = {
    birthDate.map(date => ChronoUnit.YEARS.between(date, LocalDateTime.now).toInt)
  }

}
