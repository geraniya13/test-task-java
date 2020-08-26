
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.StrictMath.sqrt;


public class task2 {

    public static void main(String[] args) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(args[0]));
            while (reader.ready()) {
                String s = reader.readLine();
                String radius = getRadius(s);
                Double[] center = getCenterCoordinates(s);
                Double[] line = getLineCoordinates(s);
                System.out.println(findCollision(center, radius, line));
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Please, enter valid file name as a first argument!");
        }
    }


    public static String findCollision(Double[] center, String radius, Double[] line) {
        Double rad = Double.parseDouble(radius);
        double b = line[3] - line[0];
        double e = line[4] - line[1];
        double h = line[5] - line[2];
        Math.pow(b, 2);
        if ((Math.pow(b, 2) + Math.pow(h, 2) + Math.pow(e, 2)) == 0) {
            return "Неверно заданные координаты";
        }
        else {
            String result = "";
            double key = 2 * line[0] * b - 2 * b * center[0] + 2 * e * line[1] - 2 * e * center[1] + 2 * line[2] * h - 2 * h * center[2];
            double t1 = ((-0.5) * sqrt(Math.pow(key, 2) - 4 * (Math.pow(b, 2) + Math.pow(h, 2) + Math.pow(e, 2)) * (Math.pow(line[0], 2) - 2 * line[0] * center[0] + Math.pow(center[0], 2) + Math.pow(line[1], 2) - 2 * line[1] * center[1] + Math.pow(center[1], 2) + Math.pow(line[2], 2) - 2 * line[2] * center[2] + Math.pow(center[2], 2) - Math.pow(rad, 2))) - line[0] * b + b * center[0] - e * line[1] + e * center[1] - line[2] * h + h * center[2])/(Math.pow(b, 2) + Math.pow(h, 2) + Math.pow(e, 2));
            double t2 = ((0.5) * sqrt(Math.pow(key, 2) - 4 * (Math.pow(b, 2) + Math.pow(h, 2) + Math.pow(e, 2)) * (Math.pow(line[0], 2) - 2 * line[0] * center[0] + Math.pow(center[0], 2) + Math.pow(line[1], 2) - 2 * line[1] * center[1] + Math.pow(center[1], 2) + Math.pow(line[2], 2) - 2 * line[2] * center[2] + Math.pow(center[2], 2) - Math.pow(rad, 2))) - line[0] * b + b * center[0] - e * line[1] + e * center[1] - line[2] * h + h * center[2])/(Math.pow(b, 2) + Math.pow(h, 2) + Math.pow(e, 2));
            if (Float.isNaN((float)t1) & Float.isNaN((float)t2)) {
                return "Коллизий не найдено";
            }
            else if (t1 == t2) {
                double x = line[0] + b * t1;
                double y = line[1] + e * t1;
                double z = line[2] + h * t1;
                result = x + ", " + y + ", " + z +  "\r";
            }
            else {
                double x = line[0] + b * t1;
                double y = line[1] + e * t1;
                double z = line[2] + h * t1;
                double x1 = line[0] + b * t2;
                double y1 = line[1] + e * t2;
                double z1 = line[2] + h * t2;
                result = x + ", " + y + ", " + z +  "\r\n" + x1 + ", " + y1 + ", " + z1 + "\r";
            }

            return result;
        }
    }

    public static Double[] getCenterCoordinates(String src) {
        Double[] array = new Double[3];
        String regex = "center: \\[(-*\\d+\\.*\\d*), (-*\\d+\\.*\\d*), (-*\\d+\\.*\\d*)\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(src);
        while (matcher.find()) {
            for (int i = 0; i < array.length; i++) {
                array[i] = Double.parseDouble(matcher.group(i + 1));
            }
        }
        return array;
    }

    public static String getRadius(String src) {
        String regex = "radius: ((\\d+)(\\.\\d*)*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(src);
        while (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static Double[] getLineCoordinates(String src) {
        Double[] array = new Double[6];
        String regex = "line: \\{\\[(-*\\d+\\.*\\d*), (-*\\d+\\.*\\d*), (-*\\d+\\.*\\d*)\\], \\[(-*\\d+\\.*\\d*), (-*\\d+\\.*\\d*), (-*\\d+\\.*\\d*)\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(src);
        while (matcher.find()) {
            for (int i = 0; i < array.length; i++) {
                array[i] = Double.parseDouble(matcher.group(i + 1));
            }
        }
        return array;
    }
}
