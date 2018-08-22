package com.oracle.microsvctk.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

import com.oracle.microsvctk.demo.read.model.HeroesRead;
import com.oracle.microsvctk.demo.read.repository.HeroesRepositoryRead;
import com.oracle.microsvctk.demo.write.model.HeroesWrite;
import com.oracle.microsvctk.demo.write.repository.HeroesRepositoryWrite;

@RestController
@RequestMapping("/heroes")
public class HeroesController {
    @Autowired
    private HeroesRepositoryWrite heroesRepositoryWrite;

    @Autowired
    private HeroesRepositoryRead heroesRepositoryRead;

    /*
     * Get all heroes
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Iterable<HeroesRead> getHeroes() {
        return heroesRepositoryRead.findAll();
    }

    /*
     * Get specific hero
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<HeroesRead> getHero(@PathVariable("id") long id) {
        return heroesRepositoryRead.findById(id);
    }

    /*
     * Add new hero, Header = Content-Type: application/json
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody HeroesWrite addHero(@RequestBody HeroesWrite hero) {
        return heroesRepositoryWrite.save(hero);
    }

    /*
     * Update hero, Header = Content-Type: application/json
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody HeroesWrite updateHero(@PathVariable("id") long id, @RequestBody HeroesWrite hero) {
        hero.setId(id);
        return heroesRepositoryWrite.save(hero);
    }

    /*
     * Delete hero
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    String deleteHero(@PathVariable("id") long id) {
        heroesRepositoryWrite.deleteById(id);
        return "Deleted hero by id: " + id;
    }
}