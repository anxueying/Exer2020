//通过javadoc生成说明文档，并使用三种注释方法
/**
@author Shelly
@version v1.0
*/
public class AnnotationTest{
	/**
	用法: javadoc [options] [packagenames] [sourcefiles] [@files]
	  -overview <file>                 从 HTML 文件读取概览文档
	  -public                          仅显示 public 类和成员
	  -protected                       显示 protected/public 类和成员 (默认值)
	  -package                         显示 package/protected/public 类和成员
	  -private                         显示所有类和成员
	  -help                            显示命令行选项并退出
	  -doclet <class>                  通过替代 doclet 生成输出
	  -docletpath <path>               指定查找 doclet 类文件的位置
	  -sourcepath <pathlist>           指定查找源文件的位置
	  -classpath <pathlist>            指定查找用户类文件的位置
	  -cp <pathlist>                   指定查找用户类文件的位置
	  -exclude <pkglist>               指定要排除的程序包列表
	  -subpackages <subpkglist>        指定要递归加载的子程序包
	  -breakiterator                   计算带有 BreakIterator 的第一个语句
	  -bootclasspath <pathlist>        覆盖由引导类加载器所加载的
									   类文件的位置
	  -source <release>                提供与指定发行版的源兼容性
	  -extdirs <dirlist>               覆盖所安装扩展的位置
	  -verbose                         输出有关 Javadoc 正在执行的操作的信息
	  -locale <name>                   要使用的区域设置, 例如 en_US 或 en_US_WIN
	  -encoding <name>                 源文件编码名称
	  -quiet                           不显示状态消息
	  -J<flag>                         直接将 <flag> 传递到运行时系统
	  -X                               输出非标准选项的提要
	*/
	public static void main(String args[]){
		System.out.println("ok");
	}
}