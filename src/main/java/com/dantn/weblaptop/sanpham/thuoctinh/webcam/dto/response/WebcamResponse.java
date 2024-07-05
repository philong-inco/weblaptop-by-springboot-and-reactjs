package com.dantn.weblaptop.sanpham.thuoctinh.webcam.dto.response;

import com.dantn.weblaptop.sanpham.util.ConvertTime;
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
public class WebcamResponse {
    Long id;
    String ma;
    String ten;
    String doPhanGiai;
    Integer trangThai;
    String ngayTao;
    String nguoiTao;
    String ngaySua;
    String nguoiSua;

    public void convertTime() {
        this.ngayTao = ConvertTime.convert(this.ngayTao);
        this.ngaySua = ConvertTime.convert(this.ngaySua);
    }
}
