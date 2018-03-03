package fxp.com.multiexpandablelist.bean;

/**
 * 设备参数实体类
 * <p>
 * Created by fxp on 2018/3/2.
 */

public class ItemInfo {

    public static String STATE_CORRECT = "0";

    public static String STATE_ERROR = "1";

    private String i_id;

    private String e_id;

    private String i_index;

    private String i_name;

    private String i_type;

    private String i_value;

    private String i_state;

    private String i_reference;

    private String i_content;

    private String i_content_time;

    private String i_remark;

    private String i_increment;

    private String i_unit;

    public String getI_unit() {
        return i_unit;
    }

    public void setI_unit(String i_unit) {
        this.i_unit = i_unit;
    }

    public String getI_id() {
        return i_id;
    }

    public void setI_id(String i_id) {
        this.i_id = i_id;
    }

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    public String getI_index() {
        return i_index;
    }

    public void setI_index(String i_index) {
        this.i_index = i_index;
    }

    public String getI_name() {
        return i_name;
    }

    public void setI_name(String i_name) {
        this.i_name = i_name;
    }

    public String getI_type() {
        return i_type;
    }

    public void setI_type(String i_type) {
        this.i_type = i_type;
    }

    public String getI_value() {
        return i_value;
    }

    public void setI_value(String i_value) {
        this.i_value = i_value;
    }

    public String getI_state() {
        return i_state;
    }

    public void setI_state(String i_state) {
        this.i_state = i_state;
    }

    public String getI_reference() {
        return i_reference;
    }

    public void setI_reference(String i_reference) {
        this.i_reference = i_reference;
    }

    public String getI_content() {
        return i_content;
    }

    public void setI_content(String i_content) {
        this.i_content = i_content;
    }

    public String getI_content_time() {
        return i_content_time;
    }

    public void setI_content_time(String i_content_time) {
        this.i_content_time = i_content_time;
    }

    public String getI_remark() {
        return i_remark;
    }

    public void setI_remark(String i_remark) {
        this.i_remark = i_remark;
    }

    public String getI_increment() {
        return i_increment;
    }

    public void setI_increment(String i_increment) {
        this.i_increment = i_increment;
    }

    @Override
    public String toString() {
        return "ItemInfo{" +
                "i_id='" + i_id + '\'' +
                ", e_id='" + e_id + '\'' +
                ", i_index='" + i_index + '\'' +
                ", i_name='" + i_name + '\'' +
                ", i_type='" + i_type + '\'' +
                ", i_value='" + i_value + '\'' +
                ", i_state='" + i_state + '\'' +
                ", i_reference='" + i_reference + '\'' +
                ", i_content='" + i_content + '\'' +
                ", i_content_time='" + i_content_time + '\'' +
                ", i_remark='" + i_remark + '\'' +
                ", i_increment='" + i_increment + '\'' +
                ", i_unit='" + i_unit + '\'' +
                '}';
    }

}
