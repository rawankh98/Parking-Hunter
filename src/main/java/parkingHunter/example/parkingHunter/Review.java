package parkingHunter.example.parkingHunter;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String body;
    private String dateTime;
    private int stars;
    private String parkName;

    @ManyToOne
    private Parking addingReview;

    public Review(){

    }
    public Review(String userName, String body, String dateTime, int stars, String parkName,Parking addingReview) {
        this.userName = userName;
        this.body = body;
        this.dateTime = dateTime;
        this.stars = stars;
        this.parkName = parkName;
        this.addingReview=addingReview;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public Parking getAddingReview() {
        return addingReview;
    }

    public void setAddingReview(Parking addingReview) {
        this.addingReview = addingReview;
    }
}
