package com.dantn.weblaptop.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GenerateCode {
    public static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
<<<<<<< HEAD
    public static final String SIZE_PREFIX = "SZ";
    public static final String COLOR_PREFIX = "CL";
    public static final String STAFF_PREFIX = "NV";
    public static final String CUSTOMER_PREFIX = "CS";
    public static final String PRODUCT_PREFIX = "PR";
    public static final String CATEGORY_PREFIX = "CA";
    public static final String BILL_PREFIX = "BL";
    public static final String PAYMENT_METHOD_PREFIX = "PAY_";
    public static final Random random = new Random();

    public static final int SUFFIXES_LENGTH = 5;

    public String generateCode(String prefix) {
=======
    public static final String RAM_PREFIX = "RAM";
    public static final String VGA_PREFIX = "GVA";
    public static final String CPU_PREFIX = "CPU";
    public static final String MAN_HINH_PREFIX = "SCR";
    public static final String O_CUNG_PREFIX = "DIS";
    public static final String HE_DIEU_HANH_PREFIX = "SYS";
    public static final String WEBCAM_PREFIX = "WEC";
    public static final String MAU_SAC_PREFIX = "COL";
    public static final String BAN_PHIM_PREFIX = "KEB";
    public static final String SAN_PHAM_PREFIX = "PRO";
    public static final String SAN_PHAM_CHI_TIET_PREFIX = "PDE";
    public static final String THUONG_HIEU_PREFIX = "BRA";
    public static final String NHU_CAU_PREFIX = "DMA";
    private static final String NhanVien = "Empl0";
    private static final String KhachHang = "Cus0";
    private static final String VaiTro = "Role0";
    private static final String PAYMENT_METHOD_PREFIX ="PAY_";
    public static final Random random = new Random();
    public static final int SUFFIXES_LENGTH = 5;
    private static final int NUMBER_LENGTH = 4;

    public static String generateCode(String prefix) {
>>>>>>> main
        StringBuilder result = new StringBuilder();
        result.append(prefix);
        for (int i = 0; i < SUFFIXES_LENGTH; i++) {
            result.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return result.toString();
    }
<<<<<<< HEAD
=======

    public static String generateNhanVienCode() {
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, NUMBER_LENGTH));
        String formattedNumber = String.format("%0" + NUMBER_LENGTH + "d", randomNumber);
        return NhanVien + formattedNumber;
    }

    public static String generateKhachHangCode() {
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, NUMBER_LENGTH));
        String formattedNumber = String.format("%0" + NUMBER_LENGTH + "d", randomNumber);
        return KhachHang + formattedNumber;
    }

    public static String generateVaiTroCode() {
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, NUMBER_LENGTH));
        String formattedNumber = String.format("%0" + NUMBER_LENGTH + "d", randomNumber);
        return VaiTro + formattedNumber;
    }

    public static String generateHHTT() {
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, NUMBER_LENGTH));
        String formattedNumber = String.format("%0" + NUMBER_LENGTH + "d", randomNumber);
        return PAYMENT_METHOD_PREFIX + formattedNumber;
    }
>>>>>>> main
}
