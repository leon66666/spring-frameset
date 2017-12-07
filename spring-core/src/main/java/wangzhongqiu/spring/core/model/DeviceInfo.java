package wangzhongqiu.spring.core.model;

/**
 * 设备信息
 * 
 *
 */
public class DeviceInfo {

    private String imei="";//设备imei号

    private String imsi="";//SIM卡imsi号

    private String mnc="";//运营商

    private String manufacturer="";//设备厂商

    private String model="";//设备型号

    private String androidVersion="";//操作系统版本

    private int screenWidth=0;//屏幕宽度

    private int screenHeight=0;//屏幕高度

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    @Override
    public String toString() {
        return "设备信息: DeviceInfo [imei=" + imei + ", imsi=" + imsi + ", mnc=" + mnc + ", manufacturer=" + manufacturer
                + ", model=" + model + ", androidVersion=" + androidVersion + ", screenWidth=" + screenWidth
                + ", screenHeight=" + screenHeight + "]";
    }

}
