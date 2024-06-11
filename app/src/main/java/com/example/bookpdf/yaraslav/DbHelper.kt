package com.example.bookpdf.yaraslav

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(val context: Context, factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, "app", factory, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        // SQL команда
        var query = "CREATE TABLE users (id INT PRIMARY KEY, login TEXT, email TEXT, pass TEXT)"
        // exacSQL функция для выполнения какой либо SQL команды
        // !! нужны для обратки null если их не поставить то будет ошибка
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }
    fun addUser(user: User) {
        val values = ContentValues()
        values.put("login", user.login)
        values.put("email", user.email)
        values.put("pass", user.pass)

        // означает что мы обращаемся к нашей базе данных в которой мы можем что-то написать
        val db = this.writableDatabase
        // команда insert что-то добавляет. null мы ставим чтобы не было сдвига по колонкам, и в качестве значения ставим values
        db.insert("users", null, values)

        //Обязательно обращаемся close чтобы закрыть базу данных
        db.close()
    }

    fun getUser(login: String, pass: String): Boolean {
        val db = this.readableDatabase
//efef
        val result = db.rawQuery("SELECT * FROM users WHERE login = '$login' AND pass = '$pass'", null)
        return result.moveToFirst()
    }


}