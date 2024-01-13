package 프로그래머스.카카오.Lv2;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Lv2_순위_검색 {
    public int[] solution(String[] info, String[] query) {

        String[] language = {"cpp", "java", "python", "-"};
        String[] part = {"backend", "frontend", "-"};
        String[] career = {"junior", "senior", "-"};
        String[] food = {"chicken", "pizza", "-"};
        // key: "- and - and - and -", val: List 형태로 Map 만들면 될듯


        Map<String, List<Integer>> map = new HashMap<>();
        for (String l : language) {
            for (String p : part) {
                for (String c : career) {
                    for (String f : food) {
                        map.put(String.format("%s %s %s %s",l,p,c,f), new ArrayList<>());
                    }
                }
            }
        }

        for (String str : info) {String[] split = str.split(" ");
            for (int i = 0; i < 2; i++) {String l = i == 0 ? split[0] : "-";
                for (int j = 0; j < 2; j++) {String p = j == 0 ? split[1] : "-";
                    for (int k = 0; k < 2; k++) {String c = k == 0 ? split[2] : "-";
                        for (int e = 0; e < 2; e++) {String f = e == 0 ? split[3] : "-";
                            map.get(String.format("%s %s %s %s", l, p, c, f)).add(Integer.parseInt(split[4]));
                        }
                    }
                }
            }
        }

        for (String l : language) {
            for (String p : part) {
                for (String c : career) {
                    for (String f : food) {
                        map.get(String.format("%s %s %s %s", l, p, c, f)).sort(Comparator.comparingInt(a -> a));
                    }
                }
            }
        }

        int[] answer = new int[query.length];
        int idx = 0;
        Pattern pattern = Pattern.compile("^(.+) (\\d+)$");
        for (String q : query) {

            Matcher matcher = pattern.matcher(q.replaceAll(" and ", " "));

            if (matcher.find()) {
                String key = matcher.group(1);
                int score = Integer.parseInt(matcher.group(2));
                answer[idx++] = binarySearch(map.get(key), score);
            }
        }


        return answer;
    }

    // 1 2 3 3 3 4 5 -> 3
    public int binarySearch(List<Integer> list, int target){
        int lo = -1;
        int hi = list.size();
        while(lo+1<hi){
            int mid = (lo+hi)/2;
            if(list.get(mid) >= target){
                hi = mid;
            }else{
                lo = mid;
            }
        }
        return list.size() - hi;
    }

}