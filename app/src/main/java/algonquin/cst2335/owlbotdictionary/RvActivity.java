//package algonquin.cst2335.owlbotdictionary;
//
//import androidx.appcompat.app.AppCompatActivity;
//import android.os.Bundle;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import android.os.AsyncTask;
//import android.util.Log;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//public class RvActivity extends AppCompatActivity {
//
//    static String JSON_URL = "https://api.edamam.com/api/food-database/parser?app_id=05584855&app_key=3482f48b78ba3c7ca6f09daeb1d14c81&ingr=apple";
//    List<WordModelClass> wordList;
//    RecyclerView recView;
//    private String stringURL;
//    String editSearch;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_rv);
//        wordList = new ArrayList<>();
//        recView = findViewById(R.id.rv_new);
//        GetData getData = new GetData();
//        getData.execute();
//        editSearch = "word";
//    }
//
//    public class GetData extends AsyncTask<String, String, String>{
//        JSONArray jsonArray;
//
//        @Override
//        protected String doInBackground(String... strings) {
//            String current = "";
//            try{
//                URL url;
//                HttpURLConnection urlConnection =  null;
//                stringURL = "https://owlbot.info/api/v4/dictionary/"
//                        + URLEncoder.encode(editSearch, "UTF-8");
//
//                try{
//                    url = new URL(JSON_URL);
//                    urlConnection = (HttpURLConnection) url.openConnection();
//                    urlConnection.setRequestProperty("Authorization", "Token 83f1e73ee0b5daed582b133867bb10f0b32db6a4");
//                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//                    //InputStream is = urlConnection.getInputStream();
//                    /**String text = (new BufferedReader(
//                            new InputStreamReader(in, StandardCharsets.UTF_8)))
//                            .lines()
//                            .collect(Collectors.joining("\n"));*/
//                    InputStreamReader isr = new InputStreamReader(in);
//                    int data = isr.read();
//                    while(data != -1){
//                        current += (char) data;
//                        data = isr.read();
//                    }
//                    return current;
//             } catch (MalformedURLException e) {
//                 e.printStackTrace();
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }finally {
//                 if(urlConnection != null){
//                     urlConnection.disconnect();
//                    }
//                }
//             } catch (Exception e) {
//                 e.printStackTrace();
//                 }
//             return current;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            try{
//                JSONObject theDocument = new JSONObject(s);
//                //JSONObject jsonObject = new JSONObject(s);
//                String word = theDocument.getString("word");
//                String pronunciation = theDocument.getString("pronunciation");
//                JSONArray jsonArray =  theDocument.getJSONArray("definitions");
//
//             for (int i =0; i< jsonArray.length(); i++){
//                     JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                     WordModelClass model =  new WordModelClass();
//                     model.setDefinition(jsonObject1.getString("definition"));
//                     wordList.add(model);
//                 }
//             } catch (JSONException e){
//             e.printStackTrace();
//             }
//             putDataIntoRecyclerView(wordList);
//        }
//    }
//    private void putDataIntoRecyclerView(List<WordModelClass> wordList){
//        AdapterClass adapter = new AdapterClass(this, wordList, RvActivity.this);
//        recView.setLayoutManager(new LinearLayoutManager(this));
//        recView.setAdapter(adapter);
//    }
//
//    private String formatDecimal(String s) {
//        Double dec = Double.parseDouble(s);
//        DecimalFormat df = new DecimalFormat("##.00");
//        return "" + df.format(dec);
//    }
//}
