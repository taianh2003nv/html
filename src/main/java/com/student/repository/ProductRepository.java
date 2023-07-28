package com.student.repository;

import com.student.model.Product;
import com.student.model.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByCid(int cid);
    List<Product> findAllByPname(String pname);
    @Modifying
    @Transactional
    @Query(value = "insert into product(pid,cid,pname) values(:pid,:cid,:pname) ",nativeQuery = true)
    void insertProduct( @Param("pid") int pid, @Param("cid") int cid,@Param("pname") String pname);
    @Query(value = "SELECT p.* , c.cname from product as p , category as c where c.cid=p.cid",nativeQuery = true)
    List<Map<String, Objects>> findInfor();
    @Query(value = "SELECT p.* , c.cname from product as p , category as c where c.cid = p.cid and p.pid=?1",nativeQuery = true)
   Map<String, Objects> findProductDtoByPId(int pid);
    @Query(value = "SELECT p.* , c.cname from product as p , category as c where c.cid = p.cid and p.pid=?2 and c.cid=?1",nativeQuery = true)
    Map<String, Objects> findProductDtoByPIdAndCid(int pid,int cid);
    @Query(value = "SELECT p.* , c.cname from product as p , category as c where c.cid = p.cid and p.pid=:pid and c.cid=:cid",nativeQuery = true)
    Map<String, Objects> findProductDtoByPIdAndCid2(@Param("pid")int sasadas,@Param("cid") int cid);
}
