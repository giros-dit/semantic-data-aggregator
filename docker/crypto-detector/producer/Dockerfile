FROM python:3

RUN mkdir -p /app
WORKDIR /app

COPY requirements.txt /app
RUN pip install -r requirements.txt

COPY producer.py /app
ENTRYPOINT ["python3", "/app/producer.py"]