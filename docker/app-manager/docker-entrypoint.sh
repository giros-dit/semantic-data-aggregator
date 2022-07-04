#!/bin/sh

set -e

# activate our virtual environment here
. /venv/bin/activate

exec python app_manager/main.py
