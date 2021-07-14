FROM python:3.8

RUN mkdir /opt/consumer

# Install dependencies
COPY ./docker/consumer/requirements.txt /opt/consumer/requirements.txt

RUN pip install -r /opt/consumer/requirements.txt

# Copy application
COPY ./docker/consumer/app /opt/consumer/app

WORKDIR /opt/consumer/app

EXPOSE 8080

CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8080", "--reload"]
