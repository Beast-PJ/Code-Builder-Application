package com.example.codebuilder.Dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.codebuilder.R;

public class Incre_Decre_Dialog_Activity extends AppCompatDialogFragment {
    ToggleButton status;
    private EditText name,value;
    private ExampleDialogListener listener;
    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.incre_decre_dialog_activity ,null);

        builder.setView(view)
                .setTitle("Increment/Decrement Statement")
                .setNegativeButton("Cancel", (dialogInterface, i) -> {

                })
                .setPositiveButton("Insert", (dialogInterface, i) -> {
                    String name1 = name.getText().toString();
                    if (name1.isEmpty()){
                        name.setError("Varible name can't be empty");
                        return;
                    }
                    String value1 = value.getText().toString();
                    String staus1 = status.getText().toString();
                    listener.applyincdec(name1,staus1,value1);
                });
        name = view.findViewById(R.id.varible_names);
        value = view.findViewById(R.id.value);
        status = view.findViewById(R.id.increment_decrement);
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
        void applyincdec(String name1,String status1, String value1);
    }
}

