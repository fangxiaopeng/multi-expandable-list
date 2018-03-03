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
import fxp.com.multiexpandablelist.entity.CheckItem;

/**
 * 多选型View - 选项适配器
 * <p>
 * Created by fxp on 2018/3/3.
 */
public class TagCheckAdapter extends BaseAdapter {

    private Context mContext;

    private List<CheckItem> checkItems;

//    boolean isSelect = false;


    public TagCheckAdapter(Context context, List<CheckItem> checkItems) {
        this.mContext = context;
        this.checkItems = checkItems;
    }

    @Override
    public int getCount() {
        return checkItems.size();
    }

    @Override
    public CheckItem getItem(int position) {
        return checkItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setIsSelect(int position, boolean isSelect) {
        checkItems.get(position).setIsSelect(isSelect);
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
        holder.tagBtn.setText(checkItems.get(position).getItemLable());

        boolean isSelect = checkItems.get(position).isSelect();
        if (isSelect) {
            holder.tagBtn.setTextColor(mContext.getResources().getColor(R.color.state_ok));
            holder.tagBtn.setBackground(mContext.getResources().getDrawable(R.drawable.tag_view_ok));
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
