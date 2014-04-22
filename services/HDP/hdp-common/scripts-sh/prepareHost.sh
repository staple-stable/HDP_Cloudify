#!/bin/bash

sudo yum -y update
sudo yum -y install wget openssh-clients
#jdk and javahome
echo "prepareHost.sh: Installing jre-1.7.0-openjdk.x86_x64"
sudo yum -y install java-1.7.0-openjdk-devel
export JAVA_HOME=/usr/lib/jvm/jre-1.7.0-openjdk.x86_x64
cp /etc/bashrc $HOME
cp /etc/profile $HOME
echo -e "export JAVA_HOME=/usr/lib/jvm/jre-1.7.0-openjdk.x86_x64\nexport PATH=$JAVA_HOME/bin:$PATH" >> $HOME/bashrc
sudo cp $HOME/bashrc /etc/bashrc
sudo source /etc/bashrc

sudo chkconfig iptables off
sudo /etc/init.d/iptables stop

#install and configure ntpd
sudo yum -y install ntp ntpdate ntp-doc
sudo chkconfig ntpd on
sudo ntpdate pool.ntp.org
sudo /etc/init.d/ntpd start

#disable SE Linux
#echo 0 >/selinux/enforce
