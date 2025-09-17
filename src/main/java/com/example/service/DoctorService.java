package com.example.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    /**
     * Trả về danh sách khung giờ khám của bác sĩ theo ngày.
     * Hiện tại dùng dữ liệu demo (hardcoded).
     *
     * @param doctorId ID bác sĩ
     * @param date Ngày muốn lấy khung giờ
     * @return Danh sách khung giờ dạng String
     */
    public List<String> getAvailableSlots(Long doctorId, LocalDate date) {
        // Dữ liệu demo
        List<String> allSlots = Arrays.asList(
                "2025-09-20 10:00",
                "2025-09-20 11:00",
                "2025-09-21 09:00",
                "2025-09-21 14:00"
        );

        // Lọc theo ngày
        return allSlots.stream()
                .filter(slot -> slot.startsWith(date.toString()))
                .collect(Collectors.toList());
    }

    /**
     * Kiểm tra đăng nhập bác sĩ.
     * Hiện tại dùng hardcoded credentials để demo.
     *
     * @param username Tên đăng nhập
     * @param password Mật khẩu
     * @return true nếu hợp lệ, false nếu không
     */
    public boolean validateLogin(String username, String password) {
        if (username == null || password == null) return false;
        return "doctor".equals(username) && "pass".equals(password);
    }
}
