package com.firsttouch.ktl.config

import org.jooq.SQLDialect
import org.jooq.impl.DataSourceConnectionProvider
import org.jooq.impl.DefaultConfiguration
import org.jooq.impl.DefaultDSLContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
import javax.sql.DataSource

@Configuration
class JooqContextConfiguration(private val dataSource: DataSource) {
    @Bean
    fun connectionProvider() = DataSourceConnectionProvider(TransactionAwareDataSourceProxy(dataSource))

    @Bean
    fun dsl() = DefaultDSLContext(configuration())

    private fun configuration(): DefaultConfiguration {
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