##Pseudodistributed Mode##
```
The configuration files should be created with the following contents and placed in the
conf directory (although you can place configuration files in any directory as long as
you start the daemons with the --config option):
```


0x01. 配置core-site.xml
```
# /usr/local/hadoop/etc/hadoop/core-site.xml 包含了hadoop启动时的配置信息。
# 编辑器中打开此文件
$ sudo vi /usr/local/hadoop/etc/hadoop/core-site.xml
<configuration>
	<property>
		<name>fs.default.name</name>
		<value>hdfs://localhost:9000</value>
		<!--
		<value>hdfs://127.0.0.1:9000</value>
		-->
	</property>
</configuration>
```

0x02. 配置yarn-site.xml
```
# /usr/local/hadoop/etc/hadoop/yarn-site.xml包含了MapReduce启动时的配置信息。
# 编辑器中打开此文件
$ sudo vi yarn-site.xml

<configuration>
<!-- Site specific YARN configuration properties -->
	<property>
		<name>yarn.nodemanager.aux-services</name>
		<value>mapreduce_shuffle</value>
	</property>
	<property>
		<name>yarn.nodemanager.aux-services.mapreduce.shuffle.class</name>
		<value>org.apache.hadoop.mapred.ShuffleHandler</value>
	</property>
</configuration>

```


0x03. 创建和配置maper-site.xml
```
# 默认情况下，/usr/local/hadoop/etc/hadoop/文件夹下有mapred-site.xml.template文件，我们要复制该文件，并命名为mapred-site.xml，该文件用于指定MapReduce使用的框架。
# 复制并重命名
# cp mapred-site.xml.template mapred-site.xml
# 编辑器打开此新建文件
# sudo vi mapred-site.xml

<configuration>
	<property>
		<name>mapreduce.framework.name</name>
		<value>yarn</value>
	</property>
</configuration>

```


0x04. 配置hdfs-site.xml
```
/usr/local/hadoop/etc/hadoop/hdfs-site.xml用来配置集群中每台主机都可用，指定主机上作为namenode和datanode的目录。

$ cd /usr/local/hadoop
$ sudo mkdir -p hdfs/{data,name}

# 你也可以在别的路径下创建上图的文件夹，名称也可以与上图不同，但是需要和hdfs-site.xml中的配置一致。
# 编辑器打开hdfs-site.xml
# sudo vi etc/hadoop/hdfs-site.xml

```

0x05. 格式化hdfs
```
01. 
$ sudo vi /etc/group
root:x:0:hadoop

02. 
$ hdfs namenode -format    
只需要执行一次即可，如果在hadoop已经使用后再次执行，会清除掉hdfs上的所有数据。

```

0x06. 启动hadoop
```
经过上文所描述配置和操作后，下面就可以启动这个单节点的集群    

01. 执行启动命令：
sbin/start-dfs.sh    
执行该命令时，如果有yes /no提示，输入yes，回车即可。    

02. 
接下来，执行：
sbin/start-yarn.sh    

03. 
执行完这两个命令后，Hadoop会启动并运行    

04. 
执行 jps命令，会看到Hadoop相关的进程，如下图：

05.
# 浏览器打开 http://localhost:50070/，会看到hdfs管理页面

06. 
浏览器打开http://localhost:8088，会看到hadoop进程管理页面

```


0x07. WordCount验证
```
01. dfs上创建input目录
$ sudo bin/hadoop fs -mkdir -p input

02.
把hadoop目录下的README.txt拷贝到dfs新建的input里
$ sudo hadoop fs -copyFromLocal README.txt input

03. 运行WordCount
$ sudo hadoop jar share/hadoop/mapreduce/sources/hadoop-mapreduce-examples-2.4.0-sources.jar org.apache.hadoop.examples.WordCount input output
 
04.
# 可以看到执行过程
 
05. 
# 运行完毕后，查看单词统计结果
$ hadoop fs -cat output/*
```


