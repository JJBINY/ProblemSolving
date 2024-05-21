package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;


/**
 * G4 2262 토너먼트 만들기
 * 그리디
 */
public class G4_2262_토너먼트만들기 {
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = 1;
//            int T = parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        1..N등까지 있을 때, 최소가 되려면?
        매 라운드마다 가장 등수가 낮은 선수를 골라 탈락.
        경기 진행될때마다 남은 선수 1234순이던 4321 순이던 어느 한쪽방향으로 정렬되도록
        등수가 가장 낮은 선수가 x번째에 있으면 x-1, x+1 중 등수가 더 낮은 쪽과 대결하는 것이 랭킹 차 최소
        why? 무조건 등수가 높은 선수가 이기므로 (1...x-1)사이 경기 승자의 랭킹은 x-1번째보다 높거나 같음, x+1...N도 마찬가지
        따라서 다음 라운드와 상관 없이 현재 가장 등수가 낮은 선수부터 위 조건에 따라 제거하는 것이 최선
        -> 현재 최선의 선택이 최적이므로 그리디
     */
    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        LinkedList<Integer> list = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        int result = 0;

        for (int i = N; i >1; i--) {
            Iterator<Integer> iterator = list.iterator();
            int prev = 0;
            while (iterator.hasNext()){
                int now = iterator.next();
                if(now == i){
                    iterator.remove();
                    int diff = N;
                    if(iterator.hasNext()){
                        diff = Math.min(diff, Math.abs(now - iterator.next()));
                    }
                    if(prev>0){
                        diff = Math.min(diff, Math.abs(now - prev));
                    }
                    result += diff;
                    break;
                }
                prev = now;
            }
        }

        return result;
    }
}