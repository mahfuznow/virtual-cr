package mahfuz.virtualcr01;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class TodayEdit extends AppCompatActivity {


    Calendar calendar;
    int dayint;
    String dayArray[] = {"saturday", "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
    String day;
    EditText time1, course1, courseId1, teacher1, room1;
    EditText time2, course2, courseId2, teacher2, room2;
    EditText time3, course3, courseId3, teacher3, room3;
    EditText time4, course4, courseId4, teacher4, room4;
    EditText time5, course5, courseId5, teacher5, room5;
    EditText time6, course6, courseId6, teacher6, room6;
    String mytime1, mycourse1, mycourseId1, myteacher1, myroom1;
    String mytime2, mycourse2, mycourseId2, myteacher2, myroom2;
    String mytime3, mycourse3, mycourseId3, myteacher3, myroom3;
    String mytime4, mycourse4, mycourseId4, myteacher4, myroom4;
    String mytime5, mycourse5, mycourseId5, myteacher5, myroom5;
    String mytime6, mycourse6, mycourseId6, myteacher6, myroom6;

    FloatingActionButton save;
    String editdate, nexteditdate;
    String uid;
    String unit, department, year, semester;


    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_edit);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        time1 = (EditText) findViewById(R.id.time1);
        courseId1 = (EditText) findViewById(R.id.code1);
        course1 = (EditText) findViewById(R.id.course1);
        teacher1 = (EditText) findViewById(R.id.teacher1);
        room1 = (EditText) findViewById(R.id.room1);

        time2 = (EditText) findViewById(R.id.time2);
        courseId2 = (EditText) findViewById(R.id.code2);
        course2 = (EditText) findViewById(R.id.course2);
        teacher2 = (EditText) findViewById(R.id.teacher2);
        room2 = (EditText) findViewById(R.id.room2);

        time3 = (EditText) findViewById(R.id.time3);
        courseId3 = (EditText) findViewById(R.id.code3);
        course3 = (EditText) findViewById(R.id.course3);
        teacher3 = (EditText) findViewById(R.id.teacher3);
        room3 = (EditText) findViewById(R.id.room3);

        time4 = (EditText) findViewById(R.id.time4);
        courseId4 = (EditText) findViewById(R.id.code4);
        course4 = (EditText) findViewById(R.id.course4);
        teacher4 = (EditText) findViewById(R.id.teacher4);
        room4 = (EditText) findViewById(R.id.room4);

        time5 = (EditText) findViewById(R.id.time5);
        courseId5 = (EditText) findViewById(R.id.code5);
        course5 = (EditText) findViewById(R.id.course5);
        teacher5 = (EditText) findViewById(R.id.teacher5);
        room5 = (EditText) findViewById(R.id.room5);

        time6 = (EditText) findViewById(R.id.time6);
        courseId6 = (EditText) findViewById(R.id.code6);
        course6 = (EditText) findViewById(R.id.course6);
        teacher6 = (EditText) findViewById(R.id.teacher6);
        room6 = (EditText) findViewById(R.id.room6);

        save = (FloatingActionButton) findViewById(R.id.save);

        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();


        calendar = Calendar.getInstance();
        dayint = calendar.get(Calendar.DAY_OF_WEEK);
        day = dayArray[dayint];


        uid = auth.getUid();
        firebaseFirestore.collection("user").document(uid).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                if (documentSnapshot != null) {
                    unit = documentSnapshot.getString("unit");
                    department = documentSnapshot.getString("department");
                    year = documentSnapshot.getString("year");
                    semester = documentSnapshot.getString("semester");

                    firebaseFirestore.collection("university").document("just").collection(unit).document(department).collection(year).document(semester).collection("class").document("edit").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            editdate = documentSnapshot.getString("date").trim();
                            nexteditdate = documentSnapshot.getString("nextdate").trim();
                            if (editdate.equals(day)) {
                                dynamicClass();
                            } else if (nexteditdate.equals(day)) {
                                dynamicClass();
                            } else {
                                staticClass();
                            }
                        }
                    });

                }


            }
        });


        //save data to dynamic class schedule

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mytime1 = time1.getText().toString().trim();
                mycourseId1 = courseId1.getText().toString().trim();
                mycourse1 = course1.getText().toString().trim();
                myteacher1 = teacher1.getText().toString().trim();
                myroom1 = room1.getText().toString().trim();

                mytime2 = time2.getText().toString().trim();
                mycourseId2 = courseId2.getText().toString().trim();
                mycourse2 = course2.getText().toString().trim();
                myteacher2 = teacher2.getText().toString().trim();
                myroom2 = room2.getText().toString().trim();

                mytime3 = time3.getText().toString().trim();
                mycourseId3 = courseId3.getText().toString().trim();
                mycourse3 = course3.getText().toString().trim();
                myteacher3 = teacher3.getText().toString().trim();
                myroom3 = room3.getText().toString().trim();

                mytime4 = time4.getText().toString().trim();
                mycourseId4 = courseId4.getText().toString().trim();
                mycourse4 = course4.getText().toString().trim();
                myteacher4 = teacher4.getText().toString().trim();
                myroom4 = room4.getText().toString().trim();

                mytime5 = time5.getText().toString().trim();
                mycourseId5 = courseId5.getText().toString().trim();
                mycourse5 = course5.getText().toString().trim();
                myteacher5 = teacher5.getText().toString().trim();
                myroom5 = room5.getText().toString().trim();

                mytime6 = time6.getText().toString().trim();
                mycourseId6 = courseId6.getText().toString().trim();
                mycourse6 = course6.getText().toString().trim();
                myteacher6 = teacher6.getText().toString().trim();
                myroom6 = room6.getText().toString().trim();


                Map<String, String> setvalue1 = new HashMap<>();
                setvalue1.put("time", mytime1);
                setvalue1.put("courseId", mycourseId1);
                setvalue1.put("course", mycourse1);
                setvalue1.put("teacher", myteacher1);
                setvalue1.put("room", myroom1);

                firebaseFirestore.collection("university").document("just").collection(unit).document(department).collection(year).document(semester).collection("class").document("dynamic").collection(day).document("class1").set(setvalue1);


                Map<String, String> setvalue2 = new HashMap<>();
                setvalue2.put("time", mytime2);
                setvalue2.put("courseId", mycourseId2);
                setvalue2.put("course", mycourse2);
                setvalue2.put("teacher", myteacher2);
                setvalue2.put("room", myroom2);

                firebaseFirestore.collection("university").document("just").collection(unit).document(department).collection(year).document(semester).collection("class").document("dynamic").collection(day).document("class2").set(setvalue2);


                Map<String, String> setvalue3 = new HashMap<>();
                setvalue3.put("time", mytime3);
                setvalue3.put("courseId", mycourseId3);
                setvalue3.put("course", mycourse3);
                setvalue3.put("teacher", myteacher3);
                setvalue3.put("room", myroom3);

                firebaseFirestore.collection("university").document("just").collection(unit).document(department).collection(year).document(semester).collection("class").document("dynamic").collection(day).document("class3").set(setvalue3);

                Map<String, String> setvalue4 = new HashMap<>();
                setvalue4.put("time", mytime4);
                setvalue4.put("courseId", mycourseId4);
                setvalue4.put("course", mycourse4);
                setvalue4.put("teacher", myteacher4);
                setvalue4.put("room", myroom4);

                firebaseFirestore.collection("university").document("just").collection(unit).document(department).collection(year).document(semester).collection("class").document("dynamic").collection(day).document("class4").set(setvalue4);


                Map<String, String> setvalue5 = new HashMap<>();
                setvalue5.put("time", mytime5);
                setvalue5.put("courseId", mycourseId5);
                setvalue5.put("course", mycourse5);
                setvalue5.put("teacher", myteacher5);
                setvalue5.put("room", myroom5);

                firebaseFirestore.collection("university").document("just").collection(unit).document(department).collection(year).document(semester).collection("class").document("dynamic").collection(day).document("class5").set(setvalue5);

                Map<String, String> setvalue6 = new HashMap<>();
                setvalue6.put("time", mytime6);
                setvalue6.put("courseId", mycourseId6);
                setvalue6.put("course", mycourse6);
                setvalue6.put("teacher", myteacher6);
                setvalue6.put("room", myroom6);

                firebaseFirestore.collection("university").document("just").collection(unit).document(department).collection(year).document(semester).collection("class").document("dynamic").collection(day).document("class6").set(setvalue6);


                //setting edit date in firebase
                Map<String, Object> setEditDate = new HashMap<>();
                setEditDate.put("date", day);
                firebaseFirestore.collection("university").document("just").collection(unit).document(department).collection(year).document(semester).collection("class").document("edit").update(setEditDate);


                //Setting random number to triger fcm notification
                long timeMill;
                timeMill = System.currentTimeMillis();

                Map<String, Object> setRandomNumber = new HashMap<>();
                setRandomNumber.put("edited", timeMill);
                firebaseFirestore.collection("university").document("just").collection(unit).document(department).collection(year).document(semester).collection("class").document("todayEdit").update(setRandomNumber);

                new Notify().execute(department,year,semester);

                Intent intent = new Intent(TodayEdit.this, TabedActivity.class);
                startActivity(intent);

            }
        });


    }




    public static class Notify extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {



            String department=params[0];
            String year=params[1];
            String semester=params[2];

            try {

                URL url = new URL("https://fcm.googleapis.com/fcm/send");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setUseCaches(false);
                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Authorization", "key=AIzaSyBcjg4jnpZGMFNd5i45KFGpELLAZkyCp00");
                conn.setRequestProperty("Content-Type", "application/json");

                JSONObject json = new JSONObject();

                json.put("to","/topics/"+department+year+semester);


                JSONObject info = new JSONObject();
                info.put("title", "Tomorrow's class schedule changed");   // Notification title
                info.put("body", "Please check out the changes");// Notification body

                info.put("type","tomorrow");


                json.put("data", info);

                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(json.toString());
                wr.flush();
                conn.getInputStream();

            } catch (Exception e) {
                Log.d("Error", "" + e);
            }


            return null;
        }
    }


    private void staticClass() {

        firebaseFirestore.collection("university").document("just").collection(unit).document(department).collection(year).document(semester).collection("class").document("static").collection(day).document("class1").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot != null) {
                    mytime1 = documentSnapshot.getString("time");
                    mycourse1 = documentSnapshot.getString("course");
                    mycourseId1 = documentSnapshot.getString("courseId");
                    myteacher1 = documentSnapshot.getString("teacher");
                    myroom1 = documentSnapshot.getString("room");

                    time1.setText(mytime1);
                    course1.setText(mycourse1);
                    courseId1.setText(mycourseId1);
                    teacher1.setText(myteacher1);
                    room1.setText(myroom1);

                }

            }
        });

        firebaseFirestore.collection("university").document("just").collection(unit).document(department).collection(year).document(semester).collection("class").document("static").collection(day).document("class2").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot != null) {
                    mytime2 = documentSnapshot.getString("time");
                    mycourse2 = documentSnapshot.getString("course");
                    mycourseId2 = documentSnapshot.getString("courseId");
                    myteacher2 = documentSnapshot.getString("teacher");
                    myroom2 = documentSnapshot.getString("room");

                    time2.setText(mytime2);
                    course2.setText(mycourse2);
                    courseId2.setText(mycourseId2);
                    teacher2.setText(myteacher2);
                    room2.setText(myroom2);

                }

            }
        });


        firebaseFirestore.collection("university").document("just").collection(unit).document(department).collection(year).document(semester).collection("class").document("static").collection(day).document("class3").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot != null) {
                    mytime3 = documentSnapshot.getString("time");
                    mycourse3 = documentSnapshot.getString("course");
                    mycourseId3 = documentSnapshot.getString("courseId");
                    myteacher3 = documentSnapshot.getString("teacher");
                    myroom3 = documentSnapshot.getString("room");

                    time3.setText(mytime3);
                    course3.setText(mycourse3);
                    courseId3.setText(mycourseId3);
                    teacher3.setText(myteacher3);
                    room3.setText(myroom3);

                }

            }
        });


        firebaseFirestore.collection("university").document("just").collection(unit).document(department).collection(year).document(semester).collection("class").document("static").collection(day).document("class4").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot != null) {
                    mytime4 = documentSnapshot.getString("time");
                    mycourse4 = documentSnapshot.getString("course");
                    mycourseId4 = documentSnapshot.getString("courseId");
                    myteacher4 = documentSnapshot.getString("teacher");
                    myroom4 = documentSnapshot.getString("room");

                    time4.setText(mytime4);
                    course4.setText(mycourse4);
                    courseId4.setText(mycourseId4);
                    teacher4.setText(myteacher4);
                    room4.setText(myroom4);

                }

            }
        });


        firebaseFirestore.collection("university").document("just").collection(unit).document(department).collection(year).document(semester).collection("class").document("static").collection(day).document("class5").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot != null) {
                    mytime5 = documentSnapshot.getString("time");
                    mycourse5 = documentSnapshot.getString("course");
                    mycourseId5 = documentSnapshot.getString("courseId");
                    myteacher5 = documentSnapshot.getString("teacher");
                    myroom5 = documentSnapshot.getString("room");

                    time5.setText(mytime5);
                    course5.setText(mycourse5);
                    courseId5.setText(mycourseId5);
                    teacher5.setText(myteacher5);
                    room5.setText(myroom5);

                }

            }
        });


        firebaseFirestore.collection("university").document("just").collection(unit).document(department).collection(year).document(semester).collection("class").document("static").collection(day).document("class6").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot != null) {
                    mytime6 = documentSnapshot.getString("time");
                    mycourse6 = documentSnapshot.getString("course");
                    mycourseId6 = documentSnapshot.getString("courseId");
                    myteacher6 = documentSnapshot.getString("teacher");
                    myroom6 = documentSnapshot.getString("room");

                    time6.setText(mytime6);
                    course6.setText(mycourse6);
                    courseId6.setText(mycourseId6);
                    teacher6.setText(myteacher6);
                    room6.setText(myroom6);

                }

            }
        });


    }


    private void dynamicClass() {


        firebaseFirestore.collection("university").document("just").collection(unit).document(department).collection(year).document(semester).collection("class").document("dynamic").collection(day).document("class1").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot != null) {
                    mytime1 = documentSnapshot.getString("time");
                    mycourse1 = documentSnapshot.getString("course");
                    mycourseId1 = documentSnapshot.getString("courseId");
                    myteacher1 = documentSnapshot.getString("teacher");
                    myroom1 = documentSnapshot.getString("room");

                    time1.setText(mytime1);
                    course1.setText(mycourse1);
                    courseId1.setText(mycourseId1);
                    teacher1.setText(myteacher1);
                    room1.setText(myroom1);

                }

            }
        });

        firebaseFirestore.collection("university").document("just").collection(unit).document(department).collection(year).document(semester).collection("class").document("dynamic").collection(day).document("class2").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot != null) {
                    mytime2 = documentSnapshot.getString("time");
                    mycourse2 = documentSnapshot.getString("course");
                    mycourseId2 = documentSnapshot.getString("courseId");
                    myteacher2 = documentSnapshot.getString("teacher");
                    myroom2 = documentSnapshot.getString("room");

                    time2.setText(mytime2);
                    course2.setText(mycourse2);
                    courseId2.setText(mycourseId2);
                    teacher2.setText(myteacher2);
                    room2.setText(myroom2);

                }

            }
        });


        firebaseFirestore.collection("university").document("just").collection(unit).document(department).collection(year).document(semester).collection("class").document("dynamic").collection(day).document("class3").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot != null) {
                    mytime3 = documentSnapshot.getString("time");
                    mycourse3 = documentSnapshot.getString("course");
                    mycourseId3 = documentSnapshot.getString("courseId");
                    myteacher3 = documentSnapshot.getString("teacher");
                    myroom3 = documentSnapshot.getString("room");

                    time3.setText(mytime3);
                    course3.setText(mycourse3);
                    courseId3.setText(mycourseId3);
                    teacher3.setText(myteacher3);
                    room3.setText(myroom3);

                }

            }
        });


        firebaseFirestore.collection("university").document("just").collection(unit).document(department).collection(year).document(semester).collection("class").document("dynamic").collection(day).document("class4").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot != null) {
                    mytime4 = documentSnapshot.getString("time");
                    mycourse4 = documentSnapshot.getString("course");
                    mycourseId4 = documentSnapshot.getString("courseId");
                    myteacher4 = documentSnapshot.getString("teacher");
                    myroom4 = documentSnapshot.getString("room");

                    time4.setText(mytime4);
                    course4.setText(mycourse4);
                    courseId4.setText(mycourseId4);
                    teacher4.setText(myteacher4);
                    room4.setText(myroom4);

                }

            }
        });

        firebaseFirestore.collection("university").document("just").collection(unit).document(department).collection(year).document(semester).collection("class").document("dynamic").collection(day).document("class5").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot != null) {
                    mytime5 = documentSnapshot.getString("time");
                    mycourse5 = documentSnapshot.getString("course");
                    mycourseId5 = documentSnapshot.getString("courseId");
                    myteacher5 = documentSnapshot.getString("teacher");
                    myroom5 = documentSnapshot.getString("room");

                    time5.setText(mytime5);
                    course5.setText(mycourse5);
                    courseId5.setText(mycourseId5);
                    teacher5.setText(myteacher5);
                    room5.setText(myroom5);

                }

            }
        });

        firebaseFirestore.collection("university").document("just").collection(unit).document(department).collection(year).document(semester).collection("class").document("dynamic").collection(day).document("class6").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot != null) {
                    mytime6 = documentSnapshot.getString("time");
                    mycourse6 = documentSnapshot.getString("course");
                    mycourseId6 = documentSnapshot.getString("courseId");
                    myteacher6 = documentSnapshot.getString("teacher");
                    myroom6 = documentSnapshot.getString("room");

                    time6.setText(mytime6);
                    course6.setText(mycourse6);
                    courseId6.setText(mycourseId6);
                    teacher6.setText(myteacher6);
                    room6.setText(myroom6);

                }

            }
        });


    }


}
