package parkingHunter.example.parkingHunter.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import parkingHunter.example.parkingHunter.Models.DBUser;
import parkingHunter.example.parkingHunter.Models.MappingParking;
import parkingHunter.example.parkingHunter.Models.NotUser;
import parkingHunter.example.parkingHunter.Repos.DBUserRepository;
import parkingHunter.example.parkingHunter.Models.Parking;
import parkingHunter.example.parkingHunter.Repos.MappingParkingRepositoriy;
import parkingHunter.example.parkingHunter.Repos.ParkingRepository;

import java.security.Principal;

@Controller
public class AddParkingController {

    @Autowired
    DBUserRepository DBUserRepository;
    @Autowired
    ParkingRepository parkingRepository;
    @Autowired
    MappingParkingRepositoriy mappingParkingRepositoriy;
    @GetMapping("/addparking")
    public String showAddparkingForm(Principal principal, Model model){
        if (principal != null) {
            model.addAttribute("userType", "Guest");
            model.addAttribute("user", DBUserRepository.findByUsername(principal.getName()));

        }else {
            NotUser user = new NotUser("Guest");
            model.addAttribute("user", user);
        }
        return "addParkingForm";
    }
    @PostMapping("/addparking")
    public RedirectView addParkingFromForm(
            @RequestParam(value = "parkingName") String parkingName,
            @RequestParam(value = "region") String region,
            @RequestParam(value = "numSpaces") String numSpaces,
            @RequestParam(value = "openingHour") String openingHour,
            @RequestParam(value = "closingHour") String closingHour,
            @RequestParam(value = "pricePerHour") String pricePerHour,
            @RequestParam(value = "lat") String lat,
            @RequestParam(value = "lon") String lon, Principal principal){
        String newUser= DBUserRepository.findByUsername(principal.getName()).getUsername();
        DBUser user= DBUserRepository.findByUsername(principal.getName());
        String url="https://www.google.com/maps/place?q="+lon+"+"+lat;
        Parking newParking=new Parking(parkingName,region,lat,lon,numSpaces,openingHour,closingHour,pricePerHour,
                newUser,url,user);

        parkingRepository.save(newParking);
        double lons=Double.parseDouble(lon);
        double lats=Double.parseDouble(lat);
        double [] lonlat=new double[2];
        lonlat[0]=lons;
        lonlat[1]=lats;


        MappingParking mappingParking=new MappingParking(lonlat,region,parkingName,user);
        mappingParkingRepositoriy.save(mappingParking);

        return new RedirectView("/");
    }
}
