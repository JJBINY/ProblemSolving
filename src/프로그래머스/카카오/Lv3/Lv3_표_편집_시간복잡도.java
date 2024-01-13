package 프로그래머스.카카오.Lv3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Lv3_표_편집_시간복잡도 {
    /*
    초기표 리스트 생성(id)
    조작할 표 리스트 생성
    명령어에 따라 표 조작

    C : 현재 선택된 행 스택에 삽입(데이터, 행번호) 및 조작 리스트에서 삭제
    Z : 스택에서 꺼내 리스트에 복구

    1. 연결리스트
    2. 이분탐색or세그먼트트리
    */
    Stack<Data> stack = new Stack<>();
    List<Data> dataList = new ArrayList<>();
    Data selected;
    Data head;
    Data tail;
    public String solution(int n, int k, String[] cmd) {

        head = Data.of(-1);
        tail = Data.of(n);
        head.next = tail;
        tail.prev = head;
        Data now = head;
        for (int i = 0; i < n; i++) {
            Data next = Data.of(i);
            now.next.prev = next;
            next.next = now.next;

            now.next = next;
            next.prev = now;

            now = next;
            dataList.add(next);
        }

        selected = head.next;
        for (int i = 0; i < k; i++) {
            selected = selected.next;
        }


        for (String command : cmd) {
            switch (command.charAt(0)){
                case 'U' :
                    up(Integer.parseInt(command.split(" ")[1]));
                    break;
                case 'D' :
                    down(Integer.parseInt(command.split(" ")[1]));
                    break;
                case 'C' :
                    remove();
                    break;
                case 'Z' :
                    restore();
                    break;
                default:
                    throw new RuntimeException("invalid command");
            }
            // System.out.println("command = " + command);
            // System.out.println("selected.id = " + selected.id);
        }


        StringBuilder sb = new StringBuilder();
        for (Data data : dataList) {
            if(data.isDeleted()){
                sb.append('X');
            }else{
                sb.append('O');
            }
        }

        return sb.toString();
    }

    public void up(int x){
        for (int i = 0; i < x; i++) {
            selected = selected.prev;
        }
    }

    public void down(int x){
        for (int i = 0; i < x; i++) {
            selected = selected.next;
        }
    }

    public void remove(){
        selected.prev.next = selected.next;
        selected.next.prev = selected.prev;

        selected.changeState();
        stack.push(selected);
        if(selected.next == tail){
            selected = selected.prev;
        }else{
            selected = selected.next;
        }
    }

    public void restore(){
        Data data = stack.pop();
        data.changeState();
        data.prev.next = data;
        data.next.prev = data;
    }

    static class Data{
        private int id;
        private boolean deleted;
        Data prev = null;
        Data next = null;

        private Data(int id, boolean deleted) {
            this.id = id;
            this.deleted = deleted;
        }

        public static Data of(int id){
            return new Data(id, false);
        }

        public boolean isDeleted(){
            return deleted;
        }

        public void changeState(){
            this.deleted = !deleted;
        }
    }
}

