package com.lifeomic.fhirlib.v3.resources

import com.lifeomic.fhirlib.v3.datatypes._
import org.joda.time.{DateTime, Years}

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
              val birthDate: Option[DateTime],
              val deceasedBoolean: Option[Boolean],
              val deceasedDateTime: Option[DateTime],
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

  def getRaceCoding(urlContains: String = "us-core-race",
                    systemContains: String = "Race"): Option[Coding] = {
    getExtensionCoding(urlContains, Some(systemContains))
  }

  def getEthnicityCoding(urlContains: String = "us-core-ethnicity",
                         systemContains: String = "Ethnicity"): Option[Coding] = {
    getExtensionCoding(urlContains, Some(systemContains))
  }

  def getLanguageCodings(): List[Coding] = {
    if (communication.isEmpty) {
      return List[Coding]()
    }

    val codings = ListBuffer[Coding]()
    communication.get.foreach(comm => {
      if (comm.language.coding.nonEmpty) {
        comm.language.coding.get.foreach(coding => {
          if (!coding.code.isEmpty) {
            codings += coding
          }
        })
      }
    })
    return codings.toList
  }

  def getLanguageCodes(): List[String] = {
    return getLanguageCodings().map(_.code.get)
  }

  def getAddresses(): List[Address] = {
    if (address.isEmpty) {
      return List[Address]()
    }
    address.get
  }

  def getAge(): Option[Int] = {
    if (birthDate.isEmpty) {
      return None
    }
    return Some(Years.yearsBetween(birthDate.get, DateTime.now).getYears)
  }

  private def getExtensionCoding(urlContains: String, systemContainsOpt: Option[String]): Option[Coding] = {
    try {
      val ext = extension.get.filter(_.url.getOrElse("") contains urlContains).headOption
      val coding = ext.get.valueCodeableConcept.get.coding.get
      systemContainsOpt match {
        case Some(systemContains) =>
          coding.filter(x => x.system.nonEmpty && (x.system.get contains systemContains)).headOption
        case None =>
          coding.headOption
      }
    } catch {
      case _: Throwable => None
    }
  }
}
