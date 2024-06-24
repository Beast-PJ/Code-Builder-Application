package com.example.codebuilder;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Loop_dialog extends AppCompatDialogFragment {
    private EditText cond,inti,updat;
    private ExampleDialogListener listener;
    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.loop_dialoag_activity,null);

        builder.setView(view)
                .setTitle("condition Statement")
                .setNegativeButton("Cancel", (dialogInterface, i) -> {

                })
                .setPositiveButton("Insert", (dialogInterface, i) -> {
                    String initial = inti.getText().toString();
                    String conditi = cond.getText().toString();
                    String update = updat.getText().toString();
                    listener.applyforloop(initial,conditi,update);
                });
        inti = view.findViewById(R.id.initilzation);
        cond = view.findViewById(R.id.condition);
        updat = view.findViewById(R.id.update);
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
        void applyforloop(String initilazation, String condition,String update);
    }
}
