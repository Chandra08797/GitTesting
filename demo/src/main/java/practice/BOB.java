package practice;

public class BOB implements RBIInterface{

    @Override
    public void deposit(double money) {
        System.out.println("From bob bank class: "+ money);
    }
}
