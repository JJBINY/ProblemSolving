package 프로그래머스.그래프.BFS;

import java.util.*;

class Lv3_수레움직이기 {

    /*
    BFS, 최단경로, 구현, 조합, 비트마스킹
     */
    public int solution(int[][] maze) {

        int N = maze.length;
        int M = maze[0].length;
        Node node = new Node();
        for(int i =0; i < N; i++){
            for(int j =0; j < M; j++){
                if(maze[i][j] == 1){
                    node.red = new Cart(i,j,1<<i*M+j);
                }else if(maze[i][j] == 2){
                    node.blue = new Cart(i,j,1<<i*M+j);
                }
            }
        }

        //현재 위치
        //방문 지점 -> 비트마스킹
        //이동 횟수
        Queue<Node> Q = new LinkedList<>();
        Q.offer(node);

        int[] dr = new int[]{-1,1,0,0};
        int[] dc = new int[]{0,0,1,-1};
        while(!Q.isEmpty()){
            Node now = Q.poll();
            Cart red = now.red;
            Cart blue = now.blue;


            if(maze[red.r][red.c] == 3 && maze[blue.r][blue.c] == 4){
                return now.seq;
            }

            for(int i = 0 ; i<4;i++){
                int nrr, nrc, nrs;
                if(maze[red.r][red.c] == 3){ //이미 도착지점인 경우 위치 고정
                    nrr = red.r;
                    nrc = red.c;
                    nrs = red.state;
                }else{
                    nrr = red.r + dr[i];
                    nrc = red.c + dc[i];

                    if(nrr<0||nrr>=N || nrc<0 || nrc>=M) continue;
                    if(maze[nrr][nrc] == 5) continue;//벽
                    // if(nrr == blue.r && nrc == blue.c) continue;

                    int rdx = nrr*M+nrc; //다음 위치 후보
                    if((red.state&(1<<rdx)) > 0) continue; //이미 방문한 위치

                    nrs = red.state|(1<<rdx);

                }
                Cart nr = new Cart(nrr,nrc,nrs);

                for(int j = 0; j<4; j++){
                    int nbr, nbc, nbs;
                    if(maze[blue.r][blue.c] == 4){ //이미 도착지점인 경우 위치 고정
                        nbr = blue.r;
                        nbc = blue.c;
                        nbs = blue.state;
                    }else{
                        nbr = blue.r + dr[j];
                        nbc = blue.c + dc[j];

                        if(nbr<0||nbr>=N || nbc<0 || nbc>=M) continue;
                        if(maze[nbr][nbc] == 5) continue; //벽

                        int bdx = nbr*M+nbc; //다음 위치 후보
                        if((blue.state&(1<<bdx)) > 0) continue; //이미 방문한 위치
                        nbs = blue.state|(1<<bdx);
                    }

                    if(nbr == nrr && nbc == nrc) continue; //수레 겹침
                    if(nbr == red.r && nbc == red.c && nrr == blue.r && nrc == blue.c) continue; //교차하는 경우

                    Cart nb = new Cart(nbr,nbc,nbs);
                    Node next = new Node(nr,nb,now.seq+1);
                    Q.offer(next);
                } //for nb
            } // for nr
        } //while - bfs

        return 0;
    }

    static class Cart{
        int r;
        int c;
        int state;
        public Cart(int r, int c, int state){
            this.r=r;
            this.c=c;
            this.state=state;

        }

        @Override
        public String toString(){
            return String.format("r = %d, c = %d, state = %s",r,c,Integer.toBinaryString(state));
        }
    }

    static class Node{
        Cart red;
        Cart blue;
        int seq;
        public Node(Cart red, Cart blue,int seq){
            this.red=red;
            this.blue=blue;
            this.seq = seq;
        }

        public Node(){};
    }
}