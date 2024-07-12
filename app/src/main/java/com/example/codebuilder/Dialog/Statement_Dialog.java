package com.example.codebuilder.Dialog;

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

import com.example.codebuilder.R;

public class Statement_Dialog extends AppCompatDialogFragment {
    private EditText state;
    private ExampleDialogListener listener;
    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.statement_dialog_activity,null);

        builder.setView(view)
                .setTitle("condition Statement")
                .setNegativeButton("Cancel", (dialogInterface, i) -> {

                })
                .setPositiveButton("Insert", (dialogInterface, i) -> {
                    String statement = state.getText().toString();
                    if (statement.isEmpty()){
                        state.setError("Condition can't be empty");
                        return;
                    }
                    listener.applystatement(statement);
                });
        state = view.findViewById(R.id.statement);
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
        void applystatement(String statement);
    }
}

