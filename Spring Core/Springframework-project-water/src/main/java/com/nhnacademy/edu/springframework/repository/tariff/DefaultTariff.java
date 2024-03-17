package com.nhnacademy.edu.springframework.repository.tariff;

import com.nhnacademy.edu.springframework.annotation.TargetMethod;
import com.nhnacademy.edu.springframework.repository.WaterInfo;
import com.nhnacademy.edu.springframework.repository.parser.DataParser;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultTariff implements Tariff {
    private DataParser dataParser;
    private List<WaterInfo> waterInfos;

    public DefaultTariff(DataParser dataParser) {
        this.dataParser = dataParser;
    }

    @Override
    @TargetMethod
    public void load(String filePath) {
        this.dataParser.parse(filePath);
        this.waterInfos = this.dataParser.findAll();
    }

    @Override
    @TargetMethod
    public List<WaterInfo> findTariffByUsage(double waterUsage) {
        if (waterUsage < 0) {
            throw new IllegalArgumentException("물 사용량은 음수가 될 수 없습니다.");
        }
        List<WaterInfo> newWaterInfos = this.waterInfos.stream().filter(waterInfo -> waterInfo.isInUsageRange(waterUsage)).collect(Collectors.toList());
        newWaterInfos.forEach(waterInfo -> waterInfo.calculateBillTotal(waterUsage));
        return newWaterInfos;
    }
}
