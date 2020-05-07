package murex.dev.mxem.Environments.controller;

import lombok.extern.slf4j.Slf4j;
import murex.dev.mxem.Environments.exception.PermissionNotFoundException;
import murex.dev.mxem.Environments.exception.RoleNotFoundException;
import murex.dev.mxem.Environments.exception.UserNotFoundException;
import murex.dev.mxem.Environments.model.Permission;
import murex.dev.mxem.Environments.model.Role;
import murex.dev.mxem.Environments.model.User;
import murex.dev.mxem.Environments.service.AuthorizationService;
import murex.dev.mxem.Environments.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin( maxAge = 3600)
@RestController
@Slf4j
@Validated
@RefreshScope
@RequestMapping(value = "/roles")
public class RoleController {
    @Autowired
    RoleService roleService;

    @Autowired
    AuthorizationService authorizationService;

    @GetMapping //done
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.findAllRoles();
        return ResponseEntity.ok(roles);
    }

    //all methods should return a response entity
    @GetMapping("/{id}")   //done
    public ResponseEntity<Role> getRoleDetails(@PathVariable String id) {
        Role role = roleService.findRoleByName(id);
        return ResponseEntity.ok(role);

    }

    @GetMapping("/{id}/permissions") //done
    public ResponseEntity<Set<Permission>> getPermissionsForRole(@PathVariable String id) {
        try{
            return (ResponseEntity.ok(roleService.findPermissionsForRole(id)));}
        catch(RoleNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{id}/users")  //done
    public ResponseEntity<Set<User>> getUsersForRole(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(roleService.findUsersForRole(id));}
        catch(RoleNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity deleteAllRoles(){
        roleService.deleteAllRoles();
        log.info("Delete all roles");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /////////////////////////////////////////
    @DeleteMapping(path="789")
    public ResponseEntity<Integer>deleteAllRolesForTest(){
        Integer rolesDeleted = roleService.deleteAllRolesForTest();
        log.info("Delete all roles");
        return ResponseEntity.ok(rolesDeleted);
    }
    /////////////////////////////////////////

    @DeleteMapping(path="/{roleId}")
    public ResponseEntity deleteRoleById(@PathVariable String roleId)  {
        try{
            roleService.deleteRoleById(roleId);
            log.info("Delete role of ID {"+roleId+"}");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (RoleNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    /////////////////////////////////////////
    @DeleteMapping(path="647")
    public ResponseEntity<Integer> deleteRoleByIdForTest(@PathVariable String roleid)  {
        try{
            Integer roleIdDeleted= roleService.deleteRoleByIdForTest(roleid);
            log.info("Delete role of ID {"+roleid+"}");
            return ResponseEntity.ok(roleIdDeleted);
        }
        catch (RoleNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    /////////////////////////////////////////



    @DeleteMapping(path="/{roleId}/permissions")
    public ResponseEntity deletePermissionsForRole(@PathVariable Long roleId)  {
        try{
            roleService.deletePermissionsForRole(roleId);
            log.info("Delete permissions' role of ID {"+roleId+"}");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (RoleNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }


    /////////////////////////////////////////
    @DeleteMapping(path="2344")
    public ResponseEntity<Integer> deletePermissionsForRoleForTest(@PathVariable Long roleId)  {
        try{
            Integer deletedPerm= roleService.deletePermissionsForRoleForTest(roleId);
            log.info("Delete permissions' role of ID {"+roleId+"}");
            return ResponseEntity.ok(deletedPerm);
        }
        catch (RoleNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }
    /////////////////////////////////////////


    @DeleteMapping(path="/{roleId}/users")
    public ResponseEntity deleteUsersForRole(@PathVariable Long roleId)  {
        try{
            roleService.deleteUsersForRole(roleId);
            log.info("Delete users' role of ID {"+roleId+"}");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (RoleNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }

    /////////////////////////////////////////
    @DeleteMapping(path="1234")
    public ResponseEntity<Integer> deleteUsersForRoleForTest(@PathVariable Long roleId)  {
        try{
            Integer deletedUser = roleService.deleteUsersForRoleForTest(roleId);
            log.info("Delete users' role of ID {"+roleId+"}");
            return ResponseEntity.ok(deletedUser);
        }
        catch (RoleNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    /////////////////////////////////////////



//    @PostMapping
//    public ResponseEntity<Void> addRole(@Valid @RequestBody Role role, UriComponentsBuilder builder, @RequestHeader("Authorization") String token){
//        role.updateOnCreation(authorizationService.getUsernameFromToken(token));
//        roleService.addRole(role);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(builder.path("/roles/{id}").buildAndExpand(role.getId()).toUri());
//        return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<Void> addRoleForTesting(@Valid @RequestBody Role role){
        //role.updateOnCreation(authorizationService.getUsernameFromToken(token));
        roleService.addRole(role);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping(path="/{roleId}") //done
    public ResponseEntity<Role> updateRole(@PathVariable String roleId, @RequestBody Role role) throws RoleNotFoundException {
        try {
            roleService.updateRole(Long.parseLong(roleId), role);
            return ResponseEntity.ok(role);
        }catch(RoleNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @PatchMapping(path="/{roleId}")
    public ResponseEntity<Role> updateNameOfRole(@PathVariable Long roleId, @RequestBody String name) throws RoleNotFoundException {
        try {
            roleService.updateNameofRole(roleId,name);
            Optional<Role>role=roleService.findRoleById(roleId);
            return ResponseEntity.ok(role.get());
        }catch(RoleNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @PostMapping("/{Role_id}/users/{User_id}")
    public ResponseEntity<Set<User>> addUserforRole(@PathVariable String Role_id, @PathVariable String User_id) throws RoleNotFoundException, UserNotFoundException {
        try {
            roleService.addUserForRole(Long.parseLong(Role_id), Long.parseLong(User_id));
            log.info("Adding a user for role # "+Role_id);
            return getUsersForRole(Long.parseLong(Role_id));
        }
        catch(RoleNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        catch(UserNotFoundException e1){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e1.getMessage());
        }
    }

    @PostMapping("/{Role_id}/permissions/{Permission_Id}")
    public ResponseEntity<Set<Permission>> addPermissionforRole(@PathVariable String Role_id, @PathVariable String Permission_Id) throws RoleNotFoundException, PermissionNotFoundException {
        try {
            roleService.addPermissionForRole(Long.parseLong(Role_id), Long.parseLong(Permission_Id));
            log.info("Adding a permission for role # "+Role_id);
            return getPermissionsForRole(Role_id);
        }
        catch(RoleNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        catch(PermissionNotFoundException e1){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e1.getMessage());
        }
    }
}
