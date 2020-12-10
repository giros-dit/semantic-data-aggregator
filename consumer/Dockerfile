FROM python:3.8

COPY requirements.txt requirements.txt

RUN pip install -r requirements.txt

COPY ./app /app

WORKDIR /app

EXPOSE 8080

CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8080", "--reload"]
