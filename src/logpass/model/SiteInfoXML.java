package logpass.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name ="Sites")
public class SiteInfoXML {

    private List<SiteInfo> siteList;

    @XmlElement(name = "SiteInfo")
    public List<SiteInfo> getSiteList() {
        return siteList;
    }

    public void setSiteList(List<SiteInfo> siteList) {
        this.siteList = siteList;
    }

}
