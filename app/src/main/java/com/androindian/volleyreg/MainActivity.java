package com.androindian.volleyreg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androindian.volleyreg.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    //1
    RequestQueue requestQueue;
    //7
    String url="http://androindian.com/apps/example_app/api.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        //2
        requestQueue = Volley.newRequestQueue(MainActivity.this);

        binding.re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //3
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("name",binding.name.getText().toString().trim());
                    jsonObject.put("mobile",binding.mobile.getText().toString().trim());
                    jsonObject.put("email",binding.email.getText().toString().trim());
                    jsonObject.put("pswrd",binding.pass.getText().toString().trim());
                    jsonObject.put("baction","register_user");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //4

                JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.POST,
                        url, jsonObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //8
                                try {
                                    String res=response.getString("response");

                                    if(res.equalsIgnoreCase("failed")){
                                        String res1=response.getString("user");
                                        Toast.makeText(MainActivity.this, res1, Toast.LENGTH_SHORT).show();

                                    }else if(res.equalsIgnoreCase("success")){
                                        String res1=response.getString("user");
                                        Toast.makeText(MainActivity.this, res1, Toast.LENGTH_SHORT).show();

                                    }else {
                                        String res1=response.getString("user");
                                        Toast.makeText(MainActivity.this, res1, Toast.LENGTH_SHORT).show();

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                            //5
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
                //6
                requestQueue.add(objectRequest);
            }


        });
    }
}
