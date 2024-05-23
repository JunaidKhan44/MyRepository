package com.example.webproject.adapters;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.webproject.models.GalleryModel;
import com.example.webproject.R;

import java.io.File;
import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private Context context;
    private ArrayList<GalleryModel> filesList;

    public GalleryAdapter(Context context, ArrayList<GalleryModel> filesList) {
        this.context = context;
        this.filesList = filesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_card_row,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final GalleryModel files = filesList.get(position);
        final Uri uri = Uri.parse(files.getUri().toString());
        final File file = new File(uri.getPath());

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        holder.playIcon.setVisibility(View.VISIBLE);
        Glide.with(context)
                .load(files.getUri())
                .into(holder.savedImage);
        holder.savedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(files.getUri().toString().endsWith(".mp4")){
                    Uri VideoURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider",file);
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setDataAndType(VideoURI, "video/*");
                    try {
                      context.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(context, "No application found to open this file.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        holder.repostID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri mainUri = Uri.fromFile(file);
                    if(files.getUri().toString().endsWith(".mp4")){
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("video/*");
                    sharingIntent.putExtra(Intent.EXTRA_STREAM, mainUri);
                    sharingIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    sharingIntent.setPackage("com.whatsapp");
                    try {
                        context.startActivity(Intent.createChooser(sharingIntent, "Share Video using"));
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(context, "No application found to open this file.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        holder.shareID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri mainUri = Uri.fromFile(file);
                   if(files.getUri().toString().endsWith(".mp4")){
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("video/*");
                    sharingIntent.putExtra(Intent.EXTRA_STREAM, mainUri);
                    sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    sharingIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    try {
                    //    context.startActivity(Intent.createChooser(sharingIntent, "Share Video using"));
                        Toast.makeText(context, "share video", Toast.LENGTH_SHORT).show();
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(context, "No application found to open this file.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return filesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName;
        ImageView savedImage;
        ImageView playIcon;
        ImageView repostID, shareID, deleteID;
        public ViewHolder(View itemView) {
            super(itemView);
            savedImage = (ImageView) itemView.findViewById(R.id.mainImageView);
            playIcon = (ImageView) itemView.findViewById(R.id.playButtonImage);
            repostID = (ImageView) itemView.findViewById(R.id.repostID);
            shareID = (ImageView) itemView.findViewById(R.id.shareID);
            deleteID = (ImageView) itemView.findViewById(R.id.deleteID);
        }
    }
}
