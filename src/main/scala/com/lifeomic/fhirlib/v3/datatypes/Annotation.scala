package com.lifeomic.fhirlib.v3.datatypes

import java.time.LocalDateTime

class Annotation(val authorReference: Option[Reference],
                 val authorString: Option[String],
                 val time: Option[LocalDateTime],
                 val text: Option[String])
