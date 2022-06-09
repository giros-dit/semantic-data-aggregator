FROM ubuntu:22.04

RUN apt-get update && apt-get install -y \
    python3.4 \
    python3-pip

RUN mkdir -p /app
WORKDIR /app

COPY RandomForestTrained.joblib /app
COPY requirements.txt /app
RUN pip install -r requirements.txt

COPY crypto-detector.py /app

CMD ["python3", "/app/crypto-detector.py", "-b", "kafka:9092", "-c", "CDS", "-p", "crypto_result"]
