package com.oracle.microsvctk.demo.write.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.oracle.microsvctk.demo.write.model.HeroesWrite;

// This will be AUTO IMPLEMENTED by Spring into a Bean called HeroesRepository
// CRUD refers Create, Read, Update, Delete

public interface HeroesRepositoryWrite extends JpaRepository<HeroesWrite, Long> {

}