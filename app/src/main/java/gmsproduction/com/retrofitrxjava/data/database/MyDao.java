package gmsproduction.com.retrofitrxjava.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;
import gmsproduction.com.retrofitrxjava.Model.Datum;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Hima on 12/18/2018.
 */

@Dao
public interface MyDao {
    @Insert(onConflict = REPLACE)
    void addUser(Datum users);

    @Query("SELECT * FROM Datum") // to read item & SET PRIORITY
    LiveData<List<Datum>> getUsers();

    @Insert(onConflict = REPLACE)
    void addUser2(List<Datum>users);

   /* @Delete
    void deleteUser(Datum users);

    @Update
    void updateUser(Datum users);

    @Query("SELECT * FROM ListItem WHERE id = :itemId") // to select certain id
    LiveData<LauncherActivity.ListItem> getListItemById(int itemId);

    @Query("DELETE from tablename") // to delete all items
    void deleteAll();*/
}