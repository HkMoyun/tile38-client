import com.mapabc.client.Tile38Client;
import com.mapabc.commands.Tile38Commands;
import io.lettuce.core.pubsub.RedisPubSubListener;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;

/**
 * @Author ke.han
 * @Date 2020/3/21 14:37
 **/
public class TestTile38 {

    public void aaa(){

        Tile38Client tile38Client = new Tile38Client("192.168.11.102", 9851,"");
        Tile38Commands commands = tile38Client.getCommands();

        String hookName = "COMPUTE_HOOK";
        String key = "COMPUTE_POINT";

        commands.setChanRoam(hookName,key,key,"*",5);

        //订阅
        StatefulRedisPubSubConnection<String, String> connection = tile38Client.getRedisClient().connectPubSub();
        connection.addListener(new RedisPubSubListener<String,String>(){
            @Override
            public void message(String channel, String message) {
                System.out.println(message);
            }
            @Override
            public void message(String pattern, String channel, String message) {}
            @Override
            public void subscribed(String channel, long count) {
                System.out.println("开启订阅  通道名称："+  channel+"  数量： "+ count);
            }
            @Override
            public void psubscribed(String pattern, long count) {}
            @Override
            public void unsubscribed(String channel, long count) {
                System.out.println("关闭订阅  通道名称："+  channel+"  数量： "+ count);
            }
            @Override
            public void punsubscribed(String pattern, long count) {}
        });

        RedisPubSubCommands<String, String> sync = connection.sync();
        //同步订阅
        sync.subscribe(hookName);

    }


}
