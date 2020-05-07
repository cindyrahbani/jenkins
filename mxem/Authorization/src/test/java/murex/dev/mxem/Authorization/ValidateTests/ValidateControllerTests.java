package murex.dev.mxem.Authorization.ValidateTests;

import murex.dev.mxem.Environments.controller.ValidateController;
import murex.dev.mxem.Environments.model.Token;
import murex.dev.mxem.Environments.service.TokenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Application;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
@ExtendWith(MockitoExtension.class)
public class ValidateControllerTests {

    @InjectMocks
    ValidateController validateController;

    @Mock
    TokenService tokenService;

    @Test
    public void testLoginForTest()
    {
        ResponseEntity<String> token1 = validateController.loginForTest();
        assertThat(token1.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testValidateToken()
    {
        Token token1 = new Token("cchedidrahbani","0123");
        ResponseEntity<Void> res = validateController.validateToken("0123");
        assertThat(res.getStatusCodeValue()).isEqualTo(401);
    }

    @Test
    public void testIdentifyToken()
    {
        Token token1 = new Token("cchedidrahbani","0123");
        HttpServletResponse response = null;
        ResponseEntity<Void> res = validateController.identifyUser("0123", response);
        assertThat(res.getStatusCodeValue()).isEqualTo(401);
    }
}