package com.utn.parcialdamapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DatoDAO {

    @Insert
    void insert(Dato dato);

    @Delete
    void delete(Dato dato);

    @Update
    void update(Dato dato);

    @Query("SELECT * FROM datos WHERE id = :id")
    Dato search(String id);

    @Query("SELECT * FROM datos")
    List<Dato> findAll();


}
