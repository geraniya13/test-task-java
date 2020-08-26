import java.math.BigInteger;


public class task1 {
    public static void main(String[] args) {

        if (args.length == 2) {
            System.out.println(iToBase((Integer.parseInt(args[0])), args[1]));
        } else if (args.length == 3) {
            System.out.println(iToBase(args[0], args[1], args[2]));
        } else {
            System.out.println("Invalid arguments.");
            System.out.println("Usage: task1 number [integerBase] baseToConvert");
        }

    }


    public static String iToBase(int nb, String base) {
        return Integer.toString(nb, base.length());
    }


    public static String iToBase(String nb, String baseSrc, String baseDst) {
        return "" + new BigInteger(new BigInteger(nb, baseSrc.length()).toString(baseDst.length()));
    }

}
