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
- Hadoop生态圈

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

**安装JDK**

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