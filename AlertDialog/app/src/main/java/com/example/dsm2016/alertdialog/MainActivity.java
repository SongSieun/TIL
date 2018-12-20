package com.example.dsm2016.alertdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CharSequence[] chkItems = {"item1", "item2", "item3"};
        final boolean[] chkSts = {true, false, false};
        Button button = (Button)findViewById(R.id.button);
        Button button1 = (Button)findViewById(R.id.button1);
        final LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View layout = inflater.inflate(R.layout.dialog_contents_item, (ViewGroup)findViewById(R.id.layout_root));
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(MainActivity.this);
                alertDlg.setTitle("대화상자 타이틀");
                //alertDlg.setMessage("메시지");
                alertDlg.setView(layout);
                alertDlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText text = (EditText)layout.findViewById(R.id.edit_text);
                        String string = text.getText().toString();
                        Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
                    }
                });
                alertDlg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDlg.create().show();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder checkDlg = new AlertDialog.Builder(MainActivity.this);
                checkDlg.setTitle("타이틀");
                checkDlg.setMultiChoiceItems(chkItems, chkSts, new DialogInterface.OnMultiChoiceClickListener() {
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
