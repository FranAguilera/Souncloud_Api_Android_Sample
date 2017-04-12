package mvp.presenter;

public interface MainPresenter {
    void initialize();
    void clickFloatingActionButton();
    void submitNewSearch(String query);
    void handleOnScrolled(int yPosition, int visibleItems, int totalItems, int pastVisibleItems);
}
