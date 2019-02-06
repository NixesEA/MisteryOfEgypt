package ru.pushapp.misteryofegypt.start;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import ru.pushapp.misteryofegypt.R;

public class ShopFragment extends Fragment implements View.OnClickListener {

    ImageButton buy;
    ImageButton close;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);


        buy = view.findViewById(R.id.btn_buy);
        buy.setOnClickListener(this);

        close = view.findViewById(R.id.shop_close);
        close.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shop_close:{
                getActivity().onBackPressed();
                break;
            }
            case R.id.btn_buy:{
                buyExternalLife();
                break;
            }
        }

    }

    private void buyExternalLife() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("local", Context.MODE_MULTI_PROCESS);
        int userMoney = sharedPreferences.getInt("money", 0);
        int currentCountExternalLife = sharedPreferences.getInt("life", 0);

        if (userMoney > 200){
            userMoney -= 200;
            currentCountExternalLife++;

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("money", userMoney);
            editor.putInt("life", currentCountExternalLife);
            editor.commit();
        }else{
            Toast.makeText(getActivity(),"Нужно больше золота", Toast.LENGTH_LONG).show();
        }
    }
}
