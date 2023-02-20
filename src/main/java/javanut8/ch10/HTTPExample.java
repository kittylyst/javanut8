package javanut8.ch10;

// https://developer.worldweatheronline.com/api/historical-weather-api.aspx


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.net.http.HttpResponse.BodyHandlers.ofString;

public final class HTTPExample {

    private final static Pattern IGNORE_COMMENTS = Pattern.compile("^#");

    public final String URL = "http://api.worldweatheronline.com/premium/v1/past-weather.ashx?q=53.236410,-9.725218&date=2018-01-01&enddate=2018-01-31&tp=24&format=csv&key=54a4f43fc39c435fa2c143536183004";

    public static void main(String[] args) throws Exception {
        HTTPExample m = new HTTPExample();
        m.run();
    }

    public void run2() throws Exception {
        var client = HttpClient.newBuilder().build();
        var uri = new URI("https://www.oreilly.com");
        var request = HttpRequest.newBuilder(uri).build();

        var response = client.send(request,
                ofString(Charset.defaultCharset()));
        var body = response.body();
        System.out.println(body);
    }

    public void run() throws Exception {
        HttpClient hc = HttpClient.newBuilder().build();
        HttpRequest req = HttpRequest.newBuilder(new URI(URL)).build();

        var response = hc.send(req, HttpResponse.BodyHandlers.ofString(Charset.defaultCharset()));
        var body = response.body();
        Stream<String> ss = Stream.of(body.split("\n"));
        final AtomicInteger ai = new AtomicInteger(0);
        double ave = ss.filter(IGNORE_COMMENTS.asPredicate().negate())
                .filter(s -> ai.getAndIncrement() % 2 > 0)
                .mapToInt(t -> {
                    String[] as = t.split(",");
                    return Integer.parseInt(as[1]);
                })
                .average()
                .getAsDouble();

//        System.out.println(body);
        System.out.println(ave);
    }

}
