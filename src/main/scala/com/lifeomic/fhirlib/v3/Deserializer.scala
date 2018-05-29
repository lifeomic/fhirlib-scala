package com.lifeomic.fhirlib.v3

import com.fasterxml.jackson.databind.ser.std.EnumSerializer
import com.lifeomic.fhirlib.v3.resources._
import com.lifeomic.fhirlib.v3.datatypes._
import org.json4s.{CustomSerializer, DefaultFormats, Formats, JNothing, JNull, JString}
import org.joda.time.{DateTime, DateTimeZone}
import org.json4s._
import org.json4s.ext.EnumNameSerializer
import org.json4s.jackson.Serialization.{read, write}

case object DateTimeSerializer extends CustomSerializer[DateTime](format => (
    {
        case JString(s) => {
            new DateTime(s)
        }
        case _ => null
    },
    {
        case d: DateTime => JString(format.dateFormat.format(d.toDate))
    }
))

private object ResourceSerializer extends Serializer[Resource] {
    private val ParentClass = classOf[Resource]

    def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), Resource] = {
        case (TypeInfo(ParentClass, _), json) => json match {
            case JObject(JField("resourceType", JString(resourceType)) :: _) => resourceType match {
                case "Condition" => json.extract[Condition]
                case "MedicationAdministration" => json.extract[MedicationAdministration]
                case "MedicationRequest" => json.extract[MedicationRequest]
                case "MedicationStatement" => json.extract[MedicationStatement]
                case "Observation" => json.extract[Observation]
                case "Patient" => json.extract[Patient]
                case "Procedure" => json.extract[Procedure]
                case "Schedule" => json.extract[Schedule]
                case "Specimen" => json.extract[Specimen]
                case "Medication" => json.extract[Medication]
                case _ => new Resource(resourceType, None, None, None, None, None)
            }
            case _ => throw new MappingException("Unsupported resourceType")
        }
    }

    def serialize(implicit format: Formats): PartialFunction[Any, JValue] = Map()
}

object Deserializer {
    def loadFhirResource(jsonString: String) : Resource = {
        DateTimeZone.setDefault(DateTimeZone.UTC);

        implicit val formats: Formats = DefaultFormats +
            ResourceSerializer +
            UriSerializer +
            DateTimeSerializer +
            new EnumNameSerializer(ClinicalStatus) +
            new EnumNameSerializer(Gender) +
            new EnumNameSerializer(HumanName_Use) +
            new EnumNameSerializer(Identifier_Use) +
            new EnumNameSerializer(MedicationAdministrationStatus) +
            new EnumNameSerializer(MedicationRequestIntent) +
            new EnumNameSerializer(MedicationRequestPriority) +
            new EnumNameSerializer(MedicationRequestStatus) +
            new EnumNameSerializer(MedicationStatementStatus) +
            new EnumNameSerializer(ObservationStatus) +
            new EnumNameSerializer(RelatedType) +
            new EnumNameSerializer(Taken) +
            new EnumNameSerializer(VerificationStatus) +
            new EnumNameSerializer(ProcedureStatus) +
            new EnumNameSerializer(Specimen_Status) +
            new EnumNameSerializer(MedicationStatus)

        read[Resource](jsonString)
    }
}
