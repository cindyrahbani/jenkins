package murex.dev.mxem.Environments.repository;

import murex.dev.mxem.Environments.model.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends CrudRepository<Token, Long> {
    Optional<Token> findById(Long id);
    List<Token> findByUsername(String username);
    List<Token> findByToken(String token);

    Object delete(String token1);
}
