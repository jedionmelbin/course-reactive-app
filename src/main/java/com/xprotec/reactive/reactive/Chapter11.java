package com.xprotec.reactive.reactive;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class Chapter11 {

    public void programWidthRange() {
        Flux.just(1, 2, 3, 4)
                .map(i -> (i * 2))
                .zipWith(Flux.range(0, 4), (source, dest) -> {
                    return dest + " " + source.toString();
                }).subscribe(log::info);

    }

    public void exampleDelay() {
        Flux<Integer> range = Flux.range(1, 12)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> {
                    log.info(i.toString());
                });

        range.subscribe();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //.subscribe();
    }

    public void intervalTimes() {
        Flux<Integer> range = Flux.range(1, 12);

        Flux<Long> delay = Flux.interval(Duration.ofSeconds(1));

        range.zipWith(delay, (ra, del) -> ra)
                .doOnNext(i -> {
                    log.info(i.toString());
                }).blockLast();
        //.subscribe();
    }

    public void exampleIntervalInfinite() {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Flux.interval(Duration.ofSeconds(1))
                .doOnTerminate(countDownLatch::countDown)
                .flatMap(i -> {
                    if (i >= 5) {
                        return Flux.error(new InterruptedException("The five number"));
                    }
                    return Flux.just(i);
                })
                .map(c -> "hola" + c.toString())
                .retry(2)
                //.doOnNext(log::info)
                .subscribe(log::info, e -> log.error(e.getMessage()));

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void exampleIntervalCreate() {
        Flux.create(emitter -> {
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        private Integer counter = 0;

                        @Override
                        public void run() {
                            emitter.next(++counter);
                            if (counter == 10) {
                                timer.cancel();
                                emitter.complete();
                            }
                        }
                    }, 1000, 1000);
                }).doOnNext(next -> log.info((next.toString())))
                .doOnComplete(() -> log.info("Completed"))
                .subscribe();
    }

    //Contra presion

    public void exampleContraPressure() {
        Flux.range(1, 10)
                .log()
                .limitRate(5)
                .subscribe();
                /*.subscribe(new Subscriber<Integer>() {
                    private Subscription subscription;
                    private Integer limit = 2;
                    private Integer consumed = 0;

                    @Override
                    public void onSubscribe(Subscription subscription) {
                        this.subscription = subscription;
                        //subscription.request(Long.MAX_VALUE);
                        subscription.request(limit);

                    }

                    @Override
                    public void onNext(Integer integer) {
                        log.info(integer.toString());
                        consumed++;
                        if (consumed.equals(limit)) {
                            consumed = 0;
                            subscription.request(limit);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
        //.subscribe(c -> log.info(c.toString()));
    }
}
