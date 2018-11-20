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
  def findIdentifiers(system: String): Option[Seq[String]] = {
    identifier.map(identifiers => {
      identifiers
        .filter(identifier => identifier.system.exists(_.equals(system)))
        .flatMap(identifier => identifier.value)
    })
  }

  /**
    * Finds the identifier value for any instance of the provided system.
    *
    * @deprecated
    * @see [[Resource.findIdentifiers()]]
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
    * @deprecated
    *
    * @param ev
    * @tparam T
    * @return
    */
  @Deprecated
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
    * Finds [[Resource]]s in [[Resource.contained]] with the provided
    * [[Resource.id]] and [[Resource]] type.
    *
    * If [[Resource.contained]] is [[None]], [[None]] will be returned.
    *
    * @param id a [[Resource.id]]
    * @tparam T the [[Resource]] type
    *
    * @return
    */
  def findContained(id: String): Option[Seq[Resource]] = {
    contained.map(contained => {
      contained.filter(resource => {
        resource.id.exists(_.equals(id))
      })
    })
  }

  /**
    *
    * @deprecated
    * @see [[Resource.findContained()]]
    *
    * @param id
    * @return
    */
  @Deprecated
  def getContained(id: String): Option[Resource] = {
    contained.flatMap(contained => {
      contained.find(resource => {
        resource.id.exists(_.equals(id))
      })
    })
  }

  /**
    * Finds [[Coding.code]]s within the [[Resource.extension]]s for the provided
    * [[Extension.url]] and within the [[Extension.valueCodeableConcept]]
    * [[CodeableConcept.coding]] values for the provided [[Coding.system]].
    *
    * If [[Resource.extension]] is [[None]], [[None]] will be returned.
    *
    * @param url    the [[Extension.url]]
    * @param system the [[Coding.system]]
    *
    * @return [[Some]] [[Seq]] of codes if found
    */
  def findCodes(url: String, system: String): Option[Seq[String]] = {
    extension
      .map(extensions => {
        extensions
          .filter(extension => extension.url.exists(_.equals(url)))
          .flatMap(extension => {
            extension.valueCodeableConcept.flatMap(concept => {
              concept.coding.map(codings => {
                codings
                  .filter(coding => coding.system.exists(_.equals(system)))
                  .flatMap(_.code)
              })
            })
          })
          .flatten
      })
  }

  /**
    *
    * @deprecated
    * @see [[Resource.findCodes()]]
    *
    * @param urlContains
    * @param systemContainsOpts
    * @return
    */
  @Deprecated
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
