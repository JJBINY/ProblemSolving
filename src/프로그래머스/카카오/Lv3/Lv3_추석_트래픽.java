package 프로그래머스.카카오.Lv3;


import java.util.*;
import java.util.stream.Collectors;

public class Lv3_추석_트래픽 {
    /*
    1000 곱해서 밀리초로 생각

    at-999 초부터 at까지 1초 간 처리량을 계산하자.
    at이 요청 시점인 경우 해당 구간의 처리량은 1 증가한다.
    => [요청-999:요청]구간이므로 해당 처리 구간이 1초 구간에 포함됨
    at이 응답+1000 시점인 경우 해당 구간의 처리량은 1 감소한다.
    => [응답+1:응답+1000]구간이므로 해당 처리 구간이 1초 구간에서 벗어남

    즉 요청과 응답 시점만 기록해놓은 뒤 오름차순 정렬 하고 순서대로 확인하면 된다.
    */

    public int solution(String[] lines) {
        int answer = 0;

        List<Event> events = new ArrayList<>();

        for(String line : lines){
            String[] split = line.split(" ");
            int resAt = getMilliSec(split[1]);
            String[] pSplit = split[2].substring(0,split[2].length()-1).split("\\.");
            int pTime =Integer.parseInt(pSplit[0])*1000-1;
            if(pSplit.length == 2){
                pTime+=Integer.parseInt(pSplit[1]);
            }
            int reqAt = resAt-pTime;
            reqAt = reqAt<0 ? 0:reqAt;
            // System.out.println(reqAt + ", "+resAt);
            events.add(new Event(reqAt, true));
            events.add(new Event(resAt+1000, false));
        }

        int cnt = 0;
        int ans = 0;


        events = events.stream().sorted(Comparator.comparing(Event::getTime)).collect(Collectors.toList());
        for(Event e: events){
            if(e.isReq){
                cnt++;
            }else{
                cnt--;
            }
            ans = ans<cnt? cnt: ans;
        }


        return ans;
    }

    public int getMilliSec(String at){
        String[] split = at.split(":");
        int hour = Integer.parseInt(split[0]);
        int min = Integer.parseInt(split[1]);
        String[] sec = split[2].split("\\.");
        int floorSec = hour*3600+min*60+Integer.parseInt(sec[0]);
        return floorSec*1000 + Integer.parseInt(sec[1]);
    }

    class Event{
        int time;
        boolean isReq;
        public Event(int time,boolean isReq){
            this.time = time;
            this.isReq = isReq;
        }
        public int getTime(){
            return time;
        }
    }
}

