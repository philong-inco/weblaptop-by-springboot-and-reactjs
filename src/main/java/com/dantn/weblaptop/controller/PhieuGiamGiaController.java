package com.dantn.weblaptop.controller;

import com.dantn.weblaptop.dto.request.create_request.CreatePhieuGiamGiaRequest;
import com.dantn.weblaptop.dto.request.update_request.UpdatePhieuGiamGiaRequest;
import com.dantn.weblaptop.dto.response.ApiResponse;
import com.dantn.weblaptop.entity.phieugiamgia.PhieuGiamGia;
import com.dantn.weblaptop.exception.AppException;
import com.dantn.weblaptop.service.impl.PhieuGiamGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/coupons")
public class PhieuGiamGiaController {
    @Autowired
    private PhieuGiamGiaService phieuGiamGiaService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllPhieuGiamGia(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setMessage("Call api success");
        apiResponse.setData(phieuGiamGiaService.getAll(page, size));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addPhieuGiamGia(@RequestBody CreatePhieuGiamGiaRequest request) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setMessage("Create ok");
        apiResponse.setData(phieuGiamGiaService.add(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse>  updatePhieuGiamGia(@RequestBody UpdatePhieuGiamGiaRequest request, @PathVariable Long id) {
//        return phieuGiamGiaService.update(phieuGiamGia, id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setMessage("Create ok");
        apiResponse.setData(phieuGiamGiaService.update(request, id));
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public PhieuGiamGia deletePhieuGiamGia(@PathVariable Long id) {
        return phieuGiamGiaService.delete(id);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ApiResponse> detailPhieuGiamGia(@PathVariable Long id) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setMessage("Call Api by id");
        apiResponse.setData(phieuGiamGiaService.detail(id));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("customer-coupons/{id}")
    public ResponseEntity<ApiResponse> getPageKhachHangPhieuGiamGia(
            @PathVariable(name = "id") Long id,
            @RequestParam Optional<String> page,
            @RequestParam Optional<String> size
    ) throws AppException {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setMessage("Call Api ustomer-coupons by id");
        apiResponse.setData(phieuGiamGiaService.getKhPGGById(id, page, size));
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("customer-coupons/del/{id}")
    public ResponseEntity<Void> updateTrangThaiKHPGG(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = "status") Integer status
    ) throws AppException {
        // 0 chưa dung 1 : dung : 2 : hết hạn
        phieuGiamGiaService.updateStatusKhachHangPhieuGiamGia(id, status);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
