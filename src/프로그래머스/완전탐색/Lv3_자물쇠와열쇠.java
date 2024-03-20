package 프로그래머스.완전탐색;

public class Lv3_자물쇠와열쇠 {
    //완전탐색
    static int N, M;
    static int[][] lock;
    static int[][] key;
    static int hole;

    public boolean solution(int[][] key, int[][] lock) {
        N = lock.length;
        M = key.length;
        this.key = key;
        this.lock = new int[3 * N][3 * N]; //3배로 확장

        // 자물쇠를 확장된 lock의 중앙에 위치시키기
        hole = N * N; // 자물쇠의 홈 부분 갯수
        for (int i = N; i < 2 * N; i++) {
            for (int j = N; j < 2 * N; j++) {
                hole -= lock[i - N][j - N];
                this.lock[i][j] = lock[i - N][j - N];
            }
        }//fori

        //자물쇠를 회전시키면서 매칭
        for (int i = 0; i < 4; i++) {
            // print(this.key);
            if (solve()) {
                return true;
            }
            rotateKey();
        }
        return false;
    }

    public boolean solve() {
        for (int i = 0; i < 3 * N - M; i++) { //열쇠위치 조정
            for (int j = 0; j < 3 * N - M; j++) {
                if (match(i, j)) { //열쇠가 (i,j)일 때 자물쇠와 매칭
                    return true;
                }
            }
        }//fori

        return false;
    }

    // 열쇠 위치(r,c)에서 자물쇠와 열쇠 매칭
    public boolean match(int r, int c) {
        int cnt = 0;
        for (int i = 0; i < M; i++) {
            if (r + i < N || r + i >= 2 * N) continue; //자물쇠 외부
            for (int j = 0; j < M; j++) {
                if (c + j < N || c + j >= 2 * N) continue; //자물쇠 외부
                // 자물쇠 영역 내부만 고려
                if (lock[r + i][c + j] == key[i][j]) return false; // 정확하게 일치해야함 -> xor 참
                if (lock[r + i][c + j] == 0) cnt++; //열쇠가 구멍을 매운 경우
            }//forj
        }//fori
        return cnt == hole; //자물쇠 빈 구멍을 모두 매운경우;
    }

    public void rotateKey() {
        int[][] nk = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                nk[j][M - 1 - i] = key[i][j];
            }
        }
        key = nk;
    }

    public void print(int[][] arr) {
        for (int[] ar : arr) {
            for (int a : ar) {
                System.out.print(a);
            }
            System.out.println();
        }
    }
}