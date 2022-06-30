package com.osipyan.armen;

import au.com.bytecode.opencsv.CSVReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReportGenerator {

    private List<Subscriber> list;
    private List<AccrualForHouse> houseList;
    private int id;
    private final String path;


    public ReportGenerator(String path) {
        this.path = path;
    }

    public void read() throws IOException {
        list = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader(path), ';', '\n', 1);
        List<String[]> allRows = reader.readAll();

        for (String[] row : allRows) {
            Subscriber subscriber = new Subscriber();
            subscriber.setId(Integer.parseInt(row[0]));
            subscriber.setLastName(row[1]);
            subscriber.setStreet(row[2]);
            subscriber.setHouseNumber(Integer.parseInt(row[3]));
            subscriber.setApartmentNumber(Integer.parseInt(row[4]));
            subscriber.setAccrualType(Integer.parseInt(row[5]));
            subscriber.setPreviousAccrual(Integer.parseInt(row[6]));
            subscriber.setCurrentAccrual(Integer.parseInt(row[7]));

            list.add(subscriber);
        }
    }

    public void generatePersonalInvoice() throws IOException {
        String csv_file = "src/main/resources/Начисления_абоненты.csv";
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(csv_file));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withHeader("№ строки", "Фамилия", "Улица", "№ дома", "№ квартиры", "Тип начисления", "Предыдущее", "Текущее", "Начисления"));


        for (int i = 0; i < list.size(); i++) {
            Subscriber s = list.get(i);
            s.setAccrual();
            list.set(i, s);

            csvPrinter.printRecord(s.getId(), s.getLastName(), s.getStreet(),
                    s.getHouseNumber(), s.getApartmentNumber(), s.getAccrualType(), s.getPreviousAccrual()
                    , s.getCurrentAccrual(), s.getAccrual());

        }
        csvPrinter.flush();
    }


    public void generateHouseInvoice() throws IOException {
        String csv_file = "src/main/resources/Начисления_дома.csv";
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(csv_file));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withHeader("№ строки", "Улица", "№ дома", "Начислено"));

        houseList = new ArrayList<>();

        int count = 0;

        while (count != list.size() - 1) {
            double accrualForHouse = 0;
            for (int j = count + 1; j <= list.size() - 1; j++) {

                Subscriber subscriber = list.get(count);
                accrualForHouse = subscriber.getAccrual();
                Subscriber subscriber1 = list.get(j);
                if (subscriber.getHouseNumber() == 0) {
                    j = list.size() - 1;
                }
                if (subscriber.getHouseNumber() == subscriber1.
                        getHouseNumber() && subscriber.getHouseNumber() != 0 && subscriber1.getHouseNumber() != 0) {

                    accrualForHouse += subscriber1.getAccrual();
                    subscriber.setAccrual(accrualForHouse);
                    subscriber1.setHouseNumber(0);

                }
                if (j == list.size() - 1 && subscriber.getHouseNumber() != 0) {
                    addHouseToTheList(subscriber, accrualForHouse);
                }

            }
            count++;
        }

        for (AccrualForHouse s : houseList) {
            csvPrinter.printRecord(s.getId(), s.getStreet(),
                    s.getHouseNumber(),
                    s.getAccrual());
        }
        csvPrinter.flush();

    }

    private void addHouseToTheList(Subscriber subscriber, double accrualForHouse) {
        houseList.add(new AccrualForHouse(++id, subscriber.getStreet(), subscriber.getHouseNumber(), accrualForHouse));
    }

}


















