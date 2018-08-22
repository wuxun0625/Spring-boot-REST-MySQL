package com.oracle.microsvctk.demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.oracle.microsvctk.demo.read.model.HeroesRead;
import com.oracle.microsvctk.demo.read.repository.HeroesRepositoryRead;
import com.oracle.microsvctk.demo.write.model.HeroesWrite;
import com.oracle.microsvctk.demo.write.repository.HeroesRepositoryWrite;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HeroesControllerTests {

	@Mock
	private HeroesRepositoryRead heroesRepositoryRead;

	@Mock
	private HeroesRepositoryWrite heroesRepositoryWrite;

	@InjectMocks
	HeroesController heroesController;

	@Before
	public void setUpRead() {
		List<HeroesRead> heroes = new ArrayList<HeroesRead>();
		HeroesRead hero1 = new HeroesRead();
		hero1.setId(1);
		hero1.setName("Superman");
		hero1.setSkill("Super power");

		HeroesRead hero2 = new HeroesRead();
		hero2.setId(2);
		hero2.setName("Batman");
		hero2.setSkill("I'm RICH!");

		heroes.add(hero1);
		heroes.add(hero2);

		// Mock get all heroes
		Mockito.when(heroesRepositoryRead.findAll()).thenReturn(heroes);
		// Mock get specific hero info
		Mockito.when(heroesRepositoryRead.findById(1L)).thenReturn(Optional.of(hero1));
	}

	@Before
	public void setUpWrite() {

		// Mock get all heroes(Just return what it passed as parameter)
		Mockito.when(heroesRepositoryWrite.save(Mockito.any(HeroesWrite.class))).thenAnswer(i -> i.getArguments()[0]);
	}

	@Test
	public void testGetHeroes() {

		System.out.println("Test get all heroes!");
		// Get heroes list
		Iterable<HeroesRead> heroes = heroesController.getHeroes();
		// Loop heroes list and verify if value is correct
		int i = 0;
		for (HeroesRead hero : heroes) {
			if (i == 0) {
				assertEquals(1, hero.getId());
				assertEquals("Superman", hero.getName());
				assertEquals("Super power", hero.getSkill());
			} else {
				assertEquals(2, hero.getId());
				assertEquals("Batman", hero.getName());
				assertEquals("I'm RICH!", hero.getSkill());
			}
			i++;
		}
	}

	@Test
	public void testGetHeroe() {

		System.out.println("Test get specific hero!");
		// Get specific hero info
		Optional<HeroesRead> hero = heroesController.getHero(1);
		// Check if specific hero info is correct
		assertEquals(1, hero.get().getId());
		assertEquals("Superman", hero.get().getName());
		assertEquals("Super power", hero.get().getSkill());
	}

	@Test
	public void testAddHeroe() {

		System.out.println("Test add new hero!");
		HeroesWrite hero = new HeroesWrite();
		hero.setId(3);
		hero.setName("Testman");
		hero.setSkill("I can do TEST!");
		// Add new hero info
		HeroesWrite returnHero = heroesController.addHero(hero);
		// Check if returned hero info is what we passed as parameter
		assertEquals(3, returnHero.getId());
		assertEquals("Testman", returnHero.getName());
		assertEquals("I can do TEST!", returnHero.getSkill());
	}

	@Test
	public void testUpdateHeroe() {

		System.out.println("Test update hero!");
		HeroesWrite hero = new HeroesWrite();
		hero.setId(3);
		hero.setName("Testman_new");
		hero.setSkill("I can do TEST_new!");
		// Update hero info
		HeroesWrite returnHero = heroesController.updateHero(3L,hero);
		// Check if returned hero info is what we passed as parameter
		assertEquals(3, returnHero.getId());
		assertEquals("Testman_new", returnHero.getName());
		assertEquals("I can do TEST_new!", returnHero.getSkill());
	}

	@Test
	public void testDeleteHeroe() {

		System.out.println("Test delete hero!");
		// Delete hero info
		String deleteResult = heroesController.deleteHero(3L);
		// Check if delete result is correct
		assertEquals("Deleted hero by id: 3", deleteResult);
	}

}
