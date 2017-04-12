package mvp.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import mvp.model.FavoriteTrack;
import mvp.model.TrackList;
import mvp.view.RankingView;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RankingTracksPresenterTest {
    private RankingTracksPresenter presenter;
    private RankingView mockView;

    @Before
    public void setUp() throws Exception {
        mockView = mock(RankingView.class);
        presenter = new RankingTracksPresenter(mockView);
    }

    @Test
    public void initialize() throws Exception {
        presenter.initialize();

        verify(mockView, times(1)).hideLoadingError();
        verify(mockView, times(1)).showLoadingProgress();

        assertTrue(presenter.isInitialCall);
    }

    @Test
    public void successRequest() throws Exception {
        List<FavoriteTrack> list = new ArrayList<>();
        FavoriteTrack mockFavoriteTrack = mock(FavoriteTrack.class);
        list.add(mockFavoriteTrack);

        TrackList mockTrackList = mock(TrackList.class);
        when(mockTrackList.getFavoriteTracks()).thenReturn(list);
        presenter.isInitialCall = true;

        RankingTracksPresenter.RankingCallback callBack = new RankingTracksPresenter.RankingCallback(presenter);
        callBack.onSuccessfulResponse(mockTrackList, presenter);
        verify(mockView, times(1)).setInitialRecyclerView(list);

        callBack.onSuccessfulResponse(mockTrackList, presenter);
        verify(mockView, times(1)).updateRecyclerView(list);
    }

    @Test
    public void failedRequest() throws Exception {
        Throwable mockThrowable = mock(Throwable.class);

        RankingTracksPresenter.RankingCallback callBack = new RankingTracksPresenter.RankingCallback(presenter);
        callBack.onFailedResponse(mockThrowable, presenter);

        verify(mockView, times(1)).hideLoadingProgress();
        verify(mockView, times(1)).showLoadingError();
    }
}