package network.callback;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class WeakRefRetrofitCallback<ResultType, PresenterType> implements Callback<ResultType> {
    protected WeakReference<PresenterType> weakReference;

    public WeakRefRetrofitCallback(PresenterType presenterType) {
        weakReference = new WeakReference<>(presenterType);
    }

    @Override
    public void onResponse(Call<ResultType> call, Response<ResultType> response) {
        PresenterType presenterType = weakReference.get();
        if (presenterType == null) return;

        ResultType resultType = response.body();
        if (resultType == null) {
            final StringBuilder errorDetails = new StringBuilder();
            errorDetails.append(response.code());
            errorDetails.append(" ");
            errorDetails.append(response.message());

            Throwable throwable = new Throwable(errorDetails.toString());
            onFailedResponse(throwable, presenterType);
        } else {
            onSuccessfulResponse(resultType, presenterType);
        }
    }

    @Override
    public void onFailure(Call<ResultType> call, Throwable t) {
        PresenterType presenterType = weakReference.get();
        if (presenterType == null) return;

        onFailedResponse(t, presenterType);
    }

    protected abstract void onSuccessfulResponse(ResultType resultType, PresenterType presenterType);

    protected abstract void onFailedResponse(Throwable throwable, PresenterType presenterType);
}


