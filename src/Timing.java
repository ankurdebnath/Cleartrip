




public class Timing {
    int startTime;
    int endTime;

    Timing(int startTime, int endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Start time + " + this.startTime + " - End Time - " + this.endTime;
    }
}
