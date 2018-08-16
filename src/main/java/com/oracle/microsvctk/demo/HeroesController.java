package com.oracle.microsvctk.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

import com.oracle.microsvctk.demo.Heroes;
import com.oracle.microsvctk.demo.HeroesRepository;

@RestController
@RequestMapping("/heroes")
public class HeroesController {
    @Autowired
    private HeroesRepository heroesRepository;

    /*
     * Get all heroes
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Iterable<Heroes> getHeroes() {
        return heroesRepository.findAll();
    }

    /*
     * Get specific hero
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Heroes> getHero(@PathVariable("id") long id) {
        return heroesRepository.findById(id);
    }

    /*
     * Add new hero, Header = Content-Type: application/json
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Heroes addHero(@RequestBody Heroes hero) {
        return heroesRepository.save(hero);
    }

    /*
     * Update hero, Header = Content-Type: application/json
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody Heroes updateHero(@PathVariable("id") long id, @RequestBody Heroes hero) {
        hero.setId(id);
        return heroesRepository.save(hero);
    }

    /*
     * Delete hero
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    String deleteHero(@PathVariable("id") long id) {
        heroesRepository.deleteById(id);
        return "Deleted hero by id: " + id;
    }
}