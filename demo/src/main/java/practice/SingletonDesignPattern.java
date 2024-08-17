package practice;

public class SingletonDesignPattern {

    private static SingletonDesignPattern singletonDesignPattern;

    private SingletonDesignPattern(){

    }

    public static SingletonDesignPattern getInstance(){
        if(singletonDesignPattern==null){
            synchronized (SingletonDesignPattern.class){
                if(singletonDesignPattern==null){
                    singletonDesignPattern = new SingletonDesignPattern();
                    return singletonDesignPattern;
                }
            }
        }
        return singletonDesignPattern;
    }

    public void printMessage(){
        System.out.println("hello executed from singleton class method");
    }

}

class main{

    public static void main(String[] args) {
        SingletonDesignPattern instance = SingletonDesignPattern.getInstance();
        instance.printMessage();
    }
}