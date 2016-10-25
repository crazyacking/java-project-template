package main.java.com.alicloud.zeekeye;

import com.codahale.metrics.*;

import java.util.concurrent.TimeUnit;

import static com.codahale.metrics.MetricRegistry.name;

/**
 * @author Mengyu
 * @date 2016/10/20
 */
public class GetStarted {
    static final MetricRegistry metrics = new MetricRegistry();

    private final Histogram responseSizes = metrics.histogram(name(GetStarted.class, "response-sizes"));

    public static void main(String args[]) {
        startReport();
        Meter requests = metrics.meter("requests");
        requests.mark();
        requests.mark();
        requests.mark();
        requests.mark();
        requests.mark();
//        wait5Seconds();

         Counter totalResolveNum = metrics.counter("TOTAL_RESOLVE_NUM");

        totalResolveNum.inc();
        totalResolveNum.inc();
        totalResolveNum.inc();
        totalResolveNum.inc();
        totalResolveNum.inc();
        totalResolveNum.inc();
        totalResolveNum.inc();

        GetStarted started = new GetStarted();

        started.handleRequest(5);
        started.handleRequest(6);
        started.handleRequest(2);
        started.handleRequest(9);

        System.out.println(started.responseSizes.toString());
    }

    static void startReport() {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);
    }

    static void wait5Seconds() {
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
        }
    }

    public void handleRequest(int size) {
        // etc
        responseSizes.update(size);
    }

}

