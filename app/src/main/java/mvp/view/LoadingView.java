package mvp.view;

public interface LoadingView {
    void showLoadingProgress();
    void hideLoadingProgress();
    void displayErrorText(final String errorMessage);
    void showLoadingError();
    void hideLoadingError();
}
