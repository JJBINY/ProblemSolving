package 프로그래머스.그리디;

import java.util.*;

public class Lv3_n더하기1카드게임 {

    /*
    그리디

    cards의 원소는 중복되지 않음
    cards의 길이 = n이므로 원소는 1부터 n까지 하나씩 존재
    n은 6의 배수 즉 짝수
    따라서 n+1은 홀수이므로 짝수 홀수 하나씩 뽑아야 한다.
    => 원소당 n+1을 만들기 위한 단 하나의 짝이 존재한다. (k개의 원소를 가지고 있다면 O(k^2)에 탐색 가능)
    */
    public int solution(int coin, int[] cards) {

        int n = cards.length;
        List<Integer> decks = new ArrayList<>();
        for (int i = 0; i < n / 3; i++) {
            decks.add(cards[i]);
        }

        List<Integer> spairs = new ArrayList<>(); //핵심 아이디어 : 코인을 사용해 카드를 뽑는 선택을 나중에 필요할 때로 미룬다.
        int idx = n / 3;
        int round = 1;

        // 대충 최악의 경우 3*(n/3)^3 -> n^3 /9 ~= 10^8 쯤
        while (idx < n) { // n/3 반복 (한번에 idx 2씩 증가)

            spairs.add(cards[idx++]);
            spairs.add(cards[idx++]);

            // 1.손패에서 찾기
            if (removePair(n + 1, decks)) { // (n/3)^2
                round++;
                continue;
            }

            // 2. 손패 + 추가 카드
            if (coin > 0 && removePair(n + 1, decks, spairs)) { // n/3 * coin
                coin -= 1;
                round++;
                continue;
            }

            // 3. 추가 카드에서 찾기
            if (coin >= 2 && removePair(n + 1, spairs)) { // 4*round^2
                coin -= 2;
                round++;
                continue;
            }
            break;
        }


        return round;
    }

    // i,j; cards.get(i)+cards.get(j) == target
    public boolean removePair(int target, List<Integer> cards) {
        for (int i = 0; i < cards.size(); i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                if (cards.get(i) + cards.get(j) == target) {
                    cards.remove(j);
                    cards.remove(i);
                    return true;
                }
            }
        } //for i

        return false;
    }

    // i, j; cards1.get(i) + cards2.get(j) == target
    public boolean removePair(int target, List<Integer> cards1, List<Integer> cards2) {
        for (int i = 0; i < cards1.size(); i++) {
            if (removePair(target, cards1.get(i), cards2)) {
                cards1.remove(i);
                return true;
            }
        }

        return false;
    }

    // card,i; card + cards.get(i) == target
    public boolean removePair(int target, int card, List<Integer> cards) {
        for (int i = 0; i < cards.size(); i++) {
            if (card + cards.get(i) == target) {
                cards.remove(i);
                return true;
            }

        } //for i

        return false;
    }
}

