package person;

public class Doctor extends Person {
    private String department;
    private String doctorId;
    private String specialization;

    public Doctor(String name, int age, String gender, String department, String doctorId, String specialization) {
        super(name, age, gender);
        this.department = department;
        this.doctorId = doctorId;
        this.specialization = specialization;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization() {
        this.specialization = specialization;
    }
}
