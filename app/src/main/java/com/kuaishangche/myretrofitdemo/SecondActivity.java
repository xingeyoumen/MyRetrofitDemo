package com.kuaishangche.myretrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class SecondActivity extends AppCompatActivity implements View.OnTouchListener {

    private TextView tv_second;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        gestureDetector = new GestureDetector(new MyGestureDetector());

        tv_second = findViewById(R.id.tv_second);
        tv_second.setOnTouchListener(this);
        tv_second.setFocusable(true);
        tv_second.setClickable(true);
        tv_second.setLongClickable(true);

    }

    //touch的触摸事件
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return gestureDetector.onTouchEvent(event);
    }


    //设置手势
    public class MyGestureDetector implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

        //ACTION_DOWN,ACTION_MOVE,移动速度（像素/秒）
        /*****OnGestureListener的函数*****/

        final int FLING_MIN_DISTANCE = 100, FLING_MIN_VELOCITY = 200;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //根据移动的距离和移动的速度
            if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
                    && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                // Fling left
                Log.i("MyGesture", "Fling left");
                Toast.makeText(SecondActivity.this, "Fling Left", Toast.LENGTH_SHORT).show();
            } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
                    && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                // Fling right
                Log.i("MyGesture", "Fling right");
                Toast.makeText(SecondActivity.this, "Fling Right", Toast.LENGTH_SHORT).show();
            }
            return true;
        }


        @Override
        public boolean onDown(MotionEvent e) {
            Log.d("TAG", " onDown()");
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.d("TAG", " onShowPress()");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.d("TAG", " onSingleTapUp()");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//            Log.d("TAG", " onScroll()===" + (e2.getX() - e1.getX()) + "====" + distanceX);
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.d("TAG", " onLongPress()");
        }


        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.e("TAG", "onSingleTapConfirmed()");
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.e("TAG", "onDoubleTap()");
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.e("TAG", "onDoubleTapEvent()");
            return false;
        }
    }
}
