package basic;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LearningRegex {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("a*");
        Matcher aaa = pattern.matcher("aaa");
        System.out.println(aaa.find());
    }
}
