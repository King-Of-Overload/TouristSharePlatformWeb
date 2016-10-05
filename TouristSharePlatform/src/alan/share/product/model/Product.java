package alan.share.product.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import alan.share.officialstrategy.model.City;
import alan.share.user.model.TripUser;
/**
 * 产品pojo映射
 * @author Alan
 *
 */
public class Product implements Serializable {
	private String pid;
	private String pname;
	private BigDecimal marketprice;
	private BigDecimal shopprice;
	private String pdescription;
	private int pclickednum;
	private int pishot;
	private Date pdate;
	private ProductSecondCategory secondCategory;//多对一 二级分类
	private TripUser seller;//发布者,多对一对象
	private String contactnum;//联系电话
	private String qualitychoice;
	private String pplaintext;
	private City city;//多对一  城市对象
	private String pcontent;
	
	private String coverImage;
	
	private List<String> showImages;
	
	
	
	public List<String> getShowImages() {
		return showImages;
	}
	public void setShowImages(List<String> showImages) {
		this.showImages = showImages;
	}
	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	public String getPcontent() {
		return pcontent;
	}
	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public BigDecimal getMarketprice() {
		return marketprice;
	}
	public void setMarketprice(BigDecimal marketprice) {
		this.marketprice = marketprice;
	}
	public BigDecimal getShopprice() {
		return shopprice;
	}
	public void setShopprice(BigDecimal shopprice) {
		this.shopprice = shopprice;
	}
	public String getPdescription() {
		return pdescription;
	}
	public void setPdescription(String pdescription) {
		this.pdescription = pdescription;
	}
	public int getPclickednum() {
		return pclickednum;
	}
	public void setPclickednum(int pclickednum) {
		this.pclickednum = pclickednum;
	}
	public int getPishot() {
		return pishot;
	}
	public void setPishot(int pishot) {
		this.pishot = pishot;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public ProductSecondCategory getSecondCategory() {
		return secondCategory;
	}
	public void setSecondCategory(ProductSecondCategory secondCategory) {
		this.secondCategory = secondCategory;
	}
	public TripUser getSeller() {
		return seller;
	}
	public void setSeller(TripUser seller) {
		this.seller = seller;
	}
	public String getContactnum() {
		return contactnum;
	}
	public void setContactnum(String contactnum) {
		this.contactnum = contactnum;
	}
	public String getQualitychoice() {
		return qualitychoice;
	}
	public void setQualitychoice(String qualitychoice) {
		this.qualitychoice = qualitychoice;
	}
	public String getPplaintext() {
		return pplaintext;
	}
	public void setPplaintext(String pplaintext) {
		this.pplaintext = pplaintext;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
