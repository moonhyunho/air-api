package com.air.api.configuration

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.sql.DataSource

@EnableJpaRepositories(
    basePackages = ["com.air.api.domain"]
)
@Configuration
class JpaDataSourceConfiguration {

    @Primary
    @Bean
    @ConfigurationProperties("api.datasource")
    fun dataSourceProperties(): DataSourceProperties = DataSourceProperties()

    @Primary
    @Bean
    fun dataSource(): DataSource =
        withDataSourceProps(dataSourceProperties().initializeDataSourceBuilder().build(), false, "default-pool")

    @Primary
    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        return LocalContainerEntityManagerFactoryBean().apply {
            this.dataSource = dataSource()
            this.setPackagesToScan("com.air.api.domain")
            this.jpaVendorAdapter = setHibernateJpaVendorAdapter("org.hibernate.dialect.MySQL5Dialect")
            this.setJpaPropertyMap(
                mapOf(
                    "hibernate.format_sql" to true
                )
            )
        }
    }

    private fun withDataSourceProps(_dataSource: DataSource, readOnly: Boolean, poolName: String): DataSource {
        if (_dataSource is HikariDataSource) {
            _dataSource.isReadOnly = readOnly
            _dataSource.isAutoCommit = false
            _dataSource.poolName = poolName
            _dataSource.minimumIdle = 3
            _dataSource.maximumPoolSize = 5
            _dataSource.connectionTestQuery = "select 1"

            _dataSource.addDataSourceProperty("cachePrepStmts", "true")
            _dataSource.addDataSourceProperty("useServerPrepStmts", "true")
            _dataSource.addDataSourceProperty("prepStmtCacheSize", "250")
            _dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")
            _dataSource.addDataSourceProperty("rewriteBatchedStatements", "true")
        }
        return _dataSource
    }

    private fun setHibernateJpaVendorAdapter(databasePlatform: String): HibernateJpaVendorAdapter {
        return HibernateJpaVendorAdapter().apply {
            this.setDatabase(Database.MYSQL)
            this.setGenerateDdl(false)
            this.setPrepareConnection(true)
            this.setShowSql(true)
            this.setDatabasePlatform(databasePlatform)
        }
    }

    @Bean
    fun exceptionTranslationPostProcessor(): PersistenceExceptionTranslationPostProcessor =
        PersistenceExceptionTranslationPostProcessor()

}
