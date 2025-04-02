package scheduling_algorithm;

public class Schedule {
    private ScheduleStrategy scheduleStrategy;
    private String name;

    public Schedule(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }

    public String getSchedulingName(){
        return scheduleStrategy.getName();
    }
    public void setSchedule(ScheduleStrategy scheduleStrategy){
        this.scheduleStrategy=scheduleStrategy;
    }
    public double[] getScheduling(int n,int process[][]){
        return scheduleStrategy.scheduling(n,process);
    }

}
