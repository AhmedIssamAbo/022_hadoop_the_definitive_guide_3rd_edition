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
hadoop@ubuntu:/usr/local/hadoop$ sudo bin/hadoop jar share/hadoop/mapreduce/sources/hadoop-mapreduce-examples-2.6.4-sources.jar org.apache.hadoop.examples.WordCount input output
16/07/11 13:25:20 INFO Configuration.deprecation: session.id is deprecated. Instead, use dfs.metrics.session-id
16/07/11 13:25:20 INFO jvm.JvmMetrics: Initializing JVM Metrics with processName=JobTracker, sessionId=
16/07/11 13:25:20 INFO input.FileInputFormat: Total input paths to process : 1
16/07/11 13:25:20 INFO mapreduce.JobSubmitter: number of splits:1
16/07/11 13:25:21 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_local1467934750_0001
16/07/11 13:25:22 INFO mapreduce.Job: The url to track the job: http://localhost:8080/
16/07/11 13:25:22 INFO mapreduce.Job: Running job: job_local1467934750_0001
16/07/11 13:25:22 INFO mapred.LocalJobRunner: OutputCommitter set in config null
16/07/11 13:25:22 INFO mapred.LocalJobRunner: OutputCommitter is org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter
16/07/11 13:25:22 INFO mapred.LocalJobRunner: Waiting for map tasks
16/07/11 13:25:22 INFO mapred.LocalJobRunner: Starting task: attempt_local1467934750_0001_m_000000_0
16/07/11 13:25:22 INFO mapred.Task:  Using ResourceCalculatorProcessTree : [ ]
16/07/11 13:25:22 INFO mapred.MapTask: Processing split: file:/usr/local/hadoop-2.6.4/input/README.txt:0+1366
16/07/11 13:25:23 INFO mapreduce.Job: Job job_local1467934750_0001 running in uber mode : false
16/07/11 13:25:23 INFO mapred.MapTask: (EQUATOR) 0 kvi 26214396(104857584)
16/07/11 13:25:23 INFO mapred.MapTask: mapreduce.task.io.sort.mb: 100
16/07/11 13:25:23 INFO mapred.MapTask: soft limit at 83886080
16/07/11 13:25:23 INFO mapred.MapTask: bufstart = 0; bufvoid = 104857600
16/07/11 13:25:23 INFO mapred.MapTask: kvstart = 26214396; length = 6553600
16/07/11 13:25:23 INFO mapreduce.Job:  map 0% reduce 0%
16/07/11 13:25:23 INFO mapred.MapTask: Map output collector class = org.apache.hadoop.mapred.MapTask$MapOutputBuffer
16/07/11 13:25:23 INFO mapred.LocalJobRunner: 
16/07/11 13:25:23 INFO mapred.MapTask: Starting flush of map output
16/07/11 13:25:23 INFO mapred.MapTask: Spilling map output
16/07/11 13:25:23 INFO mapred.MapTask: bufstart = 0; bufend = 2055; bufvoid = 104857600
16/07/11 13:25:23 INFO mapred.MapTask: kvstart = 26214396(104857584); kvend = 26213684(104854736); length = 713/6553600
16/07/11 13:25:23 INFO mapred.MapTask: Finished spill 0
16/07/11 13:25:24 INFO mapred.Task: Task:attempt_local1467934750_0001_m_000000_0 is done. And is in the process of committing
16/07/11 13:25:24 INFO mapred.LocalJobRunner: map
16/07/11 13:25:24 INFO mapred.Task: Task 'attempt_local1467934750_0001_m_000000_0' done.
16/07/11 13:25:24 INFO mapred.LocalJobRunner: Finishing task: attempt_local1467934750_0001_m_000000_0
16/07/11 13:25:24 INFO mapred.LocalJobRunner: map task executor complete.
16/07/11 13:25:24 INFO mapred.LocalJobRunner: Waiting for reduce tasks
16/07/11 13:25:24 INFO mapred.LocalJobRunner: Starting task: attempt_local1467934750_0001_r_000000_0
16/07/11 13:25:24 INFO mapred.Task:  Using ResourceCalculatorProcessTree : [ ]
16/07/11 13:25:24 INFO mapred.ReduceTask: Using ShuffleConsumerPlugin: org.apache.hadoop.mapreduce.task.reduce.Shuffle@5f9c3821
16/07/11 13:25:24 INFO reduce.MergeManagerImpl: MergerManager: memoryLimit=333971456, maxSingleShuffleLimit=83492864, mergeThreshold=220421168, ioSortFactor=10, memToMemMergeOutputsThreshold=10
16/07/11 13:25:24 INFO reduce.EventFetcher: attempt_local1467934750_0001_r_000000_0 Thread started: EventFetcher for fetching Map Completion Events
16/07/11 13:25:24 INFO reduce.LocalFetcher: localfetcher#1 about to shuffle output of map attempt_local1467934750_0001_m_000000_0 decomp: 1832 len: 1836 to MEMORY
16/07/11 13:25:24 INFO reduce.InMemoryMapOutput: Read 1832 bytes from map-output for attempt_local1467934750_0001_m_000000_0
16/07/11 13:25:24 INFO reduce.MergeManagerImpl: closeInMemoryFile -> map-output of size: 1832, inMemoryMapOutputs.size() -> 1, commitMemory -> 0, usedMemory ->1832
16/07/11 13:25:24 INFO reduce.EventFetcher: EventFetcher is interrupted.. Returning
16/07/11 13:25:24 INFO mapred.LocalJobRunner: 1 / 1 copied.
16/07/11 13:25:24 INFO reduce.MergeManagerImpl: finalMerge called with 1 in-memory map-outputs and 0 on-disk map-outputs
16/07/11 13:25:24 INFO mapred.Merger: Merging 1 sorted segments
16/07/11 13:25:24 INFO mapred.Merger: Down to the last merge-pass, with 1 segments left of total size: 1823 bytes
16/07/11 13:25:24 INFO reduce.MergeManagerImpl: Merged 1 segments, 1832 bytes to disk to satisfy reduce memory limit
16/07/11 13:25:24 INFO reduce.MergeManagerImpl: Merging 1 files, 1836 bytes from disk
16/07/11 13:25:24 INFO reduce.MergeManagerImpl: Merging 0 segments, 0 bytes from memory into reduce
16/07/11 13:25:24 INFO mapred.Merger: Merging 1 sorted segments
16/07/11 13:25:24 INFO mapred.Merger: Down to the last merge-pass, with 1 segments left of total size: 1823 bytes
16/07/11 13:25:24 INFO mapred.LocalJobRunner: 1 / 1 copied.
16/07/11 13:25:24 INFO Configuration.deprecation: mapred.skip.on is deprecated. Instead, use mapreduce.job.skiprecords
16/07/11 13:25:24 INFO mapred.Task: Task:attempt_local1467934750_0001_r_000000_0 is done. And is in the process of committing
16/07/11 13:25:24 INFO mapred.LocalJobRunner: 1 / 1 copied.
16/07/11 13:25:24 INFO mapred.Task: Task attempt_local1467934750_0001_r_000000_0 is allowed to commit now
16/07/11 13:25:24 INFO output.FileOutputCommitter: Saved output of task 'attempt_local1467934750_0001_r_000000_0' to file:/usr/local/hadoop-2.6.4/output/_temporary/0/task_local1467934750_0001_r_000000
16/07/11 13:25:24 INFO mapred.LocalJobRunner: reduce > reduce
16/07/11 13:25:24 INFO mapred.Task: Task 'attempt_local1467934750_0001_r_000000_0' done.
16/07/11 13:25:24 INFO mapred.LocalJobRunner: Finishing task: attempt_local1467934750_0001_r_000000_0
16/07/11 13:25:24 INFO mapred.LocalJobRunner: reduce task executor complete.
16/07/11 13:25:24 INFO mapreduce.Job:  map 100% reduce 100%
16/07/11 13:25:24 INFO mapreduce.Job: Job job_local1467934750_0001 completed successfully
16/07/11 13:25:24 INFO mapreduce.Job: Counters: 33
	File System Counters
		FILE: Number of bytes read=547480
		FILE: Number of bytes written=1057226
		FILE: Number of read operations=0
		FILE: Number of large read operations=0
		FILE: Number of write operations=0
	Map-Reduce Framework
		Map input records=31
		Map output records=179
		Map output bytes=2055
		Map output materialized bytes=1836
		Input split bytes=110
		Combine input records=179
		Combine output records=131
		Reduce input groups=131
		Reduce shuffle bytes=1836
		Reduce input records=131
		Reduce output records=131
		Spilled Records=262
		Shuffled Maps =1
		Failed Shuffles=0
		Merged Map outputs=1
		GC time elapsed (ms)=0
		CPU time spent (ms)=0
		Physical memory (bytes) snapshot=0
		Virtual memory (bytes) snapshot=0
		Total committed heap usage (bytes)=404750336
	Shuffle Errors
		BAD_ID=0
		CONNECTION=0
		IO_ERROR=0
		WRONG_LENGTH=0
		WRONG_MAP=0
		WRONG_REDUCE=0
	File Input Format Counters 
		Bytes Read=1366
	File Output Format Counters 
		Bytes Written=1326



# 执行 cat output/*，查看字符统计结果
$ ls -lh output/
$ cat ./output/part-r-00000 
$ cat ./output/_SUCCESS 
$ cat output/*
$ exit
```


