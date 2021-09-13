FROM apache/nifi:1.14.0

USER root

# Install gNMIc
RUN bash -c "$(curl -sL https://get-gnmic.kmrd.dev)"

RUN apt-get --allow-releaseinfo-change update

# Install Python3
RUN apt-get install -y python3 python3-pip

# Install pyangbind
RUN pip3 install pyang pyangbind


