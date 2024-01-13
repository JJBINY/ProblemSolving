package 프로그래머스.카카오.Lv3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lv3_표현_가능한_이진트리 {
    /*
      1, 3, 7, 15, 31, 63, ..., 2^n-1

      리프노드 나올때까지 재귀적으로 가운데를 루트로 양옆 쪼갬
      63 = 111111 -> (11) 1 (111) -> ( 1 (1)) 1 ((1) 1 (1)) -> 가능
      111 = 1101111 -> (110) 1 (111) -> ((1) 1 (0)) 1 ((1) 1 (1)) -> 가능
      95 = 1011111 -> (101) 1 (111) -> ((1) 0 (1)) 1 ((1) 1 (1)) -> 불가능
      42 = 101010 -> (10) 1 (010) -> ( 1 (0)) 1 ((0) 1 (0))
      8 = 1000 -> (1) 0 (00)
      10 = 1010 -> (1) 0 (10) -> (1) 0 (1 (0))
      11 = 1011 -> (1) 0 (11) -> (1) 0 (1 (1))
      13 = 1101 -> (1) 1 (01) -> (1) 0 (0 (1))
      14 = 1110 -> (1) 1 (10) -> (1) 1 (1 (0)) -> 가능
      15 = 1111 -> (1) 1 (11) -> (1) 1 (1 (1)) -> 가능

      리프가 아닌 노드가 0인 경우 불가능, 그외엔 가능

      1. 이진수(String)으로 변경
      2. 재귀적으로 문자열 길이 절반 기준으로 좌우로 쪼갬 -> 문자열 길이가 1이 될때까지
      3. 재귀적 호출 과정에서 루트가 0인데 자식 노드에 1이 존재하는 경우 불가능

      */
    public int[] solution(long[] numbers) {
        int x = 2;
        int maxLen = Long.toBinaryString(Arrays.stream(numbers).max().getAsLong()).length();
        List<Integer> treeLen = new ArrayList<>();
        for (int i = 0; i <= maxLen; i++) {
            if(i > x-1){
                x *= 2;
            }
            treeLen.add(x-1);
        }

        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            String binary = Long.toBinaryString(numbers[i]);
            binary = "0".repeat(treeLen.get(binary.length()) - binary.length()) + binary;

            isPossible = true;
            int node = checkTree(binary);
            // System.out.println(numbers[i] + " = " + binary + ", node = "+node);
            answer[i] = isPossible&&node>0 ? 1 : 0;
        }
        return answer;
    }

    boolean isPossible;
    public int checkTree(String binary) {
        // System.out.println("subTree = "+binary);
        if(!isPossible){
            return 0;
        }
        int root = (binary.length()-1) / 2;
        int cnt = 0;
        if(binary.charAt(root) == '1') cnt = 1;

        if (binary.length() == 1){
            return cnt;
        }

        int left = checkTree(binary.substring(0, root));
        int right = checkTree(binary.substring(root + 1));
        if (binary.charAt(root) == '0' && left+right >0){
            isPossible = false;
        }
        // System.out.println(cnt+left+right);
        return cnt+left+right;
    }
}
