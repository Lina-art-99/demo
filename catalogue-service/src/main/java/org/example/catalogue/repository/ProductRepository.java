package org.example.catalogue.repository;

import org.example.catalogue.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    //HQL
    // @Query(value = "select p from Product p where p.details ilike :filter")
    // List<Product> findAllByDetailsLikeIgnoreCase(@Param("filter") String filter);

    //нативный запрос(SQL)
   //  @Query(value = "select * from catalogue.t_product  where c_details ilike :filter",nativeQuery = true)
   // List<Product> findAllByDetailsLikeIgnoreCase(@Param("filter") String filter);

    //hql пишем в классе
    //  @Query(name = "Product.findAllByDetailsLikeIgnoreCase", nativeQuery = true)
    // List<Product> findAllByDetailsLikeIgnoreCase(@Param("filter") String filter);
    List<Product> findAllByDetailsLikeIgnoreCase(String filter);


}
