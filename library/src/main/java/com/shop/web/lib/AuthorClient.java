package com.shop.web.lib;

import com.shop.AuthorResource;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("author-service")
public interface AuthorClient extends AuthorResource {

}
