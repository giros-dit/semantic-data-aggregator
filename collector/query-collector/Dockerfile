FROM tiangolo/uvicorn-gunicorn-starlette:python3.7

COPY requirements.txt requirements.txt

RUN pip install -r requirements.txt

COPY ./app /app

