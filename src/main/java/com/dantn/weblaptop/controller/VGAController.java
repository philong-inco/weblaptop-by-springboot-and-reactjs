package com.dantn.weblaptop.controller;

import com.dantn.weblaptop.entity.sanpham.thuoctinh.VGA;
import com.dantn.weblaptop.generics.GenericsController;
import com.dantn.weblaptop.generics.GenericsService;
import com.dantn.weblaptop.dto.request.create_request.VGACreate;
import com.dantn.weblaptop.dto.request.update_request.VGAUpdate;
import com.dantn.weblaptop.dto.response.VGAResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vga/")
public class VGAController extends GenericsController<VGA, Long, VGACreate, VGAUpdate, VGAResponse> {
    public VGAController(@Qualifier("VGAService") GenericsService<VGA, Long, VGACreate, VGAUpdate, VGAResponse> genericsService) {
        super(genericsService);
    }
}
