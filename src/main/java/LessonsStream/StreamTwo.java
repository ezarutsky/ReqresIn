package LessonsStream;

import java.util.Arrays;
import java.util.List;

public class StreamTwo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Jan", "Tirion", "Marry", "Nikolas");

        long count = names.stream()
                .filter(i-> i.length() > 4)
                .count();
        System.out.println(count);






//        int count = 0;
//        for(int i = 0; i < names.size(); i++) {
//            if(names.get(i).length() > 4) {
//                count++;
//            }
//        }
//        System.out.println(count);
    }
}

