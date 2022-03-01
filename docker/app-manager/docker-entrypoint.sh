#!/bin/sh

set -e

# activate our virtual environment here
. /venv/bin/activate

exec uvicorn app_manager.main:app --host 0.0.0.0 \
     --port 8080 --reload \
     --log-config app_manager/config/log.yaml
