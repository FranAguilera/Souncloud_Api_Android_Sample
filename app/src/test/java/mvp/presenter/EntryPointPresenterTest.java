package mvp.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import fragments.RankingFragment;
import mvp.view.EntryPointView;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
public class EntryPointPresenterTest {
    private EntryPointPresenter presenter;
    private EntryPointView mockView;

    @Before
    public void setUp() throws Exception {
        mockView = mock(EntryPointView.class);
        presenter = new EntryPointPresenter(mockView);
    }

    @Test
    public void setRankingTab() throws Exception {
        final int selectedTab = 0;

        presenter.handlePageSelected(selectedTab);
        verify(mockView, times(1)).setRankingIcon();

        boolean isValidInstance = presenter.getTabFragment(selectedTab) instanceof RankingFragment;
        assertTrue(isValidInstance);
    }
}