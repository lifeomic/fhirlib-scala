# fhirlib

A [FHIR](https://www.hl7.org/fhir/index.html) serialization library.

## Getting Started

```scala
import com.lifeomic.fhirlib.v3.Deserializer
import com.lifeomic.fhirlib.v3.resources.Patient

val json = ...
val patient: Patient = Deserializer.loadFhirResource(json).asInstanceOf[Patient]
```

The ScalaDoc is available on [here](https://lifeomic.github.io/fhirlib-scala/)

### Installing

```
<dependency>
    <groupId>com.lifeomic</groupId>
    <artifactId>fhirlib</artifactId>
    <version>${LATEST}</version>
</dependency>
```

Find the latest version [here](https://mvnrepository.com/artifact/com.lifeomic/fhirlib).

## Running the tests

```bash
mvn test
```

## Deployment

```bash
mvn deploy
```

## Built With

* [Scala](https://www.scala-lang.org/) - The Scala Programming Language
* [Json4s](http://json4s.org/) - One AST to rule them all

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/lifeomic/fhirlib-scala/tags). 

## Authors

See also the list of [contributors](https://github.com/lifeomic/fhirlib-scala/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

- Thank you to the HL7 community for working to improve health care data interoperability.
