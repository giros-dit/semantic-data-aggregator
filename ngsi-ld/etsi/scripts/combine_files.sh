#!/bin/bash
#
# Requires install swagger-cli: npm install -g swagger-cli
#

# Generate a file which combines all the spec files into a unique swagger definition file (maintains references)
swagger-cli bundle ./spec/updated/ngsi-ld-spec-open-api.json -o ./bundle/ngsild_swagger_combined.json

# Generate a file which combines all the spec files into a unique swagger definition file (removes references)
# working with modified swagger-cli.js to avoid circular ref problem
swagger-cli -d bundle --dereference ./spec/updated/ngsi-ld-spec-open-api.json -o ./bundle/ngsild_swagger_combined_dereferenced.json
# modified version to allow circular ref
# swagger-cli bundle --dereference="ignore" ./spec/updated/ngsi-ld-spec-open-api.json -o ./bundle/ngsild_swagger_combined.json