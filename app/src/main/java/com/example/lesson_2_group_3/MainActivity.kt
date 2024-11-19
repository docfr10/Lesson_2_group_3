package com.example.lesson_2_group_3

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Класс MainActivity, наследник AppCompatActivity
class MainActivity : AppCompatActivity() {
    // Свойство, которое хранит информацию о том, было ли показано всплывающее сообщение пользователю
    private var showToast = false

    // Метод onCreate — запускается при старте активности самым первым, либо после вызова onPause/onStop
    // Создает объекты пользовательского интерфейса для показа пользователю
    override fun onCreate(savedInstanceState: Bundle?) {
        // Вызов родительского метода
        super.onCreate(savedInstanceState)

        // Присвоение свойству showToast значения из Bundle хранилища с ключом SHOW_TOAST
        if (savedInstanceState != null)
            showToast = savedInstanceState.getBoolean("SHOW_TOAST")

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

        // ТИПЫ ЛОГОВ
        /*
        Verbose - полный протокол системных действий.
        Debug - отладочная информация: параметры,порождаемые в процессе работы приложения.
        Info - информационные сообщения.
        Warn - предупреждения.
        Error - ошибки, возникающие в процессе работы приложения.
        В протоколе выделяются красно-коричневым цветом, чтобы их было сразу заметно.
         */

        // Логирование сообщений
        Log.i("Метод жизненного цикла", "onCreate")

        // Проверка того было ли показано сообщение пользователю
        if (!showToast) {
            // Всплывающее сообщение
            Toast.makeText(this, "Привет пользователь!", Toast.LENGTH_SHORT).show()
            showToast = true
        }
    }

    // Метод onStart - запускается после onCreate/onRestart
    // Подготовливает пользовательский интерфейс к показу пользователю
    override fun onStart() {
        super.onStart()
        Log.v("Метод жизненного цикла", "onStart")
    }

    // Метод onResume - запускается после onStart, либо после onPause
    // Демонстрирует пользователю интерфейс активности
    // После его вызова пользователь может взаимодействовать с интерфейсом
    override fun onResume() {
        super.onResume()
        Log.d("Метод жизненного цикла", "onResume")
    }

    // Метод onPause - запускается после onResume
    // Готовит активность к переходу в "спящий режим", перезапуску или возобновлению работы
    override fun onPause() {
        super.onPause()
        Log.w("Метод жизненного цикла", "onPause")
    }

    // Метод onStop - запускается после onPause
    // Переводит активность в "спящий режим" и готовит к возобновлению работы или уничтожению
    override fun onStop() {
        super.onStop()
        Log.e("Метод жизненного цикла", "onStop")
    }

    // Метод onRestart - запускается после onStop
    // Перезагружает пользовательский интерфейс, но создает его заново
    override fun onRestart() {
        super.onRestart()
        Log.i("Метод жизненного цикла", "onRestart")
    }

    // Метод onDestroy - запускается после onStop
    // Вызывается после уничтожения активности после завершения ее работы
    override fun onDestroy() {
        super.onDestroy()
        Log.i("Метод жизненного цикла", "onDestroy")
    }

    // Метод onSaveInstanceState - вызвается для записи значений
    // в Bundle хранилище перед перезагрузкой активности
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Запись в Bundle хранилище значения showToast с ключом SHOW_TOAST
        outState.putBoolean("SHOW_TOAST", showToast)
    }
}