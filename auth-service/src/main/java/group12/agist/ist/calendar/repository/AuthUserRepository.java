package group12.agist.ist.calendar.repository;

import group12.agist.ist.calendar.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

  Optional<AuthUser> findByUsername(String username);

  Optional<AuthUser> findByUsernameAndPasswordHashed(String username, String password);
}
