package com.nhnacademy.edu.springframework.repository.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springframework.annotation.TargetMethod;
import com.nhnacademy.edu.springframework.repository.WaterInfo;
import java.io.IOException;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class JsonDataParser implements DataParser {

    private List<WaterInfo> waterInfos;

    public JsonDataParser(List<WaterInfo> waterInfos) {
        this.waterInfos = waterInfos;
    }

    @Override
    @TargetMethod
    public void parse(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource(filePath);
        try {
            List rawData = mapper.readValue(resource.getInputStream(), List.class);
            rawData.forEach(data -> {
                String[] splitedData = data.toString().split(",");
                WaterInfo waterInfo = new WaterInfo(
                        splitedData[1].substring(6),
                        splitedData[2].substring(4),
                        Double.parseDouble(splitedData[4].substring(13)),
                        Double.parseDouble(splitedData[5].substring(12)),
                        Integer.parseInt(splitedData[6].substring(9))
                );
                waterInfos.add(waterInfo);
            });
        } catch (IOException e) {
            e.printStackTrace();
            ;
        }
    }

    @Override
    public List<WaterInfo> findAll() {
        return this.waterInfos;
    }
}
