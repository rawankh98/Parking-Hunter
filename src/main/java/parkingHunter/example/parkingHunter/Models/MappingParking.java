package parkingHunter.example.parkingHunter.Models;

import javax.persistence.*;

@Entity
public class MappingParking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double lat;
    private double lon;
    private String region;
    private String parkingName;
    @ManyToOne
    private DBUser mapParking;

    public MappingParking(){

    }

    public MappingParking(String lat, String lon,String region, String parkingName, DBUser mapParking) {
        this.lat = Double.parseDouble(lat);
        this.lon = Double.parseDouble(lon);
        this.region=region;
        this.parkingName = parkingName;
        this.mapParking = mapParking;
    }



    public int getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public double getLot() {
        return lon;
    }

    public void setLot(int lot) {
        this.lon = lot;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkName(String parkingName) {
        this.parkingName = parkingName;
    }

    public DBUser getMapParking() {
        return mapParking;
    }

    public void setMapParking(DBUser mapParking) {
        this.mapParking = mapParking;
    }
}

