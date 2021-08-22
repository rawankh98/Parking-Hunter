package parkingHunter.example.parkingHunter.Models;

import javax.persistence.*;

@Entity
public class Dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String date;
    private long totalTime;
    private double price;
    private double commulativePrice;


    private String type;
    @ManyToOne
    private Parking dashbordrepo;


    public Dashboard(){

    }

    public Dashboard(String date, long totalTime, String type,double price,double commulativePrice, Parking dashbordrepo) {
        this.date = date;
        this.totalTime = totalTime;
        this.type = type;
        this.commulativePrice=commulativePrice;
        this.price=price;
        this.dashbordrepo = dashbordrepo;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Parking getDashbordrepo() {
        return dashbordrepo;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCommulativePrice() {
        return commulativePrice;
    }

    public void setCommulativePrice(double commulativePrice) {
        this.commulativePrice = commulativePrice;
    }

    public void setDashbordrepo(Parking dashbordrepo) {
        this.dashbordrepo = dashbordrepo;
    }
}
