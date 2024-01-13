package 프로그래머스.카카오.Lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lv2_이모티콘_할인행사 {

    /*
    이모티콘마다 할인율 최악의 경우 7^4
    유저 100명 = 10^2
    10^6
    1,000,000 -> 완탐으로 충분
    */

    int[] discountRate;
    List<int[]> discountRates = new ArrayList<>();

    public int[] solution(int[][] users, int[] emoticons) {
        discountRate = new int[emoticons.length];
        simulate(0);

        int maxSignedUser = 0;
        int salesAt = 0;

        for (int[] rate : discountRates) {
            int signedUser = 0;
            int sales = 0;
            for (int[] user : users) {
                int buy = 0;
                for (int i = 0; i < rate.length; i++) {
                    if (rate[i] >= user[0]) {
                       buy += emoticons[i]*(100-rate[i])/100;
                    }
                }
                if(buy >= user[1]){
                    signedUser+=1;
                }else{
                    sales += buy;
                }
            }
            if(signedUser > maxSignedUser){
                maxSignedUser = signedUser;
                salesAt = sales;
            }else if(signedUser == maxSignedUser){
                salesAt = salesAt > sales ? salesAt : sales;
            }
        }

        return new int[]{maxSignedUser,salesAt};
    }

    public void simulate(int idx) {
        if (idx == discountRate.length) {
            discountRates.add(discountRate.clone());
            return;
        }
        for (int rate = 10; rate <= 40; rate += 10) {
            discountRate[idx] = rate;
            simulate(idx + 1);
        }
    }
}