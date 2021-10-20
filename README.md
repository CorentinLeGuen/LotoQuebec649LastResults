# Loto Québec 6.49 - Webservices

Application is deployed on Heroku here : [loto-quebec-last-draw.herokuapp.com/loto/last](https://loto-quebec-last-draw.herokuapp.com/loto/last).

## How to install

Everything is in [pom.xml](pom.xml).

`mvn clean compile assembly:single`

## How to use

You can find the postman collection [here](/postman/).

To get the results source URL :
`localhost:8080/loto/source`

To get the last results :
`localhost:8080/loto/last`

You will get a result like this one :
```json
{
    "dateTirage": "6 octobre 2021",
    "number1": "07",
    "number2": "18",
    "number3": "19",
    "number4": "38",
    "number5": "42",
    "number6": "46",
    "numberComp": "31"
}
```

To get some stats :
`http://localhost:8080/loto/stats`

You will get something like :
```json
{
    "tirages": "3935",
    "date_debut": "1982-06-12",
    "date_fin": "2021-10-06",
    "numeros": []
}
```

To get all saved records :
`localhost:8080/loto`

You can search for only one record by adding the parameter "date" to this endpoint :
`localhost:8080/loto?date=1982-07-24`