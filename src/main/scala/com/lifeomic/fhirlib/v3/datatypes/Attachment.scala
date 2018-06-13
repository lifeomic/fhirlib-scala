package com.lifeomic.fhirlib.v3.datatypes

import com.google.common.primitives.UnsignedInteger
import org.joda.time.DateTime

class Attachment(val contentType: Option[String],
                 val language: Option[String],
                 val data: Option[String],
                 val url: Option[String],
                 val size: Option[UnsignedInteger],
                 val hash: Option[String],
                 val title: Option[String],
                 val creation: Option[DateTime]) {

}
