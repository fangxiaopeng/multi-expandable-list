package fxp.com.multiexpandablelist.bean;

/**
 *
 * 设备实体类
 *
 * Created by fxp on 2018/3/2.
 */

public class EquipmentInfo {

    private String e_id;

    private int e_index;

    private String e_name;

    private String e_state;

    private String e_remark;

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    public int getE_index() {
        return e_index;
    }

    public void setE_index(int e_index) {
        this.e_index = e_index;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getE_state() {
        return e_state;
    }

    public void setE_state(String e_state) {
        this.e_state = e_state;
    }

    public String getE_remark() {
        return e_remark;
    }

    public void setE_remark(String e_remark) {
        this.e_remark = e_remark;
    }

    @Override
    public String toString() {
        return "EquipmentInfo{" +
                "e_id='" + e_id + '\'' +
                ", e_index=" + e_index +
                ", e_name='" + e_name + '\'' +
                ", e_state='" + e_state + '\'' +
                ", e_remark='" + e_remark + '\'' +
                '}';
    }

}
