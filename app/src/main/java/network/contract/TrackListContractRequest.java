package network.contract;

import mvp.model.TrackList;
import network.request.SoundCloudRetrofit;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TrackListContractRequest {
    String url = "/tracks?client_id=" + SoundCloudRetrofit.API_KEY;

    @GET(url)
    Call<TrackList> execute(@Query("q") String query,
                            @Query("limit") int limit,
                            @Query("linked_partitioning") int linked,
                            @Query("offset") int offset);
}