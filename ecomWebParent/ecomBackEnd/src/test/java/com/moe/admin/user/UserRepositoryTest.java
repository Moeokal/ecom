package com.moe.admin.user;

import com.moe.ecomcommon.entity.Role;
import com.moe.ecomcommon.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUserTable() {
    }

    @Test
    public void testCreateUserWithAdminRole() {
        Role roleAdmin = entityManager.find(Role.class, 1);
        User userMoe = new User("moe@gmail.com", "123456","Moayad", "Okal");
        userMoe.addRole(roleAdmin);
        User savedRole = repo.save(userMoe);

        assertThat(savedRole.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateUserWithTwoRoles() {
        User userHaleem = new User("Haleem@gmail.com", "123456","Haleem", "Haleem");
        Role roleEditor = new Role(3);
        Role roleAssistance = new Role(5);

        userHaleem.addRole(roleEditor);
        userHaleem.addRole(roleAssistance);

        User savedRole = repo.save(userHaleem);

        assertThat(savedRole.getId()).isGreaterThan(0);
    }

    @Test
    public void listAllUsersTest(){
        Iterable<User> listUsers = repo.findAll();
        listUsers.forEach(user -> System.out.println(user));
    }

    @Test
    public void getUserByIdTest(){
        User userMoe = repo.findById(1).get();
        System.out.println(userMoe);
        assertThat(userMoe).isNotNull();
    }

    @Test
    public void updateUserDetailsTest(){
        User userMoe = repo.findById(1).get();
        userMoe.setEnabled(true);
        userMoe.setEmail("mo@gmail.com");
        repo.save(userMoe);
    }

    @Test
    public void updateUserRolesTest(){
        User userHaleem = repo.findById(2).get();
        Role roleEditor = new Role(3);
        Role roleSalesperson = new Role(2);

        userHaleem.getRoles().remove(roleEditor);
        userHaleem.addRole(roleSalesperson);

        repo.save(userHaleem);
    }

    @Test
    public void deleteUserTest(){
        Integer id = 2;
        repo.deleteById(id);
    }
}
