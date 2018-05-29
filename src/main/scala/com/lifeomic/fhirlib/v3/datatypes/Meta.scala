package com.lifeomic.fhirlib.v3.datatypes

import org.joda.time.DateTime
import java.net.URI

class Meta(val versionId: Option[String],
           val lastUdpated: Option[DateTime],
           val profile: Option[List[URI]],
           val security: Option[List[Coding]],
           val tag: Option[List[Coding]]) {
}
