package takbaeyu;

import takbaeyu.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired
    ReviewRepository reviewRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDelivered_ReviewSetPol(@Payload Delivered delivered){

        if(delivered.isMe()){
            Review review = new Review();
            review.setMemberId(delivered.getMemberId());
            review.setRequestId(delivered.getRequestId());
            reviewRepository.save(review);

            System.out.println("##### listener ReviewSetPol : " + delivered.toJson());
        }
    }

}
