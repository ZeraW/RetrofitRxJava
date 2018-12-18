package gmsproduction.com.retrofitrxjava.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import gmsproduction.com.retrofitrxjava.Model.Datum;

/**
 * Created by Hima on 12/18/2018.
 */

@Database(entities = {Datum.class}, version = 1,exportSchema = false)  // the database class can have more than one table
public abstract class MyDb extends RoomDatabase {
    // to create single tone which mean this Db is created only one time per thread which mean if 2nd thread try to use it will not be building it from start
    private static MyDb instance;

    public abstract MyDao myDao();

    public static synchronized MyDb getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MyDb.class, "user_database")
                    .fallbackToDestructiveMigration()   // to remove all the data when upgrade the ver
                    .build();
        }
        return instance;
    }
}
