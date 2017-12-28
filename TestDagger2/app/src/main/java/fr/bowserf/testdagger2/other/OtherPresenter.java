package fr.bowserf.testdagger2.other;

import android.content.Context;

public class OtherPresenter implements OtherContract.Presenter{

    private OtherContract.View view;

    public OtherPresenter(OtherContract.View view, Context context){
        this.view = view;
    }

    @Override
    public void start() {
        view.displayStartToast();
    }
}
