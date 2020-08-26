
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class task3 {
    public static void main(String[] args) throws Exception {
            if ((args.length < 3 & args.length > 1) || args.length == 0) {
                System.out.println("Add appropriate arguments: filename date1 date2");
            }
            else {

                BufferedReader reader = new BufferedReader(new FileReader(args[0]));
                String start = reader.readLine();
                String barrelVolume = reader.readLine();
                String waterAmount = reader.readLine();
                ArrayList<String> date = new ArrayList<>();
                ArrayList<String> user = new ArrayList<>();
                ArrayList<String> info = new ArrayList<>();

                while (reader.ready()) {
                    String[] s = reader.readLine().split(" - ");
                    date.add(s[0].trim());
                    user.add(s[1].trim());
                    info.add(s[2].trim());
                }

                LocalDateTime d1 = getDate(args[1]);
                LocalDateTime d2 = getDate(args[2]);

                ArrayList<Integer> period = requiredIndexes(date, d1, d2);

                System.out.println(attemptsToAddWater(period, info));
                System.out.println(addingFailPercentage(period, info));
                System.out.println(addedWaterAmount(period, info));
                System.out.println(failedToAddWaterAmount(period, info));
                System.out.println(attemptsToScoopWater(period, info));
                System.out.println(scoopFailPercentage(period, info));
                System.out.println(scoopedWaterAmount(period, info));
                System.out.println(failedToScoopWaterAmount(period, info));
                System.out.println(startWaterAmount(period, info, waterAmount));
                System.out.println(finishWaterAmount(period, info, waterAmount));
            }
    }

    public static LocalDateTime getDate(String s) {
        String[] tmp = s.split("T");
        if (tmp[1].contains("Z")) {
            int i = tmp[1].indexOf(".");
            tmp[1] = tmp[1].substring(0, i);
        }
        String data = tmp[0] + " " + tmp[1];
        DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(data, DATEFORMATTER);
        return ldt;
    }

    public static int getAmount(String s) {
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }
        return 0;
    }

    public static ArrayList<Integer> requiredIndexes(ArrayList<String> list, LocalDateTime d1, LocalDateTime d2) {
        ArrayList<LocalDateTime> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            LocalDateTime ltd = getDate(s);
            list1.add(ltd);
        }

        int start = -1;
        int finish = -1;
        for (int i = 0; i <list1.size(); i++) {
            if (d1.isBefore(list1.get(i)) || d1.isEqual(list1.get(i))) {
                start = i;
                break;
            }
        }
        for (int i = 0; i <list1.size(); i++) {
            if (d2.isBefore(list1.get(i)) || d2.isEqual(list1.get(i))) {
                finish = i;
                break;
            }
        }
        if (start == -1) {
            start = 0;
        }
        if (finish == -1) {
            finish = list1.size() - 1;
        }
        ArrayList<Integer> result = new ArrayList<>();

        result.add(start);
        result.add(finish);

        return result;
    }

    public static int attemptsToAddWater(ArrayList<Integer> period, ArrayList<String> info) {
        int count = 0;
        for (int i = period.get(0); i < period.get(1); i++) {
            if (info.get(i).contains("top up")) {
                count++;
            }
        }
        return count;
    }

    public static String addingFailPercentage(ArrayList<Integer> period, ArrayList<String> info) {
        double attempts = attemptsToAddWater(period, info);
        if (attempts == 0) {
            return "No top up";
        }
        else {
            int count = 0;
            for (int i = period.get(0); i < period.get(1); i++) {
                if (info.get(i).contains("фейл") & info.get(i).contains("top up")) {
                    count++;
                }
            }
            if (count == 0) {
                return "No Fails";
            }
            else {
                return ((count / attempts) * 100) + "";
            }
        }
    }

    public static int addedWaterAmount(ArrayList<Integer> period, ArrayList<String> info) {
        int count = 0;
        for (int i = period.get(0); i < period.get(1); i++) {
            if (info.get(i).contains("успех") & info.get(i).contains("top up")) {
                count+= getAmount(info.get(i));
            }
        }
        return count;
    }

    public static int failedToAddWaterAmount(ArrayList<Integer> period, ArrayList<String> info) {
        int count = 0;
        for (int i = period.get(0); i < period.get(1); i++) {
            if (info.get(i).contains("фейл") & info.get(i).contains("top up")) {
                count+= getAmount(info.get(i));
            }
        }
        return count;
    }

    public static int attemptsToScoopWater(ArrayList<Integer> period, ArrayList<String> info) {
        int count = 0;
        for (int i = period.get(0); i < period.get(1); i++) {
            if (info.get(i).contains("scoop")) {
                count++;
            }
        }
        return count;
    }

    public static String scoopFailPercentage(ArrayList<Integer> period, ArrayList<String> info) {
        double attempts = attemptsToScoopWater(period, info);
        if (attempts == 0) {
            return "No top up";
        }
        else {
            int count = 0;
            for (int i = period.get(0); i < period.get(1); i++) {
                if (info.get(i).contains("фейл") & info.get(i).contains("scoop")) {
                    count++;
                }
            }
            if (count == 0) {
                return "No Fails";
            }
            else {
                return ((count / attempts) * 100) + "";
            }
        }
    }

    public static int scoopedWaterAmount(ArrayList<Integer> period, ArrayList<String> info) {
        int count = 0;
        for (int i = period.get(0); i < period.get(1); i++) {
            if (info.get(i).contains("успех") & info.get(i).contains("scoop")) {
                count+= getAmount(info.get(i));
            }
        }
        return count;
    }

    public static int failedToScoopWaterAmount(ArrayList<Integer> period, ArrayList<String> info) {
        int count = 0;
        for (int i = period.get(0); i < period.get(1); i++) {
            if (info.get(i).contains("фейл") & info.get(i).contains("scoop")) {
                count+= getAmount(info.get(i));
            }
        }
        return count;
    }

    public static int startWaterAmount(ArrayList<Integer> period, ArrayList<String> info, String waterAmount) {
        int count = getAmount(waterAmount);
        for (int i = 0; i < period.get(0); i++) {
            if (info.get(i).contains("успех")) {
                if (info.get(i).contains("top up")) {
                    count+= getAmount(info.get(i));
                }
                else {
                    count-= getAmount(info.get(i));
                }
            }
        }
        return count;
    }

    public static int finishWaterAmount(ArrayList<Integer> period, ArrayList<String> info, String waterAmount) {
        int count = startWaterAmount(period, info, waterAmount);
        for (int i = period.get(0); i < period.get(1); i++) {
            if (info.get(i).contains("успех")) {
                if (info.get(i).contains("top up")) {
                    count+= getAmount(info.get(i));
                }
                else {
                    count-= getAmount(info.get(i));
                }
            }
        }
        return count;
    }
}
