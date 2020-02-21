package com.example.myfirebase;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myfirebase.firebase.FirebaseDBHelper;
import com.example.myfirebase.interfaces.FBDataListner;
import com.example.myfirebase.model.User;
import com.example.myfirebase.utils.CommonUtils;
import com.example.myfirebase.utils.PaginationScrollListner;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FBDataListner {
    RecyclerView rvUsersList;

    FirebaseDBHelper dbHelper;
    UsersAdapter usersAdapter;
    List<User> mUserList = new ArrayList<>();



    LinearLayoutManager mLayoutManager;

    public int maxPageNumber = 100;
    private boolean isLoading = false;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        CommonUtils.showProgressDialog("Loading", progressDialog);
        progressDialog.show();

        rvUsersList = findViewById(R.id.rv_user_list);

        usersAdapter = new UsersAdapter(this, mUserList);
        mLayoutManager = new LinearLayoutManager(this);
        rvUsersList.setLayoutManager(mLayoutManager);
        rvUsersList.setAdapter(usersAdapter);

        dbHelper = new FirebaseDBHelper(this);
        dbHelper.getUserCollection();
        rvUsersList.addOnScrollListener(new PaginationScrollListner(mLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                usersAdapter.addLoadingFooter();
                dbHelper.getUserCollection();

            }

            @Override
            public int getTotalPageCount() {
                return maxPageNumber;
            }

            @Override
            public boolean isLastPage() {
                return mUserList.size() >= maxPageNumber;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });


    }

    @Override
    public void onSuccess(List<DocumentSnapshot> users) {
        progressDialog.dismiss();
        usersAdapter.removeLoadingFooter();
        isLoading = false;
        if (users.size() > 0){
            for (DocumentSnapshot documentSnapshot : users){
                mUserList.add(new User(String.valueOf(documentSnapshot.get("first_name")),String.valueOf(documentSnapshot.get("last_name"))));
            }
            usersAdapter.notifyDataSetChanged();
        }else {
            maxPageNumber = mUserList.size();
        }
    }

    @Override
    public void onError(Exception e) {
        progressDialog.dismiss();
    }
}
