package com.lifeomic.fhirlib.v3

import java.time.format.DateTimeFormatter
import java.time.{LocalDateTime, ZoneId, ZonedDateTime}

import com.fasterxml.jackson.databind.DeserializationFeature
import com.lifeomic.fhirlib.v3.datatypes._
import com.lifeomic.fhirlib.v3.resources.{Resource, _}
import org.json4s.ext.EnumNameSerializer
import org.json4s.jackson.Serialization.read
import org.json4s.{CustomSerializer, DefaultFormats, Formats, JString, JValue, TypeInfo, _}

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
            new Resource(s"$resourceType Not Implemented", None, None, None, None, None)
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

object EmptyReferenceSerializer extends Serializer[Reference] {
    private val ReferenceClass = classOf[Reference]

    override def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), Reference] = {
        new PartialFunction[(TypeInfo, JValue), Reference] {

            def isDefinedAt(infoValue: (TypeInfo, JValue)): Boolean = {
                infoValue match {
                    case (TypeInfo(ReferenceClass, _), json) => json match {
                        case x: JArray => x.arr.isEmpty
                        case _ => false
                    }
                    case _ => false
                }
            }

            def apply(infoValue: (TypeInfo, JValue)) = {
                new Reference(None, None, None)
            }
        }
    }

    override def serialize(implicit format: Formats): PartialFunction[Any, JValue] = Map()
}

object UnwrapStringSerializer extends Serializer[String] {
    private val TargetClass = classOf[String]

    override def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), String] = {
        new PartialFunction[(TypeInfo, JValue), String] {

            def isDefinedAt(infoValue: (TypeInfo, JValue)): Boolean = {
                infoValue match {
                    case (TypeInfo(TargetClass, _), json) => json match {
                        case x: JArray => x.arr.length == 1 && x.arr.head.isInstanceOf[JString]
                        case _ => false
                    }
                    case _ => false
                }
            }

            def apply(infoValue: (TypeInfo, JValue)) = {
                infoValue._2 match {
                    case x: JArray => x.arr.head match {
                        case v: JString => v.s
                        case _ => throw new MappingException(s"Nested value is not a $TargetClass")
                    }
                    case _ => throw new MappingException("Invalid type")
                }
            }
        }
    }

    override def serialize(implicit format: Formats): PartialFunction[Any, JValue] = Map()
}

object UnwrapIntSerializer extends Serializer[Int] {
    private val TargetClass = classOf[Int]

    override def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), Int] = {
        new PartialFunction[(TypeInfo, JValue), Int] {

            def isDefinedAt(infoValue: (TypeInfo, JValue)): Boolean = {
                infoValue match {
                    case (TypeInfo(TargetClass, _), json) => json match {
                        case x: JArray => x.arr.length == 1 && x.arr.head.isInstanceOf[JInt]
                        case _ => false
                    }
                    case _ => false
                }
            }

            def apply(infoValue: (TypeInfo, JValue)) = {
                infoValue._2 match {
                    case x: JArray => x.arr.head match {
                        case v: JInt => v.num.intValue()
                        case _ => throw new MappingException(s"Nested value is not a $TargetClass")
                    }
                    case _ => throw new MappingException("Invalid type")
                }
            }
        }
    }

    override def serialize(implicit format: Formats): PartialFunction[Any, JValue] = Map()
}

object UnwrapLongSerializer extends Serializer[Long] {
    private val TargetClass = classOf[Long]

    override def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), Long] = {
        new PartialFunction[(TypeInfo, JValue), Long] {

            def isDefinedAt(infoValue: (TypeInfo, JValue)): Boolean = {
                infoValue match {
                    case (TypeInfo(TargetClass, _), json) => json match {
                        case x: JArray => x.arr.length == 1 && x.arr.head.isInstanceOf[JLong]
                        case _ => false
                    }
                    case _ => false
                }
            }

            def apply(infoValue: (TypeInfo, JValue)) = {
                infoValue._2 match {
                    case x: JArray => x.arr.head match {
                        case v: JLong => v.num
                        case _ => throw new MappingException(s"Nested value is not a $TargetClass")
                    }
                    case _ => throw new MappingException("Invalid type")
                }
            }
        }
    }

    override def serialize(implicit format: Formats): PartialFunction[Any, JValue] = Map()
}

object UnwrapDoubleSerializer extends Serializer[Double] {
    private val TargetClass = classOf[Double]

    override def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), Double] = {
        new PartialFunction[(TypeInfo, JValue), Double] {

            def isDefinedAt(infoValue: (TypeInfo, JValue)): Boolean = {
                infoValue match {
                    case (TypeInfo(TargetClass, _), json) => json match {
                        case x: JArray => x.arr.length == 1 && x.arr.head.isInstanceOf[JDouble]
                        case _ => false
                    }
                    case _ => false
                }
            }

            def apply(infoValue: (TypeInfo, JValue)) = {
                infoValue._2 match {
                    case x: JArray => x.arr.head match {
                        case v: JDouble => v.num
                        case _ => throw new MappingException(s"Nested value is not a $TargetClass")
                    }
                    case _ => throw new MappingException("Invalid type")
                }
            }
        }
    }

    override def serialize(implicit format: Formats): PartialFunction[Any, JValue] = Map()
}

object UnwrapBooleanSerializer extends Serializer[Boolean] {
    private val TargetClass = classOf[Boolean]

    override def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), Boolean] = {
        new PartialFunction[(TypeInfo, JValue), Boolean] {

            def isDefinedAt(infoValue: (TypeInfo, JValue)): Boolean = {
                infoValue match {
                    case (TypeInfo(TargetClass, _), json) => json match {
                        case x: JArray => x.arr.length == 1 && x.arr.head.isInstanceOf[JBool]
                        case _ => false
                    }
                    case _ => false
                }
            }

            def apply(infoValue: (TypeInfo, JValue)) = {
                infoValue._2 match {
                    case x: JArray => x.arr.head match {
                        case v: JBool => v.value
                        case _ => throw new MappingException(s"Nested value is not a $TargetClass")
                    }
                    case _ => throw new MappingException("Invalid type")
                }
            }
        }
    }

    override def serialize(implicit format: Formats): PartialFunction[Any, JValue] = Map()
}

object Deserializer {
  org.json4s.jackson.JsonMethods.mapper.enable(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)

  def loadFhirResource(jsonString: String): Resource = {

    implicit val formats: Formats = DefaultFormats +
      ResourceSerializer +
      DateTimeSerializer +
      EmptyReferenceSerializer +
      UnwrapStringSerializer +
      UnwrapIntSerializer +
      UnwrapLongSerializer +
      UnwrapDoubleSerializer +
      UnwrapBooleanSerializer +
      UnwrapStringSerializer +
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
