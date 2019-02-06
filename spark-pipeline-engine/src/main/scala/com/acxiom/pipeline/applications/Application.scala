package com.acxiom.pipeline.applications

import com.acxiom.pipeline.{DefaultPipeline, PipelineParameters}

/**
  * Represents the configuration for a Spark application.
  *
  * @param executions         List of executions that will be used to generate the Execution Plan
  * @param stepPackages       List of packages where step objects are located
  * @param globals            A default set of globals
  * @param pipelineListener   The pipeline listener class to use while processing
  * @param securityManager    An alternate security manager class to use while processing
  * @param stepMapper         An alternate pipeline step mapper class to use while processing
  * @param pipelineParameters A default set of pipeline parameters to make available while processing
  * @param sparkConf          Configuration items to set on the SparkConf when it is created.
  */
case class Application(executions: Option[List[Execution]],
                       stepPackages: Option[List[String]],
                       globals: Option[Map[String, Any]],
                       pipelineListener: Option[ClassInfo] = None,
                       securityManager: Option[ClassInfo] = None,
                       stepMapper: Option[ClassInfo] = None,
                       pipelineParameters: Option[PipelineParameters] = None,
                       sparkConf: Option[Map[String, Any]] = None)

/**
  * Represents a single execution of a Spark application
  *
  * @param id                 The unique id of this execution to be used by dependent executions
  * @param pipelines          A list of pipelines to execute
  * @param initialPipelineId  The id of the first pipeline that should be executed
  * @param globals            A default set of globals for this execution
  * @param pipelineListener   The pipeline listener class to use while processing this execution
  * @param securityManager    An alternate security manager class to use while processing this execution
  * @param stepMapper         An alternate pipeline step mapper class to use while processing this execution
  * @param pipelineParameters A default set of pipeline parameters to make available while processing this execution
  */
case class Execution(id: Option[String],
                     pipelines: Option[List[DefaultPipeline]],
                     initialPipelineId: Option[String] = None,
                     globals: Option[Map[String, Any]] = None,
                     parents: Option[List[String]] = None,
                     pipelineListener: Option[ClassInfo] = None,
                     securityManager: Option[ClassInfo] = None,
                     stepMapper: Option[ClassInfo] = None,
                     pipelineParameters: Option[PipelineParameters] = None)

/**
  * Contains information about a class that needs to be instantiated at runtime
  *
  * @param className The fully qualified class name
  * @param parameters A map of simple parameters to pass to the constructor
  */
case class ClassInfo(className: Option[String], parameters: Option[Map[String, Any]] = None)