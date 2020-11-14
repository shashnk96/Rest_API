package com.sol;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.model.Model;
import com.model.response.Relation;
import com.model.response.Response;
import com.model.response.Term;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class Controller {

    public static final String CMLIMIT = "&cmlimit=";
    public static final String UTF_8 = "UTF-8";
    @Value("${url1}")
    private String url1;

    @Value("${url2}")
    private String url2;

    @Value("${number_of_threads}")
    private Integer numThread;


    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping(value = "/problem1")
    public Response getResultPrb1(@RequestParam String inputStr, @RequestParam Integer pageId) throws UnirestException, JsonProcessingException, UnsupportedEncodingException {
        if (inputStr == null || inputStr.trim().isEmpty() || pageId == null) {
            return badRequestSetParams();
        }
        List<Term> terms = new ArrayList<>();
        List<Relation> relations = new ArrayList<>();
        responseNodesSet(inputStr, terms, relations, pageId, null);
        return new Response(terms, relations);
    }

    private void responseNodesSet(String inputStr, List<Term> terms, List<Relation> relations, Integer pageId, Integer cmlimit) throws UnirestException, JsonProcessingException, UnsupportedEncodingException {
        String firstCall = MessageFormat.format(url1,  URLEncoder.encode(inputStr, UTF_8));
        String secondCall = MessageFormat.format(url2,  URLEncoder.encode(inputStr, UTF_8));
        if(cmlimit!=null){
            firstCall+=  CMLIMIT +cmlimit;
            secondCall+= CMLIMIT +cmlimit;
        }
        HttpResponse<String> response1 = Unirest
                .get(firstCall)
                .asString();
        HttpResponse<String> response2 = Unirest
                .get(secondCall)
                .asString();
        Model model1 = objectMapper.readValue(response1.getBody(), Model.class);
        Model model2 = objectMapper.readValue(response2.getBody(), Model.class);

        Term t1 = new Term(pageId, inputStr);
        terms.add(t1);
        model1.getQuery().getCategorymembers().forEach(c -> {
            terms.add(new Term(c.getPageid(), c.getTitle()));
            relations.add(new Relation(pageId, "subcat", c.getPageid().toString()));
        });
        model2.getQuery().getCategorymembers().forEach(c -> {
            terms.add(new Term(c.getPageid(), c.getTitle()));
            relations.add(new Relation(pageId, "related", c.getPageid().toString()));
        });
    }

    private Response badRequestSetParams() {
        Response badRes = new Response();
        TimeZone tz = TimeZone.getTimeZone("IST");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());
        badRes.setTimestamp(nowAsISO);
        badRes.setError("Bad Request");
        badRes.setStatus(400);
        badRes.setMessage("Mandatory parameter(s) missing");
        return badRes;
    }

    @GetMapping(value = "/problem2")
    public Response getResultPrb2(@RequestParam String inputStr, @RequestParam Integer cmlimit) throws UnirestException, JsonProcessingException, UnsupportedEncodingException, InterruptedException {
        if(inputStr==null || inputStr.trim().isEmpty() || cmlimit==null){
            return badRequestSetParams();
        }
        String firstCall = MessageFormat.format(url1, URLEncoder.encode(inputStr, UTF_8));
        firstCall+= CMLIMIT +cmlimit;
        HttpResponse<String> response1 = Unirest
                .get(firstCall)
                .asString();
        Model model1 = objectMapper.readValue(response1.getBody(), Model.class);
        List<Term> terms = new ArrayList<>();
        List<Relation> relations = new ArrayList<>();
        // create a pool of threads, numThread jobs will execute in parallel
        ExecutorService threadPool = Executors.newFixedThreadPool(numThread);
        model1.getQuery().getCategorymembers().forEach(c-> {
            threadPool.submit(() -> {
                try {
                    responseNodesSet(c.getTitle(), terms, relations, c.getPageid(),cmlimit);
                } catch (UnirestException | JsonProcessingException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            });
        });
        threadPool.shutdown();
        threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);

        return new Response(terms,relations);
    }
}
