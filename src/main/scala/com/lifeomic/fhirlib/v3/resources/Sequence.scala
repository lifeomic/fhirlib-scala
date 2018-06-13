package com.lifeomic.fhirlib.v3.resources

import com.lifeomic.fhirlib.v3.datatypes._

class ReferenceSeq(val chromosome: Option[CodeableConcept],
                   val genomeBuild: Option[String],
                   val referenceSeqId: Option[CodeableConcept],
                   val referenceSeqPointer: Option[Reference],
                   val referenceSeqString: Option[String],
                   val strand: Option[Int],
                   val windowStart: Option[Int],
                   val windowEnd: Option[Int])

class Variant(val start: Option[Int],
              val end: Option[Int],
              val observedAllele: Option[String],
              val referenceAllele: Option[String],
              val cigar: Option[String],
              val variantPointer: Option[Reference])

class Quality(val `type`: Option[String],
              val standardSequence: Option[CodeableConcept],
              val start: Option[Int],
              val end: Option[Int],
              val score: Option[Quantity],
              val method: Option[CodeableConcept],
              val truthTP: Option[Double],
              val queryTP: Option[Double],
              val truthFN: Option[Double],
              val queryFP: Option[Double],
              val gtFP: Option[Double],
              val precision: Option[Double],
              val recall: Option[Double],
              val fScore: Option[Double])

class Repository(val `type`: Option[String],
                 val url: Option[String],
                 val name: Option[String],
                 val datasetId: Option[String],
                 val variantsetId: Option[String],
                 val readsetId: Option[String])


class Sequence(override val id: Option[String],
               override val contained: Option[List[Resource]],
               override val meta: Option[Meta],
               override val extension: Option[List[Extension]],
               override val identifier: Option[List[Identifier]],
               val `type`: Option[String],
               val coordinateSystem: Option[Int],
               val patient: Option[Reference],
               val specimen: Option[Reference],
               val performer: Option[Reference],
               val quantity: Option[Quantity],
               val referenceSeq: Option[ReferenceSeq],
               val variant: Option[List[Variant]],
               val observedSeq: Option[String],
               val quality: Option[List[Quality]],
               val readCoverage: Option[Int],
               val repository: Option[List[Repository]],
               val pointer: Option[List[Reference]]
              ) extends Resource("Sequence", id, contained, meta, extension, identifier) {
}
