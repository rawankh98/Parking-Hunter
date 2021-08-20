package parkingHunter.example.parkingHunter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String parkingName;
    private String region;
    private String latitude;
    private String longitude;
    private int numSpaces;
    private int availableSpaces;
    private String openingHour;
    private String closingHour;
    private int pricePerHour;
    private String ownerName;
    @ManyToOne
    private DBUser addingParking;

    @OneToMany(mappedBy = "addingReview")
    private  List<Review>reviews;

    @OneToMany(mappedBy = "dashbordrepo")
    private  List<Dashboard>dashboards;
    public Parking(){

    }
    public Parking( String parkingName, String region, String latitude, String longitude, int numSpaces, int availableSpaces, String openingHour, String closingHour, int pricePerHour,String ownerName,DBUser addingParking) {
        this.ownerName = ownerName;
        this.parkingName = parkingName;
        this.region = region;
        this.latitude = latitude;
        this.longitude = longitude;
        this.numSpaces = numSpaces;
        this.availableSpaces = availableSpaces;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.pricePerHour = pricePerHour;
        this.addingParking=addingParking;
    }

    public int getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getNumSpaces() {
        return numSpaces;
    }

    public void setNumSpaces(int numSpaces) {
        this.numSpaces = numSpaces;
    }

    public int getAvailableSpaces() {
        return availableSpaces;
    }

    public void setAvailableSpaces(int availableSpaces) {
        this.availableSpaces = availableSpaces;
    }

    public String getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(String openingHour) {
        this.openingHour = openingHour;
    }

    public String getClosingHour() {
        return closingHour;
    }

    public void setClosingHour(String closingHour) {
        this.closingHour = closingHour;
    }

    public int getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public DBUser getAddingParking() {
        return addingParking;
    }

    public void setAddingParking(DBUser addingParking) {
        this.addingParking = addingParking;
    }
}
