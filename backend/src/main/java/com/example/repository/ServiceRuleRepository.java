package com.example.repository;

import com.example.model.ServiceRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRuleRepository extends JpaRepository<ServiceRule, Long> {
    List<ServiceRule> findByUserRole(String userRole);
    List<ServiceRule> findByRuleType(String ruleType);
}
