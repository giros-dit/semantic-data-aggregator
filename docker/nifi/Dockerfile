FROM apache/nifi:1.14.0

USER root

# Install gNMIc
RUN bash -c "$(curl -sL https://get-gnmic.kmrd.dev)"

RUN apt-get --allow-releaseinfo-change update

# Install Python3
RUN apt-get install -y python3 python3-pip

# Install pyangbind
RUN pip3 install pyang pyangbind

# Install OpenJDK-11
RUN apt-get update && \
 apt-get install -y openjdk-11-jdk && \
 apt-get install -y ant && \
 apt-get clean;

# Fix certificate issues
RUN apt-get update && \
 apt-get install ca-certificates-java && \
 apt-get clean && \
 update-ca-certificates -f;

# Setup JAVA_HOME -- useful for docker commandline
ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-amd64/
RUN export JAVA_HOME
