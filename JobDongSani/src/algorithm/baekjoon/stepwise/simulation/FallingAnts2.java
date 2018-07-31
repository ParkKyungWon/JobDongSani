package algorithm.baekjoon.stepwise.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 풀이 방법: 시뮬레이션을 돌리지 않고 수학적으로 계산 돌림
 * 1. 좌/우로 떨어지는 개미의 수는 처음 개미 ID의 부호 수와 같다.
 * 2. 
 * 
 * @author user
 *
 */
public class FallingAnts2 {

	private static int findId(int[] posList, int[] idList, int[] signList, int N, int L, int k) {
		int idK = Integer.MAX_VALUE;
		int fallingCnt = 0;
		boolean[] isFallen = new boolean[N];
		while(fallingCnt < k) {
			int order = 0;
			int[] fallenSet = null;
			for(int i=0;i<N;i++) {
				posList[i] += signList[i];
				if(!isFallen[i] && (posList[i] > L || posList[i] < 0)) {
					if(order == 0)
						fallenSet = new int[2];
					fallenSet[order++] = i;
					isFallen[i] = true;
				}
			}
			for(int i=0;i<N-1;i++) {
				for(int j=i+1;j<N;j++) {
					if(posList[i] == posList[j]) {
						signList[i] *= -1;
						signList[j] *= -1;
					}
				}
			}
			
			if(fallingCnt == k - 1) {
				if(order == 2) {
					fallingCnt += 2;
					if(idList[fallenSet[0]] < idList[fallenSet[1]]) {
						idK = idList[fallenSet[0]];
					}else {
						idK = idList[fallenSet[1]];
					}
					break;
				}else if(fallenSet != null) {
					fallingCnt++;
					idK = idList[fallenSet[0]];
					break;
				}
			}else if(fallingCnt == k - 2) {
				if(order == 2) {
					fallingCnt += 2;
					if(idList[fallenSet[0]] < idList[fallenSet[1]]) {
						idK = idList[fallenSet[1]];
					}else {
						idK = idList[fallenSet[0]];
					}
					break;
				}else if(fallenSet != null) {
					fallingCnt++;
				}
			}else {
				if(order == 2)
					fallingCnt += 2;
				else if(fallenSet != null)
					fallingCnt++;
			}
		}
		return idK;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str = null;
		int testNum = 0;
		int testNumCheck = 0;
		int freq = 0;
		int next = 1;
		int N = 0;
		int L = 0;
		int k = 0;
		int[] idList = null;
		int[] posList = null;
		int[] signList = null;
		boolean testcaseStart = true;
		int order = 0;
		while((str = br.readLine()) != null) {
			if(freq == 0)
				testNum = Integer.parseInt(str);
			else {
				if(testcaseStart) {
					String[] testcase = str.split(" ");
					N = Integer.parseInt(testcase[0]);
					next += N;
					L = Integer.parseInt(testcase[1]);
					k = Integer.parseInt(testcase[2]);
					idList = new int[N];
					posList = new int[N];
					signList = new int[N];
					testcaseStart = false;
				}else {
					String[] posId = str.split(" ");
					posList[order] = Integer.parseInt(posId[0]);
					idList[order] = Integer.parseInt(posId[1]);
					if(idList[order] > 0)
						signList[order] = 1;
					else
						signList[order] = -1;
					if(order < N - 1) {
						order++;
					}else {
						order = 0;
						testNumCheck++;
						testcaseStart = true;
						bw.write(String.valueOf(findId(posList, idList, signList, N, L, k)));
						bw.newLine();
					}
				}
			}
			freq++;
			if(testNumCheck == testNum)
				break;
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
