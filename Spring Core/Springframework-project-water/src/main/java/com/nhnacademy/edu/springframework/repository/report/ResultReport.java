package com.nhnacademy.edu.springframework.repository.report;

import com.nhnacademy.edu.springframework.repository.WaterInfo;
import java.util.List;

public interface ResultReport {
    List<WaterInfo> report(List<WaterInfo> waterInfos);
}
