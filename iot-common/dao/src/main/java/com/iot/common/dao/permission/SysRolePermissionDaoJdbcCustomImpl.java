package com.iot.common.dao.permission;

import com.iot.common.dao.base.AbstractJdbcDaoCustom;
import com.iot.common.entity.permission.RolePermissionEntity;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SysRolePermissionDaoJdbcCustomImpl extends AbstractJdbcDaoCustom implements SysRolePermissionDaoJdbcCustom {

    private static final String BATCH_SAVE_SQL = """
            insert into user_permission.sys_role_permission(create_time,create_uname,update_time,update_uname,permission_id,role_id,deleted) 
            values(?,?,?,?,?,?,false)
            """;

    @Override
    public void batchSave(List<RolePermissionEntity> list) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                jdbcTemplate.batchUpdate(BATCH_SAVE_SQL, new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        var entity = list.get(i);
                        ps.setObject(1,entity.getCreateTime());
                        ps.setString(2,entity.getCreateUname());
                        ps.setObject(3,entity.getCreateTime());
                        ps.setString(4,entity.getCreateUname());
                        ps.setLong(5,entity.getPermissionId());
                        ps.setLong(6,entity.getRoleId());
                    }
                    @Override
                    public int getBatchSize() {
                        return list.size();
                    }
                });
            }
        });
    }
}
