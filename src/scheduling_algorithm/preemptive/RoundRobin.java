package scheduling_algorithm.preemptive;

import scheduling_algorithm.ScheduleStrategy;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class RoundRobin implements ScheduleStrategy {

    String name="RoundRobin";

    public String getName(){
        return name;
    }

    public double[] scheduling(int n, int[][] process){ // 프로세스에는 도착순으로 들어있다고 가정
        double matrix[]=new double[3];

        //스케줄링 알고리즘을 구현
        //결과에는 평균 대기시간, 평균 응답시간, 평균 반환시간이 포함

        int currentTime=0;
        int totalWait=0;
        int totalTurnaround=0;
        int totalResponse=0;
        int waitTime[]=new int[n];
        int responseTime[]=new int[n];
        Arrays.fill(responseTime,-1);
        int turnaroundTime[]=new int[n];
        int timeSlice=5; // 타임 슬라이스 지정 (임의로)

        Queue<int[]> q=new ArrayDeque<>();
        for(int i=0;i<n;i++){
            if(process[i][0]>=currentTime){ //현재 프로세스가 레디 큐에 들어감
                q.add(new int[]{process[i][0],process[i][1],i});
            }
            while(!q.isEmpty() && (i>=n || currentTime<process[i][0])){
                int current[]=q.poll();

                if(responseTime[current[2]]==-1){
                    responseTime[current[2]]=currentTime-current[0]; // 응답시간 정의
                }

                if(current[1]<=timeSlice){ // 작업 완료
                    turnaroundTime[current[2]]=currentTime-current[0];
                    waitTime[current[2]]=turnaroundTime[current[2]]-current[1];
                }
                else{ // 완료되지 않았으므로 큐에 다시 넣음
                    q.add(new int[]{current[0],current[1]-timeSlice,current[2]});
                }
                currentTime+=Math.min(timeSlice,current[1]);
                while(i<n && currentTime>process[i][0]){
                    q.add(new int[]{process[i][0],process[i][1],i});
                    i++;
                }
            }
        }

        for(int i=0;i<n;i++){
            totalWait+=waitTime[i];
            totalResponse+=responseTime[i];
            totalTurnaround+=turnaroundTime[i];
        }

        matrix[0]=(double)totalWait/n;
        matrix[1]=(double)totalResponse/n;
        matrix[2]=(double)totalTurnaround/n;

        return matrix;
    }
}
