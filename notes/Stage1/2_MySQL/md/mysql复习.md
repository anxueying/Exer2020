

# 一  卸载MySQL：

**1 先把mysql服务给关掉**
	1.通过dos命令(知道) ： net stop/start 服务名称
	2.此电脑 -> 右键 -> 管理 -> 服务和应用程序 -> 服务 -> Mysql服务名称 -> 右键（开启、停止）

**2 卸载mysql**
    ①控制面板卸载
    ②可以通过360软件（腾讯管家等工具进行卸载）

**3 删数data和配置文件（my.ini）**
	注意 ：data中存放的全部是数据库中的内容（不重要就可以直接删除）。
	mysql5.7默认安装路径 : C:\ProgramData\MySQL\MySQL Server 5.7

**4 删除服务名称（如果存在的话）**
	sc delete 服务名称 - 获取权限（那么安装msyql时服务的名字另起一个mysql557）
	注意 ：如果删除失败那么就需要考虑权限的问题。在dos窗口上右键使用管理员权限打开。
			那么就可以获取到管理员的权限。
			

**5 如果还是安装不上，那么就需要清楚注册表中的内容。**
	要删除的内容很多 - 使用管家清理注册表。
	打开注册表 ：运行窗口中 输入 regedit
	

**6 ：上面的操作完毕后建议重启电脑。**



# 二 安装MySQL

1.详见PPT上的安装操作。

# 三 修改MySQL的默认编码集

​        1.关闭mysql服务
​	2.打开my.ini
​	3.
​		①找到安装目录下的my.ini文件

​		②在[mysqld]下面添加：
​			character-set-server=utf8
​			collation-server=utf8_general_ci

​		③在[client]下面添加：（如果没有[client]字段，先添加该字段）
​			default-character-set=utf8

​		④重启mysql服务

​	4.开启mysql服务
​	5.登录mysql ：
​	①创建数据库 create database test;
​	②查看数据库详细信息：show create database test;

# 四 基本操作

```mysql
#连接mysql -u用户名 -p密码
mysql -u root -p
密码
mysql -uroot -p密码
#选库
use 库命;
#查看所有的表
show tables;
#创建库
create database 库名;
#删除库
drop database 库名;
#查库看的详细信息
show create database 库名;
#创建库时指定编码集
create database 库名 character set 编码集;
```

注意：

```
注意 ：
1.每行命令结束必须以";"结尾
2.mysql不区分大小写 - 命令。
3.可以换行写。
 例 ：
	create
	database
	库名
	;
4.单行注释 ：#
  多行注释 ：/* */
5.mysql中双引号和单引号没有明显区别。别名时使用双引号。比如字符串，日期都使用单引号。
```

# 五 MySQL概述

## 1.数据库 ：用来实现数据的持久化

## 2.数据库的相关概念

DB : Database  - 数据库

DBMS：Database Management System - 数据库管理系统（MySQL,DB2,SQLServer........）

## 3.SQL分类：

DML:数据操纵语言  -----> 对于数据的增，删，改

DQL:数据查询语言 -------> 查

DDL:数据定义语言 -------> 对表的结构进行操作，删除，创建，添加列，修改列等操作

DCL:数据控制语言 -------> 事务的处理，权限的操作

# 六 基本的操作查询：

## 1.查询的格式

```mysql
select 字段名1，字段名2，字段名3 .....
from 表名

select * #表示所有的字段
from 表名
```

## 2.字段的别名

```mysql
select 字段名 as 别名 ，字段名 别名,字段名 “别  名”  #如果别名中有空格出现要使用双引号
from 表名
```

## 3.过滤-where

```mysql
select ......
from 表名
where 过滤条件
#注意 ：where要在from后面使用
```

## 4.运算符

![](img\搜狗截图20200530094743.png)

### 1.模糊查询 - like

```mysql
like '%a%' # %表示任意个数的任意字符
like '_a%' # _表示任意一个字符
like '_\_a%' # \_ 转义字符就表示_本身
like '_$_a%' escape '$'  # 将$作为转义字符
```

### 2.逻辑运算符

![](img\搜狗截图20200530095152.png)

### 3.not - 否定

```sql
 is not null
 not like
 .....
```

# 七 排序

## 1.格式 

```sql
order by 字段名 asc/desc   #asc升序 ，desc降序
```

## 2.二级排序

``` sql
#先按照字段名1排序，如果字段名1的值相同再拍照字段名2排序以此类推
order by 字段名1 asc/desc,字段名2 asc/desc .......
```

## 3.可以按照别名排序

```sql
select salary as s
from employees
order by s; #s是salary的别名
```

# 八 多表查询

## 1.说明

**①连接的方式：**

等值 连接 vs 非等值连接

自连接 vs 非自连接

内连接 vs 外连接

**②有sql92和sql99语法的区别**

**sql92语法**

```sql
select 表名.字段名1,表名.字段名2....
from 表名1，表名2
where 连接条件
```

**sql99语法**

``` sql
select 表名.字段名1,表名.字段名2....
#inner join内连接 left join左外连接 right join右外连接
from 表名1 inner/left/right [outer] join #outer可以省略
on 连接条件
```

**③内连接vs外连接**

•内连接: 合并具有同一列的两个以上的表的行, **结果集中不包含一个表与另一个表不匹配的行**

•外连接: 两个表在连接过程中除了返回满足连接条件的行以外**还返回左（或右）表中不满足条件的行** **，这种连接称为左（或右） 外连接**。没有匹配的行时, 结果表中相应的列为空(NULL). 

## 2.常见的一个错误（迪卡尔集错误）

发生的原因 ：多表连接中，缺少连接条件或者连接条件条件不正确

# 九 函数

## 1.函数的分类 

①单行函数  ②组函数  ③通用函数

## 2.单行函数

``` sql
LOWER('SQL Course')：将全部内容变成小写
UPPER('SQL Course')：将全部内容变成大写
CONCAT('Hello', 'World') : 将内容拼接起来
SUBSTR('HelloWorld',1,5) : 截取子串，索引从1开始。
	第一个参数：从哪个位置开始
	第二个参数：偏移量（长度）
LENGTH('HelloWorld') :子符串的长度
INSTR('HelloWorld', 'W'):w在当前字符串中第一次出现的位置,如果找不到返回0
LPAD(salary,10,'*') ：右对齐(从左边填充)
	第二个参数：指的是数据的长度
	第三个参数：长度不够用*补
RPAD(salary, 10, '*') ：左对齐（从右边填充）
	第二个参数：指的是数据的长度
	第三个参数：长度不够用*补
TRIM('H' FROM 'HelloWorld')：去除字符串两端指定的字符
REPLACE('abcd','b','m') ：将字符串中所有的b替换成m
ROUND: 四舍五入
ROUND(45.926, 2)			45.93
TRUNCATE: 截断
TRUNCATE(45.926，0)      		45
MOD: 求余
MOD(1600, 300)	
函数NOW() 返回:当前的日期和时间
SELECT NOW();
```

## 3.组函数

```sql
AVG() ：求平均值 
SUM() ：求和
MAX() ：求最大值 
MIN() ：求最小值 
COUNT() ：求总个数
```

注意 ： 

①组函数中做运算都不包括null 

②select后面出现组函数那么就不能再出现其它字段。但是，如果分组的字段是可以和组函数同时出现的。

③   count(字段名) ：求字段中有数据的个数--不包括null

​	count(数值) : 如果是一个具体的数值那么默认会认为每条数据中都有一个该数值然后统计该数值的总数。

​	count(*) : 求表中的数据有多少条。

## 4.通用函数

``` sql
case 字段名
when 值1 then 表达式1或值
when 值2 then 表达式2或值
when 值3 then 表达式3或值
.....
else 表达式n或值n
end

==============================

case 
when 条件1 then 表达式1或值
when 条件2 then 表达式2或值
when 条件3 then 表达式3或值
.....
else 表达式n或值n
end
```

## 5.if函数

```
if(表达式，值1，值2) ：如果表达式的值为true结果为值2，如果为false结果为值2
```

## 6.去重：distinct

```sql
select distinct 字段名1  #用来对字段名1的结果进行去重
from employees
```



# 十 分组和过滤(having)

## 1.分组的格式

```
group by department_id   #按照部门分组
group by department_id，job_id......  #按照不同部门的不同工种进行分组
```

## 2.过滤 having

说明：①having是在分组后过滤  ②having后面可以跟组函数

``` sql
having 过滤条件
```

# 十一 子查询

## 1.说明

①出现在其他语句内部的select语句称为子查询或内查询。
②a查询语句中嵌套了b查询语句。a查询叫作外查询/主查题。b查询语句叫子查询。

③先执行子查询，再执行主查询。主查询依赖子查询的结果
④子查询必须放在()内

## 2.子查询分类 ：

单行子查询 vs 多行子查询

注意：单行子查询和多行子查询指的是子查询返回的数据的条数。

## 3.子查询出现的位置

``` sql
	select 后面
	from 后面
	where 后面
	having 后面
```

## 4.运算符

​	单行子查询用到的运算符 : >   >=   <   <=    <>     =
​	多行子查询 ：

​		    in : 等于子查询中的任何一个结果
​		     any ：表示满足子查询中的任何一个结果
​		     all ：表示满足子查询中的所有结果

## 五 案例：

``` sql
#1.需求：谁的工资比Abel高？
#思路一 ：①先查出Abel工资是多少  ②再查出比这个薪水高的员工信息
#①先查出Abel工资是多少
SELECT salary
FROM employees
WHERE last_name='Abel'; #11000
#②再查出比这个薪水高的员工信息
SELECT last_name,salary
FROM employees
WHERE salary > 11000;

#思路二 ：自连接
SELECT e1.`first_name`,e1.`salary`
FROM employees e1 JOIN employees e2 #用e1表中的每条数据和e2表中的Abel比薪水
ON e2.`last_name`='Abel' AND e1.`salary` > e2.`salary`;

#思路三 ：子查询
#写子查询时的思路 ：先分步写再合并
SELECT last_name,salary
FROM employees
WHERE salary > (
	#注意：先执行子查询，再执行主查询。主查询依赖子查询的结果
	SELECT salary
	FROM employees
	WHERE last_name='Abel'
);
```

# 十二 创建和管理表

## 1.常用操作

``` sql
#创建库
CREATE DATABASE demo;
#删除库
DROP DATABASE demo;
#选库
USE myemployees;
#查看库中所有的表
SHOW TABLES;
#删除表
DROP TABLE p2;
```

## 2.创建表

①方式一

``` sql
CREATE TABLE student(#student是表名
id INT, #字段名 类型
NAME VARCHAR(20),#varchar相当于String
PASSWORD VARCHAR(20)
)
```

②方式二

``` sql
CREATE TABLE person
AS
#将下面查询的结果变成person实体表
SELECT employee_id,first_name
#夸库操作表：表前需要库名
FROM myemployees.employees;#employees在myemployees库中，我们在demo库中操作
```

## 3.对表中的列进行增，删，改的操作

**格式:alter table 表名 add/drop/modify/change column  ......**

```sql
#修改表名
ALTER TABLE p2 RENAME TO p1;
#修改列名 :ALTER TABLE 表名 CHANGE COLUMN 旧列名 新列名 列的类型
ALTER TABLE p1 CHANGE COLUMN NAME new_name VARCHAR(20)
#修改列的类型 :ALTER TABLE 表名 MODIFY COLUMN 列名 列的类型
ALTER TABLE p1 MODIFY COLUMN new_name INT;#如果有数据无法修改类型无法将varchar转成int
#添加新列 :ALTER TABLE 表名 ADD COLUMN 字段名 字段的类型;
ALTER TABLE p1 ADD COLUMN age INT;
#删除列 :ALTER TABLE 表名 DROP COLUMN 字段名;
ALTER TABLE p1 DROP COLUMN age;
```

# 十三 对表中的数据进行增，删，改的操作

## 1.插入数据

```sql
#方式一 ： 向表中插入数据
#格式 ：insert into 表名(列名) values(值1，值2......)
INSERT INTO p2(id,NAME) VALUES(1,'aaa');
INSERT INTO p2(id,NAME) VALUES(2,'ccc');#注意字段和数据的顺序要对应起来
INSERT INTO p2(NAME) VALUES('fff');
#格式 ：insert into 表名  values(值1，值2......)
#如果插入数据时不指明列名/字段名，那么插入的数值必须是全数据。
INSERT INTO p2 VALUES(3,'ddd');
INSERT INTO p2 VALUES('eee');


#方式二 ： 向表中插入数据（一次插入多行）
INSERT INTO p2(id,NAME) VALUES(10,'aa1'),(11,'aa2'),(12,'aa3');


#方式三 ：基于现有的数据插入到该表中
INSERT INTO p2(id)
#查询语句
SELECT department_id FROM myemployees.`employees`;
```

## 2.删除数据

```sql
#格式 ：delete from 表名 [where 过滤条件]
DELETE FROM p2 WHERE id=1;
DELETE FROM p2 WHERE id IS NULL;
#把整张表全删除
DELETE FROM p2;
```

## 3.修改数据

``` sql
#格式 ：update 表名 set 字段名=新值，字段名2=新值.... [where 过滤条件]
UPDATE p2 SET id=1000 WHERE id=10;
UPDATE p2 SET id=10,NAME='xiaolongge' WHERE id=20;
#将表中所有的字段全部修改
UPDATE p2 SET id=10,NAME='xiaolongge';
```

## 4.truncate

``` sql
 truncate table 表名 ：清空表中的内容。
 delete from 表名 ：清空表中的内容。
 
 区别：
	1.truncate table不能回滚，效率高
	2.delete from可以回滚和truncate比效率低一些。
```



# 十四 数据库事务

## 1.说明：

**事务**：一组逻辑操作单元，使数据从一种状态变换到另一种状态

**案例** ：AA给BB转账100

       AA ：1000
       BB ：1000
       操作 ：
       1.AA-100 : 900 
       ---------发生异常了--------
       2.BB+100 : 执行不了

**上面遇到的问题 ：**遇到的问题AA操作成功，但是BB没有操作成功

**在java代码中解决的思路**

```java
try{
	java代码操作AA账号的的代码 
		-- 操作数据库(update account set abalance=900 where aname="aa")
	--------异常---------------	
	java代码操作BB账号的的代码 
		-- 操作数据库(update account set abalance=1100 where aname="bb")
	commit();
}catch(Exception e){
	回滚;
}
```

**mysql中的操作**

``` sql
SET AUTOCOMMIT = FALSE;#禁止自动提交

UPDATE account SET abalance=900 WHERE aname="aa";
UPDATE account SET abalance=1100 WHERE aname="bb";

ROLLBACK;#回滚
COMMIT;#提交 - 一旦提交将不能回滚
```

# 十五 约束

## 1.约束的说明

例：创建表时，我们对某些字段有一些特殊要求，比如不能为null,比如有些字段
可以设置默认值等操作。那么我们就需要用到约束

## 2.有哪些约束？

​	primary key : 主键约束 （要求这个字段的数据不能为null且唯一）
​		一个表中只能有一个主键
​	not null :非空约束
​	unique : 唯一性约束
​	check ：检查约束 （mysql不支持）
​	default :默认值约束
​	foreign key :外健约束
​	

## 3.约束分类 ：列级约束 vs 表级约束

​      列级约束 ：只能同时对一个字段进行约束。
​		比如：not null,default
​      表级约束 ：可以同时对多个字段进行约束
​		比如：primary key,unique ,foreign key
​      

## 4.创建表时添加约束

**方式一 ：在字段后面添加约束，只对一个字段有效**

```sql
CREATE TABLE emp(
eid INT PRIMARY KEY,#主键约束 = 非空 + 唯一
ename VARCHAR(20) NOT NULL,#非空约束
eage INT DEFAULT 10,#默认值约束
sex INT
)
```

**方式二 ：如果需要对多个字段进行约束  -  primary key,unique ,foreign key**

``` sql
CREATE TABLE emp3(
    eid INT,
    eage INT,
    ename VARCHAR(20),
    epassword VARCHAR(20),
    #添加表级约束 - 主键约束 : 
    #格式 ： constranint 索引名 PRIMARY KEY(字段名1，字段名2.....);
    #索引名：可以随便起，但是不能相同。
    CONSTRAINT e_p PRIMARY KEY(eid,eage),#主键约束
    CONSTRAINT e_u UNIQUE(ename,epassword)#唯一约束
)
```



## 5.表创建成功以后添加约束

```sql
#primary key
#添加约束
ALTER TABLE e3 ADD PRIMARY KEY(eid);
#删除约束
ALTER TABLE e3 DROP PRIMARY KEY;
#修改列的方式添加约束
ALTER TABLE e3 MODIFY eid INT PRIMARY KEY 


#unique
#添加约束
ALTER TABLE e3 ADD UNIQUE(eid);
#删除约束
ALTER TABLE e3 DROP INDEX eid; # eid是索引名
#添加约束 - 通过添加CONSTRAINT的方式添加约束
ALTER TABLE e3 ADD CONSTRAINT index_eid UNIQUE(eid);#index_eid是索引的名字
ALTER TABLE e3 DROP INDEX index_eid; # index_eid是索引名


#not null
#添加约束 ---通过修改列的方式
#注意-如果该字段已经有null那么就会报错。
ALTER TABLE e3 MODIFY ename VARCHAR(20) NOT NULL;
#删除约束
ALTER TABLE e3 MODIFY ename VARCHAR(20) NULL;


#default
#添加约束
ALTER TABLE e3 ALTER ename SET DEFAULT 'xiaolongge';
#删除约束
ALTER TABLE e3 ALTER ename DROP DEFAULT;


#查表中的索引
SHOW INDEX FROM e3;
#插入数据
INSERT INTO e3(eid) VALUES(1);
#删除表中的内容
DELETE FROM e3;
#查看表结构
DESC e3;
```

## 6.外键约束

①格式：CONSTRAINT 索引名 FOREIGN KEY（当前表中的字段名） REFERENCES 主表名（字段名）
②注意 ：
   1.先创建主表再创建从表
   2.删除数据 ：先删除从表再删除主表
   3.添加数据 ：先添加主表再添加从表

③格式

```sql
#部门表 - 主表
CREATE TABLE dept(
    dept_id INT AUTO_INCREMENT PRIMARY KEY,#AUTO_INCREMENT ：自动填充数据并自增
    dept_name VARCHAR(20)
);

#员工表 - 从表 (注意：先创建主表再创建从表)
CREATE TABLE emp(
    emp_id INT AUTO_INCREMENT PRIMARY KEY, 
    last_name VARCHAR(15),
    dept_id INT,
    #格式：CONSTRAINT 索引名 FOREIGN KEY（当前表中的字段名） 
    #REFERENCES 主表名（字段名）
    CONSTRAINT emp_dept_id_fk FOREIGN KEY(dept_id) 
    REFERENCES dept(dept_id)
);
```

④创建表后的操作

```sql
#删除约束
ALTER TABLE 表名 DROP FOREIGN KEY 外键约束的索引名;
#添加约束
ALTER TABLE 表名  ADD [CONSTRAINT 索引名] FOREIGN KEY(字段名) 
REFERENCES 主表(字段名);
```

# 十六 limit

## 1.使用 ：

​	limit  数据的位置，获取数据的条数

## 2.注意 ：

​	数据的位置是从0开始

## 3/作用 ：一般用来做分页。

​	分页公式 ：limit （当前页数-1）*每页条数，每页条数

```sql
SELECT *
FROM employees
LIMIT 0,10; # 0 ：从第1条数据（位置从0开始）开始   10 ：获取10条数据
```

