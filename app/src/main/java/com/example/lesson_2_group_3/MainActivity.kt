package com.example.lesson_2_group_3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Класс MainActivity, наследник AppCompatActivity
class MainActivity : AppCompatActivity() {
    // Метод onCreate — запускается при старте приложения самым первым
    override fun onCreate(savedInstanceState: Bundle?) {
        // Вызов родительского метода
        super.onCreate(savedInstanceState)
        // Игнорирование системных рамок
        enableEdgeToEdge()
        // Определяет то, как будет выглядеть экран
        // Содержит ссылку на файл activity_main.xml
        setContentView(R.layout.activity_main)
        // Задание отступов для элементов пользовательского интерфейса
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}