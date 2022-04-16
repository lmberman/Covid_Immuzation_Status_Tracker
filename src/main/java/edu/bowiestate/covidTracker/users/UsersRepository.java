package edu.bowiestate.covidTracker.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByFirstnameAndLastname(String firstname, String lastname);

    @Query(nativeQuery = true,
            value="SELECT u.FIRSTNAME,u.LASTNAME,u.ADDRESS,u.ADDRESS2," +
                    "u.CITY,u.STATE, u.ZIP, u.PHONE,u.EMAIL, vs.VACCINATED " +
                    "FROM USERS u " +
                    "INNER JOIN VACCINATION_STATUS vs " +
                    "ON vs.USER_ID = u.ID " +
                    "WHERE u.firstname = :firstname " +
                    "and u.lastname = :lastname ")
    List<UsersOutput> findByUserVaccinationStatus(@Param("firstname") String firstname,@Param("lastname") String lastname);

}
