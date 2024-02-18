package 백준.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * G4 10464 XOR
 * 수학, 누적합?
 */
public class G4_10464_XOR {
    /*
    x^0 = x
    x^x = 0
    (x^y)^z = x^(y^z)

    x -> 1^...^x
    0001 -> 0001 = 1
    0010 -> 0011 = 3
    0011 -> 0000 = 0
    0100 -> 0100 = x

    0101 -> 0001 = 1
    0110 -> 0111 = 7
    0111 -> 0000 = 0
    1000 -> 1000 = x

    1001 -> 0001 = 1
    1010 -> 1011 = 11
    1011 -> 0000 = 0
    1100 -> 1100 = x

    1101 -> 0001 = 1
    1110 -> 1111 = 15
    1111 -> 0000 = 0
    10000-> 10000 = x
     */
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();

            int T = parseInt(br.readLine());
            while (T-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int S = parseInt(st.nextToken());
                int F = parseInt(st.nextToken());
                int ans = calculateCumulativeXOR(S - 1) ^ calculateCumulativeXOR(F);
                sb.append(ans).append("\n");
            }

            System.out.println(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int calculateCumulativeXOR(int x){
        int mod = x % 4;
        if(mod == 1){
            return 1;
        }else if(mod == 2){
            return 3 + 4 * (x / 4);
        }else if(mod == 3){
            return 0;
        }else{
            return x;
        }
    }
}
