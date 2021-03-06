package programmers.exercise.stackqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class IronBar {
    public static void main(String[] args) {
        String arrangement = "()(((()())(())()))(())";
        int ans = solution(arrangement);
        System.out.println(ans);
    }

    public static int solution(String arrangement) {
        int answer = 0;
        char[] chArr = arrangement.toCharArray();
        Stack<Character> change = new Stack<>();
        for (char ch : chArr) {
            if (change.isEmpty() || change.peek().charValue() != '(') {
                change.push(ch);
            } else {
                if (change.peek().charValue() == '(' && ch == ')') {
                    change.pop();
                    change.push('L');
                } else {
                    change.push(ch);
                }
            }
        }

        int left = 0; // (
        int right = 0; // )
        int laser = 0;
        Stack<Character> tmp = new Stack<>();
        while (!change.isEmpty()) {
            if (change.peek().charValue() == 'L') {
                if (left == 0 && right == 0) {
                    change.pop();
                } else {
                    tmp.push(change.pop());
                    laser++;
                }
            } else if (change.peek().charValue() == ')') {
                right++;
                tmp.push(change.pop());
            } else { // (
                left++;
                if (left == right) { // 초기화
                    answer += (laser + 1);
                    left = 0;
                    right = 0;
                    laser = 0;
                    change.pop();
                    while(tmp.size() > 1){
                        change.push(tmp.pop());
                    }
                    tmp.pop();
                }else{
                    tmp.push(change.pop());
                }
            }
        }
        return answer;
    }
}
