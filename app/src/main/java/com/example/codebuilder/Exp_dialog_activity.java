package com.example.codebuilder;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Exp_dialog_activity extends AppCompatDialogFragment {
    EditText oprand1,oprand2,oprand3,expression;
    ToggleButton type;
    Spinner spinner;
    String exp;
    private com.example.codebuilder.Exp_dialog_activity.ExampleDialogListener listener;
    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_exp_dialog,null);

        oprand1 = view.findViewById(R.id.oprand1);
        oprand2 = view.findViewById(R.id.oprand2);
        oprand3 = view.findViewById(R.id.oprand3);
        spinner = view.findViewById(R.id.oprator);
        expression = view.findViewById(R.id.expression);
        type = view.findViewById(R.id.type);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext().getApplicationContext(),
                R.array.oprators,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        type.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    oprand1.setVisibility(View.INVISIBLE);
                    oprand2.setVisibility(View.INVISIBLE);
                    oprand3.setVisibility(View.INVISIBLE);
                    spinner.setVisibility(View.INVISIBLE);
                    expression.setVisibility(View.VISIBLE);
                }
                else {
                    oprand1.setVisibility(View.VISIBLE);
                    oprand2.setVisibility(View.VISIBLE);
                    oprand3.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.VISIBLE);
                    expression.setVisibility(View.INVISIBLE);
                }
            }
        });
        builder.setView(view)
                .setTitle("Expression Statement")
                .setNegativeButton("Cancel", (dialogInterface, i) -> {

                })
                .setPositiveButton("Insert", (dialogInterface, i) -> {
                    if (type.isChecked()){
                        String Exp = expression.getText().toString();
                        if (Exp.isEmpty()){
                            expression.setError("Expression can't be empty");
                            return;
                        }
                        else {
                            listener.applyExp(Exp);
                        }
                    }
                    else {
                        String Oprand1 = oprand1.getText().toString();
                        String Oprand2 = oprand2.getText().toString();
                        String Oprand3 = oprand3.getText().toString();
                        String oprator = spinner.getSelectedItem().toString();

                        if (Oprand1.isEmpty()) {
                            oprand1.setError("variable name can't be Empty");
                            return;
                        }
                        if (Oprand2.isEmpty()) {
                            oprand2.setError("variable name can't be Empty");
                            return;
                        }
                        if (Oprand3.isEmpty()) {
                            oprand3.setError("variable name can't be Empty");
                            return;
                        }
                        String type = spinner.getSelectedItem().toString();
                        exp = oprand1 + " = " + oprand2 + " " + oprator + " " + oprand3;
                    }
                            listener.applyExp(exp);
                });

        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener =(com.example.codebuilder.Exp_dialog_activity.ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context +"must implement ExampleDialogListener");
        }
    }

    public interface ExampleDialogListener{
        void applyExp(String exp);
    }
}

