import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class 마_행렬과연산 {
    /*

    Rotate 직접 할 경우 4(n-1)번 -> 최악의 경우 대략 20만번
    명령 최대 10만번
    20만 * 10만 -> 2천억? 시간초과

    01 02 03 04 05      06 01 02 03 04
    06 07 08 09 10      11 07 08 09 05
    11 12 13 14 15      16 12 13 14 10
    16 17 18 19 20      21 17 18 19 15
    21 22 23 24 25      22 23 24 25 20

    ShiftRow -> 연결리스트 혹은 deque로 row단위로 기록해두면 O(1)
    Rotate -> 각 row를 deque으로 유지해서 왼쪽은 아래서 위로 pop&add first
    오른쪽은 위에서 아래로 pop&add last

    3 4
    1 2

    1 3
    2 4
     */
    Deque<Deque<Integer>> rows;
    Deque<Integer> left;
    Deque<Integer> right;
    public int[][] solution(int[][] rc, String[] operations) {
        initRows(rc);

        for (int i = 0; i < operations.length; i++) {

            if(operations[i].equals("Rotate")){
                rotate();
            }else{
                shiftRow();
            }
        }

        int[][] answer = new int[rc.length][rc[0].length];
        for (int i = 0; i < answer.length; i++) {
            answer[i][0] = left.poll();
            answer[i][answer[0].length - 1] = right.poll();
            Deque<Integer> row = rows.poll();
            for (int j = 1; j < answer[0].length-1; j++) {
                answer[i][j] = row.poll();
            }
        }

        return answer;
    }

    public void rotate(){
        Deque<Integer> top = rows.peekFirst();
        Deque<Integer> down = rows.peekLast();
        top.addFirst(left.pollFirst());
        right.addFirst(top.pollLast());
        down.addLast(right.pollLast());
        left.addLast(down.pollFirst());
    }
    public void shiftRow(){
        rows.addFirst(rows.pollLast());
        left.addFirst(left.pollLast());
        right.addFirst(right.pollLast());
    }


    private void initRows(int[][] rc) {
        rows = new LinkedList<>();
        left = new LinkedList<>();
        right = new LinkedList<>();

        for (int i = 0; i < rc.length; i++) {
            Deque<Integer> row = new LinkedList<>();
            for (int j = 1; j < rc[0].length-1; j++) {
                row.add(rc[i][j]);
            }
            rows.add(row);
        }
        for (int i = 0; i < rc.length; i++) {
            left.add(rc[i][0]);
            right.add(rc[i][rc[0].length - 1]);
        }
    }
}