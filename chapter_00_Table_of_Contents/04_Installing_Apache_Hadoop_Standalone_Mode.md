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
```


0x0. 
```
```


