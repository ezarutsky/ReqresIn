package LessonsStream;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class StreamThree {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Daenerys", "Tyrion", "", null, "Arya");

        names.stream()
                .filter(Objects::nonNull)
                .filter(i-> !i.isEmpty())
                .filter(i-> i.contains("a"))
                .forEach(System.out::println);



//        for(int i = 0; i < names.size(); i++) {
//            if(names.get(i) != null && !names.get(i).isEmpty() && names.get(i).contains("a")) {
//                System.out.println(names.get(i));
//            }
//        }


//        for (String name : names) {
//            if (name != null && !name.isEmpty() && name.contains("a")) {
//                System.out.println(name);
//            }
//        }
    }
}
