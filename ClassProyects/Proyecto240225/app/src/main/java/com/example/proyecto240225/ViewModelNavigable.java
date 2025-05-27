package com.example.proyecto240225;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
public abstract class ViewModelNavigable {

    public NonBackDispatcher nonBackDispatcher = new NonBackDispatcher();
    public void navigate(Context context, Class actividad) {
        Intent i = new Intent(context, actividad);
        context.startActivity(i);
    }
}
