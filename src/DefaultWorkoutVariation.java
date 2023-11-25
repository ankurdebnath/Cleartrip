public class DefaultWorkoutVariation implements WorkoutVariation{
    @Override
    public int getWorkoutSeats() {
        return 0;
    }

    @Override
    public String getWorkoutName() {
        return null;
    }

    @Override
    public Timing getWorkoutTiming() {
        return null;
    }

    @Override
    public boolean checkIfSeatAvailable() {
        return false;
    }

    @Override
    public void decrementSeat() {

    }

    @Override
    public void incrementSeat() {

    }

    @Override
    public WorkoutTypes getWorkoutType() {
        return null;
    }
}
