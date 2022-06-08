package com.example.myrestaurants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;
import android.view.View;

import com.example.myrestaurants.adapters.RestaurantRecyclerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import models.Business;
import models.YelpBusinessesSearchResponse;
import network.YelpApi;
import network.YelpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantsActivity extends AppCompatActivity {
        @BindView(R.id.locationTextView) TextView mLocationTextView;
        @BindView(R.id.recyclerView)
        RecyclerView mRecyclerView ;


        @BindView(R.id.errorTextView) TextView mErrorTextView;
        @BindView(R.id.progressBar)
        ProgressBar mProgressBar;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_restaurants);
                ButterKnife.bind(this);

                Intent intent = getIntent();
                String location = intent.getStringExtra("location");

                YelpApi client = YelpClient.getClient();

                Call<YelpBusinessesSearchResponse> call = client.getRestaurants(location, "restaurants");

                call.enqueue(new Callback<YelpBusinessesSearchResponse>() {
                        @Override
                        public void onResponse(Call<YelpBusinessesSearchResponse> call, Response<YelpBusinessesSearchResponse> response) {
                                hideProgressBar();

                                if (response.isSuccessful()) {
                                        List<Business> restaurants = response.body().getBusinesses();
                                        RestaurantRecyclerAdapter mAdapter = new RestaurantRecyclerAdapter(restaurants);
                                        mRecyclerView.setAdapter(mAdapter);
                                        RecyclerView.LayoutManager layoutManager =
                                                new LinearLayoutManager(RestaurantsActivity.this);
                                        mRecyclerView.setLayoutManager(layoutManager);
                                        mRecyclerView.setHasFixedSize(true);

                                        showRestaurants();
                                } else {
                                        showUnsuccessfulMessage();
                                }
                        }

                        @Override
                        public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {
                                hideProgressBar();
                                showFailureMessage();
                        }

                });
        }

        private void showFailureMessage() {
                mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
                mErrorTextView.setVisibility(View.VISIBLE);
        }

        private void showUnsuccessfulMessage() {
                mErrorTextView.setText("Something went wrong. Please try again later");
                mErrorTextView.setVisibility(View.VISIBLE);
        }

        private void showRestaurants() {
                mRecyclerView.setVisibility(View.VISIBLE);
        }

        private void hideProgressBar() {
                mProgressBar.setVisibility(View.GONE);
        }
}


