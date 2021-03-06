package com.apareciumlabs.brionsilva.safeplant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class CreateAppoinment extends AppCompatActivity {

    static double mathTime;
    static int hour;
    static int minute;
    static int day;
    static String date;


    EditText title;
    TimePicker time;
    EditText discription;

    Button save , thesaurusbtn;

    AppoinmentDataBase handleDB;

    String selTime;
    String selDate;
    String preTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_create_appoinment);




        Intent getdate = getIntent();
        selDate = getdate.getStringExtra("date");
        preTitle = getdate.getStringExtra("title");

        title = (EditText) findViewById(R.id.titleedit);
        time = (TimePicker) findViewById(R.id.timeedit);
        discription = (EditText) findViewById(R.id.editdetail);
        save = (Button) findViewById(R.id.savebtn);
        thesaurusbtn =(Button) findViewById(R.id.thesaurusbtn) ;

        handleDB = new AppoinmentDataBase(this, null, null, 1);



/*
        if((preTitle.equals("404"))) {
            Toast.makeText(getApplicationContext(), "create appointment", Toast.LENGTH_LONG).show();
        }else{

            Toast.makeText(getApplicationContext(), "update appointment", Toast.LENGTH_LONG).show();

//            int retriveHour ;
//            int retriveMinute ;
            String retriveTitle = handleDB.retriveAppoinment(date,preTitle,1) ;
            String retriveDis = handleDB.retriveAppoinment(date,preTitle,2);


            title.setText(retriveTitle);
            discription.setText(retriveDis);

            int retriveHour = handleDB.retriveTime(date,preTitle,1);
            int retriveMinute = handleDB.retriveTime(date,preTitle,2);

            time.setCurrentHour(retriveHour);
            time.setCurrentMinute(retriveMinute);

        }
*/


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (preTitle.equals("404")){

                    hour = time.getCurrentHour();

                    minute = time.getCurrentMinute();

                mathTime = (hour * 60) + minute;

                selTime = "" + hour + ":" + minute;

                Appoinment newAppoinment = new Appoinment(title.getText().toString(), discription.getText().toString(), selDate, selTime, mathTime);

                if (handleDB.checkTitle(newAppoinment)) {

                    String adedTittle = handleDB.createAppoinment(newAppoinment);


                    Toast.makeText(getApplicationContext(), adedTittle, Toast.LENGTH_SHORT).show();

                    Intent home = new Intent(CreateAppoinment.this, CalenderMain.class);
                    startActivity(home);

                } else {
                    Toast.makeText(getApplicationContext(), title.getText().toString() + " already exists, please choose a diﬀerent event title", Toast.LENGTH_LONG).show();
                }
            }else{

                   /* hour = time.getCurrentHour();

                    minute = time.getCurrentMinute();

                    mathTime = (hour * 60) + minute;

                    selTime = "" + hour + ":" + minute;

                    boolean updateResult = handleDB.updateAppointment(date ,preTitle ,title.getText().toString() , selTime ,discription.getText().toString(),mathTime);

                    if(updateResult){
                        Toast.makeText(getApplicationContext(), "Update " + preTitle +" appoitment SUCCESSFULL", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), " something went WRONG", Toast.LENGTH_LONG).show();
                    }
*/


                    hour = time.getCurrentHour();

                    minute = time.getCurrentMinute();

                    mathTime = (hour * 60) + minute;

                    selTime = "" + hour + ":" + minute;

                    boolean updateResult = handleDB.updateAppointment(selDate ,preTitle ,title.getText().toString() , selTime ,discription.getText().toString(),mathTime);
//                Toast.makeText(getApplicationContext(), preTitle, Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), selDate, Toast.LENGTH_LONG).show();
                    if(updateResult){
                        Toast.makeText(getApplicationContext(), "Update " + preTitle +" appoitment SUCCESSFULL", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), " something went WRONG", Toast.LENGTH_LONG).show();
                    }


                }
        }
        });



    }


}
