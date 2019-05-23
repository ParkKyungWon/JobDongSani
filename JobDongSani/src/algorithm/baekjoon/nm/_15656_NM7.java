package algorithm.baekjoon.nm;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class _15656_NM7 {
    private static void permutationDup(int N, int M, LinkedList<Integer> rCom, int[] nums, StringBuilder sb){
        if(rCom.size() == M){
            for(int i : rCom){
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 0; i < N; i++) {
            rCom.add(nums[i]);
            permutationDup(N, M, rCom, nums, sb);
            rCom.removeLast();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str;
        int N = 0, M = 0;
        int[] nums = null;
        while((str = br.readLine()) != null){
            String[] strArr = str.split(" ");
            if(N == 0){
                N = Integer.parseInt(strArr[0]);
                M = Integer.parseInt(strArr[1]);
                nums = new int[N];
            }else{
                for (int i = 0; i < N; i++) {
                    nums[i] = Integer.parseInt(strArr[i]);
                }
                break;
            }
        }
        Arrays.sort(nums);
        LinkedList<Integer> rCom = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        permutationDup(N, M, rCom, nums, sb);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
