import billing.PatientBilling;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import person.Doctor;
import person.Patient;

public class HospitalManagementSystem {

    private static void addDoctor(Scanner sc) {
        System.out.print("Enter Doctor's Name: ");
        String docName = sc.nextLine();
        System.out.print("Enter Doctor's Gender: ");
        String gender = sc.nextLine();
        System.out.print("Enter Doctor's Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Doctor's Department: ");
        String department = sc.nextLine();
        System.out.print("Enter Doctor's Specialization: ");
        String special = sc.nextLine();
        System.out.print("Enter Doctor's ID: ");
        String docid = sc.nextLine();

        Doctor doctor = new Doctor(docName, age, gender, department, docid, special);
        writeDoctor(doctor);
    }

    private static void writeDoctor(Doctor doctor) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter("doctors.txt", true))) {
            w.write("Doctor ID: " + doctor.getDoctorId());
            w.newLine();
            w.write("Name: " + doctor.getName());
            w.newLine();
            w.write("Gender: " + doctor.getGender());
            w.newLine();
            w.write("Age: " + doctor.getAge());
            w.newLine();
            w.write("Specialization: " + doctor.getSpecialization());
            w.newLine();
            w.write("---------------------------------------------\n");
            w.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to doctors file: " + e.getMessage());
        }
    }

    private static void addPatient(Scanner sc) {
        System.out.print("Enter Patient's Name: ");
        String patientName = sc.nextLine();
        System.out.print("Enter Patient's Gender: ");
        String pgender = sc.nextLine();
        System.out.print("Enter Patient's Age: ");
        int patientage = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Patient's ID: ");
        String patientid = sc.nextLine();
        System.out.print("Enter Patient's Disease: ");
        String disease = sc.nextLine();
        String joindate;
        while (true) {
            System.out.print("Enter Patient's Admit Date (yyyy-mm-dd): ");
            joindate = sc.nextLine();
            try {
                LocalDate parsedDate = LocalDate.parse(joindate);
                joindate = parsedDate.toString();
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-mm-dd format.");
            }
        }
        Patient patient = new Patient(patientName, patientage, pgender, patientid, disease, joindate);
        writePatient(patient);
    }

    private static void writePatient(Patient patient) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter("patients.txt", true))) {
            w.write("Patient ID: " + patient.getPatientId());
            w.newLine();
            w.write("Name: " + patient.getName());
            w.newLine();
            w.write("Gender: " + patient.getGender());
            w.newLine();
            w.write("Age: " + patient.getAge());
            w.newLine();
            w.write("Disease: " + patient.getDisease());
            w.newLine();
            w.write("Admit Date: " + patient.getAdmissionDate());
            w.newLine();
            w.write("---------------------------------------------\n");
            w.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to patients file: " + e.getMessage());
        }
    }

    private static void showDoctors() {
        try (BufferedReader rd = new BufferedReader(new FileReader("doctors.txt"))) {
            String line;
            boolean found = false;
            System.out.println("List of Doctors");
            while ((line = rd.readLine()) != null) {
                found = true;
                System.out.println(line);
            }
            if (!found) {
                System.out.println("No Doctors available.");
            }
        } catch (IOException e) {
            System.out.println("Error reading doctors file: " + e.getMessage());
        }
    }

    private static void showPatients() {
        try (BufferedReader rd = new BufferedReader(new FileReader("patients.txt"))) {
            String line;
            boolean found = false;
            System.out.println("List of Patient's");
            while ((line = rd.readLine()) != null) {
                found = true;
                System.out.println(line);
            }
            if (!found) {
                System.out.println("No Patients available.");
            }
        } catch (IOException e) {
            System.out.println("Error reading patients file: " + e.getMessage());
        }
    }

    private static void generatePatientBill(Scanner sc) {
        System.out.print("Enter Patient ID for billing: ");
        String patientId = sc.nextLine();
        Patient patient = findPatientById(patientId);
        if (patient != null) {
            PatientBilling patientBilling = new PatientBilling(patient);
            patientBilling.generateBill(sc);
        } else {
            System.out.println("Patient not found.");
        }
    }

    private static Patient findPatientById(String patientId) {
        try (BufferedReader rd = new BufferedReader(new FileReader("patients.txt"))) {
            String line;
            String id = "", name = "", gen = "", age = "", disease = "", admit = "";
            while ((line = rd.readLine()) != null) {
                if (line.startsWith("Patient ID:")) {
                    id = line.substring("Patient ID: ".length()).trim();
                }
                if (id.equals(patientId)) {
                    name = rd.readLine().substring("Name: ".length()).trim();
                    gen = rd.readLine().substring("Gender: ".length()).trim();
                    age = rd.readLine().substring("Age: ".length()).trim();
                    disease = rd.readLine().substring("Disease: ".length()).trim();
                    admit = rd.readLine().substring("Admit Date: ".length()).trim();
                    rd.readLine();
                    return new Patient(name, Integer.parseInt(age), gen, id, disease, admit);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading patients file: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean r = true;
        try {
            while (r) {
                System.out.println("Enter Your Choice:");
                System.out.println("1. New Doctor");
                System.out.println("2. Add Patient");
                System.out.println("3. Show Doctors");
                System.out.println("4. Show Patients");
                System.out.println("5. Generate Patient Bill");
                System.out.println("6. Exit");

                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        addDoctor(sc);
                        break;
                    case 2:
                        addPatient(sc);
                        break;
                    case 3:
                        showDoctors();
                        break;
                    case 4:
                        showPatients();
                        break;
                    case 5:
                        generatePatientBill(sc);
                        break;
                    case 6:
                        System.out.println("Exiting the system.");
                        r = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            }
        } finally {
            sc.close();
        }
    }
}
