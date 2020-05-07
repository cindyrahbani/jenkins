package murex.dev.mxem.Environments.service;

import murex.dev.mxem.Environments.exception.PermissionNotFoundException;
import murex.dev.mxem.Environments.exception.RoleNotFoundException;
import murex.dev.mxem.Environments.exception.UserNotFoundException;
import murex.dev.mxem.Environments.model.Permission;
import murex.dev.mxem.Environments.model.Role;
import murex.dev.mxem.Environments.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IRoleService {

    public List<Role> findAllRoles();
    public Optional<Role> findRoleById(Long id) throws RoleNotFoundException;
    public Set<Permission> findPermissionsForRole(String id) throws RoleNotFoundException;
    public Set<User> findUsersForRole(Long id) throws RoleNotFoundException;
    public void deleteAllRoles();
    public void deleteRoleById(String id) throws RoleNotFoundException;
    public void deletePermissionsForRole(Long id) throws RoleNotFoundException;
    public void deleteUsersForRole(Long id) throws RoleNotFoundException;
    public Role addRole(Role role);
    public Role updateRole(Long id, Role role) throws RoleNotFoundException;
    public Role updateNameofRole(Long id, String name) throws RoleNotFoundException;
    public User addUserForRole(Long roleId, Long userId) throws RoleNotFoundException, UserNotFoundException;
    public Permission addPermissionForRole(Long roleId, Long permissionId) throws RoleNotFoundException, PermissionNotFoundException;
}
