package 프로그래머스.카카오.Lv3;


import java.util.*;
import java.util.stream.IntStream;

public class Lv3_코딩테스트_공부 {
    /*
    모든 문제를 풀 수 있다 = 알고력 코딩력 각각이 모든 문제들의 알고력 코딩력 중 최대값 이상이어야 함
    studyA -> 시간 1소모 알고력 1 상승
    studyC -> 시간 1소모 코딩력 1 상승
    solve -> A[t] >= p[0] && C[t] >= p[1]인 경우 시간 p[4]소모 알고력 p[2], 코딩력 p[3] 상승

    dp를 사용해 알고력, 코딩력이 a,c만큼 도달하는데 걸리는 최소 시간 갱신해나가기
    dp[a][c]
    dp[a+1][c] = dp[a][c] + 1
    dp[a][c+1] = dp[a][c] + 1
    if(a >= p[0] && c >= p[1])
    then dp[a+p[2]][c+p[3]] = dp[a][c] + p[4]
    */
    public int solution(int alp, int cop, int[][] problems) {

        int aGoal = alp;
        int cGoal = cop;
        for(int[] p :problems){
            aGoal = Math.max(aGoal, p[0]);
            cGoal = Math.max(cGoal, p[1]);
        }

        int[][] dp = new int[aGoal+1][cGoal+1];

        for(int i = alp; i<=aGoal; i++){
            Arrays.fill(dp[i], 10000);
        }
        dp[alp][cop] = 0;


        for(int a = alp; a<=aGoal; a++){
            for(int c= cop; c<=cGoal; c++){
                if(a+1 <= aGoal) dp[a+1][c] = Math.min(dp[a+1][c], dp[a][c] + 1);
                if(c+1 <= cGoal) dp[a][c+1] = Math.min(dp[a][c+1], dp[a][c] + 1);
                for(int[] p : problems){
                    if(a >= p[0] && c >= p[1]){
                        int na = Math.min(a+p[2], aGoal);
                        int nc = Math.min(c+p[3], cGoal);
                        dp[na][nc] = Math.min(dp[na][nc], dp[a][c] + p[4]);
                    }
                }

            }
        }

        return dp[aGoal][cGoal];
    }
}

