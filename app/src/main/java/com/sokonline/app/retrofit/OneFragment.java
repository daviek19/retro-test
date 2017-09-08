package com.sokonline.app.retrofit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import adapter.MoviesAdapter;
import model.Movie;
import model.MovieResponse;
import rest.ApiClient;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OneFragment extends Fragment {

    private final static String API_KEY = "5cf13b15ffeb47814715e8791db9ac32";
    private final static String TAG = "Retrolog";

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflate the root view
        View rootView = inflater.inflate(R.layout.fragment_one, container, false);

        //Set the Recycler view
        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));


        //Check if the API key is set
        if (API_KEY.isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(), "Please obtain your API KEY from themoviedb.org first!", Toast.LENGTH_SHORT).show();
            //return;
        }

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                int movieCount = response.body().getTotalResults();
                Log.d(TAG, "Number of movies received: " + movies.size());
                Log.d(TAG, "Movie count: " + movieCount);
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getActivity().getApplicationContext()));
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

        return rootView;
    }
}
