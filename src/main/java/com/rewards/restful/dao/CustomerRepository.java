
package com.rewards.restful.dao;

import com.rewards.restful.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    List<CustomerEntity> findByAgeBetween(int from, int to);


}
