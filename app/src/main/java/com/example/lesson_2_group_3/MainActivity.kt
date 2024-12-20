package com.example.lesson_2_group_3

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.lesson_2_group_3.databinding.ActivityMainBinding

// Класс MainActivity, наследник AppCompatActivity
class MainActivity : AppCompatActivity() {
    // Свойство, которое хранит информацию о том, было ли показано всплывающее сообщение пользователю
    private var showToast = false

    // Метод, который будет срабатывать при нажатии на кнопку через xml параметр onClick
    fun buttonClick(view: View) {
        Toast.makeText(this, "Вы нажати на кнопку через xml макет", Toast.LENGTH_SHORT).show()
    }

    // Свойство, для использования библиотеки viewBinding
    private lateinit var activityMainBinding: ActivityMainBinding

    // Свойство, отвечающее за работу с ViewModel
    private lateinit var mainActivityViewModel: MainActivityViewModel

    // Метод onCreate — запускается при старте активности самым первым, либо после вызова onPause/onStop
    // Создает объекты пользовательского интерфейса для показа пользователю
    override fun onCreate(savedInstanceState: Bundle?) {
        // Вызов родительского метода
        super.onCreate(savedInstanceState)

        // Инициализая свойства activityMainBinding
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        // Инициализация MainActivityViewModel
        val viewModelProvider = ViewModelProvider(this)
        mainActivityViewModel = viewModelProvider[MainActivityViewModel::class.java]

        // Присвоение свойству showToast значения из Bundle хранилища с ключом SHOW_TOAST
        if (savedInstanceState != null)
            showToast = savedInstanceState.getBoolean("SHOW_TOAST")

        // Игнорирование системных рамок
        enableEdgeToEdge()

        // Определяет то, как будет выглядеть экран
        // Содержит ссылку на файл activity_main.xml
        // setContentView(R.layout.activity_main)

        // Аналог вызова setContentView с использованием viewBinding
        setContentView(activityMainBinding.root)

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

        // Создание объектов пользовательского интерфейса в коде
        val textView: TextView = findViewById(R.id.textView)
        val textView2: TextView = findViewById(R.id.textView2)
        val button = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val imageView2 = findViewById<ImageView>(R.id.imageView2)

        // Работа с объектами пользовательского интерфейса в коде
        // Замена текста в TextView
        textView.text = "Hello!"
        textView2.setText("New text")

        // Слушатель нажатий в TextView
        textView.setOnClickListener {
            // Обращение к строковому ресурсу ncreate
            textView.text = getString(R.string.ncreate)
        }

        // Замена текста в Button
        button.text = "Click me!"

        // Слушатель нажатий в Button
        button.setOnClickListener {
            Toast.makeText(this, "Кнопка нажата", Toast.LENGTH_SHORT).show()
            button.text = "You clicked!"
        }

        // Слушатель удержания кнопки
        button.setOnLongClickListener {
            button.text = "Long click!"
            return@setOnLongClickListener true
        }

        // Использование объекта activityMainBinding для взаимодействия с элементами пользовательского интерфейса
        // Смена текста в поле для ввода текста
        activityMainBinding.editTextText.setText("New text in editText")
        // Обработка введенного текста в поле для ввода
        Toast.makeText(this, activityMainBinding.editTextText.text.toString(), Toast.LENGTH_LONG)
            .show()

        // Работа со Switch
        activityMainBinding.switch1.setOnClickListener {
            if (activityMainBinding.switch1.isChecked) {
                activityMainBinding.switch1.isChecked = true
                activityMainBinding.switch1.text = "Вкл"
            } else {
                activityMainBinding.switch1.isChecked = false
                activityMainBinding.switch1.text = "Выкл"
            }
        }

        // Работа со Spinner
        // Создание адаптера, который содержит созданный список
        val spinnerAdapter = ArrayAdapter(
            this,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            listOf("Java", "Kotlin", "PHP", "Python")
        )
        // Передача созданного адаптера в spinner
        activityMainBinding.spinner.adapter = spinnerAdapter

        // Отображение введенного в поле для ввода текста в textView
        activityMainBinding.floatingActionButton.setOnClickListener {
            activityMainBinding.textView2.text =
                activityMainBinding.editTextNumberPassword.text.toString()
        }

        // Создание наблюдателя за свойством editText
        mainActivityViewModel.getEditText().observe(this) {
            Log.i("ИЗМЕНЕНО", it.toString())
            activityMainBinding.textView.text = it.toString()
        }

        // Слушатель введенного в поле для ввода текста
        activityMainBinding.editTextText.addTextChangedListener {
            // Изменение значения свойства editText
            mainActivityViewModel.getEditText().value = it.toString()
        }

        // Работа с ресурсами в коде
        activityMainBinding.button2.setOnClickListener {
            button2.text = getString(R.string.new_text)
            button2.setBackgroundColor(getColor(R.color.blue))
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