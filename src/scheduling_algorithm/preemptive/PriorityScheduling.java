package scheduling_algorithm.preemptive;

import scheduling_algorithm.ScheduleStrategy;

import java.util.*;

public class PriorityScheduling implements ScheduleStrategy {

    String name = "PriorityScheduling";

    public String getName() {
        return name;
    }

    public double[] scheduling(int n, int[][] process) {
        double[] matrix = new double[3];

        int[] remainingTime = new int[n];
        int[] startTime = new int[n];
        boolean[] started = new boolean[n];
        boolean[] completed = new boolean[n];

        for (int i = 0; i < n; i++) {
            remainingTime[i] = process[i][1];
            startTime[i] = -1;
        }

        int time = 0;
        int completedCount = 0;
        int lastProcess = -1;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int totalResponseTime = 0;

        while (completedCount < n) {
            int current = -1;
            int minPriority = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (process[i][0] <= time && !completed[i]) {
                    if (process[i][2] < minPriority || (process[i][2] == minPriority && remainingTime[i] < remainingTime[current])) {
                        minPriority = process[i][2];
                        current = i;
                    }
                }
            }

            if (current == -1) {
                time++;
                continue;
            }

            if (!started[current]) {
                startTime[current] = time;
                started[current] = true;
                totalResponseTime += time - process[current][0];
            }

            remainingTime[current]--;
            time++;

            if (remainingTime[current] == 0) {
                completed[current] = true;
                completedCount++;

                int turnaround = time - process[current][0];
                int waiting = turnaround - process[current][1];

                totalTurnaroundTime += turnaround;
                totalWaitingTime += waiting;
            }
        }

        matrix[0] = (double) totalWaitingTime / n;
        matrix[1] = (double) totalResponseTime / n;
        matrix[2] = (double) totalTurnaroundTime / n;

        return matrix;
    }
}
