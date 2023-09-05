package com.petech.user_register_challenge.network.services;

import com.petech.user_register_challenge.network.dtos.UserDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TestAvatyService {
    @POST("Desafio/rest/desafioRest")
    Call<Void> postUser(@Body UserDTO user);
}
