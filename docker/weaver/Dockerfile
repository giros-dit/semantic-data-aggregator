FROM python:3.8

USER root

RUN mkdir /opt/weaver

# Install gNMIc
RUN bash -c "$(curl -sL https://get-gnmic.kmrd.dev)"

# Install and config Poetry
RUN pip install poetry==1.1.7

RUN poetry config virtualenvs.create false

# Install semantic-tools
COPY ./semantic-tools /tmp/semantic-tools

WORKDIR /tmp/semantic-tools

RUN poetry install

# Install dependencies
COPY ./docker/weaver/requirements.txt /opt/weaver/requirements.txt

RUN pip install -r /opt/weaver/requirements.txt

# Copy application
COPY ./docker/weaver/app /opt/weaver/app

WORKDIR /opt/weaver/app

EXPOSE 8080

CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8080", "--reload", "--log-config", "config/log.yaml"]
