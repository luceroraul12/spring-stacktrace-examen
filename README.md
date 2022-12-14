Requerimientos para probar:<br>
<ol>
    <li>Tener instalado PostgreSQL, en mi caso utilice PostgreSQL 14</li>
    <li>Crear un usuario con todos los privilegios y los siguientes parametros
        <ol>username: stacktrace</ol>
        <ol>password: stacktrace</ol>
    </li>
    <li>Crear una base de datos con el nombre: <b>stacktrace</b></li>
</ol>

En la siguiente figura se muestra el digrama de tablas elaborado:<br>
![image](https://user-images.githubusercontent.com/106817372/207612382-333d8b3c-7e4b-44e5-a805-407b53a35313.png)

En el diagrama se muestran: <br>
<ol>
    <li><b>usuario</b>: datos almacenados de un usuario</li>
    <li><b>billetera</b>: Es una tabla encargada de relacionar un conjunto de activos por cada registro de billetera que a su vez relaciona a un usuario</li>
    <li><b>billetera_activos</b>: Son divisas con cantidades adquirida  y que estan relacionadas con las billeteras. Una billetera no puede tener varios registro de una misma divisa</li>
    <li><b>moneda_cripto</b>: Son el conjunto de divisas que se pueden usar al momento de registrar billetera_activos. No pueden existir divisas con mismo nombre ni tampoco con relacion nula<br>
        valore por defecto:   ![image](https://user-images.githubusercontent.com/106817372/207618625-b8b37ad7-1b51-4ccd-b094-6a5e01bac6c6.png)
    </li>
    <li><b>operacion</b>: Son los registros que se realizan de manera automatica cuando se lleva a cabo una operacion como <b>DEPOSITO</b> o <b>INTERCAMBIO</b>.<br>
        Como mi estructura de tablas es un poco diferente a la dicha en el enunciado, en lugar de guardar las billeteras, almacena los <b>billetera_activos</b> involucrados(origen-destino).<br>
        Por llaves foraneas se puede saber a que billetera pertenecen. Ademas, se le agrego el apartado cantidad_operada que indica la cantidad que se deposito/intercambio.<br>
        En caso de <b>DEPOSITO</b>, cantidad_operada se encuentra en la misma moneda que el activo_destino, mientras que para <b>INTERCAMBIO</b>, cantidad_operada se encuentra en la misma moneda que el activo_origen (luego se debe realizar dicha conversion a la moneda del destino)    
    </li>
</ol>

Respecto al backend: <br>
url Swagger: <a href="http://localhost:8080/swagger-ui/index.html">swagger</a>

Hay dos tipo tipo de Servicios,Controladores: <br>
<ol>
    <li>Necesarios para realizar ABM, ControllerABM, ServiceABM, Util y sus implementaciones. <br>
        Son clases genericas para reutilizar codigo como tambien documentacionde javadoc o swagger.
        Tanto las actividades de alta como modificacion tendran un metodo para poder verificar que la informacion de entrada(Clase del tipo DTO) cumple para llevar a cabo dicha accion.<br>
        Para el caso de billetera, adicionalmente incluye endpoints de consultas de billetera unica o asociadas a un usuario, tanto resumen monetario en pesos como tambien el estado de todos los activos asociados.
    </li>
    <li>
        Necesario para trabajar los diferentes tipo de operaciones(DEPOSITO, INTERCAMBIO), BilleteraOperacionController y BilleteraOperacionService
    </li>
</ol>

Paso logico de utilizacion: <br>
<ol>
    <li>ABM de Divisas (opcional debido a que incluyo dos por defecto: ETH y BTC)</li>
    <li>ABM de Usuario(automaticamente se crea su propia billetera)</li>
    <li>ABM de billeteras(Una billetera siempre necesita un usuario existente en el sistema)</li>
    <li>ABM Activos (necesitan una billetera y divisa). La generacion de billeteras nunca genera Activos de manera automatica, por lo tanto siempre hay que crearlos para su posterior uso en operaciones de INTERCAMBIO y DEPOSITO.<br>
        Cuando se realiza una ALTA o creacion, nunca se tendra en cuenta la cantidad almacenada, por lo que siempre los registro de activos tienen la cantidad de 0.0. Esto tiene la finalidad de que toda adquisicion por INTERCAMBIO o DEPOSITO quede registrado en el sistema.
    </li>
    <li>Operaciones activos (necesita activos ya almacenados en el sistema)</li>
</ol>
