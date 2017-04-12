package mvp.presenter;

import android.support.v4.app.Fragment;

import fragments.RankingFragment;
import mvp.view.EntryPointView;

public class EntryPointPresenter {
    private EntryPointView view;

    public EntryPointPresenter(EntryPointView view) {
        this.view = view;
    }

    public void handlePageSelected(final int tabPosition) {
        switch (tabPosition) {
            case 0:
                view.setRankingIcon();
                break;
            case 1:
                // TODO: Handle more tabs. e.g.  view.setSettingsIcon();
                break;
        }
    }

    public Fragment getTabFragment(final int tabPosition) {
        Fragment fragment = null;

        switch (tabPosition) {
            case 0:
                fragment = new RankingFragment();
                break;
            case 1:
                // TODO: Handle more tabs. e.g. fragment = new SettingsFragment();

                break;
        }
        return fragment;
    }
}
