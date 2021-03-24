package com.lifeomic.fhirlib.v3

import java.time.format.DateTimeFormatter

import com.lifeomic.fhirlib.v3.resources._
import org.junit.Assert
import org.junit.runner.RunWith
import org.scalactic.TolerantNumerics
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TestSuite extends FunSuite {
  implicit val doubleEq = TolerantNumerics.tolerantDoubleEquality(1e-8)

  test("Test Patient") {
    testPatient("/Patient.test.json")
  }

  test("Test Patient with different race and ethnicity system") {
    testPatient("/PatientUri.test.json")
  }

  test("Test Specimen") {
    val json = scala.io.Source.fromFile(getClass.getResource("/Specimen.test.json").getFile).mkString
    val specimen = Deserializer.loadFhirResource(json).asInstanceOf[Specimen]

    assert(specimen.status.get == "available")
    assert(specimen.findIdentifiers("http://ehr.acme.org/identifiers/collections").head.head == "23234352356")
    assert(specimen.subject.get.findId().get == "example")
    assert(specimen.identifier.get.length == 1)
    assert(specimen.identifier.get.head.value.get == "23234352356")
    assert(specimen.identifier.get.head.system.get == "http://ehr.acme.org/identifiers/collections")
    assert(specimen.`type`.get.coding.get.head.code.get == "122555007")
    assert(specimen.`type`.get.coding.get.head.system.get == "http://snomed.info/sct")
    assert(specimen.`type`.get.coding.get.head.display.get == "Venous blood specimen")
  }

  test("Test Condition") {
    val json = scala.io.Source.fromFile(getClass.getResource("/Condition.test.json").getFile).mkString
    val resource = Deserializer.loadFhirResource(json).asInstanceOf[Condition]

    assert(resource.id.get == "example")
    assert(resource.clinicalStatus.get.toString == ClinicalStatus.active.toString)
    assert(resource.verificationStatus.get == "confirmed")
    assert(resource.subject.get.reference.get == "Patient/example")
    assert(resource.subject.get.findId().get == "example")
    assert(resource.onsetDateTime.map(d => d.getYear).getOrElse(Assert.fail()) == 2012)
  }

  test("Test Condition with uuid urn subject reference") {
    val json = scala.io.Source.fromFile(getClass.getResource("/ConditionUuid.test.json").getFile).mkString
    val resource = Deserializer.loadFhirResource(json).asInstanceOf[Condition]

    assert(resource.id.get == "example")
    assert(resource.clinicalStatus.get.toString == ClinicalStatus.active.toString)
    assert(resource.verificationStatus.get == "confirmed")
    assert(resource.subject.get.findId().get == "f7fd3fc5-b34e-42dc-bd39-da51cdc136df")
    assert(resource.onsetDateTime.map(d => d.getYear).getOrElse(Assert.fail()) == 2012)
  }

  test("Test MedicationAdministration") {
    val json = scala.io.Source.fromFile(getClass.getResource("/MedicationAdministration.test.json").getFile).mkString
    val resource = Deserializer.loadFhirResource(json).asInstanceOf[MedicationAdministration]
    assert(resource.id.get == "medadmin0301")
    assert(resource.medicationReference.get.findId().get == "med0301")
    assert(resource.status.get == "in-progress")
  }

  test("Test MedicationStatement2") {
    val json = scala.io.Source.fromFile(getClass.getResource("/MedicationStatement2.test.json").getFile).mkString
    val resource = Deserializer.loadFhirResource(json).asInstanceOf[MedicationStatement]

    assert(resource.id.isEmpty)
    val med = resource.findContained(resource.medicationReference.get.findId().get).head.head
    assert(med.id.get == "56bfdc47-a07a-4e07-9c40-baabf30bdfed")
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
    assert(resource.subject.get.findId().get == "example")
    assert(resource.performedDateTime.map(d => d.format(DateTimeFormatter.ofPattern( "yyyy-MM-dd"))).getOrElse(Assert.fail()) == "2013-04-05")
    assert(resource.performedPeriod.isEmpty)
    assert(resource.note.get.head.text.get == "Routine Appendectomy. Appendix was inflamed and in retro-caecal position")
  }

  test("Test Coverage") {
    val json = scala.io.Source.fromFile(getClass.getResource("/Coverage.test.json").getFile).mkString
    val resource = Deserializer.loadFhirResource(json).asInstanceOf[Coverage]

    assert(resource.id.get == "9876B1")
    assert(resource.status.get == "active")
    assert(resource.`type`.get.coding.get.head.system.get.toString == "http://hl7.org/fhir/v3/ActCode")
    assert(resource.`type`.get.coding.get.head.code.get == "EHCPOL")
    assert(resource.`type`.get.coding.get.head.display.get == "extended healthcare")
    assert(resource.beneficiary.get.findId().get == "4")
    assert(resource.period.get.start.map(d => d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).getOrElse(Assert.fail()) == "2011-05-23")
    assert(resource.period.get.end.map(d => d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).getOrElse(Assert.fail()) == "2012-05-23")
    assert(resource.grouping.get.subPlan.get == "P7")
  }

  test("Test Claim") {
    val json = scala.io.Source.fromFile(getClass.getResource("/Claim.test.json").getFile).mkString
    val resource = Deserializer.loadFhirResource(json).asInstanceOf[Claim]

    assert(resource.id.get == "MED-00050")
    assert(resource.status.get == "active")
    assert(resource.`type`.get.coding.get.head.code.get == "institutional")
    assert(resource.use.get == "complete")
    assert(resource.insurer.get.display.get == "Humana Inc.")
    assert(resource.careTeam.get.head.provider.get.reference.get == "Practitioner/example")
    assert(resource.diagnosis.get.head.diagnosisCodeableConcept.get.coding.get.head.code.get == "M96.1")
    assert(resource.item.get.head.service.get.coding.get.head.code.get == "62264")
    assert(resource.item.get.head.net.get.value.get == 12500.0)
    assert(resource.total.get.value.get == 12500.0)
  }

  test("Test ExplanationOfBenefit") {
    val json = scala.io.Source.fromFile(getClass.getResource("/ExplanationOfBenefit.test.json").getFile).mkString
    val resource = Deserializer.loadFhirResource(json).asInstanceOf[ExplanationOfBenefit]

    assert(resource.id.get == "EB3500")
    assert(resource.status.get == "active")
    assert(resource.`type`.get.coding.get.head.code.get == "oral")
    assert(resource.payee.get.`type`.get.coding.get.head.code.get == "provider")
    assert(resource.careTeam.get.head.provider.get.reference.get == "Practitioner/example")
    assert(resource.item.get.head.servicedDate.map(d => d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).getOrElse(Assert.fail()) == "2014-08-16")
    assert(resource.item.get.head.adjudication.get.head.category.get.coding.get.head.code.get == "eligible")
    assert(resource.totalCost.get.value.get == 135.57)
    assert(resource.totalBenefit.get.value.get == 96.0)
  }

  test("Test Sequence") {
    val json = scala.io.Source.fromFile(getClass.getResource("/Sequence.json").getFile).mkString
    val resource = Deserializer.loadFhirResource(json).asInstanceOf[Sequence]

    assert(resource.id.get == "fda-vcf-comparison")
    assert(resource.coordinateSystem.get == 1)
    assert(resource.referenceSeq.get.referenceSeqId.get.coding.get.head.system.get == "http://www.ncbi.nlm.nih.gov/nuccore")
    assert(resource.referenceSeq.get.referenceSeqId.get.coding.get.head.code.get == "NC_000001.11")
    assert(resource.referenceSeq.get.strand.get == 1)
    assert(resource.referenceSeq.get.windowStart.get == 10453)
    assert(resource.referenceSeq.get.windowEnd.get == 101770080)
    assert(resource.variant.get.head.start.get == 13116)
    assert(resource.variant.get.head.end.get == 13117)
    assert(resource.variant.get.head.observedAllele.get == "T")
    assert(resource.variant.get.head.referenceAllele.get == "G")
    assert(resource.quality.get.head.`type`.get == "unknown")
    assert(resource.quality.get.head.standardSequence.get.coding.get.head.system.get == "https://precision.fda.gov/files/")

    assert(resource.quality.get.head.standardSequence.get.coding.get.head.code.get == "file-BkZxBZ00bpJVk2q6x43b1YBx")

    assert(resource.quality.get.head.start.get == 10453)

    assert(resource.quality.get.head.end.get == 101770080)
    assert(resource.quality.get.head.score.get.value.get == 5.000)
    assert(resource.quality.get.head.method.get.coding.get.head.system.get == "https://precision.fda.gov/apps/")
    assert(resource.quality.get.head.method.get.coding.get.head.code.get == "app-BqB9XZ8006ZZ2g5KzGXP3fpq")
    assert(resource.quality.get.head.method.get.text.get == "VCF Comparison")
    assert(resource.quality.get.head.truthTP.get == 129481)
    assert(resource.quality.get.head.truthFN.get == 3168)
    assert(resource.quality.get.head.queryFP.get == 1507)
    assert(resource.quality.get.head.gtFP.get == 2186)
    assert(resource.quality.get.head.precision.get == 0.9885)
    assert(resource.quality.get.head.fScore.get == 0.9823)
    assert(resource.repository.get.head.`type`.get == "login")
    assert(resource.repository.get.head.url.get == "https://precision.fda.gov/comparisons/1850")
    assert(resource.repository.get.head.name.get == "FDA")
  }

  test("Test Media") {
    val json = scala.io.Source.fromFile(getClass.getResource("/Media.test.json").getFile).mkString
    val media = Deserializer.loadFhirResource(json).asInstanceOf[Media]

    assert(media.`type`.get == "photo")
    assert(media.subject.get.reference.get == "be006e84-83a6-4ad2-bd4e-27ef322960a2")
    assert(media.bodySite.get.coding.get.head.system.get == "http://snomed.info.sct")
    assert(media.bodySite.get.coding.get.head.code.get == "80248007")
    assert(media.height.get == 432)
    assert(media.width.get == 640)
    assert(media.content.get.contentType.get == "application/jpg")
    assert(media.content.get.url.get == "http://breast-cancer.ca/wp-content/uploads/2014/11/Fig-2-9-IBC-mammogram.jpg")
    assert(media.id.get == "05f392d5-7b16-4990-816e-610508d4e0b0")
    assert(media.meta.get.tag.get.head.system.get == "http://lifeomic.com/fhir/dataset")
    assert(media.meta.get.tag.get.head.code.get == "b5affd0b-329c-4e73-a9b5-122938462ce9")
  }


  private def testPatient(res: String) = {
    val json = scala.io.Source.fromFile(getClass.getResource(res).getFile).mkString
    val patient = Deserializer.loadFhirResource(json).asInstanceOf[Patient]

    assert(patient.gender.orNull == Gender.female)
    assert(patient.birthDate.map(d => d.getYear).getOrElse(Assert.fail()) == 1993)
    assert(patient.birthDate.map(d => d.getMonthValue).getOrElse(Assert.fail()) == 5)
    assert(patient.birthDate.map(d => d.getDayOfMonth).getOrElse(Assert.fail()) == 7)
    val uri = new java.net.URI(patient.meta.orNull.tag.get.head.system.get)
    assert(uri.getHost == "lifeomic.com")
    assert(uri.getPath == "/fhir/dataset")

    assert(patient.findCurrentAge().get >= 25)
  }
}
