package com.lifeomic.fhirlib.v3

import java.time.format.DateTimeFormatter
import java.time.{LocalDateTime, ZoneId, ZonedDateTime}

import com.lifeomic.fhirlib.v3.datatypes._
import com.lifeomic.fhirlib.v3.resources.{Resource, _}
import org.json4s.ext.EnumNameSerializer
import org.json4s.jackson.Serialization.read
import org.json4s.{CustomSerializer, DefaultFormats, Formats, JString, _}

case object DateTimeSerializer extends CustomSerializer[LocalDateTime](format => ( {
  case JString(s) => {
    try {
      DateUtils.parseLocalDateTime(s).orNull
    } catch {
      case _: Throwable => null
    }
  }
  case _ => null
}, {
  case d: LocalDateTime => JString(ZonedDateTime.of(d, ZoneId.of("UTC")).format(DateTimeFormatter.ISO_DATE_TIME))
}
))

object ResourceSerializer extends Serializer[Resource] {
  private val ParentClass = classOf[Resource]

  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), Resource] = {
    case (TypeInfo(ParentClass, _), json) => json match {
      case x: JObject => {
        x.values.get("resourceType").orNull match {

          case "Condition" => json.extract[Condition]
          case "Media" => json.extract[Media]
          case "Medication" => json.extract[Medication]
          case "MedicationAdministration" => json.extract[MedicationAdministration]
          case "MedicationRequest" => json.extract[MedicationRequest]
          case "MedicationStatement" => json.extract[MedicationStatement]
          case "Observation" => json.extract[Observation]
          case "Patient" => json.extract[Patient]
          case "Procedure" => json.extract[Procedure]
          case "Schedule" => json.extract[Schedule]
          case "Sequence" => json.extract[Sequence]
          case "Specimen" => json.extract[Specimen]

          case resourceType: String => {
            new Resource(s"${resourceType} Not Implemented", None, None, None, None, None)
          }

          case _ => {
            throw new MappingException("This resource does not have a resourceType")
          }
        }
      }
      case _ => throw new MappingException("Invalid resource")
    }
  }

  def serialize(implicit format: Formats): PartialFunction[Any, JValue] = Map()
}

object Deserializer {
  def loadFhirResource(jsonString: String): Resource = {

    implicit val formats: Formats = DefaultFormats +
      ResourceSerializer +
      DateTimeSerializer +
      new EnumNameSerializer(Address_Use) +
      new EnumNameSerializer(Address_Type) +
      new EnumNameSerializer(ClinicalStatus) +
      new EnumNameSerializer(Gender) +
      new EnumNameSerializer(HumanName_Use) +
      new EnumNameSerializer(Identifier_Use) +
      new EnumNameSerializer(MediaType) +
      new EnumNameSerializer(MedicationAdministrationStatus) +
      new EnumNameSerializer(MedicationRequestIntent) +
      new EnumNameSerializer(MedicationRequestPriority) +
      new EnumNameSerializer(MedicationRequestStatus) +
      new EnumNameSerializer(MedicationStatementStatus) +
      new EnumNameSerializer(MedicationStatus) +
      new EnumNameSerializer(ObservationStatus) +
      new EnumNameSerializer(ProcedureStatus) +
      new EnumNameSerializer(RelatedType) +
      new EnumNameSerializer(Specimen_Status) +
      new EnumNameSerializer(Taken) +
      new EnumNameSerializer(VerificationStatus)

    read[Resource](jsonString)
  }
}
