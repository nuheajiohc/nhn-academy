package com.nhnacademy.edu.springframework.service;

import com.nhnacademy.edu.springframework.repository.WaterInfo;
import java.util.List;

public interface WaterBillService {
        List<WaterInfo> calculateBill(double waterUsage);
}
