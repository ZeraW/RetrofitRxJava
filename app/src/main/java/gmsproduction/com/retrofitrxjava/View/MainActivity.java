package gmsproduction.com.retrofitrxjava.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import gmsproduction.com.retrofitrxjava.R;
import gmsproduction.com.retrofitrxjava.ViewModel.UserViewModel;
import gmsproduction.com.retrofitrxjava.Model.Datum;

public class MainActivity extends AppCompatActivity {

    //RetrofitAPI retrofitAPI;
    RecyclerView recycler_posts;
    PostAdapter adapter;
    //CompositeDisposable compositeDisposable = new CompositeDisposable();

    private UserViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //view
        recycler_posts = findViewById(R.id.recycler_post);
        recycler_posts.setHasFixedSize(true);
        recycler_posts.setLayoutManager(new LinearLayoutManager(this));

        mViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        mViewModel.getAllUsers().observe(this, new Observer<List<Datum>>() {
            @Override
            public void onChanged(@Nullable List<Datum> users) {
                //update RecyclerView
                adapter = new PostAdapter(users);
                recycler_posts.setAdapter(adapter);

            }
        });


        //Inti Api
        //retrofitAPI = RetrofitClient.getClient(getApplicationContext()).create(RetrofitAPI.class);


    }

    @Override
    protected void onStop() {
        mViewModel.clear();
        super.onStop();
    }


    /*private void fetchData() {
        compositeDisposable.add(retrofitAPI.getUsers2()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<AllUsers>() {
                    @Override
                    public void onSuccess(AllUsers allUsers) {
                        dispData(allUsers.getData());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));

    }

    private void getUsers() {
        compositeDisposable.add(retrofitAPI.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<AllUsers>() {
                    @Override
                    public void onNext(AllUsers allUsers) {
                        dispData(allUsers.getData());
                        Log.e("sh1t", "onNext: " + allUsers.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("sh1t", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    private void dispData(List<Datum> allUsers) {
        adapter = new PostAdapter(allUsers);
        recycler_posts.setAdapter(adapter);
    }

    */
}
