package fxp.com.multiexpandablelist.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fxp.com.multiexpandablelist.R;
import fxp.com.multiexpandablelist.adapter.ExpandableListViewAdapter;
import fxp.com.multiexpandablelist.bean.EquipmentInfo;
import fxp.com.multiexpandablelist.bean.ItemInfo;
import fxp.com.multiexpandablelist.fxpInterface.GetPictureListener;

public class MainActivity extends AppCompatActivity implements GetPictureListener {

    private String TAG = "MainActivity";

    private Context context;

    private ExpandableListView expandableListView;

    private ExpandableListViewAdapter expandableListViewAdapter;

    private List<EquipmentInfo> groupList = null;

    private List<List<ItemInfo>> childrenList = null;

    public static final String IMAGE_PATH = "fxpFiles";

    /* 相机请求码 */
    private static final int REQUEST_CAMERA = 0;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();

        initViews();

        initListeners();

//        solveExceptionByVmPolicy();
    }

    private void initDatas() {

        context = getApplicationContext();

        getGroupListData();

        getChildrenListData();
    }

    private void initViews() {

        findViews();

        initExpandableListView();

    }

    private void initListeners() {
        expandableListViewAdapter.setGetPictureListener(this);
    }

    private void findViews() {
        expandableListView = (ExpandableListView) findViewById(R.id.expandable_lv);
    }

    /**
     * 初始化ExpandableListView
     */
    private void initExpandableListView() {
        expandableListViewAdapter = new ExpandableListViewAdapter(context, groupList, childrenList);
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
            groupList.add(i, equipmentInfo);
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

        ItemInfo itemInfo3 = new ItemInfo();
        itemInfo3.setE_id("3");
        itemInfo3.setI_id("3_1");
        itemInfo3.setI_type("2");
        itemInfo3.setI_name("画面色彩");
        itemInfo3.setI_content("");
        itemInfos.add(itemInfo3);

        ItemInfo itemInfo2 = new ItemInfo();
        itemInfo2.setE_id("2");
        itemInfo2.setI_id("2_1");
        itemInfo2.setI_type("1");
        itemInfo2.setI_name("播放内容");
        itemInfo2.setI_content("海贼王万国篇-路飞VS卡二");
        itemInfos.add(itemInfo2);

        ItemInfo itemInfo4 = new ItemInfo();
        itemInfo4.setE_id("4");
        itemInfo4.setI_id("4_1");
        itemInfo4.setI_type("3");
        itemInfo4.setI_name("体验评价");
        itemInfo4.setI_content("播放流畅/音质很好/画质一般/屏幕反光/机身温度过高");
        itemInfo4.setI_value("音质很好/机身温度过高");
        itemInfos.add(itemInfo4);

        ItemInfo itemInfo5 = new ItemInfo();
        itemInfo5.setE_id("5");
        itemInfo5.setI_id("5_1");
        itemInfo5.setI_type("4");
        itemInfo5.setI_name("机身状况");
        itemInfo5.setI_content("");
        itemInfos.add(itemInfo5);

        childrenList = new ArrayList<List<ItemInfo>>();
        for (int i = 0; i < 8; i++) {
            childrenList.add(itemInfos);
        }
    }

    @Override
    public void takePicture(int groupPosition, int childPosition) {
        Toast.makeText(context, "takePicture", Toast.LENGTH_SHORT).show();

        //判断是否有权限
        if (hasPermission(Manifest.permission.CAMERA, Manifest.permission.CAMERA)) {
            //有权限
            takePhoto();
        } else {
            //没权限，进行权限请求
            requestPermission(REQUEST_CAMERA, Manifest.permission.CAMERA);
        }
    }

    @Override
    public void selectPicture(int groupPosition, int childPosition) {
        Toast.makeText(context, "selectPicture", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int req, int res, Intent data) {
        switch (req) {
            case REQUEST_CAMERA:
                if (res == RESULT_OK) {
                    Log.i(TAG, "拍照成功");

                } else {
                    Log.i(TAG, "拍照失败");
                }
                break;
            default:
                break;
        }
    }

    /**
     * 调用系统相机拍照
     */
    private void takePhoto() {
        String imgName = System.currentTimeMillis() + ".jpg";
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getPhotoUri(IMAGE_PATH, imgName));
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    /**
     * 获取照片Uri
     *
     * @param path 保存照片文件夹名称
     * @param name 照片名称
     * @return
     */
    private Uri getPhotoUri(String path, String name) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        File file = new File(Environment.getExternalStorageDirectory(), path);
        if (!file.exists()) {
            file.mkdir();
        }
        File output = new File(file, name);
        try {
            if (output.exists()) {
                output.delete();
            }
            output.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 解决Android7.0相机权限问题方法1 - onCreate中调用solveExceptionByVmPolicy()
//        Uri photoURI = Uri.fromFile(output);

        // 解决Android7.0相机权限问题方法2 - FileProvider方式（谷歌官方推荐）
        Uri photoURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", output);

        return photoURI;
    }

    /**
     * 判断是否拥有权限
     *
     * @param permissions 形参String...的效果其实就和数组一样，这里的实参可以写多个String
     * @return
     */
    public boolean hasPermission(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }

    /**
     * 请求权限
     */
    protected void requestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
        Toast.makeText(context, "如果拒绝授权,会导致应用无法正常使用", Toast.LENGTH_SHORT).show();
    }

    /**
     * 申请权限的回调
     *
     * @param requestCode  requestCode
     * @param permissions  permissions
     * @param grantResults grantResults 多个权限一起返回
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "获取相机权限成功");
                takePhoto();
            } else {
                Log.i(TAG, "获取相机权限失败");
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * android 7.0系统解决拍照的问题
     * Android 7.0开始，一个应用提供自身文件给其它应用使用时，如果给出一个file://格式的URI的话，应用会抛出FileUriExposedException
     * onCreate中调用此方法即可
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void solveExceptionByVmPolicy() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }
}
