package practice;

public class HDFC implements RBIInterface{
    @Override
    public void deposit(double money) {
        System.out.println("From HDFC bank class: "+ money);
    }
}
