# tile38-client
针对 tile38 地理空间数据库封装的的连接客户端

运行工程，将代码打成jar包后，把jar包放在D盘根目录。

使用mvn把jar包放入本地maven仓库命令：
mvn install:install-file -Dfile=D:\tile38-client-1.0.0.jar -DgroupId=com.mapabc.tile38-client -DartifactId=tile38-client -Dversion=1.0.0 -Dpackaging=jar

在项目工程中引入jar包依赖pom:

        <!-- tiel38 链接依赖（生菜客户端） -->
        <dependency>
            <groupId>io.lettuce</groupId>
            <artifactId>lettuce-core</artifactId>
            <version>5.2.1.RELEASE</version>
        </dependency>
        <!-- mapabc-tiel38 客户端 -->
        <dependency>
            <groupId>com.mapabc.tile38-client</groupId>
            <artifactId>tile38-client</artifactId>
            <version>1.0.0</version>
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
        Tile38Client instance = Tile38Client.getInstance(host, port, null);
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
    
    
