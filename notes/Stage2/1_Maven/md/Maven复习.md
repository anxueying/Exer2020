# Maven复习

## 1. Why 为什么要使用Maven

现有技术的不足及Maven做了什么

| 序号 | 技术操作                 | 不足之处                                                | Maven的操作                                        | Maven的优势                            |
| ---- | ------------------------ | ------------------------------------------------------- | -------------------------------------------------- | -------------------------------------- |
| 1    | 添加第三方jar包          | 复制粘贴造成工作区中大量重复的文件                      | 每个jar包只在本地仓库中保存一份，维护坐标即可      | 节约存储空间，避免重复文件太多造成混乱 |
| 2    | jar包之间的依赖关系      | 当使用一个jar包时，需要将所有依赖包及依赖包的依赖包导入 | 自动的将当前jar包所依赖的其他所有jar包全部导入进来 | 节约了大量时间和精力                   |
| 3    | 处理jar包之间的冲突      | jar包多了，发生冲突，导致项目无法正常工作               | 完全统一规范的jar包管理体系                        | 规范、完整、准确，减少工作量           |
| 4    | 将项目拆分成多个工程模块 | 项目规模越大，团队分工、项目模块之间的协同开发越复杂    | 依赖管理机制                                       | 轻松拆分模块，并完成不同模块间的API    |
| 5    | 实现项目的分布式部署     | 每个模块都需要运行在独立的服务器上                      | 依赖管理机制                                       | 轻松拆分模块，并完成不同模块间的API    |

## 2. What Maven是什么

### 1. 自动化构建工具

注服务于Java平台的项目**构建**和**依赖管理**。与其功能相似的还有Gradle

### 2. 构建的概念

#### 构建不是创建

创建一个工程并不等于构建一个项目

#### 工程

①纯Java代码（原材料）：.java源文件

②Web工程（生产）：未编译（鸡），已编译（煮熟的鸡），编译部署（煮）

![image-20200604104834876](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200604104835.png)

③实际项目（成品）：整合第三方框架，将所有这些资源都必须按照正确的目录结构部署到服务器上

### 3. 构建环节

1)    **清理：**删除以前的编译结果，为重新编译做好准备。

2)    **编译：**将Java源程序编译为字节码文件。

3)    **测试：**针对项目中的关键点进行测试，确保项目在迭代开发过程中关键点的正确性。

4)    **报告：**在每一次测试后以标准的格式记录和展示测试结果。

5)    **打包：**将一个包含诸多文件的工程封装为一个压缩文件用于安装或部署。Java工程对应jar包，Web工程对应war包。

6)    **安装：在Maven环境下特指将打包的结果——jar包或war包安装到本地仓库中。**

7）**部署：**将打包的结果部署到远程仓库或将war包部署到服务器上运行。

### 4. 自动化构建

使用IDEA的工作流程：

![image-20200604105204318](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200604105204.png)

上述的操作在使用Maven后，交给机器自动完成：

![image-20200604105257424](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200604105257.png)

Maven如何实现：

自动的从构建过程的起点一直执行到终点（参考构建环节）

### 5. [Maven核心概念](#4. Maven的核心概念)

#### 1)    POM

#### 2)    约定的目录结构

#### 3)    坐标

#### 4)    依赖管理

#### 5)    仓库管理

#### 6)    生命周期

#### 7)    插件和目标

#### 8)    继承

#### 9)    聚合

## 3. How Maven如何使用

### 1. 安装Maven核心程序

1)    检查JAVA_HOME环境变量。Maven是使用Java开发的，所以必须知道当前系统环境中JDK的安装目录。

```
C:\Windows\System32>echo %JAVA_HOME%  E:\java\jdk1.8.0_45
```

2)    解压Maven的核心程序。

将apache-maven-3.5.4-bin.zip解压到一个**非中文无空格**的目录下。例如：

```
E:\apache-maven-3.5.4     
```

3)    配置环境变量。

| 环境变量名称（新建）：M2_HOME |
| ----------------------------- |
| E:\apache-maven-3.5.4         |

| 环境变量名称（添加）：path |
| -------------------------- |
| %M2_HOME%\bin              |

### 2. 第一个Maven工程

#### 1. 创建约定的目录结构

- main目录用于存放主程序。
- test目录用于存放测试程序。
- java目录用于存放源代码文件。
- resources目录用于存放配置文件和资源文件。

#### 2. 创建Maven的核心配置文件pom.xml

```xml
<?xml version="1.0" ?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.atguigu.maven</groupId>
	<artifactId>Hello</artifactId>
	<version>0.0.1-SNAPSHOT</version>

	<name>Hello</name>
	  
	<dependencies>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.0</version>
			<scope>test</scope>
		</dependency>
	</dependencies>
</project>

```

#### 3. 编写主代码

在src/main/java/com/atguigu/maven目录下新建文件Hello.java

#### 4. 编写测试代码

在/src/test/java/com/atguigu/maven目录下新建测试文件HelloTest.java

#### 5. 运行基本的Maven命令

打开cmd命令行，进入Hello项目根目录(pom.xml文件所在目录)执行

```
cmd 中录入 mvn  compile命令, 查看根目录变化
cmd 中继续录入mvn clean命令，然后再次查看根目录变化
cmd 中录入 mvn  compile命令, 查看根目录变化
cmd 中录入 mvn  test-compile命令， 查看target目录的变化
cmd 中录入 mvn  test命令，查看target目录变化
cmd 中录入 mvn  package命令,查看target目录变化
cmd 中录入 mvn  install命令， 查看本地仓库的目录变化
```

注意：运行Maven命令时一定要进入pom.xml文件所在的目录！

### 3. 配置文件

Maven的核心程序并不包含具体功能，具体功能由插件来完成。插件存储在本地仓库，如没有，则从远程中央仓库下载。如无法联网，则无法执行Maven。

#### 1. 配置本地仓库

将Maven的本地仓库指向一个在联网情况下下载好的目录。

1)    Maven默认的本地仓库：~\.m2\repository目录。

Tips：~表示当前用户的家目录。

2)    Maven的核心配置文件位置：

  解压目录 E:\apache-maven-3.5.4\conf\settings.xml  

3)    设置方式

```xml
<localRepository>以及准备好的仓库位置</localRepository>
```

比如：

```xml
<localRepository>D:\developer_tools\apache-maven-3.5.4\repo</localRepository>
```

#### 2. 配置阿里云镜像

为了以后下载jar包方便

```xml
<mirror>
    <id>nexus-aliyun</id>
    <mirrorOf>central</mirrorOf>
    <name>Nexus aliyun</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public</url>
</mirror>
```

#### 3. 配置jdk版本（可选）

为了不报版本1.5warning

```xml
	<profile>
		<id>jdk1.8</id>
		<activation>
			<activeByDefault>true</activeByDefault>
			<jdk>1.8<k>
		</activation>
		<properties>
			<maven.compiler.source>1.8</maven.compiler.source>
			<maven.compiler.target>1.8</maven.compiler.target>
			<maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
		</properties>
	</profile>
```

### 4. IDEA中配置Maven

注意：生效范围

![image-20200604110954770](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200604110954.png)

1)    设置maven的安装目录及本地仓库 ![image-20200604111043004](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200604111043.png)          

- Maven home directory：可以指定本地 Maven 的安装目录所在，因为我已经配置了 M2_HOME 系统参数，所以直接这样配置 IntelliJ IDEA 是可以找到的。但是假如你没有配置的话，这里可以选择你的 Maven 安装目录。此外，这里不建议使用IDEA默认的。
- User settings file / Local repository：我们还可以指定 Maven 的 settings.xml 位置和本地仓库位置。

2)    配置Maven自动导入依赖的jar包

![image-20200604111127927](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200604111127.png)

- Import Maven projects automatically：表示 IntelliJ IDEA 会实时监控项目的 pom.xml 文件，进行项 目变动设置，勾选上。
- Automatically download：在 Maven 导入依赖包的时候是否自动下载源码和文档。默认是没有勾选的，也不建议勾选，原因是这样可以加快项目从外网导入依赖包的速度，如果我们需要源码和文档的时候我们到时候再针对某个依赖包进行联网下载即可。IntelliJ IDEA 支持直接从公网下载源码和文档的。
- VM options for importer：可以设置导入的 VM 参数。一般这个都不需要主动改，除非项目真的导入太慢了我们再增大此参数。         

### 5. IDEA中创建Maven Module

1)    右键→new Module→Maven

![image-20200604111314161](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200604111314.png)

2)    点击Next，配置坐标

![image-20200604111337113](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200604111337.png)

3)    点击Next，给Module命名

4)    目录结构及说明

5)    配置Maven的核心配置文件pom.xml

6)    编写主代码、测试代码

7)    使用Maven的方式运行Maven工程

![image-20200604111449731](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200604111449.png)

### 6. Maven打包插件

本身的打包插件不负责将依赖的jar包一并打入到jar包中，需要一款能够将项目所依赖的jar包 一并打入到jar中的插件来解决这些问题。

```xml
<build>
    <plugins>
        <plugin>
            <artifactId>maven-assembly-plugin</artifactId>
            
            <configuration>
                <descriptorRefs>
                    <descriptorRef>jar-with-dependencies</descriptorRef>
                </descriptorRefs>      
                <archive>
                    <manifest>
                        <!-- 指定主类。会把其依赖的包一起打成jar包-->
                        <mainClass>xxx.xxx.XXX</mainClass>
                    </manifest>
                </archive>
            </configuration>
            
            <executions>
                <execution>                
                    <id>make-assembly</id>
                    <phase>package</phase>
                    <goals>
                        <goal>single</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

## 4. Maven的核心概念

### 1. POM

Project Object Model：项目对象模型，Maven工程的核心配置。

学习Maven就是学习pom.xml文件中的配置。

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0http://maven.apache.org/xsd/maven-4.0.0.xsd">  
  
    <!-- 模型版本。maven2.0必须是这样写，现在是maven2唯一支持的版本 -->  
    <modelVersion>4.0.0</modelVersion>  
  
    <!-- 公司或者组织的唯一标志，并且配置时生成的路径也是由此生成， 如com.winner.trade，maven会将该项目打成的jar包放本地路径：/com/winner/trade -->  
    <groupId>com.winner.trade</groupId>  
  
    <!-- 本项目的唯一ID，一个groupId下面可能多个项目，就是靠artifactId来区分的 -->  
    <artifactId>trade-core</artifactId>  
  
    <!-- 本项目目前所处的版本号 -->  
    <version>1.0.0-SNAPSHOT</version>  
  
    <!-- 打包的机制，如pom,jar, maven-plugin, ejb, war, ear, rar, par，默认为jar -->  
    <packaging>jar</packaging>  
  
    <!-- 帮助定义构件输出的一些附属构件,附属构件与主构件对应，有时候需要加上classifier才能唯一的确定该构件 不能直接定义项目的classifer,因为附属构件不是项目直接默认生成的，而是由附加的插件帮助生成的 -->  
    <classifier>...</classifier>  
  
    <!-- 定义本项目的依赖关系 -->  
    <dependencies>  
  
        <!-- 每个dependency都对应这一个jar包 -->  
        <dependency>  
  
            <!--一般情况下，maven是通过groupId、artifactId、version这三个元素值（俗称坐标）来检索该构件， 然后引入你的工程。如果别人想引用你现在开发的这个项目（前提是已开发完毕并发布到了远程仓库），-->   
            <!--就需要在他的pom文件中新建一个dependency节点，将本项目的groupId、artifactId、version写入， maven就会把你上传的jar包下载到他的本地 -->  
            <groupId>com.winner.trade</groupId>  
            <artifactId>trade-test</artifactId>  
            <version>1.0.0-SNAPSHOT</version>  
  
            <!-- maven认为，程序对外部的依赖会随着程序的所处阶段和应用场景而变化，所以maven中的依赖关系有作用域(scope)的限制。 -->  
            <!--scope包含如下的取值：compile（编译范围）、provided（已提供范围）、runtime（运行时范围）、test（测试范围）、system（系统范围） -->  
            <scope>test</scope>  
  
            <!-- 设置指依赖是否可选，默认为false,即子项目默认都继承:为true,则子项目必需显示的引入，与dependencyManagement里定义的依赖类似  -->  
            <optional>false</optional>  
  
            <!-- 屏蔽依赖关系。 比如项目中使用的libA依赖某个库的1.0版，libB依赖某个库的2.0版，现在想统一使用2.0版，就应该屏蔽掉对1.0版的依赖 -->  
            <exclusions>  
                <exclusion>  
                    <groupId>org.slf4j</groupId>  
                    <artifactId>slf4j-api</artifactId>  
                </exclusion>  
            </exclusions>  
  
        </dependency>  
  
    </dependencies>  
  
    <!-- 为pom定义一些常量，在pom中的其它地方可以直接引用 使用方式 如下 ：${file.encoding} -->  
    <properties>  
        <file.encoding>UTF-8</file.encoding>  
        <java.source.version>1.8</java.source.version>  
        <java.target.version>1.8</java.target.version>  
    </properties>  
  
    ...  
</project> 
```



### 2. 约定的目录结构

约定>配置>编码

能用配置解决的问题就不编码，能基于约定的就不进行配置

Maven指定了特定文件保存的目录

### 3. 坐标

使用如下三个向量在Maven的仓库中唯一的确定一个Maven工程。

[1]groupId：公司或组织的域名倒序+当前项目名称

[2]artifactId：当前项目的模块名称

[3]version：当前模块的版本

```xml
	<groupId>com.atguigu.maven</groupId>
	<artifactId>Hello</artifactId>
	<version>0.0.1-SNAPSHOT</version>
```

**如何使用坐标**

[1]将gav三个向量连起来

```
com.atguigu.maven+Hello+0.0.1-SNAPSHOT
```

[2]以连起来的字符串作为目录结构到仓库中查找

```
com/atguigu/maven/Hello/0.0.1-SNAPSHOT/Hello-0.0.1-SNAPSHOT.jar
```

※注意：我们自己的Maven工程必须执行安装操作才会进入仓库。安装的命令是：mvn install

### 4. 依赖管理

#### **1. 什么是依赖**

当A jar包需要用到B jar包中的类时，我们就说A对B有依赖。

```xml
<dependency>
    <!--坐标-->
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.0</version>
    <!--依赖的范围-->
    <scope>test</scope>
</dependency>
```

任何一个Maven工程会根据坐标到本地仓库中去查找它所依赖的jar包。如果能够找到则可以正常工作，否则就不行。配置的基本形式是使用dependency标签指定目标jar包的坐标。

#### 2. 依赖的类型

如果A依赖B，B依赖C，`那么A→B和B→C都是**直接依赖**，而A→C是**间接依赖**。

#### 3. 依赖的范围

##### 1)    compile

[1]main目录下的Java代码**可以**访问这个范围的依赖

[2]test目录下的Java代码**可以**访问这个范围的依赖

[3]部署到Tomcat服务器上运行时**要**放在WEB-INF的lib目录下

##### 2)    test

[1]main目录下的Java代码**不能**访问这个范围的依赖

[2]test目录下的Java代码**可以**访问这个范围的依赖

[3]部署到Tomcat服务器上运行时**不会**放在WEB-INF的lib目录下

##### 3)    provided

[1]main目录下的Java代码**可以**访问这个范围的依赖

[2]test目录下的Java代码**可以**访问这个范围的依赖

[3]部署到Tomcat服务器上运行时**不会**放在WEB-INF的lib目录下

##### 4)    其他

runtime、import、system等。

![image-20200604112434685](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200604112434.png)

#### 4. 依赖的传递性

| Maven工程 |      |      | 依赖范围 | 对A的可见性 |
| --------- | ---- | ---- | -------- | ----------- |
|           |      | C    | compile  | √           |
| A         | B    | D    | test     | ×           |
|           |      | E    | provided | ×           |

#### 5. 依赖的原则

解决jar包冲突

1)    路径最短者优先

![image-20200604112649037](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200604112649.png)

2)    路径相同时先声明者优先

![image-20200604112658292](https://img-1258293749.cos.ap-chengdu.myqcloud.com/20200604112658.png)

这里“声明”的先后顺序指的是dependency标签配置的先后顺序。如下：

```xml
    <dependencies>
        <!--路径相同时，谁先声明那么就用谁的-->
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>Women</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>org.example</groupId>
            <artifactId>Man</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
```

#### 6. 依赖的排除

有的时候为了确保程序正确可以将有可能重复的间接依赖排除。

加入exclusions配置后可以在依赖OurFriends的时候排除版本为1.1.1的commons-logging的间 接依赖

```xml
<dependency>
    <groupId>com.atguigu.maven</groupId>
    <artifactId>OurFriends</artifactId>
    <version>1.0-SNAPSHOT</version>
    <!--依赖排除-->
    <exclusions>
            <exclusion>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>

<dependency>
    <groupId>commons-logging</groupId>
    <artifactId>commons-logging</artifactId>
    <version>1.1.2</version>
</dependency>
```

#### 7. 统一管理目标jar包的版本

未统一管理

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>4.0.0.RELEASE</version>
</dependency>
```

统一管理

```xml
<!--统一管理当前模块的jar包的版本-->
<properties>
    <spring.version>4.0.0.RELEASE</spring.version>
</properties>

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>${spring.version}</version>
</dependency>
```

### 5. 仓库

#### 1)    分类

①   本地仓库：为当前本机电脑上的所有Maven工程服务。

①   远程仓库

[1] 私服：架设在当前局域网环境下，为当前局域网范围内的所有Maven工程服务。

[2]中央仓库：架设在Internet上，为全世界所有Maven工程服务。

[3]中央仓库的镜像：架设在各个大洲，为中央仓库分担流量。减轻中央仓库的压力，同时更快的响应用户请求。

#### 2） 仓库中的文件

①   Maven的插件

①   我们自己开发的项目的模块

②   第三方框架或工具的jar包

​     ※不管是什么样的jar包，在仓库中都是按照坐标生成目录结构，所以可以通过统一的方式查

​     询或依赖。

### 6. 生命周期

三套生命周期**相互独立**，**运行任何一个阶段（Phrase）的时候，它前面的所有阶段都会被运行**。

`mvn clean install site` 运行所有这三套生命周期。

Maven的插件机制完全依赖Maven的生命周期。

#### Clean Lifecycle

在进行真正的构建之前进行一些清理工作。Clean的生命周期阶段（Phrase）如下：

- pre-clean 执行一些需要在clean之前完成的工作 

- clean 移除所有上一次构建生成的文件 

- post-clean 执行一些需要在clean之后立刻完成的工作 

#### Default Lifecycle（核心）

构建的核心部分，编译，测试，打包，安装，部署等等。Default的生命周期阶段（Phrase）如下：

```
validate
generate-sources
process-sources
generate-resources
process-resources 复制并处理资源文件，至目标目录，准备打包。
compile 编译项目的源代码。
process-classes
generate-test-sources
process-test-sources
generate-test-resources
process-test-resources 复制并处理资源文件，至目标测试目录。
test-compile 编译测试源代码。
process-test-classes
test 使用合适的单元测试框架运行测试。这些测试代码不会被打包或部署。
prepare-package
package 接受编译好的代码，打包成可发布的格式，如JAR。
pre-integration-test
integration-test
post-integration-test
verify
install将包安装至本地仓库，以让其它项目依赖。
deploy将最终的包复制到远程的仓库，以让其它开发人员与项目共享或部署到服务器上运行。
```

#### Site Lifecycle

生成项目报告，站点，发布站点。Site的生命周期阶段（Phrase）如下：

- pre-site 执行一些需要在生成站点文档之前完成的工作
- **site 生成项目的站点文档**
- post-site 执行一些需要在生成站点文档之后完成的工作，并且为部署做准备
- **site-deploy 将生成的站点文档部署到特定的服务器上**

### 7. 插件和目标

1)    Maven的核心仅仅定义了抽象的生命周期，具体的任务都是交由插件完成的。

2)    每个插件都能实现多个功能，每个功能就是一个插件目标。

3)    Maven的生命周期与插件目标相互绑定，以完成某个具体的构建任务。

例如：compile就是插件maven-compiler-plugin的一个功能；pre-clean是插件maven-clean-plugin的一个目标。

## 5. 继承

### 1. why 为什么需要继承机制

非compile范围的依赖不能在依赖链传递，多人合作开发时进行项目合并会发生问题。

使用继承机制就可以将这样的依赖信息统一提取到父工程模块中进行统一管理。

### 2. what 继承机制的实现

#### 1. 继承关系的实现

##### 1. 创建父工程

**父工程的打包方式为pom**，父工程只需要保留pom.xml文件即可

```xml
<groupId>com.atguigu.maven</groupId>
<artifactId>Parent</artifactId>
<packaging>pom</packaging>
<version>1.0-SNAPSHOT</version>
```

##### 2. 在子工程中引用父工程

```xml
<!--继承-->
<parent>
	<!-- 父工程坐标 -->
    <groupId>com.atguigu.maven</groupId>
    <artifactId>Parent</artifactId>
    <version>1.0-SNAPSHOT</version>
	<!--指定从当前pom.xml文件出发寻找父工程的pom.xml文件的相对路径-->
	<relativePath>../Parent/pom.xml</relativePath>
</parent>
```

此时如果子工程的groupId和version如果和父工程重复则可以删除。

#### 2. 在父工程中管理依赖

##### 方式1：dependencyManagement标签

dependencies标签，用dependencyManagement标签括起来。

```xml
<!--依赖管理-->
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

在子项目中重新指定需要的依赖，删除范围和版本号

```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
</dependency>
```

##### 方式2：不写dependencyManagemen

子项目中什么也不需要写也可有该依赖包。

```xml
<!--依赖管理-->
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

## 6. 聚合

### 1. why 为什么要使用聚合

多个模块逐个安装操作很麻烦，使用聚合可以批量进行安装、清理

### 2. what 怎么实现

总的聚合工程中使用modules/module标签组合

```xml
<!--聚合，无需考虑先后顺序，添加相对路径即可-->
<modules>
    <module>../MakeFriend</module>
    <module>../OurFriends</module>
    <module>../HelloFriend</module>
    <module>../Hello</module>
</modules>
```

Maven可以根据各个模块的继承和依赖关系自动选择安装的顺序

## 7. Maven酷站

我们可以到http://mvnrepository.com/搜索需要的jar包的依赖信息。把配置信息粘贴到pox.xml即可（maven会自行从仓库下载）

http://search.maven.org/