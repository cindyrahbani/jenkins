package murex.dev.mxem.Authorization.RoleTests;

import murex.dev.mxem.Environments.controller.RoleController;
import murex.dev.mxem.Environments.exception.PermissionNotFoundException;
import murex.dev.mxem.Environments.exception.RoleNotFoundException;
import murex.dev.mxem.Environments.exception.UserNotFoundException;
import murex.dev.mxem.Environments.model.Permission;
import murex.dev.mxem.Environments.model.Role;
import murex.dev.mxem.Environments.model.User;
import murex.dev.mxem.Environments.service.AuthorizationService;
import murex.dev.mxem.Environments.service.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class RoleControllerTests {

    @InjectMocks
    RoleController roleController;

    @Mock
    RoleService roleService;
    @Mock
    AuthorizationService authorizationService;

    @Test
    public void getAllRoles() {
        Role roleAdmin = new Role((long) 1, "admin", null, null, false, new Date(), "user", new Date(), "user");
        Role roleGuest = new Role((long) 2, "guest", null, null, false, new Date(), "user", new Date(), "user");

        List<Role> allRoles = new ArrayList<>();
        allRoles.add(roleAdmin);
        allRoles.add(roleGuest);
        when(roleService.findAllRoles()).thenReturn(allRoles);
        ResponseEntity<List<Role>> result = roleController.getAllRoles();
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void getRoleDetails() {
        Set<Role> rolesForUser = null;


        User user1 = new User(11L, "romy", rolesForUser, false, new Date(), "user", new Date(), "user");
        User user2 = new User(12L, "tracy", rolesForUser, false, new Date(), "user", new Date(), "user");

        Set<Permission> permForAdminRole = new Set<Permission>() {
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
            public Iterator<Permission> iterator() {
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
            public boolean add(Permission permission) {
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
            public boolean addAll(Collection<? extends Permission> collection) {
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
        };

        Set<User> usersForAdminRole = new Set<User>() {
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
            public Iterator<User> iterator() {
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
            public boolean add(User user) {
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
            public boolean addAll(Collection<? extends User> collection) {
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
        };
        usersForAdminRole.add(user1);


        Set<Role> rolesForWritePerm = new Set<Role>() {
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
        };

        Set<Role> rolesForReadPerm = new Set<Role>() {
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
        };

        Role roleAdmin = new Role(1L, "admin", usersForAdminRole, permForAdminRole, false, new Date(), "user", new Date(), "user");
        rolesForWritePerm.add(roleAdmin);

        rolesForReadPerm.add(roleAdmin);

        Permission writePermission = new Permission(1L, "writeperm", rolesForWritePerm, false, new Date(), "user", new Date(), "user");
        Permission readPermission = new Permission();

        permForAdminRole.add(writePermission);
        permForAdminRole.add(readPermission);

        when(roleService.findRoleByName("admin")).thenReturn(roleAdmin);

        ResponseEntity<Role> result1 = roleController.getRoleDetails("admin");
        assertThat(result1.getStatusCodeValue()).isEqualTo(200);

    }

    @Test
    public void getPermissionsForRole() throws RoleNotFoundException {
        Set<Role> rolesForReadPermission = new Set<Role>() {
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
        };

        Set<Role> rolesForWritePermisison = new Set<Role>() {
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
        };

        Permission readPermission = new Permission(2L, "read", rolesForReadPermission, false, new Date(), "user", new Date(), "user");
        Permission writePermission = new Permission(1L, "write", null, false, new Date(), "user", new Date(), "user");
        Set<Permission> ReadAndWritePermission = new Set<Permission>() {
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
            public Iterator<Permission> iterator() {
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
            public boolean add(Permission permission) {
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
            public boolean addAll(Collection<? extends Permission> collection) {
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
        };
        ReadAndWritePermission.add(readPermission);
        ReadAndWritePermission.add(writePermission);

        Set<Permission> read_Permission = new Set<Permission>() {
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
            public Iterator<Permission> iterator() {
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
            public boolean add(Permission permission) {
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
            public boolean addAll(Collection<? extends Permission> collection) {
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
        };
        read_Permission.add(readPermission);

        Role roleAdmin = new Role(1L, "admin", null, ReadAndWritePermission, false, new Date(), "user", new Date(), "user");
        Role roleGuest = new Role(2L, "guest", null, read_Permission, false, new Date(), "user", new Date(), "user");

        rolesForReadPermission.add(roleAdmin);
        rolesForReadPermission.add(roleGuest);

        rolesForWritePermisison.add(roleAdmin);

        when(roleService.findPermissionsForRole("1L")).thenReturn(ReadAndWritePermission);

        ResponseEntity<Set<Permission>> result = roleController.getPermissionsForRole("1L");
        assertThat(result.getStatusCodeValue()).isEqualTo(200);

    }

    @Test
    public void getUsersForRoleTest() throws RoleNotFoundException {


        Set<User> allUsers = new Set<User>() {
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
            public Iterator<User> iterator() {
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
            public boolean add(User user) {
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
            public boolean addAll(Collection<? extends User> collection) {
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
        };


        Set<Role> rolesForUser1 = new Set<Role>() {
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
        };
        Set<Role> rolesForUser2 = new Set<Role>() {
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

        Set<User> usersForAdminRole = new Set<User>() {
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
            public Iterator<User> iterator() {
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
            public boolean add(User user) {
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
            public boolean addAll(Collection<? extends User> collection) {
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
        };

        Set<User> usersForGuestRole = new Set<User>() {
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
            public Iterator<User> iterator() {
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
            public boolean add(User user) {
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
            public boolean addAll(Collection<? extends User> collection) {
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
        };

        Role roleAdmin = new Role(1L, "admin", usersForAdminRole, null, false, new Date(), "user", new Date(), "user");
        Role roleGuest = new Role(2L, "guest", usersForGuestRole, null, false, new Date(), "user", new Date(), "user");

        User user1 = new User(10L, "romy", rolesForUser1, false, new Date(), "user", new Date(), "user");
        User user2 = new User(11L, "tracy", rolesForUser2, false, new Date(), "user", new Date(), "user");
        User user3 = new User(12L, "mary", rolesForUser1, false, new Date(), "user", new Date(), "user");

        rolesForUser1.add(roleAdmin);
        rolesForUser1.add(roleGuest);

        rolesForUser2.add(roleGuest);

        allUsers.add(user1);
        allUsers.add(user2);
        allUsers.add(user3);

        usersForAdminRole.add(user1);
        usersForAdminRole.add(user3);

        usersForGuestRole.add(user1);
        usersForGuestRole.add(user2);
        usersForGuestRole.add(user3);

        when(roleService.findUsersForRole(1L)).thenReturn(usersForAdminRole);
        when(roleService.findUsersForRole(2L)).thenReturn(usersForGuestRole);

        ResponseEntity<Set<User>> resultAdmin = roleController.getUsersForRole(1L);
        assertThat(resultAdmin.getStatusCodeValue()).isEqualTo(200);
        ResponseEntity<Set<User>> resultGuest = roleController.getUsersForRole(2L);
        assertThat(resultGuest.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void deleteAllRolesTest() {
        Role roleAdmin = new Role(1L, "admin", null, null, false, new Date(), "user", new Date(), "user");
        Role roleGuest = new Role(2L, "guest", null, null, false, new Date(), "user", new Date(), "user");

        ArrayList<Role> roles = new ArrayList<>();
        roles.add(roleAdmin);
        roles.add(roleGuest);
        ArrayList<Role> rolesEmpty = new ArrayList<>();

        when(roleService.deleteAllRolesForTest()).thenReturn(0);
        ResponseEntity<Integer> rolesDeleted = roleController.deleteAllRolesForTest();
        assertThat(rolesDeleted.equals(0));
    }

    @Test
    public void deleteRoleByIdTest() throws RoleNotFoundException {
        Role roleAdmin = new Role(1L, "admin", null, null, false, new Date(), "user", new Date(), "user");
        Role roleGuest = new Role(2L, "guest", null, null, false, new Date(), "user", new Date(), "user");
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(roleAdmin);
        roles.add(roleGuest);
        when(roleService.deleteRoleByIdForTest("1L")).thenReturn(0);
        ResponseEntity<Integer> roleDeleted = roleController.deleteRoleByIdForTest("1L");
        assertThat(roleDeleted.equals(0));
    }

    @Test
    public void deletePermissionsForRoleForTest() throws RoleNotFoundException{
        Permission writePermission = new Permission(1L, "write", null, false, new Date(), "user", new Date(), "user");
        Permission readPermission = new Permission(2L, "read", null, false, new Date(), "user", new Date(), "user");
        Set<Permission>permForRole= new Set<Permission>() {
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
            public Iterator<Permission> iterator() {
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
            public boolean add(Permission permission) {
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
            public boolean addAll(Collection<? extends Permission> collection) {
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
        permForRole.add(readPermission);
        permForRole.add(writePermission);
        Role roleAdmin = new Role(1L, "admin", null, permForRole, false, new Date(), "user", new Date(), "user");
        //when(roleService.findRoleById(1L)).theb
        when(roleService.deletePermissionsForRoleForTest(1L)).thenReturn(0);
        ResponseEntity<Integer> deletedPermForRole = roleController.deletePermissionsForRoleForTest(1L);
        assertThat(deletedPermForRole.equals(0));
    }

    @Test
    public void deleteUsersForRoleForTest() throws RoleNotFoundException {
        User user1 = new User(10L, "romy", null, false, new Date(), "user", new Date(), "user");
        User user2 = new User(11L, "tracy", null, false, new Date(), "user", new Date(), "user");
        Set<User>usersForRole = new Set<User>() {
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
            public Iterator<User> iterator() {
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
            public boolean add(User user) {
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
            public boolean addAll(Collection<? extends User> collection) {
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
        usersForRole.add(user1);
        usersForRole.add(user2);
        Role roleAdmin = new Role(1L, "admin", usersForRole, null, false, new Date(), "user", new Date(), "user");
        when(roleService.deleteUsersForRoleForTest(1L)).thenReturn(0);
        ResponseEntity<Integer> deletedUsersForRole = roleController.deleteUsersForRoleForTest(1L);
        assertThat(deletedUsersForRole.equals(0));
    }

    @Test
    public void addRoleTest() {
        Role roleAdmin = new Role(1L, "admin", null, null, false, new Date(), "user", new Date(), "user");
        Role roleGuest = new Role(2L, "guest", null, null, false, new Date(), "user", new Date(), "user");
        Role roleUser = new Role(3L, "user", null, null, false, new Date(), "user", new Date(), "user");
        ArrayList<Role> roles = new ArrayList<>();
        when(roleService.addRole(roleAdmin)).thenReturn(roleAdmin);
        ResponseEntity<Void> roleAdded = roleController.addRoleForTesting(roleAdmin);
        assertThat(roleAdded.equals("CREATED"));
    }


    @Test
    public void updateRoleTest() throws RoleNotFoundException {
        Role roleAdmin = new Role((long) 1, "admin", null, null, false, new Date(), "user", new Date(), "user");
        Role roleGuest = new Role(2L, "guest", null, null, false, new Date(), "user", new Date(), "user");
        Role roleUser = new Role(3L, "user", null, null, false, new Date(), "user", new Date(), "user");
        when(roleService.updateRole((long) 3, roleAdmin)).thenReturn(roleAdmin);
        ResponseEntity<Role> result = roleController.updateRole("3", roleAdmin);
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }


    @Test
    public void updateNameOfRoleTest() throws RoleNotFoundException {
        //String newName= "GUEST";
        Role roleGuest = new Role((long) 2, "guest", null, null, false, new Date(), "user", new Date(), "user");
        when(roleService.findRoleById((long) 2)).thenReturn(Optional.of(roleGuest));
        when(roleService.updateNameofRole((long) 2, "GUESTPERSON")).thenReturn(roleGuest);
        //System.out.println(roleGuest.getName());
        ResponseEntity<Role> result1 = roleController.updateNameOfRole((long) 2, "GUESTPERSON");
        assertThat(result1.getStatusCodeValue()).isEqualTo(200);
    }





    @Test
    public void addUserforRole() throws RoleNotFoundException, UserNotFoundException {

        User user1 = new User(10L, "romy", null, false, new Date(), "user", new Date(), "user");
        User user2 = new User(11L, "tracy", null, false, new Date(), "user", new Date(), "user");

        Set<User> usersForAdminRole = new Set<User>() {
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
            public Iterator<User> iterator() {
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
            public boolean add(User user) {
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
            public boolean addAll(Collection<? extends User> collection) {
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
        };
        usersForAdminRole.add(user1);
        usersForAdminRole.add(user2);

        Role roleAdmin = new Role((long) 1, "admin", usersForAdminRole, null, false, new Date(), "user", new Date(), "user");
        User user3 = new User((long) 12, "mary", null, false, new Date(), "user", new Date(), "user");
        when(roleService.addUserForRole((long) 1, (long) 12)).thenReturn(user3);
        ResponseEntity<Set<User>> result = roleController.addUserforRole("1", "12");
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void addPermissionforRole() throws RoleNotFoundException, PermissionNotFoundException {
        Permission readPermission = new Permission(2L, "read", null, false, new Date(), "user", new Date(), "user");

        Set<Permission> permissionForAdminRole = new Set<Permission>() {
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
            public Iterator<Permission> iterator() {
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
            public boolean add(Permission permission) {
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
            public boolean addAll(Collection<? extends Permission> collection) {
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
        };
        permissionForAdminRole.add(readPermission);
        Role roleAdmin = new Role((long) 1, "admin", null, permissionForAdminRole, false, new Date(), "user", new Date(), "user");
        Permission writePermission = new Permission((long) 1, "write", null, false, new Date(), "user", new Date(), "user");
        when(roleService.addPermissionForRole((long) 1, (long) 1)).thenReturn(writePermission);
        ResponseEntity<Set<Permission>> result = roleController.addPermissionforRole("1", "1");
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }


}
