package invest.pojo.datapojo;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

public class Stock implements Serializable {
    private String symbol;
    @JsonProperty("net_profit_cagr")
    private double netProfitCagr;
    private double ps;
    private int type;
    //上涨百分比
    private double percent;
    @JsonProperty("has_follow")
    private boolean hasFollow;
    @JsonProperty("tick_size")
    private double tickSize;
    @JsonProperty("pb_ttm")
    private double pbTtm;
    @JsonProperty("float_shares")
    private int floatShares;
    //当前价格
    private int current;
    private double amplitude;
    private String pcf;
    //年初至今上涨百分比
    @JsonProperty("current_year_percent")
    private double currentYearPercent;
    @JsonProperty("float_market_capital")
    private int floatMarketCapital;
    //总市值
    @JsonProperty("market_capital")
    private int marketCapital;
    @JsonProperty("dividend_yield")
    private double dividendYield;
    @JsonProperty("lot_size")
    private int lotSize;
    @JsonProperty("roe_ttm")
    private double roeTtm;
    @JsonProperty("total_percent")
    private double totalPercent;
    private int percent5m;
    @JsonProperty("income_cagr")
    private double incomeCagr;
    private double amount;
    //上涨价格
    private double chg;
    @JsonProperty("issue_date_ts")
    private int issueDateTs;
    @JsonProperty("main_net_inflows")
    private int mainNetInflows;
    private int volume;
    @JsonProperty("volume_ratio")
    private double volumeRatio;
    private double pb;
    private int followers;
    //换手率
    @JsonProperty("turnover_rate")
    private double turnoverRate;
    @JsonProperty("first_percent")
    private double firstPercent;
    private String name;
    @JsonProperty("pe_ttm")
    private double peTtm;
    @JsonProperty("total_shares")
    private int totalShares;
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public String getSymbol() {
        return symbol;
    }

    public void setNetProfitCagr(double netProfitCagr) {
        this.netProfitCagr = netProfitCagr;
    }
    public double getNetProfitCagr() {
        return netProfitCagr;
    }

    public void setPs(double ps) {
        this.ps = ps;
    }
    public double getPs() {
        return ps;
    }

    public void setType(int type) {
        this.type = type;
    }
    public int getType() {
        return type;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
    public double getPercent() {
        return percent;
    }

    public void setHasFollow(boolean hasFollow) {
        this.hasFollow = hasFollow;
    }
    public boolean getHasFollow() {
        return hasFollow;
    }

    public void setTickSize(double tickSize) {
        this.tickSize = tickSize;
    }
    public double getTickSize() {
        return tickSize;
    }

    public void setPbTtm(double pbTtm) {
        this.pbTtm = pbTtm;
    }
    public double getPbTtm() {
        return pbTtm;
    }

    public void setFloatShares(int floatShares) {
        this.floatShares = floatShares;
    }
    public int getFloatShares() {
        return floatShares;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
    public int getCurrent() {
        return current;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }
    public double getAmplitude() {
        return amplitude;
    }

    public void setPcf(String pcf) {
        this.pcf = pcf;
    }
    public String getPcf() {
        return pcf;
    }

    public void setCurrentYearPercent(double currentYearPercent) {
        this.currentYearPercent = currentYearPercent;
    }
    public double getCurrentYearPercent() {
        return currentYearPercent;
    }

    public void setFloatMarketCapital(int floatMarketCapital) {
        this.floatMarketCapital = floatMarketCapital;
    }
    public int getFloatMarketCapital() {
        return floatMarketCapital;
    }

    public void setMarketCapital(int marketCapital) {
        this.marketCapital = marketCapital;
    }
    public int getMarketCapital() {
        return marketCapital;
    }

    public void setDividendYield(double dividendYield) {
        this.dividendYield = dividendYield;
    }
    public double getDividendYield() {
        return dividendYield;
    }

    public void setLotSize(int lotSize) {
        this.lotSize = lotSize;
    }
    public int getLotSize() {
        return lotSize;
    }

    public void setRoeTtm(double roeTtm) {
        this.roeTtm = roeTtm;
    }
    public double getRoeTtm() {
        return roeTtm;
    }

    public void setTotalPercent(double totalPercent) {
        this.totalPercent = totalPercent;
    }
    public double getTotalPercent() {
        return totalPercent;
    }

    public void setPercent5m(int percent5m) {
        this.percent5m = percent5m;
    }
    public int getPercent5m() {
        return percent5m;
    }

    public void setIncomeCagr(double incomeCagr) {
        this.incomeCagr = incomeCagr;
    }
    public double getIncomeCagr() {
        return incomeCagr;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public double getAmount() {
        return amount;
    }

    public void setChg(double chg) {
        this.chg = chg;
    }
    public double getChg() {
        return chg;
    }

    public void setIssueDateTs(int issueDateTs) {
        this.issueDateTs = issueDateTs;
    }
    public int getIssueDateTs() {
        return issueDateTs;
    }

    public void setMainNetInflows(int mainNetInflows) {
        this.mainNetInflows = mainNetInflows;
    }
    public int getMainNetInflows() {
        return mainNetInflows;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
    public int getVolume() {
        return volume;
    }

    public void setVolumeRatio(double volumeRatio) {
        this.volumeRatio = volumeRatio;
    }
    public double getVolumeRatio() {
        return volumeRatio;
    }

    public void setPb(double pb) {
        this.pb = pb;
    }
    public double getPb() {
        return pb;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }
    public int getFollowers() {
        return followers;
    }

    public void setTurnoverRate(double turnoverRate) {
        this.turnoverRate = turnoverRate;
    }
    public double getTurnoverRate() {
        return turnoverRate;
    }

    public void setFirstPercent(double firstPercent) {
        this.firstPercent = firstPercent;
    }
    public double getFirstPercent() {
        return firstPercent;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setPeTtm(double peTtm) {
        this.peTtm = peTtm;
    }
    public double getPeTtm() {
        return peTtm;
    }

    public void setTotalShares(int totalShares) {
        this.totalShares = totalShares;
    }
    public int getTotalShares() {
        return totalShares;
    }
}
