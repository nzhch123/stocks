package invest.pojo.datapojo;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 *  NationalBond
 * </pre>
 * @author toolscat.com
 * @verison $Id: NationalBond v 0.1 2021-02-08 11:51:59
 */
public class NationalBond implements Serializable {

    /**
     * <pre>
     *
     * </pre>
     */
    private String	symbol;

    /**
     * <pre>
     *
     * </pre>
     */
    private Integer	chg;

    /**
     * <pre>
     * maturityTime
     * </pre>
     */
    private Date maturityTime;

    /**
     * <pre>
     *
     * </pre>
     */
    private String	rating;

    /**
     * <pre>
     * termtomaturity
     * </pre>
     */
    private String	termtomaturity;

    /**
     * <pre>
     *
     * </pre>
     */
    private Integer	type;

    /**
     * <pre>
     *
     * </pre>
     */
    private Double	percent;

    /**
     * <pre>
     *
     * </pre>
     */
    private String	hasFollow;

    /**
     * <pre>
     *
     * </pre>
     */
    private Double	tickSize;

    /**
     * <pre>
     *
     * </pre>
     */
    private Integer	volume;

    /**
     * <pre>
     *
     * </pre>
     */
    private Integer	current;

    /**
     * <pre>
     *
     * </pre>
     */
    private Double	couponRate;

    /**
     * <pre>
     * 16南山04
     * </pre>
     */
    private String	name;

    /**
     * <pre>
     *
     * </pre>
     */
    private Integer	lotSize;


    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getChg() {
        return this.chg;
    }

    public void setChg(Integer chg) {
        this.chg = chg;
    }

    public Date getMaturityTime() {
        return this.maturityTime;
    }

    public void setMaturityTime(Date maturityTime) {
        this.maturityTime = maturityTime;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTermtomaturity() {
        return this.termtomaturity;
    }

    public void setTermtomaturity(String termtomaturity) {
        this.termtomaturity = termtomaturity;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getPercent() {
        return this.percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public String getHasFollow() {
        return this.hasFollow;
    }

    public void setHasFollow(String hasFollow) {
        this.hasFollow = hasFollow;
    }

    public Double getTickSize() {
        return this.tickSize;
    }

    public void setTickSize(Double tickSize) {
        this.tickSize = tickSize;
    }

    public Integer getVolume() {
        return this.volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getCurrent() {
        return this.current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Double getCouponRate() {
        return this.couponRate;
    }

    public void setCouponRate(Double couponRate) {
        this.couponRate = couponRate;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLotSize() {
        return this.lotSize;
    }

    public void setLotSize(Integer lotSize) {
        this.lotSize = lotSize;
    }

}
