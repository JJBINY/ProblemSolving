package 프로그래머스.데브매칭2021;
import java.util.HashMap;
import java.util.Map;

public class 다_다단계칫솔판매 {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        Map<String, Node> map = new HashMap<>();
        map.put("-", new Node(null,"center"));
        for (int i = 0; i < enroll.length; i++) {
            map.put(enroll[i], new Node(map.get(referral[i]),enroll[i]));
        }

        for (int i = 0; i < seller.length; i++) {
            map.get(seller[i]).addIncome(amount[i]*100);
        }

        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = map.get(enroll[i]).income;
        }
        return answer;
    }

    class Node{
        Node parent;
        String name;
        int income = 0;

        public Node(Node parent, String name) {
            this.parent = parent;
            this.name = name;
        }


        public void addIncome(int income){
            if(income*0.1 < 1){
                this.income += income;
                return;
            }

            int commission = (int) (income * 0.1);
            this.income += income - commission;
            if(parent != null) parent.addIncome(commission);
        }
    }
}