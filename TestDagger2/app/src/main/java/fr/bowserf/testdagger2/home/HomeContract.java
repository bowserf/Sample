package fr.bowserf.testdagger2.home;

public interface HomeContract {

    interface View{

        void displayStartToast();

    }

    interface Presenter{

        void start();

    }

}
