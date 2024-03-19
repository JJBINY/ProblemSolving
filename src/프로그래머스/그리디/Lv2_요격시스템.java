package 프로그래머스.그리디;

import java.util.*;
public class Lv2_요격시스템 {
        public int solution(int[][] targets) {

            PriorityQueue<Target> pq = new PriorityQueue<>(Comparator.comparingInt(t->t.e));
            int X = 0;
            for(int[] t:targets){
                pq.offer(new Target(t[0],t[1]));
            }

            int ans = 0;

            int point = 0;
            while(!pq.isEmpty()){
                Target t = pq.poll();
                if(t.s>=point){
                    ans++;
                    point = t.e;
                }
            }


            return ans;
        }

        class Target{
            int s;
            int e;

            Target(int s, int e){
                this.s=s;
                this.e=e;
            }
        }
    }
