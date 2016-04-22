package sayem.picoapps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sayem.picoapps.domains.Portfolio;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long>{

}
