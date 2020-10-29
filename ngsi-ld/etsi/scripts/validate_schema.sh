#!/bin/bash

ajv compile -s ./schema/Entity.json -r ./schema/common.json -r ./schema/geometry-schema.json

ajv compile -s ./schema/subscriptions/Subscription.json -r ./schema/common.json -r ./schema/geometry-schema.json

ajv compile -s ./schema/temporal/EntityTemporal.json -r ./schema/common.json -r ./schema/geometry-schema.json -r ./schema/Entity.json

ajv compile -s ./schema/registrations/ContextSourceRegistration.json -r ./schema/common.json -r ./schema/geometry-schema.json

ajv compile -s ./schema/subscriptions/Notification.json -r ./schema/common.json -r ./schema/geometry-schema.json -r ./schema/Entity.json

ajv compile -s ./schema/registrations/ContextSourceNotification.json -r ./schema/common.json -r ./schema/geometry-schema.json -r ./schema/registrations/ContextSourceRegistration.json

ajv compile -s ./schema/EntityList.json -r ./schema/common.json -r ./schema/geometry-schema.json -r ./schema/Entity.json

ajv compile -s ./schema/subscriptions/SubscriptionList.json -r ./schema/common.json -r ./schema/geometry-schema.json -r ./schema/subscriptions/Subscription.json

ajv compile -s ./schema/registrations/ContextSourceRegistrationList.json -r ./schema/common.json -r ./schema/geometry-schema.json -r ./schema/registrations/ContextSourceRegistration.json

ajv compile -s ./schema/temporal/EntityTemporalList.json -r ./schema/temporal/EntityTemporal.json -r ./schema/Entity.json -r ./schema/common.json -r ./schema/geometry-schema.json

ajv compile -s ./schema/BatchOperationResult.json -r ./schema/common.json

ajv compile -s ./schema/UpdateResult.json

ajv compile -s ./schema/Entity_keyValues.json -r ./schema/common.json -r ./schema/geometry-schema.json 

ajv compile -s ./schema/temporal/Entity_temporalValues.json -r ./schema/common.json -r ./schema/geometry-schema.json -r ./schema/Entity.json
