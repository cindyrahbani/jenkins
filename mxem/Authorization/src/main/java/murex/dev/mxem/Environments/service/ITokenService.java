package murex.dev.mxem.Environments.service;


import murex.dev.mxem.Environments.exception.TokenNotValidException;
import murex.dev.mxem.Environments.model.Token;

public interface ITokenService {
    void save(Token token);
    Token findById(Long id);
    Token findByUsername(String username);
    void update(Token token);
    void delete(String id);
    Boolean tokenExists(String token);
    String getUserFromToken(String token) throws TokenNotValidException;
    int updateTest(Token token);
    public int saveTest(Token token);
    public int deleteTokenByIdTest(Long id);
    public int tokenExistsForTest(String token);

    }
