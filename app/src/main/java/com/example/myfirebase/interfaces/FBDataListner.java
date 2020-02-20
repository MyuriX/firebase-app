package com.example.myfirebase.interfaces;

import com.example.myfirebase.model.User;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

public interface FBDataListner {
    void onSuccess(List<DocumentSnapshot> users);
    void onError(Exception e);
}
