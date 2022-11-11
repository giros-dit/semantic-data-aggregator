FROM ubuntu:22.04

RUN apt-get update && apt-get install -y \
    python3.4 \
    python3-pip

RUN mkdir -p /app
WORKDIR /app

COPY requirements.txt /app
RUN pip install -r requirements.txt

COPY consumer.py /app
ENTRYPOINT ["python3", "/app/consumer.py"]
