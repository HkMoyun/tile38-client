import com.mapabc.api.Tile38Template;
import com.mapabc.client.Tile38Client;
import com.mapabc.commands.BatchedCommandType;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.protocol.CommandArgs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestBatchedCommandsProcessing {

    static Tile38Client tile38Client = new Tile38Client();
    static Tile38Template template = tile38Client.getTemplate();

    public static void main(String args[]){

        /*
        //code for scheduling a timer which generates random 5000 commands which look up to redis every second
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        final CountDownLatch countDownLatch = new CountDownLatch(5000);
        executorService.scheduleAtFixedRate(TestBatchedCommandsProcessing::testBatchedCommands, 0, 1, TimeUnit.SECONDS);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        */
        testBatchedCommands();
    }



    public static void testBatchedCommands(){
        Random random = new Random();
        List<CommandArgs<String, String>> commandArgsList = new ArrayList<>();
        for (int i = 1; i <= 5000; i++) {
            double lat = 8.0 + (37.0 - (8.0)) * random.nextDouble();
            double lng = 68.0 + (98.0 - (68.0)) * random.nextDouble();
            commandArgsList.add(new CommandArgs<>(StringCodec.UTF8)
                    .add("fleet")
                    .add("POINT")
                    .add(lng)
                    .add(lat)
                    .add(1000));
        }
        long start1 = System.currentTimeMillis();
        List<List<Object>> object1 = template.executeParallelBatchedCommands(commandArgsList , BatchedCommandType.NEARBY , 5);
//        List<List<Object>> object2 = template.executeBatchedCommands(commandArgsList , BatchedCommandType.NEARBY);
        long end1 = System.currentTimeMillis();
        System.out.println("Elapsed Time in milli seconds: "+ (end1-start1));
    }

}
