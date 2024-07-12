package com.example.codebuilder.Dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.codebuilder.R;

public class Variable_dialog extends AppCompatDialogFragment {
    EditText vname,vvalue;
    ToggleButton access_specifier,constant,static_status,Array_toggle;
    Spinner spinner;
    private ExampleDialogListener listener;
    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.varible_dialog_activity,null);
        spinner = view.findViewById(R.id.datatype);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext().getApplicationContext(),
                R.array.datatypes,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        builder.setView(view)
                .setTitle("Declaration Statement")
                .setNegativeButton("Cancel", (dialogInterface, i) -> {

                })
                .setPositiveButton("Insert", (dialogInterface, i) -> {
                    String name = vname.getText().toString();
                    if(name.isEmpty()){
                        vname.setError("variable name can't be Null");
                        return;
                    }
                    String type = spinner.getSelectedItem().toString();
                    String value = vvalue.getText().toString();
                    String Array_status = Array_toggle.getText().toString();
                    String Access = access_specifier.getText().toString();
                    String Static = Array_toggle.getText().toString();
                    String Const = Array_toggle.getText().toString();
                    listener.applyVarible(type,name,value,Array_status,Access,Static,Const);
                });
        vname = view.findViewById(R.id.varible_names);
        vvalue = view.findViewById(R.id.varible_value);
        Array_toggle = view.findViewById(R.id.array_toggle);
        access_specifier = view.findViewById(R.id.access_specifer);
        constant = view.findViewById(R.id.Constant);
        static_status = view.findViewById(R.id.static_status);
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
        void applyVarible(String type,String name,String value,String Array_status,String Access,String Static,String Const);
    }
}

