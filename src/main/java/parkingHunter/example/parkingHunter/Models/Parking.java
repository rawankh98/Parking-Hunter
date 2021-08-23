package parkingHunter.example.parkingHunter.Models;

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
    private String parkingPicture;

    @ManyToOne
    private DBUser addingParking;

    @OneToMany(mappedBy = "reserveSpace")
    private  List<Reservation> reservations;

    @OneToMany(mappedBy = "addingReview")
    private  List<Review>reviews;

    @OneToMany(mappedBy = "dashbordrepo")
    private  List<Dashboard>dashboards;
    public Parking(){

    }
    public Parking( String parkingName, String region, String latitude, String longitude, String numSpaces,
                    String openingHour, String closingHour, String pricePerHour,String ownerName,DBUser addingParking) {
        this.ownerName = ownerName;
        //int availableSpaces,
        this.parkingName = parkingName;
        this.region = region;
        this.latitude = latitude;
        this.longitude = longitude;
        this.numSpaces = Integer.parseInt(numSpaces);
        this.availableSpaces = Integer.parseInt(numSpaces);
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.pricePerHour = Integer.parseInt(pricePerHour);
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

    @Override
    public String toString() {
        return "Parking{" +
                "id=" + id +
                ", parkingName='" + parkingName + '\'' +
                ", region='" + region + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", numSpaces=" + numSpaces +
                ", availableSpaces=" + availableSpaces +
                ", openingHour='" + openingHour + '\'' +
                ", closingHour='" + closingHour + '\'' +
                ", pricePerHour=" + pricePerHour +
                ", ownerName='" + ownerName + '\'' +
                ", addingParking=" + addingParking +
                ", reviews=" + reviews +
                '}';
    }
}
