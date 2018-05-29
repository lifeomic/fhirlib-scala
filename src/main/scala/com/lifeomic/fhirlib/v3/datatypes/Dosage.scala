package com.lifeomic.fhirlib.v3.datatypes

class Dosage(val sequence: Option[Int],
             val text: Option[String],
             val additionalInstruction: Option[List[CodeableConcept]],
             val patientInstruction: Option[List[CodeableConcept]],
             val timing: Option[Timing],
             val asNeededBoolean: Option[Boolean],
             val asNeededCodeableConcept: Option[CodeableConcept],
             val site: Option[CodeableConcept],
             val route: Option[CodeableConcept],
             val method: Option[CodeableConcept],
             val doseRange: Option[Range],
             val doseQuantity: Option[Quantity],
             val maxDosePerPeriod: Option[Ratio],
             val maxDosePerAdministration: Option[Quantity],
             val maxDosePerLifetime: Option[Quantity],
             val rateRatio: Option[Ratio],
             val rateRange: Option[Range],
             val rateQuantity: Option[Quantity]) {

}
