package com.lifeomic.fhirlib.v3.datatypes

import java.net.URI

class Reference(val reference: Option[String],
                val identifier: Option[Identifier],
                val display: Option[String]) {


  /**
    * Finds the id from the [[Reference.reference]] if available.
    *
    * @return [[Some]] id
    */
  def findId(): Option[String] = {
    reference.flatMap(reference => {
      getUriMaybe(reference).flatMap(uri => {
        if (uri.getFragment != null) {
          return Some(uri.getFragment)
        } else if (uri.getScheme == "urn") {
          val parts = uri.getSchemeSpecificPart.split(":")
          if (parts(0) == "uuid") {
            return parts.lastOption
          }
        }
        uri.getPath.split("/").lastOption
      })
    })
  }

  def getUriMaybe(reference: String): Option[URI] = {
    try {
      Some(new java.net.URI(reference))
    } catch {
      case _: Throwable => None
    }
  }
}
