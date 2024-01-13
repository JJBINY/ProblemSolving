class 라_코딩테스트공부 {
    /*
    1:15:55
    dp나 다익스트라 모델링
    한번 풀어본거라 빨리푼듯.
    * */
    public int solution(int alp, int cop, int[][] problems) {

        int targetAlp = alp;
        int targetCop = cop;
        for (int i = 0; i < problems.length; i++) {
            targetAlp = Math.max(targetAlp, problems[i][0]);
            targetCop = Math.max(targetCop, problems[i][1]);
        }

        int[][] dp = new int[targetAlp + 2][targetCop + 2];
        for (int i = alp; i <= targetAlp; i++) {
            for (int j = cop; j <= targetCop; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[alp][cop] = 0;


        for (int i = alp; i <= targetAlp; i++) {
            for (int j = cop; j <= targetCop; j++) {
                dp[i][j + 1] = Math.min(dp[i][j + 1],dp[i][j] +1);
                dp[i+1][j] = Math.min(dp[i+1][j],dp[i][j] +1);

                for (int[] problem : problems) {
                    if(i < problem[0] || j < problem[1]) continue;
                    int na = Math.min(i + problem[2], targetAlp);
                    int nc = Math.min(j + problem[3], targetCop);
                    dp[na][nc] = Math.min(dp[na][nc],dp[i][j]+problem[4]);
                }
            }
        }
        return dp[targetAlp][targetCop];
    }
}