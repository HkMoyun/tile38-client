
import com.mapabc.api.Tile38Template;
import com.mapabc.client.Tile38Client;
import com.mapabc.commands.Tile38Commands;
import io.lettuce.core.pubsub.RedisPubSubListener;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;

/**
 * @Author ke.han
 * @Date 2019/11/14 16:36
 **/
public class TestRun {

    public static void main(String args[]){
        Tile38Client tile38Client = new Tile38Client();
//        Tile38Client tile38Client = new Tile38Client("192.168.11.102", 9851,"");
        Tile38Template template = tile38Client.getTemplate();

        String keys = template.getKeys("*");
        System.out.println(keys);

        String COMPUTE_POINT = template.getHook("*");
        System.out.println(COMPUTE_POINT);

        //test for setting data with fields
        template.setElementWithField("fleet" , "id" , "{\"type\":\"Polygon\",\"coordinates\":[[[-111.9787,33.4411],[-111.8902,33.4377],[-111.8950,33.2892],[-111.9739,33.2932],[-111.9787,33.4411]]]}" , "timestamp" , "12334555");

    }



}
