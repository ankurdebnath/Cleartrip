import java.util.List;

public class Driver {

    public static void main(String[] args) {
        AdminController adminController = new AdminController();

        Customer c1 = new Customer("Vaibhav");

        adminController.addCentre("Koramangla");

        adminController.registerCustomer(c1);

        adminController.addCentreTimings("Koramangla", List.of(
                new Timing(6,9),
                new Timing(18,21)
        ));

        adminController.addCentreActivities("Koramangla", List.of(WorkoutTypes.SWIMMING, WorkoutTypes.WEIGHTS));

        adminController.addWorkout("Koramangla", WorkoutTypes.SWIMMING, new Timing(6,9),100 );
        adminController.addWorkout("Koramangla", WorkoutTypes.WEIGHTS, new Timing(18,21),100 );

        adminController.bookSession("Koramangla", c1, WorkoutTypes.SWIMMING, new Timing(7,8));

        adminController.bookSession("Koramangla", c1, WorkoutTypes.WEIGHTS, new Timing(19,20));

        adminController.viewWorkoutActivity(WorkoutTypes.SWIMMING);
        adminController.viewWorkoutActivity(WorkoutTypes.WEIGHTS);


    }
}
