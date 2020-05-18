package homework;

public class ExerTest2 {
    public static void main(String[] args) {
        int[] arr1 = new int[20];
        ExerTest2 et = new ExerTest2();
        et.intArray(arr1);

        for(int i:arr1){
            System.out.println(i);
        }
    }

    public void intArray(int[] array){
        int num = 2;
        for(int i=1;i<array.length;i++){
            array[i] = num;
            num += 2;
        }
    }
}
