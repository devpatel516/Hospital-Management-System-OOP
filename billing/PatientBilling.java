package billing;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import person.Patient;

public class PatientBilling implements Billing {
    private final Patient patient;

    public PatientBilling(Patient patient) {
        this.patient = patient;
    }

    public void generateBill(Scanner sc) {
        System.out.print("Enter Medical Charges: ");
        double medicalCharges = sc.nextDouble();

        LocalDate releaseDate;
        while (true) {
            System.out.print("Enter Release Date (yyyy-mm-dd): ");
            String releaseinp = sc.next();
            try {
                releaseDate = LocalDate.parse(releaseinp);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-mm-dd format.");
            }
        }

        LocalDate admissionDate = LocalDate.parse(patient.getAdmissionDate());
        long days = ChronoUnit.DAYS.between(admissionDate, releaseDate);
        double roomCharges = days * 1000;

        System.out.print("Enter Other Charges: ");
        double otherCharges = sc.nextDouble();
        sc.nextLine();

        double totalBill = medicalCharges + roomCharges + otherCharges;
        writeBillToFile(releaseDate, days, medicalCharges, roomCharges, otherCharges, totalBill);
    }

    private void writeBillToFile(LocalDate releaseDate, long days, double medicalCharges, double roomCharges,
            double otherCharges, double totalBill) {
        try (FileWriter writer = new FileWriter("billing.txt", true)) {
            writer.write("Bill for Patient: \n");
            writer.write("Patient ID: " + patient.getPatientId() + "\n");
            writer.write("Name: " + patient.getName() + "\n");
            writer.write("Age: " + patient.getAge() + "\n");
            writer.write("Gender: " + patient.getGender() + "\n");
            writer.write("Disease: " + patient.getDisease() + "\n");
            writer.write("Admission Date: " + patient.getAdmissionDate() + "\n");
            writer.write("Release Date: " + releaseDate + "\n");
            writer.write("Total Days: " + days + "\n");
            writer.write("Medical Charges(INR): " + medicalCharges + "\n");
            writer.write("Room Charges(INR): " + roomCharges + "\n");
            writer.write("Other Charges(INR): " + otherCharges + "\n");
            writer.write("Total Bill(INR): " + totalBill + "\n");
            writer.write("----------------------------------\n");
            System.out.println("Bill generated in billing.txt file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the bill to the file.");
        }
    }
}
