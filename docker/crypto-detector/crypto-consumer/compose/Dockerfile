FROM python:3.9-slim-buster

# Set hierarchy with folders
ENV APP_PARENT_NAME=crypto-detector
ENV APP_NAME=crypto-consumer

RUN mkdir -p /app
WORKDIR /app

COPY docker/${APP_PARENT_NAME}/${APP_NAME}/requirements.txt /app
RUN pip install -r requirements.txt

COPY docker/${APP_PARENT_NAME}/${APP_NAME}/crypto-consumer.py /app
ENTRYPOINT ["python3", "/app/crypto-consumer.py"]
