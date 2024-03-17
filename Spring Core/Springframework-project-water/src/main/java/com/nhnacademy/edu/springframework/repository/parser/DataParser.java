package com.nhnacademy.edu.springframework.repository.parser;

import com.nhnacademy.edu.springframework.repository.WaterInfo;
import java.util.List;
import org.springframework.core.io.ClassPathResource;

public interface DataParser {
//    ClassPathResource parse(String filePath);

    void parse(String filePath);

    List<WaterInfo> findAll();
}

