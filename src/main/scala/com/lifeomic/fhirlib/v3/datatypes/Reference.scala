package com.lifeomic.fhirlib.v3.datatypes

class Reference(val reference: Option[String],
                val identifier: Option[Identifier],
                val display: Option[String]) {
  def getId(): Option[String] = {
    try {
      val uri = new java.net.URI(reference.get)
      if (uri.getFragment != null) {
        return Some(uri.getFragment)
      }
      uri.getPath.split("/").lastOption
    } catch {
      case _: Throwable => None
    }
  }
}
