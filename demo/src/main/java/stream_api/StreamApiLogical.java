package stream_api;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApiLogical {

    /* We have two list containing employee objects. task is to combine these two list into once and duplicate
    * employee based on employee id objects should not be there */

    public static void concatListsWithUniq(List<Employee> list1, List<Employee> list2){

        Stream.concat(list1.stream(), list2.stream())
                .collect(Collectors.toMap(emp -> emp.getId(), emp -> emp, (first, second) -> first))
                .values()
                .stream().forEach(System.out::println);
    }

    public static void collectBestFour(List<Student> students){

       students.stream().map(student -> {
            //convert map to entryset
            List<Map.Entry<String, Integer>> entries = new ArrayList<>(student.getSubjectsWithMarks().entrySet());

            //shorting in reverese order
            entries.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

            //collect best 4 of entry
            List<Map.Entry<String, Integer>> top4Subjects = entries.stream().limit(4).collect(Collectors.toList());

            //converting entry again into map to set in student object
            Map<String, Integer> top4Map = top4Subjects.stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (existing, replacement) -> existing));

            student.setSubjectsWithMarks(top4Map);
            return student;
        }).forEach(System.out::println);
    }

    //new approach
    public static void collectBestFourNew(List<Student> students){
       students.stream().map(student -> {
           Map<String, Integer> subjectWithBestFour = student.getSubjectsWithMarks().entrySet()
                   .stream()
                   .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                   .limit(4)
                   .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (existing, replacement) -> existing));
           student.setSubjectsWithMarks(subjectWithBestFour);
           return student;
       }).forEach(System.out::println);
    }


    public static void getFistTopFour(List<Integer> list){
        List<Integer> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList, Collections.reverseOrder());

        sortedList.stream()
                .limit(4)
                .forEach(System.out::println);

    }

    /*merging two list and remove duplicates*/
    public static void mergeListRemoveDuplicate(List<Integer> list1, List<Integer> list2){

        /*first approach using distinct*/
        /*Stream.concat(list1.stream(), list2.stream())
                .distinct()
                .forEach(System.out::println);*/

        /*second approach by using HashSet*/
        /*Stream.concat(list1.stream(), list2.stream())
                .collect(Collectors.toSet())
                .stream()
                .sorted()
                .forEach(System.out::println);*/
    }

    /*collect duplicate or common elements from lists*/
    public static void getDuplicates(List<Integer> list1, List<Integer> list2){
        //first approach
        /*Stream.concat(list1.stream(), list2.stream())
                .collect(Collectors.groupingBy(i->i, Collectors.counting()))
                .entrySet()
                .stream().filter(entry-> entry.getValue()>1)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);*/

        //second approach
       Set<Integer> setList2 = new HashSet<>(list2);
       list1.stream()
               .filter(setList2::contains)
               .forEach(System.out::println);
    }

    /*count frequency of elements */
    public static void countFrequency(List<Integer> list){
        Set<Map.Entry<Integer, Long>> entries = list.stream()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                .entrySet();

        for(Map.Entry<Integer, Long> entry: entries){
            System.out.println("element is: "+entry.getKey()+"\t"+"frequency is: "+entry.getValue());
        }
    }

    /*find missing*/
    public static void getMissing(List<Integer> list1, List<Integer> list2){
        // elements presented in list one but not in list two
        list1.stream()
                .filter(e-> !list2.contains(e))
                .forEach(System.out::println);
    }

    public static void mergeMaps(Map<Integer, String> map1, Map<Integer, String> map2){
        Map<Integer, String> collect = map1.entrySet().stream()
                .map(entry -> {
                    if (map2.containsKey(entry.getKey())) {
                        entry.setValue(entry.getValue().concat(map2.get(entry.getKey())));
                        map2.remove(entry.getKey());
                        return entry;
                    }
                    return entry;
                }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (existing, replace) -> existing));

       // map2.entrySet().stream()
        //        .map(entry->collect.put(entry.getKey(), entry.getValue()));
        for(Map.Entry<Integer,String> entry : collect.entrySet()){
            System.out.println("key: " + entry.getKey()+"\t"+"value: "+entry.getValue());
        }

    }

    public static Map<Integer, String> mergeMap(Map<Integer, String> map1, Map<Integer, String> map2) {
        // Merge map2 into map1 using Stream API
        Map<Integer, String> mergedMap = new HashMap<>(map1);
       // map2.forEach((key, value) -> mergedMap.merge(key, value, String::concat));

        map2.forEach((key, value) -> mergedMap.merge(key, value, (existingValue, newValue) -> existingValue.concat(newValue)));
        return mergedMap;
    }

    public static void removeKey(Map<String, Integer> map1, Set<String> set){
        Map<String, Integer> collect = map1.entrySet().stream()
                .filter(entry -> !set.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a));
        collect.forEach((key,value)-> System.out.println(key+"\t"+value));
    }

    public static void mergerTwoListUsingFlatMap(List<Integer> list1, List<Integer> list2){
        Stream.of(list1,list2).
                collect(Collectors.toList())
                .stream()
                .flatMap(list->list.stream())
                .distinct()
                .forEach(System.out::println);
    }

    public static void interSect(List<Integer> list1, List<Integer> list2){
        list2.stream()
                .filter(e->list1.contains(e))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public static void getDubleOfOdd(List<Integer> list){
        list.stream()
                .filter(e->e%2!=0)
                .map(e->e*2)
                .forEach(System.out::println);
    }

    public static void getSumOrAverage(List<Integer> list){

        int sum = list.stream()
                .mapToInt(Integer::intValue)
                .sum();

        OptionalDouble average = list.stream()
                .mapToInt(Integer::intValue)
                .average();
        System.out.println(sum);

        System.out.println(average.isPresent()?average.getAsDouble():"the value is empty");
    }

    public static void groupingElement(List<String> list){
        list.stream()
                .collect(Collectors.toMap(e->e.length(), e->e,(existing, replacement)->existing.concat(",").concat(replacement)))
                .forEach((key, value)-> System.out.println(key+"="+"["+value+"]"));
    }

    public static void getDuplicateFromList(List<Integer> list){
        list.stream()
                .collect(Collectors.groupingBy(e->e, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry->entry.getValue()>1)
                .forEach(System.out::println);
    }

    public static void sortStringAlphabetically(List<String> list){
        list.stream()
                .sorted(Comparator.reverseOrder())
                //for reverese order
                //  .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }

    public static void getEmployeeSalarySum(List<Emp> list){
        int sum = list.stream()
                .map(Emp::getSalary)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(sum);

    }

    public static void countNumberOfOccurence(String str){

        str.chars()
                .mapToObj(e->(char)e)
                .collect(Collectors.toList())
                .stream()
                .collect(Collectors.groupingBy(e-> e, Collectors.counting()))
                .forEach((key,value)-> {
                    System.out.println(key+"\t"+value);
                });
    }

    public static String[] checkLongAnagram(String sentence){

        String[] splitedWords = sentence.split(" \\s+");

        Map<String, List<String>> anagramMap = new HashMap<>();

        for(String word : splitedWords){
            char[] charArray = word.toCharArray();
            Arrays.sort(charArray);
            String sortedString = new String(charArray);
            anagramMap.computeIfAbsent(sortedString, k->new ArrayList<>()).add(word);
        }

        List<String>largeAnnagramGroup =  new ArrayList<>();
        int maxSize=0;

        for(List<String> group : anagramMap.values()){
            if(group.size()>maxSize) {
                largeAnnagramGroup = group;
                maxSize = group.size();
            }
        }

        return largeAnnagramGroup.toArray(new String[0]);

    }


}

class Test{

    public static void main(String[] args) {

        List<Employee> collect1 = Stream.of(
                new Employee(1, "ram"),
                new Employee(2, "shyam"),
                new Employee(3, "ghanshyam")
        ).toList();

        List<Employee> collect2 = Stream.of(
                new Employee(1, "gagan"),
                new Employee(2, "faizu"),
                new Employee(4, "ghanshyam")
        ).toList();

        Student s1 = new Student();
        s1.setStudentId(1);
        s1.setStudentName("chandrabhan");
        s1.setSubjectsWithMarks(Map.of("Mathemetics", 40, "Chemistry", 60, "Hindi", 95, "Biology",33, "English",45 ));

        Student s2 = new Student();
        s2.setStudentId(2);
        s2.setStudentName("jubair");
        s2.setSubjectsWithMarks(Map.of("Mathemetics", 98, "Chemistry", 34, "Hindi", 99, "Biology",100, "English",15 ));



        // StreamApiLogical.concatListsWithUniq(collect1, collect2);

       // StreamApiLogical.getFistTopFour(Arrays.asList(52, 42, 36, 29, 78 ));

       // StreamApiLogical.collectBestFour(Arrays.asList(s1,s2));

      //  StreamApiLogical.collectBestFourNew(Arrays.asList(s1, s2));

      //  StreamApiLogical.mergeListRemoveDuplicate(Arrays.asList(10,20,30,40,50,68,78), Arrays.asList(10,20,95,65,44,23));

      //  StreamApiLogical.getDuplicates(Arrays.asList(10,20,30,40,50,68,78), Arrays.asList(10,20,95,65,44,23));

      //  StreamApiLogical.countFrequency(Arrays.asList(10,10,30,40,60,30,30,90,50,85));

      //  StreamApiLogical.getMissing(Arrays.asList(10,20,30,40,50,68,78), Arrays.asList(30,40,44,23));

      //  Map<Integer, String> integerStringMap = StreamApiLogical.mergeMap(Map.of(1, "jubair", 2, "ashish", 3, "vimal", 4, "chandrabhan"), Map.of(1, "siddique", 2, "soni", 3, "gupta", 5, "surendra"));
      //  integerStringMap.forEach((key, value)-> System.out.println(key+"\t"+value));

      //  HashSet<String> objects = new HashSet<>();
      //   objects.add("a");
      //  objects.add("b");

      //  StreamApiLogical.removeKey(Map.of("a", 1, "b", 2, "c", 3), objects );

      //  StreamApiLogical.mergerTwoListUsingFlatMap(Arrays.asList(10,20,30,40,50,68,78), Arrays.asList(10,20,95,65,44,23));

      //  StreamApiLogical.interSect(Arrays.asList(10,20,30,40,50,68,78), Arrays.asList(10,20,95,65,44,23));

      //  StreamApiLogical.getSumOrAverage(Arrays.asList(10,20,95,65,44,23));

      //  StreamApiLogical.groupingElement(Arrays.asList("apple", "banana", "cherry", "date", "fig", "grape"));

      //  StreamApiLogical.getDuplicateFromList(Arrays.asList(10,20,30,30,40,50,10,68,78));

      //  StreamApiLogical.sortStringAlphabetically(Arrays.asList("apple", "hanana", "cherry", "pate", "fig", "grape"));

       StreamApiLogical.countNumberOfOccurence("chandrabhan");

      //  String[] strings = StreamApiLogical.checkLongAnagram(new String("eat tea tan ate nat bat"));
      // String string = Arrays.toString(strings);
      //  System.out.println(string);
    }
}
