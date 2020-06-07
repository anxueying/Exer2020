
import java.util.*;

/**
 * @author Mrs.An Xueying
 * 2020/6/1 23:48
 */
public class Test {
    public static void main(String[] args) {
        int[] candies = {4,2,1,1,2};
        int extraCandies = 1;
        int maxCandies = candies[0];
        List<Boolean> result = new ArrayList<>();
        for(int candie:candies){
            if(candie>maxCandies){
                maxCandies = candie;
            }
        }

        for(int candie:candies){
            boolean flag = (candie+extraCandies)>=maxCandies;
            result.add(flag);
        }

        for (Boolean aBoolean : result) {
            System.out.println(aBoolean);
        }
    }
}

