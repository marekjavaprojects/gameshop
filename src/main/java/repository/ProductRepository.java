package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gameshop.entity.User;

public interface ProductRepository extends JpaRepository<User, Long> {

}
