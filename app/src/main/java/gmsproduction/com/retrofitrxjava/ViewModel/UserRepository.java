package gmsproduction.com.retrofitrxjava.ViewModel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import gmsproduction.com.retrofitrxjava.data.database.MyDao;
import gmsproduction.com.retrofitrxjava.data.database.MyDb;
import gmsproduction.com.retrofitrxjava.Model.AllUsers;
import gmsproduction.com.retrofitrxjava.Model.Datum;
import gmsproduction.com.retrofitrxjava.data.network.Retrofit.RetrofitAPI;
import gmsproduction.com.retrofitrxjava.data.network.Retrofit.RetrofitClient;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class UserRepository {

    private MyDao dao ;
    private LiveData<List<Datum>> allUsers;
    private RetrofitAPI retrofitAPI;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private static String TAG = "dadada";
    public UserRepository(Application application) {
        MyDb db = MyDb.getInstance(application);
        dao = db.myDao();
        retrofitAPI = RetrofitClient.getClient(application).create(RetrofitAPI.class);
        allUsers = dao.getUsers();
    }

    public LiveData<List<Datum>> getAllUsers() {
        addUserToDataBase();
        return allUsers;
    }

    private void addUserToDataBase(){
        //Network Request
        compositeDisposable.add(retrofitAPI.getUsers2()
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<AllUsers>() {
                    @Override
                    public void onSuccess(AllUsers allUsers) {
                        //Save In DataBase
                        Observable<List<Datum>> userObservable = Observable.fromArray(allUsers.getData());
                        compositeDisposable.add(userObservable
                                .subscribeOn(Schedulers.io())
                                .subscribeWith(new DisposableObserver<List<Datum>>() {
                                    @Override
                                    public void onNext(List<Datum> data) {
                                        dao.addUser2(data);
                                        Log.e(TAG, "onNext: " +data );
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.e(TAG, "onError: " + e.getMessage() );
                                    }

                                    @Override
                                    public void onComplete() {
                                    }
                                }));

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }


    public void onStopClear(){
        compositeDisposable.clear();
    }


}
