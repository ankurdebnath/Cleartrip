import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdminController {

    HashMap<String, Centre> centres;

    AdminController(){

        centres = new HashMap<>();
        customers = new HashSet<>();
    }

    Set<Customer> customers;

    public void registerCustomer(Customer customer){
        customers.add(customer);
    }

    public void removeCustomer(Customer customer){
        customers.remove(customer);
    }

    public void addCentre(String centreName){
        centres.put(centreName, new Centre(centreName));
    }

    public void addCentreActivities(String centreName, List<WorkoutTypes> workoutVariations){
        if(centres.containsKey(centreName)){
            centres.get(centreName).addCentreActivities(workoutVariations);
        }
    }

    public void addCentreTimings(String centreName, List<Timing> centreTimings){
        if(centres.containsKey(centreName)){
            centres.get(centreName).setWorkoutTiming(centreTimings);
        }
    }

    public void addWorkout(String centreName, WorkoutTypes workoutType, Timing timing, int seats){
        if(centres.containsKey(centreName)){
            centres.get(centreName).addWorkout(workoutType, timing, seats);
        }
    }

    public void viewWorkoutActivity(WorkoutTypes workoutType){
        for(String centre : centres.keySet()){
            System.out.println("For centre : " + centre);
            centres.get(centre).getWorkoutActivity();
        }
    }

    public void bookSession(String centreName, Customer customer, WorkoutTypes workoutType, Timing bookingTime){
        if(centres.containsKey(centreName)){
            centres.get(centreName).bookSession(customer, workoutType, bookingTime);
        }
    }

    public void cancelSession(String centreName, Customer customer, WorkoutTypes workoutType, Timing bookingTime){
        if(centres.containsKey(centreName)){
            centres.get(centreName).cancelSession(customer, workoutType, bookingTime);
        }
    }

}
