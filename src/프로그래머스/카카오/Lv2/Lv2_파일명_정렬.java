package 프로그래머스.카카오.Lv2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lv2_파일명_정렬 {
    public static void main(String[] args) {
        Lv2_파일명_정렬 main = new Lv2_파일명_정렬();
        String[] input = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        main.solution(input);
    }

    // 파일명을 분리하라
    // 정렬하라
    public String[] solution(String[] files) {
        return Arrays.stream(files).map(File::new)
                .sorted(Comparator.comparing(File::getHead)
                        .thenComparing(File::getNumber)
                        .thenComparing(File::getTail))
                .map(File::getName).toArray(String[]::new);
    }

    class File {

        private String name;
        private String head;
        private int number;
        private String tail;
        private String REG_EXP = "^(\\D+)(\\d{1,5})(.+)?$";
        private Pattern pattern = Pattern.compile(REG_EXP);

        public File(String name) {
            this.name = name;
            separate();
        }

        private void separate() {
            Matcher matcher = pattern.matcher(name);
            if (matcher.matches()) {
//                IntStream.of(1, 2, 3).mapToObj(matcher::group).forEach(System.out::println);
                head = matcher.group(1).toLowerCase();
                number = Integer.parseInt(matcher.group(2));
                tail = matcher.group(3);
            } else {
                throw new RuntimeException("Wrong name pattern");
            }
        }

        public String getName() {
            return name;
        }

        public String getHead() {
            return head;
        }

        public int getNumber() {
            return number;
        }

        public String getTail() {
            return tail;
        }
    }

}
