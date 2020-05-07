package murex.dev.mxem.Authorization.RoleTests;

import murex.dev.mxem.Environments.exception.PermissionNotFoundException;
import murex.dev.mxem.Environments.exception.RoleNotFoundException;
import murex.dev.mxem.Environments.exception.UserNotFoundException;
import murex.dev.mxem.Environments.model.Permission;
import murex.dev.mxem.Environments.model.Role;
import murex.dev.mxem.Environments.model.User;
import murex.dev.mxem.Environments.repository.PermissionRepository;
import murex.dev.mxem.Environments.repository.RoleRepository;
import murex.dev.mxem.Environments.repository.UserRepository;
import murex.dev.mxem.Environments.service.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class RoleServiceTests {
    @InjectMocks
    RoleService roleService;

    @Mock
    RoleRepository roleRepository;
    @Mock
    PermissionRepository permissionRepository;
    @Mock
    UserRepository userRepository;


    @Test
    public void findAllRolesTest() {
        Role roleAdmin = new Role(1L, "admin", null, null, false, new Date(), "user", new Date(), "user");
        Role roleGuest = new Role(2L, "guest", null, null, false, new Date(), "user", new Date(), "user");
        Role roleUser = new Role(3L, "user", null, null, false, new Date(), "user", new Date(), "user");
        List<Role> allRoles = new ArrayList<>();
        allRoles.add(roleAdmin);
        allRoles.add(roleGuest);
        allRoles.add(roleUser);

        when(roleRepository.findAll()).thenReturn(allRoles);
        List<Role> roles = roleService.findAllRoles();
        assertThat(roles.contains(allRoles));
    }


    @Test
    public void findRoleByIdTest() throws RoleNotFoundException {
        Role roleAdmin = new Role(1L, "admin", null, null, false, new Date(), "user", new Date(), "user");
        when(roleRepository.findById(1L)).thenReturn(Optional.of(roleAdmin));
        Optional<Role> roleById = roleService.findRoleById(1L);
        assertThat(roleById.equals(roleAdmin));
    }

    @Test
    public void findRoleByNameTest() throws RoleNotFoundException {
        Role roleAdmin = new Role(1L, "admin", null, null, false, new Date(), "user", new Date(), "user");
        List<Role> roleNames = new ArrayList<>();
        roleNames.add(roleAdmin);
        when(roleRepository.findByName("admin")).thenReturn(roleNames);
        Role roleName = roleService.findRoleByName("admin");
        assertThat(roleName.equals(roleAdmin));
    }


    @Test
    public void findPermissionsForRoleTest() throws RoleNotFoundException {
        Permission readPermission = new Permission(2L, "read", null, false, new Date(), "user", new Date(), "user");
        Permission writePermission = new Permission(1L, "write", null, false, new Date(), "user", new Date(), "user");
        Set<Permission> permissionsForAdminRole = new Set<Permission>() {
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
        permissionsForAdminRole.add(readPermission);
        permissionsForAdminRole.add(writePermission);

        Role roleAdmin = new Role((long) 1, "admin", null, permissionsForAdminRole, false, new Date(), "user", new Date(), "user");
        List<Role> roles = new ArrayList<>();

        roles.add(roleAdmin);
        when(roleRepository.findByName("admin")).thenReturn(roles);
        Role roleByName = roleService.findRoleByName("admin");
        assertThat(roleByName.equals(roleAdmin));
        assertThat(roleByName.getPermissions()).isEqualTo(permissionsForAdminRole);
        Set<Permission> result = roleService.findPermissionsForRole("admin");
        assertThat(result).isEqualTo(permissionsForAdminRole);
    }

    @Test
    public void findUsersForRoleTest() throws RoleNotFoundException {
        User user1 = new User(10L, "romy", null, false, new Date(), "user", new Date(), "user");
        User user2 = new User(11L, "tracy", null, false, new Date(), "user", new Date(), "user");
        User user3 = new User(12L, "grace", null, false, new Date(), "user", new Date(), "user");
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
        usersForAdminRole.add(user3);

        Role roleAdmin = new Role(1L, "admin", usersForAdminRole, null, false, new Date(), "user", new Date(), "user");
        when(roleRepository.findById(1L)).thenReturn(Optional.of(roleAdmin));
        Optional<Role> roleById = roleService.findRoleById(1L);
        assertThat(roleById.equals(roleAdmin));
        assertThat(roleById.get().getUsers()).isEqualTo(usersForAdminRole);
        //when(roleService.findRoleById(id).get().getUsers()).thenReturn(usersForAdminRole);
        Set<User> result = roleService.findUsersForRole(1L);
        assertThat(result).isEqualTo(usersForAdminRole);
    }



    @Test
    public void deleteAllRolesTest(){
        Role roleAdmin = new Role(1L, "admin", null, null, false, new Date(), "user", new Date(), "user");
        Role roleGuest = new Role(2L, "guest", null, null, false, new Date(), "user", new Date(), "user");
        List<Role>roles= new ArrayList<>(0);
        roles.add(roleAdmin);
        roles.add(roleGuest);
        when(roleRepository.findAll()).thenReturn(roles);
        roleService.deleteAllRolesForTest();
        assertThat(roleAdmin.getIsDeleted()).isEqualTo(true);
        assertThat(roleGuest.getIsDeleted()).isEqualTo(true);
    }

    @Test
    public void deleteRoleByIdTest() throws RoleNotFoundException {
        Role roleAdmin = new Role((long)1, "admin", null, null, false, new Date(), "user", new Date(), "user");
        List<Role>listRole= new ArrayList<>();
        listRole.add(roleAdmin);
        when(roleRepository.findByName("1")).thenReturn(listRole);
//        when(roleRepository.deleteById((long)1)).thenReturn(roleAdmin);
        roleService.deleteRoleByIdForTest("1");
        assertThat(roleAdmin.getIsDeleted()).isEqualTo(true);
    }





    @Test
    public void deletePermissionsForRoleTest() throws RoleNotFoundException {
        Permission readPermission = new Permission(2L, "read", null, false, new Date(), "user", new Date(), "user");
        Permission writePermission = new Permission(1L, "write", null, false, new Date(), "user", new Date(), "user");
        Set<Permission>permisisonsForRole = new Set<Permission>() {
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
        permisisonsForRole.add(readPermission);
        permisisonsForRole.add(writePermission);
        Role roleAdmin = new Role((long)1, "admin", null, permisisonsForRole, false, new Date(), "user", new Date(), "user");
        //when(roleService.findRoleById((long)1)).thenReturn(Optional.of(roleAdmin));
        when(roleRepository.findById((long)1)).thenReturn(Optional.of(roleAdmin));
        when(roleRepository.save(roleAdmin)).thenReturn(roleAdmin);
        roleService.deletePermissionsForRoleForTest((long)1);
        assertThat(roleAdmin.getPermissions()).isEmpty();

    }

    @Test
    public void deleteUsersForRoleTest() throws RoleNotFoundException {
        User user1 = new User(10L, "romy", null, false, new Date(), "user", new Date(), "user");
        User user2 = new User(11L, "tracy", null, false, new Date(), "user", new Date(), "user");
        Set<User> usersForRole = new Set<User>() {
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
        usersForRole.add(user1);
        usersForRole.add(user2);
        Role roleAdmin = new Role((long)1, "admin", usersForRole, null, false, new Date(), "user", new Date(), "user");
        when(roleRepository.findById((long)1)).thenReturn(Optional.of(roleAdmin));
        when(roleRepository.save(roleAdmin)).thenReturn(roleAdmin);
        roleService.deleteUsersForRole((long)1);
        assertThat(roleAdmin.getUsers()).isEmpty();
    }


    @Test
    public void addRoleTest() {
        Role roleAdmin = new Role(1L, "admin", null, null, false, new Date(), "user", new Date(), "user");
        when(roleRepository.save(roleAdmin)).thenReturn(roleAdmin);
        Role result = roleService.addRole(roleAdmin);
        assertThat(result.equals(roleAdmin));

    }



    @Test
    public void updateRoleTest() throws RoleNotFoundException{
        Role roleAdmin = new Role((long)1, "admin", null, null, false, new Date(), "user", new Date(), "user");
        Role roleGuest = new Role((long)2, "guest", null, null, false, new Date(), "user", new Date(), "user");
        Role roleUser = new Role((long)3, "user", null, null, false, new Date(), "user", new Date(), "user");
        Role newRoleAdmin= new Role((long)3, "admin", null, null, false, new Date(), "user", new Date(), "user");
        when(roleRepository.findById((long)3)).thenReturn(Optional.of(roleUser));
        when(roleRepository.save(roleAdmin)).thenReturn(roleAdmin);
        Role roleUpdate= roleService.updateRole((long)3, roleAdmin);
        assertThat(roleUpdate.getId()).isEqualTo((long)3);
    }



    @Test
    public void updateNameofRoleTest() throws RoleNotFoundException{
        Role roleAdmin = new Role((long)1, "admin", null, null, false, new Date(), "user", new Date(), "user");
        when(roleRepository.findById((long)1)).thenReturn(Optional.of(roleAdmin));
        when(roleRepository.save(roleAdmin)).thenReturn(roleAdmin);
//        Optional<Role> roleById = roleService.findRoleById((long)1);
//        assertThat(roleById.equals(roleAdmin));
        //Role role = roleService.findRoleById((long)1).get();
        //assertThat(role.equals(roleAdmin));
        //assertThat(role.getName()).isEqualTo("admin");
        Role newRole =roleService.updateNameofRole( (long)1, "adminadmin");
        assertThat(newRole.getName()).isEqualTo("adminadmin");
    }



    @Test
    public void addUserForRole() throws RoleNotFoundException, UserNotFoundException {User user1 = new User(10L, "romy", null, false, new Date(), "user", new Date(), "user");
        User user2 = new User(11L, "tracy", null, false, new Date(), "user", new Date(), "user");
        User user3 = new User(12L, "grace", null, false, new Date(), "user", new Date(), "user");
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
        usersForAdminRole.add(user3);
        Role roleAdmin = new Role(1L, "admin", usersForAdminRole, null, false, new Date(), "user", new Date(), "user");
        User user4= new User(12L, "valerie", null, false, new Date(), "user", new Date(), "user");
        when(roleRepository.findById(1L)).thenReturn(Optional.of(roleAdmin));
        when(userRepository.findById(12L)).thenReturn(Optional.of(user4));
        when(roleRepository.save(roleAdmin)).thenReturn(roleAdmin);
        User newUser= roleService.addUserForRole(1L, 12L);
        assertThat(newUser).isEqualTo(user4);
        //assertThat(roleAdmin.getUsers()).isEqualTo(usersForAdminRole.add(user4));

//        Set<User> result = roleService.findUsersForRole(1L);
//        assertThat(result).isEqualTo(usersForAdminRole.add(user4));


    }

    @Test
    public void addPermissionForRoleTest() throws RoleNotFoundException, PermissionNotFoundException {
        Permission readPermission = new Permission(2L, "read", null, false, new Date(), "user", new Date(), "user");
        Permission writePermission = new Permission(1L, "write", null, false, new Date(), "user", new Date(), "user");
        Set<Permission> permissionsForAdminRole = new Set<Permission>() {
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
        permissionsForAdminRole.add(readPermission);
        permissionsForAdminRole.add(writePermission);

        Role roleAdmin = new Role((long) 1, "admin", null, permissionsForAdminRole, false, new Date(), "user", new Date(), "user");
        List<Role> roles = new ArrayList<>();
        roles.add(roleAdmin);
        Permission executePermission = new Permission(3L, "execute", null, false, new Date(), "user", new Date(), "user");
        when(roleRepository.findById(1L)).thenReturn(Optional.of(roleAdmin));
        //when(permissionRepository.save(executePermission)).thenReturn(executePermission);
        when(permissionRepository.findById(3L)).thenReturn(Optional.of(executePermission));
        when(roleRepository.save(roleAdmin)).thenReturn(roleAdmin);
        Permission newPermission= roleService.addPermissionForRole(1L, 3L);
        assertThat(newPermission).isEqualTo(executePermission);
//        Set<Permission>newPermSet= new Set<Permission>() {
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
//        };
//        newPermSet.add(readPermission);
//        newPermSet.add(writePermission);
//        newPermSet.add(executePermission);
//        assertThat(permissionsForAdminRole.add(newPermission)).isEqualTo(newPermSet);

    }
}

