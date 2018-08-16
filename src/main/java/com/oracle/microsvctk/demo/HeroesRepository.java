package com.oracle.microsvctk.demo;

import org.springframework.data.repository.CrudRepository;
import com.oracle.microsvctk.demo.Heroes;

// This will be AUTO IMPLEMENTED by Spring into a Bean called HeroesRepository
// CRUD refers Create, Read, Update, Delete

public interface HeroesRepository extends CrudRepository<Heroes, Long> {

}