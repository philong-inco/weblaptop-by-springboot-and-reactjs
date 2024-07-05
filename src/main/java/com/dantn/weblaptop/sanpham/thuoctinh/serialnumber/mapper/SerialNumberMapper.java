package com.dantn.weblaptop.sanpham.thuoctinh.serialnumber.mapper;

import com.dantn.weblaptop.entity.sanpham.SerialNumber;
import com.dantn.weblaptop.sanpham.generics.GenericsMapper;
import com.dantn.weblaptop.sanpham.thuoctinh.serialnumber.dto.request.SerialNumberCreate;
import com.dantn.weblaptop.sanpham.thuoctinh.serialnumber.dto.request.SerialNumberUpdate;
import com.dantn.weblaptop.sanpham.thuoctinh.serialnumber.dto.response.SerialNumberResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerialNumberMapper extends GenericsMapper<SerialNumber, SerialNumberCreate, SerialNumberUpdate, SerialNumberResponse> {
    @Override
    public SerialNumber createToEntity(SerialNumberCreate create) {
        SerialNumber serial = SerialNumber.builder()
                .trangThai(create.getTrangThai())
                .ngayNhap(create.getNgayNhap())
                .build();
        return serial;
    }

    @Override
    public SerialNumber updateToEntity(SerialNumberUpdate update, SerialNumber entity) {
        entity.setTrangThai(update.getTrangThai());
        entity.setNgayNhap(update.getNgayNhap());
        return entity;
    }

    @Override
    public SerialNumberResponse entityToResponse(SerialNumber entity) {
        SerialNumberResponse response = SerialNumberResponse.builder()
                .trangThai(entity.getTrangThai())
                .ngayNhap(entity.getNgayNhap() + "")
                .sanPham(entity.getSanPhamChiTiet().getSanPham().getTen())
                .ram(entity.getSanPhamChiTiet().getRam().getTen())
                .cpu(entity.getSanPhamChiTiet().getCpu().getTen())
                .webcam(entity.getSanPhamChiTiet().getWebcam().getTen())
                .banPhim(entity.getSanPhamChiTiet().getBanPhim().getTen())
                .heDieuHanh(entity.getSanPhamChiTiet().getHeDieuHanh().getTen())
                .manHinh(entity.getSanPhamChiTiet().getManHinh().getTen())
                .mauSac(entity.getSanPhamChiTiet().getMauSac().getTen())
                .oCung(entity.getSanPhamChiTiet().getOCung().getTen())
                .vga(entity.getSanPhamChiTiet().getVga().getTen())
                .ngayTao(entity.getNgayTao() + "")
                .ngaySua(entity.getNgaySua() + "")
                .nguoiTao(entity.getNguoiTao())
                .nguoiSua(entity.getNguoiSua())
                .build();
        response.convertTime();
        return response;
    }

    @Override
    public List<SerialNumberResponse> listEntityToListResponse(List<SerialNumber> entityList) {
        return entityList.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    @Override
    public Page<SerialNumberResponse> pageEntityToPageResponse(Page<SerialNumber> entityPage) {
        List<SerialNumberResponse> list = entityPage.getContent().stream()
                .map(this::entityToResponse).collect(Collectors.toList());
        return new PageImpl<>(list, entityPage.getPageable(), entityPage.getTotalElements());
    }
}
