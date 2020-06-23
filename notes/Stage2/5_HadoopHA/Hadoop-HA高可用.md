# 1. HA概述

企业中，一般不会把NameNode和DataNode部署在一台机器上。

**问题：**

ResourceManager是Yarn的单点故障源。

NameNode是HDFS的单点故障源。

**单点故障：**

集群中有一台机器故障，整个集群崩溃。

> 2NN为啥不行？
>
> 2NN是护士，NN是医生。顶不上。少了滚动日志。



**HA（High Availablity）**：高可用（7*24小时不中断服务）

# 2. HDFS-HA工作机制

通过多个NameNode消除单点故障

**解决办法**：安装多台NN

**此方案的存在的问题：**

1. 多个NN内部元数据的数据一致性的问题
2. 多个NN不能同时对外服务，只能一台NN对外服务，当这台工作的NN挂掉以后，剩下的备胎NN直接顶上。
3. 多个standby上位问题

## 工作要点

**问题1的解决方案：**

**HA架构**（HDFS）

| hadoop102   | hadoop103   | hadoop104   |
| ----------- | ----------- | ----------- |
| NameNode    | DataNode    | NameNode    |
| DataNode    | NameNode    | DataNode    |
| journalnode | journalnode | journalnode |

没有2NN了，来了这么多医生，不需要护士了。

引入三台journalnode--也是分布式的--存NN产生的edits文件。保证多个NN的数据一致性问题。

**问题2的解决方案：**

给NN定义两个状态

- active：正在使用
- standby：备胎

**问题3的解决方案：**

防止脑裂（Brain Split）：下一台转化为active时，必须连接到上一台active的节点并确定其状态为standby。

1. 手动故障转移：完全由开发人员控制，手动切换standby的nn的状态为active。

   问题：102是active，102死了，103、104连不上上一台active，无法转化状态。

2. 自动故障转移：见下文

**防止脑裂机制**

1. ssh发送kill指令（一定要都配上免密哦）
2. 调用用户自定义脚本程序

## 自动故障转移工作机制

自动故障转移为HDFS部署增加了两个新组件：**ZooKeeper和ZKFailoverController（ZKFC）进程**，如图3-20所示。ZooKeeper是维护少量协调数据，通知客户端这些数据的改变和监视客户端故障的高可用服务。

### ZooKeeper功能

**1．故障检测**

集群中的每个NameNode在ZooKeeper中维护了一个会话，如果机器崩溃，ZooKeeper中的会话将终止，ZooKeeper通知另一个NameNode需要触发故障转移。

**2．现役NameNode选择**

ZooKeeper提供了一个简单的机制用于唯一的选择一个节点为active状态。如果目前现役NameNode崩溃，另一个节点可能从ZooKeeper获得特殊的排外锁以表明它应该成为现役NameNode。

### ZKFC功能

ZKFC是自动故障转移中的另一个新组件，是ZooKeeper的客户端，也监视和管理NameNode的状态。每个运行NameNode的主机也运行了一个ZKFC进程，ZKFC负责：

**1）健康监测**

ZKFC使用一个健康检查命令定期地ping与之在相同主机的NameNode，只要该NameNode及时地回复健康状态，ZKFC认为该节点是健康的。如果该节点崩溃，冻结或进入不健康状态，健康监测器标识该节点为非健康的。

**2）ZooKeeper会话管理**

当本地NameNode是健康的，ZKFC保持一个在ZooKeeper中打开的会话。如果本地NameNode处于active状态，ZKFC也保持一个特殊的znode锁，该锁使用了ZooKeeper对短暂节点的支持，如果会话终止，锁节点将自动删除。

**3）基于ZooKeeper的选择**

如果本地NameNode是健康的，且ZKFC发现没有其它的节点当前持有znode锁，它将为自己获取该锁。如果成功，则它已经赢得了选择，并负责运行故障转移进程以使它的本地NameNode为Active。

# 3. HDFS-HA集群配置

## 1. 环境准备

（1）修改IP

（2）修改主机名及主机名和IP地址的映射

（3）关闭防火墙

（4）ssh免密登录

（5）安装JDK，配置环境变量等

## 2. 规划集群

| hadoop102   | hadoop103       | hadoop104   |
| ----------- | --------------- | ----------- |
| NameNode    | NameNode        | NameNode    |
| ZKFC        | ZKFC            | ZKFC        |
| JournalNode | JournalNode     | JournalNode |
| DataNode    | DataNode        | DataNode    |
| ZK          | ZK              | ZK          |
|             | ResourceManager |             |
| NodeManager | NodeManager     | NodeManager |

##  3. 配置Zookeeper集群

详见Zookeeper文档。

## 4. 配置HDFS-HA集群

注意，如果配置的后无法启动，可以看下日志，可能是xml中的中文乱码了，删掉就好了。或者改下编码。

### 1. 新建一个测试用的

别把正经的hadoop搞坏了

```shell
# 在opt目录下创建一个ha文件夹
$ sudo mkdir /opt/ha
# 修改用户、用户组
$ sudo chown atguigu:atguigu /opt/ha
# 将/opt/module/下的 hadoop-3.1.3拷贝到/opt/ha目录下（记得删除data 和 log目录）
$ cp -r /opt/module/hadoop-3.1.3 /opt/ha/
$ rm -rf /opt/ha/hadoop-3.1.3/data  /opt/ha/hadoop-3.1.3/logs
```

### 2. 更改配置文件和环境变量

#### my_env.sh

```shell
$ sudo vim /etc/profile.d/my_env.sh
```

把hadoop的路径改成

```SHELL
export HADOOP_HOME=/opt/ha/hadoop-3.1.3
```

#### core-site.xml

/opt/ha/下的哦，别改错了。修改以下两个部分

```xml
<configuration>
<!-- 把多个NameNode的地址组装成一个集群mycluster -->
  <property>
    <name>fs.defaultFS</name>
    <value>hdfs://mycluster</value>
  </property>
<!-- 指定hadoop运行时产生文件的存储目录 -->
  <property>
    <name>hadoop.tmp.dir</name>
    <value>/opt/ha/hadoop-3.1.3/data</value>
  </property>
</configuration>
```

#### hdfs-site.xml

全粘贴进去就可以了

```xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<!--
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License. See accompanying LICENSE file.
-->

<!-- Put site-specific property overrides in this file. -->

<configuration>
 <!-- NameNode数据存储目录 -->
  <property>
    <name>dfs.namenode.name.dir</name>
    <value>file://${hadoop.tmp.dir}/name</value>
  </property>
<!-- DataNode数据存储目录 -->
  <property>
    <name>dfs.datanode.data.dir</name>
    <value>file://${hadoop.tmp.dir}/data</value>
  </property>
<!-- JournalNode数据存储目录 -->
  <property>
    <name>dfs.journalnode.edits.dir</name>
    <value>${hadoop.tmp.dir}/jn</value>
  </property>
<!-- 完全分布式集群名称 -->
  <property>
    <name>dfs.nameservices</name>
    <value>mycluster</value>
  </property>
<!-- 集群中NameNode节点都有哪些 -->
  <property>
    <name>dfs.ha.namenodes.mycluster</name>
    <value>nn1,nn2,nn3</value>
  </property>
<!-- NameNode的RPC通信地址 -->
  <property>
    <name>dfs.namenode.rpc-address.mycluster.nn1</name>
    <value>hadoop102:8020</value>
  </property>
  <property>
    <name>dfs.namenode.rpc-address.mycluster.nn2</name>
    <value>hadoop103:8020</value>
  </property>
  <property>
    <name>dfs.namenode.rpc-address.mycluster.nn3</name>
    <value>hadoop104:8020</value>
  </property>
<!-- NameNode的http通信地址 -->
  <property>
    <name>dfs.namenode.http-address.mycluster.nn1</name>
    <value>hadoop102:9870</value>
  </property>
  <property>
    <name>dfs.namenode.http-address.mycluster.nn2</name>
    <value>hadoop103:9870</value>
  </property>
  <property>
    <name>dfs.namenode.http-address.mycluster.nn3</name>
    <value>hadoop104:9870</value>
  </property>
<!-- 指定NameNode元数据在JournalNode上的存放位置 -->
  <property>
<name>dfs.namenode.shared.edits.dir</name>
<value>qjournal://hadoop102:8485;hadoop103:8485;hadoop104:8485/mycluster</value>
  </property>
<!-- 访问代理类：client用于确定哪个NameNode为Active -->
  <property>
    <name>dfs.client.failover.proxy.provider.mycluster</name>
    <value>org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider</value>
  </property>
<!-- 配置隔离机制，即同一时刻只能有一台服务器对外响应 -->
  <property>
    <name>dfs.ha.fencing.methods</name>
    <value>sshfence</value>
  </property>
<!-- 使用隔离机制时需要ssh秘钥登录-->
  <property>
    <name>dfs.ha.fencing.ssh.private-key-files</name>
    <value>/home/atguigu/.ssh/id_rsa</value>
  </property>

</configuration>
```

### 3. 配置好后分发到其他节点

```shell
### ha文件夹
$ sudo xsync /opt/ha/
$ sudo xsync /etc/profile.d/my_env.sh
### 同时source一下所有节点
$ source /etc/profile.d/my_env.sh
```

检查一下ha文件夹和环境变量是否成功

```SHELL
$ echo $HADOOP_HOME
```

## 5. 启动HDFS-HA集群

### 1. 启动journalnode服务

```shell
# 每个节点都启动
$ hdfs --daemon start journalnode
```

### 2. 格式化NN1

```shell
$ hdfs namenode -format
$ hdfs --daemon start namenode
```

验证一下

```shell
[atguigu@hadoop102 ~]$ jpscall 
=====hadoop102====
2456 JournalNode
2760 NameNode
2843 Jps
=====hadoop103====
2483 JournalNode
2670 Jps
=====hadoop104====
2906 Jps
2667 JournalNode
```

### 3. 同步元数据信息

NN2, NN3都同步一下元数据

```shell
$ hdfs namenode -bootstrapStandby
```

检查一下，没问题

```shell
[atguigu@hadoop102 current]$ ll
总用量 16
-rw-rw-r--. 1 atguigu atguigu 394 6月  23 13:13 fsimage_0000000000000000000
-rw-rw-r--. 1 atguigu atguigu  62 6月  23 13:13 fsimage_0000000000000000000.md5
-rw-rw-r--. 1 atguigu atguigu   2 6月  23 13:13 seen_txid
-rw-rw-r--. 1 atguigu atguigu 216 6月  23 13:13 VERSION
[atguigu@hadoop103 ~]$ ll /opt/ha/hadoop-3.1.3/data/name/current
总用量 16
-rw-rw-r--. 1 atguigu atguigu 394 6月  23 13:15 fsimage_0000000000000000000
-rw-rw-r--. 1 atguigu atguigu  62 6月  23 13:15 fsimage_0000000000000000000.md5
-rw-rw-r--. 1 atguigu atguigu   2 6月  23 13:15 seen_txid
-rw-rw-r--. 1 atguigu atguigu 216 6月  23 13:15 VERSION
[atguigu@hadoop104 ~]$ ll /opt/ha/hadoop-3.1.3/data/name/current
总用量 16
-rw-rw-r--. 1 atguigu atguigu 394 6月  23 13:15 fsimage_0000000000000000000
-rw-rw-r--. 1 atguigu atguigu  62 6月  23 13:15 fsimage_0000000000000000000.md5
-rw-rw-r--. 1 atguigu atguigu   2 6月  23 13:15 seen_txid
-rw-rw-r--. 1 atguigu atguigu 216 6月  23 13:15 VERSION
```

### 4. 启动NN2,NN3

```shell
$ hdfs --daemon start namenode
```

检查一下

```shell
[atguigu@hadoop104 ~]$ jpscall 
=====hadoop102====
2919 Jps
2456 JournalNode
2760 NameNode
=====hadoop103====
2483 JournalNode
2804 NameNode
2905 Jps
=====hadoop104====
3040 NameNode
3125 Jps
2667 JournalNode
```

### 5. 查看web页面

端口：9870

![image-20200623132121858](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200623132121.png)

### 6. 启动DataNode

所有节点启动

```shell
$ hdfs --daemon start datanode
```

查看是否启动成功

```shell
[atguigu@hadoop102 current]$ jpscall 
=====hadoop102====
2456 JournalNode
2760 NameNode
3034 DataNode
3118 Jps
=====hadoop103====
2483 JournalNode
2804 NameNode
3034 DataNode
3116 Jps
=====hadoop104====
3040 NameNode
3255 DataNode
3337 Jps
2667 JournalNode
```

### 7. 将NN1状态设为Active

```shell
[atguigu@hadoop102 current]$ hdfs haadmin -transitionToActive nn1
# 检查NameNode状态 nn1是否为active
[atguigu@hadoop102 current]$ hdfs haadmin -getServiceState nn1
active
```

![image-20200623132504542](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200623132504.png)

## 6. 配置HDFS-HA自动故障转移

### 1. 关闭所有HDFS服务

```shell
$ stop-dfs.sh
```

检查一下

```shell
[atguigu@hadoop102 current]$ jpscall 
=====hadoop102====
4100 Jps
=====hadoop103====
3977 Jps
=====hadoop104====
4199 Jps
```

### 2. 更改配置文件

#### hdfs-site.xml

```xml
<!-- 启用nn故障自动转移 -->
<property>
	<name>dfs.ha.automatic-failover.enabled</name>
	<value>true</value>
</property>
```

#### core-site.xml

```xml
<!-- 指定zkfc要连接的zkServer地址 -->
<property>
	<name>ha.zookeeper.quorum</name>
<value>hadoop102:2181,hadoop103:2181,hadoop104:2181</value>
</property>
```

### 3. 启动

#### 1. 启动Zookeeper集群

```shell
[atguigu@hadoop102 hadoop]$ zk.sh start
=====================  hadoop102  =======================
ZooKeeper JMX enabled by default
Using config: /opt/module/zookeeper-3.5.7/bin/../conf/zoo.cfg
Starting zookeeper ... STARTED
=====================  hadoop103  =======================
ZooKeeper JMX enabled by default
Using config: /opt/module/zookeeper-3.5.7/bin/../conf/zoo.cfg
Starting zookeeper ... STARTED
=====================  hadoop104  =======================
ZooKeeper JMX enabled by default
Using config: /opt/module/zookeeper-3.5.7/bin/../conf/zoo.cfg
Starting zookeeper ... STARTED
```

### 2. 初始化HA在Zookeeper中状态

```shell
$ hdfs zkfc -formatZK
```

### 3. 启动HDFS服务

```shell
[atguigu@hadoop102 hadoop]$ start-dfs.sh
Starting namenodes on [hadoop102 hadoop103 hadoop104]
Starting datanodes
Starting journal nodes [hadoop102 hadoop103 hadoop104]
Starting ZK Failover Controllers on NN hosts [hadoop102 hadoop103 hadoop104]
[atguigu@hadoop102 hadoop]$ jpscall 
=====hadoop102====
6049 QuorumPeerMain
6194 ZooKeeperMain
6418 NameNode
6549 DataNode
7030 DFSZKFailoverController
6798 JournalNode
7087 Jps
=====hadoop103====
5553 DataNode
5459 NameNode
5875 Jps
5354 QuorumPeerMain
5804 DFSZKFailoverController
5661 JournalNode
=====hadoop104====
5568 DataNode
5361 QuorumPeerMain
5475 NameNode
5893 Jps
5818 DFSZKFailoverController
5676 JournalNode
```

### 4. 验证

进入Zookeeper

```shell
# 锁是临时节点 hadoop104抢到了，才能写持久节点的值为自己
[zk: localhost:2181(CONNECTED) 7] get -s /hadoop-ha/mycluster/ActiveStandbyElectorLock

	myclusternn3	hadoop104 �>(�>
cZxid = 0x40000000b
ctime = Tue Jun 23 13:47:50 CST 2020
mZxid = 0x40000000b
mtime = Tue Jun 23 13:47:50 CST 2020
pZxid = 0x40000000b
cversion = 0
dataVersion = 0
aclVersion = 0
ephemeralOwner = 0x30000935b280000
dataLength = 33
numChildren = 0

# 持久节点  记录当前活着的状态
[zk: localhost:2181(CONNECTED) 8] get -s /hadoop-ha/mycluster/ActiveBreadCrumb

	myclusternn3	hadoop104 �>(�>
cZxid = 0x40000000d
ctime = Tue Jun 23 13:47:51 CST 2020
mZxid = 0x40000000d
mtime = Tue Jun 23 13:47:51 CST 2020
pZxid = 0x40000000d
cversion = 0
dataVersion = 0
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 33
numChildren = 0
```

![image-20200623135202721](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200623135202.png)

此时杀掉nn3的NameNode

```shell
[atguigu@hadoop104 ~]$ kill -9 5475
[atguigu@hadoop104 ~]$ jpscall 
=====hadoop102====
6049 QuorumPeerMain
6194 ZooKeeperMain
6418 NameNode
6549 DataNode
7030 DFSZKFailoverController
7309 Jps
6798 JournalNode
=====hadoop103====
5553 DataNode
6018 Jps
5459 NameNode
5354 QuorumPeerMain
5804 DFSZKFailoverController
5661 JournalNode
=====hadoop104====
5568 DataNode
5361 QuorumPeerMain
6033 Jps
5818 DFSZKFailoverController
5676 JournalNode
```

再看一下Zookeeper

```shell
[zk: localhost:2181(CONNECTED) 9] get -s /hadoop-ha/mycluster/ActiveStandbyElectorLock

	myclusternn1	hadoop102 �>(�>
cZxid = 0x400000010
ctime = Tue Jun 23 13:53:11 CST 2020
mZxid = 0x400000010
mtime = Tue Jun 23 13:53:11 CST 2020
pZxid = 0x400000010
cversion = 0
dataVersion = 0
aclVersion = 0
ephemeralOwner = 0x30000935b280001
dataLength = 33
numChildren = 0
```

102抢到了锁

![image-20200623135428996](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200623135429.png)

后面104再起来，也是standby了，因为锁在102手中

![image-20200623135542441](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200623135542.png)

# 4. YARN-HA配置

## 1. 环境准备

（1）修改IP

（2）修改主机名及主机名和IP地址的映射

（3）关闭防火墙

（4）ssh免密登录

（5）安装JDK，配置环境变量等

（6）配置Zookeeper集群

## 2. 规划集群

| hadoop102       | hadoop103       | hadoop104   |
| --------------- | --------------- | ----------- |
| NameNode        | NameNode        | NameNode    |
| JournalNode     | JournalNode     | JournalNode |
| DataNode        | DataNode        | DataNode    |
| ZK              | ZK              | ZK          |
| ResourceManager | ResourceManager |             |
| NodeManager     | NodeManager     | NodeManager |

## 3. 具体配置

yarn-site.xml把第二个单个的配置去掉 加入以下内容，然后同步到其他节点中

```xml


    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>

    <!--启用resourcemanager ha-->
    <property>
        <name>yarn.resourcemanager.ha.enabled</name>
        <value>true</value>
    </property>
 
    <!--声明两台resourcemanager的地址-->
    <property>
        <name>yarn.resourcemanager.cluster-id</name>
        <value>cluster-yarn1</value>
    </property>
    <!--指定resourcemanager的逻辑列表,在value中增加主机，后面加每一台的配置-->
    <property>
        <name>yarn.resourcemanager.ha.rm-ids</name>
        <value>rm1,rm2</value>
	</property>
	<!-- ========== rm1的配置 ========== -->
	<!--指定rm1的主机名-->
    <property>
        <name>yarn.resourcemanager.hostname.rm1</name>
        <value>hadoop102</value>
	</property>
	<!-- 指定rm1的web端地址 -->
	<property>
     	<name>yarn.resourcemanager.webapp.address.rm1</name>
     	<value>hadoop102:8088</value>
	</property>
	<!-- 指定rm1的内部通信地址 -->
	<property>
     	<name>yarn.resourcemanager.address.rm1</name>
     	<value>hadoop102:8032</value>
	</property>
	<!-- 指定AM向rm1申请资源的地址 -->
	<property>
     	<name>yarn.resourcemanager.scheduler.address.rm1</name>  
     	<value>hadoop102:8030</value>
	</property>
	<!-- 指定供NM连接的地址 -->  
	<property>
     	<name>yarn.resourcemanager.resource-tracker.address.rm1</name>
     	<value>hadoop102:8031</value>
	</property>
	<!-- ========== rm2的配置 ========== -->
    <!--指定rm2的主机名-->
    <property>
        <name>yarn.resourcemanager.hostname.rm2</name>
        <value>hadoop103</value>
    </property>
    <property>
         <name>yarn.resourcemanager.webapp.address.rm2</name>
         <value>hadoop103:8088</value>
    </property>
    <property>
         <name>yarn.resourcemanager.address.rm2</name>
         <value>hadoop103:8032</value>
    </property>
    <property>
         <name>yarn.resourcemanager.scheduler.address.rm2</name>
         <value>hadoop103:8030</value>
    </property>
    <property>
         <name>yarn.resourcemanager.resource-tracker.address.rm2</name>
         <value>hadoop103:8031</value>
    </property>
 
    <!--指定zookeeper集群的地址--> 
    <property>
        <name>yarn.resourcemanager.zk-address</name>
        <value>hadoop102:2181,hadoop103:2181,hadoop104:2181</value>
    </property>

    <!--启用自动恢复--> 
    <property>
        <name>yarn.resourcemanager.recovery.enabled</name>
        <value>true</value>
    </property>
 
    <!--指定resourcemanager的状态信息存储在zookeeper集群,内部有zkfc--> 
    <property>
        <name>yarn.resourcemanager.store.class</name>     
        <value>org.apache.hadoop.yarn.server.resourcemanager.recovery.ZKRMStateStore</value>
	</property>
    <!-- 环境变量的继承 -->
     <property>
         <name>yarn.nodemanager.env-whitelist</name>
         <value>JAVA_HOME,HADOOP_COMMON_HOME,HADOOP_HDFS_HOME,HADOOP_CONF_DIR,CLASSPATH_PREPEND_DISTCACHE,HADOOP_YARN_HOME,HADOOP_MAPRED_HOME</value>
    </property>
```

## 4. 启动hdfs , yarn

```shell
#HDFS
[atguigu@hadoop102 ~]$ start-dfs.sh
Starting namenodes on [hadoop102 hadoop103 hadoop104]
Starting datanodes
Starting journal nodes [hadoop102 hadoop103 hadoop104]
Starting ZK Failover Controllers on NN hosts [hadoop102 hadoop103 hadoop104]
[atguigu@hadoop102 ~]$ jpscall 
=====hadoop102====
10306 DataNode
10786 DFSZKFailoverController
10556 JournalNode
9885 QuorumPeerMain
10175 NameNode
10847 Jps
=====hadoop103====
8384 DFSZKFailoverController
8241 JournalNode
8132 DataNode
8038 NameNode
8443 Jps
7932 QuorumPeerMain
=====hadoop104====
8210 Jps
8115 DFSZKFailoverController
7973 JournalNode
7670 QuorumPeerMain
7864 DataNode
7770 NameNode
#YARN
[atguigu@hadoop102 ~]$ start-yarn.sh
Starting resourcemanagers on [ hadoop102 hadoop103]
Starting nodemanagers
[atguigu@hadoop102 ~]$ jpscall 
=====hadoop102====
10306 DataNode
10786 DFSZKFailoverController
11349 NodeManager
11206 ResourceManager
11529 Jps
10556 JournalNode
9885 QuorumPeerMain
10175 NameNode
=====hadoop103====
8384 DFSZKFailoverController
8241 JournalNode
8132 DataNode
8038 NameNode
8535 ResourceManager
8651 NodeManager
8955 Jps
7932 QuorumPeerMain
=====hadoop104====
8115 DFSZKFailoverController
7973 JournalNode
7670 QuorumPeerMain
8295 NodeManager
7864 DataNode
7770 NameNode
8415 Jps
```

## 5. 查看服务状态

  (1) Zookeeper

```shell
[zk: localhost:2181(CONNECTED) 5] get -s /yarn-leader-election/cluster-yarn1/ActiveStandbyElectorLock

cluster-yarn1rm2
cZxid = 0x500000016
ctime = Tue Jun 23 19:33:13 CST 2020
mZxid = 0x500000016
mtime = Tue Jun 23 19:33:13 CST 2020
pZxid = 0x500000016
cversion = 0
dataVersion = 0
aclVersion = 0
ephemeralOwner = 0x300015c9c090001
dataLength = 20
numChildren = 0

[zk: localhost:2181(CONNECTED) 4] get -s /yarn-leader-election/cluster-yarn1/ActiveBreadCrumb

cluster-yarn1rm2
cZxid = 0x500000017
ctime = Tue Jun 23 19:33:13 CST 2020
mZxid = 0x500000017
mtime = Tue Jun 23 19:33:13 CST 2020
pZxid = 0x500000017
cversion = 0
dataVersion = 0
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 20
numChildren = 0
```

（2）查看服务状态

```shell
[atguigu@hadoop102 ~]$ yarn rmadmin -getServiceState rm1
standby
[atguigu@hadoop103 ~]$ yarn rmadmin -getServiceState rm2
active
```

（3）web端查看hadoop102:8088和hadoop103:8088的YARN的状态

![image-20200623193654804](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200623193654.png)



## 6. 关闭集群

### 关闭hadoop ha

```shell
[atguigu@hadoop102 ~]$ mycluster stop
Stopping namenodes on [hadoop102 hadoop103 hadoop104]
Stopping datanodes
Stopping journal nodes [hadoop102 hadoop103 hadoop104]
Stopping ZK Failover Controllers on NN hosts [hadoop102 hadoop103 hadoop104]
Stopping nodemanagers
Stopping resourcemanagers on [ hadoop102 hadoop103]
```

### 关闭Zookeeper

```shell
[atguigu@hadoop102 ~]$ zk.sh stop
=====================  hadoop102  =======================
ZooKeeper JMX enabled by default
Using config: /opt/module/zookeeper-3.5.7/bin/../conf/zoo.cfg
Stopping zookeeper ... STOPPED
=====================  hadoop103  =======================
ZooKeeper JMX enabled by default
Using config: /opt/module/zookeeper-3.5.7/bin/../conf/zoo.cfg
Stopping zookeeper ... STOPPED
=====================  hadoop104  =======================
ZooKeeper JMX enabled by default
Using config: /opt/module/zookeeper-3.5.7/bin/../conf/zoo.cfg
Stopping zookeeper ... STOPPED
```

### 将环境变量更改回来

```shell
[atguigu@hadoop102 ~]$ sudo vim /etc/profile.d/my_env.sh 
[atguigu@hadoop102 ~]$ sudo xsync /etc/profile.d/my_env.sh 
==================== hadoop102 ====================
sending incremental file list

sent 48 bytes  received 12 bytes  40.00 bytes/sec
total size is 383  speedup is 6.38
==================== hadoop103 ====================
sending incremental file list
my_env.sh

sent 478 bytes  received 41 bytes  1,038.00 bytes/sec
total size is 383  speedup is 0.74
==================== hadoop104 ====================
sending incremental file list
my_env.sh

sent 478 bytes  received 41 bytes  1,038.00 bytes/sec
total size is 383  speedup is 0.74
# 103、104也要做一样的操作并检查
[atguigu@hadoop102 ~]$ source /etc/profile.d/my_env.sh 
[atguigu@hadoop102 ~]$ cat /etc/profile.d/my_env.sh 
#JAVA_HOME,PATH
export JAVA_HOME=/opt/module/jdk1.8.0_212
export PATH=$PATH:$JAVA_HOME/bin

#HADOOP_HOME
export HADOOP_HOME=/opt/module/hadoop-3.1.3
export PATH=$PATH:$HADOOP_HOME/bin
export PATH=$PATH:$HADOOP_HOME/sbin

#ZK_HOME
export ZK_HOME=/opt/module/zookeeper-3.5.7
export PATH=$PATH:$ZK_HOME/bin

#HIVE_HOME
export HIVE_HOME=/opt/module/hive
export PATH=$PATH:$HIVE_HOME/bin

```

# 5. HDFS Federation架构设计

联邦架构。

## 1. NameNode架构的局限性

### 1. Namespace（命名空间）的限制

由于NameNode在内存中存储所有的元数据（metadata），因此单个NameNode所能存储的对象（文件+块）数目受到NameNode所在JVM的heap size的限制。50G的heap能够存储20亿（200million）个对象，这20亿个对象支持4000个DataNode，12PB的存储（假设文件平均大小为40MB）。随着数据的飞速增长，存储的需求也随之增长。单个DataNode从4T增长到36T，集群的尺寸增长到8000个DataNode。存储的需求从12PB增长到大于100PB。

### 2. 隔离问题

由于HDFS仅有一个NameNode，无法隔离各个程序，因此HDFS上的一个实验程序就很有可能影响整个HDFS上运行的程序。

### 3. 性能的瓶颈

由于是单个NameNode的HDFS架构，因此整个HDFS文件系统的吞吐量受限于单个NameNode的吞吐量。



## 2. HDFS Federation架构设计

能不能有多个NameNode

| NameNode | NameNode | NameNode          |
| -------- | -------- | ----------------- |
| 元数据   | 元数据   | 元数据            |
| Log      | machine  | 电商数据/话单数据 |

![image-20200623112404757](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200623112404.png)

## 3. HDFS Federation应用思考

目前没人在用。未来可能会用

不同应用可以使用不同NameNode进行数据管理图片业务、爬虫业务、日志审计业务。Hadoop生态系统中，不同的框架使用不同的NameNode进行管理NameSpace。（隔离性）

![image-20200623112431052](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200623112431.png)