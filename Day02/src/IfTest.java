class IfTest{
	public static void main(String[] args){
		int age = 18;
		// 条件判断 格式1
		if(age >= 18){
			System.out.println("快上车，就等你了");
		}
		// 条件判断 格式2
		if(age >= 18){
			System.out.println("快上车，就等你了");
		}else{
			System.out.println("这不是去幼儿园的车，快下车！");
		}
		// 条件判断 格式3
		if(age < 0){
			System.out.println("这不是去幼儿园的车，快下车！");
		}else if(age < 18){
			System.out.println("你当我傻");
		}else if(age > 60){
			System.out.println("去跳广场舞吧，樯橹灰飞烟灭");
		}else{
			System.out.println("快上车，就等你了");
		}
		System.out.println("测试结束");
	}
}