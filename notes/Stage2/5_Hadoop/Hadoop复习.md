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

```
sudo vim /etc/profile.d/my_env.sh
```

```
#HADOOP_HOME
export HADOOP_HOME=/opt/module/hadoop-3.1.3
export PATH=$PATH:$HADOOP_HOME/bin
export PATH=$PATH:$HADOOP_HOME/sbin
```

```
source /etc/profile
```

测试是否安装成功

```
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

初次格式化之后产生的文件夹，只能格式化一次！！如要再次格式化，先删掉data和logs，再格式化！！

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