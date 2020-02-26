package com.mapabc.client;

import com.mapabc.commands.Tile38Commands;
import com.mapabc.eunms.OutPutType;
import com.mapabc.util.StringUtil;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.codec.ByteArrayCodec;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.dynamic.RedisCommandFactory;
import io.lettuce.core.protocol.LettuceCharsets;

import java.util.Arrays;

/**
 * 客户端连接器
 * @Author ke.han
 * @Date 2019/11/6 17:39
 **/
public class Tile38Client {

    private volatile static Tile38Client tile38Client = null;

    private volatile static Tile38Commands commands = null;

    private volatile static RedisClient client = null;

    public static Tile38Client getInstance(String host,int port,String passWord){
        if(tile38Client == null){
            synchronized (Tile38Client.class) {
                if(tile38Client == null){
                    tile38Client = new Tile38Client();
                    client = tile38Client.createRedisClient(host,port);
                    commands = tile38Client.createCommands(client,passWord);
                }
            }
        }
        return tile38Client;
    }

    public Tile38Commands getCommands(){
        return commands;
    }

    public RedisClient getRedisClient(){
        return client;
    }

    private Tile38Client(){}

    private Tile38Commands createCommands(RedisClient client,String passWord){
        StatefulRedisConnection<String, String> connect = client.connect();
        RedisCommandFactory factory = new RedisCommandFactory(connect, Arrays.asList(new ByteArrayCodec(), new StringCodec(LettuceCharsets.UTF8)));
        Tile38Commands commands = factory.getCommands(Tile38Commands.class);
        if(!StringUtil.isBlack(passWord)){//若有密码则先登录
            commands.auth(passWord);
        }
        //默认输出JSON格式数据
        commands.output(OutPutType.JSON.getType());
        return commands;
    }

    private RedisClient createRedisClient(String host,int port){
        return RedisClient.create(RedisURI.create(host,port));
    }

}
