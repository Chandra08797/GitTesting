package practice;

public class GetFactoryInstanceOfBank {

    public RBIInterface getInstanceOfBanks(String bankName){

        if(bankName==null){
            return null;
        }

        if(bankName.trim().equalsIgnoreCase("sbi")){
            return new SBI();
        }

        if(bankName.trim().equalsIgnoreCase("hdfc")){
            return new HDFC();
        }

        if(bankName.trim().equalsIgnoreCase("bob")){
            return new BOB();
        }
        return null;
    }

}

class Main{

    public static void main(String[] args) {
        GetFactoryInstanceOfBank getFactoryInstanceOfBank = new GetFactoryInstanceOfBank();
        RBIInterface sbi = getFactoryInstanceOfBank.getInstanceOfBanks("sbi");
        sbi.deposit(1000);
    }
}
