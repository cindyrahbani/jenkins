package murex.dev.mxem.Authorization.ValidateTests;


import murex.dev.mxem.Environments.exception.TokenNotValidException;
import murex.dev.mxem.Environments.model.Token;
import murex.dev.mxem.Environments.repository.TokenRepository;
import murex.dev.mxem.Environments.service.TokenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import javax.ws.rs.core.Application;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Application.class)
@ExtendWith(MockitoExtension.class)
public class TokenServiceTests {
    @InjectMocks
    TokenService tokenService;

    @Mock
    TokenRepository tokenRepository;

    @Test
    public void testGetTokenById()
    {
        // given
        Token token = new Token((long)432445,"token1","123abc123");
        // when
        when(tokenRepository.findById((long) 432445)).thenReturn(Optional.of(token));
        Token tok = tokenService.findById((long) 432445);
        // then
        assertThat(tok.equals(token));
    }
    @Test
    public void testGetTokenByUserName()
    {
        // given
        Token token = new Token((long)432445,"token1","123abc123");
        ArrayList<Token> tokens= new ArrayList<Token>();
        tokens.add(token);
        // when
        when(tokenRepository.findByUsername("token1")).thenReturn(tokens);
        Token tok = tokenService.findByUsername("token1");
        // then
        assertThat(tok.equals(token));
    }

    @Test
    public void testUpdateTokenTest()
    {
        // given
        Token token = new Token((long)432445,"token1","123abc123");
        // when
        when(tokenRepository.save(token)).thenReturn(token);
        int a = tokenService.updateTest(token);
        // then
        assertThat(a).isEqualTo(1);
    }
    @Test
    public void testDeleteTokenTest()
    {
        // given
        Token token = new Token((long)432445,"token1","123abc123");
        // when
        when(tokenRepository.findById((long)432445)).thenReturn(Optional.of(token));
        int a = tokenService.deleteTokenByIdTest((long)432445);
        // then
        assertThat(a).isEqualTo(1);
    }

    @Test
    public void testTokenExists()
    {
        // given
        Token token = new Token((long)432445,"token1","123abc123");
        ArrayList<Token> tokens= new ArrayList<Token>();
        tokens.add(token);
        // when
        when(tokenRepository.findByToken("token1")).thenReturn(tokens);
        Boolean a = tokenService.tokenExists("token1");
        // then
        assertThat(a).isEqualTo(true);
    }

    @Test
    public void testGetTokenFromUser() throws TokenNotValidException {
        //given
        Token token = new Token((long)432445,"token1","123abc123");
        ArrayList<Token> tokens= new ArrayList<Token>();
        tokens.add(token);
        // when
        when(tokenRepository.findByToken("token1")).thenReturn(tokens);
        String user = tokenService.getUserFromToken("token1");
        // then
        assertThat(user).isEqualTo(token.getUsername());
    }

    @Test
    public void testSaveTest() throws TokenNotValidException {
        // given
        Token token = new Token((long)432445,"token1","123abc123");
        // when
        when(tokenRepository.save(token)).thenReturn(token);
        int a = tokenService.saveTest(token);
        // then
        assertThat(a).isEqualTo(1);
    }
}
