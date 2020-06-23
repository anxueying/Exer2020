# 一、Hive基本概念

## 1. 什么是hive

### 1. hive简介

- 基于hadoop的**一个数仓分析工具**（不是数仓）
- 将hdfs上存储的结构化的数据  映射成 一张表
- 用户可以使用hive写HQL(类SQL)来分析数据

### 2. hive本质

hive其实就是hadoop的一个客户端，底层执行引擎就是MapReduce。

**本质: 将用户写的HQL转化成MapReduce模板程序**

- Hive处理的数据存储在HDFS
- Hive分析数据底层的实现是MapReduce
- 执行程序运行在Yarn上



> hive底层不存储任何数据，那它存在哪里呢？
>
> - 数据-->hdfs
> - 元数据：描述数据的数据。-->关系型数据库
>
> hive默认是存进derby（同一时间只允许一个客户端连接），我们一般不用默认的derby来存，一般都会修改为mysql。

## 2. hive的优缺点

### 1. 优点

1. 操作简单，采用类SQL的语法分析数据。容易上手。门槛低，大大降低了大数据分析的难度，通用性高
2. Hive优势在于处理大数据，支持海量数据的分析与计算。
3. Hive支持用户自定义函数，用户可以根据自己的需求来实现自己的函数。

### 2. 缺点

#### 1. 机翻不够智能：Hive的HQL表达能力有限

（1）Hive自动生成的MapReduce作业，通常情况下不够智能化

（2）数据挖掘方面不擅长，由于MapReduce数据处理流程的限制，效率更高的算法却无法实现。(其实不是hive的缺点，是MapReduce的缺点)

#### 2. Hive的效率比较低

（1）Hive的执行延迟比较高，因此Hive常用于数据分析，对实时性要求不高的场合。**适合离线计算。**（底层是MR，其实这是MapReduce的缺点）

（2）Hive调优比较困难，粒度较粗（随着hive版本的更新，这个缺点在减弱）

#### 3. Hive不支持实时查询和行级别更新

（其实也不是hive的缺点，是hdfs的缺点）

hive分析的数据是存储在hdfs上，**hdfs不支持随机写，只支持追加写，所以在hive中不能delete和update，只能select和insert**。

## 3. hive架构原理

**1）用户接口：Client**

CLI（command-line interface）、JDBC/ODBC(jdbc访问hive)、WEBUI（浏览器访问hive）

**2）元数据：Metastore**

元数据包括：表名、表所属的数据库（默认是default）、表的拥有者、列/分区字段、表的类型（是否是外部表）、表的数据所在目录等；

默认存储在自带的derby数据库中，推荐使用MySQL存储Metastore

**3）Hadoop**

使用HDFS进行存储，使用MapReduce进行计算。

![image-20200623143557385](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200623143557.png)

**4）驱动器：Driver**

**（1）解析器（SQL Parser）：语法检查。**

将SQL字符串转换成抽象语法树AST，这一步一般都用第三方工具库完成，比如antlr；对AST进行语法分析，比如表是否存在、字段是否存在、SQL语义是否有误。

**（2）编译器（Physical Plan）：暂时不执行，生成个计划。**

将AST编译生成逻辑执行计划。

**（3）优化器（Query Optimizer）：计划调优。**

对逻辑执行计划进行优化。

**（4）执行器（Execution）：计划 翻译成 MR 在YARN上运行。**

把逻辑执行计划转换成可以运行的物理计划。对于Hive来说，就是MR/Spark。

### 流程

Hive通过给用户提供的一系列交互接口，接收到用户的指令(SQL)，使用自己的Driver，结合元数据(MetaStore)，将这些指令翻译成MapReduce，提交到Hadoop中执行，最后，将执行返回的结果输出到用户交互接口。

![image-20200623144203526](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200623144203.png)



## 4. hive和数据库比较（面试题）

SQL  structure query language

HQL Hive Query language

从结构上来看，Hive 和数据库除了拥有类似的查询语言，再无类似之处。

|          | HQL                                                      | SQL                                                          |
| -------- | -------------------------------------------------------- | ------------------------------------------------------------ |
| 查询语言 | 类SQL                                                    | SQL                                                          |
| 数据更新 | 读多写少，不支持delete,update，不建议insert              | 经常修改                                                     |
| 执行延迟 | 延迟高  1.没有索引，查询扫描整表  2. 底层MapReduce延迟高 | 延迟低，但数据规模大道超过其处理能力的时候，hive的并行计算就体现出优势了 |
|          | 利用MapReduce并行计算，支持大规模数据                    | 小规模数据                                                   |



### 1. 查询语言

专门针对Hive的特性设计了类SQL的查询语言HQL。

熟悉SQL开发的开发者可以很方便的使用Hive进行开发。

### 2. 数据更新

由于Hive是针对数据仓库应用设计的，而**数据仓库的内容是读多写少的**。因此，**Hive中不建议对数据的改写（insert into），所有的数据都是在加载的时候确定好的**。

数据库中的数据通常是需要经常进行修改的，因此可以使用 INSERT INTO … VALUES 添加数据，使用 UPDATE … SET修改数据。

### 3. 执行延迟

Hive 在查询数据的时候，由于没有索引，需要扫描整个表，因此延迟较高。另外一个导致 Hive 执行延迟高的因素是 MapReduce框架。由于MapReduce 本身具有较高的延迟，因此在利用MapReduce 执行Hive查询时，也会有较高的延迟。

数据库的执行延迟较低。当然，这个低是有条件的，即数据规模较小，当数据规模大到超过数据库的处理能力的时候，Hive的并行计算显然能体现出优势。

### 4. 数据规模

由于Hive建立在集群上并可以利用MapReduce进行并行计算，因此可以支持很大规模的数据；

数据库可以支持的数据规模较小。

# 二、 Hive安装

版本：3.1.2

## 1. Hive安装地址

图标解读：

1. 蜂装大象，本质：带不动啊。
2. 大象级的数据进去，蜂蜜级的价值出来。低价值密度-->高价值密度

1）[Hive官网地址](http://hive.apache.org/)

2）[文档查看地址](https://cwiki.apache.org/confluence/display/Hive/GettingStarted)

3）[下载地址](http://archive.apache.org/dist/hive/)

4）[github地址](https://github.com/apache/hive)

## 2. MySQL安装

离线RPM方式安装mysql

### 1. 安装包和环境准备 
```shell
# 把安装包放到/opt/software/目录下，新建mysql-lib文件夹
[atguigu@hadoop102 software]$ mkdir mysql-lib
# 解压到mysql-lib文件夹
[atguigu@hadoop102 software]$ tar -xf mysql-5.7.28-1.el7.x86_64.rpm-bundle.tar  -C ./mysql-lib/
[atguigu@hadoop102 software]$ cd mysql-lib/
# 确认一下解压没有问题
[atguigu@hadoop102 mysql-lib]$ ll
总用量 595272
-rw-r--r--. 1 atguigu atguigu  45109364 9月  30 2019 mysql-community-client-5.7.28-1.el7.x86_64.rpm
-rw-r--r--. 1 atguigu atguigu    318768 9月  30 2019 mysql-community-common-5.7.28-1.el7.x86_64.rpm
-rw-r--r--. 1 atguigu atguigu   7037096 9月  30 2019 mysql-community-devel-5.7.28-1.el7.x86_64.rpm
-rw-r--r--. 1 atguigu atguigu  49329100 9月  30 2019 mysql-community-embedded-5.7.28-1.el7.x86_64.rpm
-rw-r--r--. 1 atguigu atguigu  23354908 9月  30 2019 mysql-community-embedded-compat-5.7.28-1.el7.x86_64.rpm
-rw-r--r--. 1 atguigu atguigu 136837816 9月  30 2019 mysql-community-embedded-devel-5.7.28-1.el7.x86_64.rpm
-rw-r--r--. 1 atguigu atguigu   4374364 9月  30 2019 mysql-community-libs-5.7.28-1.el7.x86_64.rpm
-rw-r--r--. 1 atguigu atguigu   1353312 9月  30 2019 mysql-community-libs-compat-5.7.28-1.el7.x86_64.rpm
-rw-r--r--. 1 atguigu atguigu 208694824 9月  30 2019 mysql-community-server-5.7.28-1.el7.x86_64.rpm
-rw-r--r--. 1 atguigu atguigu 133129992 9月  30 2019 mysql-community-test-5.7.28-1.el7.x86_64.rpm
```
#### 环境清理
```shell
[atguigu@hadoop102 mysql-lib]$ rpm -qa|grep mariadb
mariadb-libs-5.5.64-1.el7.x86_64
[atguigu@hadoop102 mysql-lib]$ sudo rpm -e --nodeps mariadb-libs
[atguigu@hadoop102 mysql-lib]$ rpm -qa|grep mariadb
```


#### 2. 安装Mysql5.7.28
环境确定弄干净了，可以开始安装了，一定要按顺序安装

```shell
[atguigu@hadoop102 mysql-lib]$ sudo rpm -ivh mysql-community-common-5.7.28-1.el7.x86_64.rpm
警告：mysql-community-common-5.7.28-1.el7.x86_64.rpm: 头V3 DSA/SHA1 Signature, 密钥 ID 5072e1f5: NOKEY
准备中...                          ################################# [100%]
正在升级/安装...
   1:mysql-community-common-5.7.28-1.e################################# [100%]
[atguigu@hadoop102 mysql-lib]$ sudo rpm -ivh mysql-community-libs-5.7.28-1.el7.x86_64.rpm
警告：mysql-community-libs-5.7.28-1.el7.x86_64.rpm: 头V3 DSA/SHA1 Signature, 密钥 ID 5072e1f5: NOKEY
准备中...                          ################################# [100%]
正在升级/安装...
   1:mysql-community-libs-5.7.28-1.el7################################# [100%]
[atguigu@hadoop102 mysql-lib]$ sudo rpm -ivh mysql-community-libs-compat-5.7.28-1.el7.x86_64.rpm
警告：mysql-community-libs-compat-5.7.28-1.el7.x86_64.rpm: 头V3 DSA/SHA1 Signature, 密钥 ID 5072e1f5: NOKEY
准备中...                          ################################# [100%]
正在升级/安装...
   1:mysql-community-libs-compat-5.7.2################################# [100%]
[atguigu@hadoop102 mysql-lib]$ sudo rpm -ivh mysql-community-client-5.7.28-1.el7.x86_64.rpm
警告：mysql-community-client-5.7.28-1.el7.x86_64.rpm: 头V3 DSA/SHA1 Signature, 密钥 ID 5072e1f5: NOKEY
准备中...                          ################################# [100%]
正在升级/安装...
   1:mysql-community-client-5.7.28-1.e################################# [100%]
[atguigu@hadoop102 mysql-lib]$ sudo rpm -ivh mysql-community-server-5.7.28-1.el7.x86_64.rpm
警告：mysql-community-server-5.7.28-1.el7.x86_64.rpm: 头V3 DSA/SHA1 Signature, 密钥 ID 5072e1f5: NOKEY
准备中...                          ################################# [100%]
正在升级/安装...
   1:mysql-community-server-5.7.28-1.e################################# [100%]
```
### 3. 清理历史残余文件
看一下配置文件，datadir的地址

```shell
[atguigu@hadoop102 mysql-lib]$ cat /etc/my.cnf
# For advice on how to change settings please see
# http://dev.mysql.com/doc/refman/5.7/en/server-configuration-defaults.html

[mysqld]
#
# Remove leading # and set to the amount of RAM for the most important data
# cache in MySQL. Start at 70% of total RAM for dedicated server, else 10%.
# innodb_buffer_pool_size = 128M
#
# Remove leading # to turn on a very important data integrity option: logging
# changes to the binary log between backups.
# log_bin
#
# Remove leading # to set options mainly useful for reporting servers.
# The server defaults are faster for transactions and fast SELECTs.
# Adjust sizes as needed, experiment to find the optimal values.
# join_buffer_size = 128M
# sort_buffer_size = 2M
# read_rnd_buffer_size = 2M
datadir=/var/lib/mysql
socket=/var/lib/mysql/mysql.sock

# Disabling symbolic-links is recommended to prevent assorted security risks
symbolic-links=0

log-error=/var/log/mysqld.log
pid-file=/var/run/mysqld/mysqld.pid

```

```shell
#进入这个地址 看看是否有历史残余文件
[atguigu@hadoop102 mysql-lib]$ cd /var/lib/mysql
# 没东西就没问题，有就都删了
[atguigu@hadoop102 mysql]$ sudo ls
```
### 4. Mysql初始化并进入
```shell
# 都没问题就初始化
[atguigu@hadoop102 mysql]$ sudo mysqld --initialize --user=mysql
# 看下默认密码，密码是jIepXUxkR4;q
[atguigu@hadoop102 mysql]$ sudo cat /var/log/mysqld.log 
2020-06-23T07:44:46.656866Z 0 [Warning] TIMESTAMP with implicit DEFAULT value is deprecated. Please use --explicit_defaults_for_timestamp server option (see documentation for more details).
2020-06-23T07:44:47.553370Z 0 [Warning] InnoDB: New log files created, LSN=45790
2020-06-23T07:44:47.688139Z 0 [Warning] InnoDB: Creating foreign key constraint system tables.
2020-06-23T07:44:47.757195Z 0 [Warning] No existing UUID has been found, so we assume that this is the first time that this server has been started. Generating a new UUID: 698e4afa-b525-11ea-90d7-000c29a53a1a.
2020-06-23T07:44:47.758157Z 0 [Warning] Gtid table is not ready to be used. Table 'mysql.gtid_executed' cannot be opened.
2020-06-23T07:44:48.083734Z 0 [Warning] CA certificate ca.pem is self signed.
2020-06-23T07:44:48.326868Z 1 [Note] A temporary password is generated for root@localhost: jIepXUxkR4;q

#启动mysql服务
[atguigu@hadoop102 mysql]$ sudo systemctl start mysqld
[atguigu@hadoop102 mysql]$ sudo systemctl status mysqld
● mysqld.service - MySQL Server
   Loaded: loaded (/usr/lib/systemd/system/mysqld.service; enabled; vendor preset: disabled)
   Active: active (running) since 二 2020-06-23 15:46:58 CST; 4s ago
     Docs: man:mysqld(8)
           http://dev.mysql.com/doc/refman/en/using-systemd.html
  Process: 9047 ExecStart=/usr/sbin/mysqld --daemonize --pid-file=/var/run/mysqld/mysqld.pid $MYSQLD_OPTS (code=exited, status=0/SUCCESS)
  Process: 9029 ExecStartPre=/usr/bin/mysqld_pre_systemd (code=exited, status=0/SUCCESS)
 Main PID: 9050 (mysqld)
   CGroup: /system.slice/mysqld.service
           └─9050 /usr/sbin/mysqld --daemonize --pid-file=/var/run/mysqld/mysqld.pid

6月 23 15:46:57 hadoop102 systemd[1]: Starting MySQL Server...
6月 23 15:46:58 hadoop102 systemd[1]: Started MySQL Server.


#进入MySQL 用上面查的默认密码
[atguigu@hadoop102 mysql]$ mysql -uroot -p
Enter password: 
```

```mysql
#设置密码为123456
mysql> set password = password("123456");
```

### 5. 设置root用户的权限
必须设置root用户的权限，允许任意ip连接。否则hive没法连接。

```mysql
mysql> select host,user from user;
+-----------+---------------+
| host      | user          |
+-----------+---------------+
| localhost | mysql.session |
| localhost | mysql.sys     |
| localhost | root          |
+-----------+---------------+
3 rows in set (0.00 sec)

mysql> update mysql.user set host='%' where user='root';
Query OK, 1 row affected (0.00 sec)
Rows matched: 1  Changed: 1  Warnings: 0
# 权限高的在最上面（新版本优化的）
mysql> select host,user from user;
+-----------+---------------+
| host      | user          |
+-----------+---------------+
| %         | root          |
| localhost | mysql.session |
| localhost | mysql.sys     |
+-----------+---------------+
3 rows in set (0.00 sec)

mysql> flush privileges;
Query OK, 0 rows affected (0.00 sec)

mysql> quit
```

## 3. Hive安装部署

**1****）****把****apache-hive-3.1.2-bin.tar.gz****上传到****linux****的****/opt/software****目录下**

**2****）****解压****apache-hive-3.1.2-bin.tar.gz****到****/opt/module/****目录下面**

[atguigu@hadoop102 software]$ tar -zxvf /opt/software/apache-hive-3.1.2-bin.tar.gz -C /opt/module/

**3****）****修改****apache-hive-3.1.2-bin.tar.gz****的名称为****hive**

[atguigu@hadoop102 software]$ mv /opt/module/apache-hive-3.1.2-bin/ /opt/module/hive

**4****）****修改****/etc/profile.d/my_env.sh****，****添加环境变量**

[atguigu@hadoop102 software]$ sudo vim /etc/profile.d/my_env.sh

```
#HIVE_HOME

export HIVE_HOME=/opt/module/hive

export PATH=$PATH:$HIVE_HOME/bin
```



```
[atguigu@hadoop102 module]$ source /etc/profile.d/my_env.sh 
[atguigu@hadoop102 module]$ echo $HIVE_HOME
/opt/module/hive
```

**6****）****解决日志****Jar****包冲突**

hadoop和hive之间的版本冲突

```shell
[atguigu@hadoop102 software]$ mv $HIVE_HOME/lib/log4j-slf4j-impl-2.10.0.jar $HIVE_HOME/lib/log4j-slf4j-impl-2.10.0.jar.bak

[atguigu@hadoop102 lib]$ ll | grep log4j
-rw-rw-r--. 1 atguigu atguigu    63835 4月  15 00:34 log4j-1.2-api-2.10.0.jar
-rw-rw-r--. 1 atguigu atguigu   255485 4月  15 00:24 log4j-api-2.10.0.jar
-rw-rw-r--. 1 atguigu atguigu  1597622 4月  15 00:24 log4j-core-2.10.0.jar
-rw-rw-r--. 1 atguigu atguigu    24173 4月  15 00:24 log4j-slf4j-impl-2.10.0.jar.bak
-rw-rw-r--. 1 atguigu atguigu    32060 4月  15 00:34 log4j-web-2.10.0.jar
```







## 4. Hive元数据配置到MySQL

### 1. 拷贝驱动

将MySQL的JDBC驱动拷贝到Hive的lib目录下

```shell
[atguigu@hadoop102 lib]$ cp /opt/software/mysql-connector-java-5.1.37.jar  ./
```

### 2. 配置Metastore到MySQL

在$HIVE_HOME/conf目录下新建hive-site.xml文件

```shell
[atguigu@hadoop102 conf]$ vim hive-site.xml
```

添加如下内容

- metastore 元数据库
- warehouse 数据仓库
- 10000端口：hive的客户端
- 9083端口：hive作为客户端，连hadoop

```xml
<?xml version="1.0"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<configuration>
    <!-- jdbc连接的URL -->
    <property>
        <name>javax.jdo.option.ConnectionURL</name>
        <value>jdbc:mysql://hadoop102:3306/metastore?useSSL=false</value>
</property>

    <!-- jdbc连接的Driver-->
    <property>
        <name>javax.jdo.option.ConnectionDriverName</name>
        <value>com.mysql.jdbc.Driver</value>
</property>

	<!-- jdbc连接的username-->
    <property>
        <name>javax.jdo.option.ConnectionUserName</name>
        <value>root</value>
    </property>

    <!-- jdbc连接的password -->
    <property>
        <name>javax.jdo.option.ConnectionPassword</name>
        <value>123456</value>
    </property>
    <!-- Hive默认在HDFS的工作目录 -->
    <property>
        <name>hive.metastore.warehouse.dir</name>
        <value>/user/hive/warehouse</value>
    </property>
    
   <!-- Hive元数据存储版本的验证 -->
    <property>
        <name>hive.metastore.schema.verification</name>
        <value>false</value>
    </property>
    <!-- 指定存储元数据要连接的地址 -->
    <property>
        <name>hive.metastore.uris</name>
        <value>thrift://hadoop102:9083</value>
    </property>
    <!-- 指定hiveserver2连接的端口号 -->
    <property>
    <name>hive.server2.thrift.port</name>
    <value>10000</value>
    </property>
   <!-- 指定hiveserver2连接的host -->
    <property>
        <name>hive.server2.thrift.bind.host</name>
        <value>hadoop102</value>
    </property>
    <!-- 元数据存储授权  -->
    <property>
        <name>hive.metastore.event.db.notification.api.auth</name>
        <value>false</value>
    </property>

</configuration>
```

## 5. 启动Hive

### 1. 初始化元数据库

**1）登陆MySQL，新建Hive元数据库**

```mysql
 create database metastore;
```

**2）初始化Hive元数据库**

```shell
# 让hive在metastore中创建初始化的表
[atguigu@hadoop102 software]$ schematool -initSchema -dbType mysql -verbose
```

初始化后，metastore的表信息

![image-20200623164347868](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200623164347.png)

拓展：可以看下hive提供的工具schematool参数是什么意思

```shell
[atguigu@hadoop102 conf]$ schematool --help
usage: schemaTool
 -alterCatalog <arg>                Alter a catalog, requires
                                    --catalogLocation and/or
                                    --catalogDescription parameter as well
 -catalogDescription <arg>          Description of new catalog
 -catalogLocation <arg>             Location of new catalog, required when
                                    adding a catalog
 -createCatalog <arg>               Create a catalog, requires
                                    --catalogLocation parameter as well
 -dbOpts <databaseOpts>             Backend DB specific options
 -dbType <databaseType>             Metastore database type
 -driver <driver>                   driver name for connection
 -dryRun                            list SQL scripts (no execute)
 -fromCatalog <arg>                 Catalog a moving database or table is
                                    coming from.  This is required if you
                                    are moving a database or table.
 -fromDatabase <arg>                Database a moving table is coming
                                    from.  This is required if you are
                                    moving a table.
 -help                              print this message
 -ifNotExists                       If passed then it is not an error to
                                    create an existing catalog
 -info                              Show config and schema details
 -initSchema                        Schema initialization
 -initSchemaTo <initTo>             Schema initialization to a version
 -metaDbType <metaDatabaseType>     Used only if upgrading the system
                                    catalog for hive
 -moveDatabase <arg>                Move a database between catalogs.
                                    Argument is the database name.
                                    Requires --fromCatalog and --toCatalog
                                    parameters as well
 -moveTable <arg>                   Move a table to a different database.
                                    Argument is the table name. Requires
                                    --fromCatalog, --toCatalog,
                                    --fromDatabase, and --toDatabase
                                    parameters as well.
 -passWord <password>               Override config file password
 -servers <serverList>              a comma-separated list of servers used
                                    in location validation in the format
                                    of scheme://authority (e.g.
                                    hdfs://localhost:8000)
 -toCatalog <arg>                   Catalog a moving database or table is
                                    going to.  This is required if you are
                                    moving a database or table.
 -toDatabase <arg>                  Database a moving table is going to.
                                    This is required if you are moving a
                                    table.
 -upgradeSchema                     Schema upgrade
 -upgradeSchemaFrom <upgradeFrom>   Schema upgrade from a version
 -url <url>                         connection url to the database
 -userName <user>                   Override config file user name
 -validate                          Validate the database
 -verbose                           only print SQL statements
```

### 2. 启动metastore和hiveserver2

**Hive 2.x以上版本，要先启动这两个服务，否则会报错：**

```shell
FAILED: HiveException java.lang.RuntimeException: Unable to instantiate org.apache.hadoop.hive.ql.metadata.SessionHiveMetaStoreClient
```

![image-20200623164616465](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200623164616.png)

#### 1. 前台启动（不推荐）

启动后窗口不能再操作，需打开一个新的shell窗口做别的操作

（1）启动metastore

```
[atguigu@hadoop202 hive]$ hive --service metastore 

2020-04-24 16:58:08: Starting Hive Metastore Server 
```

（2）启动 hiveserver2

```
[atguigu@hadoop202 hive]$ hive --service hiveserver2

which: no hbase in (/usr/local/bin:/usr/bin:/usr/local/sbin:/usr/sbin:/opt/module/jdk1.8.0_212/bin:/opt/module/hadoop-3.1.3/bin:/opt/module/hadoop-3.1.3/sbin:/opt/module/hive/bin:/home/atguigu/.local/bin:/home/atguigu/bin)

2020-04-24 17:00:19: Starting HiveServer2 
```

#### 2. 后台启动

##### 1. nohup

放在命令开头，表示不挂起,也就是关闭终端进程也继续保持运行状态

```
 nohup  [xxx命令操作]> file  2>&1 &  
```

比如：

```shell
[atguigu@hadoop202 hive]$ nohup hive --service hiveserver2>log.txt 2>&1 &
```

 错误输出给标准输出，标准输出给log.txt,就不在控制台输出了。而是写到log.txt中。

##### 2. Linux数据流：

- 标准输入

- 标准输出：1

- 错误输出：2

##### 3. > 和 >>

覆盖、追加

##### 4. &

&: 放在命令结尾,表示后台运行

##### 5. 编写hive服务启动脚本

```shell
#$HIVE_HOME/bin目录下写脚本
[atguigu@hadoop102 bin]$ vim hs.sh
```

```shell
#!/bin/bash
HIVE_LOG_DIR=$HIVE_HOME/logs
#判断有没有这个目录，没有就创建
if [ ! -d $HIVE_LOG_DIR ]
then
	mkdir -p $HIVE_LOG_DIR
fi
#检查进程是否运行正常，参数1为进程名，参数2为进程端口
#status
function check_process()
{
	#/dev/null  黑洞 不要的流都放到这里销毁
    pid=$(ps -ef 2>/dev/null | grep -v grep | grep -i $1 | awk '{print $2}')
    ppid=$(netstat -nltp 2>/dev/null | grep $2 | awk '{print $7}' | cut -d '/' -f 1)
    echo $pid
    [[ "$pid" =~ "$ppid" ]] && [ "$ppid" ] && return 0 || return 1
}

#start
function hive_start()
{
    metapid=$(check_process HiveMetastore 9083)
    cmd="nohup hive --service metastore >$HIVE_LOG_DIR/metastore.log 2>&1 &"
    cmd=$cmd" sleep 4; hdfs dfsadmin -safemode wait >/dev/null 2>&1"
    [ -z "$metapid" ] && eval $cmd || echo "Metastroe服务已启动"
    server2pid=$(check_process HiveServer2 10000)
    #看懂这行即可 
    cmd="nohup hive --service hiveserver2 >$HIVE_LOG_DIR/hiveServer2.log 2>&1 &"
    [ -z "$server2pid" ] && eval $cmd || echo "HiveServer2服务已启动"
}

#stop
function hive_stop()
{
    metapid=$(check_process HiveMetastore 9083)
    [ "$metapid" ] && kill $metapid || echo "Metastore服务未启动"
    server2pid=$(check_process HiveServer2 10000)
    [ "$server2pid" ] && kill $server2pid || echo "HiveServer2服务未启动"
}

case $1 in
"start")
    hive_start
    ;;
"stop")
    hive_stop
    ;;
"restart")
    hive_stop
    sleep 2
    hive_start
    ;;
"status")
    check_process HiveMetastore 9083 >/dev/null && echo "Metastore服务运行正常" || echo "Metastore服务运行异常"
    check_process HiveServer2 10000 >/dev/null && echo "HiveServer2服务运行正常" || echo "HiveServer2服务运行异常"
    ;;
*)
    echo Invalid Args!
    echo 'Usage: '$(basename $0)' start|stop|restart|status'
    ;;
esac
```

##### 6. 添加执行权限

```shell
$ chmod +x hs.sh
```

##### 7. 先启动hadoop，再启动Hive后台服务

```shell
#hadoop
$ mycluster start
#hive
$ hs.sh start
```

### 3. HiveJDBC访问

### 4. Hive访问

## 6. Hive常用交互命令

## 7. Hive其他命令操作

## 8. Hive常见属性配置

### 1. Hive运行日志信息配置

### 2. Hive启动jvm堆内存设置

### 3. 参数配置方式



# 三、 Hive数据类型

# 四、DDL数据定义

# 五、DML数据操作

# 六、 查询

# 七、分区表和分桶表

# 八、 函数

# 九、 压缩和存储

# 十、企业级调优（面试重点）

# 十一、Hive实战

# 附录：常见错误和解决方案

## 1. Mysql安装报依赖错误

如果Linux是最小化安装的，在安装mysql-community-server-5.7.28-1.el7.x86_64.rpm时可能会出      现如下错误

```
[atguigu@hadoop102 software]$ sudo rpm -ivh mysql-community-server-5.7.28-1.el7.x86_64.rpm
警告：mysql-community-server-5.7.28-1.el7.x86_64.rpm: 头V3 DSA/SHA1 Signature, 密钥 ID 5072e1f5: NOKEY
错误：依赖检测失败：
        libaio.so.1()(64bit) 被 mysql-community-server-5.7.28-1.el7.x86_64 需要
        libaio.so.1(LIBAIO_0.1)(64bit) 被 mysql-community-server-5.7.28-1.el7.x86_64 需要
        libaio.so.1(LIBAIO_0.4)(64bit) 被 mysql-community-server-5.7.28-1.el7.x86_64 需要
```

通过yum安装缺少的依赖,然后重新安装mysql-community-server-5.7.28-1.el7.x86_64 即可

```
[atguigu@hadoop102 software] yum install -y libaio
```

