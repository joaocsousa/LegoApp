package xyz.aranhapreta.database

import app.cash.sqldelight.db.SqlDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

val databaseKoinModule = module {
    single<Database> {
        Database(driver = get())
    }
    single<SqlDriver> {
        createSqlDriver()
    }
    factory<CharactersDao> {
        CharactersDaoImpl(database = get(), ioDispatcher = Dispatchers.IO)
    }
}