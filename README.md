# JqwikOG Experiments

This repository contains experiments and usage examples for running [JqwikOG](https://github.com/AFungo/jqwikog), a tool for property-based testing whit a random object generator in Java.

## Running the Experiments

To execute the experiments, you need Docker installed on your system. Follow these steps:

### 1. Build the Docker Image
```sh
docker build -t experiments .
```

### 2. Run the Experiments
```sh
docker run -it -v ~/path/to/experimentos.txt:/app/experimentos.txt experiments bash -c "./gradlew build -x :compileTestJava -x :test && python3 script_config.py > /app/experimentos.txt"
```

### 3. Run single example
```sh
docker run -it experiments bash -c "./gradlew test --tests "experiments.randoopTest.PilasTest.pilaSizeTest""
```
## How to Read Experiment Results

The experiment results are stored in plain text files, with each line following the format:

CaseStudy   Time(s)   Attempts   Builders   Timeout(s)

### Field Descriptions:

* CaseStudy	Fully qualified name of the test case. Includes package, class, and test method name.
* Time(s)	Total time taken to run the experiment, in seconds (with decimals).
* Attempts	Number of attempts made to generate valid tests or fulfill the experiment's criteria.
* Builders	Indicates if generation are just with builders.
* Timeout(s)	Maximum time allowed for the experiment to run, in seconds.

## Requirements
- **Docker** installed on your system.
- **Gradle** (inside the container).
- **Python3** (inside the container).

## Contact
If you need more information, feel free to reach out or open an issue in this repository.

