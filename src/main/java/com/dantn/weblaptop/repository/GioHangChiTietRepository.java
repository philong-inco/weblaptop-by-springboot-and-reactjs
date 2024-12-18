package com.dantn.weblaptop.repository;

import com.dantn.weblaptop.entity.giohang.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Long> {

    List<GioHangChiTiet> getGioHangChiTietByGioHangId(Long idCart);

    @Transactional
    @Modifying
    @Query(value = "DELETE ghct\n" +
            "FROM gio_hang_chi_tiet ghct\n" +
            "JOIN gio_hang gh ON ghct.gio_hang_id = gh.id\n" +
            "JOIN khach_hang kh ON gh.id_khach_hang = kh.id\n" +
            "WHERE kh.id = :idKhachHang", nativeQuery = true)
    void deleteAllCart(@Param("idKhachHang") Long idKhachHang);

    @Transactional
    @Modifying
    @Query(value = "" +
            "DELETE ghct\n" +
            "FROM gio_hang_chi_tiet ghct\n" +
            "JOIN gio_hang gh ON ghct.gio_hang_id = gh.id\n" +
            "where gh.session_id = :sessionId", nativeQuery = true)
    void deleteAllCartBySessionId(@Param("sessionId") String sessionId);

    @Query(value = "SELECT ghct.* FROM gio_hang_chi_tiet AS ghct " +
            "JOIN gio_hang AS gh ON ghct.gio_hang_id = gh.id " +
            "WHERE gh.id = :cartId AND ghct.san_pham_chi_tiet_id = :productDetailId", nativeQuery = true)
    Optional<GioHangChiTiet> getCartDetailByCartIdAndProductDetailId(@Param("cartId") Long cartId, @Param("productDetailId") Long productDetailId);


}
