FROM python:3.8

RUN mkdir /opt/experimenter

# Install and config Poetry
RUN pip install poetry==1.1.7

RUN poetry config virtualenvs.create false

# Install semantic-tools
COPY ./semantic-tools /tmp/semantic-tools

WORKDIR /tmp/semantic-tools

RUN poetry install

# Install dependencies
COPY ./docker/experimenter/requirements.txt /opt/experimenter/requirements.txt

RUN pip install -r /opt/experimenter/requirements.txt

# Copy application
COPY ./docker/experimenter/app /opt/experimenter/app

WORKDIR /opt/experimenter/app

EXPOSE 8080

CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8080", "--reload", "--log-config", "config/log.yaml"]
