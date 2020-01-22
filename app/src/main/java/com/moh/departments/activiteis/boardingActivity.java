package com.moh.departments.activiteis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.moh.departments.R;
import com.moh.departments.adapters.SlidePagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class boardingActivity extends AppCompatActivity {
    private ViewPager sliderPager;
    private TabLayout indicator;
    private SlidePagerAdapter adapter;
    private List<slide> listSlides;
    private TextView btn_skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarding);

        sliderPager = findViewById(R.id.slider_pager);
        indicator = findViewById(R.id.indicator);
        btn_skip = findViewById(R.id.btn_skip);
        listSlides = new ArrayList<>(3);
        listSlides.add(new slide(R.drawable.onboarding1, "تصفح بيانات المرضى", "يمكنك تصفح بيانات المرضى و مشاهدة السجل الطبي للمريض بالكامل."));
        listSlides.add(new slide(R.drawable.onboarding2, "طلب الإجراءات اللازم عملها للمريض إلكترونياًًًً", "يمكنك طلب اجراء التحاليل والأشعة وغيرها من الفحوصات اللازمة من خلال التطبيق"));
        listSlides.add(new slide(R.drawable.onboarding3, "اشعارات لكل إضافة على ملف المريض", "يمكنك تصفح بيانات المرضى و مشاهدة السجل الطبي للمريض بالكامل."));
        adapter = new SlidePagerAdapter(this, listSlides);
        sliderPager.setAdapter(adapter);
        //  indicator.setupWithViewPager(sliderPager, true);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new boardingActivity.SliderTimer(), 10000, 10000);
        indicator.setupWithViewPager(sliderPager, true);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(boardingActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    //Timer class
    class SliderTimer extends TimerTask {
        @Override
        public void run() {
            boardingActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderPager.getCurrentItem() < listSlides.size() - 1) {
                        sliderPager.setCurrentItem(sliderPager.getCurrentItem() + 1);
                    } else {
                        sliderPager.setCurrentItem(0);
                    }
                }
            });
        }
    }


}

