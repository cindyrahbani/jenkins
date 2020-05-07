package murex.dev.mxem.Authorization.UserTests;

import murex.dev.mxem.Environments.controller.UserController;
import murex.dev.mxem.Environments.exception.RoleNotFoundException;
import murex.dev.mxem.Environments.exception.UserNotFoundException;
import murex.dev.mxem.Environments.model.Permission;
import murex.dev.mxem.Environments.model.Role;
import murex.dev.mxem.Environments.model.User;
import murex.dev.mxem.Environments.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import javax.ws.rs.core.Application;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Application.class)
@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Test
    public void testGetAllUsers()
    {
        Set<Role> roleSet = null;
        // given
        User user1 = new User((long) 75093209,"cindy",roleSet,true, new Date(),"cindy", new Date(),"cindy");
        User user2 = new User((long) 753209,"fanie", roleSet,true, new Date(),"fanie", new Date(),"fanie");
        User user3 = new User((long) 9209,"lea",roleSet,true, new Date(),"lea", new Date(),"lea");
        ArrayList<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        when(userService.findAllUsers()).thenReturn(users);
        // when
        ResponseEntity<List<User>> users1 = userController.getAllUsers();
        // then
        assertThat(users1.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testGetUserDetails() throws UserNotFoundException
    {
        Set<Role> roleSet = null;
        // given
        User user1 = new User((long) 75093209,"cindy",roleSet,true, new Date(),"cindy", new Date(),"cindy");
        User user2 = new User((long) 753209,"fanie", roleSet,true, new Date(),"fanie", new Date(),"fanie");
        User user3 = new User((long) 9209,"lea",roleSet,true, new Date(),"lea", new Date(),"lea");
        ArrayList<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        when(userService.findUserByName("fanie")).thenReturn(user1);
        // when
        ResponseEntity<User> users1 = userController.getUserDetails("fanie");
        // then
        assertThat(users1.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testgetRolesForUser() throws UserNotFoundException
    {
        Set<Role> roleSet1 = null;
        Set<Role> roleSet2 = null;
        Set<Role> roleSet3 = null;
        // given
        User user1 = new User((long) 75093209,"cindy",roleSet1,true, new Date(),"cindy", new Date(),"cindy");
        User user2 = new User((long) 753209,"fanie", roleSet2,true, new Date(),"fanie", new Date(),"fanie");
        User user3 = new User((long) 9209,"lea",roleSet3,true, new Date(),"lea", new Date(),"lea");
        ArrayList<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        when(userService.findRolesForUser("cindy")).thenReturn(roleSet1);
        // when
        ResponseEntity<Set<Role>> roles = userController.getRolesForUser("cindy");
        // then
        assertThat(roles.getStatusCodeValue()).isEqualTo(200);
    }

@Test
public void testAddRoleForUser() throws UserNotFoundException, RoleNotFoundException
{
    Set<Role> roleSet1 = null;
    Set<Role> roleSet2 = null;
    Set<Role> roleSet3 = null;
    Set <User> userSet1 = null;
    Set <Permission> permissionSet1 = null;
    // given
    User user1 = new User((long) 75093209,"cindy",roleSet1,true, new Date(),"cindy", new Date(),"cindy");
    User user2 = new User((long) 753209,"fanie", roleSet2,true, new Date(),"fanie", new Date(),"fanie");
    User user3 = new User((long) 9209,"lea",roleSet3,true, new Date(),"lea", new Date(),"lea");
    ArrayList<User> users = new ArrayList<User>();
    users.add(user1);
    users.add(user2);
    users.add(user3);
    Role role1 = new Role((long) 768472, "role1", userSet1, permissionSet1,true, new Date(),"cindy", new Date(),"cindy");

    when(userService.addRoleForUser((long) 75093209, (long)768472)).thenReturn(role1);

    // when
    ResponseEntity<Set<Role>> roles = userController.addRoleForUser("75093209", "768472");
    // then
    assertThat(roles.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testUpdateUser() throws UserNotFoundException
    {
        Set<Role> roleSet1 = null;
        // given
        User user1 = new User((long) 75093209,"cindy",roleSet1,true, new Date(),"cindy", new Date(),"cindy");

        when(userService.updateUser((long) 75093209, user1)).thenReturn(user1);
        // when
        ResponseEntity<User> user = userController.updateUser("75093209", user1);
        // then
        assertThat(user.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testUpdateNameOfUser() throws UserNotFoundException
    {
        Set<Role> roleSet1 = null;
        // given
        User user1 = new User((long) 75093209,"cindy",roleSet1,true, new Date(),"cindy", new Date(),"cindy");

        when(userService.findUserById((long) 75093209)).thenReturn(java.util.Optional.of(user1));
        when(userService.updateNameofUser(user1.getId(), "cindy")).thenReturn(user1);

        // when
        ResponseEntity<User> user = userController.updateNameOfUser(user1.getId(), "cindy");
        // then
        assertThat(user.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testAddUserTest()
    {
        Set<Role> roleSet1 = null;
        // given
        User user1 = new User((long) 75093209,"cindy",roleSet1,true, new Date(),"cindy", new Date(),"cindy");

        when(userService.addUser(user1)).thenReturn(user1);

        // when
        ResponseEntity<User> user = userController.addUserTest(user1);
        // then
        assertThat(user.getStatusCodeValue()).isEqualTo(200);
    }
    @Test
    public void testDeleteAllUsersTest()
    {
        Set<Role> roleSet1 = null;
        // given
        when(userService.deleteAllUsersTest()).thenReturn(1);

        // when
        ResponseEntity<Integer> user = userController.deleteAllUsersTest();
        // then
        assertThat(user.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testDeleteUserByIdTest() throws UserNotFoundException {
        Set<Role> roleSet1 = null;
        User user1 = new User((long) 75093209,"cindy",roleSet1,true, new Date(),"cindy", new Date(),"cindy");
        // given
        when(userService.deleteUserByIdTest((long)75093209)).thenReturn(1);

        // when
        ResponseEntity<Integer> user = userController.deleteUserByIdTest((long)75093209);
        // then
        assertThat(user.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testDeleteRolesForUserTest() throws UserNotFoundException {
        Set<Role> roleSet1 = null;
        User user1 = new User((long) 75093209,"cindy",roleSet1,true, new Date(),"cindy", new Date(),"cindy");
        // given
        when(userService.deleteRolesForUserTest((long)75093209)).thenReturn(1);

        // when
        ResponseEntity<Integer> user = userController.deleteRolesForUserTest((long)75093209);
        // then
        assertThat(user.getStatusCodeValue()).isEqualTo(200);
    }
}