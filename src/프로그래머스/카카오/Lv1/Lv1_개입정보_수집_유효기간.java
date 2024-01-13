package 프로그래머스.카카오.Lv1;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lv1_개입정보_수집_유효기간 {

    /*
    모든 달은 28일 까지 있다고 가정
     */
    public int[] solution(String today, String[] terms, String[] privacies) {

        HashMap<String, Integer> map = new HashMap<>();
        for (String term : terms) {
            String[] split = term.split(" ");
            map.put(split[0], Integer.parseInt(split[1]));
        }

        List<Integer> answer = new ArrayList<>();
        Pattern pattern = Pattern.compile("([0-9]{4}).([0-9]{2}).([0-9]{2})");
        for (int i = 0; i < privacies.length; i++) {
            String[] split = privacies[i].split(" ");

            Matcher matcher = pattern.matcher(split[0]);
            if(matcher.find()) {
                int year = Integer.parseInt(matcher.group(1));
                int month = Integer.parseInt(matcher.group(2)) + map.get(split[1]);
                int day = Integer.parseInt(matcher.group(3)) - 1;


                String expiredDate = MyDate.of(year, month, day).toString();
                if( today.compareTo(expiredDate) > 0){
                    answer.add(i+1);
                }
            }else{
                throw new RuntimeException();
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    static class MyDate{

        int year;
        int month;
        int day;

        private MyDate(int year, int month, int day) {
            if (day < 1) {
                day = 28;
                month -= 1;
            }

            if (month > 12) {
                year += month / 12;
                month %= 12;
            }

            if (month < 1) {
                month = 12;
                year -= 1;
            }
            this.year = year;
            this.month = month;
            this.day = day;
        }
        public static MyDate of(int year, int month, int day) {
            return new MyDate(year, month, day);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(year);
            sb.append('.');
            if (month < 10) {
                sb.append(0);
            }
            sb.append(month);
            sb.append('.');
            if (day < 10) {
                sb.append(0);
            }
            sb.append(day);
            return sb.toString();
        }
    }


}
