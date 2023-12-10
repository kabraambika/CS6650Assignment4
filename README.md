# CS6650Assignment4
Make something magical in distributed system

# How to save into Redis:
- Add Jedis broker/client in mavern
  ```
    <dependency>
      <groupId>redis.clients</groupId>
      <artifactId>jedis</artifactId>
      <version>4.3.1</version>
    </dependency>
  ```
- Setup a JedisPool
  ```
  JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), EC2_URL, 6379);
  ```
- Attach resources in try get and user 'set' to create a key-value cache:
  ```
        try (Jedis jedis = jedisPool.getResource()) {
          jedist.set("key", "value");
        } catch (Exception e) {
            response.setStatus(400);
            response.getWriter().println(e);
        }
  ```
- NOTE: Need to look into how to set TTL.

# How to install Kafka locally
Reference taken from [link of official documentation](https://kafka.apache.org/quickstart)

- STEP 1: GET KAFKA
[Download](https://www.apache.org/dyn/closer.cgi?path=/kafka/3.6.1/kafka_2.13-3.6.1.tgz) the latest Kafka release and extract it:

```shell
$ tar -xzf kafka_2.13-3.6.1.tgz
$ cd kafka_2.13-3.6.1
```

- STEP 2: START THE KAFKA ENVIRONMENT
NOTE: Your local environment must have Java 8+ installed.

Apache Kafka can be started using ZooKeeper or KRaft. To get started with either configuration follow one the sections below but not both.

Kafka with ZooKeeper
Run the following commands in order to start all services in the correct order:

# Start the ZooKeeper service
```shell
$ bin/zookeeper-server-start.sh config/zookeeper.properties
```
Open another terminal session and run:

# Start the Kafka broker service
```shell
$ bin/kafka-server-start.sh config/server.properties
```
Once all services have successfully launched, you will have a basic Kafka environment running and ready to use.


# How to install Kafka in AWS
- Start EC2 linux 2(amzn2-ami-kernel-5.10-hvm-2.0.20231116.0-x86_64-gp2 ami-0c0d141edc4f470cc) with t2.large and ssh
- install java
  ```ssh sudo yum install java-17-amazon-corretto-devel ```
- copy kafka downloaded (upzip folder) in ec2
- give all permission by ```ssh sudo chmod -R a+rwx kafka_2.13-3.6.1/ ```
- navigate to kafka ```ssh cd kafka_2.13-3.6.1/ ```
- run zookeeper ```ssh bin/zookeeper-server-start.sh config/zookeeper.properties ```
- open anther terminal ```ssh  bin/kafka-server-start.sh config/server.properties ```
- upload jar of springboot kafka
- start jar by ```ssh java -jar <jar_name>.jar ```
  
