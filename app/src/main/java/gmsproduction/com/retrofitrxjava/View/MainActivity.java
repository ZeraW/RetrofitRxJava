package gmsproduction.com.retrofitrxjava.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gmsproduction.com.retrofitrxjava.R;
import gmsproduction.com.retrofitrxjava.ViewModel.UserViewModel;
import gmsproduction.com.retrofitrxjava.Model.Datum;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_post)
    RecyclerView recycler_posts;
    PostAdapter adapter;

    private UserViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //view
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

    }


    @Override
    protected void onStop() {
        mViewModel.clear();
        super.onStop();
    }

}
