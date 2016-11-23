package ejb;

import javax.ejb.Local;

@Local
public interface SellShareLocalBean {
    void addShare(int exchangeID, int shareID);
}
