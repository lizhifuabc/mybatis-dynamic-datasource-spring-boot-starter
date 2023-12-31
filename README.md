# mybatis-dynamic-datasource-spring-boot-starter
基于mybatis插件机制简单实现分库分表。

1. 配置默认数据源，其他数据源使用默认数据源的相关参数（例如：超时时间等等）
2. 

## 分库分表基本概念

常见名词：

1. 分片（Sharding）：将数据分割成多个较小的数据集，每个分片独立存储。通常，分片的选择是根据数据的某个特定属性（例如，按用户ID范围、按地理位置等）进行的。
2. 分库（Database Sharding）：将分片分配到不同的数据库实例中，每个数据库实例可以独立地处理自己的分片。这可以通过在不同的物理服务器上运行多个数据库实例来实现。
3. 分表（Table Sharding）：在每个分片内部，可以进一步将表按照某种规则进行拆分，例如按照时间、按照数据范围等。这样可以将查询和写入操作分散到不同的表中，减少单个表的数据量，提高查询效率。
4. 路由（Routing）：根据查询的条件将查询路由到正确的分片和表。通常，需要一个路由层来解析查询，并将查询发送到相应的分片和表中。
5. 数据一致性和分片键选择：在分库分表架构中，需要考虑数据一致性的问题，以及选择合适的分片键来确保数据均匀分布和查询的效率。
6. 事务处理和跨分片操作：由于数据分散在多个分片和数据库实例中，处理跨分片的事务和操作变得更加复杂。通常需要额外的技术和策略来处理这些情况。

## 常见算法

1. 哈希路由：根据数据的哈希值进行路由，将数据均匀分布到不同的分片或表中。这种方法可以确保数据在各个分片之间的平衡，但可能导致某些特定查询的性能问题。
2. 范围路由：根据数据的范围进行路由，例如按照时间范围、按照地理位置范围等。这种方法适用于需要按照某个顺序或范围查询数据的场景。
3. 列值路由：根据数据的某个列的取值进行路由，例如按照用户ID、按照产品类型等。这种方法适用于需要按照某个特定属性查询数据的场景。
4. 混合路由：结合多个列或条件进行路由。可以根据多个属性的组合来决定数据的分布位置，以满足更复杂的查询需求。



1. 使用Spring的AbstractRoutingDataSource进行数据源的动态切换,原理是使用ThreadLocal先存储数据源key,等需要的的时候获取。
2. aop 分库
3. mybatis 插件分库


mvn clean deploy -DskipTests -P release