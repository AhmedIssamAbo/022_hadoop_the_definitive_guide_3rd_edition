##Standalone Mode##
```
In standalone mode, there is no further action to take, since the default properties are
set for standalone mode and there are no daemons to run.
```

0x01.
```
$ sudo addgroup hadoop
```

0x02.
```
$ sudo adduser -ingroup hadoop hadoop
```


0x03. 
```
sudo vi /etc/sudoers

# User privilege specification
root	ALL=(ALL:ALL) ALL
hadoop	ALL=(ALL:ALL) ALL
```


0x04. 
```
$ sudo apt-get install openssh-server
$ sudo apt-get install ssh

$ sudo /etc/init.d/ssh start
$ ps -e | grep "ssh"

$ ssh-keygen -t rsa -P ""
cat /home/haddop/.ssh/id_rsa.pub >> /home/hadoop/.ssh/authorized_keys

$ ssh localhost

$ ssh localhost
```


0x05. 
```
$ sudo apt-get install openjdk-7-jdk

$ java -version
```


0x0. 
```
$ sudo apt-get install axel


```


0x0. 
```
cd Downloads/
ls
sudo apt-get install axel
axel -n 30 http://mirror.bit.edu.cn/apache/hadoop/common/hadoop-2.6.4/hadoop-2.6.4.tar.gz
ls
sudo tar -xvf hadoop-2.6.4.tar.gz -C /usr/local/hadoop
sudo tar -xvf hadoop-2.6.4.tar.gz -C /usr/local/
cd /usr/local/
ls
ls -lh
sudo chown root:root hadoop-2.6.4/
ls -lh
sudo chmod g+x /usr/local/hadoop-2.6.4/
ls -lh
sudo chmod g+w /usr/local/hadoop-2.6.4/
ls -lh
sudo ln -s hadoop hadoop-2.6.4/
ls -lh
ls
sudo ln -s hadoop-2.6.4/ hadoop
ls 
ls -lh
update-alternatives - -config java
update-alternatives -config java
update-alternatives --config java
sudo vi ~/.bashrc 
sudo reboot
exit
cd /usr/local/hadoop
ls
cd etc/
ls
cd hadoop/
ls
ls -lh
vi hadoop-env.sh 
sudo vi hadoop-env.sh 
echo ${JAVA_HOME} 
cd $HADOOP_COMMON_HOME
ls
mkdir input
sudo mkdir input
cp README.txt input
sudo cp README.txt input
ls
find . -name "examples" -print
cd share/doc/hadoop/api/org/apache/hadoop/
ls
cd examples/
ls
cd -
cd ../...
cd ../../.
cd $HADOOP_COMMON_HOME
ls
find . -name "WordCount" -print
cd share/hadoop/mapreduce/sources/
ls
grep "WordCount" * 
cd share/hadoop/mapreduce/sources/
ls
cd $HADOOP_COMMON_HOME
ls
cd bin/
ls
cd ..
ls
vi bin/yarn
ls
bin/hadoop jar share/hadoop/mapreduce/sources/hadoop-mapreduce-examples-2.6.4-sources.jar org.apache.hadoop.examples.WordCount input output
bin/hadoop jar share/hadoop/mapreduce/sources/hadoop-mapreduce-examples-2.6.4-sources.jar org.apache.hadoop.examples.WordCount input output > /tmp/log1
vi /tmp/log1 
reset
vi /tmp/log1 
bin/hadoop jar share/hadoop/mapreduce/sources/hadoop-mapreduce-examples-2.6.4-sources.jar org.apache.hadoop.examples.WordCount input output > /tmp/log1
ls
sudo bin/hadoop jar share/hadoop/mapreduce/sources/hadoop-mapreduce-examples-2.6.4-sources.jar org.apache.hadoop.examples.WordCount input output
vi etc/hadoop/hadoop-env.sh 
sudo vi etc/hadoop/hadoop-env.sh 
reset
sudo bin/hadoop jar share/hadoop/mapreduce/sources/hadoop-mapreduce-examples-2.6.4-sources.jar org.apache.hadoop.examples.WordCount input output
ls
cd output/
ls
ls -lh
vi part-r-00000 
ls
cat _SUCCESS 
ls
reet
reset
sudo bin/hadoop jar share/hadoop/mapreduce/sources/hadoop-mapreduce-examples-2.6.4-sources.jar org.apache.hadoop.examples.WordCount input output
cd ..
sudo rm -rf output/
sudo bin/hadoop jar share/hadoop/mapreduce/sources/hadoop-mapreduce-examples-2.6.4-sources.jar org.apache.hadoop.examples.WordCount input output
ls
ls output/
ls
cat output/*
exit
```


