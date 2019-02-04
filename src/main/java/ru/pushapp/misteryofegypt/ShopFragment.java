package ru.pushapp.misteryofegypt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.unity3d.player.UnityPlayer;

public class ShopFragment extends Fragment implements View.OnClickListener{

    private UnityPlayerActivity mUnityMainActivity;
    private UnityPlayer mUnityPlayer;

    ImageButton buy;
    ImageButton close;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        mUnityMainActivity = (UnityPlayerActivity) getActivity();
//        mUnityPlayer = mUnityMainActivity.GetUnityPlayer();

        buy = view.findViewById(R.id.btn_buy);
        buy.setOnClickListener(this);

        close = view.findViewById(R.id.shop_close);
        close.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shop_close:
                mUnityMainActivity.onBackPressed();
                break;
            case R.id.btn_buy:
                //TODO
//                mUnityPlayer.UnitySendMessage("Player","buyLife", "");
                break;
        }

    }
}
