package com.cst2335.erickproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RvJson extends AppCompatActivity {
    //https://api.edamam.com/api/food-database/parser?app_id=05584855&app_key=3482f48b78ba3c7ca6f09daeb1d14c81&ingr=apple
    static String JSON_URL = "https://api.edamam.com/api/food-database/parser?app_id=05584855&app_key=3482f48b78ba3c7ca6f09daeb1d14c81&ingr=apple";

    List<FoodModelClass> foodList;
    RecyclerView recView;
    ArrayList<HashMap<String, String>> foodItemList, foodDetailsList;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_json);

        foodList = new ArrayList<>();
        recView = findViewById(R.id.rv_new);

        GetData getData = new GetData();
        getData.execute();
    }

    public class GetData extends AsyncTask<String, String, String>{
        JSONArray jsonArray;
        @Override
        protected String doInBackground(String... strings) {
            String current = "";
            try{
                URL url;


                try{
                    url = new URL(JSON_URL);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder json_results = new StringBuilder();

                    String line;
                    while ((line = reader.readLine()) != null) {
                        json_results.append(line + "\n");
                    }

                    String result = json_results.toString();
                    JSONObject json_Object = new JSONObject(result);
                    //publishProgress(20);

                    jsonArray = json_Object.getJSONArray("hints");

                    for (int index = 0; index < jsonArray.length(); index++)
                        try {
                            JSONObject indexObject = jsonArray.getJSONObject(index);
                            JSONObject foodObject = indexObject.getJSONObject("food");
                            // Pulling items from the array
                            //Name of the food
                            String label = foodObject.getString("label");
                            //Nested under Nutrients
                            JSONObject nutrition_Object = foodObject.getJSONObject("nutrients");

                            //Calories of the food
                            String calorieValue = nutrition_Object.getString("ENERC_KCAL");

                            //Fat of the food
                            String fatValue = nutrition_Object.getString("FAT");

                            //Protein of the food
                            String carbValue = nutrition_Object.getString("PROCNT");
                            HashMap<String, String> food = new HashMap<>();
                            food.put("Label", label);
                            food.put("Calories", "Calories: " + formatDecimal(calorieValue));
                            food.put("Fat", "Fat: " + formatDecimal(fatValue) + "g");
                            food.put("Carbs", "Carb: " + formatDecimal(carbValue) + "g");

                            foodItemList.add(food);
                            foodDetailsList.add(food);

                        } catch (JSONException e) {
                        }
                } catch (Exception e) {
                    Log.i("Exception", e.getMessage());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            /**int data = isr.read();
                    while(data != -1){
                        current += (char) data;
                        data = isr.read();
                    }
                    return current;
                 } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;*/
            return "done";
        }

        @Override
        protected void onPostExecute(String s) {

            if (jsonArray == null || jsonArray.isNull(0)) {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.Error, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
                View view = toast.getView();
                view.setBackgroundColor(Color.RED);
                toast.show();
            } else {
                FoodAdapter2 adapter = new FoodAdapter2(RvJson.this, foodList);
                //adapter = new SimpleAdapter(RvJson.this, foodItemList,
                        //R.layout.activity_rv_json, new String[]{"label", "category", "image"},
                        //new int[]{R.id.name_txt, R.id.category_txt, R.id.image_view1});
                recView.setLayoutManager(new LinearLayoutManager(RvJson.this));
                recView.setAdapter((RecyclerView.Adapter) adapter);
                //
                //recView.setAdapter(adapter);
            }

            /**
            try{
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray =  jsonObject.getJSONArray("parsed");
                for (int i =0; i< jsonArray.length(); i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    FoodModelClass model =  new FoodModelClass();
                    model.setName(jsonObject1.getString("label"));
                    model.setCategory((jsonObject1.getString("category")));
                    model.setImage(jsonObject1.getString("image"));
                    foodList.add(model);
                }

            } catch (JSONException e){
                e.printStackTrace();
            }

            putDataIntoRecyclerView(foodList);*/
        }
    }

    private void putDataIntoRecyclerView(List<FoodModelClass> foodList){
        FoodAdapter2 adapter = new FoodAdapter2(this, foodList);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(adapter);

    }
    private String formatDecimal(String s) {
        Double dec = Double.parseDouble(s);
        DecimalFormat df = new DecimalFormat("##.00");
        return "" + df.format(dec);
    }
}