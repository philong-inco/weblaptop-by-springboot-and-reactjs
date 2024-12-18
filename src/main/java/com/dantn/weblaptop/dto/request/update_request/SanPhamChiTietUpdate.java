package com.dantn.weblaptop.dto.request.update_request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SanPhamChiTietUpdate {
    Long id;
    String giaBan;
    Integer trangThai;
    Long banPhimId;
    Long cpuId;
    Long heDieuHanhId;
    Long manHinhId;
    Long mauSacId;
    Long oCungId;
    Long ramId;
    Long sanPhamId;
    Long vgaId;
    Long webcamId;
    String listSerialNumber;
    String listUrlAnhSanPham;
}
