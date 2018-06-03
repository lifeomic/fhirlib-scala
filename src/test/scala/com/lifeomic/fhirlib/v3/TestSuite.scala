package com.lifeomic.fhirlib.v3

import com.lifeomic.fhirlib.v3.resources._
import org.scalactic.TolerantNumerics
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TestSuite extends FunSuite {
    implicit val doubleEq = TolerantNumerics.tolerantDoubleEquality(1e-8)
    
    test("Test Patient") {
        val json = scala.io.Source.fromFile(getClass.getResource("/Patient.test.json").getFile).mkString
        val patient = Deserializer.loadFhirResource(json).asInstanceOf[Patient]

        assert(patient.gender.orNull == Gender.female)
        assert(patient.birthDate.orNull.year().get()  == 1993)
        assert(patient.birthDate.orNull.monthOfYear().get()  == 5)
        assert(patient.birthDate.orNull.dayOfMonth().get()  == 7)
        assert(patient.meta.orNull.tag.get.head.system.get.getHost()  == "lifeomic.com")
        assert(patient.meta.orNull.tag.get.head.system.get.getPath()  == "/fhir/dataset")

        val raceCoding = patient.getRaceCoding().get
        assert(raceCoding.code.get  == "2106-3")
        assert(raceCoding.display.get  == "White")
        assert(patient.getEthnicityCoding().get.code.get  == "2186-5")
        assert(patient.getEthnicityCoding().get.display.get  == "Nonhispanic")
        assert(patient.getAge().get >= 25)
        assert(patient.getLanguageCodes().head  == "en-US")

        assert(patient.getAddresses().head.getLatitude().get  == 42.183400380260686)
        assert(patient.getAddresses().head.getLongitude().get  == -72.46253600130517)
    }

    test("Test Specimen") {
        val json = scala.io.Source.fromFile(getClass.getResource("/Specimen.test.json").getFile).mkString
        val specimen = Deserializer.loadFhirResource(json).asInstanceOf[Specimen]

        assert(specimen.status.get == "available")
        assert(specimen.getIdentifier("http://ehr.acme.org/identifiers/collections").orNull == "23234352356")
        assert(specimen.getTypeCodings().head.code.get == "122555007")
        assert(specimen.getTypeCodings().head.system.get.toString == "http://snomed.info/sct")
    }

    test("Test Condition") {
        val json = scala.io.Source.fromFile(getClass.getResource("/Condition.test.json").getFile).mkString
        val resource = Deserializer.loadFhirResource(json).asInstanceOf[Condition]

        assert(resource.id.get == "example")
        assert(resource.clinicalStatus.get.toString == ClinicalStatus.active.toString)
        assert(resource.verificationStatus.get == "confirmed")
        assert(resource.subject.get.reference.get.getPath == "Patient/example")
        assert(resource.onsetDateTime.get.year().get() == 2012)
    }

    test("Test MedicationAdministration") {
        val json = scala.io.Source.fromFile(getClass.getResource("/MedicationAdministration.test.json").getFile).mkString
        val resource = Deserializer.loadFhirResource(json).asInstanceOf[MedicationAdministration]

        assert(resource.id.get == "medadmin0301")

        val containedMeds = resource.getContained[Medication]
        assert(containedMeds.length == 1)

        val med = resource.getContained(resource.medicationReference.get.reference.get.getFragment)
        assert(med.get.id.get == "med0301")
        assert(resource.medicationReference.get.getId().get == "med0301")
        assert(resource.status.get == "in-progress")
    }

    test("Test MedicationStatement2") {
        val json = scala.io.Source.fromFile(getClass.getResource("/MedicationStatement2.test.json").getFile).mkString
        val resource = Deserializer.loadFhirResource(json).asInstanceOf[MedicationStatement]

        assert(resource.id.isEmpty)

        val containedMeds = resource.getContained[Medication]
        assert(containedMeds.length == 1)
        val med = resource.getContained(resource.medicationReference.get.getId().get)
        assert(med.get.id.get == "56bfdc47-a07a-4e07-9c40-baabf30bdfed")
        assert(resource.status.isEmpty)
    }

    test("Test Procedure") {
        val json = scala.io.Source.fromFile(getClass.getResource("/Procedure.test.json").getFile).mkString
        val resource = Deserializer.loadFhirResource(json).asInstanceOf[Procedure]

        assert(resource.id.get == "example")
        assert(resource.status.get == "completed")
        assert(resource.code.get.coding.get.head.system.get.toString == "http://snomed.info/sct")
        assert(resource.code.get.coding.get.head.code.get == "80146002")
        assert(resource.code.get.coding.get.head.display.get == "Appendectomy (Procedure)")
        assert(resource.code.get.text.get == "Appendectomy")
        assert(resource.subject.get.getId.get == "example")
        assert(resource.performedDateTime.get.toString("yyyy-MM-dd") == "2013-04-05")
        assert(resource.performedPeriod.isEmpty)
        assert(resource.note.get.head.text.get == "Routine Appendectomy. Appendix was inflamed and in retro-caecal position")
    }
}
