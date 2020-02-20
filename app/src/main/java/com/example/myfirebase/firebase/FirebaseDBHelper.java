package com.example.myfirebase.firebase;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.myfirebase.interfaces.FBDataListner;
import com.example.myfirebase.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class FirebaseDBHelper {
    FirebaseFirestore db;
    FBDataListner fbDataListner;

    public FirebaseDBHelper(Context mContext) {
        db = FirebaseFirestore.getInstance();
        this.fbDataListner= (FBDataListner) mContext;

    }

    public void getUserCollection() {
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<User> list = new ArrayList<>();
//                            for (QueryDocumentSnapshot document : task.getResult().getDocuments()) {
//                                document.getData();
//                                User user = new User(String.valueOf(document.get("first_name")), String.valueOf(document.get("last_name")));
//                                list.add(user);
//                            }
                            fbDataListner.onSuccess(task.getResult().getDocuments());
                            Log.d(TAG, list.toString());
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                            fbDataListner.onError(task.getException());
                        }
                    }
                });

    }
}
