package com.lifeomic.fhirlib.v3.datatypes

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
      val uri = new java.net.URI(reference)
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
  }
}
