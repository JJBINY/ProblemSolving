package 백준.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Integer.parseInt;

/**
 * S5 3711 학번
 * 브루트포스
 */
public class S5_3711_학번 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            while (N-->0){
                int G = parseInt(br.readLine());
                int[] arr = new int[G];
                for (int i = 0; i <G; i++) {
                    arr[i] = parseInt(br.readLine());
                }

                int m = 1;

                while (true) {
                    Set<Integer> set = new HashSet<>();
                    for (int i = 0; i < G; i++) {
                        if (!set.add(arr[i] % m)) {
                            break;
                        }
                    }
                    if(set.size() == G){
                        break;
                    }
                    m++;
                }
                sb.append(m).append("\n");
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
