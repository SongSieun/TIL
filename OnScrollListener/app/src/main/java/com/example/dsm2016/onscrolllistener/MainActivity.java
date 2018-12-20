package com.example.dsm2016.onscrolllistener;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity implements AbsListView.OnScrollListener {

    private ArrayAdapter<String> mAdapter;
    private AsyncTask<String, Void, String> mTask;
    private ListView mListView;
    private View mFooter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.ListView);

        // 간단한 리스트를 표시할 Adapter 생성
        mAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        // 테스트 데이터를 추가
        for (int i = 1; i < 10; i++) {
            mAdapter.add("Item" + i);
        }

        // 읽기 중의 푸터(바닥글)을 생성
        mFooter = getLayoutInflater().inflate(
                R.layout.list_progress_item, null);

        // ListView에 푸터를 설정
        mListView.addFooterView(mFooter);

        // 어댑터를 설정
        mListView.setAdapter(mAdapter);

        // 스크롤리스너를 설정
        mListView.setOnScrollListener(this);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        // 마지막인 경우 다음 데이터를 읽음
        if (totalItemCount == firstVisibleItem + visibleItemCount) {
            additionalReading();
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView arg0, int arg1) {
    }

    private void additionalReading() {
        // 이미 다 읽었으면 건너 뜀
        if (mTask != null && mTask.getStatus() == AsyncTask.Status.RUNNING) {
            return;
        }
        /*
         * 보통은 네트워크나 파일에서 데이터를 읽기 위해 비동기로 읽기 처리를 구현한다.
         * 본 샘플에서는 간략화를 위해 비동기 처리는 그대로 두고, 데이터 읽기는 의도적으로 지연이 발생하게 구현하였다.
         */
        mTask = new MyAsyncTask(this).execute("text");
    }

    public class MyAsyncTask extends AsyncTask<String, Void, String> {

        public MyAsyncTask(MainActivity androidAsyncTaskActivity) {
        }

        protected String doInBackground(String... params) {
            // 2초간 정지
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return params[0];
        }

        @Override
        protected void onPostExecute(String text) {
            // 데이터 추가
            for (int n = 0; n < 10; n++) {
                mAdapter.add(text + n);
            }
        }
    }
}
/*
public class MainActivity extends Activity implements AbsListView.OnScrollListener{
    private ArrayAdapter<String> mAdapter;
    private AsyncTask<String, Void, String> mTask;
    private ListView mListView;
    private View footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView)findViewById(R.id.ListView);

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        for (int i = 1; i < 10; i++){
            mAdapter.add("Item"+ i);
        }

        footer = getLayoutInflater().inflate(R.layout.list_progress_item, null);
        mListView.addFooterView(footer);
        mListView.setAdapter(mAdapter);
        mListView.setOnScrollListener(this);
    }
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount){
        if (totalItemCount == firstVisibleItem + visibleItemCount){
            additionalReading();
        }
    }
    
    @Override
    public void onScrollStateChanged(AbsListView arg0, int arg1){}
    
    private void additionalReading(){
        if(mTask != null && mTask.getStatus() == AsyncTask.Status.RUNNING){
            return;
        }
        mTask = new MyAsyncTask(this).execute("text");
    }
    public class MyAsyncTask extends AsyncTask<String, Void, String>{
        public MyAsyncTask(MainActivity androidAsyncTaskActiviry){

        }
        protected String dolnBackground(String... params){
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return params[0];
        }

        @Override
        protected void onPostExecute(String text){
            for (int n = 0; n < 10; n++){
                mAdapter.add(text + n);
            }
        }
    }
}
*/