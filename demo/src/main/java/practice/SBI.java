package practice;

public class SBI implements RBIInterface {

    @Override
    public void deposit(double money) {
        System.out.println("From sbi class: "+ money );
    }
}
