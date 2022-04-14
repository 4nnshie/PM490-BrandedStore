package com.pm490.PM490.repository;

import com.pm490.PM490.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {
        @Query(value = "SELECT acc FROM Account acc WHERE acc.user.id = :userId")
        List<Account> findByUserId(@Param("userId") long userId);
}
