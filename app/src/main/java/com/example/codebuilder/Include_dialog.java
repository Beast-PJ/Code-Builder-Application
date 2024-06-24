package com.example.codebuilder;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;


public class Include_dialog extends AppCompatDialogFragment {
    int n=0;
    String filelist="";
    private CheckBox stdio,conio,time,stdlib,math,limit,string;
    private ExampleDialogListener listener;

    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.include_dialog_activity,null);




        builder.setView(view)
            .setTitle("condition Statement")
            .setNegativeButton("Cancel", (dialogInterface, i) -> {

            })
                .setPositiveButton("Insert", (dialogInterface, i) -> {
                    if (stdio.isChecked()){
                        filelist += "include<stdio.h>\n";
                    }
                    if (conio.isChecked()){
                        filelist += "include<conio.h>\n";
                    }
                    if (stdlib.isChecked()){
                        filelist += "include<stdlib.h>\n";
                    }
                    if (time.isChecked()){
                        filelist += "include<time.h>\n";
                    }
                    if (string.isChecked()){
                        filelist += "include<string.h>\n";
                    }
                    if (limit.isChecked()){
                        filelist += "include<limit.h>\n";
                    }
                    if (math.isChecked()){
                        filelist += "include<math.h>\n";
                    }
                    listener.applyinclude(filelist);
                });

        stdio = view.findViewById(R.id.stdio);
        conio = view.findViewById(R.id.conio);
        stdlib = view.findViewById(R.id.stdlib);
        time = view.findViewById(R.id.time);
        limit = view.findViewById(R.id.limit);
        string = view.findViewById(R.id.string);
        math = view.findViewById(R.id.math);
        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener =(ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context +"must implement ExampleDialogListener");
        }
    }

    public interface ExampleDialogListener{
    void applyinclude(String filelist);
    }
}
