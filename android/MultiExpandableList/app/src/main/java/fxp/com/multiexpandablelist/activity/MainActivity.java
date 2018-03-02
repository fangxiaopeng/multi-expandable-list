package fxp.com.multiexpandablelist.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import fxp.com.multiexpandablelist.R;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private ExpandableListView expandableListView;

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

    }

    private void initViews(){

        findViews();



    }

    private void initListeners(){

    }

    private void findViews(){
        expandableListView = (ExpandableListView)findViewById(R.id.expandable_lv);
    }

}
