public class Weights implements WorkoutVariation{

    int workoutSeats;
    String workoutName;

    Timing workoutVariationTiming;

    WorkoutTypes workoutType;

    Weights(String name, int workoutSeats, Timing workoutVariationTiming, WorkoutTypes workoutType){
        this.workoutName = name;
        this.workoutSeats = workoutSeats;
        this.workoutVariationTiming = workoutVariationTiming;
        this.workoutType = workoutType;
    }

    @Override
    public int getWorkoutSeats() {
        return this.workoutSeats;
    }

    @Override
    public String getWorkoutName() {
        return this.workoutName;
    }

    @Override
    public Timing getWorkoutTiming() {
        return this.workoutVariationTiming;
    }

    @Override
    public boolean checkIfSeatAvailable() {
        return workoutSeats > 0;
    }

    @Override
    public void decrementSeat() {
        workoutSeats--;
    }
    @Override
    public void incrementSeat() {
        workoutSeats++;
    }

    @Override
    public WorkoutTypes getWorkoutType() {
        return workoutType;
    }
}
