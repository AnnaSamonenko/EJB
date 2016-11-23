package ejb;

import javax.ejb.Local;

@Local
public interface BuyShareLocalBean {

    void buy(int buyerID, int shareID);
}
