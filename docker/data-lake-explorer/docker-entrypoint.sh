#!/bin/sh

set -e

# activate our virtual environment here
. /venv/bin/activate

exec python data_lake_explorer/main.py \
     --broker-uri "${BROKER_URI}" \
     --data-lake-uri "${DATA_LAKE_URI}" \
     --data-lake-key "${DATA_LAKE_KEY}" \
     --data-lake-region "${DATA_LAKE_REGION}"
