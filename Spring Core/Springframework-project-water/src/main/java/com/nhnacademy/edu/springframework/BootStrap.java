package com.nhnacademy.edu.springframework;

import com.nhnacademy.edu.springframework.repository.WaterInfo;
import com.nhnacademy.edu.springframework.repository.report.ResultReport;
import com.nhnacademy.edu.springframework.repository.tariff.DefaultTariff;
import com.nhnacademy.edu.springframework.repository.tariff.Tariff;
import com.nhnacademy.edu.springframework.service.WaterBillService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BootStrap {
    public static void main(String[] args) throws IOException {

        ApplicationContext context = new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework");

        WaterBillService waterBillService = context.getBean("defaultWaterBillService", WaterBillService.class);
        ResultReport resultReport = context.getBean("defaultResultReport", ResultReport.class);
        Tariff tariff = context.getBean("defaultTariff", Tariff.class);

        tariff.load("data/Tariff_20220331.json");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int waterUsage;

        while ((waterUsage = Integer.parseInt(br.readLine())) > 0) {
            List<WaterInfo> waterInfos = waterBillService.calculateBill(waterUsage);
            List<WaterInfo> report = resultReport.report(waterInfos);
            report.forEach(System.out::println);
        }

    }
}