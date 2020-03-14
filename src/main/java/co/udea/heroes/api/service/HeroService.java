package co.udea.heroes.api.service;

import co.udea.heroes.api.exception.BusinessException;
import co.udea.heroes.api.model.Hero;
import co.udea.heroes.api.repository.HeroRepository;
import co.udea.heroes.api.util.Messages;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroService {
    private Messages messages;
    private HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository,Messages messages) {
        this.heroRepository = heroRepository;
        this.messages=messages;
    }

    public Hero getHero(int id){
        Optional<Hero> optionalHero = heroRepository.findById(id);
        if(!optionalHero.isPresent()){
            throw new BusinessException(messages.get("exception.not_found_here.hero"));
        }
        return optionalHero.get();
    }

    public List<Hero> getHeroes() {
        return heroRepository.findAll();
    }

    public List<Hero> searchHeroes(String name){
        return heroRepository.searchHeroes(name);
    }

    public Hero addHero(Hero hero){
        Optional<Hero> optionalHero = heroRepository.findByName(hero.getName());
        if(optionalHero.isPresent()){
            throw new BusinessException(messages.get("exception.data_duplicate_name.hero"));
        }
        try{
            hero.setId(heroRepository.encontrarIdMayor()+1);
        }catch(Exception e){
            hero.setId(1);
        }

        return heroRepository.save(hero);
    }

    public Hero updateHero(Hero hero) {
        Optional<Hero> optionalHero = heroRepository.findById(hero.getId());
        if(!optionalHero.isPresent()){
            throw new BusinessException(messages.get("exception.not_found_here.hero"));
        }

        optionalHero = heroRepository.findByName(hero.getName());
        if(optionalHero.isPresent()){
            throw new BusinessException(messages.get("exception.data_duplicate_name.hero"));
        }

        return heroRepository.save(hero);
    }

    public Hero deleteHero(int id) {
        Optional<Hero> hero = heroRepository.findById(id);
        if(!hero.isPresent()){
            throw new BusinessException(messages.get("exception.not_found_here.hero"));
        }
        heroRepository.delete(hero.get());
        return hero.get();
    }
}
