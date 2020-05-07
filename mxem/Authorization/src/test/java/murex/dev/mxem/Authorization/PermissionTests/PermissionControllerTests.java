package murex.dev.mxem.Authorization.PermissionTests;

import murex.dev.mxem.Environments.controller.PermissionController;
import murex.dev.mxem.Environments.exception.PermissionNotFoundException;
import murex.dev.mxem.Environments.exception.RoleNotFoundException;
import murex.dev.mxem.Environments.exception.UserNotFoundException;
import murex.dev.mxem.Environments.model.Permission;
import murex.dev.mxem.Environments.model.Role;
import murex.dev.mxem.Environments.model.User;
import murex.dev.mxem.Environments.service.PermissionService;
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
public class PermissionControllerTests {
    @InjectMocks
    PermissionController permissionController;

    @Mock
    PermissionService permissionService;

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

        when(permissionService.findAllPermissions()).thenReturn(permissions);
        // when
        ResponseEntity<List<Permission>> permissions1 = permissionController.getAllPermissions();
        // then
        assertThat(permissions1.getStatusCodeValue()).isEqualTo(200);
    }
    @Test
    public void testGetPermissionDetails() throws PermissionNotFoundException
    {
        Set<Role> roleSet = null;
        // given
        Permission permission1 = new Permission((long) 423432, "fanie",roleSet, true, new Date(), "fanie", new Date(),"fanie");

        when(permissionService.findPermissionById((long) 423432)).thenReturn(java.util.Optional.of(permission1));
        // when
        ResponseEntity<Permission> perm1 = permissionController.getPermissionDetails((long)423432);
        // then
        assertThat(perm1.getStatusCodeValue()).isEqualTo(200);
    }
    @Test
    public void testGetRolesForPermission() throws PermissionNotFoundException
    {
        Set<Role> roleSet = null;
        // given
        Permission permission1 = new Permission((long) 423432, "fanie", roleSet, true, new Date(), "fanie", new Date(),"fanie");

        when(permissionService.findRolesForPermission((long) 423432)).thenReturn(roleSet);
        // when
        ResponseEntity<Set<Role>> roles = permissionController.getRolesForPermissions((long)423432);
        // then
        assertThat(roles.getStatusCodeValue()).isEqualTo(200);
    }
    @Test
    public void testAddRoleForPermission() throws PermissionNotFoundException, RoleNotFoundException
    {
        Set<Role> roleSet = null;
        // given
        Permission permission1 = new Permission((long) 423432, "fanie", roleSet, true, new Date(), "fanie", new Date(),"fanie");
        Role role1 = new Role((long)42342,"cindy",true, new Date(),"cindy",new Date(),"cindy");

        when(permissionService.addRoleForPermission((long) 423432, (long)42342)).thenReturn(role1);
        // when
        ResponseEntity<Set<Role>> roles = permissionController.addRoleForPermission("423432","42342");
        // then
        assertThat(roles.getStatusCodeValue()).isEqualTo(200);
    }
    @Test
    public void testAddPermissionTest()
    {
        Set<Role> roleSet = null;
        // given
        Permission permission1 = new Permission((long) 423432, "fanie", roleSet, true, new Date(), "fanie", new Date(),"fanie");

        when(permissionService.addPermission(permission1)).thenReturn(permission1);

        // when
        ResponseEntity<Permission> perm = permissionController.addPermissiontest(permission1);
        // then
        assertThat(perm.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testUpdatePermissionTest() throws UserNotFoundException, PermissionNotFoundException {
        Set<Role> roleSet = null;
        // given
        Permission permission1 = new Permission((long) 423432, "fanie", roleSet, true, new Date(), "fanie", new Date(),"fanie");

        when(permissionService.updatePermission((long) 75093209, permission1)).thenReturn(permission1);
        // when
        ResponseEntity<Permission> perm = permissionController.updatePermissionTest("75093209", permission1);
        // then
        assertThat(perm.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testUpdateNameOfPermissionTest() throws UserNotFoundException, PermissionNotFoundException {
        Set<Role> roleSet = null;
        // given
        Permission permission1 = new Permission((long) 423432, "cindy", roleSet, true, new Date(), "fanie", new Date(),"fanie");

        when(permissionService.findPermissionById((long) 423432)).thenReturn(java.util.Optional.of(permission1));
        when(permissionService.updateNameofPermission(permission1.getId(), "cindy")).thenReturn(permission1);

        // when
        ResponseEntity<Permission> perm = permissionController.updateNameOfPermissionTest(permission1.getId(), "cindy");
        // then
        assertThat(perm.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testDeleteAllUsersTest()
    {
        Set<Role> roleSet1 = null;
        // given
        when(permissionService.deleteAllPermissionsTest()).thenReturn(1);

        // when
        ResponseEntity<Integer> permission = permissionController.deleteAllPermissionsTest();
        // then
        assertThat(permission.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testDeleteUserByIdTest() throws PermissionNotFoundException {
        Set<Role> roleSet1 = null;
        User user1 = new User((long) 75093209,"cindy",roleSet1,true, new Date(),"cindy", new Date(),"cindy");
        // given
        when(permissionService.deletePermissionByIdTest((long)75093209)).thenReturn(1);

        // when
        ResponseEntity<Integer> perm = permissionController.deletePermissionByIdTest((long)75093209);
        // then
        assertThat(perm.getStatusCodeValue()).isEqualTo(200);
    }
}