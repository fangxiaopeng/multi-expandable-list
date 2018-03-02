package fxp.com.multiexpandablelist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import fxp.com.multiexpandablelist.R;
import fxp.com.multiexpandablelist.bean.EquipmentInfo;
import fxp.com.multiexpandablelist.bean.ItemInfo;

/**
 * ExpandableListView 适配器
 * <p>
 * Created by fxp on 2018/3/2.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;

    private LayoutInflater mInflater;

    private List<EquipmentInfo> groupList;

    private List<List<ItemInfo>> childrenList;

    private final int EDITTYPE = 0;

    public ExpandableListViewAdapter(Context context, List<EquipmentInfo> groupList, List<List<ItemInfo>> childrenList) {
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
        GroupViewHolder holder;
        if (convertView == null) {
            holder = new GroupViewHolder();
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.module_list_outer_item, null);
            holder.lable = (TextView) convertView.findViewById(R.id.lable);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }

        holder.lable.setText(groupList.get(groupPosition).getE_name());
        return convertView;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        return Integer.parseInt(childrenList.get(groupPosition).get(childPosition).getI_type());
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
        int type = getChildType(groupPosition, childPosition);
        switch (type) {
            //数值型
            case EDITTYPE: {
                convertView = setEditCell(convertView, groupPosition, childPosition);
                break;
            }
            default:
                break;
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    /**
     * 数值型View
     *
     * @param convertView
     * @param groupPosition
     * @param childPosition
     * @return
     */
    private View setEditCell(View convertView, final int groupPosition, final int childPosition) {
        EditViewHolder editViewHolder = null;
        final EditText tsEditText;

        if (null != convertView) {
            ((LinearLayout) convertView).removeAllViews();
        }

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.module_list_inner_edit_item, null);
        editViewHolder = new EditViewHolder();
        editViewHolder.lable = (TextView) convertView.findViewById(R.id.lable);
        editViewHolder.value = (EditText) convertView.findViewById(R.id.value);

        editViewHolder.lable.setText(childrenList.get(groupPosition).get(childPosition).getI_name());
        editViewHolder.value.setHint("");

        return convertView;
    }

    class GroupViewHolder {
        private TextView lable;
        private ImageView stateIcon;
    }

    class EditViewHolder {
        private TextView lable;
        private EditText value;
    }
}
