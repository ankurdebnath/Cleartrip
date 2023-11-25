


public class WorkoutVariationFactory {

    static WorkoutVariation getWorkoutVariationInstance(WorkoutTypes workoutType, Timing timing, int seats){
        if(workoutType == WorkoutTypes.SWIMMING){
            return new Swimming(workoutType.name(), seats, timing,workoutType);
        }
        return new DefaultWorkoutVariation();
    }

}
