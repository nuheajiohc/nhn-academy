package com.nhnacademy.edu.springframework.repository.tariff;

import com.nhnacademy.edu.springframework.repository.WaterInfo;
import java.util.List;

public interface Tariff {

    void load(String filePath);

    List<WaterInfo> findTariffByUsage(double waterUsage);
}
