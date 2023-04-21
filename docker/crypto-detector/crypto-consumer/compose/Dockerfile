FROM python:3.9-slim-buster

# Set hierarchy with folders
ENV APP_NAME=crypto-consumer

RUN mkdir -p /app
WORKDIR /app

COPY ${APP_NAME}/requirements.txt /app
RUN pip install -r requirements.txt

COPY ${APP_NAME}/crypto-consumer.py /app
ENTRYPOINT ["python3", "/app/crypto-consumer.py"]
