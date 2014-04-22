#!/bin/bash

#add Ambari repo
wget http://public-repo-1.hortonworks.com/ambari/centos6/1.x/updates/1.4.4.23/ambari.repo
sudo cp ambari.repo /etc/yum.repos.d

#install Ambari
sudo yum -y install ambari-server
echo -e 'n\nn\n' | sudo ambari-server setup -j /usr/lib/jvm/java
sudo ambari-server start
