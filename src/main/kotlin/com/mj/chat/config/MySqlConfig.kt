package com.mj.chat.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.support.TransactionTemplate
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
class MySqlConfig {
    @Bean
    fun transactionManager(dataSource: DataSource): DataSourceTransactionManager = DataSourceTransactionManager(dataSource)

    @Bean
    fun transactionTemplate(transactionManager: PlatformTransactionManager): TransactionTemplate = TransactionTemplate(transactionManager)

    // 추후 tx 설정 세부 조정 필요
    @Bean(name = ["createUserTransactionManager"])
    fun crateateUserTransactionManager(dataSource: DataSource): PlatformTransactionManager {
        val manager = DataSourceTransactionManager(dataSource)
        return manager
    }
}
