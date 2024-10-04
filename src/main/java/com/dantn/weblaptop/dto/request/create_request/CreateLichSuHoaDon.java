package com.dantn.weblaptop.dto.request.create_request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLichSuHoaDon {

    @NotBlank(message = "NOTE_NOT_BLANK")
    @Size(min = 20, message = "NOTE_MIN_20")
    private String ghiChuCuaHang;
    private String ghiChuKhachHang;
}
