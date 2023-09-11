FROM python:3.9-slim-buster

RUN mkdir -p /app
WORKDIR /app

COPY RandomForestTrained.joblib /app
COPY requirements.txt /app
RUN pip install -r requirements.txt

COPY crypto-detector.py /app

ENTRYPOINT ["python3", "/app/crypto-detector.py"]
