ARG IMAGE_VARIANT=slim-buster
ARG OPENJDK_VERSION=8
ARG PYTHON_VERSION=3.10.7

FROM python:${PYTHON_VERSION}-${IMAGE_VARIANT} AS py3
FROM openjdk:${OPENJDK_VERSION}-${IMAGE_VARIANT}

COPY --from=py3 / /

ARG PYSPARK_VERSION=3.3.0
RUN pip --no-cache-dir install pyspark==${PYSPARK_VERSION}

ENV SPARK_LOCAL_IP="127.0.0.1"

RUN apt-get update && apt-get install -y \
    python3.4 \
    python3-pip

RUN mkdir -p /app
WORKDIR /app

COPY RandomForestTrained.joblib /app
COPY requirements.txt /app
RUN pip install -r requirements.txt

COPY crypto-detector.py /app

RUN mkdir -p /app/checkpoint

ENTRYPOINT ["python3", "/app/crypto-detector.py"]