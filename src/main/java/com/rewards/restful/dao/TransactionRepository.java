
package com.rewards.restful.dao;

import com.rewards.restful.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {


}
