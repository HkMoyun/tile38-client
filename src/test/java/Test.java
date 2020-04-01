
import com.mapabc.client.Tile38Client;
import com.mapabc.commands.Tile38Commands;
import io.lettuce.core.pubsub.RedisPubSubListener;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;

/**
 * @Author ke.han
 * @Date 2019/11/14 16:36
 **/
public class Test {

    public static void main(String args[]){
        Tile38Client tile38Client = new Tile38Client("127.0.0.1", 9851,"");
        Tile38Commands commands = tile38Client.getCommands();

        String keys = commands.getKeys("*");
        System.out.println(keys);

    }



}
