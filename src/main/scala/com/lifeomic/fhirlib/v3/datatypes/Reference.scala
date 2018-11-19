package com.lifeomic.fhirlib.v3.datatypes

class Reference(val reference: Option[String],
                val identifier: Option[Identifier],
                val display: Option[String]) {

  /**
    * @todo - Remove [[Throwable]] catch
    *
    * @return
    */
  def getId(): Option[String] = {
    try {
      val uri = new java.net.URI(reference.get)
      if (uri.getFragment != null) {
        return Some(uri.getFragment)
      } else if (uri.getScheme == "urn") {
        val parts = uri.getSchemeSpecificPart.split(":");
        if (parts(0) == "uuid") {
          return parts.lastOption;
        }
      }
      uri.getPath.split("/").lastOption
    } catch {
      case _: Throwable => None
    }
  }
}
