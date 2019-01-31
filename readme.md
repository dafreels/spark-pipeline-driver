|Branch|Build|Coverage|
-------|-----|---------
|Develop|[![Develop Build](https://travis-ci.com/Acxiom/spark-pipeline-driver.svg?branch=develop)](https://travis-ci.com/Acxiom/spark-pipeline-driver?branch=develop)|[![Develop Coverage](https://img.shields.io/coveralls/github/Acxiom/spark-pipeline-driver/develop.svg)](https://coveralls.io/github/Acxiom/spark-pipeline-driver?branch=develop)|
|Master|[![Master Status](https://travis-ci.com/Acxiom/spark-pipeline-driver.svg?branch=master)](https://travis-ci.com/Acxiom/spark-pipeline-driver?branch=master)|[![Master Coverage](https://img.shields.io/coveralls/github/Acxiom/spark-pipeline-driver/master.svg)](https://coveralls.io/github/Acxiom/spark-pipeline-driver?branch=master)|

# Spark Pipeline Driver
The goal of this project is to make writing [Spark](http://spark.apache.org) applications easier by abstracting the logic
into reusable components that can be compiled into a jar, stored on the cluster and then configured dynamically using
external configurations such as JSON.

## Concepts
This section will attempt to provide a high level idea of how the framework achieves the project goals. Visit 
[spark-pipeline-engine](spark-pipeline-engine/readme.md) for more information.

There are several concepts that help achieve the project goal:

### Steps
The step is the smallest unit of work in the application. A step is a single reusable code function that can be executed
by a pipeline. There are two parts to a step, the actual function and the *PipelineStep* metadata. The function should 
define the parameters that are required to execute properly and the metadata is used by the pipeline to define how those
parameters are populated. The return type may be anything including *Unit*, but it is recommended that a 
*PipelineStepResponse* be returned.

### Pipelines
A pipeline is a collection of steps that should be executed in a predefined order. An application may execute one or 
more pipelines as part of an application and are useful when there may be a need to restart processing in an application
without needing to run all of the same logic again.

### Execution Plan
An execution plan allows control over how pipelines are executed. An application may choose to only have a single 
execution that runs one or more pipelines or several executions that run pipelines in parallel or based on a dependency
structure.

### Drivers
Drivers are the entry point into the application. The driver is responsible for processing the input parameters and
initializing the *DriverSetup* for the application.

### Driver Setup
Each provided driver relies on the *DriverSetup* trait. Each project is required to implement this trait and pass the
class name as a command line parameter. The driver will then call the different functions to begin processing data. Visit
[spark-pipeline-engine](spark-pipeline-engine/readme.md) for more information.

![Driver Initialization](docs/images/DefaultPipelineDriver.png "Default Pipeline Driver Flow")

### [Application](spark-pipeline-engine/docs/application.md)
The *Application* framework is a configuration based method of describing the Spark application. This includes defining 
the execution plan, pipelines, pipeline context overrides (*pipeline listener*, *security manager*, *step mapper*) and 
global values.

## Projects
There are several sub-projects:

### [Spark Pipeline Engine](spark-pipeline-engine/readme.md)
This project contains the core library and is the minimum requirement for any application.

### [Common Pipeline Steps](common-pipeline-steps/readme.md)
This component contains steps that are considered generic enough to be used in any project.

### [Streaming Pipeline Drivers](streaming-pipeline-drivers/readme.md)
This component contains drivers classes that connect to various streaming technologies like [Kafka](http://kafka.apache.org/) 
and [Kinesis](https://aws.amazon.com/kinesis/). Each class provides a basic implementation that gathers data and then 
initiates the Spark Pipeline Engine Component for processing of the incoming data.

### [Pipeline Drivers Examples](pipeline-drivers-examples/readme.md)
This project provides several examples to help demonstrate how to use the library.

## Examples
Examples of building pipelines can be found in the [pipeline-drivers-examples](pipeline-drivers-examples/readme.md) project.

## Building
The project is built using [Apache Maven](http://maven.apache.org/).
To build the project run:

	mvn

(This will clean, build, test and package the jars and generate documentation)

## Running tests
Tests are part of the main build.

## Contributing
To contribute back changes, fork the main github repository. Once the changes are ready, create a pull request against
the main develop branch.
