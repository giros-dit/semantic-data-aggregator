# FROM ubuntu:22.04

# RUN apt-get update && apt-get install -y \
#     python3.4 \
#     python3-pip

FROM python:3.10.2-bullseye

RUN mkdir -p /app
WORKDIR /app

COPY requirements.txt /app
RUN pip install -r requirements.txt

COPY event-generator.py /app
COPY pipeline-flows /app/pipeline-flows

CMD ["python3", "/app/event-generator.py", "-b", "kafka-service:9092", "-d", "1"]
