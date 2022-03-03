from ops import ngsi_ld_ops
from semantic_tools.models.application import Application
from semantic_tools.models.ngsi_ld.entity import Entity
from semantic_tools.ngsi_ld.client import NGSILDClient


def checkApplicationExistence(task: Application, ngsi: NGSILDClient) -> bool:
    """
    Checking if Task entity has a relationship
    with an already created application entity
    (and the regarding application JAR already
    uploaded in stream processing engine).
    """
    application_entities = ngsi.queryEntities(type="Application")
    application_exists = False
    if len(application_entities) > 0:
        for application_entity in application_entities:
            if application_entity['id'] == task.hasApplication.object:
                application_exists = True
                break
            else:
                application_exists = False
    else:
        application_exists = False

    return application_exists


def checkDataSourceExistence(datasource_id: str, ngsi: NGSILDClient) -> bool:
    """
    Checking if collection agent entity
    (i.e., MetricSource, TelemetrySource, ...)
    has a relationship with a data source entity
    (i.e., Prometheus, Device, ...)
    """
    datasource_entities = ngsi.queryEntities(type=datasource_id.split(":")[2])
    datasource_exists = False
    if len(datasource_entities) > 0:
        for datasource_entity in datasource_entities:
            if datasource_entity['id'] == datasource_id:
                datasource_exists = True
                break
            else:
                datasource_exists = False
    else:
        datasource_exists = False

    return datasource_exists


def checkInputExistence(entity: Entity, ngsi: NGSILDClient) -> bool:
    """
    Checking if different entities in the data pipeline
     have an input relationship with an existing entity.
     This checking between entities is used for two different cases:
    - 1. If dispatch agent has an existing
    input collection or aggregation agent.
    - 2. If aggregation agent has an existing
    input collection or aggregation agent.

    The reason for this check is to verify that the different
    stages of data processing are established between
    correct and existing entities of the data pipeline.
    """
    input_id = entity.hasInput.object
    candidate_input_entities = ngsi.queryEntities(type=input_id.split(":")[2])
    input_exists = False
    if len(candidate_input_entities) > 0:
        for candidate_input_entity in candidate_input_entities:
            if candidate_input_entity['id'] == input_id:
                input_exists = True
                break
            else:
                input_exists = False
    else:
        input_exists = False

    return input_exists


def checkEndpointExistence(endpoint_id: str, ngsi: NGSILDClient) -> bool:
    """
    Checking if data source entity (i.e., Prometheus, Device, ...)
    has a relationship with an Endpoint entity.
    """
    endpoint_entities = ngsi.queryEntities(type="Endpoint")
    endpoint_exists = False
    if len(endpoint_entities) > 0:
        for endpoint_entity in endpoint_entities:
            if endpoint_entity['id'] == endpoint_id:
                endpoint_exists = True
                break
            else:
                endpoint_exists = False
    else:
        endpoint_exists = False

    return endpoint_exists


def checkOutputExistence(entity: Entity, ngsi: NGSILDClient) -> bool:
    """
    DEPRECATED: Checking if different agent entities in
    the data pipeline have an output relationship
    with another existing agent entity.
    This checking between agent entities is used for two different cases:
    - 1. If collection agent has an existing
    output aggregation or dispatch agent.
    - 2. If aggregation agent has an existing
    output aggregation or dispatch agent.

    The reason for this check is to determine
    that the activity of a data processing
    stage depends on the previous data processing stage,
    establishing dependency relationships between agent
    entities of the data pipeline.
    """
    target_entities = ngsi.queryEntities(type="MetricTarget")
    processor_entities = ngsi.queryEntities(type="MetricProcessor")

    target_output_entities_id = []
    processor_output_entities_id = []
    output_entities_id = []

    target_output_exists = False
    processor_output_exists = False
    output_exists = False

    if len(target_entities) > 0:
        for target_entity in target_entities:
            if target_entity['hasInput']['object'] == entity.id:
                target_output_exists = True
                target_output_entities_id.append(target_entity['id'])
        if len(target_output_entities_id) > 0:
            output_entities_id.extend(target_output_entities_id)
        else:
            target_output_exists = False
    else:
        target_output_exists = False

    if len(processor_entities) > 0:
        for processor_entity in processor_entities:
            if processor_entity['hasInput']['object'] == entity.id:
                processor_output_exists = True
                processor_output_entities_id.append(processor_entity['id'])
        if len(processor_output_entities_id) > 0:
            output_entities_id.extend(processor_output_entities_id)
        else:
            processor_output_exists = False
    else:
        processor_output_exists = False

    if target_output_exists or processor_output_exists:
        ngsi_ld_ops.stateToFailed(
            ngsi, entity.id,
            {"value": "ERROR! The '{0}' entity has output: '{1}'.".format(
                entity.id, output_entities_id)})
        output_exists = True

    return output_exists
