package com.nhnacademy.edu.springframework.repository.parser;

import com.nhnacademy.edu.springframework.annotation.TargetMethod;
import com.nhnacademy.edu.springframework.repository.WaterInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

//@Component
public class CsvDataParser implements DataParser {

    private List<WaterInfo> waterInfos;

    public CsvDataParser(List<WaterInfo> waterInfos) {
        this.waterInfos = waterInfos;
    }

    @Override
    @TargetMethod
    public void parse(String filePath) {
        ClassPathResource resource = new ClassPathResource(filePath);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                parseLine(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void parseLine(String line) {
        String[] data = line.split(",");
        try {
            String city = data[1].trim();
            String sector = data[2].trim();
            double minUsage = Double.parseDouble(data[4]);
            double maxUsage = Double.parseDouble(data[5]);
            int unitPrice = Integer.parseInt(data[6]);

            WaterInfo waterInfo = new WaterInfo(city, sector, minUsage, maxUsage, unitPrice);
            waterInfos.add(waterInfo);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<WaterInfo> findAll() {
        return this.waterInfos;
    }
}
