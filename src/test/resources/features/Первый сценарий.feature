#language:ru

Функционал: Вход на сайт ТЭК-ТОРГ Росневть

  Сценарий: Вход под оператором
    Пусть совершен переход на страницу "LoginPage" по ссылке из property файла "demoTektorgRu"
    Когда очищено поле "Логин" и введено значение "orgzp"
    И очищено поле "Пароль" и введено значение "1234567"
    Когда выполнено нажатие на кнопку "Вход"
    И страница "Главноая страница оператора" загрузилась
    Тогда выполнено нажатие на кнопку "Выход"
    Тогда страница "LoginPage" загрузилась

  Сценарий: Проверка заключенных договоров
    Пусть совершен переход на страницу "LoginPage" по ссылке из property файла "demoTektorgRu"
    Когда очищено поле "Логин" и введено значение "orgzp"
    И очищено поле "Пароль" и введено значение "1234567"
    Когда выполнено нажатие на кнопку "Вход"
    И страница "Главноая страница оператора" загрузилась
    И выполнено нажатие на кнопку "Договоры"
    И выполнено нажатие на поле "Заключенные договоры"
    И выполнено нажатие на кнопку "Операции"
    Тогда страница "Договор по лоту" загрузилась