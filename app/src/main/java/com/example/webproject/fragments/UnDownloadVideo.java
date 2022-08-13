package com.example.webproject.fragments;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.webproject.R;
import com.example.webproject.adapters.StoryAdapter;
import com.example.webproject.base.Constant;
import com.example.webproject.models.StoryModel;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class UnDownloadVideo extends Fragment {



    private RecyclerView recyclerView;
    private SwipeRefreshLayout recyclerLayout;
    private File[] files;
    ArrayList<Object> filesList = new ArrayList<>();
    StoryAdapter storyAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final  View view=inflater.inflate(R.layout.fragment_undownload_video, container, false);

        recyclerView=view.findViewById(R.id.recyclerview);
        recyclerLayout=view.findViewById(R.id.swipeRecycleview);

        recyclerLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerLayout.setRefreshing(true);
                setUpRefreshLayout();
                (
                        new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        recyclerLayout.setRefreshing(false);
                        Toast.makeText(getContext(), "Refresh!", Toast.LENGTH_SHORT).show();
                    }
                },2000);


            }
        });
        Dexter.withContext(getContext()).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        setUpRefreshLayout();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
        return view;
    }



    private void setUpRefreshLayout() {
        filesList.clear();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext().getApplicationContext(),2));
        ArrayList<Object> lst=getDataWhatsApp();
        storyAdapter = new StoryAdapter(getContext().getApplicationContext(), getDataWhatsAppBusiness(lst));
        recyclerView.setAdapter(storyAdapter);
        storyAdapter.notifyDataSetChanged();
    }
    private ArrayList<Object> getDataWhatsAppBusiness(ArrayList<Object> lst) {
        StoryModel f;
        String targetPath = Environment.getExternalStorageDirectory().getAbsolutePath() + Constant.FOLDER_NAME_WHATSAPP_BUSINESS + "Media/.Statuses";
        File targetDirector = new File(targetPath);
        files = targetDirector.listFiles();


        try {
            Arrays.sort(files, new Comparator() {
                public int compare(Object o1, Object o2) {

                    if (((File) o1).lastModified() > ((File) o2).lastModified()) {
                        return -1;
                    } else if (((File) o1).lastModified() < ((File) o2).lastModified()) {
                        return +1;
                    } else {
                        return 0;
                    }
                }

            });

            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                f = new StoryModel();
                f.setName("Status: "+(i));
                f.setUri(Uri.fromFile(file));
                f.setPath(files[i].getAbsolutePath());
                f.setFilename(file.getName());
                if(f.getUri().toString().endsWith(".mp4")){
                    filesList.add(f);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        lst.addAll(filesList);
        return lst;

    }

    private ArrayList<Object> getDataWhatsApp() {
        StoryModel f;
        String targetPath = Environment.getExternalStorageDirectory().getAbsolutePath() + Constant.FOLDER_NAME_WHATSAPP + "Media/.Statuses";
        File targetDirector = new File(targetPath);
        files = targetDirector.listFiles();


        try {
            Arrays.sort(files, new Comparator() {
                public int compare(Object o1, Object o2) {

                    if (((File) o1).lastModified() > ((File) o2).lastModified()) {
                        return -1;
                    } else if (((File) o1).lastModified() < ((File) o2).lastModified()) {
                        return +1;
                    } else {
                        return 0;
                    }
                }

            });

            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                f = new StoryModel();
                f.setName("Status: "+(i));
                f.setUri(Uri.fromFile(file));
                f.setPath(files[i].getAbsolutePath());
                f.setFilename(file.getName());
                if(f.getUri().toString().endsWith(".mp4")){
                    filesList.add(f);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return filesList;

    }


}