package com.example.myfirebase.firebase;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.myfirebase.interfaces.FBDataListner;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class FirebaseDBHelper {
    private Query next;
    FirebaseFirestore db;
    FBDataListner fbDataListner;

    public FirebaseDBHelper(Context mContext) {
        db = FirebaseFirestore.getInstance();
        this.fbDataListner = (FBDataListner) mContext;

        next = db.collection("users")
                .orderBy("first_name")
                .limit(20);

    }

    public void getUserCollection() {
        next.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().getDocuments().size() > 0) {
                        DocumentSnapshot lastVisible = task.getResult().getDocuments()
                                .get(task.getResult().getDocuments().size() - 1);
                        next = db.collection("users")
                                .orderBy("first_name")
                                .startAfter(lastVisible)
                                .limit(20);
                    }
                    fbDataListner.onSuccess(task.getResult().getDocuments());

                } else {
                    fbDataListner.onError(task.getException());
                }
            }
        });
    }


}
