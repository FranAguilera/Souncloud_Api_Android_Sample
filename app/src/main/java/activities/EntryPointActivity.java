package activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import franjam.soundclouddemo.R;
import mvp.presenter.EntryPointPresenter;
import mvp.view.EntryPointView;

public class EntryPointActivity extends AppCompatActivity implements EntryPointView {
    private static final int N_TABS = 1;
    private EntryPointActivity.TabChangedListener tabChangedListener;
    private EntryPointPresenter presenter;

    @BindView(R.id.container) ViewPager viewPager;
    @BindView(R.id.tabs) TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_point_activity);
        ButterKnife.bind(this);

        tabChangedListener = new EntryPointActivity.TabChangedListener(this);
        setTabViews();

        presenter = new EntryPointPresenter(this);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    public void setRankingIcon() {
        tabLayout.getTabAt(0).setIcon(R.drawable.top_tracks_selected);
    }

    private void setTabViews() {
        SectionsPagerAdapter sectionPageAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this);

        viewPager.setAdapter(sectionPageAdapter);
        viewPager.addOnPageChangeListener(tabChangedListener);
        tabLayout.setupWithViewPager(viewPager);

        setRankingIcon();
    }

    private static class TabChangedListener implements ViewPager.OnPageChangeListener {
        private final WeakReference<EntryPointActivity> weakReference;

        public TabChangedListener(EntryPointActivity entryPointActivity) {
            this.weakReference = new WeakReference<>(entryPointActivity);
        }

        @Override
        public void onPageSelected(int position) {
            EntryPointActivity activity = weakReference.get();
            if (activity == null) return;

            activity.presenter.handlePageSelected(position);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }

    private static class SectionsPagerAdapter extends FragmentPagerAdapter {
        private final WeakReference<EntryPointActivity> weakReference;

        public SectionsPagerAdapter(FragmentManager fragmentManager, EntryPointActivity entryPointActivity) {
            super(fragmentManager);
            this.weakReference = new WeakReference<>(entryPointActivity);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            EntryPointActivity entryPointActivity = this.weakReference.get();
            if (entryPointActivity == null) return null;

            return entryPointActivity.presenter.getTabFragment(position);
        }

        @Override
        public int getCount() {
            return N_TABS;
        }
    }
}
