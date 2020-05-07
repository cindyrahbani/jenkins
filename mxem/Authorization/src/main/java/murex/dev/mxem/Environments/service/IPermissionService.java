package murex.dev.mxem.Environments.service;





import murex.dev.mxem.Environments.exception.PermissionNotFoundException;
import murex.dev.mxem.Environments.exception.RoleNotFoundException;
import murex.dev.mxem.Environments.model.Permission;
import murex.dev.mxem.Environments.model.Role;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IPermissionService {

    public List<Permission> findAllPermissions();
    public Optional<Permission> findPermissionById(Long id) throws PermissionNotFoundException;
    public void deleteAllPermissions();
    public void deletePermissionById(Long id) throws PermissionNotFoundException;
    public Set<Role> findRolesForPermission(Long id) throws PermissionNotFoundException;
    public Permission updatePermission(Long id, Permission permission) throws PermissionNotFoundException;
    public Permission updateNameofPermission(Long id, String name) throws PermissionNotFoundException;
    public Permission addPermission(Permission permission);
    public Role addRoleForPermission(Long permissionId, Long roleId) throws PermissionNotFoundException, RoleNotFoundException;
    public int deleteAllPermissionsTest();
    public int deletePermissionByIdTest(Long id) throws PermissionNotFoundException;
    public int TestDeleteAllPermissions();
}
