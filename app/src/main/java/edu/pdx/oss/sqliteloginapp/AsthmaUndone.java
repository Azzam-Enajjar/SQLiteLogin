package edu.pdx.oss.sqliteloginapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class AsthmaUndone extends AppCompatActivity {
    EditText DATE_TEXT;
    DatePicker DATE_PICKER;
    Button CHANGE_DATE_BUTTON, SUBMIT_BUTTON, DELETE_BUTTON, DELETE_ALL_BUTTON, SCORE_BUTTON;
    GridView GRIDVIEW;
    Calendar calendar = Calendar.getInstance();
    Context ctx = this;
    List<String> li1;
    ArrayAdapter<String> dataAdapter;
    Integer pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asthma_undone);

        DATE_TEXT = (EditText) findViewById(R.id.dateText);
        DATE_PICKER = (DatePicker) findViewById(R.id.datePicker);
        CHANGE_DATE_BUTTON = (Button) findViewById(R.id.changeDateButton);
        SUBMIT_BUTTON = (Button) findViewById(R.id.submitButton);
        DELETE_BUTTON = (Button) findViewById(R.id.deleteButton);
        SCORE_BUTTON = (Button) findViewById(R.id.scoreButton);
        GRIDVIEW = (GridView) findViewById(R.id.gridView);
        DELETE_ALL_BUTTON = (Button) findViewById(R.id.button1);

        DATE_PICKER.setVisibility(View.INVISIBLE);

        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        DATE_TEXT.setText(sdf.format(new Date()));

        li1 = new ArrayList<String>();

        dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, li1);
        dataAdapter.setDropDownViewResource(R.layout.activity_asthma_undone);

        displayDataOnGridView();

        CHANGE_DATE_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DATE_PICKER.setVisibility(View.VISIBLE);
                new DatePickerDialog(AsthmaUndone.this, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        SUBMIT_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date dateObject = new Date();
                try {
                    String done_date = DATE_TEXT.getText().toString();
                    dateObject = formatter.parse(done_date);
                    String doneDate = new SimpleDateFormat("yyyy-MM-dd").format(dateObject);
                    Toast.makeText(getBaseContext(), doneDate, Toast.LENGTH_LONG).show();
                    DatabaseOperations dop = new DatabaseOperations(ctx);
                    dop.putInformation1(dop, doneDate);
                    li1.add(doneDate);
                    GRIDVIEW.setAdapter(dataAdapter);
                    Toast.makeText(getBaseContext(), "Registeration success", Toast.LENGTH_LONG).show();
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "Error... Please enter a valid date", Toast.LENGTH_LONG).show();
                }
            }
        });

       DELETE_BUTTON.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               DatabaseOperations dop = new DatabaseOperations(ctx);
               String logDate = li1.get(pos);
               dop.deleteLogDate(dop, logDate);
               li1.remove(pos);
               displayDataOnGridView();
               Toast.makeText(getBaseContext(), "Selected date has been removed successfully..", Toast.LENGTH_LONG).show();
           }
       });

        SCORE_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, -90);
                SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
                String startDate = sdf.format(cal.getTime());
                String endDate = sdf.format(new Date());
                DatabaseOperations dop = new DatabaseOperations(ctx);
                Integer days = dop.numberOfDays(dop, startDate, endDate);
                Toast.makeText(getBaseContext(), "Number of days " + days, Toast.LENGTH_LONG).show();
            }
        });

       GRIDVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Toast.makeText(getBaseContext(), "Position of item selected : " + position + " Item selected " + li1.get(position), Toast.LENGTH_SHORT).show();
               pos = position;
           }
       });

       DELETE_ALL_BUTTON.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DatabaseOperations dop = new DatabaseOperations(ctx);
            dop.deleteAll(dop);
        }
    });
   }

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            DATE_TEXT.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
        }
    };

    public void displayDataOnGridView(){
        try {
            SQLiteDatabase SQ = openOrCreateDatabase("user_info", Context.MODE_PRIVATE, null);
            Cursor CR = SQ.rawQuery("SELECT * FROM " + TableData.TableInfo.TABLE_DONE, null);
            if(CR != null){
                li1.clear();
                if (CR.moveToFirst()){
                    do {
                        String logDate = CR.getString(CR.getColumnIndex(TableData.TableInfo.LOG_DATE));
                        li1.add(logDate);
                        GRIDVIEW.setAdapter(dataAdapter);
                    } while(CR.moveToNext());
                }
                else{
                    Toast.makeText(getBaseContext(),"There is no data...", Toast.LENGTH_LONG).show();
                }
            }
            CR.close();
            SQ.close();
        }catch (Exception e){
            Toast.makeText(getBaseContext(),"Error : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}

