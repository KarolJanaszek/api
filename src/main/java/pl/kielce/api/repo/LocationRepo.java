package pl.kielce.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kielce.api.model.Location;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepo extends JpaRepository<Location, Long> {
	Optional<Location> findByWoeid(Integer woeid);
	List<Location> findAllByFavoriteTrue();
}
