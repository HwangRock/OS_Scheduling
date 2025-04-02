package scheduling_algorithm.non_preemptive;

import scheduling_algorithm.ScheduleStrategy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HRN implements ScheduleStrategy {

    String name="HRN";

    public String getName(){
        return name;
    }

    static double calculatePriority(int waitTime,int workTime){
        double value=(waitTime+workTime)/workTime;

        return value;
    }

    public double[] scheduling(int n, int[][] process){
        double matrix[]=new double[3];

        int currentTime=-1;
        int totalWait=0;
        int totalTurnaround=0;
        int waitTime[]=new int[n];
        int turnaroundTime[]=new int[n];

        //스케줄링 알고리즘을 구현
        //결과에는 평균 대기시간, 평균 응답시간, 평균 반환시간이 포함

        PriorityQueue<int[]> pq=new PriorityQueue<>(Comparator.comparingDouble(a->calculatePriority(a[0],a[1])));

        for(int i=0;i<n;i++){
            if(currentTime<process[i][0]){
                waitTime[i]=process[i][0]-currentTime;
                currentTime=process[i][1];
                turnaroundTime[i]=currentTime-process[i][0];
            }
            else{
                if(pq.isEmpty()){
                    pq.add(new int[]{currentTime-process[i][0],process[i][1]});
                }
                else{
                    while(!pq.isEmpty()){
                        int currentProcess[]=pq.poll();
                        waitTime[i]=currentProcess[0]-currentTime;
                        currentTime=currentProcess[1];
                        turnaroundTime[i]=currentTime-currentProcess[0];
                    }
                }
            }
        }

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
