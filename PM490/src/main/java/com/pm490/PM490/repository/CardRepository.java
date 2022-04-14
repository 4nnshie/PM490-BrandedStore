package com.pm490.PM490.repository;

import com.pm490.PM490.model.Account;
import com.pm490.PM490.model.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CardRepository extends JpaRepository<Card, Long> {
    @Query("SELECT c FROM Card c WHERE c.user.id = :userId")
    //Optional<Card> findById(String cardNumber);
    List<Card> findByUserId(@Param("userId") long userId);
}

