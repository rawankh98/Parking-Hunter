package parkingHunter.example.parkingHunter.Models;

import javax.persistence.*;

@Entity
public class MappingParking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    private double lat;
//    private double lon;
    private  double lonlat [] ;
    private String region;
    private String parkingName;
    @ManyToOne
    private DBUser mapParking;

    public MappingParking(){

    }

    public MappingParking(double[] lonlat, String region, String parkingName, DBUser mapParking) {
        this.lonlat = lonlat;
        this.region = region;
        this.parkingName = parkingName;
        this.mapParking = mapParking;
    }

    public int getId() {
        return id;
    }

    public double[] getLonlat() {
        return lonlat;
    }

    public void setLonlat(double[] lonlat) {
        this.lonlat = lonlat;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public DBUser getMapParking() {
        return mapParking;
    }

    public void setMapParking(DBUser mapParking) {
        this.mapParking = mapParking;
    }
}

