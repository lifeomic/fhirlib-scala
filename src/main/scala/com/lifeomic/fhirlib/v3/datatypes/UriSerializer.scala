package com.lifeomic.fhirlib.v3.datatypes

import org.json4s.CustomSerializer
import java.net.URI

import org.json4s.JsonAST.JString

case object UriSerializer extends CustomSerializer[URI](format => (
    {
        case JString(s) => new URI(s)
    },
    {
        case x: URI =>
            JString(x.toString)
    }
))