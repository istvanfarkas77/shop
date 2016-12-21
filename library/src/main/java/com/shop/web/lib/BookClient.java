package com.shop.web.lib;

import com.shop.BookResource;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("book-service")
public interface BookClient extends BookResource {
}
