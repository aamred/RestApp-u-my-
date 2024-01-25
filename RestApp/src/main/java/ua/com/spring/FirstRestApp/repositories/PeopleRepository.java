package ua.com.spring.FirstRestApp.repositories;

import org.springframework.stereotype.Repository;

import ua.com.spring.FirstRestApp.models.Person;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Anton Muzhytskyi
 */

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer>{
}
