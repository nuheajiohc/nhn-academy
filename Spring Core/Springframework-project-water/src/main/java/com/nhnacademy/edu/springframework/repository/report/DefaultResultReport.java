package com.nhnacademy.edu.springframework.repository.report;

import com.nhnacademy.edu.springframework.annotation.TargetMethod;
import com.nhnacademy.edu.springframework.repository.WaterInfo;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class DefaultResultReport implements ResultReport {

    private int numberOfFilteredItem = 5;

    @Override
    @TargetMethod
    public List<WaterInfo> report(List<WaterInfo> waterInfos) {
        return filterWaterInfos(waterInfos);
    }

    private List<WaterInfo> filterWaterInfos(List<WaterInfo> waterInfos) {
        return waterInfos.stream().sorted().limit(this.numberOfFilteredItem).collect(Collectors.toList());
    }

    public void setNumberOfFilteredItem(int numberOfFilteredItem) {
        this.numberOfFilteredItem = numberOfFilteredItem;
    }
}
