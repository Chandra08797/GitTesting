package practice;

import stream_api.StreamApiLogical;
import stream_api.Student;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8_revision {

    public static void getUniqueEmployee(List<Employee> list1, List<Employee> list2){

        Stream.concat(list1.stream(), list2.stream())
                .collect(Collectors.toMap(employee -> employee.getId(), employee->employee, (existed, newEmp)->existed))
                .values()
                .stream()
                .forEach(System.out::println);
    }

    public static void collectBestFourNew(List<Student> list){

        list.stream().map(student ->{
            Map<String, Integer> collect = student.getSubjectsWithMarks().entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .limit(4)
                    .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue(), (existed, replacement) -> replacement));
            student.setSubjectsWithMarks(collect);
            return student;
        }).forEach(System.out::println);

    }

    public static void mergeListRemoveDuplicate(List<Integer> list1, List<Integer> list2){
        // first approach
     /*   list2.stream()
                .filter(e->!list1.contains(e))
                .collect(Collectors.toList())
                .stream()
                .map(e->list1.add(e));
        list1.stream().forEach(System.out::println); */

        //second approach
       /* Stream.concat(list1.stream(), list2.stream())
                .distinct()
                .forEach(System.out::println); */

        //third approach
        Stream.concat(list1.stream(), list2.stream())
                .collect(Collectors.toSet())
                .stream().sorted()
                .forEach(System.out::println);
    }

    public static void getDuplicates(List<Integer> list1, List<Integer> list2){
        //first approach
       /* list2.stream()
                .filter(e->list1.contains(e))
                .forEach(System.out::println);

        */

        //second approach
        Stream.concat(list1.stream(), list2.stream())
                .collect(Collectors.groupingBy(e->e, Collectors.counting()))
                .values()
                .stream()
                .filter(e->e>1)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public static void getMissing(List<Integer> list1, List<Integer> list2){
        // elements presented in list two but not in list one
        list2.stream()
                .filter(e->!list1.contains(e))
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        // elements presented in list one but not in list two
        list1.stream()
                .filter(e->!list2.contains(e))
                .collect(Collectors.toSet())
                .forEach(System.out::println);
    }

    public static void mergeMap(Map<Integer, String> map1, Map<Integer, String> map2 ) {
        //fist approach
        Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (value1, value2) -> value1.concat(value2)))
                .forEach((key, value) -> System.out.println(key + "\t" + value));
        //second approach
        HashMap<Integer, String> integerStringHashMap = new HashMap<>(map1);
        map2.forEach((key, value)->integerStringHashMap.merge(key, value, (existing,replacement)->replacement));

        integerStringHashMap.forEach((key,value)-> System.out.println(key+"\t"+value));

    }

    public static void mergerTwoListUsingFlatMap(List<Integer> list1, List<Integer> list2){
        Stream.of(list1, list2)
                .flatMap(list -> list.stream())
                .forEach(System.out::println);
    }

    public static void getDubleOfOdd(List<Integer> list){
        list.stream()
                .filter(e->e%2!=0)
                .map(e->e*2)
                .forEach(System.out::println);
    }

    public static void getTwoBigAndLower(int[] arr){

        List<Integer> sortedList = Arrays.stream(arr)
                .boxed()
                .sorted()
                .collect(Collectors.toList());

        List<Integer> twoBig = sortedList.stream()
                .limit(2)
                .collect(Collectors.toList());

        List<Integer> twoSmall = sortedList.stream()
                .skip(sortedList.size() - 2)
                .collect(Collectors.toList());

        System.out.println(twoBig);
        System.out.println(twoSmall);
    }



    public static void main(String[] args) {

     /*   List<stream_api.Employee> collect1 = Stream.of(
                new stream_api.Employee(1, "ram"),
                new stream_api.Employee(2, "shyam"),
                new stream_api.Employee(3, "ghanshyam")
        ).toList();

        List<stream_api.Employee> collect2 = Stream.of(
                new stream_api.Employee(1, "gagan"),
                new stream_api.Employee(2, "faizu"),
                new stream_api.Employee(4, "ghanshyam")
        ).toList();

        Student s1 = new Student();
        s1.setStudentId(1);
        s1.setStudentName("chandrabhan");
        s1.setSubjectsWithMarks(Map.of("Mathemetics", 40, "Chemistry", 60, "Hindi", 95, "Biology",33, "English",45 ));

        Student s2 = new Student();
        s2.setStudentId(2);
        s2.setStudentName("jubair");
        s2.setSubjectsWithMarks(Map.of("Mathemetics", 98, "Chemistry", 34, "Hindi", 99, "Biology",100, "English",15 ));

*/

        // StreamApiLogical.concatListsWithUniq(collect1, collect2);

        // StreamApiLogical.getFistTopFour(Arrays.asList(52, 42, 36, 29, 78 ));

        // StreamApiLogical.collectBestFour(Arrays.asList(s1,s2));

       //  collectBestFourNew(Arrays.asList(s1, s2));

       // mergeListRemoveDuplicate(Arrays.asList(10,20,30,40,50,68,78), Arrays.asList(10,20,95,65,44,23));

      //   getDuplicates(Arrays.asList(10,20,30,40,50,68,78), Arrays.asList(10,20,95,65,44,23));

       // countFrequency(Arrays.asList(10,10,30,40,60,30,30,90,50,85));

        //   getMissing(Arrays.asList(10,20,30,40,50,68,78), Arrays.asList(30,40,44,23));

        //  Map<Integer, String> integerStringMap = mergeMap(Map.of(1, "jubair", 2, "ashish", 3, "vimal", 4, "chandrabhan"), Map.of(1, "siddique", 2, "soni", 3, "gupta", 5, "surendra"));
        //  integerStringMap.forEach((key, value)-> System.out.println(key+"\t"+value));

        //  HashSet<String> objects = new HashSet<>();
        //   objects.add("a");
        //  objects.add("b");

        //  StreamApiLogical.removeKey(Map.of("a", 1, "b", 2, "c", 3), objects );

       // mergerTwoListUsingFlatMap(Arrays.asList(10,20,30,40,50,68,78), Arrays.asList(10,20,95,65,44,23));

        //  StreamApiLogical.interSect(Arrays.asList(10,20,30,40,50,68,78), Arrays.asList(10,20,95,65,44,23));

        //  StreamApiLogical.getSumOrAverage(Arrays.asList(10,20,95,65,44,23));

        //  StreamApiLogical.groupingElement(Arrays.asList("apple", "banana", "cherry", "date", "fig", "grape"));

        //  StreamApiLogical.getDuplicateFromList(Arrays.asList(10,20,30,30,40,50,10,68,78));

        //  StreamApiLogical.sortStringAlphabetically(Arrays.asList("apple", "hanana", "cherry", "pate", "fig", "grape"));

       // String[] strings = StreamApiLogical.checkLongAnagram(new String("eat tea tan ate nat bat"));
       // String string = Arrays.toString(strings);
       // System.out.println(string);

        Java8_revision.getTwoBigAndLower(new int[]{10,20,30,40,50,68,78});


    }

}
