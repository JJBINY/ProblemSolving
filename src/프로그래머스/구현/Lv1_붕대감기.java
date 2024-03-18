package 프로그래머스.구현;
public class Lv1_붕대감기 {
    public int solution(int[] bandage, int health, int[][] attacks) {

        int hp = health;
        int time = 0;
        int aSeq = 0;
        int bSeq = 0;
        while(aSeq<attacks.length){
            if(time == attacks[aSeq][0]){
                hp -= attacks[aSeq++][1];
                bSeq = 0;
            }else{

                hp += bandage[1];
                bSeq++;
                if(bSeq >= bandage[0]){
                    hp += bandage[2];
                    bSeq = 0;
                }
                hp = Math.min(hp,health);
            }
            time++;
            if(hp<=0){
                return -1;
            }
        }

        return hp;
    }
}