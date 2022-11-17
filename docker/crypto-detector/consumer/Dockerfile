FROM python:3.9-slim-buster

RUN mkdir -p /app
WORKDIR /app

COPY requirements.txt /app
RUN pip install -r requirements.txt

COPY consumer.py /app
ENTRYPOINT ["python3", "/app/consumer.py"]
