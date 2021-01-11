FROM wurstmeister/kafka:latest

# plugins folder
RUN mkdir /opt/kafka_2.13-2.7.0/plugins

# kafka-connect-http plugins
COPY ./config/plugins /opt/kafka_2.13-2.7.0/plugins/

# we replace the default connect-distributed.properties so we can properly resolve to our local kafka docker development
COPY ./config/connect-distributed.properties /opt/kafka_2.13-2.7.0/config/

# we replace the start command creating a connector instead.
COPY ./config/start-kafka.sh /usr/bin/

# permissions
RUN chmod a+x /usr/bin/start-kafka.sh

WORKDIR /opt/kafka_2.13-2.7.0
