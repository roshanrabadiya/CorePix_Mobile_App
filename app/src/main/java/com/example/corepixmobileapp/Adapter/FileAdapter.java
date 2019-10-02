package com.example.corepixmobileapp.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.corepixmobileapp.Constants.Constants;
import com.example.corepixmobileapp.ModelClass.File;
import com.example.corepixmobileapp.R;

import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {
    private Context mContext;
    List<File> filesList;

    public FileAdapter(Context mContext, List<File> filesList) {
        this.mContext = mContext;
        this.filesList = filesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.file_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            File file = filesList.get(position);
            holder.tv_file_name.setText(file.getFile_name());
            holder.tv_file_time.setText(file.getFile_date());
            holder.imageView.setImageResource(R.drawable.ic_file);
    }

    @Override
    public int getItemCount() {
        return filesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_file_name,tv_file_time;
        public ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_file_name = itemView.findViewById(R.id.tv_file_name);
            tv_file_time = itemView.findViewById(R.id.tv_file_time);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
