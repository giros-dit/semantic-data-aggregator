FROM python:3.8

USER root

RUN mkdir /opt/source-manager

# Install and config Poetry
RUN pip install poetry==1.1.7

RUN poetry config virtualenvs.create false

# Install semantic-tools
COPY ./semantic-tools /tmp/semantic-tools

WORKDIR /tmp/semantic-tools

RUN poetry install

# Install dependencies
COPY ./docker/source-manager/requirements.txt /opt/source-manager/requirements.txt

RUN pip install -r /opt/source-manager/requirements.txt

# Copy application
COPY ./docker/source-manager/app /opt/source-manager/app

WORKDIR /opt/source-manager/app

EXPOSE 8080

CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8080", "--reload", "--log-config", "config/log.yaml"]
