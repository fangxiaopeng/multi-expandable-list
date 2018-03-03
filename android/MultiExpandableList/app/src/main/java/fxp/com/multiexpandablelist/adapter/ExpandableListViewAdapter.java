package fxp.com.multiexpandablelist.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
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

    private final int TEXTTYPE = 1;

    private final int SELECTTYPE = 2;


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
            convertView = mInflater.inflate(R.layout.list_outer_item, null);
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
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    /**
     * 包含多种类型item布局时，必须重写此方法
     * <p>
     * 注意：此处返回值必须大于或等于布局种类数，不然会报错FATAL EXCEPTION: main Process: fxp.com.multiexpandablelist, PID: 28232
     * java.lang.ArrayIndexOutOfBoundsException: length=2; index=2
     * at android.widget.AbsListView$RecycleBin.addScrapView(AbsListView.java:6902)
     * at android.widget.ListView.measureHeightOfChildren(ListView.java:1338)
     * at android.widget.ListView.onMeasure(ListView.java:1233)
     *
     * @return 子布局种类数目
     */
    @Override
    public int getChildTypeCount() {
        return 6;
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
            //字符型
            case TEXTTYPE: {
                convertView = setTextCell(convertView, groupPosition, childPosition);
                break;
            }
            //单选型
            case SELECTTYPE: {
                convertView = setSelectCell(convertView, groupPosition, childPosition);
                break;
            }
            default:
                break;
        }

        return convertView;
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
        convertView = inflater.inflate(R.layout.list_inner_edit_item, null);
        editViewHolder = new EditViewHolder();
        editViewHolder.decrease = (TextView) convertView.findViewById(R.id.decrease_btn);
        editViewHolder.lable = (TextView) convertView.findViewById(R.id.lable);
        editViewHolder.value = (EditText) convertView.findViewById(R.id.value);
        editViewHolder.increase = (TextView) convertView.findViewById(R.id.increase_btn);

        tsEditText = (EditText) convertView.findViewById(R.id.value);

        ItemInfo itemInfo = childrenList.get(groupPosition).get(childPosition);
        editViewHolder.lable.setText(itemInfo.getI_name());
        editViewHolder.decrease.setText("-" + itemInfo.getI_increment());
        editViewHolder.value.setHint(itemInfo.getI_reference());
        editViewHolder.increase.setText("+" + itemInfo.getI_increment());

        //界面重绘后，让输入框显示重绘前输入的内容
        editViewHolder.value.setText(childrenList.get(groupPosition).get(childPosition).getI_content());

        editViewHolder.value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //将输入框中内容保存，界面重绘后，让输入框显示保存的内容
                childrenList.get(groupPosition).get(childPosition).setI_content(tsEditText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return convertView;
    }

    /**
     * 字符型View
     *
     * @param convertView
     * @param groupPosition
     * @param childPosition
     * @return
     */
    private View setTextCell(View convertView, final int groupPosition, final int childPosition) {
        TextViewHolder textViewHolder = null;
        final EditText tsEditText;

        if (null != convertView) {
            ((LinearLayout) convertView).removeAllViews();
        }

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_inner_text_item, null);
        textViewHolder = new TextViewHolder();
        textViewHolder.lable = (TextView) convertView.findViewById(R.id.lable);
        textViewHolder.value = (EditText) convertView.findViewById(R.id.value);

        tsEditText = (EditText) convertView.findViewById(R.id.value);

        textViewHolder.lable.setText(childrenList.get(groupPosition).get(childPosition).getI_name());
        textViewHolder.value.setHint("");
        //界面重绘后，让输入框显示重绘前输入的内容
        textViewHolder.value.setText(childrenList.get(groupPosition).get(childPosition).getI_content());

        textViewHolder.value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //将输入框中内容保存，界面重绘后，让输入框显示保存的内容
                childrenList.get(groupPosition).get(childPosition).setI_content(tsEditText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return convertView;
    }

    /**
     * 单选型View
     *
     * @param convertView
     * @param groupPosition
     * @param childPosition
     * @return
     */
    private View setSelectCell(View convertView, final int groupPosition, final int childPosition) {
        SelectViewHolder selectViewHolder = null;

        if (null != convertView) {
            ((LinearLayout) convertView).removeAllViews();
        }
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_inner_select_item, null);

        selectViewHolder = new SelectViewHolder();

        return convertView;
    }


    class GroupViewHolder {
        private TextView lable;
        private ImageView stateIcon;
    }

    class EditViewHolder {
        private TextView decrease;
        private TextView lable;
        private EditText value;
        private TextView increase;
    }

    class TextViewHolder {
        private TextView lable;
        private EditText value;
    }

    class SelectViewHolder {
        TextView lable;

    }
}
