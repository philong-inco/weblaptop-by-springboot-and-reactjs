package com.dantn.weblaptop.repository;

import com.dantn.weblaptop.entity.sanpham.SerialNumber;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SerialNumberRepository extends JpaRepository<SerialNumber, Long> {


    @Query("SELECT s FROM SerialNumber s")
    List<SerialNumber> getAllList();

    @Modifying
    @Transactional
    @Query("DELETE FROM SerialNumber s WHERE s.id = :id")
    void deleteByIdSeri(@Param("id") Long id);

    @Query("SELECT s FROM SerialNumber s WHERE s.trangThai = 0 AND s.sanPhamChiTiet.sanPham.id = :idProduct")
    List<SerialNumber> findAllByProductIdActive(@Param("idProduct") Long idProduct);

    @Query("SELECT s FROM SerialNumber s")
    Page<SerialNumber> getAllPage(Pageable pageable);

    @Query("SELECT s FROM SerialNumber s WHERE lower(s.ma) = :ma")
    List<SerialNumber> existByMaForAdd(@Param("ma") String ma);
    @Query("SELECT s FROM SerialNumber s WHERE lower(s.ma) = :ma AND s.id <> :id")
    List<SerialNumber> existByMaForUpdate(@Param("ma") String ma, @Param("id") Long id);


    @Query("SELECT s FROM SerialNumber s WHERE lower(s.ma) = :ma ")
    SerialNumber findByMa(@Param("ma") String ma);

    @Modifying
    @Transactional
    @Query("DELETE FROM SerialNumber s WHERE s.sanPhamChiTiet.id = :idSPCT AND s.trangThai = 1")
    void deleteAllByIdSPCT(@Param("idSPCT") Long idSPCT);

    @Modifying
    @Transactional
    @Query("UPDATE SerialNumber s SET s.trangThai = 1 WHERE s.id = :id")
    void changeStatusToSeriNumberDaBan(Long id);


    List<SerialNumber> findBySanPhamChiTietId(Long id);

    @Query("SELECT s FROM SerialNumber s WHERE s.sanPhamChiTiet.id = :id AND s.trangThai = 0")
    List<SerialNumber> findBySanPhamChiTietIdActive(@Param("id") Long id);


    List<SerialNumber> findBySanPhamChiTietIdAndTrangThai(Long productDetailId, Integer status);
    Page<SerialNumber> findBySanPhamChiTietIdAndTrangThai(Long productDetailId, Integer status, Pageable pageable);

}
