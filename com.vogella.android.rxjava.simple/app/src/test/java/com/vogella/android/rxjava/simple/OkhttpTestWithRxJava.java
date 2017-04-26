package com.vogella.android.rxjava.simple;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;


public class OkhttpTestWithRxJava {
    Request request;
    OkHttpClient client;

    @Before
    public void setup() {
        client = new OkHttpClient();

        request = new Request.Builder()
                .url("http://www.vogella.com/index.html")
                .build();
    }

    // Simple subscription to a fix value
    @Test
    @Ignore
    public void useOkHttp() {
        Observable<String> observable = Observable.fromCallable(() -> {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException("Call not successful");
            }
            return response.body().toString();
        });
        observable.subscribe(new DisposableObserver<String>() {

            @Override
            public void onNext(String s) {
                System.out.print(s);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error");
            }

            @Override
            public void onComplete() {
                System.out.println("Done");
            }
        });

        String s = observable.blockingFirst();
        // TODO so some validation of the result
    }


    @Test
    public void useOkHttpAsync() {
        Observable<String> observer = Observable.create(emitter ->
        {
            emitter.setCancellable(() -> {
                // for example update the UI remove listener, etlc
            });
            client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        emitter.onError(e);
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        emitter.onNext(response.body().string());
                        emitter.onComplete();
                    }
                }); });
        observer.subscribe(new DisposableObserver<String>() {

            @Override
            public void onNext(String s) {
                System.out.println("onNext " + s);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
                System.out.println("Done");
            }
        });
        observer.blockingFirst();
    }
}
