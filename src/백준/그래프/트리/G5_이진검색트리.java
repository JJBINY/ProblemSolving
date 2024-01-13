package 백준.그래프.트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * G5_Binary_Search_Tree
 * https://www.acmicpc.net/problem/5639
 */
public class G5_이진검색트리 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Integer> inputs = new ArrayList<>();

    public static void main(String[] args) {

        try {
            while (true) {
                inputs.add(Integer.parseInt(br.readLine()));
            }
        } catch (Exception e) {
        }

        printPostorder(0, inputs.size());

    }

    // [start, end)
    private static void printPostorder(int start, int end) {

        if (start >= end) {
            return;
        }

        //왼쪽 오른쪽 구분
        int root = inputs.get(start);
        int mid = start + 1;

        //루트 보다 작은 값들은 루트의 왼쪽 서브트리에 속한다.
        while (mid < end && inputs.get(mid) < root) {
            mid += 1;
        }

        printPostorder(start + 1, mid); //[start+1, mid)
        printPostorder(mid, end); // [mid, end)
        // subtree의 자식 노드들이 모두 출력 완료된 이후 root출력
        System.out.println(root);
    }

}