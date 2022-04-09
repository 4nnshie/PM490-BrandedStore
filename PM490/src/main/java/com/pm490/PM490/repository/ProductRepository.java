// Author Angie
package com.pm490.PM490.repository;

import com.pm490.PM490.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("select p from Product p where p.name like %:searchPro%")
    List<Product> searchProduct(@Param("searchPro") String searchPro);

    @Query("select p from Product p where p.name like %:searchPro% and p.color=:col and p.vendor=:idVend and p.category=:idCat")
    List<Product> searchProductAdvanced(@Param("searchPro") String searchPro, @Param("col") String col,  @Param("idVend") long idVendor, @Param("idCat") long idCategory);
}
