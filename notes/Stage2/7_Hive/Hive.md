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
| 数据规模 | 利用MapReduce并行计算，支持大规模数据                    | 小规模数据                                                   |



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

1）把apache-hive-3.1.2-bin.tar.gz上传到linux的/opt/software目录下

2）解压apache-hive-3.1.2-bin.tar.gz到/opt/module/目录下面

```shell
[atguigu@hadoop102 software]$ tar -zxvf /opt/software/apache-hive-3.1.2-bin.tar.gz -C /opt/module/
```

3）修改apache-hive-3.1.2-bin.tar.gz的名称为hive

```shell
[atguigu@hadoop102 module]$ mv apache-hive-3.1.2-bin/   hive
```

4）修改/etc/profile.d/my_env.sh，添加环境变量

```shell
[atguigu@hadoop102 software]$ sudo vim /etc/profile.d/my_env.sh
```



```shell
#HIVE_HOME
export HIVE_HOME=/opt/module/hive
export PATH=$PATH:$HIVE_HOME/bin
```

5）重新加载环境变量，并确认成功

```shell
[atguigu@hadoop102 module]$ source /etc/profile.d/my_env.sh 
# 输出/opt/module/hive即为成功
[atguigu@hadoop102 module]$ echo $HIVE_HOME
/opt/module/hive
```

6）解决日志Jar包冲突

hadoop和hive之间的log4j版本冲突，要把hive中lib中的jar包名字改一下，无法启用即可。就直接在文件名后加.bak即可。

```shell
[atguigu@hadoop102 lib]$ mv log4j-slf4j-impl-2.10.0.jar log4j-slf4j-impl-2.10.0.jar.bak

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
#不加log.txt nohup会自动生成一个nohup.out 所有服务控制台输入都会在这里，不推荐。。因为这样的日志太乱了
[atguigu@hadoop202 hive]$ nohup hive --service hiveserver2 2>&1 &
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

启动并查看状态

```shell
[atguigu@hadoop102 ~]$ hs.sh start
#马上看status是异常的，要等个1-2分钟
[atguigu@hadoop102 ~]$ hs.sh status
Metastore服务运行正常
HiveServer2服务运行异常
[atguigu@hadoop102 bin]$ hs.sh status
Metastore服务运行正常
HiveServer2服务运行正常
```



### 3. HiveJDBC访问

#### 自带beeline（客户端）访问

需要连hiveserver2

```shell
#必须用atguigu访问，因为jdbc的管理员是atguigu，不然没权限
[atguigu@hadoop102 bin]$ beeline -u jdbc:hive2://hadoop102:10000 -n atguigu
Connecting to jdbc:hive2://hadoop102:10000
Connected to: Apache Hive (version 3.1.2)
Driver: Hive JDBC (version 3.1.2)
Transaction isolation: TRANSACTION_REPEATABLE_READ
Beeline version 3.1.2 by Apache Hive
#进入到hadoop客户端
0: jdbc:hive2://hadoop102:10000> show databases;
INFO  : Compiling command(queryId=atguigu_20200624090544_e785ed6a-d1a1-404c-91ab-e6b85449c49d): show databases
INFO  : Concurrency mode is disabled, not creating a lock manager
INFO  : Semantic Analysis Completed (retrial = false)
INFO  : Returning Hive schema: Schema(fieldSchemas:[FieldSchema(name:database_name, type:string, comment:from deserializer)], properties:null)
INFO  : Completed compiling command(queryId=atguigu_20200624090544_e785ed6a-d1a1-404c-91ab-e6b85449c49d); Time taken: 0.608 seconds
INFO  : Concurrency mode is disabled, not creating a lock manager
INFO  : Executing command(queryId=atguigu_20200624090544_e785ed6a-d1a1-404c-91ab-e6b85449c49d): show databases
INFO  : Starting task [Stage-0:DDL] in serial mode
INFO  : Completed executing command(queryId=atguigu_20200624090544_e785ed6a-d1a1-404c-91ab-e6b85449c49d); Time taken: 0.02 seconds
INFO  : OK
INFO  : Concurrency mode is disabled, not creating a lock manager
+----------------+
| database_name  |
+----------------+
| default        |
+----------------+
1 row selected (1.099 seconds)
#退出
0: jdbc:hive2://hadoop102:10000> !quit
```

### 4. Hive访问

#### 自带hive（脚本）访问

不连hiveserver2

```shell
#进入
[atguigu@hadoop102 ~]$ hive
which: no hbase in (/usr/local/bin:/usr/bin:/usr/local/sbin:/usr/sbin:/opt/module/jdk1.8.0_212/bin:/opt/module/hadoop-3.1.3/bin:/opt/module/hadoop-3.1.3/sbin:/opt/module/zookeeper-3.5.7/bin:/opt/module/hive/bin:/home/atguigu/.local/bin:/home/atguigu/bin)
Hive Session ID = ede63f3a-17ad-43c5-873d-a5e21b9d2943

Logging initialized using configuration in jar:file:/opt/module/hive/lib/hive-common-3.1.2.jar!/hive-log4j2.properties Async: true
Hive-on-MR is deprecated in Hive 2 and may not be available in the future versions. Consider using a different execution engine (i.e. spark, tez) or using Hive 1.X releases.
Hive Session ID = 41e6acb4-0400-4f03-8a91-14e0428967ca
hive> show databases;
OK
default
Time taken: 0.4 seconds, Fetched: 1 row(s)
#退出
hive> quit;
```

**优化：打印 当前库 和 表头**

```shell
[atguigu@hadoop102 conf]$ vim hive-site.xml 
[atguigu@hadoop102 conf]$ hs.sh restart
[atguigu@hadoop102 conf]$ hs.sh status
Metastore服务运行正常
HiveServer2服务运行正常
```

在hive-site.xml中加入如下两个配置: 

```xml
<property>
    <name>hive.cli.print.header</name>
    <value>true</value>
    <description>Whether to print the names of the columns in query output.</description>
  </property>
   <property>
    <name>hive.cli.print.current.db</name>
    <value>true</value>
    <description>Whether to include the current database in the Hive prompt.</description>
  </property>
```

更新了配置要重启hive服务，再次进入hive

```shell
# default 当前库
hive (default)> show databases;
OK
# 表头
database_name
default
Time taken: 0.403 seconds, Fetched: 1 row(s)
```

### 5. 历史记录

#### .beeline/history

beeline的history在这里，quit之后会都写在这里

#### .hivehistory

通过hive脚本登录的各种语句，quit之后会都写在这里

![image-20200624095611132](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200624095611.png)

## 6. Hive常用交互命令

使用场景：脚本里一般会这样用，因为不能人为的进入hive客户端写sql

### help

```shell
[atguigu@hadoop102 conf]$ hive -help
which: no hbase in (/usr/local/bin:/usr/bin:/usr/local/sbin:/usr/sbin:/opt/module/jdk1.8.0_212/bin:/opt/module/hadoop-3.1.3/bin:/opt/module/hadoop-3.1.3/sbin:/opt/module/zookeeper-3.5.7/bin:/opt/module/hive/bin:/home/atguigu/.local/bin:/home/atguigu/bin)
Hive Session ID = d371508f-4fc2-400d-9f7d-defacdd5ae2e
usage: hive
 -d,--define <key=value>          Variable substitution to apply to Hive
                                  commands. e.g. -d A=B or --define A=B
    --database <databasename>     Specify the database to use
 -e <quoted-query-string>         SQL from command line
 -f <filename>                    SQL from files
 -H,--help                        Print help information
    --hiveconf <property=value>   Use value for given property
    --hivevar <key=value>         Variable substitution to apply to Hive
                                  commands. e.g. --hivevar A=B
 -i <filename>                    Initialization SQL file
 -S,--silent                      Silent mode in interactive shell
 -v,--verbose                     Verbose mode (echo executed SQL to the
                                  console)

```

建表，插入数据

```sql
#创建一个表
hive (default)> create table student(id int,name string);
OK
Time taken: 1.273 seconds
hive (default)> show tables;
OK
tab_name
student
Time taken: 0.095 seconds, Fetched: 1 row(s)
hive (default)> select * from student;
OK
student.id	student.name
Time taken: 1.229 seconds
#插入数据，真的好慢
hive (default)> insert into table student values(1,'zhangsan');
Query ID = atguigu_20200624091749_ccaf9c2a-10c2-45d2-9f40-7d2461a2dd36
Total jobs = 3
Launching Job 1 out of 3
Number of reduce tasks determined at compile time: 1
In order to change the average load for a reducer (in bytes):
  set hive.exec.reducers.bytes.per.reducer=<number>
In order to limit the maximum number of reducers:
  set hive.exec.reducers.max=<number>
In order to set a constant number of reducers:
  set mapreduce.job.reduces=<number>
Starting Job = job_1592960358915_0001, Tracking URL = http://hadoop103:8088/proxy/application_1592960358915_0001/
Kill Command = /opt/module/hadoop-3.1.3/bin/mapred job  -kill job_1592960358915_0001
Hadoop job information for Stage-1: number of mappers: 1; number of reducers: 1
2020-06-24 09:17:58,298 Stage-1 map = 0%,  reduce = 0%
2020-06-24 09:18:02,483 Stage-1 map = 100%,  reduce = 0%, Cumulative CPU 4.0 sec
2020-06-24 09:18:06,569 Stage-1 map = 100%,  reduce = 100%, Cumulative CPU 5.35 sec
MapReduce Total cumulative CPU time: 5 seconds 350 msec
Ended Job = job_1592960358915_0001
Stage-4 is selected by condition resolver.
Stage-3 is filtered out by condition resolver.
Stage-5 is filtered out by condition resolver.
Moving data to directory hdfs://hadoop102:9820/user/hive/warehouse/student/.hive-staging_hive_2020-06-24_09-17-49_291_71007690541847048-1/-ext-10000
Loading data to table default.student
MapReduce Jobs Launched: 
Stage-Stage-1: Map: 1  Reduce: 1   Cumulative CPU: 5.35 sec   HDFS Read: 15275 HDFS Write: 245 SUCCESS
Total MapReduce CPU Time Spent: 5 seconds 350 msec
OK
col1	col2
Time taken: 18.891 seconds
```

### -e sql语句

```shell
# 直接跟sql语句
[atguigu@hadoop102 datas]$ hive -e 'select * from student;'
which: no hbase in (/usr/local/bin:/usr/bin:/usr/local/sbin:/usr/sbin:/opt/module/jdk1.8.0_212/bin:/opt/module/hadoop-3.1.3/bin:/opt/module/hadoop-3.1.3/sbin:/opt/module/zookeeper-3.5.7/bin:/opt/module/hive/bin:/home/atguigu/.local/bin:/home/atguigu/bin)
Hive Session ID = ba3a8903-ebb4-4637-89d0-15389de0fb55

Logging initialized using configuration in jar:file:/opt/module/hive/lib/hive-common-3.1.2.jar!/hive-log4j2.properties Async: true
Hive Session ID = a76428c9-d789-48ee-aab1-5bf34286fc22
OK
student.id	student.name
1	zhangsan
Time taken: 1.647 seconds, Fetched: 1 row(s)
```

### -f sql文件

```shell
# 用文件里放命令
[atguigu@hadoop102 datas]$ hive -f ./stu.sql 
which: no hbase in (/usr/local/bin:/usr/bin:/usr/local/sbin:/usr/sbin:/opt/module/jdk1.8.0_212/bin:/opt/module/hadoop-3.1.3/bin:/opt/module/hadoop-3.1.3/sbin:/opt/module/zookeeper-3.5.7/bin:/opt/module/hive/bin:/home/atguigu/.local/bin:/home/atguigu/bin)
Hive Session ID = 8fc4d533-e515-4718-8db6-5d667c734615

Logging initialized using configuration in jar:file:/opt/module/hive/lib/hive-common-3.1.2.jar!/hive-log4j2.properties Async: true
Hive Session ID = ef49eca7-17bb-4544-a212-bf1899c213d3
OK
student.id	student.name
1	zhangsan
Time taken: 1.621 seconds, Fetched: 1 row(s)
# 把结果保存
[atguigu@hadoop102 datas]$ hive -f ./stu.sql > stu.txt
which: no hbase in (/usr/local/bin:/usr/bin:/usr/local/sbin:/usr/sbin:/opt/module/jdk1.8.0_212/bin:/opt/module/hadoop-3.1.3/bin:/opt/module/hadoop-3.1.3/sbin:/opt/module/zookeeper-3.5.7/bin:/opt/module/hive/bin:/home/atguigu/.local/bin:/home/atguigu/bin)
Hive Session ID = 63dfeb0f-a692-4585-9ffe-e3cc3c67bb63

Logging initialized using configuration in jar:file:/opt/module/hive/lib/hive-common-3.1.2.jar!/hive-log4j2.properties Async: true
Hive Session ID = 7470076e-24ad-4bde-9005-b441d09da248
OK
Time taken: 1.698 seconds, Fetched: 1 row(s)
# 看一下结果
[atguigu@hadoop102 datas]$ cat stu.txt 
student.id	student.name
1	zhangsan
```

### 查看hdfs路径

```shell
#hadoop的操作
[atguigu@hadoop102 datas]$ hadoop fs -ls /
Found 4 items
drwxr-xr-x   - atguigu supergroup          0 2020-06-16 19:23 /input
drwxr-xr-x   - atguigu supergroup          0 2020-06-20 10:57 /output
drwx------   - atguigu supergroup          0 2020-06-24 09:00 /tmp
drwxr-xr-x   - atguigu supergroup          0 2020-06-24 09:17 /user
[atguigu@hadoop102 datas]$ hdfs dfs -ls /
Found 4 items
drwxr-xr-x   - atguigu supergroup          0 2020-06-16 19:23 /input
drwxr-xr-x   - atguigu supergroup          0 2020-06-20 10:57 /output
drwx------   - atguigu supergroup          0 2020-06-24 09:00 /tmp
drwxr-xr-x   - atguigu supergroup          0 2020-06-24 09:17 /user

```

hive中也可查看：

```
0: jdbc:hive2://hadoop102:10000> dfs -ls /;
+----------------------------------------------------+
|                     DFS Output                     |
+----------------------------------------------------+
| Found 4 items                                      |
| drwxr-xr-x   - atguigu supergroup          0 2020-06-16 19:23 /input |
| drwxr-xr-x   - atguigu supergroup          0 2020-06-20 10:57 /output |
| drwx------   - atguigu supergroup          0 2020-06-24 09:00 /tmp |
| drwxr-xr-x   - atguigu supergroup          0 2020-06-24 09:17 /user |
+----------------------------------------------------+
5 rows selected (0.027 seconds)

```

## 7. Hive常见属性配置

### 1. Hive运行日志信息配置

我们用后台启动脚本写的运行日志脚本在这里

```shell
[atguigu@hadoop102 logs]$ ll
总用量 8
-rw-rw-r--. 1 atguigu atguigu 3140 6月  24 09:54 hiveServer2.log
-rw-rw-r--. 1 atguigu atguigu   52 6月  24 09:11 metastore.log
[atguigu@hadoop102 logs]$ pwd
/opt/module/hive/logs
```

现在我们要把hive本身的运行日志也放到这个目录里

1）Hive的log默认存放在`/tmp/atguigu/hive.log`目录下（当前用户名下）
2）修改hive的log存放日志到`/opt/module/hive/logs`
（1）修改`$HIVE_HOME/conf/hive-log4j.properties.template`文件名称为`hive-log4j.properties`

```shell
[atguigu@hadoop102 conf]$ mv hive-log4j2.properties.template hive-log4j.properties
[atguigu@hadoop102 conf]$ vim hive-log4j.properties 
```

（2）在hive-log4j.properties文件中修改log存放位置

```properties
hive.log.dir=/opt/module/hive/logs
```

重启hive（也可都配置完后重启）。

### 2. Hive启动jvm堆内存设置

新版本的hive启动的时候，默认申请的jvm堆内存大小为256M，jvm堆内存申请的太小，导致后期开启本地模式，执行复杂的sql时经常会报错：`java.lang.OutOfMemoryError: Java heap space`，因此最好提前调整一下`HADOOP_HEAPSIZE`这个参数。

```shell
[atguigu@hadoop102 conf]$ cp hive-env.sh.template hive-env.sh
[atguigu@hadoop102 conf]$ vim hive-env.sh
```

 将hive-env.sh其中的参数 `export HADOOP_HEAPSIZE=1024`的注释放开，重启hive。

### 3. 参数配置方式

优先级依次递增。即**配置文件<命令行参数<参数声明**。（谁最后设的谁生效！）

注意某些**系统级的参数**，例如**log4j相关的设定，必须用前两种方式设定，因为那些参数的读取在会话建立以前已经完成了**。

#### 1. 配置文件方式（永久）

用户自定义配置会覆盖默认配置。另外，Hive也会读入Hadoop的配置，因为Hive是作为Hadoop的客户端启动的，Hive的配置会覆盖Hadoop的配置。

**配置文件的设定对本机启动的所有Hive进程都有效。**

- 默认配置文件：hive-default.xml

- 用户自定义配置文件：hive-site.xml

#### 2. 命令行参数方式（只针对当前客户端连接）

启动Hive时，可以在命令行添加-hiveconf param=value来设定参数。

**仅对本次hive启动有效。**

hive命令行

```shell
[atguigu@hadoop103 hive]$ hive -hiveconf mapred.reduce.tasks=10;
```

beeline客户端
```shell
[atguigu@hadoop103 hive]$ beeline -u jdbc:hive2://hadoop102:10000 -n atguigu -hiveconf mapred.reduce.tasks=10;
```

##### 查看

进入hive或beeline后，查看的语法是一样的

```shell
hive (default)> set mapred.reduce.tasks;
```

#### 3. 参数声明方式（只针对当前客户端连接）

可以在HQL中使用SET关键字设定参数

**仅对本次hive启动有效。**

##### 设定参数

```
hive (default)> set mapred.reduce.tasks=100;
```

##### 查看参数设置

```sql
0: jdbc:hive2://hadoop102:10000> set hive.metastore.schema.verification;
+-------------------------------------------+
|                    set                    |
+-------------------------------------------+
| hive.metastore.schema.verification=false  |
+-------------------------------------------+
1 row selected (0.011 seconds)
```

## 8. 图形化连接hive3

### 1. DBeaver7.1.0

1. 新建连接

![image-20200624105131614](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200624105131.png)

2. 把linux上/opt/module/hive/jdbc目录中的jar包下载下来

   （不想下载的话直接按dbeaver提示下载也可，前提是网速好一点）

   ```shell
   $ sz hive-jdbc-3.1.2-standalone.jar 
   ```

   放到dbeaver安装目录中

3. 配置连接参数

   ![image-20200624105441761](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200624105441.png)

4. 编辑驱动

   ![image-20200624105702379](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200624105702.png)

5. 测试连接，成功后即可看到

![image-20200624105750769](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200624105750.png)

### 2. IDEA

1. 新建连接

![image-20200624104643185](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200624104643.png)

2. 配置参数

![image-20200624104824386](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200624104824.png)

如果不想下载还是可以自己从本地配置驱动

![image-20200624122418675](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200624122418.png)

3. 测试连接没问题后，点击ok即可

![image-20200624104909464](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200624104909.png)

# 三、 Hive数据类型

## 1. 基本数据类型

| HIVE       | MySQL       | JAVA    | 长度                                                         | 例子                                   |
| ---------- | ----------- | ------- | ------------------------------------------------------------ | -------------------------------------- |
| TINYINT    | TINYINT     | byte    | 1byte有符号整数                                              | 20                                     |
| SMALINT    | SMALINT     | short   | 2byte有符号整数                                              | 20                                     |
| **INT**    | INT         | int     | 4byte有符号整数                                              | 20                                     |
| **BIGINT** | BIGINT      | long    | 8byte有符号整数                                              | 20                                     |
| BOOLEAN    | **无**      | boolean | 布尔类型，true或者false                                      | TRUE FALSE                             |
| FLOAT      | FLOAT       | float   | 单精度浮点数                                                 | 3.14159                                |
| **DOUBLE** | DOUBLE      | double  | 双精度浮点数                                                 | 3.14159                                |
| **STRING** | **VARCHAR** | string  | 字符系列。可以指定字符集。可以使用单引号或者双引号。理论上它可以存储2GB的字符数 | ‘now is the time’   “for all good men” |
| TIMESTAMP  | TIMESTAMP   |         | 时间类型                                                     |                                        |
| BINARY     | BINARY      |         | 字节数组                                                     |                                        |

## 2. 集合数据类型

map中每一个k的含义是一样的，每一个v的含义是一样的

struct中有很多个map的value，每一个map的业务含义都不同。可以这样理解

| 数据类型 | 描述                                                         | 语法示例                                                     |
| -------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| STRUCT   | 和c语言中的struct类似，都可以通过“点”符号访问元素内容。例如，如果某个列的数据类型是STRUCT{first STRING, mid  STRING,last int},那么第1个元素可以通过字段.first来引用。2 | struct()  例如  struct<street:string,  city:string,email:int> |
| MAP      | MAP是一组键-值对元组集合，使用数组表示法可以访问数据。例如，如果某个列的数据类型是MAP，其中键->值对是’first’->’John’和’last’->’Doe’，那么可以通过字段名[‘last’]获取最后一个元素 | map()  例如map<string, int>                                  |
| ARRAY    | 数组是一组具有相同类型和名称的变量的集合。这些变量称为数组的元素，每个数组元素都有一个编号，编号从零开始。例如，数组值为[‘John’, ‘Doe’]，那么第2个元素可以通过数组名[1]进行引用。 | Array()  例如array<string>                                   |

### 实操

测试数据

```
songsong,bingbing_lili,xiao song:18_xiaoxiao song:19,hui long guan_beijing_10010
yangyang,caicai_susu,xiao yang:18_xiaoxiao yang:19,chao yang_beijing_10011
```

创建测试表 7-10列分别为 

- 字段（列）分隔符
- 元素分隔符（如array中每一个元素，map中每一个键值对，struct中每一个value）
- 键值对分隔符（map中）
- 行分隔符

```sql
create table test(
name string,
friends array<string>,
childrens map<string,int>,
address struct<street:string,city:string,email:int>
)
row format delimited fields terminated by ','
collection items terminated by '_'
map keys terminated by ':'
lines terminated by '\n'
;
```

导入文本数据到测试表

```sql
hive (default)> load data local inpath '/opt/module/hive/datas/test.txt' into table test;
```

查询三种集合列里的数据，以下分别是ARRAY，MAP，STRUCT的访问方式

```sql
hive (default)> select friends[1],children['xiao song'],address.city from test
where name="songsong";
OK
_c0     _c1     city
lili    18      beijing
Time taken: 0.076 seconds, Fetched: 1 row(s)
```



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

