package cn.shper.mvppandemo.net.entity;

/**
 * Author: Shper
 * Description: 天气详情类
 * Version: V0.1 2016/12/28
 */
public class WeatherInfo {

    private String city; // 杭州
    private String cityid; // 101210101
    private String temp; // 16
    private String WD; // 北风
    private String WS; // 2级
    private String SD; // 95%
    private String WSE; // 2
    private String time; // 10:25
    private String isRadar; // 1
    private String Radar; // JC_RADAR_AZ9571_JB
    private String njd; // 暂无实况
    private String qy; // 1006
    private long updateTime;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWD() {
        return WD;
    }

    public void setWD(String WD) {
        this.WD = WD;
    }

    public String getWS() {
        return WS;
    }

    public void setWS(String WS) {
        this.WS = WS;
    }

    public String getSD() {
        return SD;
    }

    public void setSD(String SD) {
        this.SD = SD;
    }

    public String getWSE() {
        return WSE;
    }

    public void setWSE(String WSE) {
        this.WSE = WSE;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIsRadar() {
        return isRadar;
    }

    public void setIsRadar(String isRadar) {
        this.isRadar = isRadar;
    }

    public String getRadar() {
        return Radar;
    }

    public void setRadar(String radar) {
        Radar = radar;
    }

    public String getNjd() {
        return njd;
    }

    public void setNjd(String njd) {
        this.njd = njd;
    }

    public String getQy() {
        return qy;
    }

    public void setQy(String qy) {
        this.qy = qy;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "城市：" + city
                + "；风向：" + WD
                + "；风级：" + WS
                + "；湿度：" + SD
                + "；温度：" + WSE
                + "；数据时间：" + time
                + "; 更新时间：" + updateTime;
    }
}
