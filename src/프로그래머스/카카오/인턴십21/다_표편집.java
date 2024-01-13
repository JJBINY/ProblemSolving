import java.util.Stack;

class 다_표편집 {
    /*
    1:57:30 완

    가장 최근 삭제된 행 스택으로 기록
    연결리스트로 구현하면

    시간복잡도 단순히 계산 불가능 -> 문제 조건 잘 읽기
    문제 조건에서 전체 이동 O(X) = 1,000,000
    cmd = 200,000이므로
    삭제 O(1) -> 200000
    추가 O(1) -> 200000
     */
    public String solution(int n, int k, String[] cmd) {

        Node now = new Node(0);
        Node prev = now;
        for (int i = 1; i < n; i++) {
            Node newNode = new Node(i);
            prev.next = newNode;
            newNode.prev = prev;
            prev = newNode;
        }

        for (int i = 0; i < k; i++) {
            now = now.next;
        }

        Stack<Node> stack = new Stack<>();
        for (String command : cmd) {
            String[] cmds = command.split(" ");
            if (cmds[0].equals("Z")) {
                restore(stack);
            }else if(cmds[0].equals("C")){
                now = delete(now, stack);
            }else if(cmds[0].equals("U")){
                now = moveUp(now, cmds);
            }else if(cmds[0].equals("D")){
                now = moveDown(now, cmds);
            }
        }

        while (now.prev!=null){
            now = now.prev;
        }
        return getAns(n, now);
    }

    private static String getAns(int n, Node now) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if(now != null && now.id == i){
                sb.append('O');
//                System.out.println("now.id = " + now.id);
                now = now.next;
            }else{
                sb.append('X');
            }
        }

        return sb.toString();
    }

    private static void restore(Stack<Node> stack) {
        stack.pop().restore();
    }

    private static Node delete(Node now, Stack<Node> stack) {
        stack.push(now);
        if(now.next == null){
            now.prev.next = null;
            now = now.prev;
        }else {
            now.next.prev = now.prev;
            if(now.prev != null) {
                now.prev.next = now.next;
            }
            now = now.next;
        }
        return now;
    }

    private static Node moveDown(Node now, String[] cmds) {
        for (int i = 0; i < Integer.parseInt(cmds[1]); i++) {
            now = now.next;
        }
        return now;
    }

    private static Node moveUp(Node now, String[] cmds) {
        for (int i = 0; i < Integer.parseInt(cmds[1]); i++) {
            now = now.prev;
        }
        return now;
    }

    class Node{
        int id;
        Node next;
        Node prev;

        public Node(int id) {
            this.id = id;
        }

        public void restore(){
            if(this.prev != null) {
                this.prev.next = this;
            }
            if(this.next != null) {
                this.next.prev = this;
            }
        }

    }
}