package com.hcmus.app_list_student;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ArrayList<Student> studentList;
    StudentAdapter adapter;
    ListView listView;
    DBHelper dbHelper;
    private long lastClickTime = 0;
    private static final long CLICK_INTERVAL = 1000; // Khoảng thời gian tối thiểu giữa 2 lần nhấp (ms)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        listView = findViewById(R.id.listView);
        studentList = new ArrayList<>();

        // Thêm 10 sinh viên nếu chưa tồn tại
        try {
            Log.d(TAG, "Checking and inserting students...");
            if (!dbHelper.isStudentExists("2012345")) {
                dbHelper.insertStudent(new Student("Nguyen Van A", "2012345", R.drawable.avatar1, "Công nghệ thông tin", "Khoa học máy tính", 120, 3.5f, 85));
                Log.d(TAG, "Inserted Nguyen Van A");
            }
            if (!dbHelper.isStudentExists("2012346")) {
                dbHelper.insertStudent(new Student("Tran Thi B", "2012346", R.drawable.avatar2, "Công nghệ thông tin", "Kỹ thuật phần mềm", 100, 3.8f, 90));
                Log.d(TAG, "Inserted Tran Thi B");
            }
            if (!dbHelper.isStudentExists("2012347")) {
                dbHelper.insertStudent(new Student("Le Van C", "2012347", R.drawable.avatar3, "Kinh tế", "Quản trị kinh doanh", 90, 3.2f, 80));
                Log.d(TAG, "Inserted Le Van C");
            }
            if (!dbHelper.isStudentExists("2012348")) {
                dbHelper.insertStudent(new Student("Pham Thi D", "2012348", R.drawable.avatar4, "Y học", "Bác sĩ đa khoa", 150, 3.7f, 88));
                Log.d(TAG, "Inserted Pham Thi D");
            }
            if (!dbHelper.isStudentExists("2012349")) {
                dbHelper.insertStudent(new Student("Hoang Van E", "2012349", R.drawable.avatar5, "Kỹ thuật", "Xây dựng dân dụng", 110, 3.4f, 82));
                Log.d(TAG, "Inserted Hoang Van E");
            }
            if (!dbHelper.isStudentExists("2012350")) {
                dbHelper.insertStudent(new Student("Nguyen Thi F", "2012350", R.drawable.avatar6, "Nông nghiệp", "Kỹ thuật nông nghiệp", 95, 3.6f, 87));
                Log.d(TAG, "Inserted Nguyen Thi F");
            }
            if (!dbHelper.isStudentExists("2012351")) {
                dbHelper.insertStudent(new Student("Tran Van G", "2012351", R.drawable.avatar7, "Môi trường", "Quản lý tài nguyên", 105, 3.3f, 83));
                Log.d(TAG, "Inserted Tran Van G");
            }
            if (!dbHelper.isStudentExists("2012352")) {
                dbHelper.insertStudent(new Student("Le Thi H", "2012352", R.drawable.avatar8, "Giáo dục", "Sư phạm Toán", 130, 3.9f, 92));
                Log.d(TAG, "Inserted Le Thi H");
            }
            if (!dbHelper.isStudentExists("2012353")) {
                dbHelper.insertStudent(new Student("Pham Van I", "2012353", R.drawable.avatar9, "Vật lý", "Vật lý ứng dụng", 115, 3.5f, 86));
                Log.d(TAG, "Inserted Pham Van I");
            }
            if (!dbHelper.isStudentExists("2012354")) {
                dbHelper.insertStudent(new Student("Hoang Thi K", "2012354", R.drawable.avatar10, "Hóa học", "Hóa dược", 125, 3.8f, 91));
                Log.d(TAG, "Inserted Hoang Thi K");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error inserting students: " + e.getMessage(), e);
        }

        // Lấy danh sách sinh viên từ cơ sở dữ liệu
        try {
            studentList.addAll(dbHelper.getAllStudents());
            Log.d(TAG, "Loaded " + studentList.size() + " students from database");
            if (studentList.isEmpty()) {
                Log.w(TAG, "Student list is empty after loading from database");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error loading students from database: " + e.getMessage(), e);
        }

        // Kiểm tra danh sách trước khi hiển thị
        if (studentList == null || studentList.isEmpty()) {
            Log.e(TAG, "Student list is null or empty, setting default adapter");
            studentList = new ArrayList<>(); // Đảm bảo không null
            adapter = new StudentAdapter(this, R.layout.student_item, studentList);
        } else {
            adapter = new StudentAdapter(this, R.layout.student_item, studentList);
        }

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastClickTime > CLICK_INTERVAL) {
                lastClickTime = currentTime;
                try {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("student", studentList.get(i));
                    startActivity(intent);
                } catch (Exception e) {
                    Log.e(TAG, "Error starting DetailActivity: " + e.getMessage(), e);
                }
            }
        });
    }
}