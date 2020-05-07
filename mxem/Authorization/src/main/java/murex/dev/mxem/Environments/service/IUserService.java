package murex.dev.mxem.Environments.service;

import murex.dev.mxem.Environments.exception.RoleNotFoundException;
import murex.dev.mxem.Environments.exception.UserNotFoundException;
import murex.dev.mxem.Environments.model.Role;
import murex.dev.mxem.Environments.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IUserService {

    public List<User> findAllUsers();

    public Optional<User> findUserById(Long id) throws UserNotFoundException;

    public User findUserByName(String id) throws UserNotFoundException;

    public Set<Role> findRolesForUser(String id) throws UserNotFoundException;

    public void deleteAllUsers();

    public void deleteUserById(Long id) throws UserNotFoundException;

    public void deleteRolesForUser(Long id) throws UserNotFoundException;

    public Role addRoleForUser(Long userId, Long roleId) throws UserNotFoundException, RoleNotFoundException;

    public User addUser(User user);

    public User updateUser(Long id, User user) throws UserNotFoundException;

    public User updateNameofUser(Long id, String name) throws UserNotFoundException;

    public int deleteAllUsersTest();

    public int deleteUserByIdTest(Long id) throws UserNotFoundException;

    public int deleteRolesForUserTest(Long id) throws UserNotFoundException;

    public int TestDeleteAllUsers();

    }