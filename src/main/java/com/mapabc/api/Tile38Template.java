package com.mapabc.api;

import com.mapabc.commands.Tile38Commands;
import com.mapabc.entity.Element;
import com.mapabc.eunms.DetectType;
import io.lettuce.core.RedisClient;

/**
 * @Author ke.han
 * @Date 2019/11/13 13:59
 **/
public interface Tile38Template {

    public Tile38Commands getCommands();

    public RedisClient getClient();

    public String auth(String password);

    public void flushDB();

    public void gc();

    public String outPut(String output);

    public String follow(String ip, int port);

    public void quit();

    public void aofShrink();

    public String intersects(String key, Element element);

    public String nearBy(String key, double lng, double lat, int length);

    public String nearByLimit(String key, int limit, double lng, double lat);

    public String getKeys(String key);

    public String scanObjInKey(String key);

    public String scanObjInKeyByTime(String key, long startTime, long endTime, int limit, String orderBy);

    public String withIn(String key, Element element);

    public String withIn(String key, int limit, long startTime, long endTime, String key1, String id);

    public String getMinBounds(String key);

    public String statsKeys(String keys);

    public String getElement(String key, String id);

    public String setElement(String key, String id, Element element);

    public String setElement(String key, String id, String geojson);

    public String dropObjInKey(String key);

    public String pDelObjInKey(String key, String id);

    public String replaceKey(String key, String newKey);

    public String updateKey(String key, String newKey);

    public String setExpireIdInKey(String key, String id, int time);

    public String delExpireIdInKey(String key, String id);

    public String getExpireIdInKey(String key, String id);

    public String jSet(String gJson);

    public String jGet(String gJson);

    public String jDel(String gJson);

    public String setChanWithIn(String chanName, String key,String detect,String geoJson);

    public String setChan(String chanName, String key, DetectType detect, double Lng, double Lat, int length);

    public String setChanRoam(String chanName, String key, String mark, int length);

    public String getChans(String chan);

    public String delChan(String chan);

    public String pDelChans(String chans);

    public String setHook(String webHook, String addr, String key, DetectType detect, double Lon, double Lat, int length);

    public String setHook(String webHook, String addr, String pointKey, String key, String id);

    String setHook(String webHook, String addr, String pointKey, String detect, String key, String id);

    public String getHook(String hookName);

    public String delHook(String hookName);

    public String pDelHook(String hookName);

}
