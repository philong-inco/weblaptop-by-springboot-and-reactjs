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

    @Query("SELECT s FROM SerialNumber s")
    Page<SerialNumber> getAllPage(Pageable pageable);

    @Query("SELECT s FROM SerialNumber s WHERE lower(s.ma) = :ma")
    List<SerialNumber> existByMaForAdd(@Param("ma") String ma);

    @Query("SELECT s FROM SerialNumber s WHERE lower(s.ma) = :ma AND s.id <> :id")
    List<SerialNumber> existByMaForUpdate(@Param("ma") String ma, @Param("id") Long id);

    @Query("SELECT s FROM SerialNumber s WHERE lower(s.ma) = :ma ")
    SerialNumber findByMa(@Param("ma") String ma);

    @Query("DELETE FROM SerialNumber s WHERE s.sanPhamChiTiet.id = :idSPCT AND s.trangThai = 1")
    void deleteAllByIdSPCT(@Param("idSPCT") Long idSPCT);

    @Query("UPDATE SerialNumber s SET s.trangThai = 1 WHERE s.id = :id")
    void changeStatusToSeriNumberDaBan(Long id);

    @Query("SELECT s from  SerialNumber s WHERE s.sanPhamChiTiet.sanPham.id = :idSP AND s.trangThai = 0")
    List<SerialNumber> findSerialNumberBySanPhamId(@Param("idSP") Long idSP);

    @Query("SELECT s FROM SerialNumber s WHERE s.sanPhamChiTiet.id = :id")
    List<SerialNumber> findBySanPhamChiTietId(@Param("id") Long id);

    @Query("SELECT s FROM SerialNumber s WHERE s.sanPhamChiTiet.id = :id AND s.trangThai = 0")
    List<SerialNumber> findBySanPhamChiTietIdActive(@Param("id") Long id);


    List<SerialNumber> findBySanPhamChiTietIdAndTrangThai(Long productDetailId, Integer status);

    Page<SerialNumber> findBySanPhamChiTietIdAndTrangThai(Long productDetailId, Integer status, Pageable pageable);

    Page<SerialNumber> findBySanPhamChiTietId(Long productDetailId, Pageable pageable);

    @Query("SELECT sn FROM " +
            "SerialNumber sn " +
            "WHERE (:codeSerial = '' " +
            "OR sn.ma LIKE %:codeSerial%) " +
            "AND sn.sanPhamChiTiet.id = :productDetailId")
    Page<SerialNumber> findByMaContainingAndSanPhamChiTietId(String codeSerial , Long productDetailId, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "UPDATE serial_number sn " +
            "SET sn.trang_thai = 1 " +
            "WHERE sn.id IN (:serialsId)", nativeQuery = true)
    void updateStatusByInIds(@Param("serialsId") List<Long> serialsId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE serial_number sn SET sn.trang_thai = :status WHERE sn.id IN (:ids)", nativeQuery = true)
    void updateStatusByIdsNative(@Param("status") Integer status, @Param("ids") List<Long> ids);

    @Modifying
    @Transactional
    @Query("DELETE FROM SerialNumber s WHERE s.id = :id")
    void deleteByIdSeri(@Param(("id")) Long id);

    @Query(value = "" +
            "SELECT * FROM serial_number " +
            "WHERE san_pham_chi_tiet_id = :productDetailId " +
            "AND trang_thai = 0 LIMIT :limit", nativeQuery = true)
    List<SerialNumber> findBySanPhamChiTietIdAndTrangThaiWithLimit(
            @Param("productDetailId") Long productDetailId,
            @Param("limit") int limit);
    @Query(value = "" +
            "SELECT \n" +
            "COUNT(sr.id) \n" +
            "AS serial_count\n" +
            "FROM serial_number AS sr \n" +
            "WHERE sr.san_pham_chi_tiet_id = :productDetailId  AND sr.trang_thai = 0;", nativeQuery = true)
    Integer  getQuantitySerialIsActive(@Param("productDetailId") Long productDetailId);
}
