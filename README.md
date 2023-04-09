# tile38-client

Add this dependency to your pom:

        <dependency>
            <groupId>tile38-cli</groupId>
            <artifactId>com.mapabc</artifactId>
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

    
    
