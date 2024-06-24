package com.example.codebuilder;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Database(entities = {Note.class}, version = 3)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext()
                    , NoteDatabase.class, "notes_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    public abstract NoteDoa noteDoa();

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDoa noteDao;

        private PopulateDbAsyncTask(NoteDatabase db) {
            noteDao = db.noteDoa();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            //Calendar calendar = Calendar.getInstance();
            //String currentDate  = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());

//            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
//            String result = currentDate+", "+sdf.format(Calendar.getInstance().getTime());
//            System.out.println(result);

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
            String date = dateFormat.format(calendar.getTime());
            String ntime = timeFormat.format(calendar.getTime());
            String time = ntime.replace("am", "AM").replace("pm", "PM");
            return null;
        }
    }

}
