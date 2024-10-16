package com.dantn.weblaptop.repository;

import com.dantn.weblaptop.entity.hoadon.HoaDonHinhThucThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HoaDonHinhThucThanhToanRepository extends JpaRepository<HoaDonHinhThucThanhToan, Long> {
    List<HoaDonHinhThucThanhToan> findAllByHoaDonMa(String billCode);
    List<HoaDonHinhThucThanhToan> findAllByHoaDonIdAndLoaiThanhToan(Long billId ,Integer loaiThanhToan );

    Optional<HoaDonHinhThucThanhToan> findByHoaDonMa(String billCode);
    @Query(value = "SELECT * FROM hoa_don_httc WHERE hoa_don_id = :billId AND loai_thanh_toan = :loaiThanhToan LIMIT 1", nativeQuery = true)
    Optional<HoaDonHinhThucThanhToan> findByHoaDonIdAndLoaiThanhToan(@Param("billId") Long billId,@Param("loaiThanhToan") Integer loaiThanhToan);

}
