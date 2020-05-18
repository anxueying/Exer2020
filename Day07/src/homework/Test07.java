package homework;

public class Test07 {
    public static void main(String[] args) {
        int[] arrTest = new int[]{3,9,10,8,30,29};
        MyArrays ma = new MyArrays();

        ma.print(arrTest);//打印

        ma.sort(arrTest);//排序
        ma.print(arrTest);//打印排序结果

        int[] arrCopyTest = ma.copy(arrTest,3);//copy3个
        ma.print(arrCopyTest);//打印copy结果

        //查找下标
        System.out.println(ma.indexOf(arrCopyTest,30));
        System.out.println(ma.indexOf(arrCopyTest,9));
    }
}

class MyArrays{
    public void sort(int[] arr){
        //冒泡排序：为任意一维整型数组arr实现从小到大排序
        for(int i = 0;i<arr.length;i++){
            //循环数组每个元素，当i = arr.length-1时,j=i,不经历下面的循环，循环结束
            for(int j=0;j<arr.length-i-1;j++){ //当i=0时，j比较所有元素
                //当i=2时,j不比较数组索引位置为0的元素，因为已经是最小了。接着每次循环需要比较的数减少一个，直至比较完毕
                if (arr[j]>arr[j+1]){
                    //如果前者大于后者
                    int temp = arr[j]; //中间变量：小值
                    arr[j]=arr[j+1];//前者变小
                    arr[j+1] = temp;//后者变大
                }
            }
        }
    }

    public int indexOf(int[] arr,int value){
        //在任意一维整型数组arr中查找value值的下标，如果不存在返回-1
        int result = -1;
        for(int i=0;i<arr.length;i++){
            if (arr[i]==value){
                result = i;
            }
        }
        return result;
    }

    public int[] copy(int[] arr,int len){
        //从任意一维数组arr中复制一个新数组返回，新数组的长度为len，从arr[0]开始复制
        int[] arrCopy = new int[len];
        for(int i =0;i<len;i++){
            arrCopy[i] = arr[i];
        }
        return arrCopy;
    }

    public void print(int[] arr){
        //打印数组的元素，效果：[1,2,3,4,5]
        String arrLine = "[";
        for(int i=0;i<arr.length;i++){
            if (i==arr.length-1){
                arrLine += arr[i] + "]";
            }else{
                arrLine += arr[i] + ",";
            }
        }
        System.out.println(arrLine);
    }
}
