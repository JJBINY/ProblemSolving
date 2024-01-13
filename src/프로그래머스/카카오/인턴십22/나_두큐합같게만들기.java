import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class 나_두큐합같게만들기 {
    /*
    2:31:06
    전체 큐의 합의 절반으로 각각을 만들어야 함.
    q1q2순으로 붙인다음
    l=0, r=q1.length에서부터 시작하는 투포인터

     */
    public int solution(int[] queue1, int[] queue2) {
        long sum1 = Arrays.stream(queue1).sum();
        long sum2 = Arrays.stream(queue2).sum();
        long target = (sum1 + sum2) / 2;

        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.stream(queue1).boxed().collect(Collectors.toList()));
        list.addAll(Arrays.stream(queue2).boxed().collect(Collectors.toList()));

        int answer = 0;

        int l=0;
        int r = queue1.length;

        long sum = sum1;
        while (r<list.size()) {

            while(sum>target){
                sum -= list.get(l);
                l++;
                answer++;
            }
            if(sum == target){
                return answer;
            }
            sum += list.get(r);
            r++;
            answer++;
        }
        return -1;
    }
}