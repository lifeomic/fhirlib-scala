package com.lifeomic.fhirlib.v3.resources

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.junit.{Assert, Test}

class ResourceTest {

  val mapper: ObjectMapper =
    new ObjectMapper()
      .registerModule(DefaultScalaModule)

  @Test
  def findCodes(): Unit = {

    val url = "http://hl7.org/fhir/us/core/StructureDefinition/us-core-race"
    val system = "http://hl7.org/fhir/v3/Race"

    val codes = Seq("1002-5", "1004-1", "1010-8", "1011-6")

    val coding =
      codes
        .map(code => {
          s"""
             |{
             |  "system": "$system",
             |  "code": "$code"
             |}
           """.stripMargin
        })
        .mkString("[", ",", "]")

    val json: String =
      s"""
         |{
         |  "extension": [
         |    {
         |      "url": "$url",
         |      "valueCodeableConcept": {
         |        "coding": $coding
         |      }
         |    }
         |  ]
         |}
      """.stripMargin

    val resource = mapper.readValue(json, classOf[Resource])

    val res = resource.findCodes(url, system).getOrElse(Assert.fail())

    Assert.assertEquals(codes, res)
  }

}
