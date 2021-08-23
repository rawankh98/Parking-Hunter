//package parkingHunter.example.parkingHunter.Controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import parkingHunter.example.parkingHunter.Repos.MappingParkingRepositoriy;
//
//import java.util.List;
//
//@Controller
//public class MapController {
//@Autowired
//    MappingParkingRepositoriy mappingParkingRepositoriy;
//        //    @Value("${tomtom.apikey}")
////    private String tomTomApiKey;
//
//    @GetMapping("/map")
//    public String homePage(Model model) {
////        model.addAttribute("apikey",
////                "https://api.tomtom.com/map/1/tile/basic/main/0/0/0.png?view=Unified&key=ZMaJ7IeOvM2dcAAcqxNAWzSF1fL0G166");
//////        model.addAttribute("coolLocations", coolLocations());
////        Iterable Mappp=mappingParkingRepositoriy.findAll();
////        model.addAttribute("coolLocations", Mappp);
//        System.out.println("*************************************************************************");
//        System.out.println("MAP");
//        System.out.println("*************************************************************************");
//
//        return "tomtom";
//    }
//    private List<Location> coolLocations() {
//        return List.of(
//
//                new Location(new double[]{35.93448795290056, 31.94958622949373}, "Monterey Bay Aquarium")
//
//        );
//    }
//
//    private static class Location {
//        private final double[] lnglat;
//        private final String description;
//
//        public Location(double[] lnglat, String description) {
//            this.lnglat = lnglat;
//            this.description = description;
//        }
//
//        public double[] getLnglat() {
//            return lnglat;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//    }
//
//}
