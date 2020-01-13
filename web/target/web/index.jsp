<html>
<head><meta charset="UTF-8"/></head>
<body>
<h1>Board Game Store</h1>
<hr>
<ul>
    <li>
        <h2>Uj tarsasjatek hozzaadasa [POST]</h2>
        <ul>
            <li>URI: http://localhost:8080/boardgamestore/addBoardGame</li>
            <li>parameterek: BoardGame objektum (id, name, players, playTime, suggestedAge, categories, mechanisms, releaseDate, price)</li>
        </ul>
    </li>
    <li><h2>Tarsasjatek torlese [DELETE]</h2>
        <ul>
            <li>URI: http://localhost:8080/boardgamestore/removeBoardGame</li>
            <li>Objektum alapjan: parameterek: BoardGame objektum (id, name, players, playTime, suggestedAge, categories, mechanisms, releaseDate, price)</li>
            <li>Id alapjan: paraméterek: id</li>
        </ul>
    </li>
    <li><h2>Osszes tarsasjatek kilistazasa [GET]</h2>
        <ul>
            <li>URI: http://localhost:8080/boardgamestore/boardGames</li>
        </ul>
    </li>
    <li><h2>Tarsasjatek listazasa id alapjan [GET]</h2>
        <ul>
            <li>URI: http://localhost:8080/boardgamestore/boardGame</li>
            <li>parameterek: id</li>
        </ul>
    </li>
    <li><h2>Szures nev alapjan [GET]</h2>
        <ul>
            <li>URI: http://localhost:8080/boardgamestore/boardGamesByName</li>
            <li>parameterek: name</li>
        </ul>
    </li>
    <li><h2>Szures ajanlott eletkor alapjan [GET]</h2>
        <ul>
            <li>URI: http://localhost:8080/boardgamestore/boardGamesByAge</li>
            <li>parameterek: age</li>
        </ul>
    </li>
    <li><h2>Szures kategoria alapjan [GET]</h2>
        <ul>
            <li>URI: http://localhost:8080/boardgamestore/boardGamesByCategory</li>
            <li>parameterek: category (tobb kategoriat is megadhatunk)</li>
        </ul>
    </li>
    <li><h2>Szures mechanizmus alapjan [GET]</h2>
        <ul>
            <li>URI: http://localhost:8080/boardgamestore/boardGamesByMechanism</li>
            <li>parameterek: mechanism (tobb mechanizmust is megadhatunk)</li>
        </ul>
    </li>
    <li><h2>Hamarosan megjeleno tarsasjatekok [GET]</h2>
        <ul>
            <li>URI: http://localhost:8080/boardgamestore/comingSoon</li>
        </ul>
    </li>
    <li><h2>Szures jatekosok szama alapjan [GET]</h2>
        <ul>
            <li>URI: http://localhost:8080/boardgamestore/boardGamesByPlayers</li>
            <li>parameterek: players</li>
        </ul>
    </li>
    <li><h2>Szures jatekido alapjan [GET]</h2>
        <ul>
            <li>URI: http://localhost:8080/boardgamestore/boardGamesByPlayTime</li>
            <li>parameterek: playTime</li>
        </ul>
    </li>
    <li><h2>Szures ar alapjan [GET]</h2>
        <ul>
            <li>URI: http://localhost:8080/boardgamestore/boardGamesByPrice</li>
            <li>parameterek: fromPrice, toPrice</li>
        </ul>
    </li>
    <li><h2>Tarsasjatek modositasa [PUT]</h2>
        <ul>
            <li>URI: http://localhost:8080/boardgamestore/updateBoardGame</li>
            <li>parameterek: BoardGame objektum (name, players, playTime, suggestedAge, categories, mechanisms, releaseDate, price, quantity)</li>
        </ul>
    </li>
    <li><h2>Tarsasjatek mennyisegenek novelese [GET]</h2>
        <ul>
            <li>URI: http://localhost:8080/boardgamestore/increaseQuantity</li>
            <li>parameterek: id, quantity</li>
        </ul>
    </li>
    <li><h2>Tarsasjatek aranak modositasa [GET]</h2>
        <ul>
            <li>URI: http://localhost:8080/boardgamestore/newPrice</li>
            <li>parameterek: id, price</li>
        </ul>
    </li>
</ul>
</body>
</html>
