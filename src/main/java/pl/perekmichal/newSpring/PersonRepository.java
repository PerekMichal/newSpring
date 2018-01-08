package pl.perekmichal.newSpring;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.perekmichal.newSpring.models.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
