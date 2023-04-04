package com.mapabc.commands;

import com.alibaba.fastjson.JSONObject;
import io.lettuce.core.dynamic.Commands;
import io.lettuce.core.dynamic.annotation.Command;

/**
 * tile38 命令
 *
 * @Author ke.han
 * @Date 2019/11/5 15:40
 **/
public interface Tile38Commands extends Commands {

    /******************************************************
     **                      服务设置                     **
     ******************************************************/

    /**
     * 认证登录
     *
     * @param password
     * @return
     */
    @Command("AUTH ?0")
    String auth(String password);

    @Command("FLUSHDB")
    void flushDB();

    /**
     * 强制垃圾回收
     */
    @Command("GC")
    void gc();

    /**
     * 获取或设置当前连接的输出格式。
     * 两种选项是 RESP 或 JSON 。
     *
     * @param output
     */
    @Command("OUTPUT ?0")
    String outPut(String output);

    /**
     * FOLLOW命令指示Tile38服务器跟随领导者并复制领导者的数据。
     *
     * @param ip
     * @param port
     */
    @Command("FOLLOW ?0 ?1")
    String follow(String ip, int port);

    /**
     * 关闭当前连接
     */
    @Command("QUIT")
    void quit();

    /**
     * 在后台缩小aof
     *
     * @return
     */
    @Command("AOFSHRINK")
    void aofShrink();


    /******************************************************
     **                      查询                         **
     ******************************************************/

    /**
     * OBJECT 相交   返回包含在区域内或与区域相交的对象  区域为多边形
     *
     * @param key
     * @param geojson
     * @return
     */
    @Command("INTERSECTS ?0 OBJECT ?1")
    String intersects(String key, String geojson);

    /**
     * BOUNDS 相交   返回包含在区域内或与区域相交的对象  区域为矩形
     *
     * @param key
     * @param lng1
     * @param lat1
     * @param lng2
     * @param lat2
     * @return
     */
    @Command("INTERSECTS ?0 BOUNDS ?1 ?2 ?3 ?4")
    String intersects(String key, double lng1, double lat1, double lng2, double lat2);

    /**
     * 附近
     *
     * @param key
     * @param lng
     * @param lat
     * @return
     */
    @Command("NEARBY ?0 POINT ?1 ?2 ?3")
    String nearBy(String key, double lng, double lat, int length);

    /**
     * Nearby data with distance
     *
     * @param key
     * @param lng
     * @param lat
     * @return
     */
    @Command("NEARBY ?0 DISTANCE POINT ?1 ?2 ?3 ")
    String nearByWithDistance(String key, double lng, double lat, int length);

    /**
     * 附近 - 查找最接近给定点的对象。
     *
     * @param key
     * @param limit
     * @param lng
     * @param lat
     * @return
     */
    @Command("NEARBY ?0 LIMIT ?1 POINT ?2 ?3")
    String nearByLimit(String key, int limit, double lng, double lat);

    /**
     * 返回所有匹配的 集合（key）
     *
     * @param key *返回所有 查询方式  key*  *key *key*
     * @return
     */
    @Command("KEYS ?0")
    String getKeys(String key);

    /**
     * 对 key 集合中所有对象的扫描
     *
     * @param key
     * @return
     */
    @Command("SCAN ?0")
    String scanObjInKey(String key);


    /**
     * 根据时间段查询集合中所有对象
     *
     * @param key
     * @param startTime
     * @param endTime
     * @param limit
     * @param orderBy
     * @return
     */
    @Command("SCAN ?0 WHERE z ?1 ?2 LIMIT ?3 ?4")
    String scanObjInKeyByTime(String key, long startTime, long endTime, int limit, String orderBy);

    /**
     * 返回 矩形内 完全包含的所有对象的列表
     *
     * @param key
     * @param lng1
     * @param lat1
     * @param lng2
     * @param lat2
     * @return
     */
    @Command("WITHIN ?0 BOUNDS ?1 ?2 ?3 ?4")
    String withIn(String key, double lng1, double lat1, double lng2, double lat2);

    /**
     * 返回 多边形内 完全包含的所有对象的列表
     *
     * @param key
     * @param geojson
     * @return
     */
    @Command("WITHIN ?0 OBJECT ?1")
    String withIn(String key, String geojson);

    /**
     * 返回 多边形内 完全包含的所有对象的列表
     * WITHIN poi WHERE z 5 11 GET cross lukou1  //  根据cross组的lukou1对象查询poi里全部的在lukou1里的对象
     *
     * @param key
     * @param limit
     * @param startTime
     * @param endTime
     * @param key1
     * @param id
     * @return
     */
    @Command("WITHIN ?0 LIMIT ?1 WHERE z ?2 ?3 GET ?4 ?5")
    String withIn(String key, int limit, long startTime, long endTime, String key1, String id);

    /**
     * 返回 扇形区域内 完全包含的所有对象的列表
     * 一个扇形多边形特征，跨越两个给定方位角、一个中心点和一个半径。0 度方位描述地理上的北方。
     * 返回队列集合中的所有对象，它们与给定半径中的原点成 0 度到 90 度角（以米为单位）。
     * @param key
     * @param lng
     * @param lat
     * @param r
     * @param startAngle
     * @param endAngle
     * @return
     */
    @Command("WITHIN ?0 SECTOR ?1 ?2 ?3 ?4 ?5")
    String withIn(String key, double lng, double lat, int r, int startAngle, int endAngle);

    /**
     * 返回集合(key)中所有对象的最小边界矩形
     *
     * @param key
     * @return
     */
    @Command("BOUNDS ?0")
    String getMinBounds(String key);

    /**
     * 返回一个或多个集合(key)的统计信息  多个 集合（key）之间用空格分割
     *
     * @param keys
     * @return
     */
    @Command("STATS ?0")
    String statsKeys(String keys);

    /**
     * 根据集合（key） 查询点
     *
     * @param key
     * @param id
     * @return
     */
    @Command("GET ?0 ?1")
    String getElement(String key, String id);


    /******************************************************
     **                      SET                         **
     ******************************************************/

    /**
     * 创建一个点在 key 集合
     *
     * @param key
     * @param id
     * @param lng
     * @param lat
     * @return
     */
    @Command("SET ?0 ?1 POINT ?2 ?3")
    String setElement(String key, String id, double lng, double lat);

    /**
     * 创建一个 矩形 在 key 集合
     *
     * @param key
     * @param id
     * @param lng1
     * @param lat1
     * @param lng2
     * @param lat2
     * @return
     */
    @Command("SET ?0 ?1 BOUNDS ?2 ?3 ?4 ?5")
    String setElement(String key, String id, double lng1, double lat1, double lng2, double lat2);

    /**
     * 创建一个 不规则多边形/线 在 key 集合
     *
     * @param key
     * @param id
     * @param geojson
     * @return
     */
    @Command("SET ?0 ?1 OBJECT ?2")
    String setElement(String key, String id, String geojson);

    /**
     * 从指定键中删除所有对象。不会删除键
     *
     * @param key
     * @return
     */
    @Command("DROP ?0")
    String dropObjInKey(String key);

    /**
     * 删除与指定模式匹配的对象。
     * PDEL fleet truck*
     * 这将从fleet集合中删除ID开头为的所有对象truck。
     *
     * @param key
     * @param id
     * @return
     */
    @Command("PDEL ?0 ?1")
    String pDelObjInKey(String key, String id);

    /**
     * 将集合重命名key为newkey。如果newkey已经存在，将在重命名之前将其删除。
     *
     * @param key
     * @param newKey
     * @return
     */
    @Command("RENAME ?0 ?1")
    String replaceKey(String key, String newKey);

    /**
     * 将集合更新名key为newkey，如果尚不存在。如果 newkey已经存在，则此命令不执行任何操作
     *
     * @param key
     * @param newKey
     * @return
     */
    @Command("RENAMENX ?0 ?1")
    String updateKey(String key, String newKey);

    /**
     * 在ID上设置超时。
     *
     * @param key
     * @param id
     * @param time 秒
     * @return
     */
    @Command("EXPIRE ?0 ?1 ?2")
    String setExpireIdInKey(String key, String id, int time);

    /**
     * 删除ID的现有超时设置
     *
     * @param key
     * @param id
     * @return
     */
    @Command("PERSIST ?0 ?1")
    String delExpireIdInKey(String key, String id);

    /**
     * 获取ID超时。
     *
     * @param key
     * @param id
     * @return
     */
    @Command("TTL ?0 ?1")
    String getExpireIdInKey(String key, String id);

    @Command("JSET ?0")
    String jSet(String gJson);

    @Command("JGET ?0")
    String jGet(String gJson);

    @Command("JDEL ?0")
    String jDel(String gJson);


    /******************************************************
     **                      频道                         **
     ******************************************************/

    @Command("SETCHAN ?0 WITHIN ?1 FENCE DETECT ?2 OBJECT ?3")
    String setChanWithIn(String chanName, String key, String detect, String geoJson);

    /**
     * 创建一个普通频道
     *
     * @param chanName
     * @param key
     * @param detect
     * @param Lng
     * @param Lat
     * @param length
     */
    @Command("SETCHAN ?0 NEARBY ?1 FENCE DETECT ?2 POINT ?3 ?4 ?5")
    String setChan(String chanName, String key, String detect, double Lng, double Lat, int length);


    /**
     * 创建一个漫游频道
     *
     * @param chanName
     * @param key
     * @param key1
     * @param mark
     * @param length
     * @return
     */
    @Command("SETCHAN ?0 NEARBY ?1 FENCE ROAM ?2 ?3 ?4")
    String setChanRoam(String chanName, String key, String key1, String mark, int length);


    /**
     * 查询通道
     *
     * @param chan
     * @return
     */
    @Command("CHANS ?0")
    String getChans(String chan);

    /**
     * 删除通道
     *
     * @param chan
     * @return
     */
    @Command("DELCHAN ?0")
    String delChan(String chan);


    /**
     * 批量删除通道
     *
     * @param chans
     * @return
     */
    @Command("PDELCHAN ?0")
    String pDelChans(String chans);


    /******************************************************
     **                      WEBHOOK                     **
     ******************************************************/

    /**
     * 设置一个钩子 WEBHOOK
     *
     * @param webHook
     * @param addr
     * @param key
     * @param Lon
     * @param Lat
     */
    @Command("SETHOOK ?0 ?1 NEARBY ?2 FENCE DETECT ?3 POINT ?4 ?5 ?6")
    String setHook(String webHook, String addr, String key, String detect, double Lon, double Lat, int length);

    /**
     * SETHOOK myhook http://10.0.1.5/hook NEARBY people FENCE ROAM people * 5000
     *
     * @param webHook
     * @param addr
     * @param key
     * @param key1
     * @param length
     * @return
     */
    @Command("SETHOOK ?0 ?1 NEARBY ?2 FENCE ROAM ?3 * ?4")
    String setHook(String webHook, String addr, String key, String key1, int length);

    /**
     * SETHOOK roadId kafka://000.000.00.000:9092/jinXing WITHIN Point GET jinXingKey jinXingId
     *
     * @param webHook
     * @param addr
     * @param pointKey
     * @param key
     * @param id
     * @return
     */
    @Command("SETHOOK ?0 ?1 WITHIN ?2 GET ?3 ?4")
    String setHook(String webHook, String addr, String pointKey, String key, String id);

    /**
     *
     * @param webHook
     * @param addr
     * @param pointKey
     * @param detect
     * @param key
     * @param id
     * @return
     */
    @Command("SETHOOK ?0 ?1 WITHIN ?2 FENCE DETECT ?3 GET ?4 ?5")
    String setHook(String webHook, String addr, String pointKey, String detect, String key, String id);

    /**
     * 查询钩子
     *
     * @param hookName
     * @return
     */
    @Command("HOOKS ?0")
    String getHook(String hookName);

    /**
     * 根据名称删除钩子
     *
     * @param hookName
     * @return
     */
    @Command("DELHOOK ?0")
    String delHook(String hookName);

    /**
     * 批量删除钩子
     *
     * @param hookName
     * @return
     */
    @Command("PDELHOOK ?0")
    String pDelHook(String hookName);


}
