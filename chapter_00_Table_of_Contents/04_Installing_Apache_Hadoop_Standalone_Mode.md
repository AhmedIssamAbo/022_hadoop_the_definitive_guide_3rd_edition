##Standalone Mode##
```
In standalone mode, there is no further action to take, since the default properties are
set for standalone mode and there are no daemons to run.
```

0x01. 创建 hadoop 用户组
```
$ sudo addgroup hadoop
```

0x02. 创建 hadoop 用户
```
$ sudo adduser -ingroup hadoop hadoop
```


0x03. 为 hadoop 用户添加权限
```
修改/etc/sudoers,为hadoop用户赋予与root相同的权限
$ sudo vi /etc/sudoers

# User privilege specification
root	ALL=(ALL:ALL) ALL
hadoop	ALL=(ALL:ALL) ALL
```


0x04. 使用新增加的hadoop用户登录ubuntu系统

0x05. 安装ssh,并启动服务
```
# 01. 安装ssh
$ sudo apt-get install openssh-server
$ sudo apt-get install ssh

# 02. 启动ssh服务
$ sudo /etc/init.d/ssh start

# 03. 验证ssh服务是否正常启动
$ ps -e | grep "ssh"

# 04. 设置免密码登录,生成私钥和公钥
$ ssh-keygen -t rsa -P ""
# 此时会在／home／hadoop/.ssh下生成两个文件：id_rsa和id_rsa.pub，前者为私钥，后者为公钥。
cat /home/haddop/.ssh/id_rsa.pub >> /home/hadoop/.ssh/authorized_keys

# 05. 登录ssh
$ ssh localhost
$ exit

# 06. 再次登录ssh
$ ssh localhost
```


0x05. 安装Java环境
```
$ sudo apt-get install openjdk-7-jdk

# 查看安装结果，输入命令：java -version，结果如下表示安装成功。
$ java -version
```


0x06. 安装hadoop-2.6.4
```
# 01. 下载hadoop-2.6.4.tar.gz
$ sudo apt-get install axel
$ axel -n 30 http://mirror.bit.edu.cn/apache/hadoop/common/hadoop-2.6.4/hadoop-2.6.4.tar.gz

# 02. 将hadoop安装到/usr/local下
$ sudo tar -xvf hadoop-2.6.4.tar.gz -C /usr/local/hadoop
$ sudo tar -xvf hadoop-2.6.4.tar.gz -C /usr/local/

# 03. 赋予hadoop用户对该文件夹的读写权限
$ cd /usr/local/
$ sudo chown root:root hadoop-2.6.4/
$ sudo chmod g+w /usr/local/hadoop-2.6.4/
$ sudo ln -s hadoop-2.6.4/ hadoop
```


0x07. 配置系统环境变量以及Hadoop环境变量
```
# 01. 获取JAVA_HOME环境变量
# 配置该文件前需要知道Java的安装路径，用来设置JAVA_HOME环境变量，可以使用下面命令行查看安装路径
$ update-alternatives --config java
# 完整的路径为
# /usr/lib/jvm/java-7-openjdk-amd64/jre/bin/java
# 我们只取前面的部分 /usr/lib/jvm/java-7-openjdk-amd64

# 02. 配置.bashrc文件
$ sudo vi ~/.bashrc
# 在文件末尾追加下面内容，然后保存退出
#####################################################################
#HADOOP VARIABLES START
export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64
export HADOOP_INSTALL=/usr/local/hadoop
export PATH=$PATH:$HADOOP_INSTALL/bin
export PATH=$PATH:$HADOOP_INSTALL/sbin
export HADOOP_MAPRED_HOME=$HADOOP_INSTALL
export HADOOP_COMMON_HOME=$HADOOP_INSTALL
export HADOOP_HDFS_HOME=$HADOOP_INSTALL
export YARN_HOME=$HADOOP_INSTALL
export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_INSTALL/lib/native
export HADOOP_OPTS="-Djava.library.path=$HADOOP_INSTALL/lib"
#HADOOP VARIABLES END
#####################################################################
```


0x08. 配置Hadoop环境变量
```
# 编辑/usr/local/hadoop/etc/hadoop/hadoop-env.sh
# 执行下面命令，打开该文件的编辑窗口
# sudo vi /usr/local/hadoop/etc/hadoop/hadoop-env.sh
# 找到JAVA_HOME变量，修改此变量如下
# export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64 
```


0x09. hadoop中的WordCount测试

```
$ hadoop version
```

```
# 单机模式安装完成，下面通过执行hadoop自带实例WordCount验证是否安装成功

# 在/usr/local/hadoop路径下创建input文件夹
$ cd $HADOOP_COMMON_HOME
$ sudo mkdir input

# 拷贝README.txt到input
$ sudo cp README.txt input

# sudo权限执行WordCount
$ cd $HADOOP_COMMON_HOME
$ sudo bin/hadoop jar share/hadoop/mapreduce/sources/hadoop-mapreduce-examples-2.6.4-sources.jar org.apache.hadoop.examples.WordCount input output

# 执行结果



# 执行 cat output/*，查看字符统计结果
$ ls -lh output/
$ cat ./output/part-r-00000 
$ cat ./output/_SUCCESS 
$ cat output/*
$ exit
```


