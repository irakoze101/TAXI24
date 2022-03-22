package rw.bkg.taxi24.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.bkg.taxi24.models.Rider;

@Repository
public interface RidersRepo extends JpaRepository<Rider, String> {
}
