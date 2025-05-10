package com.hcmus.app_list_student;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    TextView nameTextView, idTextView, facultyTextView, majorTextView, creditTextView, gpaTextView, trainingScoreTextView;
    ImageView avatarImageView;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        avatarImageView = findViewById(R.id.detailAvatar);
        nameTextView = findViewById(R.id.detailName);
        idTextView = findViewById(R.id.detailId);
        facultyTextView = findViewById(R.id.detailFaculty);
        majorTextView = findViewById(R.id.detailMajor);
        creditTextView = findViewById(R.id.detailCredits);
        gpaTextView = findViewById(R.id.detailGpa);
        trainingScoreTextView = findViewById(R.id.detailTrainingScore);
        backButton = findViewById(R.id.backButton);

        Student student = (Student) getIntent().getSerializableExtra("student");

        if (student != null) {
            Log.d(TAG, "Student data received: " + student.getName() + ", ID: " + student.getId());
            try {
                avatarImageView.setImageResource(student.getAvatarResId());
                nameTextView.setText(student.getName());
                idTextView.setText("MSSV: " + student.getId());
                facultyTextView.setText("Khoa: " + student.faculty);
                majorTextView.setText("Chuyên ngành: " + student.major);
                creditTextView.setText("Số tín chỉ: " + student.creditsCompleted);
                gpaTextView.setText("GPA: " + student.gpa);
                trainingScoreTextView.setText("Điểm rèn luyện: " + student.trainingScore);
            } catch (Exception e) {
                Log.e(TAG, "Error displaying student data: " + e.getMessage(), e);
                finish();
            }
        } else {
            Log.e(TAG, "Student data is null");
            finish();
        }

        // Xử lý sự kiện cho nút "Quay lại"
        backButton.setOnClickListener(v -> finish());
    }
}