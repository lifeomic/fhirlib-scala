package com.lifeomic.fhirlib.v3.datatypes

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.junit.{Assert, Test}

class AddressTest {

  val mapper: ObjectMapper =
    new ObjectMapper()
      .registerModule(DefaultScalaModule)

  @Test
  def getLatitudes(): Unit = {

    val json: String =
      """
        |{
        |  "extension": [
        |    {
        |      "url": "http://hl7.org/fhir/StructureDefinition/geolocation",
        |      "extension": [
        |        {
        |          "url": "latitude",
        |          "valueDecimal": 0
        |        }
        |      ]
        |    },
        |    {
        |      "url": "http://hl7.org/fhir/StructureDefinition/geolocation",
        |      "extension": [
        |        {
        |          "url": "latitude",
        |          "valueDecimal": 1
        |        }
        |      ]
        |    }
        |  ]
        |}
      """.stripMargin

    val address: Address = mapper.readValue(json, classOf[Address])

    val latitudes = address.getLatitudes().getOrElse(Assert.fail())

    Assert.assertEquals(Seq(0, 1), latitudes)
  }

  @Test
  @Deprecated
  def getLatitude(): Unit = {

    val valueDecimal: Double = 39.7805336

    val json: String =
      s"""
        |{
        |  "extension": [
        |    {
        |      "url": "http://hl7.org/fhir/StructureDefinition/geolocation",
        |      "extension": [
        |        {
        |          "url": "latitude",
        |          "valueDecimal": $valueDecimal
        |        }
        |      ]
        |    }
        |  ]
        |}
      """.stripMargin

    val address: Address = mapper.readValue(json, classOf[Address])

    val latitude = address.getLatitude().getOrElse(Assert.fail())

    Assert.assertEquals(valueDecimal, latitude)

  }

  @Test
  def getLongitudes(): Unit = {

    val json: String =
      """
        |{
        |  "extension": [
        |    {
        |      "url": "http://hl7.org/fhir/StructureDefinition/geolocation",
        |      "extension": [
        |        {
        |          "url": "longitude",
        |          "valueDecimal": 0
        |        }
        |      ]
        |    },
        |    {
        |      "url": "http://hl7.org/fhir/StructureDefinition/geolocation",
        |      "extension": [
        |        {
        |          "url": "longitude",
        |          "valueDecimal": 1
        |        }
        |      ]
        |    }
        |  ]
        |}
      """.stripMargin

    val address: Address = mapper.readValue(json, classOf[Address])

    val longitudes = address.getLongitudes().getOrElse(Assert.fail())

    Assert.assertEquals(Seq(0, 1), longitudes)
  }

  @Test
  @Deprecated
  def getLongitude(): Unit = {

    val valueDecimal: Double = -86.1654050

    val json: String =
      s"""
        |{
        |  "extension": [
        |    {
        |      "url": "http://hl7.org/fhir/StructureDefinition/geolocation",
        |      "extension": [
        |        {
        |          "url": "longitude",
        |          "valueDecimal": $valueDecimal
        |        }
        |      ]
        |    }
        |  ]
        |}
      """.stripMargin

    val address: Address = mapper.readValue(json, classOf[Address])

    val longitude = address.getLongitude().getOrElse(Assert.fail())

    Assert.assertEquals(valueDecimal, longitude)

  }

}
