package com.example.codebuilder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Program_details extends AppCompatActivity implements Con_Dialog.ExampleDialogListener,Loop_dialog.ExampleDialogListener,Include_dialog.ExampleDialogListener,Variable_dialog.ExampleDialogListener,Statement_Dialog.ExampleDialogListener,Incre_Decre_Dialog_Activity.ExampleDialogListener,Main_Dialog_activity.ExampleDialogListener,Exp_dialog_activity.ExampleDialogListener {

    public static final String EXTRA_ID = "com.example.codebuilder.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example..EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.codebuilder.EXTRA_DESCRIPTION";
    public static final String PROGRAM_CODE = "com.example.codebuilder.PROGRAM_CODE";
    public static final String FLOWCHART_STEP = "com.example.codebuilder.FLOWCHART_STEP";
    public static final String EXTRA_DATE = "com.example.codebuilder.EXTRA_DATE";
    public static final String EXTRA_TIME = "com.example.codebuilder.EXTRA_TIME";
    public static ArrayList<String> Filelist = new ArrayList<>();
    public static ArrayList<String> flowsteps = new ArrayList<>();
    public static String algo_step = "Step 1: Start\n";
    public static int curlybraket = 0;
    public String cif, draged, condition1;
    EditText titletxt, contenttxt;
    ImageButton SaveProg, algo_btn;
    Button tif, telse, tfor, tinclude, twhile, tdowhile, tvariable, tprintf, tscanf, tincre_decre, tswitch, tmain, tcase, tcls, treturn, tbreak, tcurlybraket, texp;
    int i = 0, n = 0, a = 2;
    private EditText editTextTitle;
    private EditText editTextDescription;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_details);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy hh:mm a");
        String date = dateFormat.format(calendar.getTime());
        String time = date.replace("am", "AM").replace("pm", "PM");


        titletxt = findViewById(R.id.title_text);
        contenttxt = findViewById(R.id.content_text);
        SaveProg = findViewById(R.id.save_prog_btn);
        tif = findViewById(R.id.tif);
        tfor = findViewById(R.id.tfor);
        telse = findViewById(R.id.telse);
        twhile = findViewById(R.id.twhile);
        tdowhile = findViewById(R.id.tdowhile);
        tinclude = findViewById(R.id.tinclude);
        tprintf = findViewById(R.id.tprintf);
        tscanf = findViewById(R.id.tscanf);
        tincre_decre = findViewById(R.id.increment_decrement);
        tswitch = findViewById(R.id.tswitch);
        algo_btn = findViewById(R.id.algo_btn);
        tvariable = findViewById(R.id.decrale_varible);
        tmain = findViewById(R.id.tmain);
        tcase = findViewById(R.id.tcase);
        tcls = findViewById(R.id.tcls);
        treturn = findViewById(R.id.truturn);
        tbreak = findViewById(R.id.tbreak);
        tcurlybraket = findViewById(R.id.tcurlybraket);
        texp = findViewById(R.id.texp);

        SaveProg.setOnClickListener(view -> saveNote());
        algo_btn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Algorithm_activity.class)));
        editTextTitle = findViewById(R.id.title_text);
        editTextDescription = findViewById(R.id.content_text);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));


        } else {
            setTitle("Add Note");
        }


        flowsteps.add("Start");
        tinclude.setOnTouchListener((v, event) -> {
            draging(v, event, "include");
            return false;
        });
        twhile.setOnTouchListener((v, event) -> {
            draging(v, event, "while");
            return false;
        });
        tdowhile.setOnTouchListener((v, event) -> {
            draging(v, event, "dowhile");
            return false;
        });
        telse.setOnTouchListener((v, event) -> {
            draging(v, event, "else");
            return false;
        });
        tif.setOnTouchListener((v, event) -> {
            draging(v, event, "if");
            return false;
        });
        tfor.setOnTouchListener((v, event) -> {
            draging(v, event, "for");
            return false;
        });
        tvariable.setOnTouchListener((v, event) -> {
            draging(v, event, "variable");
            return false;
        });
        tprintf.setOnTouchListener((v, event) -> {
            draging(v, event, "printf");
            return false;
        });
        tscanf.setOnTouchListener((v, event) -> {
            draging(v, event, "scanf");
            return false;
        });
        tincre_decre.setOnTouchListener((v, event) -> {
            draging(v, event, "increment_decrement");
            return false;
        });
        tswitch.setOnTouchListener((v, event) -> {
            draging(v, event, "switch");
            return false;
        });
        tcase.setOnTouchListener((v, event) -> {
            draging(v, event, "case");
            return false;
        });
        tmain.setOnTouchListener((v, event) -> {
            draging(v, event, "main");
            return false;
        });
        tcls.setOnTouchListener((v, event) -> {
            draging(v, event, "cls");
            return false;
        });
        treturn.setOnTouchListener((v, event) -> {
            draging(v, event, "return");
            return false;
        });
        tbreak.setOnTouchListener((v, event) -> {
            draging(v, event, "break");
            return false;
        });
        tcurlybraket.setOnTouchListener((v, event) -> {
            draging(v, event, "}");
            return false;
        });
        texp.setOnTouchListener((v, event) -> {
            draging(v, event, "exp");
            return false;
        });

        contenttxt.setOnDragListener((v, event) -> {
            if (event.getAction() == DragEvent.ACTION_DROP) {
                switch (draged) {
                    case "if":
                    case "else":
                    case "while":
                    case "dowhile":
                        if (curlybraket >= 1)
                            openDialog();
                        else
                            Toast.makeText(this, "main method not detected", Toast.LENGTH_SHORT).show();
                        break;

                    case "for":
                        if (curlybraket >= 1)
                            openDialogforloop();
                        else
                            Toast.makeText(this, "main method not detected", Toast.LENGTH_SHORT).show();
                        break;
                    case "include":
                        if (curlybraket == 0)
                            openDialoginclude();
                        else
                            Toast.makeText(this, "main() detected", Toast.LENGTH_SHORT).show();
                        break;
                    case "variable":
                        if (curlybraket >= 1)
                            openDialogVarible();
                        else
                            Toast.makeText(this, "main method not detected", Toast.LENGTH_SHORT).show();
                        break;
                    case "printf":
                    case "scanf":
                        if (curlybraket >= 1)
                            openDialogPrintf();
                        else
                            Toast.makeText(this, "main method not detected", Toast.LENGTH_SHORT).show();
                        break;
                    case "increment_decrement":
                        if (curlybraket >= 1)
                            openDialogIncDec();
                        else
                            Toast.makeText(this, "main method not detected", Toast.LENGTH_SHORT).show();
                        break;
                    case "switch":
                        break;
                    case "case":
                        break;
                    case "main":
                        openDialogMain();
                        break;
                    case "cls":
                        if (curlybraket >= 1)
                            applyCls();
                        else
                            Toast.makeText(this, "main method not detected", Toast.LENGTH_SHORT).show();
                        break;
                    case "return":
                        if (curlybraket >= 1) {

                        } else
                            Toast.makeText(this, "main method not detected", Toast.LENGTH_SHORT).show();
                        break;
                    case "break":
                        if (curlybraket >= 1)
                            applyBreak();
                        else
                            Toast.makeText(this, "main method not detected", Toast.LENGTH_SHORT).show();
                        break;
                    case "}":
                        applyCurlyBraket();
                        break;
                    case "exp":
                        if (curlybraket >= 1)
                           openExpressionDialog();
                        else
                            Toast.makeText(this, "main method not detected", Toast.LENGTH_SHORT).show();
                        break;
                }
                returnToOriginalPosition();
            }
            return true;
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        algo_step = "Step 1: Start\n";
        flowsteps.clear();
        curlybraket=0;
    }



    private void returnToOriginalPosition() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

    }



    public void openDialog() {
        Con_Dialog con = new Con_Dialog();
        con.show(getSupportFragmentManager(), "Condtional Dialog");
    }

    public void openDialogforloop() {
        Loop_dialog con = new Loop_dialog();
        con.show(getSupportFragmentManager(), "For Loop Dialog");
    }

    public void openDialoginclude() {
        Include_dialog con = new Include_dialog();
        con.show(getSupportFragmentManager(), "Include Dialog");
    }

    public void openDialogVarible() {
        Variable_dialog con = new Variable_dialog();
        con.show(getSupportFragmentManager(), "Variable Dialog");
    }

    public void openDialogPrintf() {
        Statement_Dialog con = new Statement_Dialog();
        con.show(getSupportFragmentManager(), "Statement Dialog");
    }

    public void openDialogIncDec() {
        Incre_Decre_Dialog_Activity con = new Incre_Decre_Dialog_Activity();
        con.show(getSupportFragmentManager(), "Increment/Decrement Dialog");
    }

    public void openDialogMain() {
        Main_Dialog_activity con = new Main_Dialog_activity();
        con.show(getSupportFragmentManager(), "Main Dialog");
    }

    public void openExpressionDialog() {
        Exp_dialog_activity con = new Exp_dialog_activity();
        con.show(getSupportFragmentManager(), "Expression Dialog");
    }




    private void saveNote() {

        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }
        Date currentDate = new Date();

        // Create a date format object to format the date as needed
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss",Locale.getDefault());
        dateFormat.format(currentDate);
        time.format(currentDate);


        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(PROGRAM_CODE,algo_step);
        data.putExtra(EXTRA_DATE,dateFormat);
        data.putExtra(EXTRA_TIME, time);
        data.putExtra(FLOWCHART_STEP, flowsteps);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    //    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.save_note:
//                saveNote();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
    @Override
    public void applycondition(String condition) {
        condition1 = condition;
        if (curlybraket >= 1) {
            if (draged.equals("if") || draged.equals("else") || draged.equals("while")) {
                cif = draged + "(" + condition1 + "){\n";
            } else if (draged.equals("dowhile")) {
                cif = "do{\n\nwhile(" + condition1 + ");";
            }
            algo_step = algo_step + "Step " + a + ": " + draged + " " + condition1 + " then\n";
            a++;
            
            curlybraket++;
            contenttxt.append(cif);
        } else {
            Toast.makeText(this, "main method not detected", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void applyforloop(String initilazation, String condition, String update) {
        if (curlybraket >= 1) {
            cif = "for( " + initilazation + " ; " + condition + " ; " + update + " ){\n";
            algo_step = algo_step + "Step " + a + ": Loop following statement for "+condition+" numbers of time\n";
            a++;
            flowsteps.add("For loop Range: "+initilazation+" to "+ condition);
            contenttxt.append((cif));
            curlybraket++;
        } else {
            Toast.makeText(this, "main method not detected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void applyinclude(String filelist) {
        contenttxt.append((filelist));
        algo_step = algo_step + "Step " + a + ": Include all necessary header files\n";
        a++;
    }

    @Override
    public void applyVarible(String type, String name, String value, String Array_status, String Access, String Static, String Const) {
        Utility.showToast(getApplicationContext(), Access);
        cif = "";
        if (Access.equals("Private")) {
            cif += "private ";
        }
        if (Access.equals("Static")) {
            cif += "static ";
        }
        if (Access.equals("Constant")) {
            cif += "Const ";
        }
        if (value.isEmpty()) {
            if (Array_status.equals("Single")) {
                cif += type + " " + name + ";\n";
            }
            if (Array_status.equals("Array")) {
                cif += type + " " + name + "[];\n";
            }
        } else {
            if (Array_status.equals("Single")) {
                cif += type + " " + name + " = " + value + ";\n";
            }
            if (Array_status.equals("Array")) {
                cif += type + " " + name + " = " + value + "[];\n";
            }

        }
        algo_step = algo_step + "Step " + a + ": Declare the " + type + " " + name + "=" + value + "\n";
        a++;
        flowsteps.add("Declare Varible " + name + " with value "+value);
        contenttxt.append(cif);
    }

    @Override
    public void applystatement(String statement) {

        if (statement.isEmpty()) {
            Utility.showToast(getApplicationContext(), "statement can't be empty");
        } else {
            cif = draged + "(" + statement + ");\n";
            if (draged.equals("printf")) {
                algo_step = algo_step + "Step " + a + ": " + "Display " + statement + "\n";
                flowsteps.add("Display " + statement);
            }
            if (draged.equals("scanf")) {
                algo_step = algo_step + "Step " + a + ": " + "Accept the " + statement + " from user\n";
                flowsteps.add("Accept " + statement + " from user");
            }
            a++;
        }
        contenttxt.append(cif);
    }

    @Override
    public void applyincdec(String name1, String status1, String value1) {
        if (value1.isEmpty() || value1 == "1") {
            cif = name1 + status1 + status1 + ";\n";
        } else {
            cif = name1 + " = " + name1 + " " + status1 + " " + value1 + ";\n";
        }
        contenttxt.append(cif);
        if (status1 == "+") {
            algo_step = algo_step + "Step " + a + ": Increment the " + name1 + "\n";
            flowsteps.add("Increment the " + name1);
        }
        else {
            algo_step = algo_step + "Step " + a + ": Decrement the " + name1 + "\n";
            flowsteps.add("Decrement the " + name1);
        }
            a++;
    }

    @Override
    public void applymain_method(String type, String access) {
        cif = access + " " + type + " main(){\n";
        algo_step = algo_step + "Step " + a + ": Define the " + access + " " + type + " main() method\n";
        a++;
        curlybraket++;
        contenttxt.append(cif);
    }

    public void applyCls() {
        cif = "clsscr();\n";
        algo_step = algo_step + "Step " + a + ": Define the Screen\n";
        a++;
        flowsteps.add("Clear the Screen");
        contenttxt.append(cif);
    }

    public void applyBreak() {
        cif = "break;\n";
        algo_step = algo_step + "Step " + a + ": implement break statement\n";
        a++;
        flowsteps.add("Break the Flow");
        contenttxt.append(cif);
    }

    public void applyCurlyBraket() {
        if (curlybraket == 0) Toast.makeText(this, "No { detected", Toast.LENGTH_SHORT).show();
        else {
            contenttxt.append("}\n");
            curlybraket--;
        }
    }

    @Override
    public void applyExp(String exp) {
        String exp1 = exp + ";";
        contenttxt.append(exp1);
        algo_step = algo_step + "Step " + a + ": " + "Do " + exp + "\n";
        flowsteps.add("Calculate " + exp );
        a++;
    }

    public boolean draging(View v, MotionEvent event, String dragz) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(null, shadowBuilder, v, 0);
            draged = dragz;
            return true;
        }
        return false;
    }

}


