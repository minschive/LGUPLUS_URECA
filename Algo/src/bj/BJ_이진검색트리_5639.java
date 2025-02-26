package bj;

//import java.io.*;
//import java.util.*;
//// 방법 1
//public class BJ_이진검색트리_5639 {
//    // 전역 배열로 전위 순회 결과를 저장할 배열
//    static int[] preorder;
//
//    // 후위 순회 결과를 저장할 StringBuilder
//    static StringBuilder sb;
//
//    public static void main(String[] args) throws Exception {
//        // 빠른 입출력을 위한 BufferedReader, BufferedWriter 사용
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        // 후위 순회 결과를 한꺼번에 출력하기 위해 StringBuilder 초기화
//        sb = new StringBuilder();
//
//        // 입력을 임시 저장할 리스트
//        List<Integer> list = new ArrayList<>();
//        String input;
//        int size = 0;
//
//        // EOF(파일 끝)까지 입력을 받음
//        while ((input = br.readLine()) != null && !input.isEmpty()) {
//            list.add(Integer.parseInt(input));
//            size++;
//        }
//
//        // 전위 순회 결과를 배열로 옮김
//        preorder = new int[size];
//        for (int i = 0; i < size; i++) {
//            preorder[i] = list.get(i);
//        }
//
//        // 후위 순회 함수 호출 (전 구간: [0, size))
//        postOrder(0, size);
//
//        // 결과 출력
//        bw.write(sb.toString());
//        bw.flush();
//        bw.close();
//        br.close();
//    }
//
//    // postOrder: [start, end) 구간을 후위 순회하여 sb에 결과 저장
//    static void postOrder(int start, int end) {
//        // 구간이 비어있으면 재귀 종료
//        if (start >= end) {
//            return;
//        }
//
//        // 전위 순회에서 첫 번째 값이 루트
//        int root = preorder[start];
//
//        // 루트보다 큰 값이 시작되는 지점(boundary)을 찾음
//        int boundary = start + 1;
//        while (boundary < end && preorder[boundary] < root) {
//            boundary++;
//        }
//
//        // 왼쪽 서브트리에 대해 재귀 (start+1 부터 boundary 직전)
//        postOrder(start + 1, boundary);
//        // 오른쪽 서브트리에 대해 재귀 (boundary 부터 end 직전)
//        postOrder(boundary, end);
//
//        // 후위 순회에서는 루트를 마지막에 추가
//        sb.append(root).append("\n");
//    }
//}

// 방법 2
import java.io.*;
import java.util.*;

public class BJ_이진검색트리_5639 { // 보조강사님 풀이
    // 전역 변수로 출력할 결과를 저장할 StringBuilder
    static StringBuilder sb;
    // 루트 노드를 저장할 전역 변수
    static Node root = null;
    
    // 노드 클래스 정의
    static class Node {
        int data;
        Node left, right;
        
        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    
    public static void main(String[] args) throws Exception {
        // 빠른 입출력을 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 빠른 출력을 위한 BufferedWriter 사용
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 결과를 저장할 StringBuilder 초기화
        sb = new StringBuilder();
        
        String input;
        // EOF까지 입력을 받음
        while ((input = br.readLine()) != null && !input.isEmpty()) {
            int value = Integer.parseInt(input);
            root = insertNode(root, value);
        }
        
        // 후위 순회 실행
        postOrder(root);
        
        // 결과 출력
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    
    // insertNode: BST에 새로운 노드를 삽입하는 함수
    static Node insertNode(Node node, int value) {
        // 현재 위치가 비어있다면 새 노드 생성
        if (node == null) {
            return new Node(value);
        }
        
        // value 가 현재 노드보다 작거나 같으면 왼쪽으로 삽입
        if (value <= node.data) {
            node.left = insertNode(node.left, value);
        }
        // value 가 현재 노드보다 크면 오른쪽으로 삽입
        else {
            node.right = insertNode(node.right, value);
        }
        
        return node;
    }
    
    // postOrder: 후위 순회를 수행하고 결과를 StringBuilder에 저장
    static void postOrder(Node node) {
        // 노드가 null 이면 종료
        if (node == null) {
            return;
        }
        
        // 왼쪽 서브트리 순회
        postOrder(node.left);
        // 오른쪽 서브트리 순회
        postOrder(node.right);
        // 현재 노드의 값을 StringBuilder에 추가
        sb.append(node.data).append("\n");
    }
}