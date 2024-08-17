package practice;

public class BuilderDesignPattern {

    private String name;
    private int salary;
    private int age;

    public BuilderDesignPattern(FieldBuilder fieldBuilder) {
        this.name = fieldBuilder.name;
        this.salary = fieldBuilder.salary;
        this.age = fieldBuilder.age;
    }

    public static class FieldBuilder{
        private String name;
        private int salary;
        private int age;

        public FieldBuilder setName(String name){
            this.name=name;
            return this;
        }

        public FieldBuilder setSalary(int salary){
            this.salary=salary;
            return this;
        }

        public FieldBuilder setAge(int age){
            this.age = age;
            return this;
        }

        public BuilderDesignPattern build(){
            return new BuilderDesignPattern(this);
        }
    }

    @Override
    public String toString() {
        return "BuilderDesignPattern{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}

class BuilderMain{
    public static void main(String[] args) {
        BuilderDesignPattern chandrabhan = new BuilderDesignPattern.FieldBuilder()
                .setName("chandrabhan")
                .setAge(29)
                .setSalary(10000)
                .build();
        System.out.println(chandrabhan);
    }
}
