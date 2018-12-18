package gmsproduction.com.retrofitrxjava.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import gmsproduction.com.retrofitrxjava.Model.Datum;

/**
 * Created by Hima on 12/18/2018.
 */

public class UserViewModel extends AndroidViewModel {
    private UserRepository repository;
    private LiveData<List<Datum>> allNotes;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allNotes = repository.getAllUsers();
    }

    public LiveData<List<Datum>> getAllUsers() {
        return allNotes;
    }

    public void clear(){
        repository.onStopClear();
    }
}
