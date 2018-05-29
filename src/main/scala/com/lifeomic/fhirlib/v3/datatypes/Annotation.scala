package com.lifeomic.fhirlib.v3.datatypes

import org.joda.time.DateTime

class Annotation(val authorReference: Option[Reference],
                 val authorString: Option[String],
                 val time: Option[DateTime],
                 val text: String)
