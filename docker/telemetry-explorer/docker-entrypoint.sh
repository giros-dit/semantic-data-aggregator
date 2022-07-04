#!/bin/sh

set -e

# activate our virtual environment here
. /venv/bin/activate

exec uvicorn telemetry_explorer.main:app --host 0.0.0.0 \
     --port 8080 --reload \
     --log-config telemetry_explorer/config/log.yaml
