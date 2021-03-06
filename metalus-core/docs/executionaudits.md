# Execution Audits
The execution audit framework captures metrics at each level of execution: *execution*, *pipeline* and *step*.

## Audit Types
### Execution
This audit is automatically started when the "executionStarted" event is triggered and ends when the "executionFinished"
event is fired. The timing should be inclusive of the combined timings of all executed pipelines.

### Pipeline
This audit is automatically started when the "pipelineStarted" event is triggered and ends when the "pipelineFinished" 
event is fired. The timing should be inclusive of the combined timings of all executed steps.

### Step
This audit is automatically started when the "pipelineStepStarted" event is triggered and ends when the "pipelineStepFinished" 
event is fired.

### Fork Steps
All of the steps contained in a fork, including the join, will be tracked based on the *groupId* and rolled up under the 
fork step for analysis. The *groupId* of the step audit can be used to group all steps involved in a single execution.

## Additional Classes
### PipelineContext
The PipelineContext contains the *rootAudit* which holds all of the data related to the overall execution. Helper 
functions have been provided to make accessing pipeline and step audits easier.

### PipelineListener
Implementations of the PipelineListener interface will have access to the audits through the *PipelineContext*. The *metrics*
functions provided allow any data to be stored/retrieved related to an audit.

## Metrics
Each audit contains a metrics object where metrics can be set with the **setMetric** and **setMetrics** methods.
Metrics for step type audits can additionally be set by providing a named return in the PipelineStepResponse that
follows a naming schema such as: ```$metrics.<metric_name>```
