public class Spot {
    String type;
    Vehicle vehicle;
    String ticketID;
    public Spot(String type) { //spots are empty when initialized, so vehicle and ticket is null
        this.type = type;
        this.vehicle = null;
        this.ticketID = null;
    }   
}
