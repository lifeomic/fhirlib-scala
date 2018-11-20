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

}
