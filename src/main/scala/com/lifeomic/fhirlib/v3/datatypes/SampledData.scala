package com.lifeomic.fhirlib.v3.datatypes

class SampledData(val origin: Quantity,
                  val period: Double,
                  val factor: Option[Double],
                  val lowerLimit: Option[Double],
                  val upperLimit: Option[Double],
                  val dimensions: Option[String],
                  val data: Option[String]) {

}
