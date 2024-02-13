package 백준.많은조건분기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

/**
 * G3 1111 IQ Test
 * 수학, 많은 조건 분기
 */
public class G3_1111_IQTest {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = parseInt(br.readLine());
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        br.close();

        /*
        x0*a+b = x1
        x1*a+b = x2
        a = (x0-x1) / (x1-x2)

        X*a+b = X
         */

        if (N > 1 && Arrays.stream(nums).filter(num -> num == nums[0]).count() == N) {
            System.out.println(nums[0]);
        } else if (N < 3) {
            System.out.println('A');
        } else {
            if (nums[0] == nums[1]) {
                System.out.println('B');
            } else if (!isPossible(nums)) {
                System.out.println('B');
            } else {
                int a = (nums[1] - nums[2]) / (nums[0] - nums[1]);
                int b = nums[1] - a * nums[0];
                System.out.println(nums[N - 1] * a + b);
            }
        }

    }

    static boolean isPossible(int[] nums) {
        int a = (nums[1] - nums[2]) / (nums[0] - nums[1]);
        int b = nums[1] - a * nums[0];

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] * a + b != nums[i + 1]) {
                return false;
            }
        }
        return true;
    }
}

