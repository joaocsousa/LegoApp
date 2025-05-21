package xyz.aranhapreta.database

import android.annotation.SuppressLint
import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

@SuppressLint("StaticFieldLeak")
object DatabaseContext {
    lateinit var Context: Context
}

actual fun createSqlDriver(): SqlDriver {
    return AndroidSqliteDriver(
        schema = Database.Schema,
        context = DatabaseContext.Context,
        name = "database.db"
    )
}