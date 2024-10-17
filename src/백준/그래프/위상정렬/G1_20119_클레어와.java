package 백준.그래프.위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/**
 * G1_20119_클레어와 물약
 * 그래프, 위상 정렬
 */
public class G1_20119_클레어와 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Node.init(N);

		 
		InDegree[] inDegrees = new InDegree[N+1];
		for(int i = 1; i < N + 1; i++) {
			inDegrees[i] = new InDegree();
		}
		
		// 위상 정렬을 위해 간선 연결 및 진입차수를 계산한다
		while(M-->0) { // O(sum(k)), sum(k) <= 400,000
			st=  new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int[] srcs = new int[k];
			for(int i =0 ; i<k;i++) {
				srcs[i] = Integer.parseInt(st.nextToken());
			}
			
			// 도착지의 seq번째 레시피의 진입차수 기록
			int dst = Integer.parseInt(st.nextToken());
			int seq =inDegrees[dst].amounts.size(); 
			inDegrees[dst].amounts.add(k);
			
			// 간선 연결
			Edge edge = new Edge(Node.of(dst), seq);
			for(int src : srcs) {
				Node.of(src).edges.add(edge);
			}
		}
		
		// 현재 보유한 물약 노드들부터 위상 정렬 시작
		Deque<Integer> q = new ArrayDeque<>();
		int L = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		boolean[] visited = new boolean[N+1];
		while(L-- >0) { // O(L)
			int id = Integer.parseInt(st.nextToken());
			visited[id] = true;
			q.add(id);
		}
		
		// 위상 정렬 시작
		while(!q.isEmpty()) { // O(N)
			int cur = q.poll();
			for(Edge edge : Node.of(cur).edges) {
				Node next = edge.next;
				int seq = edge.seq;
				inDegrees[next.id].minusAt(seq);
				if(inDegrees[next.id].amounts.get(seq) > 0) continue;
				if(visited[next.id]) continue;
				visited[next.id] = true;
				q.offer(next.id);
			}
		}
		
		// inDegree 값이 0 이하인 경우 만들 수 있다.
		int[] results = IntStream.range(1, N+1).filter(i->visited[i]).toArray(); // O(N)
		StringBuilder sb = new StringBuilder();
		for(int r : results) {
			sb.append(r).append(" ");
		}
		System.out.println(results.length);
		System.out.println(sb.toString());
	}
	
	static class Node{
		static Node[] nodes;
		int id;
		List<Edge> edges = new ArrayList<>();
		
		static void init(int N) {
			nodes = new Node[N+1];
		}
		
		static Node of(int id) {
			if(nodes[id] == null) {
				nodes[id] = new Node();
				nodes[id].id=id;
			}
			return nodes[id];
		}
	}
	
	static class Edge{
		Node next;
		int seq;
		public Edge(Node next, int seq) {
			this.next=next;
			this.seq=seq;
		}
	}
	
	static class InDegree{
		List<Integer> amounts = new ArrayList<>();
		
		public void minusAt(int seq) {
			amounts.set(seq, amounts.get(seq)-1);
		}
	}
}