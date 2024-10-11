package com.dantn.weblaptop.dto.request.create_request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateHoaDonHinhThucThanhToanRequest {
    @NotNull(message = "AMOUNT_NOT_NULL")
    @Min(value = 0, message = "AMOUNT_INVALID")
    BigDecimal soTien;
    @NotNull(message = "idHTTT chưa có")
    Long idHTTT;
    @NotNull(message = "Loại Trả sau || TT chưa có")
    Integer loaiThanhToan;

}