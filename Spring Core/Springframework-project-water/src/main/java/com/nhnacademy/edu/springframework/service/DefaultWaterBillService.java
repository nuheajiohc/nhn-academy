package com.nhnacademy.edu.springframework.service;

import com.nhnacademy.edu.springframework.annotation.TargetMethod;
import com.nhnacademy.edu.springframework.repository.WaterInfo;
import com.nhnacademy.edu.springframework.repository.tariff.Tariff;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DefaultWaterBillService implements WaterBillService {
    private Tariff tariffRepository;

    public DefaultWaterBillService(Tariff tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    @Override
    @TargetMethod
    public List<WaterInfo> calculateBill(double waterUsage) {
        return tariffRepository.findTariffByUsage(waterUsage);
    }
}
