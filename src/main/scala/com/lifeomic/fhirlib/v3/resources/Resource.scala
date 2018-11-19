package com.lifeomic.fhirlib.v3.resources

import com.lifeomic.fhirlib.v3.datatypes._

import scala.reflect.ClassTag

class Resource(val resourceType: String,
               val id: Option[String],
               val contained: Option[List[Resource]],
               val meta: Option[Meta],
               val extension: Option[List[Extension]],
               val identifier: Option[List[Identifier]]) {

  /**
    * Finds the identifier values for the provided system.
    *
    * @param system a system
    * @return [[String]] [[Option]]
    */
  def getIdentifiers(system: String): Option[Seq[String]] = {
    identifier.map(identifiers => {
      identifiers
        .filter(identifier => identifier.system.exists(_.equals(system)))
        .flatMap(identifier => identifier.value)
    })
  }

  /**
    * Finds the identifier value for any instance of the provided system.
    *
    * @deprecated - use [[Resource.getIdentifiers()]] instead
    *
    * @param system a system
    * @return [[String]] [[Option]]
    */
  @Deprecated
  def getIdentifier(system: String): Option[String] = {
    identifier.flatMap(identifiers => {
      identifiers
        .find(identifier => identifier.system.exists(_.equals(system)))
        .flatMap(identifier => identifier.value)
    })
  }

  /**
    *
    * @todo - Handle missing [[contained]]
    * @todo - Remove try-catch [[Throwable]]
    *
    * @param ev
    * @tparam T
    * @return
    */
  def getContained[T <: Resource]()(implicit ev: ClassTag[T]): List[T] = {
    try {
      contained.get.collect {
        case x: T => x
      }
    } catch {
      case _: Throwable => List[T]()
    }
  }

  /**
    *
    * @todo - Handle missing [[contained]]
    * @todo - Remove try-catch [[Throwable]]
    * @todo - Handle multiple [[Coding]] values
    *
    * @param id
    * @return
    */
  def getContained(id: String): Option[Resource] = {
    try {
      val res = contained.get.filter(x => x.id.orNull == id)
      return res.headOption
    } catch {
      case _: Throwable => None
    }
  }

  /**
    * @todo - Migrate to [[Patient]] companion object
    * @todo - Remove [[Option]] type for argument for systemContainsOpts
    * @todo - handle multiple [[Coding]] values
    *
    * @param urlContains
    * @param systemContainsOpts
    * @return
    */
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
