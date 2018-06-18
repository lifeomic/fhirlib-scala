package com.lifeomic.fhirlib.v3

import com.lifeomic.fhirlib.v3.resources._
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
    assert(resource.subject.get.reference.get == "Patient/example")
    assert(resource.onsetDateTime.get.year().get() == 2012)
  }

  test("Test MedicationAdministration") {
    val json = scala.io.Source.fromFile(getClass.getResource("/MedicationAdministration.test.json").getFile).mkString
    val resource = Deserializer.loadFhirResource(json).asInstanceOf[MedicationAdministration]

    assert(resource.id.get == "medadmin0301")

    val containedMeds = resource.getContained[Medication]
    assert(containedMeds.length == 1)

    val med = resource.getContained(resource.medicationReference.get.getId.get)
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


  def testPatient(res: String) = {
    val json = scala.io.Source.fromFile(getClass.getResource(res).getFile).mkString
    val patient = Deserializer.loadFhirResource(json).asInstanceOf[Patient]

    assert(patient.gender.orNull == Gender.female)
    assert(patient.birthDate.orNull.year().get() == 1993)
    assert(patient.birthDate.orNull.monthOfYear().get() == 5)
    assert(patient.birthDate.orNull.dayOfMonth().get() == 7)
    val uri = new java.net.URI(patient.meta.orNull.tag.get.head.system.get)
    assert(uri.getHost() == "lifeomic.com")
    assert(uri.getPath() == "/fhir/dataset")

    val raceCoding = patient.getRaceCoding().get
    assert(raceCoding.code.get == "2106-3")
    assert(raceCoding.display.get == "White")
    assert(patient.getEthnicityCoding().get.code.get == "2186-5")
    assert(patient.getEthnicityCoding().get.display.get == "Nonhispanic")
    assert(patient.getAge().get >= 25)
    assert(patient.getLanguageCodes().head == "en-US")

    assert(patient.getAddresses().head.getLatitude().get == 42.183400380260686)
    assert(patient.getAddresses().head.getLongitude().get == -72.46253600130517)

    val languageCodes = patient.getLanguageCodes()
    assert(languageCodes.length == 1)
    assert(languageCodes.head == "en-US")

    val languageCodings = patient.getLanguageCodings()
    assert(languageCodings.length == 1)

    val languageCoding = languageCodings.head
    assert(languageCoding.system.get == "http://hl7.org/fhir/ValueSet/languages")
    assert(languageCoding.version.isEmpty)
    assert(languageCoding.code.get == "en-US")
    assert(languageCoding.display.get == "English (United States)")
    assert(languageCoding.userSelected.isEmpty)
  }
}
