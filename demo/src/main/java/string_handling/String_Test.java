package string_handling;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class String_Test {

    public static boolean checkPalindrome(String str){

        int left =0;  int right= str.length()-1;

        while(left<right){
            if(str.charAt(left++)!= str.charAt(right--))
                return false;
        }
            return true;
    }

    public static boolean checkAnagram(String str1, String str2){
        if(str1.length()!=str2.length()){
            return false;
        }
        else{
            char[] charStr1 = str1.toCharArray();
            char[] charStr2 = str2.toCharArray();

            Arrays.sort(charStr1);
            Arrays.sort(charStr2);

            return Arrays.equals(charStr1, charStr2);
        }
    }

    public static int countOccurrence(String str1, char ch){
        int count = 0;
        char[] charArray = str1.toCharArray();
        for(char c : charArray){
            if(c==ch)
                count++;
        }
        return count;
    }

    public static String removeDuplicateChar(String str){
        System.out.println(str);
        String str2 = str.chars()
                .mapToObj(e -> (char) e)
                .collect(Collectors.toSet())
                .stream().sorted()
                .collect(Collectors.toSet())
                .toString();

        System.out.println(str2);
        return str2;
    }

    public static void falcumString(String[] str, String sentence){

        List<String> result = Arrays.stream(sentence.split(" "))
                .map(word -> Arrays.stream(str)
                        .filter(a -> word.startsWith(a))
                        .findFirst()
                        .orElse(word))
                .collect(Collectors.toList());

        String output = String.join(" ", result);
        System.out.println(output);

    }

}

class Test_Main{
    public static void main(String[] args) {

        // System.out.println(String_Test.checkPalindrome("domod"));
       //  System.out.println(String_Test.checkAnagram("tea", "eat"));
      //  System.out.println(String_Test.removeDuplicateChar("chandrabhan"));
        String_Test.falcumString(new String[]{"cat","dat","rat"}, "The cattle are so dattle and rattle");
    }
}

