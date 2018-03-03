package fxp.com.multiexpandablelist.entity;

/**
 * 多选型View - 标签entity
 * <p>
 * Created by fxp on 2018/3/3.
 */

public class CheckItem {

    private int ITEM_NORMAL = 0;

    private int ITEM_ERROR = 1;

    private int ITEM_NOTCHECK = 2;

    private String itemLable = null;

    private int itemState = 2;

    private boolean isSelect = true;

    public int getItemState() {
        return itemState;
    }

    public void setItemState(int itemState) {
        this.itemState = itemState;
    }

    public String getItemLable() {
        return itemLable;
    }

    public void setItemLable(String itemLable) {
        this.itemLable = itemLable;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }
}
