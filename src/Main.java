import scheduling_algorithm.Schedule;
import scheduling_algorithm.non_preemptive.FCFS;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {

        Scanner s=new Scanner(System.in);
        System.out.print("스케줄링의 이름 : ");
        String schedulingName=s.nextLine();

        Schedule sc=new Schedule(schedulingName);
        sc.setSchedule(new FCFS()); // 사용할 스케줄링

        System.out.print("Process 갯수 입력: ");
        int n=s.nextInt();
        s.nextLine();
        int process[][]=new int[n][2];
        for(int i=0;i<n;i++){
            System.out.print("Process 도착시간과 작업시간 입력: ");
            StringTokenizer stt=new StringTokenizer(s.nextLine()); // 도착시간과 작업시간을 입력
            process[i][0]=Integer.parseInt(stt.nextToken());
            process[i][1]=Integer.parseInt(stt.nextToken());
        }

        double result[]=sc.getScheduling(n,process);
        System.out.println("=========================");
        System.out.println(sc.getName()+"의 스케줄링: "+sc.getSchedulingName());
        System.out.println("평균 대기시간 : "+result[0]);
        System.out.println("평균 응답시간 : "+result[1]);
        System.out.println("평균 반환시간 : "+result[2]);
        System.out.println("=========================");
    }
}