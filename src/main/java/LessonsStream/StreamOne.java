package LessonsStream;

import java.util.Arrays;
import java.util.List;

public class StreamOne {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println(numbers.get(1));


        numbers.stream()
                .filter(i -> i % 2 == 0)
                .forEach(System.out::println);


        //циклы

//        for(int i = 0; i < numbers.size(); i++) {
//            if (i % 2 == 0) {
//                System.out.println(i);
//
//            }
//        }

    //циклы
//        for (Integer i : numbers) {
//            if (i % 2 == 0) {
//                System.out.println(i);
//            }
//        }
//    }
    }
}

