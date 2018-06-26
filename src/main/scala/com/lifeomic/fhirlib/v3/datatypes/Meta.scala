package com.lifeomic.fhirlib.v3.datatypes

import java.time.LocalDateTime

class Meta(val versionId: Option[String],
           val lastUdpated: Option[LocalDateTime],
           val profile: Option[List[String]],
           val security: Option[List[Coding]],
           val tag: Option[List[Coding]]) {
}
