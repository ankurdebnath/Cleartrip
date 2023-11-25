import javax.sound.midi.Soundbank;
import java.util.*;
import java.util.stream.Collectors;


public class Centre {


    String name;
    List<Timing> timings;

    Set<WorkoutTypes> workoutVariationNames;
    Set<WorkoutVariation> workoutVariationSet;



    Centre(String name){
        this.name = name;
        workoutVariationNames = new HashSet<>();
        workoutVariationSet = new HashSet<>();
        this.timings = new ArrayList<>();
    }

    Map<Timing, WorkoutVariation> workoutSlot;


    public void addCentreActivities(List<WorkoutTypes> workoutVariations){
        workoutVariationNames.addAll(workoutVariations);
    }

    public void removeWorkoutVariation(WorkoutVariation workoutVariation){
        workoutVariationNames.remove(workoutVariation);
    }



    public void setWorkoutTiming(List<Timing> centreTimings){
        for(int i=0; i < centreTimings.size(); i++){
            Timing t = centreTimings.get(i);
            if(t.startTime >  t.endTime){
                System.out.println("Invalid Timing!" + t + " Not adding");
            }else{
                this.timings.add(t);
            }
        }
    }

    public void bookSession(Customer customer, WorkoutTypes workoutType, Timing bookingTime){
        if(!workoutVariationNames.contains(workoutType)){
            System.out.println("Not a valid workout variation for this centre");
            return;
        }
        List<WorkoutVariation> workoutVariationSetForGivenType = workoutVariationSet.stream().filter(s -> s.getWorkoutType() == workoutType).toList();
        for(int i=0; i < workoutVariationSetForGivenType.size(); i++){
            WorkoutVariation wV = workoutVariationSetForGivenType.get(i);
            Timing wVTiming = wV.getWorkoutTiming();
            if(bookingTime.startTime < wVTiming.startTime || bookingTime.endTime > wVTiming.endTime){
                System.out.println("Not a valid time slot for this workout ");
                continue;
            }
            if(!wV.checkIfSeatAvailable()){
                System.out.println("No seats left for this workout ");
                continue;
            }
            System.out.println("Booked slot for " + customer +  " workout type " + workoutType + " time - " + bookingTime);
            wV.decrementSeat();
        }
    }

    public void cancelSession(Customer customer, WorkoutTypes workoutType, Timing bookingTime){
        if(!workoutVariationNames.contains(workoutType)){
            System.out.println("Not a valid workout variation for this centre");
            return;
        }
        workoutVariationSet.forEach(wV->{
            if(wV.getWorkoutType() == workoutType && wV.getWorkoutTiming().startTime == bookingTime.startTime && wV.getWorkoutTiming().endTime == bookingTime.endTime ){
                wV.incrementSeat();
                System.out.println("Session cancel Successful !");
            }
        });


    }

    public void getWorkoutActivity(){
        workoutVariationSet.forEach(wV->{
            System.out.println(" Workout " + wV.getWorkoutType() + ". Seats left - " + wV.getWorkoutSeats() + " timing - " + wV.getWorkoutTiming());
        });
    }



    public void addWorkout(WorkoutTypes workoutType, Timing timing, int seats){
        if(checkIfWorkoutCanBeAdded(workoutType, timing)){
            WorkoutVariation workoutVariation = WorkoutVariationFactory.getWorkoutVariationInstance(workoutType,timing,seats);
            workoutVariationNames.add(workoutType);
            workoutVariationSet.add(workoutVariation);
            System.out.println("Workout added for " + workoutType + " time - " + timing);
        }else{
            System.out.println("Can't add workout for the given time");
        }
    }

    public void getWorkoutSlotsAvailable(WorkoutTypes workoutType){
        workoutVariationSet.forEach(wV->{
            if(wV.getWorkoutType() == workoutType && wV.checkIfSeatAvailable()){
                System.out.println(" Workout " + wV.getWorkoutType() + " available. Seats left - " + wV.getWorkoutSeats() + " timing - " + wV.getWorkoutTiming());
            }
        });
    }

    private boolean checkIfWorkoutCanBeAdded(WorkoutTypes workoutType, Timing timing){
        if(!workoutVariationNames.contains(workoutType)){
            System.out.println("Not a valid workout variation for this centre");
            return false;
        }
        if(!timings.isEmpty() && isCentreOpenTimingsClash(timings, timing)){
            System.out.println("timing outside centre open timings");
            return false;
        }
        List<Timing> timingsForActivities = workoutVariationSet.stream().map(WorkoutVariation::getWorkoutTiming).toList();
        if(isActivityTimingsClash(timingsForActivities, timing)){
            System.out.println("Slot already booked for another activity");
            return false;
        }
        return true;
    }

    private boolean isActivityTimingsClash(List<Timing> timingList, Timing timingToAdd){
        for(int i=0; i < timingList.size(); i++){
            Timing activityTiming = timingList.get(i);
            if(
                    (timingToAdd.startTime <= activityTiming.endTime && timingToAdd.startTime >= activityTiming.startTime) ||
                            (timingToAdd.endTime >= activityTiming.startTime && timingToAdd.endTime <= activityTiming.endTime)
            ){
                return true;
            }
        }
        return false;
    }

    private boolean isCentreOpenTimingsClash(List<Timing> timingList, Timing timingToAdd){
        if(timingList.isEmpty()) return true;
        for(int i=0; i < timingList.size(); i++){
            Timing centreTiming = timingList.get(i);
            if( timingToAdd.startTime < centreTiming.startTime && timingToAdd.endTime > centreTiming.endTime ){
                return true;
            }
        }
        return false;
    }








}
