package parkingHunter.example.parkingHunter.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import parkingHunter.example.parkingHunter.Models.NotUser;
import parkingHunter.example.parkingHunter.Models.Parking;
import parkingHunter.example.parkingHunter.Repos.DBUserRepository;
import parkingHunter.example.parkingHunter.Repos.ParkingRepository;
import parkingHunter.example.parkingHunter.Repos.ReservationRepository;
import parkingHunter.example.parkingHunter.Repos.ReviewRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class UserController {
    @Autowired
    DBUserRepository dbUserRepository;
    @Autowired
    ParkingRepository parkingRepository;
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @GetMapping("/userReservation/{id}")
    public String userReservation(Model model){

        return "userBookingForm";
    }
    @GetMapping("/userShowParking")
    public String userShowParking(Principal principal, Model model) {
        if (principal == null) {

            Iterable parking = parkingRepository.findAll();
            model.addAttribute("parkings", parking);

            Iterable parkings = parkingRepository.findAll();
            model.addAttribute("parkingsOwner", parkings);
            model.addAttribute("coolLocations", coolLocations());
        } else {
            String userType = dbUserRepository.findByUsername(principal.getName()).getAuthority();
            model.addAttribute("userType", userType);
            model.addAttribute("user", dbUserRepository.findByUsername(principal.getName()));
            Iterable parking = parkingRepository.findAll();
            model.addAttribute("parkings", parking);
            Iterable parkings = parkingRepository.findAll();
            model.addAttribute("parkingsOwner", parkings);
            model.addAttribute("coolLocations", coolLocations());


        }

        return "userShowDetails";
    }

    @GetMapping("/userShowParking/{id}")
    public String detailsOfParking(@PathVariable Integer id, Principal principal, Model model) {
        if (principal != null) {
            String userType = dbUserRepository.findByUsername(principal.getName()).getAuthority();
            model.addAttribute("userType", userType);
            model.addAttribute("user", dbUserRepository.findByUsername(principal.getName()));

            Parking parking = parkingRepository.findById(id).get();
            model.addAttribute("parking", parking);
            Iterable addingReviewId = reviewRepository.findByaddingReviewId(id);
            model.addAttribute("reviewsByBarkingId", addingReviewId);


            List<Location> all = coolLocations();
            List<Location> oneLocation = new ArrayList<>();
            for (Location location : all) {
                if (String.valueOf(parking.getLatitude()).equals(String.valueOf(location.getLnglat()[0])) &&
                        String.valueOf(parking.getLongitude()).equals(String.valueOf(location.getLnglat()[1]))) {
                    oneLocation.add(location);
                }
            }
            model.addAttribute("parkingsOwner", parking);
            model.addAttribute("coolLocations", oneLocation);

            Iterable res = reservationRepository.findByUserName(principal.getName());

            model.addAttribute("res",res);


        } else {
            NotUser user=new NotUser("Guest");

            model.addAttribute("user",user);

            model.addAttribute("userType", "notUser");
            Parking parking = parkingRepository.findById(id).get();
            model.addAttribute("parking", parking);
            Iterable addingReviewId = reviewRepository.findByaddingReviewId(id);
            model.addAttribute("reviewsByBarkingId", addingReviewId);


            List<Location> all = coolLocations();
            List<Location> oneLocation = new ArrayList<>();
            for (Location location : all) {
                if (String.valueOf(parking.getLatitude()).equals(String.valueOf(location.getLnglat()[0])) &&
                        String.valueOf(parking.getLongitude()).equals(String.valueOf(location.getLnglat()[1]))) {
                    oneLocation.add(location);
                }
            }
            model.addAttribute("parkingsOwner", parking);
            model.addAttribute("coolLocations", oneLocation);
        }



        return "detailsOfParking";
    }


    private List<Location> coolLocations() {

        List<Parking> parkings = (List<Parking>) parkingRepository.findAll();
        List<Location> all = new ArrayList<>();
        String name = "";

        for (int i = 0; i < parkings.size(); i++) {
            double lonLat[] = new double[2];
            lonLat[0] = Double.parseDouble(parkings.get(i).getLatitude());
            lonLat[1] = Double.parseDouble(parkings.get(i).getLongitude());
            name = parkings.get(i).getParkingName();


            all.add(new Location(lonLat, name));
        }

        return all;
    }

    private static class Location {
        private final double[] lnglat;
        private final String description;

        public Location(double[] lnglat, String description) {
            this.lnglat = lnglat;
            this.description = description;
        }

        public double[] getLnglat() {
            return lnglat;
        }

        public String getDescription() {
            return description;
        }
    }
}
