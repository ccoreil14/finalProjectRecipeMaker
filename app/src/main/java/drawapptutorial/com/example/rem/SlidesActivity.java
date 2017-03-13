package drawapptutorial.com.example.rem;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.ArrayList;

public class SlidesActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    public RecipeObj recipe;
    public ArrayList<RecipeStepObj> steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slides);

        String recipeJson = getIntent().getStringExtra("recipeJson");
        loadFromJson(recipeJson);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    public void loadFromJson(String json) {
        steps = new ArrayList<>();
        recipe = MainActivity.jsonToRecipe(json, steps);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_slides, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class SlidesFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public RecipeObj recipe;
        public RecipeStepObj step;

        public SlidesFragment() {
        }

        public static SlidesFragment newInstance(RecipeObj recipe, RecipeStepObj step) {
            SlidesFragment fragment = new SlidesFragment();
            fragment.recipe = recipe;
            fragment.step = step;
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_slides, container, false);
            TextView textTitle = (TextView) rootView.findViewById(R.id.textTitle);
            TextView textInner = (TextView) rootView.findViewById(R.id.textInner);

            if (step == null) {
                textTitle.setText(recipe.getName());
                textInner.setText(recipe.getDescription());
            }

            else {
                textTitle.setText("Step " + step.getStepOrderNumber());
                textInner.setText(step.getStepDesc());
            }

            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            RecipeStepObj step = position == 0 ? null : steps.get(position - 1);
            return SlidesFragment.newInstance(recipe, step);
        }

        @Override
        public int getCount() {
            return steps.size() + 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0)
                return recipe.getName();
            else
                return "Step " + position;
        }
    }
}
