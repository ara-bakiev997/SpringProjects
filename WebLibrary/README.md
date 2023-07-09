# WebLibrary

# Содержание
1. [Описание проекта](#описание-проекта) 
2. [Демонстрация работы](#демонстрация-работы)
3. [Структура проекта](#структура-проекта)
4. [Подключение базы данных](#подключение-базы-данных)  

# Описание проекта
Эта директория содержит исходный код веб-библиотеки на Java. Приложение реализовано с использованием `Spring MVC`. Оно позволяет библиотекарям регистрировать читателей, выдавать им книги и освобождать книги (после того, как читатель возвращает книгу обратно в библиотеку). Для разработки данного проекта были использованы следующие технологии: `Spring MVC`, `Spring DI`, `Spring Data JDBC`, `Spring Validator`, `Hibernate Validator`, `HikariCP` и `Maven`. В качестве базы данных выбрана `PostgreSQL`. Для удобного администрирования базы данных использовался `pgAdmin`. Для удобной и повторяемой развертки базы и административного интерфейса использован `Docker Compose`. В качестве веб-сервера использовался Tomcat.

# Демонстрация работы


# Структура проекта

#### Описание основных пакетов проекта:

`configs` - Пакет, содержащий конфигурационные классы для приложения. В нем находятся классы `SpringMvcAppDispatcherServletInitializer`, ответственный за настройку диспетчера сервлетов, и `WebLibraryConfig`, отвечающий за создание и конфигурацию бинов.

`controllers` - Пакет, содержащий контроллеры, которые обрабатывают запросы от клиента. Здесь находятся классы `BookController` и `PersonController`, отвечающие за обработку запросов, связанных с книгами и персонами соответственно.

`dao` - Пакет, содержащий классы для взаимодействия с базой данных. В этом пакете находятся классы `BookDAO` и `PersonDAO`, которые предоставляют методы для работы с соответствующими сущностями в базе данных.

`models` - Пакет, содержащий модели данных приложения. Здесь находятся классы `Book` и `Person`, которые представляют сущности книги и персоны соответственно. Эти модели завалидированы с использованием `Hibernate Validator`.

`utils` - Пакет, содержащий утилитарные классы. Здесь находится класс `PersonValidator`, который является Spring валидатором и используется для валидации объектов типа Person.

<details>
<summary>Структура проекта</summary>

```yaml
├── pom.xml
└── src
    └── main
        ├── java
        │   └── edu
        │       └── spring
        │           ├── configs
        │           │   ├── SpringMvcAppDispatcherServletInitializer.java
        │           │   └── WebLibraryConfig.java
        │           ├── controllers
        │           │   ├── BookController.java
        │           │   └── PersonController.java
        │           ├── dao
        │           │   ├── BookDAO.java
        │           │   └── PersonDAO.java
        │           ├── models
        │           │   ├── Book.java
        │           │   └── Person.java
        │           └── utils
        │               └── PersonValidator.java
        ├── resources
        │   ├── db.properties
        │   ├── db.properties.origin
        │   ├── docker-compose.yml
        │   └── schema.sql
        └── webapp
            └── WEB-INF
                └── views
                    ├── books
                    │   ├── edit.html
                    │   ├── index.html
                    │   ├── new.html
                    │   └── show.html
                    └── people
                        ├── edit.html
                        ├── index.html
                        ├── new.html
                        └── show.html

```
</details>

# Подключение базы данных

В данном проекте используется PostgreSQL в связке с pgAdmin для администрирования базы данных. Для удобства развертывания и управления, вся инфраструктура базы данных запускается с помощью Docker Compose.

Docker Compose позволяет определить и описать контейнеры, их конфигурацию и зависимости в файле docker-compose.yml. В данном файле определены контейнеры для PostgreSQL и pgAdmin, а также настройки для каждого контейнера, включая порты, переменные окружения и другие параметры.

<details>
<summary>Файл docker-compose.yml</summary>

```yaml
version: '3.1'

services:

  db:
    image: postgres
    environment:
      POSTGRES_PASSWORD: admin
      POSTGRES_DB: project1
    ports:
      - 5432:5432

  adminer:
    image: dpage/pgadmin4
    environment:
      PGADMIN_DEFAULT_EMAIL: user@domain.com
      PGADMIN_DEFAULT_PASSWORD: admin
    ports:
      - 80:80
```

</details>
