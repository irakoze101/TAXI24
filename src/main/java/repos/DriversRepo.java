package repos;

import models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriversRepo extends JpaRepository<Driver, String> {
}