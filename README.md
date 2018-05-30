# fhirlib for scala

## Install as a maven dependency
```
<dependency>
  <groupId>com.lifeomic</groupId>
  <artifactId>fhirlib</artifactId>
  <version>0.2.9</version>
</dependency>
```

## Example usage
```
import com.lifeomic.fhirlib.v3.resources._

object Example extends App {

  // Load a FHIR resource into a String variable
  val jsonString = scala.io.Source.fromFile(getClass.getResource("/Patient.json").getFile).mkString

  // Deserialize the json string into a Patient object
  val patient = fhirlib.v3.Deserializer.loadFhirResource(jsonString).asInstanceOf[Patient]

  println(patient.birthDate.get.year().get())
}
```