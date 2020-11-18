FROM python:3.8

COPY requirements.txt requirements.txt

RUN pip install -r requirements.txt

COPY ./app /app

WORKDIR /app

# Sleep forever until main.py is available
CMD [ "tail", "-f", "/dev/null" ]