# challenge-meli

## Build de la aplicación 
Para buildear la aplicacion local, luego de clonar el repositorio:  
1.	Abrir un cmd sobre el directorio clonado, y ejecutar el comando *mvn clean install*  
2.	Una vez que haya finalizado el build, ir a la carpeta target y ejecutar el comando *java -jar challenge-meli-0.0.1-SNAPSHOT.jar*  
3.	Una vez que ejecute el jar, ya se pueden consultar los servicios desde el postman con la url http://localhost:8080/


## CouponController
Contiene dos metodos:
* Un metodo POST getCouponProducts, que tiene como request CouponProductsRequest, y response CouponProductsResponse
* Un metodo GET getStats, cuyo response es FavItemsResponse, el cual es una lista del tipo FavItem  
  
La clase ProductPrice, se utiliza en el metodo getCouponProducts al generarse una lista del tipo ProductPrice, la cual contiene los items_id y precio. Dicha lista se utiliza posteriormente para encontrar la sublista de items cuyo monto total sea el más próximo a monto indicado en el cupon 

## /coupon
### Descripción
Dado un listado con los ítems id, y el monto del cupón otorgado, este método devuelve la lista de ítems de podría comprar el usuario, gastando el máximo posible del monto del cupón.  
Consulta la API de items para conseguir el precio de cada item *https://api.mercadolibre.com/items/{item_id}*

### URL
Metodo POST.  
https://gh6tkwbk4l.execute-api.us-east-2.amazonaws.com/challenge/coupon  

### Body request 
<details>
<p>
    <ul>
        <li>item_ids: lista con los ítems id que el usuario tiene marcado como favoritos</li>
        <li>amount: monto del cupón que se le otorga al cliente.</li>
    </ul>
</p>
<p>

```json
{
    "item_ids": [
        "MLA1733065530",
        "MLA1151535835",
        "MLA783225766",
        "MLA923172637"
    ],
    "amount": 29785
}
```
</p>
</details>

### Response  
<details>
<p>
    <ul>
        <li>item_ids: lista con los id de ítems que se le ofrece al usuario</li>
        <li>total: monto total gastado para la lista de ítems dada</li>
    </ul>
</p>
<p>

```json
{
    "item_ids": [
        "MLA1733065530",
        "MLA1151535835"
    ],
    "total": 29614.7
}

```
</p>
</details>

## /coupon/stats

### Descripción
Este método devuelve los 5 ítems más favoriteados  
*Nota: este método se encuentra en la api, pero todavía no se encuentra desarrollado, por lo que al ejecutarlo no devuelve ningún dato*

### URL
Método HTTP: GET  
https://gh6tkwbk4l.execute-api.us-east-2.amazonaws.com/challenge/coupon/stats  

### Response  
<details>
<p>
    <ul>
        <li>favItems: listado que contiene el item id y la cantidad de favoritos que tiene</li>
    </ul>
</p>
<p>

```json
{
    "favItems": [
        {
            "id": "MLA1733065530",
            "quantity": 20
        }
    ]
}

```
</p>
</details>
