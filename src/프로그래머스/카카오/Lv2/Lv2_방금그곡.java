package 프로그래머스.카카오.Lv2;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Lv2_방금그곡 {
    public static void main(String[] args) {
    }

    /*
    1. 재생시간과 코드를 토대로 코드 문자열 생성
    2. 해시맵에 저장
    3. 맵에서 주어진 인풋 m과 일치하는 부분이 있는 key 추출

    조회된 key가 여러개일 경우 정렬 조건
    1. 재생시간이 긴 순
    2. 먼저 입력된 음악 제목
     */
    public String solution(String m, String[] musicinfos) {

        List<Music> musics = transMusics(musicinfos);
        List<Music> answers = musics.stream()
                .filter(f -> f.getPlayedCodes().contains(transCodes(m)))
                .collect(Collectors.toList());

        if(answers.isEmpty()){
            return "(None)";
        }
        return answers.stream()
                .sorted(Comparator
                        .comparing(Music::getPlayTime)
                        .reversed()
                        .thenComparing(Music::getStartAt))
                .findFirst().get().getName();
    }

    private List<Music> transMusics(String[] musicinfos) {
        List<Music> musics = new ArrayList<>();
        for (String info : musicinfos) {
            String[] split = info.split(",");
            musics.add(Music.of(split[2], transCodes(split[3]), split[0], split[1]));
        }
        return musics;
    }

    public String transCodes(String codes) {
        Pattern pattern = Pattern.compile("([A-Z])#");

        StringBuffer sb = new StringBuffer();
        Matcher matcher = pattern.matcher(codes);
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toLowerCase());
        }

        matcher.appendTail(sb);
        return sb.toString();
    }
    static class Music {
        private String name;
        private String codes;
        private String startAt;
        private String endAt;
        private int playTime;

        private Music(String name, String codes, String startAt, String endAt) {
            this.name = name;
            this.codes = codes;
            this.startAt = startAt;
            this.endAt = endAt;
            this.playTime = calculatePlayTime(startAt, endAt);
        }

        public static Music of(String name, String codes, String startAt, String endAt) {
            return new Music(name, codes, startAt, endAt);
        }

        private int calculatePlayTime(String startAt, String endAt) {
            String[] startAtSplit = startAt.split(":");
            String[] endAtSplit = endAt.split(":");
            int hour = Integer.parseInt(endAtSplit[0]) - Integer.parseInt(startAtSplit[0]);
            int minute = Integer.parseInt(endAtSplit[1]) - Integer.parseInt(startAtSplit[1]);
            return hour * 60 + minute;
        }

        public String getPlayedCodes() {
            StringBuffer sb = new StringBuffer();
            sb.append(codes.repeat(playTime / codes.length()));
            sb.append(codes.substring(0, playTime % codes.length()));
            return sb.toString();
        }

        public String getName() {
            return name;
        }

        public int getPlayTime() {
            return playTime;
        }

        public String getStartAt() {
            return startAt;
        }
    }

}
