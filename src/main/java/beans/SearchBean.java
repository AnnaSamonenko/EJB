package beans;

import dao.impl.ShareDAOimpl;
import entities.Share;

import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name="searchBean")
public class SearchBean {

    private ShareDAOimpl shareDAOimpl = new ShareDAOimpl();

    public List<Share> searchByCompanyName(String companyName) {
        return shareDAOimpl.searchByCompanyName(companyName);
    }
}
