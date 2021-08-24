package parkingHunter.example.parkingHunter.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import parkingHunter.example.parkingHunter.Models.Parking;
import parkingHunter.example.parkingHunter.Repos.MappingParkingRepositoriy;
import parkingHunter.example.parkingHunter.Repos.ParkingRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MapController {
@Autowired
    MappingParkingRepositoriy mappingParkingRepositoriy;
        //    @Value("${tomtom.apikey}")
//    private String tomTomApiKey;

    @Autowired
    ParkingRepository parkingRepository;

    @GetMapping("/map")
    public String homePage(Model model) {
//        model.addAttribute("apikey",
//                "https://api.tomtom.com/map/1/tile/basic/main/0/0/0.png?view=Unified&key=ZMaJ7IeOvM2dcAAcqxNAWzSF1fL0G166");
////        model.addAttribute("coolLocations", coolLocations());
//        Iterable Mappp=mappingParkingRepositoriy.findAll();
//        model.addAttribute("coolLocations", Mappp);
        System.out.println("*************************************************************************");
        System.out.println("MAP");
        System.out.println("*************************************************************************");

        Iterable parkings= parkingRepository.findAll();
        model.addAttribute("parkingsOwner",parkings);
        model.addAttribute("coolLocations", coolLocations());

        return "tomtom";
    }
    private List<Location> coolLocations() {

        List<Parking> parkings= (List<Parking>) parkingRepository.findAll();
       List<Location> all = new ArrayList<>();
       double lonLat[] = new double[2];
       String name="";

        for (int i = 0; i < parkings.size(); i++) {
            lonLat[0]=Double.parseDouble(parkings.get(i).getLatitude());
            lonLat[1]=Double.parseDouble(parkings.get(i).getLongitude());
            name=parkings.get(i).getParkingName();

           all.add(new Location( lonLat, name)) ;
        }
        all.add(new Location(new double[]{35.93448795290056, 31.94958622949373}, "Husseini"));
        return all;
//
//        return List.of(
//                new Location(new double[]{-121.901481, 36.618253}, "Monterey Bay Aquarium"),
//                new Location(new double[]{21.006010, 52.231606}, "Palace of Culture and Science"),
//                new Location(new double[]{2.337612, 48.860717}, "Louvre Museum")
//        );

    }

    static class Location {
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
