#!/bin/sh

set -e

# activate our virtual environment here
. /venv/bin/activate

exec uvicorn data_lake_explorer.main:app --host 0.0.0.0 \
     --port 8080 --reload \
     --log-config data_lake_explorer/config/log.yaml
