// Author Angie
package com.pm490.PM490.repository;

import com.pm490.PM490.model.Category;
import com.pm490.PM490.model.Product;
import com.pm490.PM490.model.ProductStatus;
import com.pm490.PM490.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("select p from Product p where p.status =:status")
    List<Product> findAllStatus(@Param("status") ProductStatus status);

    @Query("select p from Product p where p.vendor =:vendor")
    List<Product> findAllByVendor(@Param("vendor") User vendor);

    @Query("select p from Product p where p.name like %:searchPro% ")
    List<Product> searchProductAdvanced(@Param("searchPro") String searchPro);

    //color*
    @Query("select p from Product p where p.name like %:searchPro% and p.color=:col ")
    List<Product> searchProductAdvanced(@Param("searchPro") String searchPro, @Param("col") String col);

    //vendor*
    @Query("select p from Product p where p.name like %:searchPro% and p.vendor=:vendor ")
    List<Product> searchProductAdvanced(@Param("searchPro") String searchPro, @Param("vendor") User vendor);

    //category*
    @Query("select p from Product p where p.name like %:searchPro% and p.category=:idCat ")
    List<Product> searchProductAdvanced(@Param("searchPro") String searchPro, @Param("idCat") Category idCategory);

    //color vendor*
    @Query("select p from Product p where p.name like %:searchPro% and p.color=:col and p.vendor=:vendor")
    List<Product> searchProductAdvanced(@Param("searchPro") String searchPro, @Param("col") String col,  @Param("vendor") User vendor);

    //color category*
    @Query("select p from Product p where p.name like %:searchPro% and p.color=:col and p.category=:idCat")
    List<Product> searchProductAdvanced(@Param("searchPro") String searchPro, @Param("col") String col,  @Param("idCat") Category idCategory);

    //vendor category*
    @Query("select p from Product p where p.name like %:searchPro% and p.vendor=:vendor  and p.category=:idCat")
    List<Product> searchProductAdvanced(@Param("searchPro") String searchPro, @Param("vendor") User vendor,  @Param("idCat") Category idCategory);

    //color vendor category*
    @Query("select p from Product p where p.name like %:searchPro% and p.color=:col and p.vendor=:vendor and p.category=:idCat")
    List<Product> searchProductAdvanced(@Param("searchPro") String searchPro, @Param("col") String col,  @Param("vendor") User vendor, @Param("idCat") Category idCategory);


}
