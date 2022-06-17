FROM ubuntu:xenial

# Install packages
RUN apt-get update
RUN apt-get install -y openjdk-8-jdk python3 python3-pip curl locales nano git iputils-ping

# Install kafka-python library
RUN pip3 install kafka-python

# Copy files
COPY sources/kafka/complex-publishers/files/5GEVE-publisher /5GEVE-publisher
COPY sources/kafka/complex-publishers/files/SOLogParser-publisher /SOLogParser-publisher
COPY sources/kafka/complex-publishers/files/VSLogParser-publisher /VSLogParser-publisher

# Entrypoint script
COPY sources/kafka/complex-publishers/files/entrypoint.sh /
RUN chmod +x entrypoint.sh
ENTRYPOINT /bin/bash entrypoint.sh
