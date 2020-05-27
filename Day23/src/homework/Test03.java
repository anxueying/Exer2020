package homework;

import java.lang.reflect.ParameterizedType;

/**
 * @author Mrs.An Xueying
 * 2020/5/28 2:38
 */

public class Test03 {
	public static void main(String[] args) {
		SubA a = new SubA();
		System.out.println(a.getType());
		
		SubB b = new SubB();
		System.out.println(b.getType());
	}
}
abstract class Base<T>{
	private Class type;
	
	public Base(){
		//为type属性赋值为T的实际类型
		//1. 获取当前类名（继承Base的子类的全类名）
		Class<? extends Base> clazz = this.getClass();

		try {
			//2. 获取父类的参数
			ParameterizedType pt = (ParameterizedType) clazz.getGenericSuperclass();
			//3. 清洗参数名称为 type
			type = (Class) pt.getActualTypeArguments()[0];
		} catch (Exception e) {
			//4. 无参数则为Object类
			type = Object.class;
		}

	}

	public Class getType() {
		return type;
	}
}
class SubA extends Base<String>{

}
class SubB extends Base{

}