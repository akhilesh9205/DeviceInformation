package com.sampleapplication.deviceinformation;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ListItemsView extends RelativeLayout {

    TextView mInfoHeadingtextView;
    TextView mInfoTextview;

    public ListItemsView(Context context) {
        super(context);
        initialiseView(context);
    }

    public ListItemsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialiseView(context);
    }

    public ListItemsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialiseView(context);
    }

    private void initialiseView(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.list_items, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mInfoHeadingtextView = (TextView) this.findViewById(R.id.info_heading_textview);
        mInfoTextview = (TextView) this.findViewById(R.id.info_textview);
    }

    public void setInfoHeadingText(String heading) {
        mInfoHeadingtextView.setText(heading);
    }

    public void setInfoTextView(String info) {
        mInfoTextview.setText(info);
    }

    public void setInfoTextColor(boolean value) {
        if (value) {
            mInfoTextview.setTextColor(Color.GREEN);
        }else {
            mInfoTextview.setTextColor(Color.RED);
        }
    }
}
