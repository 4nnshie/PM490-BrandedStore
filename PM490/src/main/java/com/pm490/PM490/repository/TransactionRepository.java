package com.pm490.PM490.repository;

import com.pm490.PM490.model.ItemList;
import com.pm490.PM490.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
