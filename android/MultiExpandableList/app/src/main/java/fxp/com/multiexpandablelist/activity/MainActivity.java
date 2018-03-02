package fxp.com.multiexpandablelist.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import fxp.com.multiexpandablelist.R;
import fxp.com.multiexpandablelist.adapter.ExpandableListViewAdapter;
import fxp.com.multiexpandablelist.bean.EquipmentInfo;
import fxp.com.multiexpandablelist.bean.ItemInfo;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private ExpandableListView expandableListView;

    private ExpandableListViewAdapter expandableListViewAdapter;

    private List<EquipmentInfo> groupList = null;

    private List<List<ItemInfo>> childrenList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();

        initViews();

        initListeners();
    }

    private void initDatas(){

        context = getApplicationContext();

        getGroupListData();

        getChildrenListData();

    }

    private void initViews(){

        findViews();

        initExpandableListView();

    }

    private void initListeners(){

    }

    private void findViews(){
        expandableListView = (ExpandableListView)findViewById(R.id.expandable_lv);
    }

    /**
     *  初始化ExpandableListView
     */
    private void initExpandableListView(){
        expandableListViewAdapter = new ExpandableListViewAdapter(context,groupList,childrenList);
        expandableListView.setAdapter(expandableListViewAdapter);
        expandableListView.setGroupIndicator(null);
        //设置默认为展开状态
        for (int i = 0; i < expandableListViewAdapter.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }
    }

    /**
     * 模拟ExpandableListView一级列表数据
     */
    private void getGroupListData() {
        groupList = new ArrayList<EquipmentInfo>();
        for (int i = 0; i < 8; i++) {
            EquipmentInfo equipmentInfo = new EquipmentInfo();
            equipmentInfo.setE_id("" + i);
            equipmentInfo.setE_name("小米电视A4");
            groupList.add(i,equipmentInfo);
        }
    }

    /**
     * 模拟ExpandableListView二级列表数据
     */
    private void getChildrenListData() {
        childrenList = new ArrayList<List<ItemInfo>>();
        for (int i = 0; i < 8; i++) {
            List<ItemInfo> itemInfos = new ArrayList<ItemInfo>();
            ItemInfo itemInfo = new ItemInfo();
            itemInfo.setE_id("" + i);
            itemInfo.setI_id(i + "_0");
            itemInfo.setI_name("机身温度");
            itemInfo.setI_content("25");
            itemInfos.add(itemInfo);
            childrenList.add(i,itemInfos);
        }
    }

}
