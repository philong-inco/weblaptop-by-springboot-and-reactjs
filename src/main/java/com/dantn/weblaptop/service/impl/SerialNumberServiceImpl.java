package com.dantn.weblaptop.service.impl;

import com.dantn.weblaptop.dto.request.create_request.SerialNumberCreate;
import com.dantn.weblaptop.dto.request.update_request.SerialNumberUpdate;
import com.dantn.weblaptop.dto.response.SanPhamChiTietResponse;
import com.dantn.weblaptop.dto.response.SerialNumberResponse;
import com.dantn.weblaptop.entity.sanpham.SanPhamChiTiet;
import com.dantn.weblaptop.entity.sanpham.SerialNumber;
import com.dantn.weblaptop.mapper.impl.SerialNumberMapper;
import com.dantn.weblaptop.repository.SanPhamChiTietRepository;
import com.dantn.weblaptop.repository.SerialNumberRepository;
import com.dantn.weblaptop.service.SerialNumberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SerialNumberServiceImpl implements SerialNumberService {

    SerialNumberRepository serialNumberRepository;
    SanPhamChiTietRepository sanPhamChiTietRepository;
    SerialNumberMapper mapper;

    @Override
    public SerialNumberResponse add(SerialNumberCreate create) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(create.getSanPhamChiTietId()).get();
        if (sanPhamChiTiet == null) return null;
        if (serialNumberRepository.existByMaForAdd(create.getMa().trim().toLowerCase()).size() > 0)
            return null;
        SerialNumber entity = mapper.createToEntity(create);
        entity.setSanPhamChiTiet(sanPhamChiTiet);
        return mapper.entityToResponse(serialNumberRepository.save(entity));
    }

    @Override
    public SerialNumberResponse update(SerialNumberUpdate update) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(update.getSanPhamChiTietId()).get();
        if (sanPhamChiTiet == null) return null;
        if (serialNumberRepository.existByMaForUpdate(update.getMa().trim().toLowerCase(), update.getId()).size() > 0)
            return null;
        SerialNumber entityOld = serialNumberRepository.findById(update.getId()).get();
        if (entityOld == null) return null;
        SerialNumber entity = mapper.updateToEntity(update, entityOld);
        entity.setSanPhamChiTiet(sanPhamChiTiet);
        return mapper.entityToResponse(serialNumberRepository.save(entity));
    }

    @Override
    public boolean delete(Long id) {
        SerialNumber entity = serialNumberRepository.findById(id).get();
        if (entity == null) return false;
        serialNumberRepository.deleteById(id);
        return true;
    }

    @Override
    public List<SerialNumberResponse> getAll() {
        return mapper.listEntityToListResponse(serialNumberRepository.getAllList());
    }

    @Override
    public Page<SerialNumberResponse> getAllPage(Pageable pageable) {
        return mapper.pageEntityToPageResponse(serialNumberRepository.getAllPage(pageable));
    }

    @Override
    public boolean existByAdd(String ma) {
        return serialNumberRepository.existByMaForAdd(ma.trim().toLowerCase()).size() > 0;
    }

    @Override
    public boolean existByUpdate(String ma, Long id) {
        return serialNumberRepository.existByMaForUpdate(ma.trim().toLowerCase(), id).size() > 0;
    }

    @Override
    public SerialNumberResponse findById(Long id) {
        SerialNumber entity = serialNumberRepository.findById(id).get();
        if (entity == null) return null;
        return mapper.entityToResponse(entity);
    }

    @Override
    public SerialNumberResponse findByMa(String ma) {
        SerialNumber entity = serialNumberRepository.findByMa(ma.trim().toLowerCase());
        if (entity == null) return null;
        return mapper.entityToResponse(entity);
    }
}