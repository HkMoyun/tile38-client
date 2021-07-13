import com.alibaba.fastjson.JSONObject;
import com.mapabc.api.Tile38Template;
import com.mapabc.client.Tile38Client;
import com.mapabc.commands.Tile38Commands;
import com.mapabc.entity.Element;
import com.mapabc.entity.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * 2021/7/13 17:31
 *
 * @auther ke.han
 */
public class TestHookWithIn {

    public static void main(String[] args) throws InterruptedException {

        Tile38Client tile38Client = new Tile38Client();
        Tile38Template template = tile38Client.getTemplate();

        //创建电子围栏集合
        //SET jinXing yeqing OBJECT {"coordinates":[[[[116.465935,40.017757],[116.466064,40.00668],[116.480269,40.006779],[116.479926,40.018841],[116.465978,40.01871],[116.465978,40.01871],[116.465806,40.018513],[116.465935,40.017757]]]],"key":"yeqing","type":"MultiPolygon"}
        String s = template.setElement("area", "beijing", getArea());
        System.out.println(s);

        //创建WITHIN地理空间计算数据推送kafka
        String s2 = template.setHook("beijing", "kafka://192.168.11.151:9092/topicName", "Point", "area", "beijing");
        System.err.println(s2);

        String hook = template.getHook("*");
        System.out.println(hook);

        String keys = template.getKeys("*");
        System.out.println(keys);
        long l = System.currentTimeMillis();
        //创建gps点集合 并更新数据
        for (int i = 0; i < 10; i++) {
            double x = (i + 1.0) / 10000;
            String s1 = template.setElement("Point", "p1", new Point(116.471214 + x, 40.012925 + x));
//            Thread.sleep(1000);
            System.out.println(i + " " + s1);
        }
        long e = System.currentTimeMillis();
        System.out.println(" 耗时: " + (e - l) + " ms");
        tile38Client.close();
    }

    private static String getArea() {
        String str = "116.465935 40.017757,116.466064 40.00668,116.480269 40.006779,116.479926 40.018841,116.465978 40.01871,116.465978 40.01871,116.465806 40.018513,116.465935 40.017757";
        String[] split = str.split(",");
        List<List<List<List<Double>>>> all = new ArrayList<>();
        List<List<List<Double>>> coordinatesAll = new ArrayList<>();
        List<List<Double>> coordinates = new ArrayList<>();
        for (String ss : split) {
            List<Double> listFirst = new ArrayList<>();
            String[] ssArr = ss.split(" ");
            double x = Double.parseDouble(ssArr[0]);
            double y = Double.parseDouble(ssArr[1]);
            listFirst.add(x);
            listFirst.add(y);
            coordinates.add(listFirst);
        }
        coordinatesAll.add(coordinates);
        all.add(coordinatesAll);
        JSONObject t = new JSONObject();
        t.put("key", "yeqing");
        t.put("type", "MultiPolygon");
        t.put("coordinates", all);
        System.out.println(JSONObject.toJSONString(t));
        return JSONObject.toJSONString(t);
    }


}
