package parkingHunter.example.parkingHunter;

import javax.persistence.*;
import java.util.List;

@Entity
public class DBUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String username;
    private String password;
    private String authority;
    private String profilePicture;
    // all details can be added here ...

    @OneToMany(mappedBy = "addingParking")
    private List<Parking> parkings;
    public DBUser(){

    }

    public DBUser(String username, String password, String authority,String profilePicture) {
        this.username = username;
        this.password = password;
        this.authority = authority;
        this.profilePicture=profilePicture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

}
