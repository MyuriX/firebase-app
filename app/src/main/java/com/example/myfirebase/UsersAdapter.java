package com.example.myfirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirebase.model.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    Context mContect;
    List<User> mUserList;
    private boolean isLoadingAdded = false;
    public static final int LOADING = 1002;
    public static final int DATA = 1003;

    public UsersAdapter(Context mContect, List<User> mUserList){
        this.mContect = mContect;
        this.mUserList = mUserList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType){
            case DATA:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_users,parent,false);
                viewHolder = new ViewHolder(view);
                break;

            case LOADING:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress,parent,false);
                viewHolder = new ViewHolder(view);
                break;
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case DATA:
                User user = mUserList.get(position);
                holder.tvFirstName.setText(user.first_name);
                holder.tvLastName.setText(user.last_name);

                break;

            case LOADING:
                break;
        }

    }

    public void addLoadingFooter(){
        isLoadingAdded = true;
        mUserList.add(new User("first_name","last_name"));
        notifyItemInserted(mUserList.size()- 1);
    }

    public void removeLoadingFooter(){
        isLoadingAdded = false;
        if (mUserList.size() == 0) return;
        int position = mUserList.size() - 1;
        User item = mUserList.get(position);
        if (item != null){
            mUserList.remove(position);
            notifyItemRemoved(position);
        }
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == mUserList.size() - 1 && isLoadingAdded) ? LOADING:DATA;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFirstName,tvLastName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFirstName = itemView.findViewById(R.id.tv_first_name);
            tvLastName = itemView.findViewById(R.id.tv_last_name);
        }
    }
}
