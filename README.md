# Loto Québec 6.49 - Webservices

## How to install

Everything is in [pom.xml](pom.xml).

## How to use

You can find the postman collection [here](/postman/).

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