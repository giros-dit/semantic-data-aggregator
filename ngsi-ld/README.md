# NGSI-LD APIs

Collector implements query and subscribe APIs for NGSI-LD entities. Collector leverages [connexion](https://github.com/zalando/connexion) framework which implements NGSI-LD APIs out from [ETSI's OpenAPI specifications](https://forge.etsi.org/rep/NGSI-LD/NGSI-LD). The OpenAPI specifications (OAS) are stored in the schemas folder. 

ETSI defines the data models for NGSI-LD APIs as standard JSON schemas. However, some of the keywords used in these JSON schemas are currently not supported in OpenAPI (OpenAPI 3.1 release is expected to fully support standard JSON schema). As a result, ETSI's NGSI-LD OAS cannot work out of the box. These OAS files have been slightly modified to adapt the JSON schemas. Regarding our proposed collector, the query Entity API has been adapted as some of the original specification uses unsupported keywords in Open API. These conflicting keywords range from 'definitions' to 'propertyNames', or mixing two types of MIMEs as a content type. The latter in particular breaks connexion library logic as ETSI defines the content type as "application/json;application/ld+json" but connexion expects a single MIME type (as specified by OpenAPI). We could contribute upstream by proposing a new OAS which defines "application/json" and "application/ld+json" separately.

# Context Broker

For this prototype we will be using [Scorpio](https://scorpio.readthedocs.io/en/latest/) context broker.
