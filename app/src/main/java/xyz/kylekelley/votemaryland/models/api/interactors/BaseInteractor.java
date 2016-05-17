package xyz.kylekelley.votemaryland.models.api.interactors;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

import xyz.kylekelley.votemaryland.models.api.requests.RequestType;

/**
 * Created by marcvandehey on 3/22/16.
 */
public abstract class BaseInteractor<ResponseType, CallbackType> extends AsyncTask<RequestType, Void, ResponseType> {

    private RequestType request;
    private WeakReference<CallbackType> callback;

    @Nullable
    public CallbackType getCallback() {
        CallbackType callbackType = null;

        if (callback != null && callback.get() != null) {
            callbackType = callback.get();
        }

        return callbackType;
    }

    @CallSuper
    public void enqueueRequest(@NonNull RequestType request, @Nullable CallbackType callback) {
        this.request = request;

        if (callback != null) {
            this.callback = new WeakReference<>(callback);
        }

        execute(request);
    }

    @CallSuper
    public void onDestroy() {
        request = null;

        if (callback != null) {
            callback.clear();
        }
    }
}
