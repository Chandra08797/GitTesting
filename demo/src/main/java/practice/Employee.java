package practice;


public class Employee {
    private int id;
    private String name;

    public Employee(int id, String name){
        this.id=id;
        this.name=name;
    }

    public int getId(){
        return id;
    }

    @Override
    public String toString(){
        return "Employee: "+id+ "\t"+name;

    }

}
