Инструкция для запуска:
1) Выполнить скрипт на создание БД (CREATE DATABASE rss_reader;) PostgreSQL;
2) Настроить configs (application.yml), указать datasource в каждом модуле.
  Можно использовать профили для конфигов (маска application-*), *-(название профиля).
3) Запустить сервис rss-reader чтобы сервис загрузил rss ленты, ленты задаются хардкорно
(БД, Java), 
  (в конфиге можно указать переодичность обновления, на данный момент стоит не прекращающаяся загрузка)
  Во время запуска, сервис rss-reader вызовет сервис для создания нужных объектов в БД. 
  (Схема. таблицы создадутся автоматически)
  
4) Далее запустить модуль rss-web и перейти по адресу localhost:8080.
    Кликнуть на подписку из списка и выбрать новость для чтения. Также можно искать тайтлы 
    по ключевым словам в строке поиска.
