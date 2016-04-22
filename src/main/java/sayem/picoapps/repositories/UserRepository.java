package sayem.picoapps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sayem.picoapps.domains.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
