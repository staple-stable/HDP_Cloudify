#! /bin/bash

sudo yum install -y openssh-clients
chmod 600 /home/user/gs-files/cloudify_key.pem
cp /home/user/gs-files/cloudify_key.pem ~/.ssh/id_rsa
