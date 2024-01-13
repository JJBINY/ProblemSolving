package 프로그래머스.카카오.Lv3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Lv3_광고_삽입 {
    /*

    100 * 60 * 60 = 360,000이므로 0초부터 일일이 비교한다고 해도 충분히 해볼만함
    logs는 최악의 경우 300,000개 이므로
    매번 30만번 계산해면 108,000,000,000번 = 대략 100억번 -> 단순 계산시 시간초과

    */
    public String solution(String play_time, String adv_time, String[] logs) {

        int advTimeSec = Log.getBySec(adv_time);
        int playTimeSec = Log.getBySec(play_time);

        List<Log> logList = Arrays.stream(logs).map(Log::of).collect(Collectors.toList());

        long[] totalWatchAt = new long[playTimeSec+1];
        //해당 시각에 시작 - 해당 시각에 종료 기록
        for (Log log : logList) {
            totalWatchAt[log.startAt] +=1;
            totalWatchAt[log.endAt] -= 1;
        }

        //누적합 : totalWatch[i] = [i,i+1] 사이에 시청중인 시청자의 수
        for (int i = 1; i < playTimeSec; i++) {
            totalWatchAt[i] += totalWatchAt[i - 1];
        }

        //누적합 : totalWatchAt[i] =  [0,i+1] 사이 총 시청시간
        for (int i = 1; i < playTimeSec; i++) {
            totalWatchAt[i] += totalWatchAt[i - 1];
        }


        long max = totalWatchAt[advTimeSec];
        int maxAt = -1;
        //구간합 : [0,b+1] - [0,a+1] =  [a+1,b+1]
        for (int i = 0; i < playTimeSec-advTimeSec+1; i++) {
            long sum = totalWatchAt[i + advTimeSec] - totalWatchAt[i];
            if(sum > max){
                max = sum;
                maxAt = i;
            }
        }
        return Log.getTimeBySec(maxAt+1);

    }

    static class Log {
        int startAt;
        int endAt;

        private Log(int startAt, int endAt) {
            this.startAt = startAt;
            this.endAt = endAt;
        }

        static public Log of(String log) {
            String[] split = log.split("-");
            return new Log(getBySec(split[0]), getBySec(split[1]));
        }

        static public int getBySec(String time) {
            int[] split = Arrays.stream(time.split(":")).mapToInt(Integer::parseInt).toArray();
            return split[0] * 3600 + split[1] * 60 + split[2];
        }

        public static String getTimeBySec(int timeBySec) {
            int hour = timeBySec / 3600;
            int minute = timeBySec % 3600 / 60;
            int sec = timeBySec % 3600 % 60;
            StringBuilder sb = new StringBuilder();
            sb.append(hour / 10);
            sb.append(hour % 10);
            sb.append(':');
            sb.append(minute / 10);
            sb.append(minute % 10);
            sb.append(':');
            sb.append(sec / 10);
            sb.append(sec % 10);
            return sb.toString();
        }
    }


}
