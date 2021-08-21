package parkingHunter.example.parkingHunter.Models;

import javax.persistence.*;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String date;
    private String starTime;
    private String endTime;

// ManyToMany!!!
    @ManyToOne
    private Parking reserveSpace;

    public Reservation(){

    }

    public int getId() {
        return id;
    }

    public Reservation(String userName,String date, String starTime, String endTime, Parking reserveSpace) {
        this.userName = userName;
        this.date = date;
        this.starTime = starTime;
        this.endTime = endTime;
        this.reserveSpace = reserveSpace;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStarTime() {
        return starTime;
    }

    public void setStarTime(String starTime) {
        this.starTime = starTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Parking getReserveSpace() {
        return reserveSpace;
    }

    public void setReserveSpace(Parking reserveSpace) {
        this.reserveSpace = reserveSpace;
    }
}
