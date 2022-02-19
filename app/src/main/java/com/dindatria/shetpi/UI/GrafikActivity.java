package com.dindatria.shetpi.UI;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dindatria.shetpi.API.ApiClient;
import com.dindatria.shetpi.Model.DatamasukModel;
import com.dindatria.shetpi.Model.GetDatamasuk;
import com.dindatria.shetpi.Model.GetHasilPengukuran;
import com.dindatria.shetpi.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GrafikActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        getWindow().setFlags(WindowManager.LayoutParams.
                FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Call<GetHasilPengukuran> getHasilPengukuranCall = ApiClient.getApiInterface().gethasilpengukuran();
        getHasilPengukuranCall.enqueue(new Callback<GetHasilPengukuran>() {
            //  pengambilan data
            @Override
            public void onResponse(Call<GetHasilPengukuran> call, Response<GetHasilPengukuran> response) {
                if (response.isSuccessful()) {

                    List<Entry> lineEntries = new ArrayList<Entry>();

                    for (int i=0; i<response.body().getPengukuranModels().size(); i++){
                        lineEntries.add(new Entry(i, response.body().getPengukuranModels().get(i).getSuhu()));
                        drawLineChart(lineEntries);
                    }
                    List<Entry> lineEntries2 = new ArrayList<Entry>();
                    for (int i=0; i<response.body().getPengukuranModels().size(); i++){
                        lineEntries2.add(new Entry(i, response.body().getPengukuranModels().get(i).getDetak_jantung()));
                        drawLineChart2(lineEntries2);
                    }


                } else {
                    Toast.makeText(getApplicationContext(), "Tidak Ada Response Server", Toast.LENGTH_LONG).show();
                }
            }

            public void onFailure(Call<GetHasilPengukuran> call, Throwable t) {
                Toast.makeText(GrafikActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void drawLineChart(List<Entry> datamasukModels) {
        LineChart lineChart = findViewById(R.id.chart);
        List<Entry> lineEntries = datamasukModels;
        LineDataSet lineDataSet = new LineDataSet(lineEntries, getString(R.string.txt_suhu));
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setLineWidth(2);
        lineDataSet.setColor(Color.RED);
        lineDataSet.setCircleColor(Color.YELLOW);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleHoleRadius(3);
        lineDataSet.setDrawHighlightIndicators(true);
        lineDataSet.setHighLightColor(Color.RED);
        lineDataSet.setValueTextSize(12);
        lineDataSet.setValueTextColor(Color.DKGRAY);

        LineData lineData = new LineData(lineDataSet);
        lineChart.getDescription().setText("Banyak Data");
        lineChart.getDescription().setTextSize(12);
        lineChart.setDrawMarkers(true);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        lineChart.animateY(1000);
        lineChart.getXAxis().setGranularityEnabled(true);
        lineChart.getXAxis().setGranularity(1.0f);
        lineChart.getXAxis().setLabelCount(lineDataSet.getEntryCount());
        lineChart.setData(lineData);


    }
    private void drawLineChart2(List<Entry> datamasukModels){
        LineChart lineChart2 = findViewById(R.id.chart2);
        List<Entry> lineEntries2 = datamasukModels;
        LineDataSet lineDataSet2 = new LineDataSet(lineEntries2, getString(R.string.txt_detak_jantung));
        lineDataSet2.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet2.setHighlightEnabled(true);
        lineDataSet2.setLineWidth(2);
        lineDataSet2.setColor(Color.GREEN);
        lineDataSet2.setCircleColor(Color.YELLOW);
        lineDataSet2.setCircleRadius(6);
        lineDataSet2.setCircleHoleRadius(3);
        lineDataSet2.setDrawHighlightIndicators(true);
        lineDataSet2.setHighLightColor(Color.RED);
        lineDataSet2.setValueTextSize(12);
        lineDataSet2.setValueTextColor(Color.DKGRAY);


        LineData lineData2 = new LineData(lineDataSet2);
        lineChart2.getDescription().setText("Banyak Data");
        lineChart2.getDescription().setTextSize(12);
        lineChart2.setDrawMarkers(true);
        lineChart2.getXAxis().setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        lineChart2.animateY(1000);
        lineChart2.getXAxis().setGranularityEnabled(true);
        lineChart2.getXAxis().setGranularity(1.0f);
        lineChart2.getXAxis().setLabelCount(lineDataSet2.getEntryCount());
        lineChart2.setData(lineData2);

    }

    private List<Entry> getDataSet() {
        List<Entry> lineEntries = new ArrayList<Entry>();
        lineEntries.add(new Entry(0, 1));
        lineEntries.add(new Entry(1, 2));
        lineEntries.add(new Entry(2, 3));
        lineEntries.add(new Entry(3, 4));
        lineEntries.add(new Entry(4, 2));
        lineEntries.add(new Entry(5, 3));
        lineEntries.add(new Entry(6, 1));
        lineEntries.add(new Entry(7, 5));
        lineEntries.add(new Entry(8, 7));
        lineEntries.add(new Entry(9, 6));
        lineEntries.add(new Entry(10, 4));
        lineEntries.add(new Entry(11, 5));
        return lineEntries;

//
    }
    private List<Entry> getDataSet2(){
        List<Entry> lineEntries2 = new ArrayList<Entry>();
        lineEntries2.add(new Entry(0, 1));
        lineEntries2.add(new Entry(1, 2));
        lineEntries2.add(new Entry(2, 3));
        lineEntries2.add(new Entry(3, 4));
        lineEntries2.add(new Entry(4, 2));
        lineEntries2.add(new Entry(5, 3));
        lineEntries2.add(new Entry(6, 1));
        lineEntries2.add(new Entry(7, 5));
        lineEntries2.add(new Entry(8, 7));
        lineEntries2.add(new Entry(9, 6));
        lineEntries2.add(new Entry(10, 4));
        lineEntries2.add(new Entry(11, 5));
        return lineEntries2;
    }


}
