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

    @GetMapping("/userReservation/{id}")
    public String userReservation(Model model){

        return "userBookingForm";
    }
    @GetMapping("/userShowParking")
    public String userShowParking(Principal principal, Model model) {
        if (principal == null) {

            Iterable parking = parkingRepository.findAll();
            System.out.println(parking);
            model.addAttribute("parkings", parking);
            System.out.println(parking);

            Iterable parkings = parkingRepository.findAll();
            model.addAttribute("parkingsOwner", parkings);
            model.addAttribute("coolLocations", coolLocations());
        } else {
            String userType = dbUserRepository.findByUsername(principal.getName()).getAuthority();
            model.addAttribute("userType", userType);
            model.addAttribute("user", dbUserRepository.findByUsername(principal.getName()));
            Iterable parking = parkingRepository.findAll();
            System.out.println(parking);
            model.addAttribute("parkings", parking);
            System.out.println(parking);
            Iterable parkings = parkingRepository.findAll();
            model.addAttribute("parkingsOwner", parkings);
            model.addAttribute("coolLocations", coolLocations());


        }



//            Iterable addingReviewId=reviewRepository.findAll();
//            model.addAttribute("review",addingReviewId);
//
//            Iterable reservations= reservationRepository.findAll();
//
//
//            Iterable oneReservations= reservationRepository.findByUserName(principal.getName());
//
//            model.addAttribute("allReservations",reservations);
//            model.addAttribute("oneReservation",oneReservations);


        return "userShowDetails";
    }

    @GetMapping("/userShowParking/{id}")
    public String detailsOfParking(@PathVariable Integer id, Principal principal, Model model) {
        if (principal != null) {
            String userType = dbUserRepository.findByUsername(principal.getName()).getAuthority();
            model.addAttribute("userType", userType);
            model.addAttribute("user", dbUserRepository.findByUsername(principal.getName()));
//            model.addAttribute("parkingsOwner", parkingRepository.findAllByAddingParking(dbUserRepository
//            .findByUsername(principal.getName())));
            Parking parking = parkingRepository.findById(id).get();
            model.addAttribute("parking", parking);
            Iterable addingReviewId = reviewRepository.findByaddingReviewId(id);
            System.out.println(addingReviewId);
            model.addAttribute("reviewsByBarkingId", addingReviewId);


            List<MapController.Location> all = coolLocations();
            List<MapController.Location> oneLocation = new ArrayList<>();
            for (MapController.Location location : all) {
                if (String.valueOf(parking.getLatitude()).equals(String.valueOf(location.getLnglat()[0])) &&
                        String.valueOf(parking.getLongitude()).equals(String.valueOf(location.getLnglat()[1]))) {
                    oneLocation.add(location);
                }
                System.out.println(oneLocation);
            }
            model.addAttribute("parkingsOwner", parking);
            model.addAttribute("coolLocations", oneLocation);


        } else {
            NotUser user=new NotUser("Guest");

            model.addAttribute("user",user);

            model.addAttribute("userType", "notUser");
            Parking parking = parkingRepository.findById(id).get();
            model.addAttribute("parking", parking);
            Iterable addingReviewId = reviewRepository.findByaddingReviewId(id);
            System.out.println(addingReviewId);
            model.addAttribute("reviewsByBarkingId", addingReviewId);


            List<MapController.Location> all = coolLocations();
            List<MapController.Location> oneLocation = new ArrayList<>();
            for (MapController.Location location : all) {
                if (String.valueOf(parking.getLatitude()).equals(String.valueOf(location.getLnglat()[0])) &&
                        String.valueOf(parking.getLongitude()).equals(String.valueOf(location.getLnglat()[1]))) {
                    oneLocation.add(location);
                }
                System.out.println(oneLocation);
            }
            model.addAttribute("parkingsOwner", parking);
            model.addAttribute("coolLocations", oneLocation);
        }
        return "detailsOfParking";
    }


    private List<MapController.Location> coolLocations() {

        List<Parking> parkings = (List<Parking>) parkingRepository.findAll();
        List<MapController.Location> all = new ArrayList<>();
        double lonLat[] = new double[2];
        String name = "";

        for (int i = 0; i < parkings.size(); i++) {
            lonLat[0] = Double.parseDouble(parkings.get(i).getLatitude());
            lonLat[1] = Double.parseDouble(parkings.get(i).getLongitude());
            name = parkings.get(i).getParkingName();

            all.add(new MapController.Location(lonLat, name));
        }
        all.add(new MapController.Location(new double[]{35.93448795290056, 31.94958622949373}, "Husseini"));
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
