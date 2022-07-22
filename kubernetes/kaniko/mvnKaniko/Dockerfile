#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY semantic-metrics-datamodels/drivers/ /home/app/drivers
COPY semantic-metrics-datamodels/yang-models/ /home/app/yang-models
RUN mvn -f /home/app/drivers/consumers/Netflow2CDS/pom.xml clean package

#
# Package stage
#
FROM flink:1.14.4-scala_2.12-java11
COPY --from=build /home/app/drivers/consumers/Netflow2CDS/target/Netflow2CDS-1.0.jar /opt/flink/usrlib/Netflow2CDS-1.0.jar
