package com.hcmus.app_list_student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class StudentAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Student> students;

    public StudentAdapter(Context context, int layout, List<Student> students) {
        this.context = context;
        this.layout = layout;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students != null ? students.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return students != null && i >= 0 && i < students.size() ? students.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private static class ViewHolder {
        ImageView avatar;
        TextView name, id;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layout, null);
            holder = new ViewHolder();
            holder.avatar = convertView.findViewById(R.id.avatarImageView);
            holder.name = convertView.findViewById(R.id.nameTextView);
            holder.id = convertView.findViewById(R.id.idTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Student student = (Student) getItem(i);
        if (student != null) {
            holder.name.setText(student.getName() != null ? student.getName() : "N/A");
            holder.id.setText(student.getId() != null ? "MSSV: " + student.getId() : "MSSV: N/A");
            try {
                holder.avatar.setImageResource(student.getAvatarResId());
            } catch (Exception e) {
                holder.avatar.setImageResource(android.R.drawable.ic_menu_gallery); // Hình mặc định nếu lỗi
            }
        } else {
            holder.name.setText("N/A");
            holder.id.setText("MSSV: N/A");
            holder.avatar.setImageResource(android.R.drawable.ic_menu_gallery);
        }

        return convertView;
    }
}