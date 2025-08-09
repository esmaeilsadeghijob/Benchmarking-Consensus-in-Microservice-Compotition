package com.javatar.artistsservice.config;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.env.spi.NameQualifierSupport;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.descriptor.jdbc.IntegerJdbcType;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        super();
    }

    @Override
    public NameQualifierSupport getNameQualifierSupport() {
        return NameQualifierSupport.NONE;
    }

    @Override
    public boolean hasAlterTable() {
        return false;
    }

    @Override
    public boolean dropConstraints() {
        return false;
    }

    @Override
    public String getAddColumnString() {
        return "add column";
    }

    @Override
    public String getForUpdateString() {
        return "";
    }

    @Override
    public boolean supportsOuterJoinForUpdate() {
        return false;
    }

    @Override
    public boolean supportsIfExistsBeforeTableName() {
        return true;
    }

    @Override
    public boolean supportsCascadeDelete() {
        return false;
    }

    @Override
    public void contributeTypes(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        var jdbcTypeRegistry = typeContributions.getTypeConfiguration().getJdbcTypeRegistry();
        jdbcTypeRegistry.addDescriptor(SqlTypes.INTEGER, IntegerJdbcType.INSTANCE);
    }
}
