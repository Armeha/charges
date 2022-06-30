package com.osipyan.armen;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        String path = "src/main/resources/Абоненты.csv";
        ReportGenerator reportGenerator = new ReportGenerator(path);

        reportGenerator.read();
        reportGenerator.generatePersonalInvoice();
        reportGenerator.generateHouseInvoice();
    }

}
