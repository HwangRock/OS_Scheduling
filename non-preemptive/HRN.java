import java.io.*;
import java.util.*;

public class Main {
    static int[] scheduling(int n,int process[][]) {
        int matrix[]=new int[3];

        //스케줄링 알고리즘을 구현
        //결과에는 평균 대기시간, 평균 응답시간, 평균 반환시간이 포함

        return matrix;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int process[][]=new int[n][2];
        for(int i=0;i<n;i++){
            StringTokenizer stt=new StringTokenizer(br.readLine()); // 도착시간과 작업시간을 입력
            process[i][0]=Integer.parseInt(stt.nextToken());
            process[i][1]=Integer.parseInt(stt.nextToken());
        }

        int result[]=scheduling(n,process);
        bw.write("평균 대기시간 : "+result[0]+"\n");
        bw.write("평균 응답시간 : "+result[1]+"\n");
        bw.write("평균 반환시간 : "+result[2]);

        bw.flush();
        bw.close();
    }
}
