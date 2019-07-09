package com.odbpo.fenggou.flowlayout_single;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private String[] mVals = new String[]
            {"Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android", "Weclome", "Button ImageView", "TextView", "Helloworld",
                    "Android", "Weclome Hello", "Button Text", "TextView"};

    private TagFlowLayout mFlowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LayoutInflater mInflater = LayoutInflater.from(this);
        mFlowLayout = (TagFlowLayout) findViewById(R.id.flowLayout);
        //mFlowLayout.setMaxSelectCount(3);

        mFlowLayout.setAdapter(new TagAdapter<String>(mVals) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        mFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        });

        //修改单选相关逻辑bug
        TagAdapter tagAdapter = mFlowLayout.getAdapter();
        tagAdapter.setSelectedList(0);//默认选中第一个
        mFlowLayout.getChildAt(0).setClickable(false);//设置第一个不可点击

        mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                setTagClickable(position, parent);//修改单选相关逻辑bug
                Toast.makeText(MainActivity.this, mVals[position], Toast.LENGTH_SHORT).show();
                //view.setVisibility(View.GONE);
                return true;
            }
        });

        mFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                setTitle("choose:" + selectPosSet.toString());
            }
        });
    }

    //修改单选相关逻辑bug
    private void setTagClickable(int position, FlowLayout parent) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            if (i == position) {
                parent.getChildAt(i).setClickable(false);
            } else {
                parent.getChildAt(i).setClickable(true);
            }
        }
    }
}
