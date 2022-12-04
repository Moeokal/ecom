package com.moe.admin.user;

import com.moe.ecomcommon.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
