public class Employee {
    private String id;
    private  String fname;
    private  String lname;
    private  String dept;

    public Employee(){

    }

    public Employee(String id, String fname, String lname, String dept) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.dept = dept;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", dept='" + dept + '\'' +
                '}';
    }
}
