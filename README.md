# tile38-client

First
mvn install:install-file -Dfile=tile38-cli-2.0.1.jar -DgroupId=com.mapabc.tile38-cli -DartifactId=tile38-cli -Dversion=2.0.1 -Dpackaging=jar

Add this dependency to your pom:

        <dependency>
            <groupId>com.mapabc</groupId>
            <artifactId>tile38-cli</artifactId>
            <version>2.0.1</version>
        </dependency>
        


Configuration


    @Value("${tile38.host}")
    private String host;
    
    @Value("${tile38.port}")
    private Integer port;
    
    @Bean
    public Tile38Template getTile38Template(){
        Tile38Client instance = new Tile38Client(host, port, null);
        return new Tile38TemplateImpl(instance);
    }


Service


    @Autowired
    Tile38Template tile38Template;
    public void test(){
        String data = tile38Template.getKeys("OD-*");
        JSONArray keys = JSONObject.parseObject(data).getJSONArray("keys");
        .....
    }

    
    
