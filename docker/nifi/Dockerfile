FROM apache/nifi:1.13.2

USER root

# Install gNMIc
RUN bash -c "$(curl -sL https://get-gnmic.kmrd.dev)"

RUN apt-get update

# Install Python3
RUN apt-get install -y python3 python3-pip

# Install pyangbind
RUN pip3 install pyang pyangbind


