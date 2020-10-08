FROM tiangolo/uvicorn-gunicorn-starlette:python3.7

RUN apt-get update && apt-get install -y libsnappy-dev

COPY requirements.txt requirements.txt

RUN pip install -r requirements.txt

COPY ./app /app

