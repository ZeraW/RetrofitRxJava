package gmsproduction.com.retrofitrxjava.ViewModel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import gmsproduction.com.retrofitrxjava.data.database.MyDao;
import gmsproduction.com.retrofitrxjava.data.database.MyDb;
import gmsproduction.com.retrofitrxjava.Model.AllUsers;
import gmsproduction.com.retrofitrxjava.Model.Datum;
import gmsproduction.com.retrofitrxjava.data.network.Retrofit.RetrofitAPI;
import gmsproduction.com.retrofitrxjava.data.network.Retrofit.RetrofitClient;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class UserRepository {

    private MyDao dao ;
    private LiveData<List<Datum>> allUsers;
    private RetrofitAPI retrofitAPI;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public UserRepository(Application application) {
        MyDb db = MyDb.getInstance(application);
        dao = db.myDao();
        retrofitAPI = RetrofitClient.getClient(application).create(RetrofitAPI.class);
        allUsers = dao.getUsers();
    }

    public LiveData<List<Datum>> getAllUsers() {
        compositeDisposable.add(retrofitAPI.getUsers2()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<AllUsers>() {
                    @Override
                    public void onSuccess(AllUsers allUsers) {
                        for (int i=0; i<allUsers.getData().size(); i++){
                            insert(allUsers.getData().get(i));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));

        return allUsers;
    }

    public void insert(Datum datum) {
        new InsertNoteAsyncTask(dao).execute(datum);
    }

    public void insert2(List<Datum> datum){
        new InsertNoteAsyncTask2(dao).execute(datum);
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Datum, Void, Void> {
        private MyDao dao;

        private InsertNoteAsyncTask(MyDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Datum... datum) {
            dao.addUser(datum[0]);
            return null;
        }
    }





    public void onStopClear(){
        compositeDisposable.clear();
    }



    private static class InsertNoteAsyncTask2 extends AsyncTask<List<Datum>,Void,Void> {
        private MyDao dao;

        public InsertNoteAsyncTask2(MyDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(List<Datum>[] lists) {
            dao.addUser2(lists);
            return null;
        }
    }
}
