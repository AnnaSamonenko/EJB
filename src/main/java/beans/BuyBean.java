package beans;

import ejb.BuyShareLocalBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "buyBean")
public class BuyBean {
    @EJB
    BuyShareLocalBean buyShareEJB;

    public void buy(int buyerID, int shareID) {
        buyShareEJB.buy(buyerID, shareID);
    }

    public BuyShareLocalBean getBuyShareEJB() {
        return buyShareEJB;
    }

    public void setBuyShareEJB(BuyShareLocalBean buyShareEJB) {
        this.buyShareEJB = buyShareEJB;
    }
}
