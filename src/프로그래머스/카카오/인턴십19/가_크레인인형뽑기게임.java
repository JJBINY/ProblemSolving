package 프로그래머스.카카오.인턴십19;

import java.util.Arrays;
import java.util.Stack;

public class 가_크레인인형뽑기게임 {
    /*
    20분

    뽑은 인형 스택에 보관
    스택에 넣기전 스택 맨위 값과 비교해서 같으면 터트림(result+=1)

    각 세로 라인마다 제일 윗줄의 인덱스 기록

     */
    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        int len = board.length;
        //현재 보드에서 각 세로 라인별 가장 높은 인형 위치
        int[] tops = new int[len];
        Arrays.fill(tops, -1);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {

                if (tops[j] > -1) continue;
                if(board[i][j] == 0) continue;
                tops[j] = i;
            }
        }

        Stack<Integer> stack = new Stack<>();
        for (int move : moves) {

            if(tops[move-1] == len) continue;
            int target = board[tops[move-1]++][move-1];

            if (stack.isEmpty()) {
                stack.push(target);
            } else {
                if(stack.peek() == target){
                    answer+=2;
                    stack.pop();
                }else{
                    stack.push(target);
                }
            }
        }

        return answer;
    }
}
