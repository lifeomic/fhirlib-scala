package com.lifeomic.fhirlib.v3.datatypes

import java.time.LocalDateTime

import com.google.common.primitives.UnsignedInteger

class Attachment(val contentType: Option[String],
                 val language: Option[String],
                 val data: Option[String],
                 val url: Option[String],
                 val size: Option[UnsignedInteger],
                 val hash: Option[String],
                 val title: Option[String],
                 val creation: Option[LocalDateTime]) {

}
