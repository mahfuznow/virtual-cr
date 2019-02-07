package mahfuz.virtualcr01;


import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.annotation.Nullable;


/**
 * A simple {@link Fragment} subclass.
 */
public class othersFragment extends Fragment {

    Calendar calendar;
    int dayint;
    String dayArray[]={"saturday","sunday","monday","tuesday","wednesday","thursday","friday","saturday","sunday"};
    public String dayOfWeek;
    TextView Date;
    TextView time1,course1,courseId1,teacher1,room1;
    TextView time2,course2,courseId2,teacher2,room2;
    TextView time3,course3,courseId3,teacher3,room3;
    TextView time4,course4,courseId4,teacher4,room4;
    TextView time5,course5,courseId5,teacher5,room5;
    TextView time6,course6,courseId6,teacher6,room6;
    String mytime1,mycourse1,mycourseId1,myteacher1,myroom1;
    String mytime2,mycourse2,mycourseId2,myteacher2,myroom2;
    String mytime3,mycourse3,mycourseId3,myteacher3,myroom3;
    String mytime4,mycourse4,mycourseId4,myteacher4,myroom4;
    String mytime5,mycourse5,mycourseId5,myteacher5,myroom5;
    String mytime6,mycourse6,mycourseId6,myteacher6,myroom6;
    LinearLayout heading,title;
    ScrollView scroll;

    String date;

    int year,month,dayOfMonth;


    Button selectDate;
    DatePickerDialog datePickerDialog;

    LinearLayout select;
    RelativeLayout selectedClass;


    FirebaseFirestore firebaseFirestore,firebaseFirestore1,firebaseFirestore2;
    FirebaseAuth auth;

    public othersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_others, container, false);

        Date = (TextView) view.findViewById(R.id.date);

        time1 = (TextView) view.findViewById(R.id.time1);
        course1 = (TextView) view.findViewById(R.id.course1);
        courseId1 = (TextView) view.findViewById(R.id.code1);
        teacher1 = (TextView) view.findViewById(R.id.teacher1);
        room1 = (TextView) view.findViewById(R.id.room1);

        time2 = (TextView) view.findViewById(R.id.time2);
        course2 = (TextView) view.findViewById(R.id.course2);
        courseId2 = (TextView) view.findViewById(R.id.code2);
        teacher2 = (TextView) view.findViewById(R.id.teacher2);
        room2 = (TextView) view.findViewById(R.id.room2);

        time3 = (TextView) view.findViewById(R.id.time3);
        course3 = (TextView) view.findViewById(R.id.course3);
        courseId3 = (TextView) view.findViewById(R.id.code3);
        teacher3 = (TextView) view.findViewById(R.id.teacher3);
        room3 = (TextView) view.findViewById(R.id.room3);

        time4 = (TextView) view.findViewById(R.id.time4);
        course4 = (TextView) view.findViewById(R.id.course4);
        courseId4 = (TextView) view.findViewById(R.id.code4);
        teacher4 = (TextView) view.findViewById(R.id.teacher4);
        room4 = (TextView) view.findViewById(R.id.room4);

        time5 = (TextView) view.findViewById(R.id.time5);
        course5 = (TextView) view.findViewById(R.id.course5);
        courseId5 = (TextView) view.findViewById(R.id.code5);
        teacher5 = (TextView) view.findViewById(R.id.teacher5);
        room5 = (TextView) view.findViewById(R.id.room5);

        time6 = (TextView) view.findViewById(R.id.time6);
        course6 = (TextView) view.findViewById(R.id.course6);
        courseId6 = (TextView) view.findViewById(R.id.code6);
        teacher6 = (TextView) view.findViewById(R.id.teacher6);
        room6 = (TextView) view.findViewById(R.id.room6);

        heading = (LinearLayout) view.findViewById(R.id.heading);
        title = (LinearLayout) view.findViewById(R.id.title);

        scroll = (ScrollView) view.findViewById(R.id.scroll);




        selectDate = (Button) view.findViewById(R.id.selectDate);



        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore1= FirebaseFirestore.getInstance();
        firebaseFirestore2= FirebaseFirestore.getInstance();
        auth= FirebaseAuth.getInstance();

        select= (LinearLayout) view.findViewById(R.id.select);
        selectedClass = (RelativeLayout)  view.findViewById(R.id.selectedClass);








        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                                calendar.set(year,month,day);
                                dayint = calendar.get(Calendar.DAY_OF_WEEK);
                                dayOfWeek = dayArray[dayint];


                                Date.setText(DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime()));


                                selectedClass.setVisibility(View.VISIBLE);
                                staticClass();

                            }
                        }, year, month, dayOfMonth);
                //datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });





        return view;
    }








    private void staticClass() {

        firebaseFirestore2.collection("class").document("static").collection(dayOfWeek).document("class1").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot!=null)
                {
                    mytime1 =documentSnapshot.getString("time");
                    mycourse1 =documentSnapshot.getString("course");
                    mycourseId1 =documentSnapshot.getString("courseId");
                    myteacher1 =documentSnapshot.getString("teacher");
                    myroom1 =documentSnapshot.getString("room");

                    time1.setText(mytime1);
                    course1.setText(mycourse1);
                    courseId1.setText(mycourseId1);
                    teacher1.setText(myteacher1);
                    room1.setText(myroom1);

                }

            }
        });

        firebaseFirestore2.collection("class").document("static").collection(dayOfWeek).document("class2").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot!=null)
                {
                    mytime2 =documentSnapshot.getString("time");
                    mycourse2 =documentSnapshot.getString("course");
                    mycourseId2 =documentSnapshot.getString("courseId");
                    myteacher2 =documentSnapshot.getString("teacher");
                    myroom2 =documentSnapshot.getString("room");

                    time2.setText(mytime2);
                    course2.setText(mycourse2);
                    courseId2.setText(mycourseId2);
                    teacher2.setText(myteacher2);
                    room2.setText(myroom2);

                }

            }
        });


        firebaseFirestore2.collection("class").document("static").collection(dayOfWeek).document("class3").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot!=null)
                {
                    mytime3 =documentSnapshot.getString("time");
                    mycourse3 =documentSnapshot.getString("course");
                    mycourseId3 =documentSnapshot.getString("courseId");
                    myteacher3 =documentSnapshot.getString("teacher");
                    myroom3 =documentSnapshot.getString("room");

                    time3.setText(mytime3);
                    course3.setText(mycourse3);
                    courseId3.setText(mycourseId3);
                    teacher3.setText(myteacher3);
                    room3.setText(myroom3);

                }

            }
        });


        firebaseFirestore2.collection("class").document("static").collection(dayOfWeek).document("class4").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot!=null)
                {
                    mytime4 =documentSnapshot.getString("time");
                    mycourse4 =documentSnapshot.getString("course");
                    mycourseId4 =documentSnapshot.getString("courseId");
                    myteacher4 =documentSnapshot.getString("teacher");
                    myroom4 =documentSnapshot.getString("room");

                    time4.setText(mytime4);
                    course4.setText(mycourse4);
                    courseId4.setText(mycourseId4);
                    teacher4.setText(myteacher4);
                    room4.setText(myroom4);

                }

            }
        });


        firebaseFirestore2.collection("class").document("static").collection(dayOfWeek).document("class5").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot!=null)
                {
                    mytime5 =documentSnapshot.getString("time");
                    mycourse5 =documentSnapshot.getString("course");
                    mycourseId5 =documentSnapshot.getString("courseId");
                    myteacher5 =documentSnapshot.getString("teacher");
                    myroom5 =documentSnapshot.getString("room");

                    time5.setText(mytime5);
                    course5.setText(mycourse5);
                    courseId5.setText(mycourseId5);
                    teacher5.setText(myteacher5);
                    room5.setText(myroom5);

                }

            }
        });


        firebaseFirestore2.collection("class").document("static").collection(dayOfWeek).document("class6").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot!=null)
                {
                    mytime6 =documentSnapshot.getString("time");
                    mycourse6 =documentSnapshot.getString("course");
                    mycourseId6 =documentSnapshot.getString("courseId");
                    myteacher6 =documentSnapshot.getString("teacher");
                    myroom6 =documentSnapshot.getString("room");

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
