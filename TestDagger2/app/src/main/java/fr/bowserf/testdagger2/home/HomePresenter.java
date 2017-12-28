package fr.bowserf.testdagger2.home;

public class HomePresenter implements HomeContract.Presenter{

    private HomeContract.View view;

    public HomePresenter(HomeContract.View view){
        this.view = view;
    }

    @Override
    public void start() {
        view.displayStartToast();
    }
}
