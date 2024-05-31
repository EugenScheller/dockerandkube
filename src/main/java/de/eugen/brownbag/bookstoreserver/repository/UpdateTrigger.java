package de.eugen.brownbag.bookstoreserver.repository;

import org.h2.api.Trigger;

import java.sql.Connection;

public class UpdateTrigger implements Trigger {
    @Override
    public void init(Connection conn, String schemaName, String triggerName, String tableName,
                     boolean before, int type) { /* code here */ }

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) {
        // Here we actually set the `updated_at` to CURRENT_TIMESTAMP
        newRow[3] = System.currentTimeMillis();
    }
}