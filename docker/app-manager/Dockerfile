FROM python:3.8

USER root

RUN mkdir /opt/app-manager

# Install and config Poetry
RUN pip install poetry==1.1.7

RUN poetry config virtualenvs.create false

# Install semantic-tools
COPY ./semantic-tools /tmp/semantic-tools

WORKDIR /tmp/semantic-tools

RUN poetry install

# Install dependencies
COPY ./docker/app-manager/requirements.txt /opt/app-manager/requirements.txt

RUN pip install -r /opt/app-manager/requirements.txt

# Copy application
COPY ./docker/app-manager/app /opt/app-manager/app

WORKDIR /opt/app-manager/app

EXPOSE 8080

CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8080", "--reload", "--log-config", "config/log.yaml"]
