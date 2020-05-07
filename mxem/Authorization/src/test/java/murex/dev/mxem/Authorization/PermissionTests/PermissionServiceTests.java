package murex.dev.mxem.Authorization.PermissionTests;

import murex.dev.mxem.Environments.exception.PermissionNotFoundException;
import murex.dev.mxem.Environments.exception.RoleNotFoundException;
import murex.dev.mxem.Environments.model.Permission;
import murex.dev.mxem.Environments.model.Role;
import murex.dev.mxem.Environments.repository.PermissionRepository;
import murex.dev.mxem.Environments.repository.RoleRepository;
import murex.dev.mxem.Environments.service.PermissionService;
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
public class PermissionServiceTests {
    @InjectMocks
    PermissionService permissionService;

    @Mock
    PermissionRepository permissionRepository;

    @Mock
    RoleRepository roleRepository;

    @Test
    public void testGetAllPermissions()
    {
        Set<Role> roleSet = null;
        // given
        Permission permission1 = new Permission((long) 423432, "fanie",roleSet, true, new Date(), "fanie", new Date(),"fanie");
        Permission permission2 = new Permission((long) 986459, "cindy",roleSet, true, new Date(), "cindy", new Date(),"cindy");
        Permission permission3 = new Permission((long) 543298, "kiki",roleSet, true, new Date(), "kiki", new Date(),"kiki");

        ArrayList<Permission> permissions = new ArrayList<Permission>();
        permissions.add(permission1);
        permissions.add(permission2);
        permissions.add(permission3);

        when(permissionRepository.findAll()).thenReturn(permissions);
        // when
        List<Permission> permissions1 = permissionService.findAllPermissions();
        // then
        assertThat(permissions1.equals(permissions));
    }

    @Test
    public void testGetUserDetails() throws PermissionNotFoundException {
        Set<Role> roleSet = null;
        // given
        Permission permission1 = new Permission((long) 423432, "fanie",roleSet, true, new Date(), "fanie", new Date(),"fanie");
        Permission permission2 = new Permission((long) 986459, "cindy",roleSet, true, new Date(), "cindy", new Date(),"cindy");
        Permission permission3 = new Permission((long) 543298, "kiki",roleSet, true, new Date(), "kiki", new Date(),"kiki");

        ArrayList<Permission> permissions = new ArrayList<Permission>();
        permissions.add(permission1);
        permissions.add(permission2);
        permissions.add(permission3);

        when(permissionRepository.findById((long) 423432)).thenReturn(java.util.Optional.of(permission1));
        // when
        Optional<Permission> permissions1 = permissionService.findPermissionById((long) 423432);
        // then
        assertThat(permissions1.equals(permission1));
    }

    @Test
    public void testFindRolesForPermission() throws PermissionNotFoundException {
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
        Permission permission1 = new Permission((long) 423432, "fanie",roles, true, new Date(), "fanie", new Date(),"fanie");

        when(permissionRepository.findById((long) 423432)).thenReturn(java.util.Optional.of(permission1));
        when(permissionService.findPermissionById((long) 423432)).thenReturn(java.util.Optional.of(permission1));
        // when
        Set<Role> roles1 = permissionService.findRolesForPermission((long) 423432);
        // then
        assertThat(roles1.equals(permission1.getRoles()));
    }

    @Test
    public void TestDeleteAllPermissionsTest()
    {
        Set<Role> roleSet = null;
        // given
        Permission permission1 = new Permission((long) 423432, "fanie",roleSet, true, new Date(), "fanie", new Date(),"fanie");
        Permission permission2 = new Permission((long) 986459, "cindy",roleSet, true, new Date(), "cindy", new Date(),"cindy");
        Permission permission3 = new Permission((long) 543298, "kiki",roleSet, true, new Date(), "kiki", new Date(),"kiki");

        ArrayList<Permission> permissions = new ArrayList<Permission>();
        permissions.add(permission1);
        permissions.add(permission2);
        permissions.add(permission3);

        when(permissionRepository.findAll()).thenReturn(permissions);
        // when
        int permissions1 = permissionService.TestDeleteAllPermissions();
        // then
        assertThat(permissions1).isEqualTo(permissions1);
    }

    @Test
    public void TestDeletePermissionById() throws PermissionNotFoundException {
        Set<Role> roleSet = null;
        // given
        Permission permission1 = new Permission((long) 423432, "fanie",roleSet, true, new Date(), "fanie", new Date(),"fanie");
        Permission permission2 = new Permission((long) 986459, "cindy",roleSet, true, new Date(), "cindy", new Date(),"cindy");
        Permission permission3 = new Permission((long) 543298, "kiki",roleSet, true, new Date(), "kiki", new Date(),"kiki");

        ArrayList<Permission> permissions = new ArrayList<Permission>();
        permissions.add(permission1);
        permissions.add(permission2);
        permissions.add(permission3);

        when(permissionRepository.findById((long) 423432)).thenReturn(Optional.of(permission1));
        // when
        int permissions1 = permissionService.deletePermissionByIdTest((long) 423432);
        // then
        assertThat(permissions1).isEqualTo(1);
    }

    @Test
    public void TestAddPermission() throws PermissionNotFoundException {
        Set<Role> roleSet = null;
        // given
        Permission permission1 = new Permission((long) 423432, "fanie",roleSet, true, new Date(), "fanie", new Date(),"fanie");
        Permission permission2 = new Permission((long) 986459, "cindy",roleSet, true, new Date(), "cindy", new Date(),"cindy");
        Permission permission3 = new Permission((long) 543298, "kiki",roleSet, true, new Date(), "kiki", new Date(),"kiki");

        ArrayList<Permission> permissions = new ArrayList<Permission>();
        permissions.add(permission1);
        permissions.add(permission2);
        permissions.add(permission3);

        when(permissionRepository.save(permission1)).thenReturn(permission1);
        // when
        Permission permissions1 = permissionService.addPermission(permission1);
        // then
        assertThat(permissions1).isEqualTo(permission1);
    }

    @Test
    public void TestUpdatePermission() throws PermissionNotFoundException {
        Set<Role> roleSet = null;
        // given
        Permission permission1 = new Permission((long) 423432, "fanie",roleSet, true, new Date(), "fanie", new Date(),"fanie");
        Permission permission2 = new Permission((long) 986459, "cindy",roleSet, true, new Date(), "cindy", new Date(),"cindy");
        Permission permission3 = new Permission((long) 543298, "kiki",roleSet, true, new Date(), "kiki", new Date(),"kiki");

        ArrayList<Permission> permissions = new ArrayList<Permission>();
        permissions.add(permission1);
        permissions.add(permission2);
        permissions.add(permission3);

        when(permissionRepository.findById((long) 423432)).thenReturn(Optional.of(permission1));
        when(permissionRepository.save(permission1)).thenReturn(permission1);
        // when
        Permission permissions1 = permissionService.updatePermission((long) 423432,permission1);
        // then
        assertThat(permissions1).isEqualTo(permission1);
    }

    @Test
    public void TestUpdateNameOfPermission() throws PermissionNotFoundException {
        Set<Role> roleSet = null;
        // given
        Permission permission1 = new Permission((long) 423432, "fanie",roleSet, true, new Date(), "fanie", new Date(),"fanie");
        Permission permission2 = new Permission((long) 986459, "cindy",roleSet, true, new Date(), "cindy", new Date(),"cindy");
        Permission permission3 = new Permission((long) 543298, "kiki",roleSet, true, new Date(), "kiki", new Date(),"kiki");
        ArrayList<Permission> permissions = new ArrayList<Permission>();
        permissions.add(permission1);
        permissions.add(permission2);
        permissions.add(permission3);
        when(permissionRepository.findById((long) 423432)).thenReturn(Optional.of(permission1));
        when(permissionRepository.save(permission1)).thenReturn(permission1);
        // when
        Permission permissions1 = permissionService.updateNameofPermission((long) 423432,"hello");
        // then
        assertThat(permissions1).isEqualTo(permission1);
    }
    @Test
    public void TestAddRoleForPermission() throws PermissionNotFoundException, RoleNotFoundException, RoleNotFoundException {
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
        Permission permission1 = new Permission((long) 423432, "fanie",roles, true, new Date(), "fanie", new Date(),"fanie");
        Permission permission2 = new Permission((long) 986459, "cindy",roles, true, new Date(), "cindy", new Date(),"cindy");
        Permission permission3 = new Permission((long) 543298, "kiki",roles, true, new Date(), "kiki", new Date(),"kiki");
        ArrayList<Permission> permissions = new ArrayList<Permission>();
        permissions.add(permission1);
        permissions.add(permission2);
        permissions.add(permission3);
        when(permissionRepository.findById((long) 423432)).thenReturn(Optional.of(permission1));
        when(roleRepository.findById((long) 123456)).thenReturn(Optional.of(role1));
        when(permissionRepository.save(permission1)).thenReturn(permission1);
        // when
        Role roles1 = permissionService.addRoleForPermission((long) 423432,(long) 123456);
        // then
        assertThat(roles1).isEqualTo(role1);
    }
}