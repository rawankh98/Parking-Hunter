import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {

//    @Value("${tomtom.apikey}")
//    private String tomTomApiKey;

    @GetMapping("/map")
    public String homePage(Model model) {
        model.addAttribute("apikey", "ZMaJ7IeOvM2dcAAcqxNAWzSF1fL0G166");

        System.out.println("*************************************************************************");
        System.out.println("MAP");
        System.out.println("*************************************************************************");

        return "tomtom";
    }

}
