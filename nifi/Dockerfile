FROM apache/nifi

USER root

RUN curl -sL https://github.com/karimra/gnmic/raw/master/install.sh | bash

RUN apt-get update

# Install Python3
RUN apt-get install -y python3 python3-pip

# Install pyangbind
RUN pip3 install pyang pyangbind
