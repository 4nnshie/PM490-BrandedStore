package com.pm490.PM490.repository;

import com.pm490.PM490.model.Card;
import com.pm490.PM490.model.TransactionHist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface TransactionHistRepository extends JpaRepository<TransactionHist, Long> {

}