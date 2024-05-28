package demo.streams;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Log4j2
@Configuration
public class MultiStream {

    @Bean
    public Consumer<KStream<String, String>> stream1() {
        return stream -> stream
                .peek((k, v) -> log.info("received event on stream 1"))
                .peek(this::stream1);
    }

    @Bean
    public Consumer<KStream<String, String>> stream2() {
        return stream -> stream
                .peek((k, v) -> log.info("received event on stream 2"))
                .peek(this::stream2);
    }

    public void stream1(String key, String event) {
        log.info("key: {}, event: {}", key, event);
    }

    public void stream2(String key, String event) {
        log.info("key: {}, event: {}", key, event);
    }
}
