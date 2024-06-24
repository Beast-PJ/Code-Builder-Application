package com.example.codebuilder;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDoa {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM NOTE_TABLE")
    void deleteAllNotes();

    @Query("SELECT * FROM NOTE_TABLE WHERE title like :NoteText")
    LiveData<List<Note>> getSearchedNotes(String NoteText);


    @Query("SELECT * FROM NOTE_TABLE")
    LiveData<List<Note>> getAllNotes();
}
