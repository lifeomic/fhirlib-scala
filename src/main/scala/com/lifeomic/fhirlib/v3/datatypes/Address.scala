package com.lifeomic.fhirlib.v3.datatypes

object Address_Use extends Enumeration {
  type Address_Use = Value
  val usual, official, temp, nickname, anonymous, old, maiden = Value
}

object Address_Type extends Enumeration {
  type Address_Type = Value
  val postal, physical, temp, nickname, anonymous, old, maiden = Value
}

class Address(val use: Option[String],
              val `type`: Option[String],
              val text: Option[String],
              val line: Option[List[String]],
              val city: Option[String],
              val district: Option[String],
              val state: Option[String],
              val postalCode: Option[String],
              val country: Option[String],
              val period: Option[Period],
              val extension: Option[List[Extension]]) {

  def getLatitudes(): Option[Seq[Double]] = {
    extension
      .map(extensions => {
        extensions
          .filter(extension => {
            extension.url.exists(url => url.equals("http://hl7.org/fhir/StructureDefinition/geolocation"))
          })
          .flatMap(extension => {
            extension.extension
              .map(extensions => {
                extensions
                  .filter(extension => {
                    extension.url.exists(url => url.equals("latitude"))
                  })
                  .flatMap(extension => extension.valueDecimal)
              })
          })
          .flatten
      })
  }

  @Deprecated
  def getLatitude(): Option[Double] = {
    extension
      .flatMap(extensions => {
        extensions
          .find(extension => {
            extension.url.exists(url => url.equals("http://hl7.org/fhir/StructureDefinition/geolocation"))
          })
          .flatMap(extension => {
            extension.extension
              .flatMap(extensions => {
                extensions
                  .find(extension => {
                    extension.url.exists(url => url.equals("latitude"))
                  })
                  .flatMap(extension => extension.valueDecimal)
              })
          })
      })
  }

  def getLongitudes(): Option[Seq[Double]] = {
    extension
      .map(extensions => {
        extensions
          .filter(extension => {
            extension.url.exists(url => url.equals("http://hl7.org/fhir/StructureDefinition/geolocation"))
          })
          .flatMap(extension => {
            extension.extension
              .map(extensions => {
                extensions
                  .filter(extension => {
                    extension.url.exists(url => url.equals("longitude"))
                  })
                  .flatMap(extension => extension.valueDecimal)
              })
          })
          .flatten
      })
  }

  @Deprecated
  def getLongitude(): Option[Double] = {
    extension
      .flatMap(extensions => {
        extensions
          .find(extension => {
            extension.url.exists(url => url.equals("http://hl7.org/fhir/StructureDefinition/geolocation"))
          })
          .flatMap(extension => {
            extension.extension
              .flatMap(extensions => {
                extensions
                  .find(extension => {
                    extension.url.exists(url => url.equals("longitude"))
                  })
                  .flatMap(extension => extension.valueDecimal)
              })
          })
      })
  }
}
