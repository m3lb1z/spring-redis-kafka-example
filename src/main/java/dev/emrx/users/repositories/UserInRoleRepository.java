package dev.emrx.users.repositories;

import dev.emrx.users.entities.User;
import dev.emrx.users.entities.UserInRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserInRoleRepository extends JpaRepository<UserInRole, Integer> {

    @Query("SELECT uir.user FROM UserInRole uir WHERE uir.role.id = :roleId")
    List<User> findAllUsersInRole(Integer roleId);

    @Query("SELECT uir.user FROM UserInRole uir WHERE uir.role.name = :roleName")
    List<User> findAllUsersByRoleName(String roleName);

    List<UserInRole> findByUser(User user);
}
