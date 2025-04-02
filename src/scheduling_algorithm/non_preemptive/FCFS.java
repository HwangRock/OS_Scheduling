package scheduling_algorithm.non_preemptive;


import scheduling_algorithm.ScheduleStrategy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FCFS implements ScheduleStrategy {
    String name="FCFS";

    public String getName(){
        return name;
    }

    public double[] scheduling(int n, int[][] process) {
        double matrix[]=new double[3];

        //스케줄링 알고리즘을 구현
        //결과에는 평균 대기시간, 평균 응답시간, 평균 반환시간이 포함

        PriorityQueue<int[]> q=new PriorityQueue<>(Comparator.comparingInt(a->a[0]));
        for(int i=0;i<n;i++){
            q.add(new int[]{process[i][0],process[i][1]});
        }

        int waitTime[]=new int[n];
        int turnaroundTime[]=new int[n];

        int idx=0;
        int currentTime=0;
        while(!q.isEmpty()){
            int curProcess[]=q.poll();

            currentTime=Math.max(currentTime,curProcess[0]);

            waitTime[idx]=currentTime-curProcess[0]; //대기시간 계산
            currentTime+=curProcess[1]; // 시작시간 증가
            turnaroundTime[idx]=currentTime-curProcess[0]; // 응답시간 계산

            idx++;
        }

        int totalWait=0;
        int totalTurnaround=0;
        for(int i=0;i<n;i++){
            totalWait+=waitTime[i];
            totalTurnaround+=turnaroundTime[i];
        }

        matrix[0]=(double)totalWait/n;
        matrix[1]=matrix[0];
        matrix[2]=(double)totalTurnaround/n;

        return matrix;
    }
}
