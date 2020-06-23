学习框架的思路：

1. what：干什么
2. why：功能，有啥用
3. how：怎么用

# 1. Zookeeper入门

## 1. 概述

分布式。提供协调服务。

功能：

1. 文件系统：负责存储和管理大家都关心的数据
2. 通知机制：通知观察者数据状态的变化

![image-20200622084722636](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200622084722.png)

## 2. 特点

**1）Zookeeper：一个领导者（Leader），多个跟随者（Follower）组成的集群。**

**2）集群中只要有半数以上节点存活，Zookeeper集群就能正常服务。**

3）全局数据一致：每个Server保存一份相同的数据副本，Client无论连接到哪个Server，数据都是一致的。

4）更新请求顺序进行，来自同一个Client的更新请求按其发送顺序依次执行。

5）数据更新原子性，一次数据更新要么成功，要么失败。

6）实时性，在一定时间范围内，Client能读到最新数据。（快）



![image-20200622085315672](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200622085315.png)

## 3. 数据结构

ZooKeeper数据模型的结构与Unix文件系统很类似，整体上可以看作是一棵**树**，每个节点称做一个ZNode。**每一个ZNode默认能够存储1MB的数据**，每个ZNode都可以**通过其路径唯一标识**。



区别：Linux目录本身不能当做一个值。比如`/opt/`目录，目录本身并不能存数据。里面里可以存目录、值，但其本身不能存储。

![image-20200622085531346](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200622085531.png)

## 4. 应用场景

### 1. 统一命名服务

在分布式环境下，经常需要对应用/服务进行统一命名，便于识别。

例如：IP不容易记住，而域名容易记住。

![image-20200622090434965](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200622090435.png)

### 2. 统一配置管理

分布式环境下，配置文件同步非常常见。

（1）一般要求一个集群中，所有节点的配置信息是一致的，比如 Kafka 集群。

（2）对配置文件修改后，希望能够快速同步到各个节点上。

**配置管理可交由ZooKeeper实现。**

（1）可将配置信息写入ZooKeeper上的一个Znode。

（2）各个客户端服务器监听这个Znode。

（3）一旦Znode中的数据被修改，ZooKeeper将通知各个客户端服务器。

![image-20200622090617111](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200622090617.png)

### 3. 服务器动态上下线

![image-20200622090713192](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200622090713.png)

### 4. 软负载均衡

 企业中一般不用这个做。一般使用nginx做负载均衡的代理

![image-20200622090830143](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200622090830.png)

**什么叫负载均衡**

很多服务器干同样的活，但是怎么分配这个量呢？负载均衡就是把客户端的任务请求平均的分配给各个服务器。

## 5. 下载

[官网](https://zookeeper.apache.org/)

在公司装版本时，不要装最新的，也不要搬最老的。大概找个3-4个月之前的。

# 2. Zookeeper安装

    1）启动zk服务器
    zkServer.sh start | status | stop
    服务后台进程名
    QuorumPeerMain
    
    2）启动zk客户端
    zkCli.sh
    zk客户端后台进程名：ZooKeeperMain
    
    3）zk客户端退出
    quit
## 1. 本地模式安装部署

1. 解压到指定目录

   ```shell
   [atguigu@hadoop102 software]$ tar -zxvf zookeeper-3.5.7.tar.gz -C /opt/module/
   ```

2. 文件夹名修改

   ```shell
   mv apache-zookeeper-3.5.7-bin/    zookeeper-3.5.7/
   ```

3. 配置修改

   ```
   mv zoo_sample.cfg zoo.cfg
   ```

   配置文件修改内容

   zookeeper-3.5.7/下新建目录zkData，修改这里即可

   ```
   dataDir=/opt/module/zookeeper-3.5.7/zkData
   ```

4. 操作

   ```shell
   # 启动
   [atguigu@hadoop102 zookeeper-3.5.7]$ bin/zkServer.sh start
   
   #查看
   [atguigu@hadoop102 bin]$ jps
   2353 Jps
   2166 QuorumPeerMain
   
   #状态
   [atguigu@hadoop102 zookeeper-3.5.7]$ bin/zkServer.sh status
   ZooKeeper JMX enabled by default
   Using config: /opt/module/zookeeper-3.5.7/bin/../conf/zoo.cfg
   Mode: standalone
   
   #启动
   [atguigu@hadoop102 zookeeper-3.5.7]$ bin/zkCli.sh
   
   #退出
   [zk: localhost:2181(CONNECTED) 0] quit
   
   #停止
   [atguigu@hadoop102 zookeeper-3.5.7]$ bin/zkServer.sh stop
   ```

## 2. 配置参数解读

```shell
[atguigu@hadoop102 conf]$ cat zoo.cfg 
# The number of milliseconds of each tick
tickTime=2000
# The number of ticks that the initial 
# synchronization phase can take
initLimit=10
# The number of ticks that can pass between 
# sending a request and getting an acknowledgement
syncLimit=5
# the directory where the snapshot is stored.
# do not use /tmp for storage, /tmp here is just 
# example sakes.
dataDir=/opt/module/zookeeper-3.5.7/zkData
# the port at which the clients will connect
clientPort=2181
# the maximum number of client connections.
# increase this if you need to handle more clients
#maxClientCnxns=60
#
# Be sure to read the maintenance section of the 
# administrator guide before turning on autopurge.
#
# http://zookeeper.apache.org/doc/current/zookeeperAdmin.html#sc_maintenance
#
# The number of snapshots to retain in dataDir
#autopurge.snapRetainCount=3
# Purge task interval in hours
# Set to "0" to disable auto purge feature
#autopurge.purgeInterval=1
```

Zookeeper中的配置文件zoo.cfg中参数含义解读如下：

**1 tickTime =2000：通信心跳数，Zookeeper服务器与客户端心跳时间，单位毫秒**

Zookeeper使用的基本时间，服务器之间或客户端与服务器之间维持心跳的时间间隔，也就是每个tickTime时间就会发送一个心跳，时间单位为毫秒。

它用于心跳机制，并且设置最小的session超时时间为两倍心跳时间。(session的最小超时时间是2*tickTime)

**2 initLimit =10：LF初始通信时限**

集群中的Follower跟随者服务器与Leader领导者服务器之间初始连接时能容忍的最多心跳数（tickTime的数量），用它来限定集群中的Zookeeper服务器连接到Leader的时限。

**3 syncLimit =5：LF同步通信时限**

集群中Leader与Follower之间的最大响应时间单位，假如响应超过syncLimit * tickTime，Leader认为Follwer死掉，从服务器列表中删除Follwer。

**4）dataDir：数据文件目录+数据持久化路径**

主要用于保存Zookeeper中的数据。

**5）clientPort =2181：客户端连接端口**

监听客户端连接的端口。

# 3. Zookeeper实战（开发重点）

## 0. 错误解决

分发时遇到的小问题

```
[atguigu@hadoop102 bin]$ sudo xsync /etc/profile.d/my_env.sh
sudo: xsync：找不到命令
```

xsync 应该只放在了用户家目录（atguigu）下面，

atguigu只能分发非root目录下的东西~ 像环境变量这种东西就要root才能分发。

但是，虽然root拥有所有用户文件的权限，但是没有这些用户的环境变量，root有自己的环境变量。

需要把xsync复制一份到/bin/中，也就是root的环境变量中。

![img](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200622105516.png)

这样之后再使用就没有错啦

## 1. 分布式安装部署

与本地模式不同的是，集群模式下配置一个文件myid，这个文件在dataDir目录下，这个文件里面有一个数据就是A的值，Zookeeper启动时读取此文件，拿到里面的数据与zoo.cfg里面的配置信息比较从而判断到底是哪个server。

#### 配置服务器号码

```
[atguigu@hadoop102 zkData]$ cat myid 
2
# 3、4分发后修改
[atguigu@hadoop103 zkData]$ cat myid 
3
[atguigu@hadoop104 zkData]$ cat myid 
4
```

#### zoo.cfg

同样要修改文件名为zoo.cfg，同时修改其中的路径为

```shell
dataDir=/opt/module/zookeeper-3.5.7/zkData
```

需要新增以下内容

```shell
#######################cluster##########################
server.2=hadoop102:2888:3888
server.3=hadoop103:2888:3888
server.4=hadoop104:2888:3888
```

具体完整版见下方

**配置参数解读**

```
server.A=B:C:D
```

- A是一个数字，表示这个是第几号服务器；
- B是这个服务器的地址
- C是这个服务器Follower与集群中的Leader服务器交换信息的端口
- D是万一集群中的Leader服务器挂了，需要一个端口来重新进行选举，选出一个新的Leader，而这个端口就是用来执行选举时服务器相互通信的端口。

```shell
# The number of milliseconds of each tick
tickTime=2000
# The number of ticks that the initial 
# synchronization phase can take
initLimit=10
# The number of ticks that can pass between 
# sending a request and getting an acknowledgement
syncLimit=5
# the directory where the snapshot is stored.
# do not use /tmp for storage, /tmp here is just 
# example sakes.
dataDir=/opt/module/zookeeper-3.5.7/zkData
# the port at which the clients will connect
clientPort=2181
# the maximum number of client connections.
# increase this if you need to handle more clients
#maxClientCnxns=60
#
# Be sure to read the maintenance section of the 
# administrator guide before turning on autopurge.
#
# http://zookeeper.apache.org/doc/current/zookeeperAdmin.html#sc_maintenance
#
# The number of snapshots to retain in dataDir
#autopurge.snapRetainCount=3
# Purge task interval in hours
# Set to "0" to disable auto purge feature
#autopurge.purgeInterval=1
#######################cluster##########################
server.2=hadoop102:2888:3888
server.3=hadoop103:2888:3888
server.4=hadoop104:2888:3888
```

配置好之后，把zookeeper分发后，记得改myid哦！！

#### 配置环境变量

启动时比较方便

```shell
#ZK_HOME
export ZK_HOME=//opt/module/zookeeper-3.5.7
expor PATH=$PATH:$ZK_HOME/bin
```

改完分发给各个集群，然后

![image-20200622103426320](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200622103426.png)

```SHELL
[atguigu@hadoop102 bin]$ source /etc/profile
```

用完之后关掉，后面别误操作！

只起一台的时候，没有到半数，所以不会运行。选举出leader后，后面启动进来的直接就是follower。

```shell
[atguigu@hadoop102 bin]$ zkServer.sh start
ZooKeeper JMX enabled by default
Using config: //opt/module/zookeeper-3.5.7/bin/../conf/zoo.cfg
Starting zookeeper ... STARTED
[atguigu@hadoop102 bin]$ zkServer.sh status
ZooKeeper JMX enabled by default
Using config: //opt/module/zookeeper-3.5.7/bin/../conf/zoo.cfg
Client port found: 2181. Client address: localhost.
Error contacting service. It is probably not running.
```

#### 写一个群起脚本

每次启动实在太麻烦了，写一个群起脚本吧

```shell
#!/bin/bash
if [ $# -lt 1 ]
then
    echo "No Args Input..."
    exit ;
fi

case $1 in
"start")
        for i in hadoop102 hadoop103 hadoop104
    do
        echo "=====================  $i  ======================="
        ssh $i "source /etc/profile && /opt/module/zookeeper-3.5.7/bin/zkServer.sh start"
    done
;;
"stop")
        for i in hadoop102 hadoop103 hadoop104
    do
        echo "=====================  $i  ======================="
        ssh $i "source /etc/profile && /opt/module/zookeeper-3.5.7/bin/zkServer.sh stop"
    done
;;
"status")
        for i in hadoop102 hadoop103 hadoop104
    do
        echo "=====================  $i  ======================="
        ssh $i "source /etc/profile && /opt/module/zookeeper-3.5.7/bin/zkServer.sh status"
    done
;;
*)
    echo "Input Args Error..."
;;
esac
```

每回source是防止ssh到其他节点时没有带前一个节点的环境变量，如果后面的节点没有，则可能报错。保险起见。

```shell
[atguigu@hadoop102 bin]$ chmod +x zk.sh
```

群起集群

```shell
[atguigu@hadoop102 bin]$ zk.sh status
=====================  hadoop102  =======================
ZooKeeper JMX enabled by default
Using config: /opt/module/zookeeper-3.5.7/bin/../conf/zoo.cfg
Client port found: 2181. Client address: localhost.
Error contacting service. It is probably not running.
=====================  hadoop103  =======================
ZooKeeper JMX enabled by default
Using config: /opt/module/zookeeper-3.5.7/bin/../conf/zoo.cfg
Client port found: 2181. Client address: localhost.
Error contacting service. It is probably not running.
=====================  hadoop104  =======================
ZooKeeper JMX enabled by default
Using config: /opt/module/zookeeper-3.5.7/bin/../conf/zoo.cfg
Client port found: 2181. Client address: localhost.
Error contacting service. It is probably not running.
[atguigu@hadoop102 bin]$ zk.sh start
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
[atguigu@hadoop102 bin]$ zk.sh status
=====================  hadoop102  =======================
ZooKeeper JMX enabled by default
Using config: /opt/module/zookeeper-3.5.7/bin/../conf/zoo.cfg
Client port found: 2181. Client address: localhost.
Mode: follower
=====================  hadoop103  =======================
ZooKeeper JMX enabled by default
Using config: /opt/module/zookeeper-3.5.7/bin/../conf/zoo.cfg
Client port found: 2181. Client address: localhost.
Mode: leader
=====================  hadoop104  =======================
ZooKeeper JMX enabled by default
Using config: /opt/module/zookeeper-3.5.7/bin/../conf/zoo.cfg
Client port found: 2181. Client address: localhost.
Mode: follower
```

## 2. 客户端命令行操作

启动客户端

```shell
[atguigu@hadoop103 zookeeper-3.5.7]$ bin/zkCli.sh
```

基本语法

| 命令基本语法 | 功能描述                                                     |
| ------------ | ------------------------------------------------------------ |
| help         | 显示所有操作命令                                             |
| ls path      | 使用 ls 命令来查看当前znode的子节点  -w 监听子节点变化  -s  附加次级信息 |
| create       | 普通创建  -s 含有序列  -e 临时（重启或者超时消失）           |
| get path     | 获得节点的值  -w 监听节点内容变化  -s  附加次级信息          |
| set          | 设置节点的具体值                                             |
| stat         | 查看节点状态                                                 |
| delete       | 删除节点                                                     |
| deleteall    | 递归删除节点                                                 |

```shell
[zk: localhost:2181(CONNECTED) 0] help
ZooKeeper -server host:port cmd args
	addauth scheme auth
	close 
	config [-c] [-w] [-s]
	connect host:port
	#增加节点
	create [-s] [-e] [-c] [-t ttl] path [data] [acl]
	delete [-v version] path
	deleteall path
	delquota [-n|-b] path
	#获取节点的值
	get [-s] [-w] path
	getAcl [-s] path
	history 
	listquota path
	#展示节点下面的子节点
	ls [-s] [-w] [-R] path
	ls2 path [watch]
	printwatches on|off
	#退出
	quit 
	reconfig [-s] [-v version] [[-file path] | [-members serverID=host:port1:port2;port3[,...]*]] | [-add serverId=host:port1:port2;port3[,...]]* [-remove serverId[,...]*]
	redo cmdno
	removewatches path [-c|-d|-a] [-l]
	rmr path
	#设置节点的值
	set [-s] [-v version] path data
	setAcl [-s] [-v version] [-R] path acl
	setquota -n|-b val path
	# 查看节点状态
	stat [-w] path
	sync path
Command not found: Command not found help

[zk: localhost:2181(CONNECTED) 1] ls /
[zookeeper]
[zk: localhost:2181(CONNECTED) 2] ls /zookeeper
[config, quota]
[zk: localhost:2181(CONNECTED) 3] ls /zookeeper/config
[]
[zk: localhost:2181(CONNECTED) 4] get /zookeeper/config
server.2=hadoop102:2888:3888:participant
server.3=hadoop103:2888:3888:participant
server.4=hadoop104:2888:3888:participant
version=0

#ls -s 查看路径的stat信息
[zk: localhost:2181(CONNECTED) 5] ls -s /
[zookeeper]cZxid = 0x0
ctime = Thu Jan 01 08:00:00 CST 1970
mZxid = 0x0
mtime = Thu Jan 01 08:00:00 CST 1970
pZxid = 0x0
cversion = -1
dataVersion = 0
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 0
numChildren = 1

#创建节点  不存在绝对、相对 都要从根节点写
[zk: localhost:2181(CONNECTED) 6] create /sanguo
Created /sanguo
[zk: localhost:2181(CONNECTED) 7] ls /
[sanguo, zookeeper]
[zk: localhost:2181(CONNECTED) 8] get /sanguo
null
#赋予节点的值
[zk: localhost:2181(CONNECTED) 9] delete /sanguo
[zk: localhost:2181(CONNECTED) 10] create /sanguo 'diaochan'
Created /sanguo
[zk: localhost:2181(CONNECTED) 11] get /sanguo
diaochan

[zk: localhost:2181(CONNECTED) 12] create /sanguo/shuguo 'liubei'
Created /sanguo/shuguo
[zk: localhost:2181(CONNECTED) 13] ls /sanguo
[shuguo]
[zk: localhost:2181(CONNECTED) 14] get /sanguo/shuguo
liubei

# get -s 也可以输出stat
[zk: localhost:2181(CONNECTED) 15] get -s /sanguo/shuguo
liubei
cZxid = 0x100000005
ctime = Mon Jun 22 11:02:53 CST 2020
mZxid = 0x100000005
mtime = Mon Jun 22 11:02:53 CST 2020
pZxid = 0x100000005
cversion = 0
dataVersion = 0
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 6
numChildren = 0

[zk: localhost:2181(CONNECTED) 16] create -e /sanguo/wuguo 'zhouyu'
Created /sanguo/wuguo
[zk: localhost:2181(CONNECTED) 18] ls /sanguo
[shuguo, wuguo]

[zk: localhost:2181(CONNECTED) 19] create /sanguo/weiguo
Created /sanguo/weiguo
[zk: localhost:2181(CONNECTED) 20] ls /sanguo
[shuguo, weiguo, wuguo]
[zk: localhost:2181(CONNECTED) 21] create /sanguo/weiguo
Node already exists: /sanguo/weiguo
#生成的序号只会递增，不可修改
[zk: localhost:2181(CONNECTED) 22] create -s /sanguo/weiguo
Created /sanguo/weiguo0000000003
[zk: localhost:2181(CONNECTED) 23] ls /sanguo
[shuguo, weiguo, weiguo0000000003, wuguo]
[zk: localhost:2181(CONNECTED) 24] create -s -e /sanguo/wuguo
Created /sanguo/wuguo0000000004
[zk: localhost:2181(CONNECTED) 25] ls /sanguo
[shuguo, weiguo, weiguo0000000003, wuguo, wuguo0000000004]
# 设置节点的值
[zk: localhost:2181(CONNECTED) 26] set /sanguo/weiguo 'caocao'
[zk: localhost:2181(CONNECTED) 27] get /sanguo/weiguo
weiguo             weiguo0000000003   
[zk: localhost:2181(CONNECTED) 27] get /sanguo/weiguo
caocao
[zk: localhost:2181(CONNECTED) 28] set /sanguo/weiguo 'caopi'
[zk: localhost:2181(CONNECTED) 29] get /sanguo/weiguo
caopi

#监听内容  注册了一个监控器（一次性的）只通知一次就罢工了
#NodeDataChanged
[zk: localhost:2181(CONNECTED) 30] get -w /sanguo/weiguo
caopi
# 在其他客户端更改值，这边就通知了
[zk: localhost:2181(CONNECTED) 31] 
WATCHER::

WatchedEvent state:SyncConnected type:NodeDataChanged path:/sanguo/weiguo

# 只监听子节点的增删，子节点的内容变化并不care，也是临时工
#NodeChildrenChanged
[zk: localhost:2181(CONNECTED) 32] ls -w /sanguo
[shuguo, weiguo, weiguo0000000003, wuguo, wuguo0000000004]
[zk: localhost:2181(CONNECTED) 33] 
WATCHER::

WatchedEvent state:SyncConnected type:NodeChildrenChanged path:/sanguo

# 删除和递归删除
[zk: localhost:2181(CONNECTED) 33] create /sanguo/shuguo/zhangfei
Created /sanguo/shuguo/zhangfei
[zk: localhost:2181(CONNECTED) 34] delete /sanguo/shuguo
Node not empty: /sanguo/shuguo
[zk: localhost:2181(CONNECTED) 35] deleteall /sanguo/shuguo
[zk: localhost:2181(CONNECTED) 36] ls /sanguo
[weiguo, wuguo]

#直接查看stat信息
[zk: localhost:2181(CONNECTED) 38] stat /sanguo
cZxid = 0x100000004
ctime = Mon Jun 22 11:01:58 CST 2020
mZxid = 0x100000012
mtime = Mon Jun 22 11:23:34 CST 2020
pZxid = 0x100000018
cversion = 8
dataVersion = 1
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 5
numChildren = 2

```



## 3. API应用

### 1. IDEA环境搭建

pom.xml

```xml
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.8.2</version>
        </dependency>
        <dependency>
            <groupId>org.apache.zookeeper</groupId>
            <artifactId>zookeeper</artifactId>
            <version>3.5.7</version>
        </dependency>
    </dependencies>
```

\src\main\resources\log4j.properties

```properties
log4j.rootLogger=INFO, stdout  
log4j.appender.stdout=org.apache.log4j.ConsoleAppender  
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout  
log4j.appender.stdout.layout.ConversionPattern=%d %p [%c] - %m%n  
log4j.appender.logfile=org.apache.log4j.FileAppender  
log4j.appender.logfile.File=target/spring.log  
log4j.appender.logfile.layout=org.apache.log4j.PatternLayout  
log4j.appender.logfile.layout.ConversionPattern=%d %p [%c] - %m%n
```

### 2. 创建Zookeeper客户端

```java
    //链接字符串，逗号分隔，中间不能有空格
    private String connectString = "hadoop102:2181,hadoop103:2181,hadoop104:2181";
    //会话超时时间，自定义，一般给5个心跳，即10s
    private int sessionTimeout = 10000; //10s
    //当前客户端默认监听器对象，一般不用
    private ZooKeeper zkClient;


    @Before
    public  void  init() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            /**
             * 匿名实现类的回调函数，这里是默认的，一般不在这里重写，就放在这里就可以了
             * @param event
             */
            @Override
            public void process(WatchedEvent event) {

            }
        });
    }

    @After
    public void after() throws InterruptedException {
        zkClient.close();
    }

```

### 3. 创建子节点

```java
    /**
     * 创建子节点
     */
    @Test
    public void createNode() throws KeeperException, InterruptedException {
        /**
         *      * @param path  创建的节点路径
         *      * @param data  节点的值的字节数组
         *      * @param acl  权限列表  一般给个ZooDefs.Ids.OPEN_ACL_UNSAFE即可，不用过多研究
         *      * @param createMode  创建模式
         *           CreateMode.PERSISTENT    持久
         *           CreateMode.EPHEMERAL     临时  创建临时节点，after运行了，就没了哦！
         *           直接执行不行哦，要设置before、after
         *
         */
        String path = zkClient.create("/atguigu", "shangguigu".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(path);
    }
```

### 4. 获取子节点并监听节点变化

```java
    /**
     * 查看子节点 不监听
     */
    @Test
    public void lsNode() throws KeeperException, InterruptedException {
        //只查看不监听
        List<String> children = zkClient.getChildren("/", false);
        System.out.println(children);
    }

    /**
     * 查看子节点 监听
     */
    @Test
    public void lsAndWatchNode() throws KeeperException, InterruptedException {
        //就调用默认监听方法了，无法打印不同的事件了。所以一般不用这个
        //List<String> children = zkClient.getChildren("/", true);

        List<String> children = zkClient.getChildren("/atguigu", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event.toString());
            }
        });
        System.out.println(children);
        //想看下监听信息，监听10s 也是只监听一次，也是个临时工
        Thread.sleep(10000);
    }
```

### 5. 获取子节点的值并监听节点变化

```java
    /**
     * 获取节点的值
     */
    @Test
    public void getNode() throws KeeperException, InterruptedException {

        Stat stat = zkClient.exists("/atguigu", false);
        if (stat==null){
            System.out.println("节点不存在");
            return;
        }

        /**
         * 在获取节点的值之前要先判断获取的节点是否存在
         *      * @param path the given path
         *      * @param watcher explicit watcher
         *      * @param stat the stat of the node
         */
        byte[] data = zkClient.getData("/atguigu", false, stat);
        String s = new String(data);
        System.out.println(s);
    }

    /**
     * 获取节点的值并监听
     */
    @Test
    public void getAndWatchNode() throws KeeperException, InterruptedException {

        Stat stat = zkClient.exists("/atguigu", false);
        if (stat==null){
            System.out.println("节点不存在");
            return;
        }

        /**
         * 在获取节点的值之前要先判断获取的节点是否存在
         *      * @param path the given path
         *      * @param watcher explicit watcher
         *      * @param stat the stat of the node
         */
        byte[] data = zkClient.getData("/atguigu", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                //真实企业中不同的事件是需要做不同的操作，发不同的消息。所以不用true参数，这是业务逻辑，不是语法上的要求
                System.out.println(event.toString());
            }
        }, stat);
        String s = new String(data);
        System.out.println(s);

        //想看下监听信息，监听10s 也是只监听一次，也是个临时工
        Thread.sleep(10000);
    }
```

### 6. 判断节点是否存在，设置子节点的值

```java
    /**
     * 设置节点的值
     */
    @Test
    public void setNode() throws KeeperException, InterruptedException {
        //判断一下 做一个优雅知性的coder
        Stat stat = zkClient.exists("/atguigu", false);
        if (stat==null){
            System.out.println("节点不存在");
            return;
        }
        //if the given version is-1, it matches any node's versions 但最好不要这么搞
        zkClient.setData("/atguigu", "bigdata0421".getBytes(), stat.getVersion());
    }
```

### 7. 删除节点

#### delete

```java
    /**
     * 删除没有子节点的节点
     */
    @Test
    public void delNode() throws KeeperException, InterruptedException {
        //判断一下 做一个优雅知性的coder
        Stat stat = zkClient.exists("/atguigu", false);
        if (stat==null){
            System.out.println("节点不存在");
            return;
        }

        //一样要版本号，-1就啥版本都删
        zkClient.delete("/atguigu", stat.getVersion());
    }
```

#### deleteall

```java
   /**
     * 递归删除节点
     */
    @Test
    public void delAllNodeTest() throws KeeperException, InterruptedException {
        deleteAllNode("/atguigu", zkClient);
    }

    public void deleteAllNode(String path,ZooKeeper zkClient) throws KeeperException, InterruptedException {
        //判断一下 做一个优雅知性的coder
        Stat stat = zkClient.exists(path, false);
        if (stat==null){
            System.out.println("节点不存在");
            return;
        }

        List<String> children = zkClient.getChildren(path, false);
        if (children.isEmpty()){
            //说明传入的节点没有子节点，可以直接删除
            zkClient.delete(path, stat.getVersion());
        }else {
            for (String child : children) {
                //删除子节点，调用本方法递归，子节点路径是 path+"/"+child
                deleteAllNode(path+"/"+child, zkClient);
            }

            zkClient.delete(path, stat.getVersion());
        }
    }
```

# 4. Zookeeper内部原理

## 1. 节点类型

创建znode时设置顺序标识，znode名称后会附加一个值，**顺序号是一个单调递增的计数器，由父节点维护。**

**注意：在分布式系统中，顺序号可以被用于为所有的事件进行全局排序，这样客户端可以通过顺序号推断事件的顺序**

### 1. 持久（Persistent）节点

客户端和服务器端断开连接后，创建的节点不删除。

（1）持久化目录节点

（2）持久化顺序编号目录节点：Zookeeper给该节点名称进行顺序编号

### 2. 短暂（Ephemeral）节点

客户端和服务器端断开连接后，创建的节点自己删除

（3）临时目录节点

（4）临时顺序编号目录节点：Zookeeper给该节点名称进行顺序编号

## 2. Stat结构体

```shell
# 直接查看stat信息
[zk: localhost:2181(CONNECTED) 38] stat /sanguo
# 创建节点的事务zxid
cZxid = 0x100000004
#底层是时间戳，只是新版本升级后翻译了一下 创建时间
ctime = Mon Jun 22 11:01:58 CST 2020
# 最后更新的事务zxid 不一定是连续的哦 但看大小即可确定顺序
mZxid = 0x100000012
# 底层是时间戳，只是新版本升级后翻译了一下 最后修改的时间
mtime = Mon Jun 22 11:23:34 CST 2020
# 最后更新的子节点的zxid，记录当前节点下子节点变化的记录id，子节点变化后会递增
pZxid = 0x100000018
# 子节点变化号 ：修改次数
cversion = 8
# 数据版本号 当前节点的数据 Zookeeper用的是乐观锁
dataVersion = 1
aclVersion = 0
# 重要：如果是临时节点，这个是znode拥有者的session id（当前客户端的会话id）。如果不是临时节点则是0。
ephemeralOwner = 0x0
# 重要：znode的数据长度
dataLength = 5
# 重要：znode的子节点数量，包含临时节点的数量
numChildren = 2
```

**zxid**

Zookeeper自己维护的id，我们也看不到。只能新创建一个节点看一下stat看看到几了。

- 每次修改ZooKeeper状态都会收到一个zxid形式的时间戳，也就是ZooKeeper事务ID。

- 事务具有原子性。成功就成功，失败就失败。不会部分成功部分失败。

- 事务ID是ZooKeeper中所有修改总的次序。每个修改都有唯一的zxid，如果zxid1小于zxid2，那么zxid1在zxid2之前发生。

**悲观锁/乐观锁**

改节点值的一瞬间，另外一个客户端对其进行获取，1秒前还是A，1秒后变成B了。。。

**乐观锁**：在每一个数据都加个版本号，这样变成B了，一看版本号，就发现不是自己撞鬼了。

**悲观锁：**全部锁住，我改这个值，别人都别获取，大家手举起来！

## 3. 监听器原理（面试重点）



![image-20200622141137841](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200622141138.png)

## 4. 选举机制（面试重点）

### 半数机制

集群中半数以上机器存活，集群可用。所以Zookeeper适合安装奇数台服务器。

![image-20200622142419653](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200622142419.png)

### 投票原则

1. LOOKING状态的服务器可以再次投票，其他状态不可。
2. 自私原则：第一轮投票投自己
3. 墙头草原则：交换选票信息后发现，我投的人id小，他投的人id大（先看zxid，后看myid），我跟他投一样的。

为啥大的厉害！

因为zxid越大，说明当前服务器的数据越完整。

### 选举情况

（默认zxid都相等）

1. 每个服务器逐一启动：肯定是第3个当leader

   > （1）服务器1启动，发起一次选举。服务器1投自己一票。此时服务器1票数一票，不够半数以上（3票），1/5=0.2<0.5;选举无法完成，服务器1状态保持为LOOKING；
   >
   > （2）服务器2启动，再发起一次选举。服务器1和2分别投自己一票并交换选票信息：此时服务器1发现服务器2的ID比自己目前投票推举的（服务器1）大，更改选票为推举服务器2。此时服务器1票数0票，服务器2票数2票，2/5=0.4<0.5;没有半数以上结果，选举无法完成，服务器1，2状态保持LOOKING
   >
   > （3）服务器3启动，发起一次选举。第一轮都投自己，然后交换选票信息，第二轮此时服务器1和2都会更改选票为服务器3。此次投票结果：服务器1为0票，服务器2为0票，服务器3为3票。此时服务器3的票数已经超过半数，3/5=0/6>0/5;服务器3当选Leader。服务器1，2更改状态为FOLLOWING，服务器3更改状态为LEADING；
   >
   > （4）服务器4启动，发起一次选举。此时服务器1，2，3已经不是LOOKING状态，不会更改选票信息。交换选票信息结果：服务器3为3票，服务器4为1票。此时服务器4服从多数，更改选票信息为服务器3，并更改状态为FOLLOWING；
   >
   > （5）服务器5启动，同4一样当小弟。

2. 所有服务器几乎同时启动：肯定是第5个当leader

   > 直接发起投票
   >
   > 第一轮：自己投自己，交换选票信息。1、2、3、4机器发现5最厉害，改投5号
   >
   > 第二轮：5得到5票。超过半数，成为leader

看日志

```shell
[atguigu@hadoop102 logs]$ tail zookeeper-atguigu-server-hadoop102.out 
2020-06-22 10:37:28,274 [myid:2] - INFO  [hadoop102/192.168.1.102:3888:QuorumCnxManager$Listener@924] - Received 
2020-06-22 10:37:28,276 [myid:2] - INFO  [WorkerReceiver[myid=2]:FastLeaderElection@679] - Notification: 2 (messa (n.peerEPoch), FOLLOWING (my state)0 (n.config version)
2020-06-22 10:37:31,449 [myid:2] - INFO  [NIOWorkerThread-1:FourLetterCommands@234] - The list of known four lett445044=crst, 1936880500=srst, 1701738089=envi, 1668247142=conf, -720899=telnet close, 2003003507=wchs, 2003003504683435=gtmk, 1937010027=stmk}]
2020-06-22 10:37:31,449 [myid:2] - INFO  [NIOWorkerThread-1:FourLetterCommands@235] - The list of enabled four le
2020-06-22 10:37:31,449 [myid:2] - INFO  [NIOWorkerThread-1:NIOServerCnxn@518] - Processing srvr command from /12
2020-06-22 10:56:10,546 [myid:2] - WARN  [QuorumPeer[myid=2](plain=[0:0:0:0:0:0:0:0]:2181)(secure=disabled):Follo
2020-06-22 10:56:10,547 [myid:2] - INFO  [SyncThread:2:FileTxnLog@218] - Creating new log file: log.100000001
2020-06-22 11:08:21,303 [myid:2] - INFO  [NIOWorkerThread-12:QuorumZooKeeperServer@157] - Submitting global close
2020-06-22 14:16:19,824 [myid:2] - WARN  [NIOWorkerThread-10:NIOServerCnxn@366] - Unable to read additional data 
2020-06-22 14:44:11,520 [myid:2] - INFO  [NIOWorkerThread-9:QuorumZooKeeperServer@157] - Submitting global closeS
[atguigu@hadoop102 logs]$ pwd
/opt/module/zookeeper-3.5.7/logs
```

2号改投了（只有三个节点）

![image-20200622144942613](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200622144942.png)

## 5. 写数据流程

重点是：半数以上的成功信息收到后，再发送写的命令。

![image-20200622151656005](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200622151656.png)

    1）客户端连接zk集群的任意一台机器，发送写请求
    2）如果客户端连接的服务器不是leader。则连接的这台服务器，会将写请求发送给leader
    3）当leader接收到写请求以后，会将当次写操作构造成一个事务，对应一个zxid，然后将这个写的操作广播给所有服务器
    4）当各台服务器接收到leader发送的写请求后，会将此次写操作维护到一个待写列表里，然后给leader回复可以写
    5）leader收到半数以上太服务器回复的可以写的信息后，会再次广播，执行的操作
    6）此时各个服务器真正的执行写操作
    7）最后由客户端连接的那台服务器通知客户端，写操作成功。

# 5. 企业面试真题

## 1. 简述Zookeeper的选举机制

**投票原则：**

- **自私原则：**第一轮投票肯定投自己
- **墙头草原则:**第一轮投票完以后，大家会交换选票信息，这个时候会把票改投给那个厉害的人

> 厉害怎么判断？（myid,zxid）
>
> - 先判断zxid，zxid大的赢，因为zxid越大，说明当前服务器的数据越完整
> - 如果zxid相等，那就看myid

**情况1：服务器一台一台的按照顺序启动**

机器1启动：会发起一轮选举，机器1把票投给自己，然后发现选票个数不够集群个数的半数以上，机器1选举失败，然后状态改为LOOKING

机器2启动：会发起一轮选举，第一轮投票，机器1投自己，机器2投自己，然后会交换选票，这时，机器1发现机器2myid比自己大，更改选票信息
将自己的票改投给机器2，然后机器2发现自己手里得了两票，不够集群半数机制，选举失败，两台机器重新变为looking

机器3启动：会发起一轮选举，第一轮投票，各自投各自（自私原则），然后交换选票信息，机器1和2发现机器3更厉害（zxid一直，但是人家的myid更大），
此时机器1和2将自己的选票改为3，机器3得票3票，超过了集群个数的半数，机器3当选leader，把自己的状态改为LEADING，机器1和2FOLLOWING

机器4启动；会发起一轮选举，第一轮投票，机器4投自己，但是因为机器1，2，3的状态已经不再是LOOKING，因此机器1 2 3不再更改选票信息，机器4少数
服从多数，将自己的状态改为FOLLOWING

机器5启动：会发起一轮选举，第一轮投票，机器5投给自己，但是因为机器1 2 3 4的状态不再是LOOKING，不会更改自己的选票信息，机器5少数服从多数
将自己的状态改为FOLLOWING。

**情况2：五台机器一起启动**
五台机器发起一轮选举，第一次投票，各自投各自（自私原则），然后交换选票信息，结果 1 2 3 4号机器发现5号机器最厉害，就会
把自己的选票信息改投5号，5号已绝对优势当选leader

## 2. Zookeeper的监听原理是什么

1. 创建zk客户端的时候，会对应着两个线程  一个叫connect线程，另一个叫listener线程
     a:connnect线程负责客户端跟服务器通信
     b:listener线程负责监听zookeeper服务器发过来的消息，接收到消息以后，会调用自己实现的一个回调方法 process();

2. 当zk客户端在zk服务器中注册的监听事件发生后，zk服务会通知到listener线程，linstener线程调用回调方法

## 3. Zookeeper的部署方式有哪几种？

（1）部署方式单机模式、集群模式

（2）角色：Leader和Follower

（3）集群最少需要机器数：3

## 4. ZooKeeper的常用命令

ls create get delete set…

# 6. Zookeeper源码

Zookeeper构造器

```java
  /**
     * To create a ZooKeeper client object, the application needs to pass a
     * connection string containing a comma separated list of host:port pairs,
     * each corresponding to a ZooKeeper server.
     * <p>
     * Session establishment is asynchronous. This constructor will initiate
     * connection to the server and return immediately - potentially (usually)
     * before the session is fully established. The watcher argument specifies
     * the watcher that will be notified of any changes in state. This
     * notification can come at any point before or after the constructor call
     * has returned.
     * <p>
     * The instantiated ZooKeeper client object will pick an arbitrary server
     * from the connectString and attempt to connect to it. If establishment of
     * the connection fails, another server in the connect string will be tried
     * (the order is non-deterministic, as we random shuffle the list), until a
     * connection is established. The client will continue attempts until the
     * session is explicitly closed.
     * <p>
     * Added in 3.2.0: An optional "chroot" suffix may also be appended to the
     * connection string. This will run the client commands while interpreting
     * all paths relative to this root (similar to the unix chroot command).
     * <p>
     * For backward compatibility, there is another version
     * {@link #ZooKeeper(String, int, Watcher, boolean)} which uses default
     * {@link StaticHostProvider}
     *
     * @param connectString
     *            comma separated host:port pairs, each corresponding to a zk
     *            server. e.g. "127.0.0.1:3000,127.0.0.1:3001,127.0.0.1:3002" If
     *            the optional chroot suffix is used the example would look
     *            like: "127.0.0.1:3000,127.0.0.1:3001,127.0.0.1:3002/app/a"
     *            where the client would be rooted at "/app/a" and all paths
     *            would be relative to this root - ie getting/setting/etc...
     *            "/foo/bar" would result in operations being run on
     *            "/app/a/foo/bar" (from the server perspective).
     * @param sessionTimeout
     *            session timeout in milliseconds
     * @param watcher
     *            a watcher object which will be notified of state changes, may
     *            also be notified for node events
     * @param canBeReadOnly
     *            (added in 3.4) whether the created client is allowed to go to
     *            read-only mode in case of partitioning. Read-only mode
     *            basically means that if the client can't find any majority
     *            servers but there's partitioned server it could reach, it
     *            connects to one in read-only mode, i.e. read requests are
     *            allowed while write requests are not. It continues seeking for
     *            majority in the background.
     * @param aHostProvider
     *            use this as HostProvider to enable custom behaviour.
     * @param clientConfig
     *            (added in 3.5.2) passing this conf object gives each client the flexibility of
     *            configuring properties differently compared to other instances
     * @throws IOException
     *             in cases of network failure
     * @throws IllegalArgumentException
     *             if an invalid chroot path is specified
     */
    public ZooKeeper(String connectString, int sessionTimeout, Watcher watcher,
            boolean canBeReadOnly, HostProvider aHostProvider,
            ZKClientConfig clientConfig) throws IOException {
        LOG.info("Initiating client connection, connectString=" + connectString
                + " sessionTimeout=" + sessionTimeout + " watcher=" + watcher);

        if (clientConfig == null) {
            clientConfig = new ZKClientConfig();
        }
        this.clientConfig = clientConfig;
        watchManager = defaultWatchManager();
        watchManager.defaultWatcher = watcher;
        ConnectStringParser connectStringParser = new ConnectStringParser(
                connectString);
        hostProvider = aHostProvider;

        cnxn = createConnection(connectStringParser.getChrootPath(),
                hostProvider, sessionTimeout, this, watchManager,
                getClientCnxnSocket(), canBeReadOnly);
        cnxn.start();
    }
    
    
    public void start() {
        sendThread.start();
        eventThread.start();
    }

```

