package co.udea.heroes.api.controler;

import co.udea.heroes.api.model.Hero;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heroes")
public class HeroController {
    @GetMapping
    public Hero getHero(int id){
        return new Hero(1,"Dead pool");
    }
}
