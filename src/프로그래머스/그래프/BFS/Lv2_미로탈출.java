package 프로그래머스.그래프.BFS;

import java.util.*;

class Lv2_미로탈출 {
    public int solution(String[] maps) {
        int answer = 0;
        int R = maps.length;
        int C = maps[0].length();

        Node start = null;

        for(int i = 0;i<R;i++){
            for(int j = 0;j<C;j++){
                if(maps[i].charAt(j) == 'S'){
                    start = new Node(i,j,0,0);

                    break;
                }
            }
        }
        Queue<Node> Q = new LinkedList<>();
        Q.offer(start);
        boolean[][][] visited = new boolean[R][C][2];
        visited[start.r][start.c][start.l] = true;

        int[] dr= new int[]{1,-1,0,0};
        int[] dc= new int[]{0,0,-1,1};

        while(!Q.isEmpty()){
            Node now = Q.poll();
            // System.out.println(now);

            if(maps[now.r].charAt(now.c)  == 'E'&& now.l == 1){
                return now.seq;
            }

            for(int i=0;i<4;i++){
                int nr = now.r+dr[i];
                int nc = now.c+dc[i];
                if(nr<0 || nr>=R || nc<0 ||nc>=C) continue;
                if(maps[nr].charAt(nc) == 'X') continue;
                int nl = now.l | (maps[nr].charAt(nc) == 'L'?1:0);
                if(visited[nr][nc][nl]) continue;
                visited[nr][nc][nl] = true;

                Q.offer(new Node(nr,nc,nl,now.seq+1));
            }
        }

        return -1;
    }

    class Node{
        int r;
        int c;
        int l;
        int seq;
        public Node(int r, int c,int l, int seq){
            this.r=r;
            this.c=c;
            this.l=l;
            this.seq=seq;
        }

        public String toString(){
            return String.format("%d %d %d %d",r,c,l,seq);
        }
    }
}