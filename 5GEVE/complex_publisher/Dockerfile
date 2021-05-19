FROM ubuntu:xenial

# Install packages
RUN apt-get update
RUN apt-get install -y openjdk-8-jdk python3 python3-pip curl locales nano git iputils-ping

# Install kafka-python library
RUN pip3 install kafka-python

# Copy files
COPY files/publisher-eve.py /
COPY files/publisher-logparser.py /
COPY files/log-sample-input.json /

# Entrypoint script
COPY files/entrypoint.sh /
RUN chmod +x entrypoint.sh
ENTRYPOINT /bin/bash entrypoint.sh
