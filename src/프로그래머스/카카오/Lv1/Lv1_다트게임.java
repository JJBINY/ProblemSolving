package 프로그래머스.카카오.Lv1;

public class Lv1_다트게임 {
    public static void main(String[] args) {
        Lv1_다트게임 main = new Lv1_다트게임();
        main.solution("d");
//        solution.solution();
    }

    public int solution(String dartResult) {

        double prev = 0;
        double now = 0;
        double total = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < dartResult.length(); i++) {

            char c = dartResult.charAt(i);
            switch (c) {
                case 'S':
                    total += prev;
                    prev = now;
                    now = Math.pow(Double.valueOf(sb.toString()), 1);
                    sb.setLength(0);
                    break;
                case 'D':
                    total += prev;
                    prev = now;
                    now = Math.pow(Double.valueOf(sb.toString()), 2);
                    sb.setLength(0);
                    break;
                case 'T':
                    total += prev;
                    prev = now;
                    now = Math.pow(Double.valueOf(sb.toString()), 3);
                    sb.setLength(0);
                    break;
                case '*':
                    total += prev;
                    now *= 2;
                    break;
                case '#':
                    now *= -1;
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return (int)(total+now+prev);
    }

}
