package sayem.picoapps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sayem.picoapps.domains.Cv;

@Repository
public interface CvRepository extends JpaRepository<Cv, Long> {
	public Cv findCvByUserId(Long id);
}
