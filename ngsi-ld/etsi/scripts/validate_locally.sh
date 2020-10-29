#!/bin/bash
#
# Requires install swagger-cli: npm install -g swagger-cli
#

# Validate locally the definition using the swagger-cli validate option
swagger-cli validate ./spec/updated/ngsi-ld-spec-open-api.json
