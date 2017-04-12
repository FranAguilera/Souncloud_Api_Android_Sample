package network.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SoundCloudRetrofit {
    public static final String API_KEY = "YOUR_API_KEY_HERE";
    private static final String MAIN_SITE = "http://api.soundcloud.com";
    protected Retrofit retrofit;

    public SoundCloudRetrofit() {
        retrofit = getRetrofit();
    }

    private Retrofit getRetrofit() {
        Gson gson = new GsonBuilder().create();

        return new Retrofit.Builder()
                .baseUrl(MAIN_SITE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
