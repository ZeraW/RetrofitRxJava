package gmsproduction.com.retrofitrxjava.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import gmsproduction.com.retrofitrxjava.Model.Datum;
import gmsproduction.com.retrofitrxjava.R;

/**
 * Created by Hima on 10/31/2018.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    List<Datum> allUsers;

    public PostAdapter() {
    }

    public PostAdapter(List<Datum> allUsers) {
        this.allUsers = allUsers;
    }


    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_layout, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        Datum currentData = allUsers.get(position);
        holder.txt_title.setText(currentData.getFirstName());
        holder.txt_author.setText(currentData.getLastName());

    }

    @Override
    public int getItemCount() {
        return allUsers.size();
    }


    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView txt_title, txt_content, txt_author;

        public PostViewHolder(View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.text_title);
            txt_content = itemView.findViewById(R.id.text_content);
            txt_author = itemView.findViewById(R.id.text_author);
        }
    }
}
