package com.dantn.weblaptop.entity.sanpham.thuoctinh;

import com.dantn.weblaptop.entity.base.BaseEntity;
import com.dantn.weblaptop.entity.sanpham.SanPhamChiTiet;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Set;

/**
 * @since 06/3/2024
 * Github: https://github.com/philong-inco
 */


@Entity
@Table(name = "man_hinh")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManHinh extends BaseEntity {

    String ma;
    @Column(name = "trang_thai")
    Integer trangThai;
    String ten;
    @Column(name = "kich_thuoc")
    String kichThuoc;

    @Column(name = "do_phan_giai")
    String doPhanGiai;
    @OneToMany(mappedBy = "manHinh",
            cascade = {CascadeType.DETACH, CascadeType.PERSIST,
                    CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @ToString.Exclude
    Set<SanPhamChiTiet> sanPhamChiTiets;

}
