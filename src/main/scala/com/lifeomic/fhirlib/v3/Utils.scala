package com.lifeomic.fhirlib.v3

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import com.github.fge.jsonschema.core.report.ProcessingReport
import com.github.fge.jsonschema.main.JsonSchemaFactory

object Utils {
    def loadResource(name: String): JsonNode = {
        val mapper = new ObjectMapper
        val file = scala.io.Source.getClass().getResource(name)
        mapper.readTree(file)
    }

    def validateSchema(jsonString: String, schemaName: String): Boolean = {
        val mapper = new ObjectMapper
        val json = mapper.readTree(jsonString)

        val jsonSchema = Utils.loadResource(schemaName)
        val factory = JsonSchemaFactory.byDefault
        val schema = factory.getJsonSchema(jsonSchema)

        var report : ProcessingReport = null
        report = schema.validate(json)
        println(report)
        report.isSuccess()
    }
}
