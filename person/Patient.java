package person;

public class Patient extends Person {
    private String patientId;
    private String disease;
    private String admissionDate;

    public Patient(String name, int age, String gender, String patientId, String disease, String admissionDate) {
        super(name, age, gender);
        this.patientId = patientId;
        this.disease = disease;
        this.admissionDate = admissionDate;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public int getAge() {
        return super.getAge();
    }

    public void setAge(int age) {
        super.setAge(age);
    }

    public String getGender() {
        return super.getGender();
    }

    public void setGender(String gender) {
        super.setGender(gender);
    }

    public void displayDetails() {
        super.displayDetails();
        System.out.println("Patient ID: " + patientId + ", Disease: " + disease + ", Admission Date: " + admissionDate);
    }
}
