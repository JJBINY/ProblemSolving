package 프로그래머스.카카오.Lv3;

import java.util.*;
import java.util.stream.Collectors;

public class Lv3_길찾기게임 {
    /*
    각 레벨(y)을 키로 레벨에 속하는 노드들의 리스트를 값으로 하는 Map을 생성
    Map을 이용하여 트리 구성

    레벨이 높은 순부터 주어진 조건에 맞게 트리에 노드들을 삽입
    */
    public Integer[][] solution(int[][] nodeinfo) {

        Map<Integer, List<Node>> map = getNodeMap(nodeinfo);

        List<Integer> levels = map.keySet().stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        Node root = map.get(levels.remove(0)).get(0); // 조건에 의하면 루트 노드와 같은 레벨을 루트노드밖에 존재하지 않는다.

        levels.stream().map(map::get).forEach(nodes ->
                        nodes.stream().forEach(root::add));

        List<Integer> preOrder = root.addByPreOrder(new ArrayList<>());
        List<Integer> postOrder = root.addByPostOrder(new ArrayList<>());



        Integer[][] answer = new Integer[2][];
        answer[0] = preOrder.toArray(new Integer[0]);
        answer[1] = postOrder.toArray(new Integer[0]);
        return answer;
    }

    private static Map<Integer, List<Node>> getNodeMap(int[][] nodeinfo) {
        int id = 1;
        Map<Integer, List<Node>> map = new HashMap<>();
        for (int[] point : nodeinfo) {
            Node node = new Node(id++, point[0], point[1]);
            List<Node> nodes = map.getOrDefault(node.getLevel(), new ArrayList<>());
            nodes.add(node);
            if (!map.containsKey(node.getLevel())) {
                map.put(node.getLevel(), nodes);
            }
        }
        return map;
    }

    static class Node{
        private int id;
        private int x;
        private int y;
        private Node lc = null;
        private Node rc = null;

        public Node(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
        public int getLevel(){return y;}
        public int getX(){return x;}

        public boolean add(Node child){
            Node now = this;
            while(now.getLevel() > child.getLevel()){
                System.out.println("now.id = " + now.id);
                if(now.getX() > child.getX()){
                    if(now.lc == null){
                        now.lc = child;
                        return true;
                    }else{
                        now = now.lc;
                    }
                }else{
                    if(now.rc == null){
                        now.rc = child;
                        return true;
                    }else{
                        now = now.rc;
                    }
                }
            }

            return false;
        }

        public List<Integer> addByPreOrder(List<Integer> result){
            result.add(this.id);
            if(this.lc !=null) this.lc.addByPreOrder(result);
            if(this.rc !=null) this.rc.addByPreOrder(result);
            return result;
        }

        public List<Integer> addByPostOrder(List<Integer> result){
            if(this.lc !=null)  this.lc.addByPostOrder(result);
            if(this.rc !=null)  this.rc.addByPostOrder(result);
            result.add(this.id);
            return result;
        }
    }
}