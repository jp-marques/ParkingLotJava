public class ParkingLotDemo {
    public static void main(String[] args) {
        int nfloors = 4;
        int nSpotsPerFloor = 6;
        Lot lot = new Lot("Lot01", nfloors, nSpotsPerFloor);
        lot.getOpenSpotCount("cars");
        
    }
}
