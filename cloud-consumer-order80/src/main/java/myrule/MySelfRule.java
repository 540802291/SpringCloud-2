package myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySelfRule {
    //创建一个新的包，写负载算法

    @Bean
    public IRule myRule(){
        return new RandomRule();  //定义为随机
    }
}
