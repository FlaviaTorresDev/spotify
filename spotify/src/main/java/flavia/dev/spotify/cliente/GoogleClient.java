package flavia.dev.spotify.cliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "GoogleClient",
        url = "https://google.com"
)
public interface GoogleClient {

    @GetMapping
    String helloWorld();
}


