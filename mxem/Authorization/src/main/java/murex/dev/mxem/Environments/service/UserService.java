package murex.dev.mxem.Environments.service;

import lombok.extern.slf4j.Slf4j;
import murex.dev.mxem.Environments.exception.RoleNotFoundException;
import murex.dev.mxem.Environments.exception.UserNotFoundException;
import murex.dev.mxem.Environments.model.Permission;
import murex.dev.mxem.Environments.model.Role;
import murex.dev.mxem.Environments.model.RolesPermissions;
import murex.dev.mxem.Environments.model.User;
import murex.dev.mxem.Environments.repository.RoleRepository;
import murex.dev.mxem.Environments.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<User> findAllUsers(){
        List<User> users = (List<User>) userRepository.findAll();
        return users;
    }

    @Override
    public Optional<User> findUserById(Long id) throws UserNotFoundException {
        Optional<User> user= userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public User findUserByName(String id) throws UserNotFoundException {
        List<User> user= userRepository.findByName(id);
        if(user.size()==0){
            throw new UserNotFoundException();
        }
        return user.get(0);
    }

    @Override
    public Set<Role> findRolesForUser(String id) throws UserNotFoundException {
        return findUserByName(id).getRoles();
    }


    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    public void deleteUserById(Long id) throws UserNotFoundException {
        Optional<User> result = userRepository.findById(id);
        if (!result.isPresent()) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
    }

    @Override
    public void deleteRolesForUser(Long id) throws UserNotFoundException {
        User user=findUserById(id).get();
        user.setRoles(Collections.EMPTY_SET);
        userRepository.save(user);
    }

    @Override
    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public Role addRoleForUser(Long userId, Long roleId) throws UserNotFoundException, RoleNotFoundException {
        Optional<User> userInTable = userRepository.findById(userId);
        if (!userInTable.isPresent()) {
            throw new UserNotFoundException();
        }
        Optional<Role> roleInTable = roleRepository.findById(roleId);
        if (!roleInTable.isPresent()) {
            throw new RoleNotFoundException();
        }
        userInTable.get().getRoles().add(roleInTable.get());
        userRepository.save(userInTable.get());
        return roleInTable.get();
    }

    @Override
    public User updateUser(Long id, User user) throws UserNotFoundException {
        Optional<User> userInTable = userRepository.findById(id);
        if (!userInTable.isPresent()) {
            throw new UserNotFoundException();
        }
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public User updateNameofUser(Long id, String name) throws UserNotFoundException {
        User user =findUserById(id).get();
        user.setName(name);
        return userRepository.save(user);
    }

    public RolesPermissions getRolesPermissions(String username){
        Set<String> permNames =  new HashSet<String>();
        Set<String> roleNames = new HashSet<String>();
        log.info("CEST LE USERNAME : "+username);
        User user = userRepository.findByName(username).get(0);
        for(Role role : user.getRoles()){
            roleNames.add(role.getName());
            for(Permission perm : role.getPermissions() ){
                permNames.add(perm.getName());
            }
        }
        RolesPermissions res = new RolesPermissions();
        res.setPermissions(permNames);
        res.setRoles(roleNames);
        return(res);
    }

    @Override
    public int TestDeleteAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        userRepository.deleteAll();
        for (int i =0 ; i<users.size();++i){
            users.get(i).setIsDeleted(true);
        }
        return 1;
    }

    @Override
    public int deleteAllUsersTest(){
        userRepository.deleteAll();
        return 1;
    }

    @Override
    public int deleteUserByIdTest(Long id) throws UserNotFoundException {
        Optional<User> result = userRepository.findById(id);
        if (!result.isPresent()) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
        return 1;
    }

    @Override
    public int deleteRolesForUserTest(Long id) throws UserNotFoundException {
        User user=findUserById(id).get();
        user.setRoles(Collections.EMPTY_SET);
        userRepository.save(user);
        return 1;
    }
}