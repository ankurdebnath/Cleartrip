





public interface WorkoutVariation {

    int getWorkoutSeats();
    String getWorkoutName();

    Timing getWorkoutTiming();

    boolean checkIfSeatAvailable();

    void decrementSeat();

    void incrementSeat();

    WorkoutTypes getWorkoutType();

}
