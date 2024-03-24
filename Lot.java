import java.util.ArrayList;
import java.util.List;

public class Lot {
    String lotID;
    List<List<Spot>> spots;
    public Lot(String lotID, int floors, int openSlots){
        this.lotID = lotID;
        spots = new ArrayList<>();
        for(int i = 0; i<floors; i++) {
            spots.add(new ArrayList<>());
            List<Spot> floorSpots = spots.get(i); //gets the list of open parking spots for current floor
            floorSpots.add(new Spot("ev"));//assign 2 designated ev spots and 1 bike spot
            floorSpots.add(new Spot("ev"));
            floorSpots.add(new Spot("bike"));

            for(int j = 3; j<openSlots; j++) { //assign the rest of the open spots to cars
                spots.get(i).add(new Spot("car"));
            }

        }
    }
    public String parkVehicle(String type, String registration, String colour) {
        Vehicle vehicle = new Vehicle(type, registration, colour);
        for(int i = 0; i<spots.size(); i++){ //loop through floors
            for(int j = 0; j<spots.get(i).size(); j++) { //loop through spots on each floor
                Spot spot = spots.get(i).get(j); //get the spot we are currently checking
                if(spot.type.equals(type) && spot.vehicle == null) { //if type matches and spot is empty
                    spot.vehicle = vehicle;
                    spot.ticketID = createTicketID(i+1, j+1);
                    return spot.ticketID;
                }
            }
        }
        System.out.println("No open spots available for given type: " + type);
        return null;
    }

    private String createTicketID(int floor, int spotNumber){
        return lotID + "_" + floor + "_" + spotNumber; //<parking lot id>_<floor no>_<slot no>
    }

    public void unPark(String ticketID) {
        int floor;
        int spotNumber;
        try { //get the floor number and spot number from the ticketID
            String[] extract = ticketID.split("_");
            floor = Integer.parseInt(extract[1])-1;
            spotNumber = Integer.parseInt(extract[2])-1;
        } catch (NumberFormatException e) {
            System.err.println("Invalid ticket format. Unable to extract floor and spot numbers.");
            return;
        }
        try {
            Spot spot = spots.get(floor).get(spotNumber); //get the spot that is being unparked
            System.out.println(spot.vehicle + " has been unparked.");
            spot.vehicle = null;
            spot.ticketID = null;
        } catch (IndexOutOfBoundsException e) { //if floor or spotnumber go out of bounds (i.e., floor 5 when there are only 2 floors)
            System.err.println("Ticket " + ticketID + " has incorrect values.");
        }
        return;
    }

    int getOpenSpotCount(String type) {
        int count = 0;
        for(List<Spot> floor: spots) {
            for (Spot spot: floor) {
                if (spot.vehicle == null && spot.type.equals(type)) {
                    count++;
                }
            }
        }
        return count;
    }

    void displayOpenSpots(String type) {
        boolean full = true;
        for(int i = 0; i<spots.size(); i++){
            for(int j = 0; j<spots.get(i).size(); j++){
                Spot spot = spots.get(i).get(j);
                if(spot.vehicle == null && spot.type.equals(type)){
                    full = false;
                    System.out.println("Floor: " + (i+1) + " | Spot: " + (j+1));
                }
            }
        }
        if(full) {
            System.out.println("There are no vacant spots.");
        }
    }

}