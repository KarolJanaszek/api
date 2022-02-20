package pl.kielce.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kielce.api.model.ConsolidatedWeather;

import java.util.Optional;

@Repository
public interface ConsolidatedWeatherRepo extends JpaRepository<ConsolidatedWeather, Long> {
	Optional<ConsolidatedWeather> findByCwId(Long cwid);
}
