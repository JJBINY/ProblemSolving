package 프로그래머스.카카오.Lv4;

import java.util.*;

public class Lv4_무지의_먹방_라이브 {
    /*
결국 k초에 어떤 음식을 먹어야 하는지 묻는 것과 같음
20,000,000,000,000 -> k에 대한 선형탐색 불가능
food_times의 길이를 n이라고 하면 n에 대한 선형탐색한번 돌 때마다 적어도 남은 food수만큼으로 k값 줄여 나갈 수 있음
위 과정 반복하다가 k가 남은 food수보다 줄어들면 선형탐색으로 정답찾기 가능
[3, 1, 2, 4] 10
[1 -1 0 2] 3

정렬 후에 작은 것부터 한번에 지워나가는 방법O(n)도 있지만 정렬이 O(nlogn)이라 결국 이 방법이랑 시간복잡도 큰 차이는 없어 보임
*/
    long len;
    public int solution(int[] food_times, long k) {
        len = food_times.length;
        long cycle = k / food_times.length;
        k %= food_times.length; //최악의 경우 200,000으로 나눈 나머지는 [0,199,999] 20만개임
        List<Food> foods = new ArrayList<>();
        for (int i = 0; i < food_times.length; i++) {
            long left = food_times[i] - cycle;
            if(left>0){
                foods.add(new Food(i + 1, left));
            }else{
                k -= left;
            }
        }

        for (Food food : foods) {
            System.out.println("food = " + food);
        }
        return simulate(foods,k);
    }

    public int simulate(List<Food> foods, long k){
        long cycle = k / foods.size();
        k %= foods.size();
        List<Food> leftFoods = new ArrayList<>();
        for (int i = 0; i < foods.size(); i++) {
            long left = foods.get(i).time - cycle;
            if(left>0){
                leftFoods.add(new Food(foods.get(i).id, left));
            }else{
                k -= left;
            }
        }
        if(leftFoods.isEmpty()){
            return -1;
        }
        if (k < leftFoods.size()) {
            return leftFoods.get((int)k).id;
        }else{
            return simulate(leftFoods, k);
        }
    }
    class Food{
        int id;
        long time;

        public Food(int id, long time) {
            this.id = id;
            this.time = time;
        }
    }
}