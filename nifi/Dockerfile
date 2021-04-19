FROM apache/nifi

USER root

<<<<<<< HEAD
# Install gNMIc
RUN bash -c "$(curl -sL https://get-gnmic.kmrd.dev)"
=======
RUN curl -sL https://github.com/karimra/gnmic/raw/master/install.sh | bash
>>>>>>> develop

RUN apt-get update

# Install Python3
RUN apt-get install -y python3 python3-pip

# Install pyangbind
RUN pip3 install pyang pyangbind
<<<<<<< HEAD


=======
>>>>>>> develop
