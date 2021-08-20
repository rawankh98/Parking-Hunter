package parkingHunter.example.parkingHunter;

import javax.persistence.*;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String date;
    private String starTime;
    private String endTime;

    @ManyToOne
    private Parking reserveSpace;

    public int getId() {
        return id;
    }

    public Reservation(String date, String starTime, String endTime, Parking reserveSpace) {
        this.date = date;
        this.starTime = starTime;
        this.endTime = endTime;
        this.reserveSpace = reserveSpace;
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
