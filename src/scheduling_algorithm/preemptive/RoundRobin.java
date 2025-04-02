package scheduling_algorithm.preemptive;

import scheduling_algorithm.ScheduleStrategy;

public class RoundRobin implements ScheduleStrategy {

    String name="RoundRobin";

    public String getName(){
        return name;
    }

    public double[] scheduling(int n, int[][] process){
        double matrix[]=new double[3];

        //스케줄링 알고리즘을 구현
        //결과에는 평균 대기시간, 평균 응답시간, 평균 반환시간이 포함

        int currentTime=-1;
        int totalWait=0;
        int totalTurnaround=0;
        int waitTime[]=new int[n];
        int turnaroundTime[]=new int[n];

        matrix[0]=(double)totalWait/n;
        matrix[1]=matrix[0];
        matrix[2]=(double)totalTurnaround/n;

        return matrix;
    }
}
