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

        List<ItemInfo> itemInfos = new ArrayList<ItemInfo>();

        ItemInfo itemInfo1 = new ItemInfo();
        itemInfo1.setE_id("1");
        itemInfo1.setI_id("1_0");
        itemInfo1.setI_type("0");
        itemInfo1.setI_name("机身温度");
        itemInfo1.setI_increment("0.5");
        itemInfo1.setI_reference("-10~60");
        itemInfo1.setI_content("");
        itemInfos.add(itemInfo1);

        ItemInfo itemInfo2 = new ItemInfo();
        itemInfo2.setE_id("2");
        itemInfo2.setI_id("2_1");
        itemInfo2.setI_type("1");
        itemInfo2.setI_name("播放内容");
        itemInfo2.setI_content("海贼王万国篇-路飞VS卡二");
        itemInfos.add(itemInfo2);

/*        ItemInfo itemInfo3 = new ItemInfo();
        itemInfo3.setE_id("3");
        itemInfo3.setI_id("3_1");
        itemInfo3.setI_type("2");
        itemInfo3.setI_name("播放内容");
        itemInfo3.setI_content("海贼王万国篇-路飞VS卡二");
        itemInfos.add(itemInfo3);*/

        childrenList = new ArrayList<List<ItemInfo>>();
        for (int i = 0; i < 8; i++) {
            childrenList.add(itemInfos);
        }
    }

}
