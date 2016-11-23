package beans;

import dao.impl.ExchangeDAOimpl;
import ejb.SellShareEJB;
import ejb.SellShareLocalBean;
import entities.Exchange;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

@ManagedBean(name = "exchangeBean")
public class ExchangeBean {

    @ManagedProperty(value = "#{exchange}")
    Exchange exchange = new Exchange();

    @ManagedProperty(value = "#{exchangeId}")
    private int exchangeID;

    @ManagedProperty(value = "#{shareId}")
    private int shareID;

    @EJB
    SellShareLocalBean sellShareEJB;

    private ExchangeDAOimpl exchangeDAOimpl = new ExchangeDAOimpl();

    public List<Exchange> findAllExchanges() {
        List exchenges = exchangeDAOimpl.findAllExchanges();
        return exchenges;
    }

    public ExchangeDAOimpl getExchangeDAOimpl() {
        return exchangeDAOimpl;
    }

    public void setExchangeDAOimpl(ExchangeDAOimpl exchangeDAOimpl) {
        this.exchangeDAOimpl = exchangeDAOimpl;
    }

    public int getExchangeID() {
        return exchangeID;
    }

    public void setExchangeID(int exchangeID) {
        this.exchangeID = exchangeID;
    }

    public int getShareID() {
        return shareID;
    }

    public void setShareID(int shareID) {
        this.shareID = shareID;
    }

    public SellShareLocalBean getSellShareLocalBean() {
        return sellShareEJB;
    }

    public void setSellShareEJB(SellShareEJB sellShareEJB) {
        this.sellShareEJB = sellShareEJB;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public void create(Exchange exchange) {
        exchangeDAOimpl.create(exchange);
    }

    public void update(int id, Exchange exchange) {
        exchangeDAOimpl.update(id, exchange);
    }

    public void delete(int exchangeID) {
        exchangeDAOimpl.delete(exchangeID);
    }

    public void addShare(int exchangeID, int shareID) {
        sellShareEJB.addShare(exchangeID, shareID);
    }
}
