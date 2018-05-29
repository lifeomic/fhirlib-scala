package com.lifeomic.fhirlib.v3.datatypes

class CodeableConcept(val coding: Option[List[Coding]],
                      val text: Option[String]) {

    def flatten(): Seq[Any] = {
        coding.map(_.headOption.map(_.flatten).getOrElse(Seq())).getOrElse(Seq()) + text.orNull
    }

}
