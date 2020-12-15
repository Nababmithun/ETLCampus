package com.bipul.nrsingdidristict.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bipul.nrsingdidristict.R;
import com.google.gson.internal.$Gson$Preconditions;

public class AdminActivity extends AppCompatActivity {
    CardView aboutCV, appointmentCV, complainCV, dailyWorkCV, adminGonosunaniCV,
            setInfoCV, adminStuffCV, unoMessageCV, adminCV,adminAppointmentSubjectCV,adminComplainsCV;

    private ImageView saveIV, updateIV, deleteIV,workScheduleSaveIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        init();
    }

    private void init() {
        adminGonosunaniCV = findViewById(R.id.adminGonoSunaniCV);
        adminStuffCV = findViewById(R.id.adminStuffCV);
        adminAppointmentSubjectCV = findViewById(R.id.adminAppointmentSubjectCV);
        saveIV = findViewById(R.id.saveIV);
        updateIV = findViewById(R.id.updateIV);
        deleteIV = findViewById(R.id.deleteIV);
        workScheduleSaveIV = findViewById(R.id.workScheduleSaveIV);
        adminComplainsCV = findViewById(R.id.adminComplainsCV);

        workScheduleSaveIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this,WorkScheduleActivity.class));
            }
        });

        adminComplainsCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this,ComplainFetchAllActivity.class));
            }
        });


        adminAppointmentSubjectCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this,AppoimentFatchAllActivity.class));
            }
        });

        updateIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this,AppoimentFatchAllActivity.class));
            }
        });

        adminGonosunaniCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, PublicHeadingActivity.class));
            }
        });

        adminStuffCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, EmployeeSaveActivity.class));

            }
        });

        saveIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this,EditorActivity.class));
            }
        });

    }

    public void backBtn(View view) {
        onBackPressed();
    }
}