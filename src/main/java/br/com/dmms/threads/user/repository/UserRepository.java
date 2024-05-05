package br.com.dmms.threads.user.repository;

import br.com.dmms.threads.user.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends Neo4jRepository<User, String> {

    Optional<User> findByEmail(String email);

}
