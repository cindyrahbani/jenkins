package murex.dev.mxem.Environments.service;

import murex.dev.mxem.Environments.exception.PermissionNotFoundException;
import murex.dev.mxem.Environments.exception.RoleNotFoundException;
import murex.dev.mxem.Environments.model.Permission;
import murex.dev.mxem.Environments.model.Role;
import murex.dev.mxem.Environments.repository.PermissionRepository;
import murex.dev.mxem.Environments.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PermissionService implements IPermissionService {
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Permission> findAllPermissions() {
        List<Permission> permissions = (List<Permission>) permissionRepository.findAll();
        return permissions;
    }

    @Override
    public Optional<Permission> findPermissionById(Long id) throws PermissionNotFoundException {
        Optional<Permission> permission = permissionRepository.findById(id);
        if (!permission.isPresent()) {
            throw new PermissionNotFoundException();
        }
        return permission;
    }

    @Override
    public Set<Role> findRolesForPermission(Long id) throws PermissionNotFoundException {
        return findPermissionById(id).get().getRoles();
    }

    @Override
    public void deleteAllPermissions() {
        permissionRepository.deleteAll();
    }

    @Override
    public void deletePermissionById(Long id) throws PermissionNotFoundException {
        Optional<Permission> result = permissionRepository.findById(id);
        if (!result.isPresent()) {
            throw new PermissionNotFoundException();
        }
        permissionRepository.deleteById(id);
    }

    @Override
    public Permission addPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission updatePermission(Long id, Permission permission) throws PermissionNotFoundException {
        Optional<Permission> permissionInTable = permissionRepository.findById(id);
        if (!permissionInTable.isPresent()) {
            throw new PermissionNotFoundException();
        }
        permission.setId(id);
        return permissionRepository.save(permission);
    }

    @Override
    public Permission updateNameofPermission(Long id, String name) throws PermissionNotFoundException {
        Permission permission =findPermissionById(id).get();
        permission.setName(name);
        return permissionRepository.save(permission);
    }

    @Override
    public Role addRoleForPermission(Long permissionId, Long roleId) throws PermissionNotFoundException, RoleNotFoundException {
        Optional<Permission> permissionInTable = permissionRepository.findById(permissionId);
        if (!permissionInTable.isPresent()) {
            throw new PermissionNotFoundException();
        }
        Optional<Role> roleInTable = roleRepository.findById(roleId);
        if (!roleInTable.isPresent()) {
            throw new RoleNotFoundException();
        }
        permissionInTable.get().getRoles().add(roleInTable.get());
        permissionRepository.save(permissionInTable.get());
        return roleInTable.get();
    }

    @Override
    public int TestDeleteAllPermissions() {
        List<Permission> permissions = (List<Permission>) permissionRepository.findAll();
        permissionRepository.deleteAll();
        for (int i =0 ; i<permissions.size();++i){
            permissions.get(i).setIsDeleted(true);
        }
        return 1;
    }

    @Override
    public int deleteAllPermissionsTest(){
        permissionRepository.deleteAll();
        return 1;
    }

    @Override
    public int deletePermissionByIdTest(Long id) throws PermissionNotFoundException {
        Optional<Permission> result = permissionRepository.findById(id);
        if (!result.isPresent()) {
            throw new PermissionNotFoundException();
        }
        permissionRepository.deleteById(id);
        return 1;
    }
}