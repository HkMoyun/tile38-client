package com.mapabc.client;

import com.mapabc.api.Tile38Template;
import com.mapabc.api.Tile38TemplateImpl;
import com.mapabc.commands.Tile38Commands;
import com.mapabc.eunms.OutPutType;
import com.mapabc.util.StringUtil;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.codec.ByteArrayCodec;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.dynamic.RedisCommandFactory;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 客户端连接器
 *
 * @Author ke.han
 * @Date 2019/11/6 17:39
 **/
public class Tile38Client implements Serializable {

    private Tile38Commands commands = null;

    private RedisClient client = null;

    private StatefulRedisConnection<String, String> connect = null;
    public Tile38Client() {
        client = this.createRedisClient("127.0.0.1", 9851);
        commands = this.createCommands(client, "");
    }

    public Tile38Client(String host, int port, String passWord) {
        if (passWord != null){
            client = this.createRedisClient(host, port,passWord);
        } else {
            client = this.createRedisClient(host, port);
        }
        commands = this.createCommands(client, passWord);
    }

    public Tile38Client(String sentinelHost, int sentinelPort, String password , String masterId) {
        client = this.createRedisClient(sentinelHost , sentinelPort , password , masterId);
        commands = this.createCommands(client, password);
    }

    public void close() {
        if (connect != null) {
            try {
                connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            connect = null;
        }
        commands = null;
    }

    public Tile38Template getTemplate() {
        return new Tile38TemplateImpl(this);
    }

    public Tile38Commands getCommands() {
        return commands;
    }

    public RedisClient getRedisClient() {
        return client;
    }

    private Tile38Commands createCommands(RedisClient client, String passWord) {
        this.connect = client.connect();
        RedisCommandFactory factory = new RedisCommandFactory(this.connect, Arrays.asList(new ByteArrayCodec(), new StringCodec(StandardCharsets.UTF_8)));
        Tile38Commands commands = factory.getCommands(Tile38Commands.class);
        //若有密码则先登录
        if (!StringUtil.isBlack(passWord)) {
            commands.auth(passWord);
        }
        //默认输出JSON格式数据
        commands.outPut(OutPutType.JSON.getType());
        return commands;
    }

    private RedisClient createRedisClient(String host, int port) {
        return RedisClient.create(RedisURI.create(host, port));
    }
    
    private RedisClient createRedisClient(String host, int port, String passWord) {
        RedisURI uri = RedisURI.Builder.redis(host, port).withPassword(passWord.toCharArray()).build();
        RedisClient redisClient = RedisClient.create(uri);
        redisClient.setOptions(ClientOptions.builder().protocolVersion(ProtocolVersion.RESP2).build());
        return redisClient;
    }
    
    private RedisClient createRedisClient(String sentinelHost, int sentinelPort, String password , String masterId) {
        RedisURI redisURI = RedisURI.Builder.sentinel(sentinelHost, sentinelPort, masterId, password).withDatabase(0).build();
        return RedisClient.create(redisURI);
    }

}
