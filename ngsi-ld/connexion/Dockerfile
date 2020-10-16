FROM python:3.7

COPY requirements.txt requirements.txt

RUN pip install -r requirements.txt

COPY ./app /app

WORKDIR /app

EXPOSE 8080

CMD ["python", "server.py"]
