# RESTfull
RESTfull web-сервер для взаимодействия клиент-сервер 

Разделы 

Общая статистика. Запрос информации о всех персонах упоминаемых на опредененном сайте.

/stat/over_stat?site=<название сайта>

Пример запроса по lenta.ru:
/stat/over_stat?site=lenta.ru

[
    {
	"person":"Путин",
	"mentions":100000
    },
    {
	"person":"Медведев",
	"mentions":50000
    },
    {
	"person":"Навальный",
	"mentions":25000
    }
]
