package edu.bowiestate.covidTracker.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByFirstnameAndLastname(String firstname, String lastname);
}
