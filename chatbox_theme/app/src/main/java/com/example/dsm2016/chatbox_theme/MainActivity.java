package com.example.dsm2016.chatbox_theme;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Dialog mDialog;
    private Button addTodoList, checkTodoList;
    private TextView todoResult;
    private String currentDate;

    DBHelper dbHelper = new DBHelper(getApplicationContext(), "Todolist.db", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CharSequence[] chkItems = {"item1", "item2", "item3"};
        final boolean[] chkSts = {true, false, false};

        addTodoList = (Button) findViewById(R.id.addToDoList);
        checkTodoList = (Button) findViewById(R.id.checkToDoList);
        todoResult = (TextView) findViewById(R.id.todoResult);

        // 날짜는 현재 날짜로 고정
        // 현재 시간 구하기
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        // 출력될 포맷 설정
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        currentDate = simpleDateFormat.format(date);

        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        addTodoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View todoLayout = inflater.inflate(R.layout.dialog_button_item, (ViewGroup) findViewById(R.id.layout_root));
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(MainActivity.this);
                alertDlg.setTitle("할 일 추가하기");
                alertDlg.setView(todoLayout);
                alertDlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText todoText = (EditText) todoLayout.findViewById(R.id.toDoText);
                        String todoString = todoText.getText().toString();
                        dbHelper.insert(currentDate, todoString);
                        todoResult.setText(dbHelper.getResult());
                    }
                });
                alertDlg.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDlg.create().show();
            }
        });
        checkTodoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder checkDlg = new AlertDialog.Builder(MainActivity.this);
                checkDlg.setTitle("확인하기");
                checkDlg.setMultiChoiceItems(chkItems, chkSts, new DialogInterface.OnMultiChoiceClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                    }
                });
                checkDlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                checkDlg.create().show();
            }
        });
    }
}
