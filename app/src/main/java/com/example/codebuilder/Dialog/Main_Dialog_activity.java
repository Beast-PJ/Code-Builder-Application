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

public class Main_Dialog_activity extends AppCompatDialogFragment {
    private static Main_Dialog_activity.ExampleDialogListener listener;
    EditText vname;
    ToggleButton access_specifier;
    Spinner spinner;

    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_main_dialog,null);
        spinner = view.findViewById(R.id.type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext().getApplicationContext(),
                R.array.function_type,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        builder.setView(view)
                .setTitle("Main Method")
                .setNegativeButton("Cancel", (dialogInterface, i) -> {

                })
                .setPositiveButton("Insert", (dialogInterface, i) -> {

                    String type = spinner.getSelectedItem().toString();
                    String Access = access_specifier.getText().toString();
                    listener.applymain_method(type,Access);
                });
        vname = view.findViewById(R.id.varible_names);
        access_specifier = view.findViewById(R.id.access_specifer);
        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener =(Main_Dialog_activity.ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context +"must implement ExampleDialogListener");
        }
    }

    public interface ExampleDialogListener{
        void applymain_method(java.lang.String type, java.lang.String access);
    }
}

