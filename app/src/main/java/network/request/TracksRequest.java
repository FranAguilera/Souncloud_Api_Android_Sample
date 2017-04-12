package network.request;

import mvp.model.TrackList;
import network.callback.WeakRefRetrofitCallback;
import network.contract.TrackListContractRequest;
import retrofit2.Call;

public class TracksRequest extends SoundCloudRetrofit {
    private WeakRefRetrofitCallback callback;
    private TrackListContractRequest trackContract;

    public TracksRequest(WeakRefRetrofitCallback callback) {
        this.callback = callback;
        this.trackContract = retrofit.create(TrackListContractRequest.class);
    }

    public void execute(String query, int limit, int index, int offset) {
        Call<TrackList> call = trackContract.execute(query, limit, index, offset);
        call.enqueue(callback);
    }
}