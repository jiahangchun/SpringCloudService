package com.jiahangchun.controller;

import com.jiahangchun.DO.BookDO;
import com.jiahangchun.repo.BookRepo;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
public class ElasticController {

    @Autowired
    private BookRepo bookRepo;

    @GetMapping("/test/elastic/save")
    public void testSave(){
        List<BookDO> bookDOS=new ArrayList<>();
        for(int i=0;i<10000000;i++){
            BookDO bookDO=new BookDO();
            bookDO.setBookName(i+"aaaaa");
            bookDO.setId(i+"");
            bookDO.setImgSrc("ssssssssssssssssssssssssssss");
            bookRepo.save(bookDO);
            System.out.println("add book");
        }
         return ;
    }


    @GetMapping("/test/elastic")
    public void test(){
        Pageable pageable = new PageRequest(0, 10);
        Iterable<BookDO> bookDOS= bookRepo.findAll();
        bookDOS.forEach((book)->{
            System.out.println(book.getBookName());
        });
        return ;
    }

    @GetMapping("/test/elastic2")
    public void test2() throws UnknownHostException {
        Settings settings= Settings.builder().build();
        TransportClient transportClient=TransportClient.builder().build();
        transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"),9200));

        SearchRequestBuilder searchRequestBuilder=transportClient.prepareSearch("link").setTypes("_doc");

        SearchResponse searchResponse=searchRequestBuilder.execute().actionGet();
        SearchHit[] hits=searchResponse.getHits().getHits();
        if(null!=hits){
            for(SearchHit hit:hits){
                Map<String,Object> aaa=hit.getSource();
                System.out.println("aaaaaaaaaaa");
            }
        }

        return ;
    }

}
