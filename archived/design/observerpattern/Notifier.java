package observerpattern;

public abstract class Notifier implements Observer{
    BankAccount bankAccount;
    double account;
    String event;

    public Notifier(BankAccount bankAccount){
        this.bankAccount = bankAccount;
        bankAccount.registerObserver(this);
    }

    @Override
    public void update(double account, String event){
        this.account = account;
        this.event = event;
        display();
    }

    public abstract void display();
}