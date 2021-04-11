package com.example.food;
import android.widget.EditText;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.Login;
public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText user = view.findViewById(R.id.editTextTextPersonName);
        EditText pass = view.findViewById(R.id.editTextTextPassword);
        view.findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = user.getText().toString();
                String password = pass.getText().toString();
                Login newPerson = new Login(name, password);
                newPerson.CheckUser(name, password);
                System.out.println("here");
                newPerson.SignUp(name, password, "C:\\Users\\Jeffrey Xie\\AndroidStudioProjects\\Food2\\app\\src\\main\\java\\java\\Profile.JSON");
            }
        });
    }
}