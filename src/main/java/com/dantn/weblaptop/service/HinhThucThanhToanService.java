package com.dantn.weblaptop.service;

import com.dantn.weblaptop.dto.request.create_request.CreateHinhThucThanhToanRequest;
import com.dantn.weblaptop.dto.request.update_request.UpdateHinhThucThanhToanRequest;
import com.dantn.weblaptop.dto.response.HinhThucThanhToanResponse;
import com.dantn.weblaptop.dto.response.ResultPaginationResponse;
import com.dantn.weblaptop.exception.AppException;

import java.util.Optional;

public interface HinhThucThanhToanService {
    HinhThucThanhToanResponse create (CreateHinhThucThanhToanRequest request);

    HinhThucThanhToanResponse update (Long id , UpdateHinhThucThanhToanRequest request) throws AppException;

    ResultPaginationResponse getPaymentMethodsPage (Optional<String> page , Optional<String> size);

    void  updateStart(Long id);

}
