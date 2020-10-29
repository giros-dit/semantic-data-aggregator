#!/bin/bash
#
# Requires install swagger-cli: npm install -g swagger-cli
#

# Merge the swagger definition into an unified and de-referenced file (use this file will avoid reference resolution problems during the validation)
swagger-cli bundle --dereference ./spec/updated/ngsi-ld-spec-open-api.json -o ./bundle/ngsild_swagger_combined_dereferenced.json &&
# modified version to allow circular ref
# ~/work/swagger-cli/bin/swagger-cli.js bundle --dereference="ignore" ./spec/updated/ngsi-ld-spec-open-api.json -o ./bundle/ngsild_swagger_combined_dereferenced.json &&
echo '--> Sending to validator.swagger.io' &&
# Validate the swagger definition against the official online swagger validation service
curl -X POST -d @bundle/ngsild_swagger_combined_dereferenced.json -H 'Content-Type:application/json' https://validator.swagger.io/validator/debug
