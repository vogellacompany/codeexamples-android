package com.vogella.android.rxjava.simple;

import org.junit.Test;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

import static junit.framework.Assert.assertTrue;


public class RxJavaUnitTest {
    String result="";

    // Simple subscription to a fix value
    @Test
    public void returnAValue(){
        result = "";
        Observable<String> observer = Observable.just("Hello"); // provides datea
        observer.subscribe(s -> result=s); // Callable as subscriber
        assertTrue(result.equals("Hello"));
    }

    @Test public void test(){
        Observable<Todo> todoObservable = Observable.create(new ObservableOnSubscribe<Todo>() {
            @Override
            public void subscribe(ObservableEmitter<Todo> emitter) throws Exception {
                try {
                    List<Todo> todos = RxJavaUnitTest.this.getTodos();
                    for (Todo todo : todos) {
                        emitter.onNext(todo);
                    }
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
    }

    private List<Todo> getTodos() {
        return null;
    }

}
