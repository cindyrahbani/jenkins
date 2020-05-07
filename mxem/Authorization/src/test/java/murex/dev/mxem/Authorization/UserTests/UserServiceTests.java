package murex.dev.mxem.Authorization.UserTests;

import murex.dev.mxem.Environments.exception.RoleNotFoundException;
import murex.dev.mxem.Environments.exception.UserNotFoundException;
import murex.dev.mxem.Environments.model.Role;
import murex.dev.mxem.Environments.model.User;
import murex.dev.mxem.Environments.repository.RoleRepository;
import murex.dev.mxem.Environments.repository.UserRepository;
import murex.dev.mxem.Environments.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import javax.ws.rs.core.Application;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Application.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

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
        when(userRepository.findAll()).thenReturn(users);
        // when
        List<User> users1 = userService.findAllUsers();
        // then
        assertThat(users1.equals(users));
    }

    @Test
    public void testAddRoleForUser() throws RoleNotFoundException, UserNotFoundException {
        Role role1 = new Role((long) 123456, "Cindy", false, new Date(),"Cindy",new Date(),"Cindy");
        Role role2 = new Role((long) 456354, "kiki", false, new Date(),"kiki",new Date(),"kiki");
        Role role3 = new Role((long) 987609, "fanie", false, new Date(),"fanie",new Date(),"fanie");

        Set<Role> roles = new Set<Role>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Role> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] ts) {
                return null;
            }

            @Override
            public boolean add(Role role) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Role> collection) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }
        };
        roles.add(role1);
        roles.add(role2);
        roles.add(role3);
        // given
        User user1 = new User((long) 75093209,"cindy",roles,true, new Date(),"cindy", new Date(),"cindy");
        User user2 = new User((long) 753209,"fanie", roles,true, new Date(),"fanie", new Date(),"fanie");
        User user3 = new User((long) 9209,"lea",roles,true, new Date(),"lea", new Date(),"lea");
        ArrayList<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        when(userRepository.findById((long) 75093209)).thenReturn(Optional.of(user1));
        when(roleRepository.findById((long) 123456)).thenReturn(Optional.of(role1));
        when(userRepository.save(user1)).thenReturn(user1);
        // when
        Role roles1 = userService.addRoleForUser(((long) 75093209),((long) 123456));
        // then
        assertThat(roles1.equals(role1));
    }
    @Test
    public void testAddUser()
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

        when(userRepository.save(user1)).thenReturn(user1);
        // when
        User users1 = userService.addUser(user1);
        // then
        assertThat(users1.equals(user1));
    }

    @Test
    public void testUpdateUser() throws UserNotFoundException {
        Set<Role> roleSet = null;
        // given
        User user1 = new User((long) 75093209,"cindy",roleSet,true, new Date(),"cindy", new Date(),"cindy");
        User user2 = new User((long) 753209,"fanie", roleSet,true, new Date(),"fanie", new Date(),"fanie");
        User user3 = new User((long) 9209,"lea",roleSet,true, new Date(),"lea", new Date(),"lea");
        ArrayList<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        when(userRepository.findById((long) 75093209)).thenReturn(Optional.of(user1));
        when(userRepository.save(user1)).thenReturn(user1);
        // when
        User users1 = userService.updateUser((long) 75093209,user1);
        // then
        assertThat(users1.equals(user1));
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
        when(userRepository.findById((long) 75093209)).thenReturn(java.util.Optional.of(user1));
        // when
        Optional<User> users1 = userService.findUserById((long) 75093209);
        // then
        assertThat(users1.equals(user1));
    }

    @Test
    public void testUpdateNameOfUser() throws UserNotFoundException {
        Set<Role> roleSet = null;
        // given
        User user1 = new User((long) 75093209,"cindy",roleSet,true, new Date(),"cindy", new Date(),"cindy");
        User user2 = new User((long) 753209,"fanie", roleSet,true, new Date(),"fanie", new Date(),"fanie");
        User user3 = new User((long) 9209,"lea",roleSet,true, new Date(),"lea", new Date(),"lea");
        ArrayList<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        when(userRepository.findById((long) 75093209)).thenReturn(Optional.of(user1));
        when(userRepository.save(user1)).thenReturn(user1);
        // when
        User users1 = userService.updateNameofUser(((long) 75093209),"hello");
        // then
        assertThat(users1.equals(user1));
    }

    @Test
    public void testGetUserByName() throws UserNotFoundException
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
        when(userRepository.findByName("cindy")).thenReturn(users);
        // when
        User users1 = userService.findUserByName("cindy");
        // then
        assertThat(users1.equals(users));
    }

    @Test
    public void testGetRolesForUser() throws UserNotFoundException
    {
        Role role1 = new Role((long) 123456, "Cindy", false, new Date(),"Cindy",new Date(),"Cindy");
        Role role2 = new Role((long) 456354, "kiki", false, new Date(),"kiki",new Date(),"kiki");
        Role role3 = new Role((long) 987609, "fanie", false, new Date(),"fanie",new Date(),"fanie");

        Set<Role> roles = new Set<Role>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Role> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] ts) {
                return null;
            }

            @Override
            public boolean add(Role role) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Role> collection) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }
        };
        roles.add(role1);
        roles.add(role2);
        roles.add(role3);
        // given
        User user1 = new User((long) 75093209,"cindy",roles,true, new Date(),"cindy", new Date(),"cindy");
        User user2 = new User((long) 753209,"fanie", roles,true, new Date(),"fanie", new Date(),"fanie");
        User user3 = new User((long) 9209,"lea",roles,true, new Date(),"lea", new Date(),"lea");
        ArrayList<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        when(userRepository.findByName("cindy")).thenReturn(users);
        // when
        Set<Role> roles1 = userService.findRolesForUser("cindy");
        // then
        assertThat(roles1.equals(user1.getRoles()));
    }

    @Test
    public void TestDeleteAllUsersTest() throws UserNotFoundException
    {
        Role role1 = new Role((long) 123456, "Cindy", false, new Date(),"Cindy",new Date(),"Cindy");
        Role role2 = new Role((long) 456354, "kiki", false, new Date(),"kiki",new Date(),"kiki");
        Role role3 = new Role((long) 987609, "fanie", false, new Date(),"fanie",new Date(),"fanie");

        Set<Role> roles = new Set<Role>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Role> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] ts) {
                return null;
            }

            @Override
            public boolean add(Role role) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Role> collection) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }
        };
        roles.add(role1);
        roles.add(role2);
        roles.add(role3);
        // given
        User user1 = new User((long) 75093209,"cindy",roles,true, new Date(),"cindy", new Date(),"cindy");
        User user2 = new User((long) 753209,"fanie", roles,true, new Date(),"fanie", new Date(),"fanie");
        User user3 = new User((long) 9209,"lea",roles,true, new Date(),"lea", new Date(),"lea");
        ArrayList<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        when(userRepository.findAll()).thenReturn(users);
        // when
        int number = userService.TestDeleteAllUsers();
        // then
        assertThat(number).isEqualTo(1);
    }

    @Test
    public void TestDeleteUserByIdTest() throws UserNotFoundException
    {
        Role role1 = new Role((long) 123456, "Cindy", false, new Date(),"Cindy",new Date(),"Cindy");
        Role role2 = new Role((long) 456354, "kiki", false, new Date(),"kiki",new Date(),"kiki");
        Role role3 = new Role((long) 987609, "fanie", false, new Date(),"fanie",new Date(),"fanie");

        Set<Role> roles = new Set<Role>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Role> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] ts) {
                return null;
            }

            @Override
            public boolean add(Role role) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Role> collection) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }
        };
        roles.add(role1);
        roles.add(role2);
        roles.add(role3);
        // given
        User user1 = new User((long) 75093209,"cindy",roles,true, new Date(),"cindy", new Date(),"cindy");
        User user2 = new User((long) 753209,"fanie", roles,true, new Date(),"fanie", new Date(),"fanie");
        User user3 = new User((long) 9209,"lea",roles,true, new Date(),"lea", new Date(),"lea");
        ArrayList<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        when(userRepository.findById((long) 75093209)).thenReturn(Optional.of(user1));
        // when
        int number = userService.deleteUserByIdTest((long) 75093209);
        // then
        assertThat(number).isEqualTo(1);
    }

    @Test
    public void TestDeleteRolesForUser() throws UserNotFoundException
    {
        Role role1 = new Role((long) 123456, "Cindy", false, new Date(),"Cindy",new Date(),"Cindy");
        Role role2 = new Role((long) 456354, "kiki", false, new Date(),"kiki",new Date(),"kiki");
        Role role3 = new Role((long) 987609, "fanie", false, new Date(),"fanie",new Date(),"fanie");

        Set<Role> roles = new Set<Role>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Role> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] ts) {
                return null;
            }

            @Override
            public boolean add(Role role) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Role> collection) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }
        };
        roles.add(role1);
        roles.add(role2);
        roles.add(role3);
        // given
        User user1 = new User((long) 75093209,"cindy",roles,true, new Date(),"cindy", new Date(),"cindy");
        User user2 = new User((long) 753209,"fanie", roles,true, new Date(),"fanie", new Date(),"fanie");
        User user3 = new User((long) 9209,"lea",roles,true, new Date(),"lea", new Date(),"lea");
        ArrayList<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        when(userRepository.findById((long)75093209)).thenReturn(Optional.of(user1));
        when(userRepository.save(user1)).thenReturn(user1);
        // when
        int number = userService.deleteRolesForUserTest((long) 75093209);
        // then
        assertThat(number).isEqualTo(1);
    }

//    @Test
//    public void TestGetRolesPermissions() throws UserNotFoundException
//    {
//        Role role1 = new Role((long) 123456, "Cindy", false, new Date(),"Cindy",new Date(),"Cindy");
//        Set<Role> roles = new Set<Role>() {
//            @Override
//            public int size() {
//                return 0;
//            }
//
//            @Override
//            public boolean isEmpty() {
//                return false;
//            }
//
//            @Override
//            public boolean contains(Object o) {
//                return false;
//            }
//
//            @Override
//            public Iterator<Role> iterator() {
//                return null;
//            }
//
//            @Override
//            public Object[] toArray() {
//                return new Object[0];
//            }
//
//            @Override
//            public <T> T[] toArray(T[] ts) {
//                return null;
//            }
//
//            @Override
//            public boolean add(Role role) {
//                return false;
//            }
//
//            @Override
//            public boolean remove(Object o) {
//                return false;
//            }
//
//            @Override
//            public boolean containsAll(Collection<?> collection) {
//                return false;
//            }
//
//            @Override
//            public boolean addAll(Collection<? extends Role> collection) {
//                return false;
//            }
//
//            @Override
//            public boolean retainAll(Collection<?> collection) {
//                return false;
//            }
//
//            @Override
//            public boolean removeAll(Collection<?> collection) {
//                return false;
//            }
//
//            @Override
//            public void clear() {
//
//            }
//
//            @Override
//            public boolean equals(Object o) {
//                return false;
//            }
//
//            @Override
//            public int hashCode() {
//                return 0;
//            }
//        };
//        roles.add(role1);
//
//        Permission permission1 = new Permission((long) 423432, "fanie",roles, true, new Date(), "fanie", new Date(),"fanie");
//        Set<Permission> permissions = new Set<Permission>() {
//            @Override
//            public int size() {
//                return 0;
//            }
//
//            @Override
//            public boolean isEmpty() {
//                return false;
//            }
//
//            @Override
//            public boolean contains(Object o) {
//                return false;
//            }
//
//            @Override
//            public Iterator<Permission> iterator() {
//                return null;
//            }
//
//            @Override
//            public Object[] toArray() {
//                return new Object[0];
//            }
//
//            @Override
//            public <T> T[] toArray(T[] ts) {
//                return null;
//            }
//
//            @Override
//            public boolean add(Permission permission) {
//                return false;
//            }
//
//            @Override
//            public boolean remove(Object o) {
//                return false;
//            }
//
//            @Override
//            public boolean containsAll(Collection<?> collection) {
//                return false;
//            }
//
//            @Override
//            public boolean addAll(Collection<? extends Permission> collection) {
//                return false;
//            }
//
//            @Override
//            public boolean retainAll(Collection<?> collection) {
//                return false;
//            }
//
//            @Override
//            public boolean removeAll(Collection<?> collection) {
//                return false;
//            }
//
//            @Override
//            public void clear() {
//
//            }
//
//            @Override
//            public boolean equals(Object o) {
//                return false;
//            }
//
//            @Override
//            public int hashCode() {
//                return 0;
//            }
//        };
//        permissions.add(permission1);
//
//        User user1 = new User((long) 75093209,"cindy",roles,true, new Date(),"cindy", new Date(),"cindy");
//
//        // given
//        role1.setPermissions(permissions);
//        ArrayList<User> users1 = new ArrayList<User>();
//        users1.add(user1);
//        Set<User> users = new Set<User>() {
//            @Override
//            public int size() {
//                return 0;
//            }
//
//            @Override
//            public boolean isEmpty() {
//                return false;
//            }
//
//            @Override
//            public boolean contains(Object o) {
//                return false;
//            }
//
//            @Override
//            public Iterator<User> iterator() {
//                return null;
//            }
//
//            @Override
//            public Object[] toArray() {
//                return new Object[0];
//            }
//
//            @Override
//            public <T> T[] toArray(T[] ts) {
//                return null;
//            }
//
//            @Override
//            public boolean add(User user) {
//                return false;
//            }
//
//            @Override
//            public boolean remove(Object o) {
//                return false;
//            }
//
//            @Override
//            public boolean containsAll(Collection<?> collection) {
//                return false;
//            }
//
//            @Override
//            public boolean addAll(Collection<? extends User> collection) {
//                return false;
//            }
//
//            @Override
//            public boolean retainAll(Collection<?> collection) {
//                return false;
//            }
//
//            @Override
//            public boolean removeAll(Collection<?> collection) {
//                return false;
//            }
//
//            @Override
//            public void clear() {
//
//            }
//
//            @Override
//            public boolean equals(Object o) {
//                return false;
//            }
//
//            @Override
//            public int hashCode() {
//                return 0;
//            }
//        };
//        users.add(user1);
//
//        Set<String> permNames =  new HashSet<String>();
//        Set<String> roleNames = new HashSet<String>();
//        permNames.add("fanie");
//        roleNames.add("cindy");
//        RolesPermissions res = new RolesPermissions();
//        res.setPermissions(permNames);
//        res.setRoles(roleNames);
//
//        when(userRepository.findByName("cindy")).thenReturn(users1);
//        users1.get(0).setRoles(roles);
//        // when
//        System.out.println("testinggggggg");
//        System.out.println(users1.get(0));
//        System.out.println("testinggggggg22222222222222222222");
//        System.out.println("this is itt"+ (users1.get(0)).getRoles());
//
//        RolesPermissions rp = userService.getRolesPermissions("cindy");
//        // then
//        assertThat((rp).equals(res));
//    }
}