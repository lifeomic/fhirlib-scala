package com.lifeomic.fhirlib.v3.datatypes

object ContactPoint_Use extends Enumeration {
  type ContactPoint_Use = Value
  val phone, fax, email, pager, url, sms, other = Value
}

class ContactPoint(val system: Option[String],
                   val value: Option[String],
                   val use: Option[String],
                   val rank: Option[String],
                   val period: Option[Period]) {

}
