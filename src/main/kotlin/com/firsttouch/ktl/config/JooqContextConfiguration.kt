package com.firsttouch.ktl.config

import org.jooq.SQLDialect
import org.jooq.impl.DataSourceConnectionProvider
import org.jooq.impl.DefaultConfiguration
import org.jooq.impl.DefaultDSLContext
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
import javax.sql.DataSource


@Configuration
@ConfigurationProperties(prefix = "datasource")
class JooqContextConfiguration {
    lateinit var driverClassName: String
    lateinit var url: String
    lateinit var username: String
    lateinit var password: String

    @Bean
    fun getDataSource(): DataSource {
        val dataSourceBuilder = DataSourceBuilder.create()
        dataSourceBuilder.driverClassName(driverClassName)
        dataSourceBuilder.url(url)
        dataSourceBuilder.username(username)
        dataSourceBuilder.password(password)
        return dataSourceBuilder.build()
    }

    @Bean
    fun connectionProvider() = DataSourceConnectionProvider(TransactionAwareDataSourceProxy(getDataSource()))

    @Bean
    fun dsl() = DefaultDSLContext(configuration())

    fun configuration(): DefaultConfiguration {
        val jooqConfiguration = DefaultConfiguration()

        val settings=jooqConfiguration.settings()
        settings.withExecuteWithOptimisticLocking(true)
                .withExecuteLogging(true)

        jooqConfiguration.set(settings)
        jooqConfiguration.set(connectionProvider())

        jooqConfiguration.setSQLDialect(SQLDialect.MYSQL)

        return jooqConfiguration
    }
}