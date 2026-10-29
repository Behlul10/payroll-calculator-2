package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class PayrollApp {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        String filePath = "src/main/resources/employees.csv";
        String rootPath = "src/main/resources/";
        String userFileName;
        String line;
        ArrayList<Employee> employees = new ArrayList<>();

        try {
            FileReader fileReaderEmployees = new FileReader(filePath);
            BufferedReader bufReaderEmployees = new BufferedReader(fileReaderEmployees);

            System.out.println("name the file with the .extention: ");
            userFileName = myScanner.nextLine();

            FileWriter writer = new FileWriter(rootPath + userFileName);

            //Read ignore the first line (the header)
            String header = bufReaderEmployees.readLine();
            System.out.println("Skipping header: " + header);

            while ((line = bufReaderEmployees.readLine()) != null) {
                String[] fields = line.split("\\|");

                //extract string vals raw
                String idStr = fields[0];
                String nameStr = fields[1];
                String hoursStr = fields[2];
                String rateStr = fields[3];

                //parse strings into the corect types
                int id = Integer.parseInt(idStr);
                float hours = Float.parseFloat(hoursStr);
                float rate = Float.parseFloat(rateStr);
                Employee emp = new Employee(id, nameStr, hours, rate);

                employees.add(emp);

                writer.write(String.format("Employee ID: %d, Name: %s, Gross Pay: $%.2f%n",
                        emp.getEmployeeId(), emp.getName(), emp.getGrossPay()));
            }
            writer.close();
            System.out.println("------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
