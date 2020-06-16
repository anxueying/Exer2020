# 说明

- 版本：原来讲的是2.7，现在讲3.1.3

- 删减部分：
  - 入门：第5章 Hadoop编译源码 不讲 一般不会用到

- 学习时长：总计7天左右
  - 入门：1.5天左右
- 重点：
  - Hadoop运行环境搭建
  - Hadoop运行模式
    - 本地
    - 完全分布式
    - 伪分布式（删减，不讲）：一台虚拟机上模拟分布式场景



# 一、入门

## 1. 概论

### 1. 概念

**why**

无法在一定时间范围内用常规软件工具进行捕捉、管理和处理

**what**

数据集合，信息资产

**how**

新处理模式，**海量**、**高增长率**和**多样化**。解决 海量数据的存储和海量数据的分析计算问题。

**数据存储单位从小到大**

bit->Byte->KB->MB->GB->**TB->PB->EB**->ZB->YB->BB->NB->DB

每位之间倍数为1024

BAT等大厂一般为PB级别，小公司一般TB级别

### 2. 特点

大量，高速，多样，低价值密度

### 3. 应用场景

物流仓储、零售、旅游、广告、保险、金融、房产、人工智能

### 4. 发展前景

17年才有的专业，目前人才需求较大

### 5. 业务流程

1. 产品人员提需求
2. 数据部门搭建数据平台、分析数据指标
3. 数据可视化

### 6. 部门组织结构

![image-20200610122131611](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200610122131.png)

## 2.  Hadoop及大数据生态

### 1. what

- Apache开发的分布式系统基础架构
- 解决：海量数据的存储和海量数据的分析计算问题
- 广义：Hadoop生态圈

![image-20200610141152004](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200610141152.png)

### 2. history

Doug Cutting 开发的Lucene，学习和模仿Google解决这些问题的方法：微型版Nutch。

Google在大数据方面的三篇论文

- GFS --> HDFS  存储
- Map-Reduce -->MR  计算
- BigTable --> HBase

### 3. version

- [Apache Hadoop](http://hadoop.apache.org/releases.html)：最原始（最基础）的[版本](https://archive.apache.org/dist/hadoop/common/)，对于入门学习最好。

- [Cloudera Hadoop](https://www.cloudera.com/downloads/cdh/5-10-0.html)：[CDH](http://archive-primary.cloudera.com/cdh5/cdh/5/)，内部集成了很多大数据框架

- [Hortonworks Hadoop](https://hortonworks.com/products/data-center/hdp/)：[HDP](https://hortonworks.com/downloads/#data-platform)，文档较好
- CDP：CDH收购了HDP后出的，很贵，CDH、HDP不太更新了

### 4. Advantage

- 高可靠性：底层维护多个数据副本。某个计算元素或存储出现故障，不会导致数据丢失
- 高扩展性：在集群间分配任务数据，可方便的扩展数以千计的节点
- 高效性：MapReduce思想下，Hadoop并行工作，加快任务处理速度
- 高容错性：自动将失败的任务重新分配

### 5. Construction（重点）

#### 1. Hadoop1.x和Hadoop2.x区别

分开的好处：

1. 降低耦合性
2. 可以将资源调度模块给其他框架使用

![image-20200610143042528](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200610143042.png)

#### 2. HDFS架构（数据存储）

##### 1. NameNode(nn)

存储文件的**元数据**及**每个文件的块列表**和**块所在的DateNode**等。

> 元数据：用来描述数据的数据。如：
>
> - 文件名
> - 文件目录结构
> - 文件属性
>   - 生成时间
>   - 副本数
>   - 文件权限

DateNode相当于书，NameNode相当于一本目录。

##### 2. DateNode(dn)

在本地文件系统**存储文件块数据**，以及**块数据的校验和**。

##### 3. Secondary NameNode(2nn)

每隔一段时间对NameNode元数据备份。

#### 3. YARN架构（资源调度）

![image-20200610150501333](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200610150501.png)

##### 1. Resource Manager (RM)

包工头 需要做项目

1. 处理客户端请求
2. 监控NodeManager
3. 启动或监控ApplicationMaster
4. 资源的分配与调度

##### 2. NodeManager (NM)

部门管理 管资源  各部门管理者

1. 管理单个节点上的资源
2. 处理来自ResourceManager的命令
3. 处理来自ApplicationMaster的命令

##### 3. ApplicationMaster(AM) 

管任务 项目负责人，组织各部门的人过来做各种任务，监控项目进度和健康情况。

1. 负责数据的切分
2. 为应用程序申请资源并分配
3. 任务的监控与容错

##### 4. Container

项目经理也是员工，ApplicationMaster在Container中运行。

YARN中的资源抽象。封装了某个节点上的多维度资源，如内存、CPU、磁盘、网络等。

可以理解为容器，实际上是把资源锁在它里面，不让别人抢走。

#### 4. MapReduce架构（计算）

可以这么理解：Hive是对MapReduce的封装，用sql封装的。

##### 1. Map阶段

并行处理输入数据

##### 2. Reduce阶段

对Map结果进行汇总

![image-20200610152213705](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200610152213.png)

### 6. 大数据技术生态体系

![image-20200610152642767](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200610152642.png)

- 数据来源层：不用管
- 数据传输层：
  - HDFS, HBase
  - Kafka
- 数据存储层： 
  - Sqoop, Flume
  - Kafka
- 资源管理层：YARN
- 数据计算层：
  - MapReduce，Hive
  - Spark Core， Spark Mlib，Spark sql
  - 实时：Spark Streaming， Flink， Strom（过时）

- 任务调度层
  - Oozie（不学）
  - Azkaban
- ZooKeeper

### 7. 推荐系统框架图

![image-20200610154554045](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200610154554.png)

## 3. Hadoop运行环境搭建（重点）

**模板机必改：**

1. ip 
2. 防火墙
3. 目录
4. 用户 ，配root权限

**IP配置**

```shell
sudo vim /etc/sysconfig/network-scripts/ifcfg-ens33
```

```shell
DEVICE=ens33
TYPE=Ethernet
ONBOOT=yes
BOOTPROTO=static
NAME="ens33"
IPADDR=192.168.1.100
PREFIX=24
GATEWAY=192.168.1.2
DNS1=114.114.114.114
DNS2=8.8.8.8
```

**重启服务**

```shell
systemctl restart network
```

**使用xshell进行连接**

**安装必须的软件**

```shell
sudo yum install -y epel-release

sudo yum install -y psmisc nc net-tools rsync vim lrzsz ntp libzstd openssl-static tree iotop git
```

**修改hostname**

```
sudo hostnamectl --static set-hostname hadoop100
```

**配置主机名称映射，打开/etc/hosts**

```shell
sudo vim /etc/hosts
```

添加如下内容

```shell
192.168.1.100 hadoop100
192.168.1.101 hadoop101
192.168.1.102 hadoop102
192.168.1.103 hadoop103
192.168.1.104 hadoop104
192.168.1.105 hadoop105
192.168.1.106 hadoop106
192.168.1.107 hadoop107
192.168.1.108 hadoop108
```

**修改window10的主机映射文件（hosts文件）**

进入C:\Windows\System32\drivers\etc路径

打开hosts文件并添加如下内容

```
192.168.1.100 hadoop100
192.168.1.101 hadoop101
192.168.1.102 hadoop102
192.168.1.103 hadoop103
192.168.1.104 hadoop104
192.168.1.105 hadoop105
192.168.1.106 hadoop106
192.168.1.107 hadoop107
192.168.1.108 hadoop108
```

**关闭防火墙**

```shell
sudo systemctl stop firewalld          #临时
sudo systemctl disable firewalld       #永久
```

**创建atguigu用户并赋予root权限**

```shell
sudo useradd atguigu
sudo passwd atguigu


vi sudo

#做如下修改
root    ALL=(ALL)     ALL
atguigu   ALL=(ALL)     NOPASSWD:ALL
```

**在/opt下创建文件夹**

在/opt目录下创建module、software文件夹，修改文件夹的所有者为atguigu

```shell
#方式一：
sudo mkdir /opt/module /opt/software

sudo chown atguigu:atguigu /opt/module /opt/software

sudo chgrp atguigu:atguigu /opt/module /opt/software

#方式二
sudo mkdir module
sudo mkdir software
chown atguigu module/
chown atguigu software/
chgrp atguigu module/
chgrp atguigu software/
```

**克隆3台机器**

hadoop102，hadoop103，hadoop104

**改ip和hostname，改完重启，用xshell连接时使用atguigu账号**

### 1. 安装JDK

```
tar -zxvf jdk-8u212-linux-x64.tar.gz -C /opt/module/
```

**设置JDK环境变量**

```shell
[atguigu@hadoop102 profile.d]$ sudo vim my_env.sh
[atguigu@hadoop102 profile.d]$ cat my_env.sh 
#JAVA_HOME,PATH 
# export 提升为全局变量
export JAVA_HOME=/opt/module/jdk1.8.0_212
export PATH=$PATH:$JAVA_HOME/bin

#HADOOP_HOME

[atguigu@hadoop102 profile.d]$ java -version
-bash: java: 未找到命令
[atguigu@hadoop102 profile.d]$ source my_env.sh
[atguigu@hadoop102 profile.d]$ java -version
java version "1.8.0_212"
Java(TM) SE Runtime Environment (build 1.8.0_212-b10)
Java HotSpot(TM) 64-Bit Server VM (build 25.212-b10, mixed mode)
```

**xargs**

将echo内容作为一个参数传递给命令，在本地创建一个test文件

```shell
echo "test.txt" | cat
echo "test.txt" | xargs cat
```

![image-20200610183005690](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200610183005.png)

-n1 变为一列

![image-20200612092656134](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200612092656.png)

### 2. 安装Hadoop

**解压**

```shell
[atguigu@hadoop102 software]$ tar -zxvf hadoop-3.1.3.tar.gz -C /opt/module/
```



**配置环境变量**

```shell
sudo vim /etc/profile.d/my_env.sh
```

```shell
#HADOOP_HOME
export HADOOP_HOME=/opt/module/hadoop-3.1.3
export PATH=$PATH:$HADOOP_HOME/bin
export PATH=$PATH:$HADOOP_HOME/sbin
```

```shell
source /etc/profile
```

测试是否安装成功

```shell
hadoop version
```



**重要目录**

（1）**bin目录**：存放对Hadoop相关服务（HDFS,YARN）进行操作的脚本

（2）**etc目录**：Hadoop的配置文件目录，存放Hadoop的配置文件

（3）lib目录：存放Hadoop的本地库（对数据进行压缩解压缩功能）

（4）**sbin目录**：存放启动或停止Hadoop相关服务的脚本

（5）share目录：存放Hadoop的依赖jar包、文档、和官方案例

## 4. Hadoop运行模式

### 1. [Local (Standalone) Mode](https://hadoop.apache.org/docs/stable/hadoop-project-dist/hadoop-common/SingleCluster.html#Standalone_Operation)

本地运行模式，官方wordcount

![image-20200612094809800](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200612094809.png)



```shell
# 创建目录
$ mkdir input
# copy文件到input目录
$ cp etc/hadoop/*.xml input
# 运行官方案例
# bin/hadoop jar  --hadoop: bin目录下的脚本 jar:表示运行一个jar包
# share/hadoop/mapreduce/hadoop-mapreduce-examples-3.2.1.jar --运行的jar包
# grep ： 筛选案例
# input ： 读取内容的目录
# output ： 将结果放入到output目录中，运行下方命令时该目录不存在，也不能存在。可以是别的名字
# 'dfs[a-z.]+' ： 正则表达式（随便自定义）
$ hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.2.1.jar grep input output 'dfs[a-z.]+'
$ cat output/*
1	dfsadmin


$ mkdir wcinput
$ vi wcinput/wc.input

hadoop yarn
hadoop mapreduce
atguigu
atguigu

$ hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.1.3.jar wordcount wcinput wcoutput

$ cat wcoutput/part-r-00000
atguigu 2
hadoop  2
mapreduce       1
yarn    1
```

### 2. [Pseudo-Distributed Mode](https://hadoop.apache.org/docs/stable/hadoop-project-dist/hadoop-common/SingleCluster.html#Pseudo-Distributed_Operation)

### 3. [Fully-Distributed Mode](https://hadoop.apache.org/docs/stable/hadoop-project-dist/hadoop-common/SingleCluster.html#Fully-Distributed_Operation)(重点)

分析：

​    1）准备3台客户机（关闭防火墙、静态ip、主机名称）

​    2）安装JDK

​    3）配置环境变量

​    4）安装Hadoop

​    5）配置环境变量

​	6）配置集群

​    7）单点启动

​    8）配置ssh

​    9）群起并测试集群

#### 1. 虚拟机准备

参见3. Hadoop运行环境搭建（重点）

#### 2. 编写集群分发脚本xsync

##### 1. scp （secure copy）安全拷贝

实现服务器与服务器之间的数据拷贝

```shell
#scp -r 本地路径（如果只想拷贝目录下的文件记得加*） 用户名@主机名:目录
$ scp -r $pdir/$fname $user@hadoop$host:$pdir/$fname
```

实操：

```shell
#从102推送到103
$ scp -r ./* atguigu@hadoop103:/opt/software/
#从104拉102的内容
$ scp -r atguigu@hadoop102:/opt/software/*  ./
#在103，将102的内容复制到104
$ scp -r atguigu@hadoop102:/opt/software/*  atguigu@hadoop103:/opt/software/

#不写用户，默认与当前用户一样的
$ scp -r @hadoop102:/opt/software/*  ./
```

> 拷贝过来的/opt/module目录，别忘了在hadoop102、hadoop103、hadoop104上修改所有文件的**所有者和所有者组**。sudo chown atguigu:atguigu -R /opt/module

因为一开始在102已经改好了，所以无需再操作了。

module文件夹同理。

```shell
# 把环境变量配置文件拷贝时需要目标客户端的root用户
$ scp -r /etc/profile.d/my_env.sh  root@hadoop103:/etc/profile.d/
```

```shell
[atguigu@hadoop103 module]$ source /etc/profile.d/my_env.sh 
[atguigu@hadoop103 module]$ hadoop version
```

104机同理。

##### 2. rsync远程同步工具

rsync主要用于备份和镜像。具有速度快、避免复制相同内容和支持符号链接的优点。

rsync和scp区别：用rsync做文件的复制要比scp的速度快，rsync只对差异文件做更新。scp是把所有文件都复制过去。

**初次copy建议scp，不需检查，效率更高。**

**后续copy用rsync，避免人工拷贝不完全，也节省时间。**

```shell
#102 新建了一个aa.txt
[atguigu@hadoop102 software]$ rsync -av ./* atguigu@hadoop103:/opt/software/
atguigu@hadoop103's password: 
sending incremental file list
aa.txt
hadoop-3.1.3.tar.gz
jdk-8u212-linux-x64.tar.gz

sent 129,712 bytes  received 226,605 bytes  41,919.65 bytes/sec
total size is 533,089,015  speedup is 1,496.11


#103 只拷贝了aa.txt过来
[atguigu@hadoop103 software]$ ll
总用量 520604
-rw-rw-r--. 1 atguigu atguigu         3 6月  12 11:05 aa.txt
-rw-r--r--. 1 atguigu atguigu 338075860 3月   9 17:07 hadoop-3.1.3.tar.gz
-rw-r--r--. 1 atguigu atguigu 195013152 7月   3 2019 jdk-8u212-linux-x64.tar.gz

```

##### 3. xsync 集群分发脚本

循环复制文件到所有节点的相同目录下

```shell
#!/bin/bash
#1. 判断参数个数
if [ $# -lt 1 ]
then
  echo Not Enough Arguement!
  exit;
fi
#2. 遍历集群所有机器
for host in hadoop102 hadoop103 hadoop104
do
  echo ====================  $host  ====================
  #3. 遍历所有目录，挨个发送
  for file in $@
  do
    #4 判断文件是否存在
    # file是传进来的参数 中 分解出来的每个文件
    if [ -e $file ]
    then
      #5. 获取父目录
      # cd -P 软链接（进入真实的物理地址）
      # dirname命令可以返回文件所在的目录
      # $file 表示当前动行的命令名
      # $(dirname $file) 切换到 file 所在的目录
      # 在bash中，$( )与（反引号）都是用来作命令替换的。
      # 1.每个命令之间用;隔开
      # 说明：各命令的执行给果，不会影响其它命令的执行。换句话说，各个命令都会执行，但不保证每个命令都执行成功。
      # 因此，下方命令的执行步骤
      #1. 进入file所在的目录（实际物理地址）
      #2. pwd获得当前路径，并赋值给pdir
      pdir=$(cd -P $(dirname $file); pwd)
      #6. 获取当前文件的名称
      #获得文件的文件名（去掉路径），并赋值给fname
      fname=$(basename $file)
      # 创建父目录
      ssh $host "mkdir -p $pdir"
      #同步 
      rsync -av $pdir/$fname $host:$pdir
    else
      echo $file does not exists!
    fi
  done
done
```

```shell
#修改脚本 xsync 具有执行权限
$ chmod u+x xsync
#将脚本移动到hadoop/bin中，以便全局调用(已配置hadoop环境变量)
$ mv xsync /opt/module/hadoop-3.1.3/bin/
#测试脚本
$ xsync /opt/module/hadoop-3.1.3/bin/xsync
```

#### 3. ssh免密登录配置

```
ssh 另一台电脑的ip地址
```

##### 0. 加密方式

- 对称加密：加密解密用的同样的方式
- 非对称加密：比如加密方+p，解密方-p

免密登录的原理（了解）：

![image-20200612114328593](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200612114328.png)

##### 1. 配置免密登录

群起集群需要ssh，单起不需要

```shell
$ ssh-keygen -t rsa
```

然后敲（三个回车），就会生成两个文件id_rsa（私钥）、id_rsa.pub（公钥）

```shell
$ ssh-copy-id hadoop102
$ ssh-copy-id hadoop103
$ ssh-copy-id hadoop104
```

```
[atguigu@hadoop102 .ssh]$ ll
总用量 16
-rw-------. 1 atguigu atguigu  399 6月  12 11:48 authorized_keys
-rw-------. 1 atguigu atguigu 1675 6月  12 11:46 id_rsa
-rw-r--r--. 1 atguigu atguigu  399 6月  12 11:46 id_rsa.pub
-rw-r--r--. 1 atguigu atguigu  555 6月  12 11:48 known_hosts

[atguigu@hadoop103 .ssh]$ ll
总用量 16
-rw-------. 1 atguigu atguigu  798 6月  12 11:58 authorized_keys
-rw-------. 1 atguigu atguigu 1675 6月  12 11:57 id_rsa
-rw-r--r--. 1 atguigu atguigu  399 6月  12 11:57 id_rsa.pub
-rw-r--r--. 1 atguigu atguigu  555 6月  12 11:58 known_hosts



[atguigu@hadoop104 .ssh]$ ll
总用量 8
-rw-------. 1 atguigu atguigu 399 6月  12 11:48 authorized_keys
-rw-r--r--. 1 atguigu atguigu 185 6月  12 10:45 known_hosts

[atguigu@hadoop104 .ssh]$ cat authorized_keys 
ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC6eZDMQZIS1oTv/na5mytJNULUMvG5P4L7Kxuao1a4a4iEsI0LpLpjXqICB9s+E3qfPeGVOHYy1oxqDlxrDgPH1BxX33SBNGFG+ankXvWnZNJyUZrMgNV6sB2HZySGqLF7iNTurswKfZ0ohd7CC+kW6e3gS+0WqyRpzJZtzCndkQem7fEKu9MTrsmoVxcx4uUmWmaUfQTTjAYs4Gt8JIHNtMTMd1F1UO77sO0RfodXQwR18QcZJy35PDIpjnzgl9vb+FOQQdEILe5CZ0Kn0xI0ynpQ5OuYVr3M4VnGr6hm321HIwyRkslj8UWvDb+zNEQisPP0jtZ6TSugfS27x18X atguigu@hadoop102
ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC7lsGvgzQp8BqG0Gul9K9yqIv2tNML3N/P8m8qnxf8Oi4QK/UW13WBpXSJsqznQDDDhjbX/BuZWczRrQH1WaCbieABFbhkOpQNVMnAW0ok/lQg7hFIt7kMMFXBGUtAo4B9j1SO5UpHMxF+RbJFh2x61ch6n5L+chQSue4iQp+j8JhkDLXZrha/uDhUlss9WYjDXf2nByWTXTRiZZFFfovpPQsolg9yTSDngH90El3g58i/iHm5Ep5mYfcLXsblVSAq3Fe/pOwWKbQMEz0y/xzh5vKcegXXrL/9z5qtO4WyD5uW51tIau62ymUs6k4hZv48BrqoMZjTP+3kZWpJ5FjJ atguigu@hadoop103
```

known_hosts中放置了曾经ssh连接过的所有ip

**在hadoop103也生成公钥和私钥，并拷贝到三台机器上。目的是hadoop103也可以无密登录其它机器**

```shell
#在/home/atguigu（家cd~）目录下创建xsync文件，添加分发脚本语句
#修改脚本 xsync 具有执行权限
[atguigu@hadoop102 ~]$ sudo chmod u+x xsync
[atguigu@hadoop102 ~]$ ll
总用量 4
-rwxrwxr-x. 1 atguigu atguigu 623 6月  12 12:02 xsync
#新建测试文件
[atguigu@hadoop102 ~]$ touch /opt/software/test.txt
#运行脚本（已配置环境变量）
[atguigu@hadoop102 ~]$ ./xsync /opt/software/test.txt 
==================== hadoop102 ====================
sending incremental file list

sent 65 bytes  received 12 bytes  154.00 bytes/sec
total size is 0  speedup is 0.00
==================== hadoop103 ====================
sending incremental file list
test.txt

sent 108 bytes  received 35 bytes  286.00 bytes/sec
total size is 0  speedup is 0.00
==================== hadoop104 ====================
sending incremental file list
test.txt

sent 108 bytes  received 35 bytes  286.00 bytes/sec
total size is 0  speedup is 0.00
```

##### 2..ssh文件夹下（~/.ssh）的文件功能解释

| 文件            | 功能                                   |
| --------------- | -------------------------------------- |
| known_hosts     | 记录ssh访问过计算机的公钥(public  key) |
| id_rsa          | 生成的私钥                             |
| id_rsa.pub      | 生成的公钥                             |
| authorized_keys | 存放授权过的无密登录服务器公钥         |

#### 4. 集群配置

##### 1. 集群部署规划

|      | hadoop102          | hadoop103                    | hadoop104                   |
| ---- | ------------------ | ---------------------------- | --------------------------- |
| HDFS | NameNode  DataNode | DataNode                     | SecondaryNameNode  DataNode |
| YARN | NodeManager        | ResourceManager  NodeManager | NodeManager                 |
| 启动 | start-dfs.sh       | start-yarn.sh                |                             |

- NameNode和SecondaryNameNode不要安装在同一台服务器

- ResourceManager也很消耗内存，不要和NameNode、SecondaryNameNode配置在同一台机器上。

如果资源够的话，需要6台，每一台的配置如下：

1. namenode
2. resourcemanager
3. SecondaryNameNode
4. DataNode  ，NodeManager  
5. DataNode  ，NodeManager
6. DataNode  ，NodeManager

为了少开，将1-3分别并到4、5、6中，这样只需要开三台就好了。

##### 2. 配置集群

core-default.xml 默认配置，当修改了core-site.xml后，会优先使用core-site中的配置参数

![image-20200613090750752](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200613090750.png)

根据这里面的默认配置，重新根据自己的需要，配置文件内容。如版本与图中不一致，可导官方文档中查看[对应版本](https://hadoop.apache.org/docs/)的xml内容标签

![image-20200612142126196](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200612142126.png)

**核心配置文件**

配置core-site.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
</configuration>
	<!--指定HDFS中NameNode的地址 -->
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://hadoop102:9820</value>
	</property>
	<!-- 指定Hadoop运行时产生文件的存储目录 -->
    <property>
        <name>hadoop.tmp.dir</name>
        <value>/opt/module/hadoop-3.1.3/data</value>
	</property>
	<!--  通过web界面操作hdfs的权限 -->
	<property>
        <name>hadoop.http.staticuser.user</name>
        <value>atguigu</value>
	</property>
	<!-- 后面hive的兼容性配置  -->
    <property>
        <name>hadoop.proxyuser.atguigu.hosts</name>
        <value>*</value>
    </property>
    <property>
        <name>hadoop.proxyuser.atguigu.groups</name>
        <value>*</value>
	</property>
</configuration>

```

配置hdfs-site.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>

<configuration>
    <property>
        <name>dfs.namenode.secondary.http-address</name>
        <value>hadoop104:9868</value>
    </property>
</configuration>
```

配置yarn-site.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>

<configuration>
	<!--  Reducer获取数据的方式-->
    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
	</property>
	<!--  指定YARN的ResourceManager的地址-->
    <property>
        <name>yarn.resourcemanager.hostname</name>
        <value>hadoop103</value>
	</property>
	<!-- 环境变量通过从NodeManagers的容器继承的环境属性，对于mapreduce应用程序，除了默认值 hadoop op_mapred_home应该被添加外。属性值 还有如下-->
    <property>
        <name>yarn.nodemanager.env-whitelist</name>
  <value>JAVA_HOME,HADOOP_COMMON_HOME,HADOOP_HDFS_HOME,HADOOP_CONF_DIR,CLASSPATH_PREPEND_DISTCACHE,HADOOP_YARN_HOME,HADOOP_MAPRED_HOME</value>
	</property>
	<!-- 解决Yarn在执行程序遇到超出虚拟内存限制，Container被kill  -->
    <property>
        <name>yarn.nodemanager.pmem-check-enabled</name>
        <value>false</value>
    </property>
    <property>
        <name>yarn.nodemanager.vmem-check-enabled</name>
        <value>false</value>
    </property>
	<!-- 后面hive的兼容性配置  -->
    <property>
        <name>yarn.scheduler.minimum-allocation-mb</name>
        <value>512</value>
    </property>
    <property>
        <name>yarn.scheduler.maximum-allocation-mb</name>
        <value>4096</value>
    </property>
    <property>
        <name>yarn.nodemanager.resource.memory-mb</name>
        <value>4096</value>
	</property>
</configuration>
```

配置完后，分发配置好的hadoop文件(如xsync没配置环境变量则用路径调用)

```shell
$ xsync  /opt/module/hadoop-3.1.3/etc/hadoop/
```



#### 5. 群起集群

如初次启动，需要格式化NN

```shell
$ hdfs namenode -format
```

> - 初次格式化之后产生的文件夹，只能格式化一次！！
> - 如要再次格式化，一定要先停止上次启动的所有NN和DN进程，然后再删除data和logs，再格式化！！
> - PS：data和logs不要分发！！不要分发！！不要分发！！

![image-20200612144253986](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200612144254.png)

```shell
# 在NameNode节点（hadoop102）机器启动HDFS--已配置环境变量
[atguigu@hadoop102 hadoop-3.1.3]$ start-dfs.sh
Starting namenodes on [hadoop102]
Starting datanodes
hadoop104: WARNING: /opt/module/hadoop-3.1.3/logs does not exist. Creating.
hadoop103: WARNING: /opt/module/hadoop-3.1.3/logs does not exist. Creating.
Starting secondary namenodes [hadoop104]
# jdk/bin中的jps，查看所有的java进程
[atguigu@hadoop102 hadoop-3.1.3]$ jps
2874 DataNode
3130 Jps
2716 NameNode

# 在ResourceManager节点（hadoop103）机器启动YARN--已配置环境变量
[atguigu@hadoop103 ~]$ start-yarn.sh
Starting resourcemanager
Starting nodemanagers
# jdk/bin中的jps，查看所有的java进程
[atguigu@hadoop103 ~]$ jps
2258 ResourceManager
2741 Jps
2393 NodeManager
2076 DataNode
```

**web端查看**

- [namenode](http://hadoop102:9870/) HDFS信息

- [resourceManager](http://hadoop103:8088/) 任务信息

- [SecondaryNameNode](http://hadoop104:9868/)

小问题：hadoop104可以连接，但页面信息不正常

![image-20200612162700526](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200612162700.png)

**解决办法：**

1. 关闭集群服务

2. dfs-dust.js的第61行

```shell
return moment(Number(v)).format('ddd MMM DD HH:mm:ss ZZ YYYY');
```

改成

```shell
return new Date(Number(v)).toLocaleString();
```

3. 开启集群服务
4. shift+F5  刷新hadoop104的web页面

![image-20200612163227931](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200612163227.png)



##### 集群基本测试

```shell
#新建一个测试文件并输入文本
[atguigu@hadoop102 hadoop-3.1.3]$ vim name.txt
#将文件传到HDFS中
[atguigu@hadoop102 hadoop-3.1.3]$ hadoop dfs -put name.txt /input/
WARNING: Use of this script to execute dfs is deprecated.
WARNING: Attempting to execute replacement "hdfs dfs" instead.

2020-06-12 22:35:59,985 INFO sasl.SaslDataTransferClient: SASL encryption trust check: localHostTrusted = false, remoteHostTrusted = false
# 查看web是否已经上传成功
# 使用hdfs中的input和output
[atguigu@hadoop102 hadoop-3.1.3]$ hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.1.3.jar wordcount /input /output

```

hadoop103:

![image-20200612224103616](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200612224103.png)



hadoop102: output中可看到运行结果，也可下载

![image-20200612224038215](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200612224038.png)

##### 写一个脚本一键群起（停）

```shell
[atguigu@hadoop102 bin]$ cat mycluster 
#!/bin/bash
case $1 in
"start")
#dfs  yarn
ssh hadoop102 start-dfs.sh
ssh hadoop103 start-yarn.sh
;;
"stop")
# dfs yarn
ssh hadoop102 stop-dfs.sh
ssh hadoop103 stop-yarn.sh
;;
*)
echo "args is error! please input start or stop"
;;
esac
[atguigu@hadoop102 bin]$ chmod u+x mycluster
```

##### jpscall 一键查看集群各机器jps

```shell
[atguigu@hadoop102 bin]$ cat jpscall 
#!/bin/bash
for name in hadoop102 hadoop103 hadoop104
do
	echo "=====$name===="	
	ssh $name jps
done
[atguigu@hadoop102 bin]$ chmod u+x jpscall 
```

**测试两个脚本**

```shell
[atguigu@hadoop102 bin]$ mycluster stop
Stopping namenodes on [hadoop102]
Stopping datanodes
Stopping secondary namenodes [hadoop104]
Stopping nodemanagers
Stopping resourcemanager
[atguigu@hadoop102 bin]$ jpscall 
=====hadoop102====
5618 JobHistoryServer
8184 Jps
=====hadoop103====
7134 Jps
5023 ApplicationHistoryServer
=====hadoop104====
7152 Jps
[atguigu@hadoop102 bin]$ mycluster start
Starting namenodes on [hadoop102]
Starting datanodes
Starting secondary namenodes [hadoop104]
Starting resourcemanager
Starting nodemanagers
```

#### 6. 集群启动/停止方式总结

注意，在102上是dfs，103上是yarn

##### 1. 各个服务组件逐一启动/停止

（1）分别启动/停止HDFS组件

```shell
$ hdfs --daemon start/stop namenode/datanode/secondarynamenode
```

（2）启动/停止YARN

```shell
$ yarn --daemon start/stop resourcemanager/nodemanager
```

##### 2. 各个模块分开启动/停止（配置ssh是前提）常用

（1）整体启动/停止HDFS

```shell
$ start-dfs.sh
$ stop-dfs.sh
```

（2）整体启动/停止YARN

```shell
$ start-yarn.sh
$ stop-yarn.sh
```

#### 7. 配置历史服务器

```shell
[atguigu@hadoop102 bin]$ cd /opt/module/hadoop-3.1.3/etc/hadoop/
[atguigu@hadoop102 hadoop]$ vim mapred-site.xml
```

```xml
<!-- 历史服务器端地址 -->
<property>
    <name>mapreduce.jobhistory.address</name>
    <value>hadoop102:10020</value>
</property>

<!-- 历史服务器web端地址 -->
<property>
    <name>mapreduce.jobhistory.webapp.address</name>
    <value>hadoop102:19888</value>
</property>
```

```shell
[atguigu@hadoop102 hadoop]$ xsync $HADOOP_HOME/etc/hadoop/mapred-site.xml 
[atguigu@hadoop102 hadoop]$ mapred --daemon start historyserver
[atguigu@hadoop102 hadoop]$ jps
4673 NodeManager
4210 NameNode
4370 DataNode
5143 JobHistoryServer
5210 Jps
```

http://hadoop102:19888/jobhistory

#### 8. 配置日志的聚集

日志聚集概念：应用运行完成以后，**将程序运行日志信息上传到HDFS系统上。**

日志聚集功能好处：可以方便的查看到程序运行详情，方便开发调试。

> 注意：开启日志聚集功能，需要重新启动
>
> - NodeManager 
> - ResourceManager
> - HistoryManager

开启日志聚集功能具体步骤如下：

1）配置yarn-site.xml

vim yarn-site.xml

在该文件里面增加如下配置。

```xml
<!-- 开启日志聚集  -->
<property>
    <name>yarn.log-aggregation-enable</name>
    <value>true</value>
</property>
<!-- 访问路径-->
<property>  
    <name>yarn.log.server.url</name>  
    <value>http://hadoop102:19888/jobhistory/logs</value>
</property>
<!-- 保存的时间7天 -->
<property>
    <name>yarn.log-aggregation.retain-seconds</name>
    <value>604800</value>
</property>
```

2）分发配置

```shell
$ xsync $HADOOP_HOME/etc/hadoop/yarn-site.xml
```

重启集群（注意运行客户端）

```SHELL
# 关闭NodeManager
[atguigu@hadoop103 ~]$ stop-yarn.sh
# 关闭HistoryServer
[atguigu@hadoop102 ~]$ mapred --daemon stop historyserver
# 启动NodeManager
[atguigu@hadoop103 ~]$ start-yarn.sh
# 启动timelineserver
[atguigu@hadoop103 ~]$ yarn --daemon start timelineserver
# 启动HistoryServer
[atguigu@hadoop102 ~]$ mapred --daemon start historyserver
```

执行WordCount程序测试是否配置成功

```shell
[atguigu@hadoop102 ~]$ hadoop jar  $HADOOP_HOME/share/hadoop/mapreduce/hadoop-mapreduce-examples-3.1.3.jar wordcount /input /output
```

![image-20200612225609707](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200612225609.png)

![image-20200612225636454](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200612225636.png)

![image-20200612225658919](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200612225659.png)

以上信息确认无误。

#### 9. 集群时间同步

时间同步的方式：找一个机器，作为时间服务器，所有的机器与这台集群时间进行定时的同步，比如，每隔十分钟，同步一次时间。

同步原理：

![image-20200612230250774](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200612230250.png)

步骤：

1. 在**所有节点**关闭ntp服务和自启动

```shell
$ sudo systemctl stop ntpd
$ sudo systemctl disable ntpd
```

2. 修改配置文件（102）

```shell
# 102上修改ntp配置文件
[atguigu@hadoop102 ~]$ sudo vim /etc/ntp.conf
```

```shell
#a）修改1（授权192.168.1.0-192.168.1.255网段上的所有机器可以从这台机器上查询和同步时间） 删#
#restrict 192.168.1.0 mask 255.255.255.0 nomodify notrap为
restrict 192.168.1.0 mask 255.255.255.0 nomodify notrap

#b）修改2（集群在局域网中，不使用其他互联网上的时间） 加#
server 0.centos.pool.ntp.org iburst
server 1.centos.pool.ntp.org iburst
server 2.centos.pool.ntp.org iburst
server 3.centos.pool.ntp.org iburst
为
#server 0.centos.pool.ntp.org iburst
#server 1.centos.pool.ntp.org iburst
#server 2.centos.pool.ntp.org iburst
#server 3.centos.pool.ntp.org iburst

#c）添加3（当该节点丢失网络连接，依然可以采用本地时间作为时间服务器为集群中的其他节点提供时间同步）
server 127.127.1.0
fudge 127.127.1.0 stratum 10
```

```shell
# 102上修改ntpd 配置文件 让硬件时间与系统时间一起同步
$ sudo vim /etc/sysconfig/ntpd
```

```shell
#添加内容
SYNC_HWCLOCK=yes
```

3. 其他机器配置

```shell
$ crontab -e
```

```shell
#配置内容
*/10 * * * * /usr/sbin/ntpdate hadoop102
```

4. 在**所有节点**开启ntp服务和自启动

```shell
$ sudo systemctl start ntpd
$ sudo systemctl enable ntpd
```

5. 验证

   测试的时候可以将10分钟调整为1分钟，节省时间

```shell
#修改任意机器时间
$ sudo date -s "2017-9-11 11:11:11"
# 十分钟后查看机器是否与时间服务器同步
$ date
```

## 5. Hadoop编译源码（了解）

一般用不上，需要的时候查文档即可。

### 1. 前期准备工作

#### 1. CentOS联网

配置CentOS能连接外网。Linux虚拟机ping [www.baidu.com](http://www.baidu.com) 是畅通的

注意：采用root角色编译，减少文件夹权限出现问题

#### 2. jar包准备

**hadoop源码、JDK8、maven、ant 、protobuf**

（1）hadoop-3.1.3-src.tar.gz

（2）jdk-8u212-linux-x64.tar.gz

（3）apache-ant-1.9.9-bin.tar.gz（build工具，打包用的）

（4）apache-maven-3.0.5-bin.tar.gz

（5）protobuf-2.5.0.tar.gz（序列化的框架）

### 2. Jar包安装

注意：所有操作必须在root用户下完成

#### 1. JDK解压、配置环境变量 JAVA_HOME和PATH，验证java-version(如下都需要验证是否配置成功)

```shell
[root@hadoop101 software] # tar -zxf jdk-8u212-linux-x64.tar.gz -C /opt/module/

[root@hadoop101 software]# vi /etc/profile
#JAVA_HOME：
export JAVA_HOME=/opt/module/jdk1.8.0_212
export PATH=$PATH:$JAVA_HOME/bin

[root@hadoop101 software]#source /etc/profile
```

**验证命令：java -version**

#### 2. Maven解压、配置  MAVEN_HOME和PATH

```shell
[root@hadoop101 software]# tar -zxvf apache-maven-3.0.5-bin.tar.gz -C /opt/module/

[root@hadoop101 apache-maven-3.0.5]# vi conf/settings.xml

<mirrors>
    <!-- mirror
     | Specifies a repository mirror site to use instead of a given repository. The repository that
     | this mirror serves has an ID that matches the mirrorOf element of this mirror. IDs are used
     | for inheritance and direct lookup purposes, and must be unique across the set of mirrors.
     |
<mirror>
       <id>mirrorId</id>
       <mirrorOf>repositoryId</mirrorOf>
       <name>Human Readable Name for this Mirror.</name>
       <url>http://my.repository.com/repo/path</url>
      </mirror>
     -->
        <mirror>
                <id>nexus-aliyun</id>
               <mirrorOf>central</mirrorOf>
                <name>Nexus aliyun</name>
                <url>http://maven.aliyun.com/nexus/content/groups/public</url>
        </mirror>
</mirrors>

[root@hadoop101 apache-maven-3.0.5]# vi /etc/profile
#MAVEN_HOME
export MAVEN_HOME=/opt/module/apache-maven-3.0.5
export PATH=$PATH:$MAVEN_HOME/bin

[root@hadoop101 software]#source /etc/profile

```

**验证命令：mvn -version**

#### 3. ant解压、配置  ANT _HOME和PATH

```shell
[root@hadoop101 software]# tar -zxvf apache-ant-1.9.9-bin.tar.gz -C /opt/module/

[root@hadoop101 apache-ant-1.9.9]# vi /etc/profile
#ANT_HOME
export ANT_HOME=/opt/module/apache-ant-1.9.9
export PATH=$PATH:$ANT_HOME/bin

[root@hadoop101 software]#source /etc/profile

```

**验证命令：ant -version**

#### 4. 安装  glibc-headers 和  g++  命令如下

```shell
[root@hadoop101 apache-ant-1.9.9]# yum install glibc-headers
[root@hadoop101 apache-ant-1.9.9]# yum install gcc-c++
```

#### 5. 安装make和cmake

```shell
[root@hadoop101 apache-ant-1.9.9]# yum install make
[root@hadoop101 apache-ant-1.9.9]# yum install cmake
```

#### 6. 解压protobuf ，进入到解压后protobuf主目录，/opt/module/protobuf-2.5.0，然后相继执行命令

```shell
[root@hadoop101 software]# tar -zxvf protobuf-2.5.0.tar.gz -C /opt/module/
[root@hadoop101 opt]# cd /opt/module/protobuf-2.5.0/

[root@hadoop101 protobuf-2.5.0]#./configure 
[root@hadoop101 protobuf-2.5.0]# make 
[root@hadoop101 protobuf-2.5.0]# make check 
[root@hadoop101 protobuf-2.5.0]# make install 
[root@hadoop101 protobuf-2.5.0]# ldconfig 

[root@hadoop101 hadoop-dist]# vi /etc/profile
#LD_LIBRARY_PATH
export LD_LIBRARY_PATH=/opt/module/protobuf-2.5.0
export PATH=$PATH:$LD_LIBRARY_PATH

[root@hadoop101 software]#source /etc/profile

```

**验证命令：protoc --version**

#### 7. 安装openssl库

```shell
[root@hadoop101 software]#yum install openssl-devel
```

#### 8. 安装 ncurses-devel库

```shell
[root@hadoop101 software]#yum install ncurses-devel
```

### 3. 编译源码

#### 1. 解压源码到/opt/目录

```shell
[root@hadoop101 software]# tar -zxvf hadoop-3.1.3-src.tar.gz -C /opt/
```

#### 2. 进入到hadoop源码主目录

```shell
[root@hadoop101 hadoop-3.1.3-src]# pwd  /opt/hadoop-3.1.3-src
```

#### 3. 通过maven执行编译命令

```shell
[root@hadoop101 hadoop-3.1.3-src]#mvn package -Pdist,native -DskipTests -Dtar
```

等待时间30分钟左右，最终成功是全部SUCCESS。

![image-20200613015544672](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200613015544.png)

#### 4. 成功的64位hadoop包在/opt/hadoop-3.1.3-src/hadoop-dist/target下

```shell
[root@hadoop101 target]# pwd /opt/hadoop-3.1.3-src/hadoop-dist/target
```

#### 5. 编译源码过程中常见的问题及解决方案

（1）MAVEN install时候JVM内存溢出

处理方式：在环境配置文件和maven的执行文件均可调整MAVEN_OPT的heap大小。（详情查阅MAVEN 编译 JVM调优问题，如：http://outofmemory.cn/code-snippet/12652/maven-outofmemoryerror-method）

（2）编译期间maven报错。可能网络阻塞问题导致依赖库下载不完整导致，多次执行命令（一次通过比较难）：

[root@hadoop101 hadoop-3.1.3-src]#mvn package -Pdist,nativeN -DskipTests -Dtar

（3）报ant、protobuf等错误，插件下载未完整或者插件版本问题，最开始链接有较多特殊情况，同时推荐

2.7.0版本的问题汇总帖子 http://www.tuicool.com/articles/IBn63qf

## 6. 常见错误及解决方案

1）防火墙没关闭、或者没有启动YARN

*INFO client.RMProxy: Connecting to ResourceManager at hadoop108/192.168.10.108:8032*

2）主机名称配置错误

3）IP地址配置错误

4）ssh没有配置好

5）root用户和atguigu两个用户启动集群不统一

6）配置文件修改不细心

7）未编译源码

*Unable to load native-hadoop library for your platform... using builtin-java classes where applicable17/05/22 15:38:58 INFO client.RMProxy: Connecting to ResourceManager at hadoop108/192.168.10.108:8032*

8）不识别主机名称

java.net.UnknownHostException: hadoop102: hadoop102

​    at java.net.InetAddress.getLocalHost(InetAddress.java:1475)

​    at org.apache.hadoop.mapreduce.JobSubmitter.submitJobInternal(JobSubmitter.java:146)

​    at org.apache.hadoop.mapreduce.Job$10.run(Job.java:1290)

​    at org.apache.hadoop.mapreduce.Job$10.run(Job.java:1287)

​    at java.security.AccessController.doPrivileged(Native Method)

at javax.security.auth.Subject.doAs(Subject.java:415)

解决办法：

（1）在/etc/hosts文件中添加192.168.1.102 hadoop102

​    （2）主机名称不要起hadoop hadoop000等特殊名称

9）DataNode和NameNode进程同时只能工作一个。

![image-20200613015744219](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200613015744.png)

10）执行命令不生效，粘贴word中命令时，遇到-和长–没区分开。导致命令失效

解决办法：尽量不要粘贴word中代码。

11）jps发现进程已经没有，但是重新启动集群，提示进程已经开启。原因是在linux的根目录下/tmp目录中存在启动的进程临时文件，将集群相关进程删除掉，再重新启动集群。

12）jps不生效。

原因：全局变量hadoop java没有生效。解决办法：需要source /etc/profile文件。

13）8088端口连接不上

[atguigu@hadoop102 桌面]$ cat /etc/hosts

注释掉如下代码

\#127.0.0.1  localhost localhost.localdomain localhost4 localhost4.localdomain4

\#::1     hadoop102

# 二、HDFS

## 1. HDFS概述

### 1. 产生背景

问题：数据量大，一个操作系统存不下所有的数据。分配到更多系统的磁盘中，不方便管理和维护。

分布式文件管理系统：一种系统来管理多台机器上的文件

HDFS：分布式文件管理系统中的一种。

Hadoop Distributed File System

- 文件系统：存储文件，目录树定位
- 分布式：多台机器

使用场景：

- 一次写入，多次读出
- 不支持文件修改，不适合当网盘
- 数据分析

### 2. HDFS优缺点

#### 优点

##### 1. 高容错性

1. 数据自动保存多个副本（默认三个）
2. 某一个副本丢失后，可以自动恢复

##### 2. 适合处理大数据

1. 数据规模：处理数据规模可达GB、TB、PB
2. 文件规模：处理百万规模以上的文件数量

##### 3. 可构建在廉价机器

#### 缺点

##### 1. 不适合低延时数据访问

毫秒级存储数据（不适合实时，主要负责离线）

##### 2. 无法高效对大量的小文件进行存储

1. 占用NameNode大量的内存存储文件目录和块信息
2. 寻址时间 > 读取时间，违反HDFS的设计目标

##### 3. 不支持并发写入、文件随机修改

1. 只能有一个写，不允许多线程写
2. 仅支持数据append（追加），不支持文件的随机修改

### 3. HDFS组成架构

![image-20200613102901609](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200613102901.png)

- Client：客户端，可进行读写操作，都要通过NameNode
- NameNode：存储元数据，告诉DataNodes在哪个位置
- 副本（Replication）：两个相同的块
- 机架（Rack）：存放一台台节点，节点中是一块（Blocks）一块的
- 完整的文件：多块合在一起
- 写（write）：先告诉NameNode，分配一个位置，去那个位置写
- 读（Read）：发出请求，Namenode识别（内部），读取内容

##### 1. NameNode（nn）

就是Master，它是一个主管、管理者

- 管理HDFS的名称空间
- 配置副本策略：如文件file有300m，切分为100M(1) 200M(2)，如下放置，任一节点挂掉仍可正常读取file
  - 机器A:(1)
  - 机器B:(1)(2)
  - 机器C:(2)
- 管理数据块（Block）映射信息
- 处理客户端读写请求

##### 2. DateNode（dn）

就是Slave。NameNode下达命令，DataNode执行实际的操作

- 存储实际的数据块
- 执行数据块的读/写操作

##### 3. Client

客户端

- 文件切分。文件上传HDFS的时候，Client将文件切分成一个一个的Block，然后进行上传
- 与NameNode交互，获取文件的位置信息
- 与DataNode交互，读取或者写入数据
- Client提供一些命令来管理HDFS，比如NameNode格式化
- Client可以通过一些命令来访问HDFS，比如对HDFS增删改查操作

##### 4. Secondary NameNode

并非Namenode的热备。当Namenode挂掉时，它并不能马上替换Namenode并提供服务

- 辅助NameNode，分担其工作量
  - 定期合并Fsimage和Edits，并推送给Namenode
- 紧急情况下，可辅助恢复Namenode

### 4. HDFS文件块大小（面试重点）

物理上分块存储，默认大小128M(Hadoop2.x版本及其之后的版本)，老版本64M

配置参数：dfs.blocksize

**为什么定为128M呢？**

![image-20200613104822075](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200613104822.png)

**为什么块的大小不能设置太小，也不能设置为太大？**

![image-20200613104914381](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200613104914.png)

## 2. HDFS的Shell操作（开发重点）

### 1. 基本语法

对于操作HDFS来说，以下两个命令完全相同

```shell
$ bin/hadoop fs 
$ bin/hdfs dfs

#已设置hadoop环境变量
$ hadoop fs 
$ hdfs dfs
```



### 2. 命令大全

```shell
[atguigu@hadoop102 ~]$ hadoop fs
Usage: hadoop fs [generic options]
	[-appendToFile <localsrc> ... <dst>]
	[-cat [-ignoreCrc] <src> ...]
	[-checksum <src> ...]
	[-chgrp [-R] GROUP PATH...]
	[-chmod [-R] <MODE[,MODE]... | OCTALMODE> PATH...]
	[-chown [-R] [OWNER][:[GROUP]] PATH...]
	[-copyFromLocal [-f] [-p] [-l] [-d] [-t <thread count>] <localsrc> ... <dst>]
	[-copyToLocal [-f] [-p] [-ignoreCrc] [-crc] <src> ... <localdst>]
	[-count [-q] [-h] [-v] [-t [<storage type>]] [-u] [-x] [-e] <path> ...]
	[-cp [-f] [-p | -p[topax]] [-d] <src> ... <dst>]
	[-createSnapshot <snapshotDir> [<snapshotName>]]
	[-deleteSnapshot <snapshotDir> <snapshotName>]
	[-df [-h] [<path> ...]]
	[-du [-s] [-h] [-v] [-x] <path> ...]
	[-expunge]
	[-find <path> ... <expression> ...]
	[-get [-f] [-p] [-ignoreCrc] [-crc] <src> ... <localdst>]
	[-getfacl [-R] <path>]
	[-getfattr [-R] {-n name | -d} [-e en] <path>]
	[-getmerge [-nl] [-skip-empty-file] <src> <localdst>]
	[-head <file>]
	[-help [cmd ...]]
	[-ls [-C] [-d] [-h] [-q] [-R] [-t] [-S] [-r] [-u] [-e] [<path> ...]]
	[-mkdir [-p] <path> ...]
	[-moveFromLocal <localsrc> ... <dst>]
	[-moveToLocal <src> <localdst>]
	[-mv <src> ... <dst>]
	[-put [-f] [-p] [-l] [-d] <localsrc> ... <dst>]
	[-renameSnapshot <snapshotDir> <oldName> <newName>]
	[-rm [-f] [-r|-R] [-skipTrash] [-safely] <src> ...]
	[-rmdir [--ignore-fail-on-non-empty] <dir> ...]
	[-setfacl [-R] [{-b|-k} {-m|-x <acl_spec>} <path>]|[--set <acl_spec> <path>]]
	[-setfattr {-n name [-v value] | -x name} <path>]
	[-setrep [-R] [-w] <rep> <path> ...]
	[-stat [format] <path> ...]
	[-tail [-f] [-s <sleep interval>] <file>]
	[-test -[defsz] <path>]
	[-text [-ignoreCrc] <src> ...]
	[-touch [-a] [-m] [-t TIMESTAMP ] [-c] <path> ...]
	[-touchz <path> ...]
	[-truncate [-w] <length> <path> ...]
	[-usage [cmd ...]]

Generic options supported are:
-conf <configuration file>        specify an application configuration file
-D <property=value>               define a value for a given property
-fs <file:///|hdfs://namenode:port> specify default filesystem URL to use, overrides 'fs.defaultFS' property from configurations.
-jt <local|resourcemanager:port>  specify a ResourceManager
-files <file1,...>                specify a comma-separated list of files to be copied to the map reduce cluster
-libjars <jar1,...>               specify a comma-separated list of jar files to be included in the classpath
-archives <archive1,...>          specify a comma-separated list of archives to be unarchived on the compute machines

The general command line syntax is:
command [genericOptions] [commandOptions]
```

-help：输出这个命令参数

```shell
#查看rm命令的说明
[atguigu@hadoop102 ~]$ hadoop fs -help rm
-rm [-f] [-r|-R] [-skipTrash] [-safely] <src> ... :
  Delete all files that match the specified file pattern. Equivalent to the Unix
  command "rm <src>"
                                                                                 
  -f          If the file does not exist, do not display a diagnostic message or 
              modify the exit status to reflect an error.                        
  -[rR]       Recursively deletes directories.                                   
  -skipTrash  option bypasses trash, if enabled, and immediately deletes <src>.  
  -safely     option requires safety confirmation, if enabled, requires          
              confirmation before deleting large directory with more than        
              <hadoop.shell.delete.limit.num.files> files. Delay is expected when
              walking over large directory recursively to count the number of    
              files to be deleted before the confirmation. 
```

### 3. 常用命令实操

```shell
# 查看hdfs中文件列表
[atguigu@hadoop102 ~]$ hadoop fs -ls /
```

#### 1. 上传

```shell
# -moveFromLocal：从本地剪切粘贴到HDFS
$ touch kongming.txt
$ hadoop fs  -moveFromLocal  ./kongming.txt  /sanguo/shuguo

# -copyFromLocal：从本地文件系统中拷贝文件到HDFS路径去
$ hadoop fs -copyFromLocal README.txt /

# -appendToFile：追加一个文件到已经存在的文件末尾
# 添加内容
$ touch liubei.txt
$ vi liubei.txt
# 追加 注意第一个是本地路径 第二个是hdfs文件的路径（只能本地->hdfs）
$ hadoop fs -appendToFile liubei.txt /sanguo/shuguo/kongming.txt

#-put：等同于copyFromLocal
# 区别：copyFromLocal可以指定线程 -t（一般不用）
$ hadoop fs -put ./zaiyiqi.txt /user/atguigu/test/
```

#### 2. 下载

```shell
# -copyToLocal：从HDFS拷贝到本地
$ hadoop fs -copyToLocal /sanguo/shuguo/kongming.txt ./

# -get：等同于copyToLocal（没区别），就是从HDFS下载文件到本地
$ hadoop fs -get /sanguo/shuguo/kongming.txt ./

# -getmerge：合并下载多个文件，比如HDFS的目录 /user/atguigu/test下有多个文件:log.1, log.2,log.3,...
$ hadoop fs -getmerge /user/atguigu/test/ ./zaiyiqi.txt
#以下写法等同
$ hadoop fs -getmerge /user/atguigu/test/* ./zaiyiqi.txt
```

#### 3. HDFS直接操作

```shell
# 1）-ls: 显示目录信息
$ hadoop fs -ls /

# 2）-mkdir：在HDFS上创建目录
$ hadoop fs -mkdir -p /sanguo/shuguo

# 3）-cat：显示文件内容
$ hadoop fs -cat /sanguo/shuguo/kongming.txt

# 4）-chgrp 、-chmod、-chown：Linux文件系统中的用法一样，修改文件所属权限
$ hadoop fs  -chmod  666  /sanguo/shuguo/kongming.txt
$ hadoop fs  -chown  atguigu:atguigu   /sanguo/shuguo/kongming.txt

# 5）-cp ：从HDFS的一个路径拷贝到HDFS的另一个路径
$ hadoop fs -cp /sanguo/shuguo/kongming.txt /zhuge.txt

# 6）-mv：在HDFS目录中移动文件
$ hadoop fs -mv /zhuge.txt /sanguo/shuguo/

# 7）-tail：显示一个文件的末尾
$ hadoop fs -tail /sanguo/shuguo/kongming.txt

# 8）-rm：删除文件或文件夹
$ hadoop fs -rm /user/atguigu/test/jinlian2.txt

# 9）-rmdir：删除空目录
# 创建一个空目录
$ hadoop fs -mkdir /test
# 删除
$ hadoop fs -rmdir /test

# 10）-du统计文件夹的大小信息
# 统计目录总大小
$ hadoop fs -du -s -h /user/atguigu/aa
37 111 /aa
#第一列标示该目录下总文件大小
#第二列标示该目录下所有文件在集群上的总存储大小和副本数相关，副本数是3 ，所以第二列的是第一列的三倍 （第二列内容=文件大小*副本数）
#第三列标示查询的目录

# 和不加参数一样
$ hadoop fs -du  -h /user/atguigu/aa
37 111 sanguo.txt

# 11）-setrep：设置HDFS中文件的副本数量  
$ hadoop fs -setrep 10 /user/atguigu/sanguo.txt
# 3<=真实的副本数<=设备数
# 不管真实几个副本，第二列按照设置的副本数计算 
$ hadoop fs -du  -h /user/atguigu/aa
37 370 sanguo.txt
```

![image-20200613111908256](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200613111908.png)

这里设置的副本数只是记录在NameNode的元数据中，**是否真的会有这么多副本，还得看DataNode的数量**。因为目前只有3台设备，最多也就3个副本，只有节点数的增加到10台时，副本数才能达到10。

## 3. HDFS客户端操作（开发重点）

### 1. 准备工作

#### 1. windows上hadoop依赖配置

将`05_尚硅谷大数据技术之hadoop\2.资料\01_jar包\03_Windows依赖\hadoop-3.0.0`放到一个目录（`D:\developer_tools\hadoop-3.0.0`)并配置环境变量，配置方法与JDK相同

![image-20200613131238557](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200613131238.png)

注意：

1. 测试`winutils`如果不行，重启动电脑
2. 有些电脑可能会有些问题（比如无法在代码中上传或者下载）需要将：`hadoop.dll` 和 `winutils.exe`放到`c:\windows\system32`目录下就好了

![image-20200613131419425](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200613131419.png)

这样就是配置成功了

#### 2. idea中创建一个空工程，然后创建一个Maven的module并添加依赖

```xml
    <!--HDFS的依赖-->
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-slf4j-impl</artifactId>
            <version>2.12.0</version>
        </dependency>
        <dependency>
            <groupId>org.apache.hadoop</groupId>
            <artifactId>hadoop-client</artifactId>
            <version>3.1.3</version>
        </dependency>
    </dependencies>
```

   注意 ：IDEA中maven的配置（如果需要下载依赖的jar包）

![image-20200613131617623](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200613131617.png)

记录一下maven的配置（以免下次不小心更改了忘了原路径）

注意 ：IDEA中maven的配置（如果需要下载依赖的jar包）

![image-20200613131642452](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200613131642.png)

`settings.xml`的信息（只写了需要更改的部分），如果idea中显示`settings.xml`有问题，那试着按下面的信息更改即可

```xml
<?xml version="1.0" encoding="UTF-8"?>

<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">

<localRepository>D:\developer_tools\apache-maven-3.5.4\repo</localRepository>

  <mirrors>	 
  <mirror>

	<id>alimaven</id>

	<name>aliyun maven</name>

	<url>http://maven.aliyun.com/nexus/content/groups/public/</url>

	<mirrorOf>central</mirrorOf>

  </mirror>
  </mirrors>

</settings>
```

#### 3. 在项目的`src/main/resources`目录下，新建一个文件，命名为`log4j2.xml`，在文件中填入

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="error" strict="true" name="XMLConfig">
    <Appenders>
        <!-- 类型名为Console，名称为必须属性 -->
        <Appender type="Console" name="STDOUT">
            <!-- 布局为PatternLayout的方式，
            输出样式为[INFO] [2018-01-22 17:34:01][org.test.Console]I'm here -->
            <Layout type="PatternLayout"
                    pattern="[%p] [%d{yyyy-MM-dd HH:mm:ss}][%c{10}]%m%n" />
        </Appender>

    </Appenders>

    <Loggers>
        <!-- 可加性为false -->
        <Logger name="test" level="info" additivity="false">
            <AppenderRef ref="STDOUT" />
        </Logger>

        <!-- root loggerConfig设置 -->
        <Root level="info">
            <AppenderRef ref="STDOUT" />
        </Root>
    </Loggers>
</Configuration>
```

### 2. HDFS的API操作

测试框架

```java
package com.atguigu.java;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.net.URI;

/**
 * @author Mrs.An Xueying
 * 2020/6/13 14:21
 */
public class HDFSTest {
    FileSystem fs;
    Configuration conf;
    /**
     * 在执行任意一个单元测试前执行该方法
     * @throws IOException
     * @throws InterruptedException
     */
	@Before
    public void createObject() throws IOException, InterruptedException {
        /**
         * 1. 获取文件系统对象
         * get(
         * final URI uri,  //core-site.xml  HDFS中NameNode的地址  集群操作路径  hdfs://hadoop102:9820
         * final Configuration conf, //配置文件的对象 可以不配置，用默认 该配置只是针对本次操作有效，而不是改变集群配置
         * final String user //操作集群的用户 atguigu
         * )
         */
        conf = new Configuration();
        //副本数 从hdfs-default中找参数和值
        conf.set("dfs.replication", "1");
        fs = FileSystem.get(URI.create("hdfs://hadoop102:9820"), conf, "atguigu");
    }

    /**
     * 在执行任意一个单元测试后再执行该方法
     */
    @After
    public void close(){
        /**
         * 3. 关资源
         */
        if(fs!=null){
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

#### 1. 上传

配置hdfs-site.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>

<configuration>
    <property>
        <name>dfs.replication</name>
        <value>2</value>
    </property>
</configuration>
```

```java
    /**
     * 创建文件系统的对象
     */
    @Test
    public void test() throws IOException {

        /**
         * 2. 具体操作
         * 复制文件
         * boolean delSrc : 是否删除原文件
         * boolean overwrite : 如果目标地址已经存在和上传对象同名文件是否覆盖，true则覆盖，false则抛异常
         * Path src : 原文件  (本地）
         * Path dst : 目标地址 （HDFS)
         */
        fs.copyFromLocalFile(false,false,new Path("D:\\developer_tools\\200421JavaSE\\Items.txt"),new Path("/"));
    }
```

![image-20200613144121756](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200613144121.png)



![image-20200613144345849](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200613144345.png)

配置文件的生效顺序（优先级：由高到低）：

- conf：客户端代码中设置的值
- resource中的hdfs-site：工程下的配置文件
- hdfs-site：集群中的配置文件
- hdfs-default：集群中的默认配置文件

#### 2. 下载

```java
    /**
     * 下载文件
     */
    @Test
    public void download() throws IOException {
        /**
         * boolean delSrc, 是否删除源文件
         * Path src, 源文件
         * Path dst, 目标地址
         * boolean useRawLocalFileSystem  是否使用crc校验
         */
        fs.copyToLocalFile(false,new Path("/Items.txt"),new Path("D:\\developer_tools\\HadoopTest\\HDFS\\Items.txt"),true);
    }

```

#### 3. 删除

```java
    /**
     * 删除文件夹
     */
    @Test
    public void delete() throws IOException {
        /**
         * Path f, 路径
         * boolean recursive 目录必须true 否则抛异常 ；文件无所谓都可
         */
        boolean delete = fs.delete(new Path("/test"), true);
        System.out.println("是否成功删除："+delete);
    }
```

#### 4. 移动/改名

```java
   /**
     * 改名
     */
    @Test
    public void rename() throws IOException {
        /**
         * path src 源文件
         * path dst 目标文件（修改名后的文件）或地址
         */
        fs.rename(new Path("/Items.txt"),new Path("/MyQuestions.txt"));
    }

    /**
     * 移动文件
     */
    @Test
    public void remove() throws IOException {
        /**
         * path src 源文件
         * path dst 目标文件（修改名后的文件）或地址
         */
        fs.rename(new Path("/MyQuestions.txt"),new Path("/sanguo/"));
    }

```



#### 5. 文件详情查看

```java
/**
     * 查看文件详情
     */
    @Test
    public void listFile() throws IOException {
        /**
         * 获取迭代器
         * pathString  文件或目录
         * recursive 是否递归
         */
        RemoteIterator<LocatedFileStatus> localFile = fs.listFiles(new Path("/sanguo/"), true);
        //获取具体的数据
        while (localFile.hasNext()){

            //获取一个数据
            LocatedFileStatus file = localFile.next();
            //所有者
            String owner = file.getOwner();
            //所属组
            String group = file.getGroup();
            //副本数
            short replication = file.getReplication();
            //文件名
            String name = file.getPath().getName();
            //获取块信息
            BlockLocation[] blockLocations = file.getBlockLocations();
            for (BlockLocation block : blockLocations) {
                //输出块信息
                String[] blockHosts = block.getHosts();
                for (String blockHost : blockHosts) {
                    System.out.println(blockHost);
                }
            }
            System.out.println("======="+name+"========");
        }
    }
```



#### 6. 文件/文件夹 判断

```java
    /**
     * 判断是文件还是文件夹
     */
    @Test
    public void ifDirs() throws IOException {
        FileStatus[] fileStatuses = fs.listStatus(new Path("/sanguo/"));
        for (FileStatus file : fileStatuses) {
            String name = file.getPath().getName();
            boolean b1 = file.isFile();
            System.out.println(name+"是否为文件："+b1);
            boolean b2 = file.isDirectory();
            System.out.println(name+"是否为目录："+b2);
        }
    }

```

#### 7. 上传、下载的本质就是IO流

```java
    /**
     * 上传和下载的本质就是IO流
     */
    @Test
    public void ioTest() throws IOException {
        //输入流：从本地读文件
        FileInputStream fis = new FileInputStream("D:\\developer_tools\\200421JavaSE\\Items.txt");
        //输出流：上传至HDFS
        FSDataOutputStream os = fs.create(new Path("/Items.txt"));
        //一边读一边写:文件对拷
        IOUtils.copyBytes(fis, os, conf);
        //关流
        IOUtils.closeStream(os);
        IOUtils.closeStream(fis);
    }
```



## 4. HDFS的数据流（多理解）

### 1. HDFS写数据流程

#### 1. 文件写入

（1）客户端通过Distributed FileSystem模块向NameNode请求上传文件，NameNode检查目标文件是否已存在，父目录是否存在。

（2）NameNode返回是否可以上传。

（3）客户端请求第一个 Block上传到哪几个DataNode服务器上。

（4）NameNode返回3个DataNode节点，分别为dn1、dn2、dn3。

（5）客户端通过FSDataOutputStream模块请求dn1上传数据，dn1收到请求会继续调用dn2，然后dn2调用dn3，将这个通信管道建立完成。

（6）dn1、dn2、dn3逐级应答客户端。

（7）客户端开始往dn1上传第一个Block（先从磁盘读取数据放到一个本地内存缓存），以Packet为单位，dn1收到一个Packet就会传给dn2，dn2传给dn3；dn1每传一个packet会放入一个应答队列等待应答。

（8）当一个Block传输完成之后，客户端再次请求NameNode上传第二个Block的服务器。（重复执行3-7步）。

![image-20200613164445843](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200613164445.png)

- 连接问题：5 请求建立通道 DateNode1--成功，DateNode2--失败 
  - 客户端重新建立通道DateNode1--成功，DateNode3--成功
  - 此时DateNode2没有数据
  - DateNode会定时向NameNode汇报块信息，NameNode发现少一个副本，会找一个DateNode，建立一个副本补全
- 传输问题：7传输数据  DateNode1--成功，DateNode2--失败 
  - 处理方式与连接问题一样，会重新建立通道，然后向成功的DateNode传输

#### 2. 网络拓扑-节点距离计算

上面提到了FSDataOutputStream会找距离最近的DateNode，那什么叫距离最近呢？

**节点距离：两个节点到达最近的共同祖先的距离总和。**

![image-20200613170038034](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200613170038.png)

#### 3. 机架感知（副本存储节点选择）

[3.1.3官方文档](http://hadoop.apache.org/docs/r2.7.2/hadoop-project-dist/hadoop-hdfs/HdfsDesign.html#Data_Replication)

常见的情况,当需要3个副本时

- 第一个副本：
  - 如果写的机器本身在一个DateNode上，HDFS会将一个副本在本地机器（写的机器）上，
  - 否则会随机找一个Datenode。
- 第二个副本
  - 在另一个机架的随机一个节点
- 第三个副本
  - 在第二个副本所在机架的随机节点

![image-20200615091248039](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200615091248.png)

### 2. HDFS读数据流程

![image-20200615091916613](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200615091916.png)

（1）客户端通过Distributed FileSystem向NameNode请求下载文件，NameNode通过查询元数据，找到文件块所在的DataNode地址。

（2）挑选一台DataNode（就近原则，然后随机）服务器，请求读取数据。

（3）DataNode开始传输数据给客户端（从磁盘里面读取数据输入流，以Packet为单位来做校验）。

（4）客户端以Packet为单位接收，先在本地缓存，然后写入目标文件。

## 5. NameNode和SecondaryNameNode（面试开发重点）

用哪个用户启动的集群，那该用户就是超级管理员。

```
#实际的存储路径
/opt/module/hadoop-3.1.3/data/dfs/data/current/BP-2023207197-192.168.1.102-1591944079816/current/finalized/subdir0/subdir0
```

### 1. NN和2NN工作机制

![image-20200615103254586](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200615103254.png)

- 滚动日志edits：历史记录都有，2NN中少了最新的（如图中290）
- 元数据镜像文件fsimage：只有两份
  - 一个是旧的（下一次会被覆盖）
  - 一个是新的（下一次就是旧的）

![image-20200615211115739](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200615211115.png)

**NameNode中的元数据是存储在哪里的？**

首先，我们做个假设，如果存储在NameNode节点的磁盘中，因为经常需要进行随机访问，还有响应客户请求，必然是效率过低。因此，元数据需要存放在内存中。但如果只存在内存中，一旦断电，元数据丢失，整个集群就无法工作了。**因此产生在磁盘中备份元数据的FsImage**--只记录操作结果。

这样又会带来新的问题，当在内存中的元数据更新时，如果同时更新FsImage，就会导致效率过低，且容易丢失数据。但如果不更新，就会发生一致性问题，一旦NameNode节点断电，就会产生数据丢失。

(搞buffer的问题：1. 占用原本就吃紧的内存 2. 如果突然断电仍会造成部分内容丢失 3. 在达到buffer阈值写入磁盘时，namenode无法工作)

**因此，引入Edits文件--只记录操作(只进行追加操作，效率很高)。每当元数据有更新或者添加元数据时，修改内存中的元数据并追加到Edits中。**这样，一旦NameNode节点断电，可以通过FsImage和Edits的合并，合成元数据。

但是，如果长时间添加数据到Edits中，会导致该文件数据过大，效率降低，而且一旦断电，恢复元数据需要的时间过长。因此，需要定期进行FsImage和Edits的合并，如果这个操作由NameNode节点完成，又会效率过低。因此，引入一个新的节点SecondaryNamenode，专门用于FsImage和Edits的合并。

1）第一阶段：NameNode启动

（1）第一次启动NameNode格式化后，创建Fsimage和Edits文件。如果不是第一次启动，直接加载编辑日志和镜像文件到内存。

（2）客户端对元数据进行增删改的请求。

（3）NameNode记录操作日志，更新滚动日志。

（4）NameNode在内存中对元数据进行增删改。

2）第二阶段：Secondary NameNode工作

（1）Secondary NameNode询问NameNode是否需要CheckPoint。直接带回NameNode是否检查结果。

（2）Secondary NameNode请求执行CheckPoint。

（3）NameNode滚动正在写的Edits日志。

（4）将滚动前的编辑日志和镜像文件拷贝到Secondary NameNode。

（5）Secondary NameNode加载编辑日志和镜像文件到内存，并合并。

（6）生成新的镜像文件fsimage.chkpoint。

（7）拷贝fsimage.chkpoint到NameNode。

（8）NameNode将fsimage.chkpoint重新命名成fsimage。

### 2. Fsimage和Edits解析

#### 1. NameNode被格式化之后产生的文件解析

目录：`/opt/module/hadoop-3.1.3/data/dfs/name/current`

- Fsimage：HDFS文件系统元数据的一个永久性的检查点，其中包含HDFS文件系统的所有目录和文件iNode的序列化信息
- Edits：存放HDFS文件系统的所有更新操作的路径，文件系统客户端的所有写操作首先会被记录到Edits文件中。
- seen_txid：保存的是一个数字，最后一个edits_的数字
- 每次NameNode启动的时候都会讲Fsimage文件读入内存，加载Edits里面的更新操作，保证内存中的元数据信息是最新的、同步的，可以看成NameNode启动的时候就酱Fsimage和Edits文件进行了合并。

#### 2. 查看Fsimage文件

```
hdfs oiv -p 文件类型 -i镜像文件 -o 转换后文件输出路径
```

```shell
$ hdfs oiv -p XML -i fsimage_0000000000000000286 -o  /home/atguigu/fsimage.xml
```

```xml
<?xml version="1.0"?>
<fsimage><version><layoutVersion>-64</layoutVersion><onDiskVersion>1</onDiskVersion><oivRevision>ba631c436b806728f8ec2f54ab1e289526c90579</oivRevision></version>
<NameSection><namespaceId>159338274</namespaceId><genstampV1>1000</genstampV1><genstampV2>1022</genstampV2><genstampV1Limit>0</genstampV1Limit><lastAllocatedBlockId>1073741845</lastAllocatedBlockId><txid>286</txid></NameSection>
<ErasureCodingSection>
<erasureCodingPolicy>
<policyId>1</policyId><policyName>RS-6-3-1024k</policyName><cellSize>1048576</cellSize><policyState>DISABLED</policyState><ecSchema>
<codecName>rs</codecName><dataUnits>6</dataUnits><parityUnits>3</parityUnits></ecSchema>
</erasureCodingPolicy>

<erasureCodingPolicy>
<policyId>2</policyId><policyName>RS-3-2-1024k</policyName><cellSize>1048576</cellSize><policyState>DISABLED</policyState><ecSchema>
<codecName>rs</codecName><dataUnits>3</dataUnits><parityUnits>2</parityUnits></ecSchema>
</erasureCodingPolicy>

<erasureCodingPolicy>
<policyId>3</policyId><policyName>RS-LEGACY-6-3-1024k</policyName><cellSize>1048576</cellSize><policyState>DISABLED</policyState><ecSchema>
<codecName>rs-legacy</codecName><dataUnits>6</dataUnits><parityUnits>3</parityUnits></ecSchema>
</erasureCodingPolicy>

<erasureCodingPolicy>
<policyId>4</policyId><policyName>XOR-2-1-1024k</policyName><cellSize>1048576</cellSize><policyState>DISABLED</policyState><ecSchema>
<codecName>xor</codecName><dataUnits>2</dataUnits><parityUnits>1</parityUnits></ecSchema>
</erasureCodingPolicy>

<erasureCodingPolicy>
<policyId>5</policyId><policyName>RS-10-4-1024k</policyName><cellSize>1048576</cellSize><policyState>DISABLED</policyState><ecSchema>
<codecName>rs</codecName><dataUnits>10</dataUnits><parityUnits>4</parityUnits></ecSchema>
</erasureCodingPolicy>

</ErasureCodingSection>

<INodeSection><lastInodeId>16442</lastInodeId><numInodes>7</numInodes><inode><id>16385</id><type>DIRECTORY</type><name></name><mtime>1592035833804</mtime><permission>atguigu:supergroup:0755</permission><nsquota>9223372036854775807</nsquota><dsquota>-1</dsquota></inode>
<inode><id>16432</id><type>DIRECTORY</type><name>sanguo</name><mtime>1592033587230</mtime><permission>atguigu:supergroup:0755</permission><nsquota>-1</nsquota><dsquota>-1</dsquota></inode>
<inode><id>16433</id><type>FILE</type><name>readme.txt</name><replication>3</replication><mtime>1592019819419</mtime><atime>1592019664387</atime><preferredBlockSize>134217728</preferredBlockSize><permission>atguigu:supergroup:0644</permission><blocks><block><id>1073741838</id><genstamp>1016</genstamp><numBytes>103</numBytes></block>
</blocks>
<storagePolicyId>0</storagePolicyId></inode>
<inode><id>16434</id><type>FILE</type><name>help.txt</name><replication>3</replication><mtime>1592019740614</mtime><atime>1592019740462</atime><preferredBlockSize>134217728</preferredBlockSize><permission>atguigu:atguigu:0777</permission><blocks><block><id>1073741839</id><genstamp>1015</genstamp><numBytes>39</numBytes></block>
</blocks>
<storagePolicyId>0</storagePolicyId></inode>
<inode><id>16435</id><type>FILE</type><name>append.txt</name><replication>3</replication><mtime>1592019873041</mtime><atime>1592019872912</atime><preferredBlockSize>134217728</preferredBlockSize><permission>atguigu:supergroup:0644</permission><blocks><block><id>1073741840</id><genstamp>1017</genstamp><numBytes>38</numBytes></block>
</blocks>
<storagePolicyId>0</storagePolicyId></inode>
<inode><id>16440</id><type>FILE</type><name>MyQuestions.txt</name><replication>1</replication><mtime>1592030641742</mtime><atime>1592030641661</atime><preferredBlockSize>134217728</preferredBlockSize><permission>atguigu:supergroup:0644</permission><blocks><block><id>1073741844</id><genstamp>1021</genstamp><numBytes>1907</numBytes></block>
</blocks>
<storagePolicyId>0</storagePolicyId></inode>
<inode><id>16442</id><type>FILE</type><name>Items.txt</name><replication>1</replication><mtime>1592035834608</mtime><atime>1592035833804</atime><preferredBlockSize>134217728</preferredBlockSize><permission>atguigu:supergroup:0644</permission><blocks><block><id>1073741845</id><genstamp>1022</genstamp><numBytes>1907</numBytes></block>
</blocks>
<storagePolicyId>0</storagePolicyId></inode>
</INodeSection>
<INodeReferenceSection></INodeReferenceSection><SnapshotSection><snapshotCounter>0</snapshotCounter><numSnapshots>0</numSnapshots></SnapshotSection>
<INodeDirectorySection><directory><parent>16385</parent><child>16442</child><child>16432</child></directory>
<directory><parent>16432</parent><child>16440</child><child>16435</child><child>16434</child><child>16433</child></directory>
</INodeDirectorySection>
<FileUnderConstructionSection></FileUnderConstructionSection>
<SecretManagerSection><currentId>0</currentId><tokenSequenceNumber>0</tokenSequenceNumber><numDelegationKeys>0</numDelegationKeys><numTokens>0</numTokens></SecretManagerSection><CacheManagerSection><nextDirectiveId>1</nextDirectiveId><numDirectives>0</numDirectives><numPools>0</numPools></CacheManagerSection>
</fsimage>
```

##### 为什么Fsimage中没有记录块所对应DataNode

- NN重启后，DN会主动找NN汇报自身情况
- 反正都要汇报，就不记录了，还更效率

#### 3. 查看Edits文件

```
hdfs oev -p 文件类型 -i镜像文件 -o 转换后文件输出路径
```

```shell
$  hdfs oev -p XML -i edits_0000000000000000288-0000000000000000289 -o /home/atguigu/edits.xml
```

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<EDITS>
  <EDITS_VERSION>-64</EDITS_VERSION>
  <RECORD>
    <OPCODE>OP_START_LOG_SEGMENT</OPCODE>
    <DATA>
      <TXID>288</TXID>
    </DATA>
  </RECORD>
  <RECORD>
    <OPCODE>OP_END_LOG_SEGMENT</OPCODE>
    <DATA>
      <TXID>289</TXID>
    </DATA>
  </RECORD>
</EDITS>
```

##### NameNode如何确定下次开机启动的时候合并哪些Edits？

根据edits和fsimage匹配的version：

- EDITS_VERSION
- layoutVersion

### 3. CheckPoint时间设置（了解）

`hdfs-default.xml`

1）通常情况下，SecondaryNameNode每隔一小时执行一次。

```xml
<property>
  <name>dfs.namenode.checkpoint.period</name>
  <value>3600</value>
</property>
```

2）一分钟检查一次操作次数，3当操作次数达到1百万时，SecondaryNameNode执行一次。

```xml
<property>
  <name>dfs.namenode.checkpoint.txns</name>
  <value>1000000</value>
<description>操作动作次数</description>
</property>

<property>
  <name>dfs.namenode.checkpoint.check.period</name>
  <value>60</value>
<description> 1分钟检查一次操作次数</description>
</property >

```

### 4. NameNode故障处理（扩展）

后面不会用2NN，因为它总会丢失一部分。所以此部分是扩展内容

#### 将SecondaryNameNode中数据拷贝到NameNode存储数据的目录(了解即可)

```shell
# kill -9 NameNode进程 jps看进程id
$ hdfs --daemon stop namenode
# 删除NameNode存储的数据--测试是否能正常恢复
$ rm -rf /opt/module/hadoop-3.1.3/data/dfs/name
current/*
# 拷贝SecondaryNameNode中数据到原NameNode存储数据目录
$ scp -r  atguigu@hadoop104:/opt/module/hadoop-3.1.3/data/dfs/namesecondary/current/*  /opt/module/hadoop-3.1.3/data/dfs/name/current/

# 重新启动NameNode
$ hdfs --daemon start namenode
```

### 5. 集群安全模式

安全模式：只能查看

什么时候会进：

1. 资源不足时，不接受写操作
2. hdfs启动时，也会进入到安全模式（除了初始化后第一次启动，此时没有块信息，不会进入）
   1. 先读2NN内容，恢复
   2. 接收DN的汇报内容
3. 自我保护状态（实际上说的也是1、2）

什么时候会退出：满足**最小副本条件**之后30s

![image-20200615215435779](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200615215435.png)

![image-20200615114340309](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200615114340.png)

1）基本语法

集群处于安全模式，不能执行重要操作（写操作）。集群启动完成后，自动退出安全模式。

```shell
#（功能描述：查看安全模式状态）
$ bin/hdfs dfsadmin -safemode get  
 #（功能描述：进入安全模式状态）
$ bin/hdfs dfsadmin -safemode enter 
 #（功能描述：离开安全模式状态）
$ bin/hdfs dfsadmin -safemode leave
 #（功能描述：等待安全模式状态）
$ bin/hdfs dfsadmin -safemode wait 
```

2）案例

 模拟等待安全模式

3）查看当前模式

```shell
[atguigu@hadoop102 hadoop-3.1.3]$ hdfs dfsadmin -safemode get

Safe mode is OFF
```

4）先进入安全模式

```shell
[atguigu@hadoop102 hadoop-3.1.3]$ bin/hdfs dfsadmin -safemode enter
```

5）创建并执行下面的脚本

在/opt/module/hadoop-3.1.3路径上，编辑一个脚本safemode.sh

```shell
[atguigu@hadoop102 hadoop-3.1.3]$ vim safemode.sh

#!/bin/bash
hdfs dfsadmin -safemode wait
#上面一从等待状态退出就执行下面的命令
echo "巴拉巴拉小魔仙已准备就绪！"
```

```
[atguigu@hadoop102 hadoop-3.1.3]$ chmod 777 safemode.sh
[atguigu@hadoop102 hadoop-3.1.3]$ ./safemode.sh 
```

6）再打开一个窗口，执行

```shell
[atguigu@hadoop102 hadoop-3.1.3]$ bin/hdfs dfsadmin -safemode leave
```

7）观察

8）再观察上一个窗口

```shell
Safe mode is OFF
```

![image-20200615221836302](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200615221836.png)

## 6. DataNode（面试开发重点）

### 1. 工作机制

![image-20200615120534431](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200615120534.png)



### 2. 数据完整性

校验准确率由低到高，效率由高到低：

- 奇偶校验：看1的个数是奇数还是偶数
- crc校验（32位）
- md5校验（128位）
- sha1校验（160位）

如下是DataNode节点保证数据完整性的方法。

（1）当DataNode读取Block的时候，它会计算CheckSum。

（2）如果计算后的CheckSum，与Block创建时值不一样，说明Block已经损坏。

（3）Client读取其他DataNode上的Block。

（4）DataNode在其文件创建后周期验证CheckSum。

![image-20200615230503103](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200615230503.png)

### 3. 掉线时限参数设置

需要注意的是`hdfs-site.xml `配置文件中的`heartbeat.recheck.interval`的单位为毫秒，`dfs.heartbeat.interval`的单位为秒。

```xml
<property>
    <name>dfs.namenode.heartbeat.recheck-interval</name>
    <value>300000</value>
</property>
<property>
    <name>dfs.heartbeat.interval</name>
    <value>3</value>
</property>
```

![image-20200615231017940](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200615231018.png)

### 4. 服役新数据节点

#### 1.  服役新节点（如无样板机，需要配置的）：

​	1.配置JDK和Hadoop（配置各种配置文件）
​	2.配置环境变量
​	3.hosts文件（提前已经配置到hadoop110）
​	4.关闭防火墙
​	5.启动DataNode
​	6.创建用户名，创建对应的使用目录
​	7.修改ip地址
​	8.修改hostname

#### 2. 使用样板机创建新节点（前文提到的hadoop100即为样板机）

​	1.配置JDK和Hadoop（配置各种配置文件 - 使用scp拷贝）
​	2.配置环境变量，修改hostname，记着把hadoop中的data和logs删除掉
​	3.启动DataNode
​	4.hadoop102-105必须配置在hosts文件中（前面已经配置过了）。

#### 3. 实操hadoop105

克隆样板机，如下配置后，重启

![image-20200615231533691](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200615231533.png)

```shell
#这些可以在做样板机的时候做好
[atguigu@hadoop102 ~]$ scp -r /opt/module/* atguigu@hadoop105:/opt/module/
[atguigu@hadoop102 ~]$ scp  -r /etc/profile.d/my_env.sh root@hadoop105:/etc/profile.d/
```

```shell
#测试环境已经搭建好
[atguigu@hadoop105 module]$ source /etc/profile.d/my_env.sh 
[atguigu@hadoop105 module]$ hadoop version
Hadoop 3.1.3
Source code repository https://gitbox.apache.org/repos/asf/hadoop.git -r ba631c436b806728f8ec2f54ab1e289526c90579
Compiled by ztang on 2019-09-12T02:47Z
Compiled with protoc 2.5.0
From source with checksum ec785077c385118ac91aadde5ec9799
This command was run using /opt/module/hadoop-3.1.3/share/hadoop/common/hadoop-common-3.1.3.jar
[atguigu@hadoop105 hadoop-3.1.3]$ rm -rf data
[atguigu@hadoop105 hadoop-3.1.3]$ rm -rf logs
```

```shell
#启动节点
[atguigu@hadoop105 hadoop-3.1.3]$ hdfs --daemon start datanode
[atguigu@hadoop105 hadoop-3.1.3]$ jps
1792 DataNode
1864 Jps
```

![image-20200615232725717](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200615232725.png)

### 5. 退役旧数据节点

- 在DN上配置可分发可不分发，因为读取黑白名单的是DN。否则要分发。
- 不允许白名单和黑名单中同时出现同一个主机名称

#### 1. 黑名单退役

在黑名单上面的主机都会被强制退出。所有需要退役的节点必须在黑名单设置，因为退役节点可以把自己存的块转到其他节点上。

只动名单，没动配置文件不需重启NameNode，只需刷新节点即可。

##### 1. 配置黑名单

```shell
[atguigu@hadoop102 ~]$ touch /opt/module/hadoop-3.1.3/etc/hadoop/dfs.hosts.exclude
[atguigu@hadoop102 ~]$ vim /opt/module/hadoop-3.1.3/etc/hadoop/dfs.hosts.exclude 
#黑名单
hadoop105
```

##### 2. 配置文件增加属性

```shell
$ vim /opt/module/hadoop-3.1.3/etc/hadoop/hdfs-site.xml
```

```xml
<property>
<name>dfs.hosts.exclude</name>
      <value>/opt/module/hadoop-3.1.3/etc/hadoop/dfs.hosts.exclude</value>
</property>
```

##### 3. 刷新（只修改了黑名单）| 重启NameNode（修改了hadoop配置文件）

```shell
#重启NameNode
[atguigu@hadoop102 hadoop]$ hdfs --daemon stop namenode
[atguigu@hadoop102 hadoop]$ hdfs --daemon start namenode

#刷新
[atguigu@hadoop102 hadoop]$ hdfs dfsadmin -refreshNodes
Refresh nodes successful
[atguigu@hadoop102 hadoop]$ yarn rmadmin -refreshNodes
2020-06-16 00:18:25,820 INFO client.RMProxy: Connecting to ResourceManager at hadoop103/192.168.1.103:8033
```

![image-20200616002420976](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200616002421.png)

所有块已经复制完成后，节点会从”退役中“变为”已退役“，并在节点列表中显示。

如果副本数是3，服役的节点小于等于3，是不能退役成功的，需要修改副本数后才能退役

##### 4. 此时hadoop105的节点还在运行，需要手动关闭

![image-20200616002638075](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200616002638.png)

```shell
$ hdfs --daemon stop datanode
```

##### 5. 集群再平衡

如数据不均衡 ，则可调用

```shell
$ /opt/module/hadoop-3.1.3/sbin/start-balancer.sh 
```

#### 2. 添加白名单

添加到白名单的主机节点，都允许访问NameNode，不在白名单的主机节点，都会被退出。

没过程，不在白名单的直接隐藏了。不推荐

```shell
$ vim /opt/module/hadoop-3.1.3/etc/hadoop/dfs.hosts
hadoop102
hadoop103
hadoop104

$ vim /opt/module/hadoop-3.1.3/etc/hadoop/hdfs-site.xml
```

```xml
<property>
<name>dfs.hosts</name>
<value>/opt/module/hadoop-3.1.3/etc/hadoop/dfs.hosts</value>
</property>
```

```shell
#分发
$ xsync hdfs-site.xml 
#重启
$ mycluster stop
$ mycluster start
```

![image-20200616004127057](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200616004141.png)

### 6. 多目录设置

比如一个机器，两个磁盘，可以使不同目录对应不同磁盘，使每个目录存储的数据不一样，比如file分成file1和file2存在D、E两个盘中，避免D或E损坏导致file整个丢失。（数据不是副本）

没啥实际意义，hadoop本来就有副本机制。

配置hdfs-site.xml（需要分发）

```xml
<property>
        <name>dfs.datanode.data.dir</name>
<value>file:///${hadoop.tmp.dir}/dfs/data1,file:///${hadoop.tmp.dir}/dfs/data2</value>
</property>
```

# 三、MapReduce

## 1. MapReduce概述

### 1. 定义

- 分布式**运算**程序的编程框架。

- “基于Hadoop的数据分析应用”的核心框架

  将**用户编写的业务逻辑代码**和**自带默认组件**整合成一个完整的**分布式运算程序**

### 2. 优缺点

#### 优点

1. 易于编程：简单的实现一些接口，就可以完成一个分布式程序。与写一个简单的串行程序是一模一样的。
2. 良好的扩展性：简单的增加廉价的PC机器就可扩展它的计算能力
3. 高容错性：其中一台挂了，自动就把任务转移节点，不至于运行失败。
4. 适合PB级以上海量数据的离线处理

#### 缺点

1. 不擅长实时计算：无法ms或s级返回结果。
2. 不擅长流式计算：输入数据集是静态的，由其自身的设计特点决定的。
3. 不擅长DAG（有向图）计算：多个应用程序存在依赖关系，A-->B-->C，A的输出是B的输入，B的输出是C的输入。**MapReduce每次作业的输出结果都要写入磁盘，再由下个程序调用，会造成大量的磁盘IO，导致MapReduce处理此类计算的性能非常低下。**

### 3. 核心思想

![image-20200616010105216](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200616010105.png)

![image-20200615165240148](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200615165240.png)

（1）分布式的运算程序往往需要分成至少2个阶段。

（2）第一个阶段的MapTask并发实例，完全并行运行，互不相干。

（3）第二个阶段的ReduceTask并发实例互不相干，但是他们的数据依赖于上一个阶段的所有MapTask并发实例的输出。

（4）MapReduce编程模型只能包含一个Map阶段和一个Reduce阶段，如果用户的业务逻辑非常复杂，那就只能多个MapReduce程序，串行运行。

总结：分析WordCount数据流走向深入理解MapReduce核心思想。

![image-20200616010323188](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200616010326.png)

### 4. 进程

一个完整的MapReduce程序在分布式运行时有三类实例进程：

（1）**MrAppMaster**：负责**整个程序**的过程调度及状态协调。

（2）**MapTask**：负责**Map阶段**的整个数据处理流程。

（3）**ReduceTask**：负责**Reduce阶段**的整个数据处理流程。

### 5. 官方WordCount源码

三个类

- Map类:继承Mapper
- Reduce类:继承Reducer
- 驱动类：main方法，说明使用Map类,Reduce类

```java
package org.apache.hadoop.examples;

import java.io.IOException;
import java.io.PrintStream;
import java.util.StringTokenizer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class WordCount
{
  public static void main(String[] args)
    throws Exception
  {
    Configuration conf = new Configuration();
    String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
    if (otherArgs.length < 2) {
      System.err.println("Usage: wordcount <in> [<in>...] <out>");
      System.exit(2);
    }
    Job job = Job.getInstance(conf, "word count");
    job.setJarByClass(WordCount.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setCombinerClass(IntSumReducer.class);
    job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    for (int i = 0; i < otherArgs.length - 1; i++) {
      FileInputFormat.addInputPath(job, new Path(otherArgs[i]));
    }
    FileOutputFormat.setOutputPath(job, new Path(otherArgs[(otherArgs.length - 1)]));

    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }

  public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable>
  {
    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context)
      throws IOException, InterruptedException
    {
      int sum = 0;
      for (IntWritable val : values) {
        sum += val.get();
      }
      this.result.set(sum);
      context.write(key, this.result);
    }
  }

  public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable>
  {
    private static final IntWritable one = new IntWritable(1);
    private Text word = new Text();

    public void map(Object key, Text value, Mapper<Object, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException
    {
      StringTokenizer itr = new StringTokenizer(value.toString());
      while (itr.hasMoreTokens()) {
        this.word.set(itr.nextToken());
        context.write(this.word, one);
      }
    }
  }
}
```

### 6. 常用数据序列化类型

| Java类型   | Hadoop Writable类型 |
| ---------- | ------------------- |
| Boolean    | BooleanWritable     |
| Byte       | ByteWritable        |
| Int        | IntWritable         |
| Float      | FloatWritable       |
| Long       | LongWritable        |
| Double     | DoubleWritable      |
| **String** | **Text**            |
| Map        | MapWritable         |
| Array      | ArrayWritable       |

Java类型是Serialzable接口，是传统的序列化。弊端是序列化时会多一些额外数据。所以Hadoop自己搞了一套对应的序列化数据类型。

- String   --->   Text
- 其他都是在后面+Writable

### @@7. WordCount案例实操

## 2. Hadoop序列化

### 1. 序列化概述

#### 1. what

##### 序列化

把内存中的对象，转换成字节序列。

- 网络数据传输：把网易云音乐中的歌分享到微信中。
- 磁盘上对象的传输：map写到本地

##### 反序列化

将收到字节序列或者是磁盘的持久化数据，转换成内存中的对象

- 网络数据传输：微信中听网易云音乐分享过来的歌
- 磁盘上对象的传输：reduce从本地读

#### 2. why

##### 为什么要序列化

序列化可以存储“活的“对象，可以将其发送到远程计算机。（一般只生存在内存中）

##### 为什么不用java的序列化

java的序列化是重量级的序列化框架（Serializable），会有许多额外的信息

- 不便于高效传输
- 占用空间

#### 3. How good

1. 紧凑：高效使用存储空间
2. 快速：读写数据的额外开销小
3. 可扩展：随着通信协议的升级而可升级
4. 互操作：支持多语言的交互

### 2. 自定义bean对象实现序列化接口（writable）

```java
package com.atguigu.writable;

import org.apache.hadoop.io.Writable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author Mrs.An Xueying
 * 2020/6/16 20:09
 *
 * 使用hadoop序列化框架
 * 1. 自定义类并实现wirtable接口
 * 2. 重写wirte和readFields方法
 * 3. 读时数据的顺序必须和写时数据的顺序相同
 */
public class FlowBean implements Writable {
    //上行流量
    private long upFlow;
    //下行流量
    private long downFlow;
    //总流量
    private long sumFlow;
    /**
     * 序列化：写
     * @param out
     * @throws IOException
     */
    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);
    }

    /**
     * 反序列化：读
     * @param in
     * @throws IOException
     */
    public void readFields(DataInput in) throws IOException {
        upFlow = in.readLong();
        downFlow = in.readLong();
        sumFlow = in.readLong();
    }
}

```

### 3. 序列化案例实操

#### 1. 本地运行

在`D:\hdfstest\input`下创建`text.txt`，内容为

```
aa
ab
ac
aa
cc
bb
dd
aa
cc
dd
bb
abc
```

1. 以maven新建module，pom.xml中添加HDFS依赖

   ```xml
       <!--HDFS的依赖-->
       <dependencies>
           <dependency>
               <groupId>junit</groupId>
               <artifactId>junit</artifactId>
               <version>4.12</version>
           </dependency>
           <dependency>
               <groupId>org.apache.logging.log4j</groupId>
               <artifactId>log4j-slf4j-impl</artifactId>
               <version>2.12.0</version>
           </dependency>
           <dependency>
               <groupId>org.apache.hadoop</groupId>
               <artifactId>hadoop-client</artifactId>
               <version>3.1.3</version>
           </dependency>
       </dependencies>
   ```

2. 创建Mapper、Reducer类

   ```java
   package com.atguigu.wordcount;
   
   import org.apache.hadoop.io.IntWritable;
   import org.apache.hadoop.io.LongWritable;
   import org.apache.hadoop.io.Text;
   import org.apache.hadoop.mapreduce.Mapper;
   
   import java.io.IOException;
   
   /**
    * @author Mrs.An Xueying
    * 2020/6/16 9:29
    * 1. 自定义的类需要继承Mapper，Mapper的四个泛型如下：
    * Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT>代表数据类型，是两对，分别为：
    *     ①  输入：KEYIN, VALUEIN
    *          KEYIN：数据的偏移量，一行一行读数据，用来记录数据读到哪里了
    *          VALUEIN：实际读取的具体的一行数据
    *     ②  输出：KEYOUT, VALUEOUT
    *          KEYOUT：单词
    *          VALUEOUT：单词出现的数量（1）
    */
   public class CountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
       /**
        * (KEYOUT, VALUEOUT)
        */
       private Text outkey = new Text();
       private IntWritable outValue = new IntWritable(1);
       /**
        *      * Called once for each key/value pair in the input split. Most applications
        *      * should override this, but the default is the identity function.
        * 该方法用来处理具体的业务逻辑
        * @param key 输入数据的KEYIN，数据的偏移量
        * @param value 输入数据的VALUEIN，实际读取的具体的一行数据
        * @param context 上下文 在这里用来将数据写出去
        * @throws IOException
        * @throws InterruptedException
        */
       @Override
       @SuppressWarnings("unchecked")
       protected void map(LongWritable key, Text value,
                          Context context) throws IOException, InterruptedException {
           //1. 先将读进来的一行数据转换成String便于操作
           String line = value.toString();
           //2. 切割数据（按照空格切）
           String[] words = line.split(" ");
           //3. 遍历所有的单词并进行封装（K,V)
           for (String word : words) {
               //写数据
               outkey.set(word);
               context.write(outkey,outValue);
           }
       }
   
   }
   
   ```

   ```java
   package com.atguigu.wordcount;
   
   import org.apache.hadoop.io.IntWritable;
   import org.apache.hadoop.io.Text;
   import org.apache.hadoop.mapreduce.Reducer;
   
   import java.io.IOException;
   
   /**
    * @author Mrs.An Xueying
    * 2020/6/16 9:29
    * 1. 自定义的类需要继承Reducer，Reducer的四个泛型如下：
    * Reducer<KEYIN, VALUEIN, KEYOUT, VALUEOUT>代表数据类型，是两对，分别为：
    *     ①  输入：KEYIN, VALUEIN
    *          KEYIN：mapper中输出的key的类型
    *          VALUEIN：mapper中输出的value的类型
    *     ②  输出：KEYOUT, VALUEOUT
    *          KEYOUT：单词
    *          VALUEOUT：单词出现的数量（1）
    */
   public class CountReducer extends Reducer<Text, IntWritable, Text,IntWritable> {
       /**
        * KEYOUT  不用封装了，直接用key即可
        * VALUEOUT
        */
       private IntWritable outValue = new IntWritable();
       /**
        * 该方法就是具体操作业务逻辑的方法
        * （一组一组的读数据，key相同则为一组，因此一个key，对应一组value）
        * @param key 单词
        * @param values 相同单词的一组value
        * @param context
        * @throws IOException
        * @throws InterruptedException
        */
       @Override
       protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
           //累加value值
           int sum = 0;
           //遍历所有的value
           for (IntWritable value : values) {
               //value.get()：将IntWritable转成基本数据类型int
               sum += value.get();
               //封装（K,V）
               outValue.set(sum);
               //将数据写出去
               context.write(key,outValue);
           }
       }
   }
   
   ```

3. 创建驱动类

   ```java
   package com.atguigu.wordcount;
   
   import org.apache.hadoop.conf.Configuration;
   import org.apache.hadoop.fs.Path;
   import org.apache.hadoop.io.IntWritable;
   import org.apache.hadoop.io.Text;
   import org.apache.hadoop.mapreduce.Job;
   import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
   import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
   import java.io.IOException;
   
   /**
    * @author Mrs.An Xueying
    * 2020/6/16 9:29
    * 驱动类
    * 1， 作为程序的入口
    * 2. 进行相关的一些关联
    * 3. 参数的设置
    */
   public class CountDriver {
   
       /**
        * 1. 获取job对象
        * 2. 关联jar
        * 3. 关联mapper和reducer
        * 4. 设置mapper的输出类型
        * 5. 设置最终（reducer）输出的key和value的类型
        * 6. 设置输入输出路径
        * 7. 提交job任务
        * @param args
        */
   
       public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
            // 1. 获取job对象
           Job job = Job.getInstance(new Configuration());
           //2. 关联jar
           job.setJarByClass(CountDriver.class);
           //3. 关联mapper和reducer
           job.setMapperClass(CountMapper.class);
           job.setReducerClass(CountReducer.class);
           //4. 设置mapper的输出类型
           job.setMapOutputKeyClass(Text.class);
           job.setMapOutputValueClass(IntWritable.class);
           //5. 设置最终（reducer）输出的key和value的类型
           job.setOutputKeyClass(Text.class);
           job.setOutputValueClass(IntWritable.class);
           //6. 设置输入输出路径
           FileInputFormat.setInputPaths(job,new Path("D:\\hdfstest\\input"));
           //此目录必须不存在
           FileOutputFormat.setOutputPath(job,new Path("D:\\hdfstest\\output"));
           // 7. 提交job任务  参数true ：打印进度
           boolean isSuccess = job.waitForCompletion(true);
           //虚拟机退出的状态：0正常退出 1不正常退出
           //System.exit(isSuccess?0:1);
       }
   }
   ```

4. 运行后，在`D:\\hdfstest\\output`路径中产生如下文件

   ![image-20200616190034392](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200616190034.png)

5. 打开`part-r-00000`

   ```
   aa	3
   ab	1
   abc	1
   ac	1
   bb	2
   cc	2
   dd	2
   ```

#### 2. 在集群中运行MR任务（本地写好，提交到集群jar包）

现在一般不写了，因为写起来很麻烦，容易出问题。后面会用hive封装，hive用sql写。但是要多练，要理解这个流程和思想。这样才有基础去做优化和看源码。

1. 创建驱动类，把输入输出路径改成入参

```java
package com.atguigu.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import java.io.IOException;

/**
 * 在集群中运行MR任务：
 * 1. 路径问题 - 数据读取和输出的路径
 * 2，打jar包（maven）
 * 3. 思考 打jar包的时候需不需要将依赖的jar包打进包里
 *      -  junit  不要
 *      -  log4j  不要  集群里有
 *      -  hadoop-client 不要 集群里有
 */
public class CountDriver2 {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 1. 获取job对象
        Job job = Job.getInstance(new Configuration());
        //2. 关联jar
        job.setJarByClass(CountDriver.class);
        //3. 关联mapper和reducer
        job.setMapperClass(CountMapper.class);
        job.setReducerClass(CountReducer.class);
        //4. 设置mapper的输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //5. 设置最终（reducer）输出的key和value的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //6. 设置输入输出路径  改成入参
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        // 7. 提交job任务  参数true ：打印进度
        boolean b = job.waitForCompletion(true);
        //虚拟机退出的状态：0正常退出 1不正常退出
        System.exit(b?0:1);
    }
}

```

2. 打包 package，改个名字，`mywordcount.jar`，放到hadoop102的/home/atguigu中

3. 运行jar包

   ```shell
   # hadoop jar jar包名 要运行的类的全类名  输入路径（必须存在，且文件夹内放有要输入的数据） 输出路径（不能存在）
   [atguigu@hadoop102 ~]$ hadoop jar mywordcount.jar com.atguigu.wordcount.CountDriver2 /input /output
   ```

4. 查看任务进度

   ![image-20200616192718833](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200616192718.png)

5. 查看结果（与本地运行一致）

   ![image-20200616192750720](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200616192750.png)

#### 3. 本地提交任务到集群

1. 配置集群信息

   ```java
   package com.atguigu.wordcount;
   
   import org.apache.hadoop.conf.Configuration;
   import org.apache.hadoop.fs.Path;
   import org.apache.hadoop.io.IntWritable;
   import org.apache.hadoop.io.Text;
   import org.apache.hadoop.mapreduce.Job;
   import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
   import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
   import java.io.IOException;
   
   /**
    * 在本地提交任务到集群上
    * 1.配置一些内容
    * 2.打包：
    *      打包前 ：job.setJarByClass(CountDriver3.class);
    *      打包后 ：job.setJar("D:\\code\\hadoop\\target\\hadoop-1.0-SNAPSHOT.jar");
    * 3.配置（给args传值）
    *     VMOPTIONS :  -DHADOOP_USER_NAME=atguigu  (使用哪个用户操作集群)
    *     PROGRAM ARGUMENTS ：hdfs://hadoop102:9820/input hdfs://hadoop102:9820/output （输入和输出路径）
    */
   public class CountDriver3 {
       public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
           //用来设置配置内容的对象
           Configuration conf = new Configuration();
           //指定HDFS中NameNode的地址
           conf.set("fs.defaultFS", "hdfs://hadoop102:9820");
           //指定MR运行在Yarn上
           conf.set("mapreduce.framework.name","yarn");
           //指定MR可以在远程集群上运行
           conf.set("mapreduce.app-submission.cross-platform","true");
           //指定resourcemanager的位置
           conf.set("yarn.resourcemanager.hostname","hadoop103");
   
           //        1.获取job对象
           Job job = Job.getInstance(conf);
   //        2.关联jar ---打包前需要配置的
           job.setJarByClass(CountDriver3.class);
           //指定jar包的路径---打包后需要配置的
          // job.setJar("D:\\IdeaProjects\\myhadoop\\target\\myhadoop-1.0-SNAPSHOT.jar");
   //        3.关联mapper和reducer
           job.setMapperClass(CountMapper.class);
           job.setReducerClass(CountReducer.class);
   //        4.设置mapper的输出的key和value类型
           job.setMapOutputKeyClass(Text.class);
           job.setMapOutputValueClass(IntWritable.class);
   //        5.设置最终(reducer)输出的key和value的类型
           job.setOutputKeyClass(Text.class);
           job.setOutputValueClass(IntWritable.class);
   //        6.设置输入输出路径
           FileInputFormat.setInputPaths(job,new Path(args[0]));
           //注意 ：输出目录必须不存在
           FileOutputFormat.setOutputPath(job,new Path(args[1]));
   //        7.提交job任务
           //boolean verbose是否打印进度
           boolean isSuccess = job.waitForCompletion(true);
           //虚拟机退出的状态 ：0是正常退出，1非正常退出
   //        System.exit(isSuccess ? 0 : 1);
   
       }
   }
   
   ```

   

2. 配置idea中运行参数

   ![image-20200616195511797](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200616195511.png)

3. 打包，复制生成的包路径放到关联jar的位置，替换掉原来的setJarByClass

   ![image-20200616195543763](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200616195543.png)

4. 运行，查看结果

   ![image-20200616195659913](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200616195700.png)

#### 4. 统计客户手机流量（FlowBean）

给定一批数据要求统计每个客户的手机流量

![image-20200616232041404](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200616232041.png)

```
1	13736230513	192.196.100.1	www.atguigu.com	2481	24681	200
2	13846544121	192.196.100.2			264	0	200
3 	13956435636	192.196.100.3			132	1512	200
4 	13966251146	192.168.100.1			240	0	404
5 	18271575951	192.168.100.2	www.atguigu.com	1527	2106	200
6 	84188413	192.168.100.3	www.atguigu.com	4116	1432	200
7 	13590439668	192.168.100.4			1116	954	200
8 	15910133277	192.168.100.5	www.hao123.com	3156	2936	200
9 	13729199489	192.168.100.6			240	0	200
10 	13630577991	192.168.100.7	www.shouhu.com	6960	690	200
11 	15043685818	192.168.100.8	www.baidu.com	3659	3538	200
12 	15959002129	192.168.100.9	www.atguigu.com	1938	180	500
13 	13560439638	192.168.100.10			918	4938	200
14 	13470253144	192.168.100.11			180	180	200
15 	13682846555	192.168.100.12	www.qq.com	1938	2910	200
16 	13992314666	192.168.100.13	www.gaga.com	3008	3720	200
17 	13509468723	192.168.100.14	www.qinghua.com	7335	110349	404
18 	18390173782	192.168.100.15	www.sogou.com	9531	2412	200
19 	13975057813	192.168.100.16	www.baidu.com	11058	48243	200
20 	13768778790	192.168.100.17			120	120	200
21 	13568436656	192.168.100.18	www.alibaba.com	2481	24681	200
22 	13568436656	192.168.100.19			1116	954	200
```

1. 创建FlowBean

```java
package com.atguigu.writable;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author Mrs.An Xueying
 * 2020/6/16 20:09
 *
 * 使用hadoop序列化框架
 * 1. 自定义类并实现wirtable接口
 * 2. 重写wirte和readFields方法
 * 3. 读时数据的顺序必须和写时数据的顺序相同
 */
public class FlowBean implements Writable {
    //上行流量
    private long upFlow;
    //下行流量
    private long downFlow;
    //总流量
    private long sumFlow;

    public FlowBean() {
    }

    public FlowBean(long upFlow, long downFlow) {
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = upFlow+downFlow;
    }

    /**
     * 当我们通过reducer向外写数据时（对象）实际上调用的是toString方法
     * @return
     */
    @Override
    public String toString() {
        return upFlow + " " + downFlow + " " + sumFlow;
    }

    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }

    /**
     * 序列化：写
     * @param out
     * @throws IOException
     */
    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);
    }

    /**
     * 反序列化：读
     * @param in
     * @throws IOException
     */
    public void readFields(DataInput in) throws IOException {
        upFlow = in.readLong();
        downFlow = in.readLong();
        sumFlow = in.readLong();
    }
}

```

2. 编写Map、Reduce类

   ```java
   package com.atguigu.writable;
   
   import org.apache.hadoop.io.LongWritable;
   import org.apache.hadoop.io.Text;
   import org.apache.hadoop.mapreduce.Mapper;
   
   import java.io.IOException;
   
   /**
    * @author Mrs.An Xueying
    * 2020/6/16 20:49
    * LongWritable, Text  偏移量，一行数据
    * Text,FlowBean  输出key，value
    */
   public class FlowMapper extends Mapper<LongWritable, Text,Text,FlowBean> {
       private Text outKey = new Text();
   
       /**
        * 读数据
        * @param key 偏移量
        * @param value 一行数据
        * @param context 输出
        * @throws IOException
        * @throws InterruptedException
        */
       @Override
       protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
           //分割数据
           String[] phoneInfo = value.toString().split("\t");
           //封装K,V
           outKey.set(phoneInfo[1]);
           //从数组中取出对应的数据，并转成long类型
           long upFlow = Long.parseLong(phoneInfo[phoneInfo.length - 3]);
           long downFlow = Long.parseLong(phoneInfo[phoneInfo.length - 2]);
           //封装value
           FlowBean flowBean = new FlowBean(upFlow, downFlow);
           //写数据
           context.write(outKey,flowBean);
       }
   }
   
   ```

   ```java
   package com.atguigu.writable;
   
   import org.apache.hadoop.io.Text;
   import org.apache.hadoop.mapreduce.Reducer;
   
   import java.io.IOException;
   
   /**
    * @author Mrs.An Xueying
    * 2020/6/16 21:00
    */
   public class FlowReducer extends Reducer<Text,FlowBean,Text,FlowBean> {
       @Override
       protected void reduce(Text key, Iterable<FlowBean> values, Context context)
               throws IOException, InterruptedException {
   
           long upFlow = 0; //累加相同手机号的upflow
           long downFlow = 0; //累加相同手机号的downflow
           //遍历一组一组的数据
           for (FlowBean value : values) {
               //取出每一条数据的upflow,downflow并将upflow和downflow分别累加
               upFlow += value.getUpFlow();
               downFlow += value.getDownFlow();
           }
           //封装K,V
           FlowBean outValue = new FlowBean(upFlow, downFlow);
           //写出数据
           context.write(key,outValue);
       }
   }
   
   ```

   

3. 编写驱动类

   ```java
   package com.atguigu.writable;
   
   import org.apache.hadoop.conf.Configuration;
   import org.apache.hadoop.fs.Path;
   import org.apache.hadoop.io.Text;
   import org.apache.hadoop.mapreduce.Job;
   import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
   import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
   
   import java.io.IOException;
   
   /**
    * @author Mrs.An Xueying
    * 2020/6/16 21:00
    */
   public class FlowDriver {
       /*
            1.获取job对象
            2.关联jar
            3.关联mapper和reducer
            4.设置mapper的输出的key和value类型
            5.设置最终(reducer)输出的key和value的类型
            6.设置输入输出路径
            7.提交job任务
         */
       public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
   
   //        1.获取job对象
           Job job = Job.getInstance(new Configuration());
   //        2.关联jar
           job.setJarByClass(FlowDriver.class);
   //        3.关联mapper和reducer
           job.setMapperClass(FlowMapper.class);
           job.setReducerClass(FlowReducer.class);
   //        4.设置mapper的输出的key和value类型
           job.setMapOutputKeyClass(Text.class);
           job.setMapOutputValueClass(FlowBean.class);
   //        5.设置最终(reducer)输出的key和value的类型
           job.setOutputKeyClass(Text.class);
           job.setOutputValueClass(FlowBean.class);
   //        6.设置输入输出路径
           FileInputFormat.setInputPaths(job,new Path("D:\\hdfstest\\input"));
           FileOutputFormat.setOutputPath(job,new Path("D:\\hdfstest\\output"));
   //        7.提交job任务
           job.waitForCompletion(true);
   
       }
   }
   
   ```

   

4. 查看结果

   ![image-20200616225733438](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200616225733.png)

#### 5. 注意

1. 任务提交给yarn才能看到任务状态，否则可以用waitForCompletion的返回值来确定任务是否正常运行完成。

2. 想在控制台显示任务进度信息，需要在resources中加入`log4j2.xml`

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <Configuration status="error" strict="true" name="XMLConfig">
       <Appenders>
           <!-- 类型名为Console，名称为必须属性 -->
           <Appender type="Console" name="STDOUT">
               <!-- 布局为PatternLayout的方式，
               输出样式为[INFO] [2018-01-22 17:34:01][org.test.Console]I'm here -->
               <Layout type="PatternLayout"
                       pattern="[%p] [%d{yyyy-MM-dd HH:mm:ss}][%c{10}]%m%n" />
           </Appender>
   
       </Appenders>
   
       <Loggers>
           <!-- 可加性为false -->
           <Logger name="test" level="info" additivity="false">
               <AppenderRef ref="STDOUT" />
           </Logger>
   
           <!-- root loggerConfig设置 -->
           <Root level="info">
               <AppenderRef ref="STDOUT" />
           </Root>
       </Loggers>
   </Configuration>
   
   ```


3. 运行时出现问题![image-20200617010821309](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200617010821.png)

   可以看看是否是输出结果文件夹已存在

4. hadoop环境变量问题![image-20200617010901910](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200617010902.png)

   一定要设置对

   ![image-20200616200037404](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200616200037.png)

5. Windows中c盘system32要放入hadoop的三个文件：

   -  hadoop.dll
   -  winutils.exe
   - libwinutils.lib

6. 发生正常生成output文件夹，但其中没内容，任务进度0%就停止，很有可能是数据类型的包导错了，如Text。

7. 报错如下![image-20200617010659527](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200617010659.png)

   请检查4、5，并使hadoop版本（本地）与配置文件版本一致

## 3. MapReduce框架原理

### 1. InputFormat数据输入

![image-20200616232840558](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200616232840.png)

1. 过程：Map阶段  -----> shuffle阶段 ------> Reduce阶段
   注意：shuffle阶段的前一半属于Map阶段，后一半属性Reduce阶段

   比如：上学-午休-放学

2. MR数据流：输入数据 ---> InputFormat （FileInputFormat）-----> Mapper-----> Shuffle ------> Reducer -----> OutputFormat ----> 输出数据

   比如：来学校-上课-学习-午休-上课-学习-离开学校

3. 从MapTask和ReduceTask去看：

   1. MapTask和ReduceTask都是线程

   2. MapTask源码

      ```java
      // If there are reducers then the entire attempt's progress will be 
      // split between the map phase (67%) and the sort phase (33%).
      mapPhase = getProgress().addPhase("map", 0.667f);
      sortPhase  = getProgress().addPhase("sort", 0.333f);
      ```

   3. ReduceTask源码

      ```java
      copyPhase = getProgress().addPhase("copy");
      sortPhase  = getProgress().addPhase("sort");
      reducePhase = getProgress().addPhase("reduce");
      ```

      比如：每天上课5个小时，午休2个小时，来回路上2个小时，分别是坐车去和走回来。每天课程分别为语数外。

   4. 总结：map ----> sort ---> copy ---> sort ---->reduce

   5. shuffle就是其中的`sort ---> copy ---> sort`

#### 1. 切片与MapTask并行度决定机制

#### 2. Job提交流程源码和切片源码详解

##### **DEBUG**

![image-20200616235322007](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200616235322.png)



##### 查看job提交流程源码

1. waitForCompletion方法处打断点，进入方法

2. 源码

   ```java
   public boolean waitForCompletion(boolean verbose
                                      ) throws IOException, InterruptedException,
                                               ClassNotFoundException {
       //此处是为了防止重复提交                                            
       if (state == JobState.DEFINE) {
         //提交任务，进入方法，继续看源码
         submit();
       }
       if (verbose) {
         monitorAndPrintJob();
       } else {
         // get the completion poll interval from the client.
         int completionPollIntervalMillis = 
           Job.getCompletionPollInterval(cluster.getConf());
         while (!isComplete()) {
           try {
             Thread.sleep(completionPollIntervalMillis);
           } catch (InterruptedException ie) {
           }
         }
       }
       return isSuccessful();
     }
   
   /**
      * Submit the job to the cluster and return immediately.
      * @throws IOException
      */
     public void submit() 
            throws IOException, InterruptedException, ClassNotFoundException {
       //再次验证状态
       ensureState(JobState.DEFINE);
       //调用新的API
       setUseNewAPI();
       //获取连接，进入看源码
       connect();
         //out出来之后 这里创建了对象，这里主要是看任务到底是提交到集群上还是本地上
       final JobSubmitter submitter = 
           getJobSubmitter(cluster.getFileSystem(), cluster.getClient());
       status = ugi.doAs(new PrivilegedExceptionAction<JobStatus>() {
         public JobStatus run() throws IOException, InterruptedException, 
         ClassNotFoundException {
             //拿到了JobSubmitter 在此处打断点
           return submitter.submitJobInternal(Job.this, cluster);
         }
       });
       state = JobState.RUNNING;
       LOG.info("The url to track the job: " + getTrackingURL());
      }
   
     private synchronized void connect()
             throws IOException, InterruptedException, ClassNotFoundException {
       //判断节点是集群还是本地
       if (cluster == null) {
         cluster =   //调用方法
           ugi.doAs(new PrivilegedExceptionAction<Cluster>() {  //内部类
                      public Cluster run()
                             throws IOException, InterruptedException, 
                                    ClassNotFoundException {
                          //
                        return new Cluster(getConfiguration());//在本行打断点进入
                      }
                    });
       }
     }
   
     //构造器   参数：conf 配置文件
     public Cluster(Configuration conf) throws IOException {
         //另外一个构造器
       this(null, conf);
     }
   
   //另外一个构造器
     public Cluster(InetSocketAddress jobTrackAddr, Configuration conf) 
         throws IOException {
         //配置文件
       this.conf = conf;
         //机器名称
       this.ugi = UserGroupInformation.getCurrentUser();
         //在这里进入方法
       initialize(jobTrackAddr, conf);
     }
   
     private void initialize(InetSocketAddress jobTrackAddr, Configuration conf)
         throws IOException {
   
       initProviderList();
       final IOException initEx = new IOException(
           "Cannot initialize Cluster. Please check your configuration for "
               + MRConfig.FRAMEWORK_NAME
               + " and the correspond server addresses.");
       if (jobTrackAddr != null) {
         LOG.info(
             "Initializing cluster for Job Tracker=" + jobTrackAddr.toString());
       }
       for (ClientProtocolProvider provider : providerList) {
         LOG.debug("Trying ClientProtocolProvider : "
             + provider.getClass().getName());
         ClientProtocol clientProtocol = null;
         try {
           if (jobTrackAddr == null) {
               //在此处打节点，跳到这里，进入方法  //还有第二次进，配置了就有配置的值，否则此时就会有一个默认值local 创建一个localjobrunner
             clientProtocol = provider.create(conf);
           } else {
             clientProtocol = provider.create(jobTrackAddr, conf);
           }
   		//再次验证为null进入else，再次try
           if (clientProtocol != null) {
             clientProtocolProvider = provider;
             client = clientProtocol;
             LOG.debug("Picked " + provider.getClass().getName()
                 + " as the ClientProtocolProvider");
             break;
           } else {
             LOG.debug("Cannot pick " + provider.getClass().getName()
                 + " as the ClientProtocolProvider - returned null protocol");
           }
         } catch (Exception e) {
           final String errMsg = "Failed to use " + provider.getClass().getName()
               + " due to error: ";
           initEx.addSuppressed(new IOException(errMsg, e));
           LOG.info(errMsg, e);
         }
       }
   
       if (null == clientProtocolProvider || null == client) {
         throw initEx;
       }
     }
   
   //mapreduce.framework.name 本地任务没做配置就没有这个值
       public ClientProtocol create(Configuration conf) throws IOException {
           //查看get
           //有则创建YARNRunner，没有返回null
           return "yarn".equals(conf.get("mapreduce.framework.name")) ? new YARNRunner(conf) : null;
       }
   
   
   public String get(String name) {
       //遍历，找结果
           String[] names = this.handleDeprecation((Configuration.DeprecationContext)deprecationContext.get(), name);
           String result = null;
           String[] var4 = names;
           int var5 = names.length;
   		
           for(int var6 = 0; var6 < var5; ++var6) {
               String n = var4[var6];
               result = this.substituteVars(this.getProps().getProperty(n));
           }
   
           return result;
       }
   
     JobStatus submitJobInternal(Job job, Cluster cluster) 
     throws ClassNotFoundException, InterruptedException, IOException {
   
       //validate the jobs output specs 
         //路径检查 输出路径存不存在
       checkSpecs(job);
   
       Configuration conf = job.getConfiguration();
       addMRFrameworkToDistributedCache(conf);
   
         //重点：打断点
         //得到一个路径，在本地提交任务到集群的过程中，会在一个目录中放置一些配置信息（切片信息、配置信息等 ），这些配置信息和任务一起发布到集群上
       Path jobStagingArea = JobSubmissionFiles.getStagingDir(cluster, conf);
       //configure the command line options correctly on the submitting dfs
       InetAddress ip = InetAddress.getLocalHost();
       if (ip != null) {
         submitHostAddress = ip.getHostAddress();
         submitHostName = ip.getHostName();
         conf.set(MRJobConfig.JOB_SUBMITHOST,submitHostName);
         conf.set(MRJobConfig.JOB_SUBMITHOSTADDR,submitHostAddress);
       }
         //生成jobid
       JobID jobId = submitClient.getNewJobID();
         //设置jobid
       job.setJobID(jobId);
         //把目录拼到一起
       Path submitJobDir = new Path(jobStagingArea, jobId.toString());
       JobStatus status = null;
       try {
         conf.set(MRJobConfig.USER_NAME,
             UserGroupInformation.getCurrentUser().getShortUserName());
         conf.set("hadoop.http.filter.initializers", 
             "org.apache.hadoop.yarn.server.webproxy.amfilter.AmFilterInitializer");
         conf.set(MRJobConfig.MAPREDUCE_JOB_DIR, submitJobDir.toString());
         LOG.debug("Configuring job " + jobId + " with " + submitJobDir 
             + " as the submit dir");
         // get delegation token for the dir
         TokenCache.obtainTokensForNamenodes(job.getCredentials(),
             new Path[] { submitJobDir }, conf);
         
         populateTokenCache(conf, job.getCredentials());
   
         // generate a secret to authenticate shuffle transfers
         if (TokenCache.getShuffleSecretKey(job.getCredentials()) == null) {
           KeyGenerator keyGen;
           try {
             keyGen = KeyGenerator.getInstance(SHUFFLE_KEYGEN_ALGORITHM);
             keyGen.init(SHUFFLE_KEY_LENGTH);
           } catch (NoSuchAlgorithmException e) {
             throw new IOException("Error generating shuffle secret key", e);
           }
           SecretKey shuffleKey = keyGen.generateKey();
           TokenCache.setShuffleSecretKey(shuffleKey.getEncoded(),
               job.getCredentials());
         }
         if (CryptoUtils.isEncryptedSpillEnabled(conf)) {
           conf.setInt(MRJobConfig.MR_AM_MAX_ATTEMPTS, 1);
           LOG.warn("Max job attempts set to 1 since encrypted intermediate" +
                   "data spill is enabled");
         }
   
           //job 和 目录 -->配置文件
         copyAndConfigureFiles(job, submitJobDir);
   
           //拼出来的目录
         Path submitJobFile = JobSubmissionFiles.getJobConfPath(submitJobDir);
         
         // Create the splits for the job
         LOG.debug("Creating splits at " + jtFs.makeQualified(submitJobDir));
           //调用writeSplits方法写切片信息
         int maps = writeSplits(job, submitJobDir);
         conf.setInt(MRJobConfig.NUM_MAPS, maps);
         LOG.info("number of splits:" + maps);
   
         int maxMaps = conf.getInt(MRJobConfig.JOB_MAX_MAP,
             MRJobConfig.DEFAULT_JOB_MAX_MAP);
         if (maxMaps >= 0 && maxMaps < maps) {
           throw new IllegalArgumentException("The number of map tasks " + maps +
               " exceeded limit " + maxMaps);
         }
   
         // write "queue admins of the queue to which job is being submitted"
         // to job file.
         String queue = conf.get(MRJobConfig.QUEUE_NAME,
             JobConf.DEFAULT_QUEUE_NAME);
         AccessControlList acl = submitClient.getQueueAdmins(queue);
         conf.set(toFullPropertyName(queue,
             QueueACL.ADMINISTER_JOBS.getAclName()), acl.getAclString());
   
         // removing jobtoken referrals before copying the jobconf to HDFS
         // as the tasks don't need this setting, actually they may break
         // because of it if present as the referral will point to a
         // different job.
         TokenCache.cleanUpTokenReferral(conf);
   
         if (conf.getBoolean(
             MRJobConfig.JOB_TOKEN_TRACKING_IDS_ENABLED,
             MRJobConfig.DEFAULT_JOB_TOKEN_TRACKING_IDS_ENABLED)) {
           // Add HDFS tracking ids
           ArrayList<String> trackingIds = new ArrayList<String>();
           for (Token<? extends TokenIdentifier> t :
               job.getCredentials().getAllTokens()) {
             trackingIds.add(t.decodeIdentifier().getTrackingId());
           }
           conf.setStrings(MRJobConfig.JOB_TOKEN_TRACKING_IDS,
               trackingIds.toArray(new String[trackingIds.size()]));
         }
   
         // Set reservation info if it exists
         ReservationId reservationId = job.getReservationId();
         if (reservationId != null) {
           conf.set(MRJobConfig.RESERVATION_ID, reservationId.toString());
         }
   
         // Write job file to submit dir
           //写配置信息
         writeConf(conf, submitJobFile);
         
         //
         // Now, actually submit the job (using the submit name)
         //
         printTokens(jobId, job.getCredentials());
           //提交任务了!!
         status = submitClient.submitJob(
             jobId, submitJobDir.toString(), job.getCredentials());
         if (status != null) {
           return status;
         } else {
           throw new IOException("Could not launch job");
         }
       } finally {
         if (status == null) {
           LOG.info("Cleaning up the staging area " + submitJobDir);
           if (jtFs != null && submitJobDir != null)
             jtFs.delete(submitJobDir, true);
   
         }
       }
     }
     
   ```

   ![image-20200617004606399](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200617004606.png)

   本地目录中存放的job配置信息会随着任务的提交而消失

3. job的主要任务

   1. 往本地提交or集群
   2. 往本地写job的配置信息（提交完就没了）
   3. 提交任务

```java
开始提交任务
waitForCompletion()
submit();

1.建立连接
connect();	
		
	1.1创建提交Job的代理
		new Cluster(getConfiguration());
	1.2判断是本地yarn还是远程
		initialize(jobTrackAddr, conf); 

2. 提交job
	submitter.submitJobInternal(Job.this, cluster)
	2.1创建给集群提交数据的Stag路径
		Path jobStagingArea = JobSubmissionFiles.getStagingDir(cluster, conf);

	2.2获取jobid ，并创建Job路径
		JobID jobId = submitClient.getNewJobID();

	2.3拷贝jar包到集群
		copyAndConfigureFiles(job, submitJobDir);	
			rUploader.uploadFiles(job, jobSubmitDir);

	2.4计算切片，生成切片规划文件
		writeSplits(job, submitJobDir);
		maps = writeNewSplits(job, jobSubmitDir);
		input.getSplits(job);

	2.5向Stag路径写XML配置文件
		writeConf(conf, submitJobFile);
		conf.writeXml(out);

	2.6提交Job,返回提交状态
		status = submitClient.submitJob(jobId, submitJobDir.toString(), job.getCredentials());

```

![image-20200617005047945](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200617005048.png)

#### 3. FileInputFormat切片机

![image-20200617005127758](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200617005127.png)制



#### 4. CombineTextInputFormat切片机制

#### 5. CombineTextInputFormat案例实操

#### 6. TestInputFormat的KV

### 2. MapReduce工作流程

### 3. Shuffle机制

#### 1. Shuffle机制

#### 2. Partition分区

#### 3. Partition分区案例实操

#### 4. WritebleComparable排序

#### 5. WritebleComparable排序（全排序）

#### 6. WritebleComparable排序（区内排序）

#### 7. Combiner合并

#### 8. Combiner合并案例实操

### 4. MapTask工作机制

### 5. ReduceTask工作机制

### 6. OutputFormat数据输出

#### 1. OutputFormat接口实现类

#### 2. 自定义OutputFormat

#### 3. 自定义OutputFormat案例实操

### 7. Join多种应用

#### 1. Reduce Join

#### 2. Reduce Join案例实操

#### 3. Map Join

#### 4. Map Join案例实操

### 8. 计数器应用

### 9. 数据清洗（ETL）

### 10. MapReduce开发总结

# 四、Yarn

## 1. Yarn基本架构

## 2. Yarn工作机制

## 3. 作业提交全过程

## 4. 资源调度器

## 5. 容量调度器多队列提交案例

### 1. 需求

### 2. 配置多队列的容量调度器

### 3. 向Hive队列提交任务

## 6. MapReduce&Yarn常见错误及解决方案

1. 运行任务时发生

```
Failed to execute goal org.codehaus.mojo:exec-maven-plugin:3.0.0:exec (default-cli) on project myhadoop: Command execution failed.
```

一般是输入输出路径的问题。如：输出目录已存在，将结果输出目录换成不存在的目录即可

2. 找不到hadoop包

   