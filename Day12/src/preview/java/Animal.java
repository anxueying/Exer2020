package preview.java;
/*
1、static关键字可以修饰什么？
2、static关键字的特点
3、说出 "==" 和  equals 的区别
 */
class Animal{
    public String noise(){ 
         return "pe0ep"; 
    }

    public static void main(String[] args) {
        Animal animal = new Dog();
        Cat cat = (Cat)animal;
        System.out.println(cat.noise());
    }
}

class Dog extends Animal{
    public String noise(){ 
        return "bark"; 
     }
}

class Cat extends Animal{
    public String noise(){ 
        return "meow"; 
    }
}

