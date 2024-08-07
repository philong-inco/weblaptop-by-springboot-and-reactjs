package com.dantn.weblaptop.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class VaiTro_Response {
    Long id;
    String ma;
    Integer trang_thai;
    String ten;
    String moTa;
}
