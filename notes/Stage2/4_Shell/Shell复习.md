# grep语法

```
grep [-abcEFGhHilLnqrsvVwxy][-A<显示列数>][-B<显示列数>][-C<显示列数>][-d<进行动作>][-e<范本样式>][-f<范本文件>][--help][范本样式][文件或目录...]
```

**参数**：

- **-a 或 --text** : 不要忽略二进制的数据。
- **-A<显示行数> 或 --after-context=<显示行数>** : 除了显示符合范本样式的那一列之外，并显示该行之后的内容。
- **-b 或 --byte-offset** : 在显示符合样式的那一行之前，标示出该行第一个字符的编号。
- **-B<显示行数> 或 --before-context=<显示行数>** : 除了显示符合样式的那一行之外，并显示该行之前的内容。
- **-c 或 --count** : 计算符合样式的列数。
- **-C<显示行数> 或 --context=<显示行数>或-<显示行数>** : 除了显示符合样式的那一行之外，并显示该行之前后的内容。
- **-d <动作> 或 --directories=<动作>** : 当指定要查找的是目录而非文件时，必须使用这项参数，否则grep指令将回报信息并停止动作。
- **-e<范本样式> 或 --regexp=<范本样式>** : 指定字符串做为查找文件内容的样式。
- **-E 或 --extended-regexp** : 将样式为延伸的正则表达式来使用。
- **-f<规则文件> 或 --file=<规则文件>** : 指定规则文件，其内容含有一个或多个规则样式，让grep查找符合规则条件的文件内容，格式为每行一个规则样式。
- **-F 或 --fixed-regexp** : 将样式视为固定字符串的列表。
- **-G 或 --basic-regexp** : 将样式视为普通的表示法来使用。
- **-h 或 --no-filename** : 在显示符合样式的那一行之前，不标示该行所属的文件名称。
- **-H 或 --with-filename** : 在显示符合样式的那一行之前，表示该行所属的文件名称。
- **-i 或 --ignore-case** : 忽略字符大小写的差别。
- **-l 或 --file-with-matches** : 列出文件内容符合指定的样式的文件名称。
- **-L 或 --files-without-match** : 列出文件内容不符合指定的样式的文件名称。
- **-n 或 --line-number** : 在显示符合样式的那一行之前，标示出该行的列数编号。
- **-o 或 --only-matching** : 只显示匹配PATTERN 部分。
- `-P` 注意是大写 可以让grep使用perl的正则表达式语法
- **-q 或 --quiet或--silent** : 不显示任何信息。
- **-r 或 --recursive** : 此参数的效果和指定"-d recurse"参数相同。
- **-s 或 --no-messages** : 不显示错误信息。
- **-v 或 --revert-match** : 显示不包含匹配文本的所有行。
- **-V 或 --version** : 显示版本信息。
- **-w 或 --word-regexp** : 只显示全字符合的列。
- **-x --line-regexp** : 只显示全列符合的列。
- **-y** : 此参数的效果和指定"-i"参数相同。

# 1. Shell概述

shell 命令行解释器，作为一个壳保护Linux内核。脚本语言，易编写、易调试、灵活性强。

**用途：**操作一系列指令。只需执行一个shell脚本。

## 1. Linux提供的shell解析器

不一样的风格有不一样的解析器

```shell
[root@hadoop20 ~]# cat /etc/shells 
/bin/sh                 常用
/bin/bash				常用
/usr/bin/sh
/usr/bin/bash           
/bin/tcsh				C语言风格
/bin/csh				C语言风格
```

## 2. bash 和 sh 的关系

sh实际上是bash的软链接

```shell
[root@hadoop20 bin]# ll | grep bash
-rwxr-xr-x. 1 root root      964536 4月   1 10:17 bash
lrwxrwxrwx. 1 root root          10 6月   8 10:36 bashbug -> bashbug-64
-rwxr-xr-x. 1 root root        6964 4月   1 10:17 bashbug-64
lrwxrwxrwx. 1 root root           4 6月   8 10:36 sh -> bash   
```

## 3. centos默认的解析器是bash

```shell
[root@hadoop20 ~]# echo $SHELL
/bin/bash
```

# 2. Shell脚本入门

## 1. 脚本格式

开头：指定解析器

```shell
#!/bin/bash
```

文件格式`*.sh`，写上后缀是方便合作者判断文件类型，对于linux无影响

## 2. shell脚本

```shell
#!/bin/bash
#这是我的第一个shell脚本
echo "helloworld"
```

## 3. 脚本执行方式

### 1. bash（sh）+脚本的路径

不用赋予脚本+x权限。本质是bash解析器帮你执行脚本，所以脚本本身不需要执行权限

#### sh+脚本的相对路径

```shell
[root@hadoop20 shell]# sh helloworld.sh 
helloworld
```

#### sh+脚本的绝对路径

```shell
[root@hadoop20 shell]# sh /root/shell/helloworld.sh 
helloworld
```

#### bash+脚本的相对路径

```shell
[root@hadoop20 shell]# bash helloworld.sh 
helloworld
```

#### bash+脚本的绝对路径

```shell
[root@hadoop20 shell]# bash /root/shell/helloworld.sh 
helloworld
```

### 2. 采用输入脚本的绝对路径或相对路径执行脚本（用的较多）

必须具有可执行权限+x。本质是脚本需要自己执行，所以需要执行权限

```shell
[root@hadoop20 shell]# ./helloworld.sh
-bash: ./helloworld.sh: 权限不够
[root@hadoop20 shell]# ll
总用量 4
-rw-r--r--. 1 root root 64 6月   9 10:46 helloworld.sh
[root@hadoop20 shell]# chmod +x helloworld.sh 
[root@hadoop20 shell]# ll
总用量 4
-rwxr-xr-x. 1 root root 64 6月   9 10:46 helloworld.sh
[root@hadoop20 shell]# ./helloworld.sh 
helloworld
```

# 3. 变量

## 1. 系统预定义变量

### 1. **常用系统变量**

$HOME、$PWD、$SHELL、$USER等

```SHELL
[root@hadoop20 shell]# echo $HOME
/root
[root@hadoop20 shell]# echo $PWD
/root/shell
[root@hadoop20 shell]# echo $SHELL
/bin/bash
[root@hadoop20 shell]# echo $USER
root

[root@hadoop20 shell]# set 			显示当前Shell中所有变量
```

### 2. 自定义变量

#### 1. 基本语法

```
变量=值				定义变量，注意：不要有空格
unset 变量			 撤销变量
readonly 变量		     声明静态变量，静态变量不能撤销
```

```shell
[root@hadoop20 shell]# readonly age=10
[root@hadoop20 shell]# echo $age
10
[root@hadoop20 shell]# age=20
-bash: age: 只读变量
[root@hadoop20 shell]# unset age
-bash: unset: age: 无法反设定: 只读 variable
```

#### 2. 定义规则

**变量定义规则**

（1）变量名称可以由字母、数字和下划线组成，但是不能以数字开头，环境变量名建议大写。

（2）等号两侧不能有空格

（3）在bash中，变量默认类型都是字符串类型，无法直接进行数值运算。

```shell
[root@hadoop20 shell]# num=30+50
[root@hadoop20 shell]# echo num
num
[root@hadoop20 shell]# echo $num
30+50
```

（4）变量的值如果有空格，需要使用双引号或单引号括起来。

```shell
[root@hadoop20 shell]# num="hello world"
[root@hadoop20 shell]# echo $num
hello world
```

#### 3. 变量提升为全局

把变量提升为全局环境变量，可供其他Shell程序使用

```
#!/bin/bash
echo $B

[root@hadoop101 datas]$ ./helloworld.sh 
Helloworld
```

提升全局环境变量

```
[root@hadoop101 datas]$ export B=2
[root@hadoop101 datas]$ ./helloworld.sh 
helloworld
2
```

### 3.  特殊变量

#### 1. $n

$n  功能描述：n为数字

- `$0`代表该脚本名称，
- `$1`-`$9`代表第一到第九个参数，
- 十以上的参数，十以上的参数需要用大括号包含，如`${10}`

```shell
#!/bin/bash
# test1.sh 
# $n $0 $1-9 ${10}
# 与加双引号"name:$0"一样，加单引号则输出name:$0，特殊变量不会解析
echo name:$0
echo p1:$1
echo p2:$2
```

```
[root@hadoop20 shell]# ./test1.sh 1 2 3 4 5
name:./test1.sh
p1:1
p2:2
```

#### 2. $#

获取所有输入参数个数，常用于循环

#### 3. $*、$@

`$* ` （功能描述：这个变量代表命令行中所有的参数，$*把所有的参数看成一个整体）

`$@  `（功能描述：这个变量也代表命令行中所有的参数，不过$@把每个参数区分对待）

#### 4. $？

**最后一次执行的命令的返回状态。**

- 如果这个变量的值为0，证明上一个命令正确执行；
- 如果这个变量的值为非0（具体是哪个数，由命令自己来决定），则证明上一个命令执行不正确了。

# 4. 运算符

```shell
$((运算式))
$[运算式]
```

# 5. 条件判断

## 1. 基本语法

条件非空即为true，[ atguigu ]返回true，[] 返回false。

```shell
test condition						用的较少
[ condition ]						condition前后要有空格
```

## 2. 常用判断条件

### 1. 两个整数之间比较

```shell
= 字符串比较
-lt 小于（less than）         
-le 小于等于（less equal）
-eq 等于（equal）           
-gt 大于（greater than）
-ge 大于等于（greater equal）  
-ne 不等于（Not equal）
```

### 2. 按照文件权限进行判断

```shell
-r 有读的权限（read）       
-w 有写的权限（write）
-x 有执行的权限（execute）
```

### 3. 按照文件类型进行判断

```shell
-f 文件存在并且是一个常规的文件（file）
-e 文件存在（existence）    
-d 文件存在并是一个目录（directory）

-r 文件名    如果文件存在并可读，返回true
-s 文件名    如果文件存在并且不为空，返回true
-w 文件名    如果文件存在并可写，返回true
-x 文件名    如果文件存在并可执行，返回true
```

```shell
[ condition ] && echo true || echo false
```

# 6. 流程控制（重点）

## 1. if判断

```shell
if [ 条件判断式 ];then 
  程序 
fi 
```

```shell
if  [ 条件判断式 ] 
  then 
    程序 
fi
```

```shell
if [ 条件判断式 ] 
  then 
    程序 
elif [ 条件判断式 ]
	then
		程序
else
	程序
fi
```

1. [ 条件判断式 ]，中括号和条件判断式之间必须有空格
2. if后要有空格

## 2. case语句

```shell
case $变量名 in 
  "值1"） 
    如果变量的值等于值1，则执行程序1 
    ;; 
  "值2"） 
    如果变量的值等于值2，则执行程序2 
   ;; 
  …省略其他分支… 
  *） 
    如果变量的值都不是以上的值，则执行此程序 
    ;; 
esac
```

1. case行尾必须为单词`in`，每一个模式匹配必须以右括号`）`结束。
2. 双分号`;;`表示命令序列结束，相当于java中的break。
3. 最后的`*）`表示默认模式，相当于java中的default。

```shell
#!/bin/bash

case $1 in
"1")
        echo "banzhang"
;;

"2")
        echo "cls"
;;
*)
        echo "songsong"
;;
esac
```

## 3. for循环

```shell
for (( 初始值;循环控制条件;变量变化 )) 
  do 
    程序 
done
```

```shell
# 案例
s=0
for((i=0;i<=100;i++))
do
        s=$[$s+$i]
done
echo $s
```

```shell
for 变量 in 值1 值2 值3… 
  do 
    程序 
  done
```

```shell
#!/bin/bash
#打印 $* 和 $@ 的区别

for i in $*
do
      echo "ban zhang love $i "
done

echo "========="

for j in $@
do      
        echo "ban zhang love $j"
done

echo "========="

for i in "$*"
do
      echo "ban zhang love $i "
done

echo "========="

for j in "$@"
do      
        echo "ban zhang love $j"
done
```

```shell
[root@hadoop101 datas]$ bash for.sh cls xz bd
ban zhang love cls 
ban zhang love xz 
ban zhang love bd 
=========
ban zhang love cls
ban zhang love xz
ban zhang love bd
=========
ban zhang love cls xz bd
=========
ban zhang love cls
ban zhang love xz
ban zhang love bd
```

## 4. while循环

```shell
while [ 条件判断式 ] 
  do 
    程序
  done
```

```shell
s=0
i=1
while [ $i -le 100 ]
do
        s=$[$s+$i]
        i=$[$i+1]
done

echo $s
```

# 7. read读取控制台输入

```
read(选项)(参数)
```

选项

- -p：指定读取值时的提示符；

- -t：指定读取值时等待的时间（秒）。不写就结束命令

参数

- 变量：指定读取值的变量名

# 8. 函数

## 1. 系统函数

### 1. basename

basename命令会删掉所有的前缀包括最后一个（‘/’）字符，然后将字符串显示出来。

```shell
basename [string / pathname] [suffix]
#suffix为后缀，如果suffix被指定了，basename会将pathname或string中的suffix去掉。
```

```shell
[root@hadoop101 datas]$ basename /home/atguigu/banzhang.txt 
banzhang.txt

[root@hadoop101 datas]$ basename /home/atguigu/banzhang.txt .txt
banzhang
```

### 2. dirname

从给定的包含绝对路径的文件名中去除文件名（非目录的部分），然后返回剩下的路径（目录的部分）

```shell
dirname 文件绝对路径
```

```shell
[root@hadoop101 ~]$ dirname /home/atguigu/banzhang.txt 
/home/atguigu
```

## 2. 自定义函数

```shell
[ function ] funname[()]
{
	Action;
	[return int;]
}
funname
```

（1）必须在调用函数地方之前，先声明函数，shell脚本是逐行运行。不会像其它语言一样先编译。

（2）函数返回值，只能通过$?系统变量获得。

（3）函数可以加return返回，如果不加，将以最后一条命令运行结果，作为返回值。return后跟数值n(0-255)

```shell
#!/bin/bash
function sum()
{
    s=0
    s=$[ $1 + $2 ]
    echo "$s"
    if [ $s -lt 60 ];then
        echo "重考"
    else
        echo "及格"
    fi
    return 21;
}

read -p "input num1:" num1
read -p "input num2:" num2

sum $num1 $num2
echo $?
echo "================="
```

```
[root@hadoop20 shell]# bash function.sh 
input num1:10
input num2:50
60
及格
21
```

# 9. shell工具

## 0. 获取文件行数

```shell
cat file.txt | wc -l

awk '{print NR}' file.txt | tail -n1

awk 'END{print NR}' file.txt 

grep -nc "" file.txt 

grep -c "" file.txt 

grep -vc "^$" file.txt 

grep -n "" file.txt|awk -F: '{print '}|tail -n1 | cut -d: -f1

grep -nc "" file.txt

sed -n "$=" file.txt 

wc -l file.txt 

cat file.txt | wc -l

wc -l file.txt | cut -d' ' -f1
```

输出文件第10行，如小于，则输出最后一行，并告知文件小于10行

```shell
row_num=$(cat file.txt | wc -l)
echo $row_num
if [ $row_num -lt 10 ];then
    echo "The number of row is less than 10"
else
    awk '{if(NR==10){print $0}}' file.txt
fi
```



## 1. cut

在文件中负责剪切数据用的

```shell
cut [选项参数]  filename                  默认分隔符是制表符
```

| 选项参数 | 功能                         |
| -------- | ---------------------------- |
| -f       | 列号，提取第几列             |
| -d       | 分隔符，按照指定分隔符分割列 |
| -c       | 指定具体的字符               |

```shell
[root@hadoop101 datas]$ echo $PATH
/usr/lib64/qt-3.3/bin:/usr/local/bin:/bin:/usr/bin:/usr/local/sbin:/usr/sbin:/sbin:/home/atguigu/bin

[root@hadoop101 datas]$ echo $PATH | cut -d: -f 2-
/usr/local/bin:/bin:/usr/bin:/usr/local/sbin:/usr/sbin:/sbin:/home/atguigu/bin
```

## 2. sed（了解）

所有的操作不会改变文件

```
sed [选项参数]  ‘command’  filename
```

| 选项参数              | 功能                                  |
| --------------------- | ------------------------------------- |
| -e                    | 直接在指令列模式上进行sed的动作编辑。 |
| -i                    | 直接编辑文件                          |
| -n或--quiet或--silent | 仅显示script处理后的结果              |

| 命令 | 功能描述                                                     |
| ---- | ------------------------------------------------------------ |
| a    | 新增，a的后面可以接字串，在下一行出现                        |
| d    | 删除                                                         |
| s    | 查找并替换   ，‘g’表示global，全部替换                       |
| p    | 打印，亦即将某个选择的数据印出。通常 p 会与参数 sed -n 一起运行～ |

```shell
# 插入到第2行下面
[root@hadoop101 datas]$ sed '2a mei nv' sed.txt     
dong shen
guan zhen
mei nv
wo  wo
lai  lai

le  le
#删除sed.txt文件所有包含wo的行
[root@hadoop101 datas]$ sed '/wo/d' sed.txt
dong shen
guan zhen
lai  lai

le  le

# 将sed.txt文件中的第二行删除并将wo替换为ni
[root@hadoop101 datas]$ sed -e '2d' -e 's/wo/ni/g' sed.txt 
dong shen
ni  ni
lai  lai

le  le

#仅列出 /etc/passwd 文件内的第 5-7 行
[root@www ~]# nl /etc/passwd | sed -n '5,7p'
5 lp:x:4:7:lp:/var/spool/lpd:/sbin/nologin
6 sync:x:5:0:sync:/sbin:/bin/sync
7 shutdown:x:6:0:shutdown:/sbin:/sbin/shutdown
```

## 3. awk

把文件逐行的读入，以空格为默认分隔符将每行切片，切开的部分再进行分析处理。

```shell
awk [选项参数] ‘pattern1{action1}  pattern2{action2}...’filename 
```

pattern：表示AWK在数据中查找的内容，就是匹配模式

action：在找到匹配内容时所执行的一系列命令

| 选项参数 | 功能                 |
| -------- | -------------------- |
| -F       | 指定输入文件折分隔符 |
| -v       | 赋值一个用户定义变量 |

```shell
# 搜索passwd文件以root关键字开头的所有行，并输出该行的第7列。
[root@hadoop101 datas]$ awk -F: '/^root/{print $7}' passwd 
/bin/bash
#输出该行的第1，7列
[root@hadoop101 datas]$ awk -F: '/^root/{print $1","$7}' passwd 
root,/bin/bash
# 在所有行前面添加列名user，shell在最后一行添加"dahaige，/bin/zuishuai"。
#BEGIN 在所有数据读取行之前执行；END 在所有数据执行之后执行。
[root@hadoop101 datas]$ awk -F : 'BEGIN{print "user, shell"} /^root/{print $1","$7} END{print "dahaige,/bin/zuishuai"}' passwd
user, shell
root,/bin/bash
dahaige,/bin/zuishuai
#将passwd文件中的用户id增加数值1并输出
[root@hadoop101 datas]$ awk -v i=1 -F: '{print $3+i}' passwd
1
2
3
4
```

| 变量     | 说明                                   |
| -------- | -------------------------------------- |
| FILENAME | 文件名                                 |
| NR       | 已读的记录数                           |
| NF       | 浏览记录的域的个数（切割后，列的个数） |

```shell
[root@VM_0_13_centos leetcode]# awk '{print "filename:" FILENAME ,", linenum:" NR,", columns:" NF}' words.txt 
filename:words.txt , linenum:1 , columns:6
filename:words.txt , linenum:2 , columns:4

#切分ip
[root@VM_0_13_centos leetcode]# ifconfig eth0 | grep inet | awk '{print $2}'
197.251.10.13

#输出空行的行号
[root@VM_0_13_centos leetcode]# awk '/^$/{print NR}'  words.txt
```

## 4. sort

将文件进行排序，并将排序结果标准输出。

```
sort(选项)(参数)
```

| 选项 | 说明                     |
| ---- | ------------------------ |
| -n   | 依照数值的大小排序       |
| -r   | 以相反的顺序来排序       |
| -t   | 设置排序时所用的分隔字符 |
| -k   | 指定需要排序的列         |

参数：指定待排序的文件列表

## 5. wc

计算数字。利用wc指令我们可以计算文件的Byte数、字数或是列数。

```
wc [选项参数] filename
```

| 选项参数 | 功能             |
| -------- | ---------------- |
| -l       | 统计文件行数     |
| -w       | 统计文件的单词数 |
| -m       | 统计文件的字符数 |
| -c       | 统计文件的字节数 |

# 10. 正则表达式

**what**

使用单个字符串来描述、匹配一系列符合某个句法规则的字符串

**where**

在Linux中，grep，sed，awk等命令都支持通过正则表达式进行模式匹配。

**why**

正则表达式通常被用来检索、替换那些符合某个模式的文本。

## 1. 常规匹配

一串不包含特殊字符的正则表达式匹配它自己

## 2. 常用特殊字符

| 特别字符 | 描述                                                         |
| :------- | :----------------------------------------------------------- |
| $        | 匹配输入字符串的结尾位置。如果设置了 RegExp 对象的 Multiline 属性，则 $ 也匹配 '\n' 或 '\r'。要匹配 $ 字符本身，请使用 \$。 |
| ( )      | 标记一个子表达式的开始和结束位置。子表达式可以获取供以后使用。要匹配这些字符，请使用 \( 和 \)。 |
| *        | 匹配前面的子表达式零次或多次。要匹配 * 字符，请使用 \*。     |
| +        | 匹配前面的子表达式一次或多次。要匹配 + 字符，请使用 \+。     |
| .        | 匹配除换行符 \n 之外的任何单字符。要匹配 . ，请使用 \. 。    |
| [        | 标记一个中括号表达式的开始。要匹配 [，请使用 \[。            |
| ?        | 匹配前面的子表达式零次或一次，或指明一个非贪婪限定符。要匹配 ? 字符，请使用 \?。 |
| \        | 将下一个字符标记为或特殊字符、或原义字符、或向后引用、或八进制转义符。例如， 'n' 匹配字符 'n'。'\n' 匹配换行符。序列 '\\' 匹配 "\"，而 '\(' 则匹配 "("。 |
| ^        | 匹配输入字符串的开始位置，除非在方括号表达式中使用，当该符号在方括号表达式中使用时，表示不接受该方括号表达式中的字符集合。要匹配 ^ 字符本身，请使用 \^。 |
| {        | 标记限定符表达式的开始。要匹配 {，请使用 \{。                |
| \|       | 指明两项之间的一个选择。要匹配 \|，请使用 \|。               |

### 1. `^`匹配一行的开头

### 2. `$`匹配一行的结束

**^$ 匹配空行**

### 3. `.`匹配一个任意的字符

### 4. `*`匹配一行的开头

不单独使用，他和`.`连用，表示匹配`.`0次或多次

**`.* `匹配所有文件类型  or  匹配文件内所有内容（`.`是任意字符）**

### 1. `^`匹配一行的开头

### 5. `[]`匹配某个范围内的一个字符

- `[6,8]`------匹配6或者8

- `[a-z]`------匹配一个a-z之间的字符

- `[a-z]*`-----匹配任意字母字符串

- `[a-c, e-f]`-匹配a-c或者e-f之间的任意字符

### 6. `\`转义

不会单独使用。想匹配某一特殊字符本身时,将转义字符和特殊字符连用，来表示特殊字符本身.

## 3. 非打印字符

| 字符 | 描述                                                         |
| :--- | :----------------------------------------------------------- |
| \cx  | 匹配由x指明的控制字符。例如， \cM 匹配一个 Control-M 或回车符。x 的值必须为 A-Z 或 a-z 之一。否则，将 `c` 视为一个原义的 `'c'` 字符。 |
| \f   | 匹配一个换页符。等价于 `\x0c` 和 `\cL`。                     |
| \n   | 匹配一个换行符。等价于 `\x0a` 和 `\cJ`。                     |
| \r   | 匹配一个回车符。等价于 `\x0d` 和 `\cM`。                     |
| \s   | 匹配任何空白字符，包括空格、制表符、换页符等等。等价于` [ \f\n\r\t\v]`。注意 Unicode 正则表达式会匹配全角空格符。 |
| \S   | 匹配任何非空白字符。等价于 `[^ \f\n\r\t\v]`。                |
| \t   | 匹配一个制表符。等价于 `\x09` 和 `\cI`。                     |
| \v   | 匹配一个垂直制表符。等价于 `\x0b` 和 `\cK`。                 |

## 4. 限定符

用来指定正则表达式的一个给定组件必须要出现多少次才能满足匹配

限定符出现在范围表达式之后。因此，它应用于整个范围表达式

| 字符  | 描述                                                         |
| :---- | :----------------------------------------------------------- |
| *     | 匹配前面的子表达式零次或多次。例如，zo* 能匹配 "z" 以及 "zoo"。* 等价于{0,}。 |
| +     | 匹配前面的子表达式一次或多次。例如，'zo+' 能匹配 "zo" 以及 "zoo"，但不能匹配 "z"。+ 等价于 {1,}。 |
| ?     | 匹配前面的子表达式零次或一次。例如，"do(es)?" 可以匹配 "do" 、 "does" 中的 "does" 、 "doxy" 中的 "do" 。? 等价于 {0,1}。 |
| {n}   | n 是一个非负整数。匹配确定的 n 次。例如，'o{2}' 不能匹配 "Bob" 中的 'o'，但是能匹配 "food" 中的两个 o。 |
| {n,}  | n 是一个非负整数。至少匹配n 次。例如，'o{2,}' 不能匹配 "Bob" 中的 'o'，但能匹配 "foooood" 中的所有 o。'o{1,}' 等价于 'o+'。'o{0,}' 则等价于 'o*'。 |
| {n,m} | m 和 n 均为非负整数，其中n <= m。最少匹配 n 次且最多匹配 m 次。例如，"o{1,3}" 将匹配 "fooooood" 中的前三个 o。'o{0,1}' 等价于 'o?'。请注意在逗号和两个数之间不能有空格。 |

`*` 和 `+` 限定符都是贪婪的，因为它们会尽可能多的匹配文字，只有在它们的后面加上一个 `? `就可以实现非贪婪或最小匹配。