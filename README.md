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
