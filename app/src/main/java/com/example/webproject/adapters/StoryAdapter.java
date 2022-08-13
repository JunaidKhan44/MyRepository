package com.example.webproject.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.webproject.R;
import com.example.webproject.models.StoryModel;
import com.example.webproject.base.Constant;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {

    private Context context;
    private ArrayList<Object>  fileList;


    public StoryAdapter(Context context, ArrayList<Object> fileList) {
        this.context = context;
        this.fileList = fileList;
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.card_row,null,false);
        return new StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {

        final StoryModel files= (StoryModel) fileList.get(position);
        if(files.getUri().toString().endsWith(".mp4"))
        {
            holder.playIcon.setVisibility(View.VISIBLE);
        }else{
            holder.playIcon.setVisibility(View.INVISIBLE);
        }
        Glide.with(context)
                .load(files.getUri())
                .into(holder.saveImage);

        holder.downloadID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkFolder();
                final String path = ((StoryModel) fileList.get(position)).getPath();
                final File file = new File(path);
                String destPath = Environment.getExternalStorageDirectory().getAbsolutePath() + Constant.SAVE_FOLDER_NAME;
                File destFile = new File(destPath);
                try {
                    FileUtils.copyFileToDirectory(file,destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                MediaScannerConnection.scanFile(
                        context,
                        new String[]{ destPath + files.getFilename()},
                        new String[]{ "*/*"},
                        new MediaScannerConnection.MediaScannerConnectionClient()
                        {
                            public void onMediaScannerConnected()
                            {
                            }
                            public void onScanCompleted(String path, Uri uri)
                            {
                                Log.d("path: ",path);
                            }
                        });
                Toast.makeText(context, "Saved to: "+ destPath + files.getFilename(), Toast.LENGTH_LONG).show();


            }
        });


    }

    public void checkFolder() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + Constant.SAVE_FOLDER_NAME ;
        File dir = new File(path);

        boolean isDirectoryCreated = dir.exists();
        if (!isDirectoryCreated) {
            isDirectoryCreated = dir.mkdir();
        }
        if (isDirectoryCreated) {
            Log.d("Folder", "Already Created");
        }
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder{

        ImageView playIcon,saveImage;
        Button downloadID;

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            saveImage=itemView.findViewById(R.id.mainIMageView);
            downloadID=itemView.findViewById(R.id.downloadID);
            playIcon=itemView.findViewById(R.id.playButtonImage);
        }
    }
}
