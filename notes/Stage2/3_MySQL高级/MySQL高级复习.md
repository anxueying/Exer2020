# 在Linux上安装MySQL

## 0. 卸载MySQL

**卸载MySQL的步骤：**

1. 查看是否安装Mysql

```
[atguigu@hadoop102 module]$ rpm -qa | grep -i mysql

MySQL-client-5.6.24-1.el6.x86_64

MySQL-server-5.6.24-1.el6.x86_64
```

2. 查看MySQL服务是否启动，关闭

```
[atguigu@hadoop102 module]$ sudo service mysql status

[sudo] password for atguigu: 

MySQL running (2681)                    [确定]

 

[atguigu@hadoop102 module]$ sudo service mysql stop

Shutting down MySQL....                  [确定]
```

3. 卸载MySQL安装的组件

```
[atguigu@hadoop102 module]$ sudo rpm -e MySQL-server-5.6.24-1.el6.x86_64

[sudo] password for atguigu: [atguigu@hadoop102 module]$ sudo rpm -e MySQL-client-5.6.24-1.el6.x86_64
```

4. 查找并删除MySQL相关的文件

```
[atguigu@hadoop102 module]$ whereis mysql

mysql: /usr/lib64/mysql

 

[atguigu@hadoop102 module]$ sudo find / -name mysql

/var/lib/mysql

/var/lib/mysql/mysql

/usr/lib64/mysql

 

[atguigu@hadoop102 module]$ sudo rm -rf /var/lib/mysql

[atguigu@hadoop102 module]$ sudo rm -rf /usr/lib64/mysql
```

 

## 1. 检查当前系统是否安装过MySQL

CentOS 6命令：**rpm -qa|grep mysql**

先卸载系统自带的mysql，执行卸载命令**rpm -e --nodeps mysql-libs**

CentOS 7命令：**rpm -qa|grep mariadb**

先卸载系统自带的mysql，执行卸载命令rpm -e --nodeps mariadb-libs

![image-20200608112835545](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200608112835.png)

检查/tmp目录的权限是否是满的

![image-20200608112930553](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200608112930.png)

## 2. 安装mysql

```
rpm -ivh 01_mysql-community-common-5.7.28-1.el7.x86_64.rpm
rpm -ivh 02_mysql-community-libs-5.7.28-1.el7.x86_64.rpm
rpm -ivh 03_mysql-community-libs-compat-5.7.28-1.el7.x86_64.rpm
rpm -ivh 04_mysql-community-client-5.7.28-1.el7.x86_64.rpm
rpm -ivh 05_mysql-community-server-5.7.28-1.el7.x86_64.rpm
```

## 3.查看是否安装成功

```
mysqladmin --version
```

## 4.初始化

```
mysqld --initialize --user=mysql
```

## 5.查看密码

```
cat /var/log/mysqld.log  
```

## 6.启动mysql服务

```
systemctl start mysqld
```

## 7.登录mysql

```
mysql -u账号 -p密码
```

## 8.修改密码(修改当前用户的密码)

```
set password=password("密码");
```

用root帮别人改密码

```
ALTER USER 'root'@'localhost' IDENTIFIED BY '你的密码'; 
```

# MySQL服务

查看是否是开机自启：默认是开机自启的

```
systemctl is-enabled mysqld
```

查看服务状态：

```
ps -aux | grep mysqld
systemctl status mysqld
```

## ntsysv

（自启、取消自启）界面式

使用空格取消选中，然后按TAB确定！

## chkconfig

用命令行设置开机自启

**Centos6中有mysql重复启动的问题**

此时尝试登陆会报错

解决方法：杀死所有和mysql进程相关的操作，然后重启服务！

```
killall mysql	  杀死所有mysql进程
killall mysqld    杀死所有mysql守护进程
service mysql start   		重启服务
```

# MySQL安装位置

这个表是centos6的（也可看配置文件来看路径）

| 参数            | 路径                                | 解释                          | 备注                           |
| --------------- | ----------------------------------- | ----------------------------- | ------------------------------ |
| **--datadir**   | **/var/lib/mysql/**                 | **mysql数据库文件的存放路径** |                                |
| **--basedir**   | **/usr/bin**                        | **相关命令目录**              | **mysqladmin mysqldump等命令** |
| --plugin-dir    | /usr/lib64/mysql/plugin             | mysql插件存放路径             |                                |
| **--log-error** | **/var/lib/mysql/jack.atguigu.err** | **mysql错误日志路径**         | ****                           |
| --pid-file      | /var/lib/mysql/jack.atguigu.pid     | 进程pid文件                   |                                |
| --socket        | /var/lib/mysql/mysql.sock           | 本地连接时用的unix套接字文件  |                                |
|                 | /usr/share/mysql                    | 配置文件目录                  | mysql脚本及配置文件            |
|                 | /etc/init.d/mysql                   | 服务启停相关脚本              |                                |

这个表是centos7的路径





![image-20200608115705881](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200608115705.png)

# MySQL配置字符集

| SQL语句                                         | 描述                              | 备注                                       |
| ----------------------------------------------- | --------------------------------- | ------------------------------------------ |
| create database 库名 character set utf8         | 创建数据库，顺便执行字符集为utf-8 |                                            |
| show create database 库名                       | 查看数据库的字符集                |                                            |
| show variables like '%char%'                    | 查询所有跟字符集相关的信息        |                                            |
| set [字符集属性]=utf8                           | 设置相应的属性为utf8              | 只是临时修改，当前有效。服务重启后，失效。 |
| alter database 库名character set 'utf8'         | 修改数据库的字符集                | （用不到）已生成的库表字符集如何变更       |
| alter table 表名convert to character set 'utf8' | 修改表的字符集                    | （用不到）已生成的库表字符集如何变更       |

**永久修改字符集**

1. 关闭mysql服务

2. 修改配置文件：

```
vim /etc/my.cnf
```

3. 在mysqld节点下最后加上中文字符集配置

```
character_set_server=utf8
```

4. 启动mysql服务

> 已经创建的数据库的设定不会发生变化，参数修改只对新建的数据库有效！

# MySQL用户和权限管理

## 1. 用户管理

| 命令                                                         | 描述                                     | 备注                                                         |
| ------------------------------------------------------------ | ---------------------------------------- | ------------------------------------------------------------ |
| create user zhang3 identified by '123123';                   | 创建名称为zhang3的用户，密码设为123123； |                                                              |
| select host,user,password,select_priv,insert_priv,drop_priv  from mysql.user; | 查看用户和权限的相关信息                 |                                                              |
| set  password =password('123456')                            | 修改当前用户的密码                       |                                                              |
| update  mysql.user set password=password('123456') where user='li4'; | 修改其他用户的密码                       | 所有通过user表的修改，必须用flush privileges;命令才能生效    |
| **flush privileges**                                         | **刷新**                                 | **做完用户变更要刷新一下**                                   |
| update  mysql.user set user='li4' where user='wang5';        | 修改用户名                               | 所有通过user表的修改，必须用flush privileges;命令才能生效    |
| drop  user li4                                               | 删除用户                                 | 不要通过delete from user  u where user='li4' 进行删除，系统会有残留信息保留。 |

![image-20200608142205570](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200608142205.png)

`\G`以列形式展现数据

host：允许哪些主机登录该用户

% 表示所有远程通过 TCP方式的连接

​    IP 地址 如 (192.168.1.2,127.0.0.1) 通过制定ip地址进行的TCP方式的连接

​    机器名  通过制定i网络中的机器名进行的TCP方式的连接

​    ::1  IPv6的本地ip地址 等同于IPv4的 127.0.0.1

​    localhost 本地方式通过命令行方式的连接 ，比如mysql -u xxx -p 123xxx 方式的连接。

password : mysql 5.7 的密码保存到 authentication_string 字段中不再使用password 字段。

​	加密算法为MYSQLSHA1 ，不可逆 。

 **select_priv , insert_priv**等为该用户所拥有的权限。

## 2. 权限管理

### 1. 授权

| 命令                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| grant 权限1,权限2,…权限n  on 数据库名称.表名称 to 用户名@用户地址  identified by ‘连接口令’ | 该权限如果发现没有该用户，则会直接新建一个用户。  示例：  grant select,insert,delete,drop on atguigudb.* to  li4@localhost ;  给li4用户用本地命令行方式下，授予atguigudb这个库下的所有表的插删改查的权限。 |

```
grant  all privileges on *.* to  root@'%'   identified by '123321';
```

  授予通过网络方式登录的的root用户 ，对所有库所有表的全部权限，密码设为123321.  

### 2. 收回

| 命令                                                         | 描述                                | 备注 |
| ------------------------------------------------------------ | ----------------------------------- | ---- |
| show grants                                                  | 查看当前用户权限                    |      |
| revoke [权限1,权限2,…权限n]  on   库名.表名   from 用户名@用户地址 ; | 收回权限命令                        |      |
| REVOKE ALL PRIVILEGES ON mysql.* FROM joe@localhost;         | 收回全库全表的所有权限              |      |
| REVOKE select,insert,update,delete ON mysql.* FROM  joe@localhost; | 收回mysql库下的所有表的插删改查权限 |      |

权限收回后，必须用户重新登录后，才能生效。

**flush privileges;**  #所有通过user表的修改，必须用该命令才能生效。

### 3. 使用远程工具连接Mysql

1）关闭Linux的防火墙

2）授权远程连接的用户和密码

```
grant  all privileges on *.* to  root@'%'   identified by '123321';
```

3）使用授权的账户和密码通过SQLyog或Navicat连接

# 索引优化分析

## 1. 索引

### what

索引是数据结构，可以简单理解为排好序的快速查找数据结构。

### why

**优势**：

Ø **提高数据检索的效率**，降低数据库的IO成本。

Ø **通过索引列对数据进行排序，降低数据排序的成本，降低了CPU的消耗。**

**劣势**：

Ø 虽然索引大大提高了查询速度，同时却会**降低更新表的速度**，如对表进行INSERT、UPDATE和DELETE。因为更新表时，MySQL不仅要保存数据，还要保存一下索引文件每次更新添加了索引列的字段，都会调整因为更新所带来的键值变化后的索引信息。

Ø 实际上索引也是一张表，该表保存了主键与索引字段，并指向实体表的记录，所以**索引列也是要占用空间的**。

## 2. MySQL的索引

度：分叉

深度：层

### 1. B-tree索引

与二叉树相比（一个数据一个块，只有2个叉），3层的b+树可以表示上百万的数据。

- 有多个叉，降低了树的深度，提高检索效率
- 每个磁盘块有多个数据，每次读取时可以降低IO成本

![image-20200608151944189](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200608151944.png)

### 2. B+tree（MySQL底层）

磁盘单位：页（4k） 

- B-树的关键字、记录和索引是放在一起的；B+树的非叶子节点中只有关键字和指向下一个节点的索引，记录只放在叶子节点中。
- B+树的性能要好些，减少磁盘访问次数。B+树的叶子节点使用指针连接在一起，方便顺序遍历

![image-20200608152950725](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200608152950.png)

> 思考：为什么说B+树比B-树更适合实际应用中操作系统的文件索引和数据库索引？
>
> （1）B+树的磁盘读写代价更低 
>
> 　　B+树的内部结点并没有指向关键字具体信息的指针。因此其内部结点相对B 树更小。如果把所有同一内部结点的关键字存放在同一盘块中，那么盘块所能容纳的关键字数量也越多。一次性读入内存中的需要查找的关键字也就越多。相对来说IO读写次数也就降低了。 
>
> （2）B+树的查询效率更加稳定 
>
> 由于非终结点并不是最终指向文件内容的结点，而只是叶子结点中关键字的索引。所以任何关键字的查找必须走一条从根结点到叶子结点的路。所有关键字查询的路径长度相同，导致每一个数据的查询效率相当。

## 3. MySQL索引分类

### 1. 单值索引

一列--单值；2列或多列--复合索引

```mysql
/*随表一起创建：*/
CREATE TABLE customer (id INT(10) UNSIGNED  AUTO_INCREMENT ,customer_no VARCHAR(200),customer_name VARCHAR(200),
  PRIMARY KEY(id),
  KEY (customer_name)
);

/*单独建单值索引：*/
CREATE  INDEX idx_customer_name ON customer(customer_name);
```

### 2. 唯一索引

值必须唯一，但允许有空值。当字段加上unique约束时默认就会创建该字段唯一索引

```
CREATE TABLE emp3(
id INT,
NAME VARCHAR(20) UNIQUE
);
删除索引
drop index 索引名 on 表名
添加索引
create unique index 索引名 on 表名(字段名...);
```

### 3. 主键索引

当字段设置为主键时默认就会创建该字段的主键索引，innodb为聚簇索引

```
CREATE TABLE emp4(
id INT PRIMARY KEY,
NAME VARCHAR(20)
);
#删除索引
ALTER TABLE 表名 DROP PRIMARY KEY;
#添加索引
ALTER TABLE 表名 ADD PRIMARY KEY 表名(字段名....);
```

### 4. 复合索引

```
CREATE TABLE emp2(
id INT,
NAME VARCHAR(20),
KEY(id,NAME) #创建复合索引
);
创建索引
CREATE INDEX 索引名 ON 表名(字段名....);
删除索引
drop index 索引名 on 表名
```

## 4. 索引的创建时机

### 1. 适合

Ø 主键自动建立唯一索引；

Ø 频繁作为查询条件的字段应该创建索引

Ø 查询中与其它表关联的字段，外键关系建立索引

Ø 单键/组合索引的选择问题， 组合索引性价比更高

Ø 查询中排序的字段，排序字段若通过索引去访问将大大提高排序速度

Ø 查询中统计或者分组字段

### 2. 不适合

Ø 表记录太少

Ø 经常增删改的表或者字段

Ø Where条件里用不到的字段不创建索引

Ø 过滤性不好的不适合建索引

# Explain性能分析

```
 Explain+SQL语句
```

## 概念

### ①  Explain 之type

type是查询的访问类型，是较为重要的一个指标结果值从最好到最坏依次是：

system > const > eq_ref > ref > fulltext > ref_or_null > index_merge > unique_subquery > index_subquery > range > index > ALL

### ②  Explain 之 key_len

表示索引中使用的字节数，可通过该列计算查询中使用的索引的长度。 key_len字段能够帮你检查是否充分的利用上了索引。ken_len越长，说明索引使用的越充分。

### ③Explain 之 key

实际使用的索引。如果为NULL，则没有使用索引。

# 单表使用索引及索引失效

**前提：别为了使用索引而使用索引。**

SQL_NO_CACHE : 不从缓存中获取数据。

```
SELECT SQL_NO_CACHE * FROM emp 
```

## 1. 全值匹配我最爱

查询的字段按照顺序在索引中都可以匹配到！

![image-20200608163616756](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200608163616.png)

## 2. 最佳左前缀法则

过滤条件要使用索引必须**按照索引建立时的顺序，依次满足**

**一旦跳过某个字段，索引后面的字段都无法被使用。**

 where中的先后顺序无所谓。

![image-20200608165048222](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200608165048.png)

如复合索引（age，id，name）可用的索引组合

- age
- age，id
- age，id，name
- age，name  只能用上age的索引

## 3. 不要在索引列上做任何操作

- 在查询列上使用了函数：等号左边无计算！
- 在查询列上做了转换：等号右边无转换！

## 4. 索引列上有范围查询时，范围条件右边的列将失效

建议：将可能做范围查询的字段的索引顺序放在最后

## 5. 使用不等于(!= 或者<>)的时候索引失效

mysql 在使用不等于(!= 或者<>)时，有时会无法使用索引会导致全表扫描。

## 6.  is not null 不能使用索引，is null可以使用索引

当字段允许为Null的条件下：

 is not null用不到索引，is null可以用到索引。

## 7. like以通配符%或_开头索引失效

前缀不能出现模糊匹配！

## 8. 字符串不加单引号索引失效

类型会进行转换，导致索引失效

## 9. 减少使用or

使用union all或者union来替代

## 10. 尽量使用覆盖索引

查询列和索引列一致，不要写select *!

**覆盖索引**是select的数据列只用从索引中就能够取得，不必读取数据行，换句话说查询列要被所建的索引覆盖