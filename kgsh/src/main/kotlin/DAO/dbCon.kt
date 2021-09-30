package DAO

import org.jetbrains.exposed.sql.Database

fun getSQLCon(url: String, user: String, pass: String) = Database.connect(
    url,
    "com.microsoft.sqlserver.jdbc.SQLServerDriver",
    user = user,
    password = pass
)


