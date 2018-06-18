package com.lifeomic.fhirlib.v3.resources

import com.lifeomic.fhirlib.v3.datatypes._

import scala.reflect.ClassTag

class Resource(val resourceType: String,
               val id: Option[String],
               val contained: Option[List[Resource]],
               val meta: Option[Meta],
               val extension: Option[List[Extension]],
               val identifier: Option[List[Identifier]]) {


  def getIdentifier(system: String): Option[String] = {
    try {
      identifier.get.filter(_.system.getOrElse("") == system).head.value
    } catch {
      case _: Throwable => None
    }
  }

  def getContained[T <: Resource]()(implicit ev: ClassTag[T]): List[T] = {
    try {
      contained.get.collect {
        case x: T => x
      }
    } catch {
      case _: Throwable => List[T]()
    }
  }

  def getContained(id: String): Option[Resource] = {
    try {
      val res = contained.get.filter(x => x.id.orNull == id)
      return res.headOption
    } catch {
      case _: Throwable => None
    }
  }

  protected def getExtensionCoding(urlContains: String, systemContainsOpts: Option[List[String]]): Option[Coding] = {
    try {
      val ext = extension.get.find(_.url.getOrElse("") contains urlContains)
      val coding = ext.get.valueCodeableConcept.get.coding.get
      systemContainsOpts match {
        case Some(systemContains) =>
          coding.find(x => x.system.nonEmpty && systemContains.exists(x.system.get contains _))
        case None =>
          coding.headOption
      }
    } catch {
      case _: Throwable => None
    }
  }
}
