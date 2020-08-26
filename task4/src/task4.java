import java.util.regex.Pattern;

public class task4 {
    public static void main(String[] args) throws Exception {
        if (args.length == 2) {
            String a = args[0];
            String b = args[1];
            System.out.println(compare(a, b));
        } else {
            System.out.println("Please enter valid arguments!");
            System.out.println("Usage: java -jar task4.jar word1 word2");
        }
    }

    public static String compare(String s1, String s2) {
        if (!(s2.contains("*"))) {
            if (s1.equals(s2)) {
                return "OK";
            }
            else return "KO";
        }
        else {
            s2 = s2.replaceAll("\\*", ".*");
            return  Pattern.matches(s2, s1) ? "OK" : "KO";
        }
    }
}
