import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class 나_수식최대화 {
    /*
    빡구현;;
    30분걸림;;
    2:11:37
     */
    public long solution(String expression) {
        long answer = 0;
        List<String> priority = new ArrayList<>();
        priority.add("+-*");
        priority.add("+*-");
        priority.add("-*+");
        priority.add("-+*");
        priority.add("*-+");
        priority.add("*+-");


        List<Long> numbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            numbers.add(Long.parseLong(matcher.group()));
        }

        List<Character> operations = new ArrayList<>();
        pattern = Pattern.compile("\\+|\\*|-");
        matcher = pattern.matcher(expression);
        while (matcher.find()) {
            operations.add(matcher.group().charAt(0));
        }

        for (String p : priority) {
            LinkedList<Long> nums = new LinkedList<>();
            nums.addAll(numbers);
            List<Character> opers = new LinkedList<>();
            opers.addAll(operations);

            for (int i = 0; i < 3; i++) {
                char oper = p.charAt(i);
                int idx = 0;
                while(idx < opers.size()) {
                    if (opers.get(idx) == oper) {
                        opers.remove(idx);
                        nums.set(idx, operate(nums.get(idx), nums.get(idx + 1), oper));
                        nums.remove(idx + 1);
                    }else{
                        idx++;
                    }

                }
            }
            answer = Math.max(answer,Math.abs(nums.getFirst()));
        }

        return answer;
    }

    public long operate(long n1, long n2, char oper){
        if(oper == '*') return n1*n2;
        if(oper == '-') return n1-n2;
        if(oper == '+') return n1+n2;
        return -1;
    }
}