package com.example.jiaw2.mysecondapplication.calender;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jiaw2 on 2016/12/23.
 */
public interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

    class Repo {

    }
}
