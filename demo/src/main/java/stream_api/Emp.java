package stream_api;

public class Emp {
    private String name;
    private int salary;

    public Emp(String name,int salary){
        this.salary=salary;
        this.name=name;
    }

    public int getSalary(){
        return salary;
    }

    @Override
    public String toString() {
        return "Employee: " + name + "\t" + salary;
    }

}
