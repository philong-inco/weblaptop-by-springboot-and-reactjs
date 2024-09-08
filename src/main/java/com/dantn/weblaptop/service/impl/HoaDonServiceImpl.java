package com.dantn.weblaptop.service.impl;

import com.dantn.weblaptop.constant.HoaDonStatus;
import com.dantn.weblaptop.dto.request.create_request.CreateLichSuHoaDonRequest;
import com.dantn.weblaptop.dto.request.update_request.UpdateHoaDonRequest;
import com.dantn.weblaptop.dto.response.HoaDonResponse;
import com.dantn.weblaptop.dto.response.Meta;
import com.dantn.weblaptop.dto.response.ResultPaginationResponse;
import com.dantn.weblaptop.dto.response.SerialNumberDaBanResponse;
import com.dantn.weblaptop.entity.hoadon.HoaDon;
import com.dantn.weblaptop.entity.nhanvien.NhanVien;
import com.dantn.weblaptop.entity.phieugiamgia.PhieuGiamGia;
import com.dantn.weblaptop.exception.AppException;
import com.dantn.weblaptop.exception.ErrorCode;
import com.dantn.weblaptop.mapper.impl.HoaDonMapper;
import com.dantn.weblaptop.repository.HoaDonRepository;
import com.dantn.weblaptop.repository.NhanVienRepository;
import com.dantn.weblaptop.service.HoaDonService;
import com.dantn.weblaptop.service.LichSuHoaDonService;
import com.dantn.weblaptop.service.SerialNumberDaBanService;
import com.dantn.weblaptop.util.BillUtils;
import com.dantn.weblaptop.util.GenerateCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class HoaDonServiceImpl implements HoaDonService {
    HoaDonRepository billRepository;
    LichSuHoaDonService billHistoryService;
    NhanVienRepository employeeRepository;
    SerialNumberDaBanService serialNumberDaBanService;

    @Override
    public ResultPaginationResponse getBillPage(Optional<String> page, Optional<String> size) {
        String sPage = page.isPresent() ? page.get() : "0";
        String sSize = size.isPresent() ? size.get() : "5";
        Pageable pageable = PageRequest.of(Integer.parseInt(sPage), Integer.parseInt(sSize), Sort.by("id").descending());
        Page<HoaDon> billHistoryPage = billRepository.findAll(pageable);
        Page<HoaDonResponse> responses = billHistoryPage.map(bill -> HoaDonMapper.toHoaDonResponse(bill));

        Meta meta = Meta.builder()
                .page(responses.getNumber())
                .pageSize(responses.getSize())
                .pages(responses.getTotalPages())
                .total(responses.getTotalElements())
                .build();

        ResultPaginationResponse response = ResultPaginationResponse
                .builder()
                .meta(meta)
                .result(responses.getContent())
                .build();
        return response;
    }

    @Override
    public HoaDonResponse createBill() throws AppException {
        List<HoaDon> billListStatusPending = billRepository.findByTrangThaiAndLoaiHoaDon(HoaDonStatus.getHoaDonStatusEnum("Đơn mới"), 0);
        if (billListStatusPending.size() >= 5) {
            throw new AppException(ErrorCode.MAXIMUM_5);
        }
        NhanVien existingEmployee = employeeRepository.findById(1L).get();
        // save
        HoaDon newBill = new HoaDon();
        newBill.setMa(GenerateCode.generateHoaDon());
        newBill.setNhanVien(existingEmployee);
        newBill.setTrangThai(HoaDonStatus.DON_MOI);
        newBill.setLoaiHoaDon(0);// 0 : Tại quầy - 1 : Online
        HoaDonResponse response = HoaDonMapper.toHoaDonResponse(billRepository.save(newBill));

        CreateLichSuHoaDonRequest billHistoryRequest = new CreateLichSuHoaDonRequest();
        billHistoryRequest.setIdHoaDon(response.getId());
        // TT LSHD : 0 -tạo mới | 1 - Cập nhập : 2 - thanh toán :
        billHistoryRequest.setTrangThai(0);
        // sủa khi có security
        billHistoryRequest.setIdNhanVien(1L);
        // save
        billHistoryService.create(billHistoryRequest);

        log.info("Bill History : " + billHistoryRequest);
        return response;
    }

    @Override
    public HoaDonResponse updateBill(Long id, UpdateHoaDonRequest request) {
        return null;
    }

    @Override
    public HoaDonResponse updateBillByCode(String code, UpdateHoaDonRequest request) {
        return null;
    }

    @Override
    public HoaDonResponse getBillById(Long id) throws AppException {
        HoaDon existingBill = billRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.BILL_NOT_FOUND));

        return HoaDonMapper.toHoaDonResponse(existingBill);
    }

    @Override
    public HoaDonResponse getBillByCode(String code) throws AppException {
        HoaDon existingBill = billRepository.findHoaDonByMa(code).orElseThrow(
                () -> new AppException(ErrorCode.BILL_NOT_FOUND));
        return HoaDonMapper.toHoaDonResponse(existingBill);
    }

    @Override
    public HoaDonResponse getBillByIdAndStatus(Long id, String status) {
        HoaDon bill = billRepository.findByIdAndTrangThai(id, HoaDonStatus.getHoaDonStatusEnum(status)).orElse(null);
        if (bill != null) {
            return HoaDonMapper.toHoaDonResponse(bill);
        }
        return null;
    }

    @Override
    public ResultPaginationResponse pageBillByStatusAndType(String status, Integer type, Optional<String> page, Optional<String> size) {
        String sPage = page.isPresent() ? page.get() : "0";
        String sSize = size.isPresent() ? size.get() : "5";
        Pageable pageable = PageRequest.of(Integer.parseInt(sPage), Integer.parseInt(sSize), Sort.by("id").descending());
        HoaDonStatus billStatus = HoaDonStatus.getHoaDonStatusEnumByKey(status);
        Page<HoaDon> billPage = billRepository.findByTrangThaiAndLoaiHoaDon(billStatus, type, pageable);
        Page<HoaDonResponse> responses = billPage.map(bill -> HoaDonMapper.toHoaDonResponse(bill));

        Meta meta = Meta.builder()
                .page(responses.getNumber())
                .pageSize(responses.getSize())
                .pages(responses.getTotalPages())
                .total(responses.getTotalElements())
                .build();

        ResultPaginationResponse response = ResultPaginationResponse
                .builder()
                .meta(meta)
                .result(responses.getContent())
                .build();
        return response;
    }

    @Override
    public void updateStatus(String code, String status) throws AppException {
        Optional<HoaDon> optional = billRepository.findHoaDonByMa(code);
        if (optional.isPresent()) {
            HoaDon bill = optional.get();
            bill.setTrangThai(HoaDonStatus.getHoaDonStatusEnumByKey(status));
            CreateLichSuHoaDonRequest billHistoryRequest = new CreateLichSuHoaDonRequest();
            billHistoryRequest.setIdHoaDon(bill.getId());
            billHistoryRequest.setTrangThai(BillUtils.convertBillStatusEnumToInteger(bill.getTrangThai()));
            // sủa khi có security
            billHistoryRequest.setIdNhanVien(1L);
            // save
            billHistoryService.create(billHistoryRequest);
            billRepository.save(bill);
        }
    }

    @Override
    public ResultPaginationResponse filterHoaDon(Specification<HoaDon> specification, Pageable pageable) {
        Page<HoaDon> billPage = billRepository.findAll(specification, pageable);
        Page<HoaDonResponse> responses = billPage.map(
                bill -> HoaDonMapper.toHoaDonResponse(bill)
        );
        Meta meta = Meta.builder()
                .page(responses.getNumber())
                .pageSize(responses.getSize())
                .pages(responses.getTotalPages())
                .total(responses.getTotalElements())
                .build();

        ResultPaginationResponse response = ResultPaginationResponse
                .builder()
                .meta(meta)
                .result(responses.getContent())
                .build();
        return response;
    }
//Lặp code bên .. ĐÃ bán
    public BigDecimal tinhTien(String codeBill) {
        HoaDon hoaDon = billRepository.findHoaDonByMa(codeBill).get();
        List<SerialNumberDaBanResponse> listSerialNumberDaBan = serialNumberDaBanService.getSerialNumberDaBanPage(codeBill);
        PhieuGiamGia phieuGiamGia = hoaDon.getPhieuGiamGia();
        BigDecimal tongTien = listSerialNumberDaBan.stream()
                .map(response -> {
                    BigDecimal gia = response.getGia() != null ? response.getGia() : BigDecimal.ZERO;
                    Integer soLuong = response.getSoLuong() != null ? response.getSoLuong() : 0;
                    return gia.multiply(BigDecimal.valueOf(soLuong));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Tổng tiền : " + tongTien);
        hoaDon.setTongTienBanDau(tongTien);
        BigDecimal tienGiam = BigDecimal.ZERO;
        if (phieuGiamGia != null) {
            Integer loaiPGG = phieuGiamGia.getLoaiGiamGia();
            Integer trangThai = phieuGiamGia.getTrangThai();
            BigDecimal giaTraiPhieuGiam = phieuGiamGia.getGiaTriGiamGia();
            if (trangThai == 3 || trangThai == 2) {
                hoaDon.setPhieuGiamGia(null);
                hoaDon = billRepository.save(hoaDon);
                return null;
            }
//            1 % : 2 VND
            if (loaiPGG == 2) {
                tienGiam = giaTraiPhieuGiam;
            } else {
//          tính % của phiếu giảm rồi trừ đi
                tienGiam = tongTien.multiply(giaTraiPhieuGiam).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
            }
            tongTien = tongTien.subtract(tienGiam);
        }
        System.out.println("Tổng tiền sau giảm giá : " + tongTien);
        hoaDon.setTongTienPhaiTra(tongTien);
        billRepository.save(hoaDon);
        return tongTien;
    }
}
