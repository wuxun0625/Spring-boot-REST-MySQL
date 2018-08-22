package com.oracle.microsvctk.demo.read.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.oracle.microsvctk.demo.read.model.HeroesRead;

// This will be AUTO IMPLEMENTED by Spring into a Bean called HeroesRepository
// CRUD refers Create, Read, Update, Delete

public interface HeroesRepositoryRead extends JpaRepository<HeroesRead, Long> {

}