package fxp.com.multiexpandablelist.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

import fxp.com.multiexpandablelist.bean.EquipmentInfo;
import fxp.com.multiexpandablelist.bean.ItemInfo;

/**
 *
 * ExpandableListView 适配器
 *
 * Created by fxp on 2018/3/2.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;

    private List<EquipmentInfo> groupList;

    private List<List<ItemInfo>> childrenList;

    public ExpandableListViewAdapter(Context context, List<EquipmentInfo> groupList, List<List<ItemInfo>> childrenList){
        this.context = context;
        this.groupList = groupList;
        this.childrenList = childrenList;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public Object getGroup(int i) {
        return groupList.get(i);
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public int getChildrenCount(int i) {
        return childrenList.get(i).size();
    }

    @Override
    public Object getChild(int i, int i1) {
        return childrenList.get(i).get(i1);
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
