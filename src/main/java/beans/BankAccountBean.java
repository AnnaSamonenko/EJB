package beans;

import ejb.BankAccountEJB;
import ejb.BankAccountLocalBean;
import entities.BankAccount;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name = "bankAccountBean")
public class BankAccountBean {

    @ManagedProperty(value = "#{bankAccount}")
    private BankAccount bankAccount = new BankAccount();

    @ManagedProperty(value = "#{operationMoney}")
    int operationMoney;

    @EJB
    BankAccountLocalBean bankAccountEjb;

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public int getOperationMoney() {
        return operationMoney;
    }

    public void setOperationMoney(int operationMoney) {
        this.operationMoney = operationMoney;
    }

    public BankAccountLocalBean  getBankAccountEjb() {
        return bankAccountEjb;
    }

    public void setBankAccountEjb(BankAccountEJB bankAccountEjb) {
        this.bankAccountEjb = bankAccountEjb;
    }

    public void create(int buyerID) {
        BankAccountEJB bankAccountEjb = new BankAccountEJB();
        try {
            bankAccountEjb.create(buyerID);
        }
        catch (Exception ex){
            System.out.print(ex.getMessage());
        }
    }

    public void addMoney(int buyerID, int moneyToAdd) {
        bankAccountEjb.addMoney(buyerID, moneyToAdd);
    }

}
