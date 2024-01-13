package 프로그래머스.카카오.Fail;

import java.util.*;
import java.util.stream.IntStream;

public class Lv4_동굴탐험 {


    /*
   루트 노드가 입구
   임의의 서로 다른 두 방 사이 최단경로 하나
   이동이 불가능한 경우 없음
   양방향 트리

   1. 모든 노드 순회
   2. 방문 순서 존재 (연속으로 방문할 필요는 없음)but 아래와 같은 경우는 존재하지 않음
   A->B, A->C
   X->A, Z->A
   A->B->C

   n개의 노드와 n-1개의 양방향 간선

    order [A,B] 중 모든 B가 방문 가능하면 전체 방문 가능함
    B를 방문하기 위해선
    1. A를 방문
    2. 부모 노드를 방문

    order를 우선순위가 가장 높은 간선으로 간주하고 간선마다 유니온파인드
    양방향 그래프를 단방향 그래프로 변경

    1. order가 존재한다면 먼저 방문
    2. 현재 노드의 부모 노드 방문
    3. 간선으로 연결된 두 노드의 부모가 같은 경우 싸이클 발생 -> 전체 방문 불가능


   */

    int[] reachable;
    boolean[] visited;
    public boolean solution(int n, int[][] path, int[][] order) {

        Node[] nodes = new Node[n];
        reachable = new int[n];

        IntStream.range(0, n).forEach(i -> {
           nodes[i] = new Node(i);
           reachable[i] = i;
        });

        Node[][] edges = new Node[n][n];
        Arrays.stream(path).forEach(p -> {
            edges[p[1]][p[0]] = nodes[p[0]];
            edges[p[0]][p[1]] = nodes[p[1]];
        });

        Queue<Node> queue = new LinkedList<>();
        queue.add(nodes[0]);

        visited = new boolean[n];
        visited[0] = true;
        while(!queue.isEmpty()){
            Node now = queue.poll();
            for (Node next : edges[now.id]) {
                if(next == null) continue;
                if(visited[next.id]) continue;
                next.parent = now;
                queue.add(next);
            }
        }

        List<Integer> targets = new ArrayList<>();
        Arrays.stream(order).forEach(o->{
            nodes[o[1]].order = nodes[o[0]];
            targets.add(o[1]);
        });


        return targets.stream().filter(t->{
            visited = new boolean[n];
            System.out.println("target = "+t);
            return !isReachable(nodes[t]);
        }).count() == 0;
    }

    public boolean isReachable(Node target){
        System.out.println("target.id = " + target.id);
        if(target.id == 0 || reachable[target.id] == 0) return true;
        if(visited[target.id]) return false;
        visited[target.id] = true;
        if(target.order != null) {

            if(!isReachable(target.order))return false;
            reachable[target.id] = 0;
        }
        if(target.parent!=null && !isReachable(target.parent)) return false;
        reachable[target.id] = 0;

        return true;
    }


    public class Node {
        int id;
        Node parent;
        Node order = null;

        public Node(int id) {
            this.id = id;
        }

    }
}