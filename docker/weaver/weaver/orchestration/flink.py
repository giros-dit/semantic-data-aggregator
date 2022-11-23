import logging

from flink_client.api.default_api import DefaultApi
from flink_client.api_client import ApiClient
from semantic_tools.bindings.pipelines.task import Task

logger = logging.getLogger(__name__)


def instantiate_job_from_task(flink: ApiClient, task: Task,
                              jarId: str, args: dict) -> dict:
    """
    Insantiates a Flink job from a given Task entity
    and its associated Application, i.e., JAR.
    """
    # Get a entry class of the Stream Aplication (if it exists)
    if hasattr(args, "entryClass"):
        entryClass = args["entryClass"]
        # Get a list of arguments separated by commas to run the Flink job
        arguments = get_job_arguments_list(args)
        # Run job for JAR id
        job = DefaultApi(flink).jars_jarid_run_post(
            jarid=jarId, entry_class=entryClass, program_arg=arguments)
    else:
        # Get a list of arguments separated by commas to run the Flink job
        arguments = get_job_arguments_list(args)
        # Run job for JAR id
        job = DefaultApi(flink).jars_jarid_run_post(
            jarid=jarId, program_arg=arguments)

    logger.info(
        "Job '{0}' with '{1}' JAR instantiated in Flink engine.".format(
            task.id, jarId))
    return job


def get_job_arguments_list(args: dict) -> str:
    """
    Get all the arguments for a specific
    Task entity as a list separated by commas.
    """
    arguments_list = []
    for key, value in args.items():
        if key != "entryClass":
            arguments_list.append(value)

    return ','.join(arguments_list)
