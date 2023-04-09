# tile38-client

Add this dependency to your pom:

        <dependency>
            <groupId>tile38-cli</groupId>
            <artifactId>com.mapabc</artifactId>
            <version>1.0.1</version>
        </dependency>
        
接下来就可以在工程中使用tile38了。
下面以 springboot 为例的配置类代码:


import com.mapabc.api.Tile38Template;

import com.mapabc.api.Tile38TemplateImpl;

import com.mapabc.client.Tile38Client;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

@Configuration

public class Tile38ClientConfig {

    @Value("${tile38.host}")
    private String host;
    
    @Value("${tile38.port}")
    private Integer port;
    
    @Bean
    public Tile38Template getTile38Template(){
        Tile38Client instance = new Tile38Client(host, port, null);
        return new Tile38TemplateImpl(instance);
    }
}

使用示例：
首先在类中注入Tile38Template类，然后就可以在方法中直接使用了：

@Service

public class TestServiceImpl implements TestService{

    @Autowired
    Tile38Template tile38Template;
    public void test(){
        String data = tile38Template.getKeys("OD-*");
        JSONArray keys = JSONObject.parseObject(data).getJSONArray("keys");
        .....
    }
}
    
    
