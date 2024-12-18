package com.dantn.weblaptop.service;

import com.dantn.weblaptop.dto.ChangeEmail_Dto;
import com.dantn.weblaptop.dto.request.create_request.CreateNhanVien;
import com.dantn.weblaptop.dto.request.update_request.UpdateNhanVien;
import com.dantn.weblaptop.dto.response.KhachHangResponse;
import com.dantn.weblaptop.dto.response.NhanVienResponse;
import com.dantn.weblaptop.dto.response.VaiTro_Response;
import jakarta.mail.MessagingException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NhanVien_Service {
    Page<NhanVienResponse> pageNhanVien(Integer pageNo, Integer size);

    Page<NhanVienResponse> pageSearchNhanVien(Integer pageNo, Integer size, String search);

    Page<NhanVienResponse> pageSearchTrangThaiNhanVien(Integer pageNo, Integer size, Integer trangThai);

    Page<NhanVienResponse> searchNhanVienByGioiTinh(Integer pageNo, Integer size, Integer gioiTinh);

    Page<NhanVienResponse> searchNhanVienByNamSinh(Integer pageNo, Integer size, Integer year);

    NhanVienResponse findByEmail(String email);

    NhanVienResponse create(CreateNhanVien createNhanVienRequest);

    NhanVienResponse update(UpdateNhanVien updateNhanVienRequest, Long id);

    NhanVienResponse getOne(Long id);

    NhanVienResponse login(String email, String password);

    List<VaiTro_Response> findVaiTroByNhanVien(Long id);

    List<NhanVienResponse> getDanhSachNhanVien();

    void removeOrRevert(Long id);

    void rollBackStatusNhanVien(Long id);

    boolean changeEmail(ChangeEmail_Dto changeEmailDto, String newEmailNv);

    void sendForgotPasswordEmailForNhanVien(String email) throws MessagingException;

    void updatePassword(String newPassword, String email);

    void updateImageNV(String image, Long id);

    void sentEmailForgotPassword(String email) throws MessagingException;


}
