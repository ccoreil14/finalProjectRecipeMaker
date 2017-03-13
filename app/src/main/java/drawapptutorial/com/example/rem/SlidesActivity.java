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

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

        private TextView textTime;
        private Button timerBtn;

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
            TextView textDescription = (TextView) rootView.findViewById(R.id.textDescription);
            TextView textHeat = (TextView) rootView.findViewById(R.id.textHeat);
            textTime = (TextView) rootView.findViewById(R.id.textTime);
            timerBtn = (Button) rootView.findViewById(R.id.timerBtn);
            timerBtn.setText("Start Timer");

            timerBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startTimer();
                }
            });

            if (step == null) {
                textTitle.setText(recipe.getName());
                textDescription.setText(recipe.getDescription());
                textHeat.setVisibility(View.GONE);
                textTime.setVisibility(View.GONE);
                timerBtn.setVisibility(View.GONE);
            }

            else {
                textTitle.setText("Step " + step.getStepOrderNumber());
                textDescription.setText("Details: " + step.getStepDesc());
                switch (step.getAttType())
                {
                    case "Details":
                        textHeat.setVisibility(View.GONE);
                        textTime.setVisibility(View.GONE);
                        timerBtn.setVisibility(View.GONE);
                        break;

                    case "Timer":
                        textHeat.setVisibility(View.GONE);
                        textTime.setText("Step time length: " + step.getTimeOfStep());
                        break;

                    case "Oven":
                        textHeat.setText("Preheat oven to "+step.getHeatLevel() + " degrees fahrenheit.");
                        textTime.setText("Cook for " + step.getTimeOfStep() + " minutes");
                        break;

                    case "Microwave":
                        textHeat.setText("Cook it on "+ step.getHeatLevel());
                        textTime.setText("Cook for " + step.getTimeOfStep() + " minutes");
                        break;
                }
            }

            return rootView;
        }

        public void updateTime(double time) {
            if (time > step.getTimeOfStep() * 60) {
                stopTimer();
                return;
            }
            int seconds = (int)(step.getTimeOfStep() * 60 - time);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            String secondsString = "" + seconds;
            if (secondsString.length() < 2)
                secondsString = "0" + secondsString;
            textTime.setText(minutes + ":" + secondsString);
        }

        private TimerTask currentTimer;
        public void startTimer() {
            currentTimer = new TimerTask(new TimerCallback() {
                @Override
                public void update(double time) {
                    updateTime(time);
                }
            });
            currentTimer.execute(0.0);
            timerBtn.setText("Timer running");
            timerBtn.setEnabled(false);
        }

        public void stopTimer() {
            timerBtn.setText("Start Timer");
            currentTimer = null;

            // TODO: notification if I feel like it
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
