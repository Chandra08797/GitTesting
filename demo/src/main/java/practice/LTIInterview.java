package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LTIInterview {

    public static void main(String[] args) {

        List<Employee> list1 = new ArrayList<>();

        list1.add(new Employee(1,"ram"));
        list1.add(new Employee(2,"Shyam"));
        list1.add(new Employee(3,"kumar"));
        List<Employee> list2 = new ArrayList<>();

        list1.add(new Employee(1,"ram"));
        list1.add(new Employee(2,"Shyam"));
        list1.add(new Employee(4,"kishan"));

        List<Employee> list3 = new ArrayList<>();

       /* for(int i=0; i<list1.size(); i++){
            list2.add(list1.get(i));
        }

        for(int i=0; i<list2.size(); i++){
            for(int j=i+1; j<list2.size(); j++){
                if(list2.get())
            }
        }

        @GetMapping("/get")
        public String getMessage(){
            return "hii";

        }*/

        List<Employee> collect = Stream.concat(list1.stream(), list2.stream())
                .collect(Collectors.toMap(emp -> emp.getId(), emp -> emp, (existing, replacement) -> existing))
                .values()
                .stream()
                .collect(Collectors.toList());

        collect.forEach(System.out::println);

    }
}
