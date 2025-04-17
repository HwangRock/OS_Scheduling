package scheduling_algorithm.preemptive;

import scheduling_algorithm.ScheduleStrategy;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class SRTF implements ScheduleStrategy {

    String name = "SRTF";

    public String getName() {
        return name;
    }

    public double[] scheduling(int n, int[][] process) {
        double[] matrix = new double[3];

        int currentTime = 0;
        int totalWait = 0, totalTurnaround = 0, totalResponse = 0;
        int[] waitTime = new int[n];
        int[] responseTime = new int[n];
        Arrays.fill(responseTime, -1);
        int[] turnaroundTime = new int[n];

        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        int index = 0;

        while (index < n || !q.isEmpty()) {

            // 도착한 프로세스 모두 큐에 넣음
            while (index < n && process[index][0] <= currentTime) {
                q.add(new int[]{process[index][0], process[index][1], index});
                index++;
            }

            if (!q.isEmpty()) {
                int[] current = q.poll();
                int pid = current[2];

                if (responseTime[pid] == -1) {
                    responseTime[pid] = currentTime - process[pid][0];
                }

                current[1] -= 1; // 남은 시간 1초 감소
                currentTime += 1;

                // 도착한 새 프로세스 다시 추가
                while (index < n && process[index][0] <= currentTime) {
                    q.add(new int[]{process[index][0], process[index][1], index});
                    index++;
                }

                if (current[1] > 0) {
                    q.add(current); // 남은 시간 있으면 다시 넣음
                } else {
                    turnaroundTime[pid] = currentTime - process[pid][0];
                    waitTime[pid] = turnaroundTime[pid] - process[pid][1];
                }

            } else {
                // 큐가 비었는데 아직 도착 안한 프로세스가 있다면, 시간 점프
                currentTime = process[index][0];
            }
        }

        for (int i = 0; i < n; i++) {
            totalWait += waitTime[i];
            totalResponse += responseTime[i];
            totalTurnaround += turnaroundTime[i];
        }

        matrix[0] = (double) totalWait / n;
        matrix[1] = (double) totalResponse / n;
        matrix[2] = (double) totalTurnaround / n;

        return matrix;
    }
}
