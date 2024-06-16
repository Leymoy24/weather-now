<img src= "https://github.com/Leymoy24/film-finder/assets/91724230/4576aefc-c387-46fe-8c80-5d55695ede78" width="64">

# **Weather Now**
**Weather Now** - мобильное приложения для просмотра текущей погоды в конкретном городе.
</br>Приложение состоит из двух экранов:
- Экран "Список городов"
- Экран "Прогноз погоды"
  
## Функционал
- Просмотр списка городов:
	- Группировка городов в алфавитном порядке
  - Отображение городов в виде "sticky label"
- Просмотр текущей температуры в выбранном городе
- Отображение загрузки данных
- Оповещение об ошибке получения данных

## Дизайн
<img src="https://github.com/Leymoy24/weather-now/assets/91724230/d54d9a78-fe3c-4d80-9290-21936ddda15e" width="250">
<img src="https://github.com/Leymoy24/weather-now/assets/91724230/e48297d1-de80-4330-85e3-8a72e6bd7131" width="250">
<img src="https://github.com/Leymoy24/weather-now/assets/91724230/a7b056a6-cf59-4d2c-b28f-9ed3af9c8095" width="250">

## Демонстрация работоспособности sticky label </br>
https://github.com/Leymoy24/weather-now/assets/91724230/df48f0fc-cb10-46e9-a9c6-eefed8346d0f

## Используемые технологии
1. **Язык:** Kotlin
2. **Работа с сетью:** Ktor Client
3. **Многопоточность:** Kotlin Coroutines/Flow
4. **View:** Jetpack Compose, Material Design 3 Components
5. **Сериализаторы:** Kotlinx Serialization, Gson
6. **Навигация:** Jetpack Navigation
7. **DI:** Dagger Hilt
8. **Архитектура:** MVVM, Clean Architecture

## Установка и настройка
Чтобы запустить приложение на своем устройстве, выполните следующие шаги:
- Запуск с помощью Android Studio:
1. Склонируйте репозиторий в вашу локальную папку: https://github.com/Leymoy24/weather-now.git
2. Откройте проект в Android Studio
3. Запустите приложение на эмуляторе или на устройстве
- Запуск без Android Studio:
1. Скачайте [apk-файл](https://disk.yandex.ru/d/42xS-Tbz9_B3xQ) на своё устройство
2. Установите apk-файл
3. Запустите приложение

## Дополнительная информация
Источник данных с информацией о погоде - [OpenWeatherMap API](https://openweathermap.org/current)
