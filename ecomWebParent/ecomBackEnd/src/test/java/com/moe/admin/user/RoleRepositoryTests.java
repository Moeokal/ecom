package com.moe.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.moe.ecomcommon.entity.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository repo;

    @Test
    public void testCreateAdminRole() {
        Role roleAdmin = new Role("Admin", "manage everything");
        Role savedRole = repo.save(roleAdmin);

        assertThat(savedRole.getId()).isGreaterThan(0);
    }

    @ParameterizedTest
    @CsvSource(value = {"Salesperson:manage product price, customers, shipping, orders and sales reports:2",
            "Editor:manage categories, brands, products, articles and menus:3",
            "Shipper:view products, orders and update order status:4",
            "Assistant:manage questions and reviews:5"},
            delimiter = ':')
    public void testCreateRestRole(String name, String description, String id) {
        Role role = new Role(name, description);
        Role savedRole = repo.save(role);

        assertThat(savedRole.getId()).isEqualTo(Integer.parseInt(id));
    }
}
