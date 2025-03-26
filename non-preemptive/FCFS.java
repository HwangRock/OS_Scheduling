import java.io.*;
import java.util.*;

public class Main {
    static double[] scheduling(int n,int process[][]) {
        double matrix[]=new double[3];

        //스케줄링 알고리즘을 구현
        //결과에는 평균 대기시간, 평균 응답시간, 평균 반환시간이 포함

        PriorityQueue<int[]>q=new PriorityQueue<>(Comparator.comparingInt(a->a[0]));
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        System.out.print("Process 갯수 입력: ");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int process[][]=new int[n][2];
        for(int i=0;i<n;i++){
            System.out.print("Process 도착시간과 작업시간 입력: ");
            StringTokenizer stt=new StringTokenizer(br.readLine()); // 도착시간과 작업시간을 입력
            process[i][0]=Integer.parseInt(stt.nextToken());
            process[i][1]=Integer.parseInt(stt.nextToken());
        }

        double result[]=scheduling(n,process);
        bw.write("평균 대기시간 : "+result[0]+"\n");
        bw.write("평균 응답시간 : "+result[1]+"\n");
        bw.write("평균 반환시간 : "+result[2]);

        bw.flush();
        bw.close();
    }
}
