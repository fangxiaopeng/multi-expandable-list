package fxp.com.multiexpandablelist.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import fxp.com.multiexpandablelist.R;

/**
 * 单选型View - 选项适配器
 * <p>
 * Created by fxp on 2018/3/3.
 */

public class TagBaseAdapter extends BaseAdapter {

    private Context mContext;

    // 选项列表
    private List<String> mList;

    // 当前选中状态
    private String itemState;

    public TagBaseAdapter(Context context, List<String> list, String itemState) {
        this.mContext = context;
        this.mList = list;
        this.itemState = itemState;
    }

    public void setItemState(String itemState) {
        this.itemState = itemState;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_tagview_item, null);
            holder = new ViewHolder();
            holder.tagBtn = (TextView) convertView.findViewById(R.id.tag_btn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tagBtn.setText(getItem(position));

        if (position == 0 && itemState.equals("正常")) {
            holder.tagBtn.setTextColor(mContext.getResources().getColor(R.color.state_ok));
            holder.tagBtn.setBackground(mContext.getResources().getDrawable(R.drawable.tag_view_ok));
        } else if (position == 1 && itemState.equals("异常")) {
            holder.tagBtn.setTextColor(mContext.getResources().getColor(R.color.state_error));
            holder.tagBtn.setBackground(mContext.getResources().getDrawable(R.drawable.tag_view_error));
        } else {
            holder.tagBtn.setTextColor(mContext.getResources().getColor(R.color.light_gray));
            holder.tagBtn.setBackground(mContext.getResources().getDrawable(R.drawable.tag_view));
        }

        return convertView;
    }

    static class ViewHolder {
        TextView tagBtn;
    }
}