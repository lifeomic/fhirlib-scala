package com.lifeomic.fhirlib.v3.datatypes

object ContactPoint_Use extends Enumeration {
    type ContactPoint_Use = Value
    val phone, fax, email, pager, url, sms, other = Value
}
import ContactPoint_Use._

class ContactPoint(val system: Option[String],
                   val value: Option[String],
                   val use: Option[ContactPoint_Use],
                   val rank: Option[String],
                   val period: Option[Period]) {

}
