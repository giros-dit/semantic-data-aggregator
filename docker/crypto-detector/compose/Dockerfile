FROM python:3.9-slim-buster

# Set hierarchy with folders
ENV APP_NAME=crypto-detector

RUN mkdir -p /app
WORKDIR /app

COPY docker/${APP_NAME}/requirements.txt /app
COPY docker/${APP_NAME}/RandomForestTrained.joblib /app
RUN pip install -r requirements.txt

COPY docker/${APP_NAME}/crypto-detector.py /app

ENTRYPOINT ["python3", "/app/crypto-detector.py"]
