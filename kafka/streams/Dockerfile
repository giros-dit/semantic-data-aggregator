FROM ubuntu:18.04

RUN apt-get update

RUN apt-get install -y openjdk-8-jdk nano wget curl

RUN apt-get install -y maven

RUN wget http://apache.uvigo.es/kafka/2.7.0/kafka_2.13-2.7.0.tgz && tar -zxvf kafka_2.13-2.7.0.tgz

# streams apps folder
RUN mkdir kafka_2.13-2.7.0/streams.examples

COPY ./streams.examples kafka_2.13-2.7.0/streams.examples

WORKDIR kafka_2.13-2.7.0
