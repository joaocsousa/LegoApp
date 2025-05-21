package xyz.aranhapreta.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

actual fun createSqlDriver(): SqlDriver {
    return NativeSqliteDriver(
        schema = Database.Schema,
        name = "database.db"
    )
}