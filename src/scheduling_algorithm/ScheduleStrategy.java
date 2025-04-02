package scheduling_algorithm;

public interface ScheduleStrategy {
    public double[] scheduling(int n,int process[][]);
    public String getName();
}
