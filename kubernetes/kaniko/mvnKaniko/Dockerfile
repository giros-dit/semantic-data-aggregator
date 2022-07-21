#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY semantic-metrics-datamodels/aggregations/ /home/app/aggregations
COPY semantic-metrics-datamodels/yang-models/ /home/app/yang-models
RUN mvn -f /home/app/aggregations/netflow/goflowAggregator/pom.xml clean package

#
# Package stage
#
FROM flink:1.14.4-scala_2.12-java11
COPY --from=build /home/app/aggregations/netflow/goflowAggregator/target/netflowAggregator-1.0.jar /opt/flink/usrlib/netflowAggregator-1.0.jar
