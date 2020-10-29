#!/bin/bash

ajv validate -s ./schema/Entity.json -d ./examples/Entity-example.json -r ./schema/common.json -r ./schema/geometry-schema.json

ajv validate -s ./schema/Entity.json -d ./examples/Vehicle_C2.2.json -r ./schema/common.json -r ./schema/geometry-schema.json

ajv validate -s ./schema/Entity.json -d ./examples/OffStreetParking_C2.3.json -r ./schema/common.json -r ./schema/geometry-schema.json

ajv validate -s ./schema/Entity_keyValues.json -d ./examples/Vehicle_keyValues_C2.2.json -r ./schema/common.json -r ./schema/geometry-schema.json

ajv validate -s ./schema/Entity_keyValues.json -d ./examples/OffStreetParking_keyValues_C2.3.json -r ./schema/common.json -r ./schema/geometry-schema.json

ajv validate -s ./schema/Entity.json -d ./examples/Vehicle_MultiAttribute_C2.2.json -r ./schema/common.json -r ./schema/geometry-schema.json

ajv validate -s ./schema/subscriptions/Subscription.json -d ./examples/Subscription-example_C.4.json -r ./schema/common.json -r ./schema/geometry-schema.json

ajv validate -s ./schema/registrations/ContextSourceRegistration.json -d ./examples/ContextSourceRegistration-example_C.3.json -r ./schema/common.json -r ./schema/geometry-schema.json

ajv validate -s ./schema/temporal/EntityTemporal.json -d ./examples/EntityTemporal-example_C5.5.3.json -r ./schema/common.json -r ./schema/geometry-schema.json -r ./schema/Entity.json

ajv validate -s ./schema/temporal/Entity_temporalValues.json -d ./examples/Entity_temporalValues_C.5.6.3.json -r ./schema/common.json -r ./schema/geometry-schema.json -r ./schema/Entity.json

