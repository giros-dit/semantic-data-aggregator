# NGSI-LD Data Models

The NGSI-LD protocol encodes data using the JSON-LD format. For this reason, data models associated with NGSI-LD entities can be formally specified using JSON schema.

In this repository, we provide JSON schemas arranged per domain. For example, the [clarity_data_lake](schemas/clarity_data_lake) folder, contains data models derived from the NGSI-LD information model that captures context information related to the DataLake platform in 5G-CLARITY as depicted [here](../information-models/clarity-data-lake/D43-figures-S3-Models.png).

## Repository structure

The structure of the repository is arranged into the following main folders:

- `schemas`: Contains JSON schemas for each domain, e.g., [CLARITY DataLake Bucket JSON schema](schemas/clarity_data_lake/bucket.json)
- `examples`: Contains examples of JSON-LD payloads for each domain, e.g., [CLARITY DataLake Bucket example](examples/clarity_data_lake/bucket/example-normalized.json)
- `context`: Contains JSON-LD context vocabulary for each domain, e.g., [CLARITY DataLake Context](context/clarity_data_lake/context.jsonld)
- `bindings`: Contains Python class bindings (using pydantic library) for each domain, e.g., [CLARITY DataLake Bucket class](bindings/clarity_data_lake/bucket.py)
- `tests`: Contains Python unit tests that allow to validate example JSON-LD payloads against the respective class bindings, e.g., [CLARITY DataLake tests](tests/clarity_data_lake.py)

## NGSI-LD Core (meta model)

When defining domain-specific JSON schemas, these must inherit the NGSI-LD meta model, which in is also known as the core domain. The meta model defines the foundational NGSI-LD elements Entity, Property, and Relationship.

In this sense, ETSI CIM exposes a public GitLab repository that contains [JSON schemas for the NGSI-LD API](https://forge.etsi.org/rep/NGSI-LD/NGSI-LD/-/tree/master/schema). However, the current specification of the [meta model](https://forge.etsi.org/rep/NGSI-LD/NGSI-LD/-/blob/master/schema/Entity.json) contains errors in its JSON schema, specifically in the `definitions.Property.properties.value.oneOf` line. Therefore, we decided to locally "fork" these JSON schemas and made the necessary adjustments to make the schemas valid. This local fork can be found in the [entity.json](schemas/entity.json) file.

In addition,[common.json](schemas/common.json), [geometry.json](schemas/geometry.json),[subscription.json](schemas/subscription.json), and [notification.json](schemas/notification.json) schemas file have been forked from [upstream](https://forge.etsi.org/rep/NGSI-LD/NGSI-LD/-/tree/master/schema). Files have been modified to reference local files, and also, the `format: uri` has been removed as this format is not compatible with `pydantic` library.

## How to generate Python bindings from JSON schema

Once we have defined JSON schemas for data models, we want to parse and validate NGSI-LD payloads that follow the specified data models in programatic way. To this end, we must generate class bindings in the selected progamming language. In the case of the SDA, most of the internal components are developed using Python language, so we have to find a tool able to produce Python class bindings from JSON schemas. The [datamode-code-generator](https://koxudaxi.github.io/datamodel-code-generator/) is a tool that generates pydantic models from OpenAPI and JSON schema specifications.

### Poetry installation and configuration

This repository includes a [poetry](https://python-poetry.org) manifest that can be leveraged to run `datamode-code-generator` within an isolated virtual environment. To do so, follow these steps:

1. Download and install poetry by following the [official docummentacion](https://python-poetry.org/docs/master/#installing-with-the-official-installer).
2. Make sure you have the right Python version for this project (Python>3.9 in this case):
     ```bash
    sudo apt-get install python3.9
    ```
3. Install `distutils` package for your specific Python release:
    ```bash
    sudo apt-get install python3.9-distutils
    ```
4. Enable virtual environment for your specific Python release:
    ```bash
    poetry env use 3.9
    ```
5. Setup the virtual environment with poetry:
    ```bash
    poetry shell
    poetry install
    ```
The virtual environment is now prepared and activated to be used.

### Code generation

For our use case, we generate these models from the JSON schemas defined for each domain as follows:

```bash
datamodel-codegen --input schemas --input-file-type jsonschema --output bindings --aliases datamodel-codegen/aliases.json --custom-template-dir datamodel-codegen/templates
```
This command will generate Python class bindings for all JSON schemas found within the `schemas` folder.
