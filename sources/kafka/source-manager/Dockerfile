# Based on https://stackoverflow.com/a/57886655
FROM python:3.9-slim-buster as base

ENV PYTHONFAULTHANDLER=1 \
    PYTHONHASHSEED=random \
    PYTHONUNBUFFERED=1

# Set hierarchy with folders
ENV APP_NAME=source-manager
ENV APP_DIR=/opt/sda/${APP_NAME}
RUN mkdir -p ${APP_DIR}
WORKDIR ${APP_DIR}

FROM base as builder

ENV PIP_DEFAULT_TIMEOUT=100 \
    PIP_DISABLE_PIP_VERSION_CHECK=1 \
    PIP_NO_CACHE_DIR=1 \
    POETRY_VERSION=1.1.13

RUN pip install "poetry==$POETRY_VERSION"
RUN python -m venv /venv

# Copy semantic-tools dependency
RUN mkdir -p /opt/semantic-tools
COPY semantic-tools /opt/semantic-tools
# Copy app artifacts
COPY sources/kafka/${APP_NAME}/pyproject.toml sources/kafka/${APP_NAME}/poetry.lock ./
RUN poetry export -f requirements.txt --without-hashes | /venv/bin/pip install -r /dev/stdin

COPY sources/kafka/${APP_NAME}/ .
RUN poetry build && /venv/bin/pip install dist/*.whl

FROM base as final

COPY --from=builder /venv /venv
COPY --from=builder ${APP_DIR} .
COPY sources/kafka/${APP_NAME}/docker-entrypoint.sh .
RUN chmod +x docker-entrypoint.sh

CMD ["./docker-entrypoint.sh"]
