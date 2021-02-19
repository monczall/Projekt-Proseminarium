<span style="color: #33415C;">.</span>
<div class="container my-3">
    <h3>Warsztat 1</h3>
    <div class="table-wrapper">
        <p id="showData"></p>
    </div>
</div>
<div class="container my-3">
    <h3>Warsztat 2</h3>
    <div class="table-wrapper">
        <p id="showData2"></p>
    </div>
</div>

<script>
    function CreateTableFromJSON() {
        var myBooks = JSON.parse(Get('http://localhost:8080/naprawy'));
        myBooks = myBooks["Naprawy"];
        // EXTRACT VALUE FOR HTML HEADER. 
        // ('Book ID', 'Book Name', 'Category' and 'Price')
        var col = [];
        for (var i = 0; i < myBooks.length; i++) {
            for (var key in myBooks[i]) {
                if (col.indexOf(key) === -1) {
                    col.push(key);
                }
            }
        }

        // CREATE DYNAMIC TABLE.
        var table = document.createElement("table");

        // CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

        var tr = table.insertRow(-1);                   // TABLE ROW.

        for (var i = 0; i < col.length; i++) {
            if(i != 8){
                var th = document.createElement("th");      // TABLE HEADER.
                th.innerHTML = col[i];
                tr.appendChild(th);
            }
            
        }

        // ADD JSON DATA TO THE TABLE AS ROWS.
        for (var i = 0; i < myBooks.length; i++) {
            if(myBooks[i].id_uzytkownika == '<?php echo $_SESSION["id"]?>'){
                tr = table.insertRow(-1);

                for (var j = 0; j < col.length; j++) {
                    if(j != 8){
                        var tabCell = tr.insertCell(-1);
                        tabCell.innerHTML = myBooks[i][col[j]];
                    }
                }
            }

            
        }

        // FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
        var divContainer = document.getElementById("showData");
        divContainer.innerHTML = "";
        divContainer.appendChild(table);
    }
    //Pobieranie JSONA
    function Get(yourUrl){
      var Httpreq = new XMLHttpRequest(); // a new request
      Httpreq.open("GET",yourUrl,false);
      Httpreq.send(null);
      return Httpreq.responseText;          
    }
</script>

<script>
    CreateTableFromJSON();
</script>

<script>
    function CreateTableFromJSON2() {
        var myBooks = JSON.parse(Get('http://localhost:8081/naprawy'));
        myBooks = myBooks["Naprawy"];
        // EXTRACT VALUE FOR HTML HEADER. 
        // ('Book ID', 'Book Name', 'Category' and 'Price')
        var col = [];
        for (var i = 0; i < myBooks.length; i++) {
            for (var key in myBooks[i]) {
                if (col.indexOf(key) === -1) {
                    col.push(key);
                }
            }
        }

        // CREATE DYNAMIC TABLE.
        var table = document.createElement("table");

        // CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

        var tr = table.insertRow(-1);                   // TABLE ROW.

        for (var i = 0; i < col.length; i++) {
            if(i != 8){
                var th = document.createElement("th");      // TABLE HEADER.
                th.innerHTML = col[i];
                tr.appendChild(th);
            }
            
        }

        // ADD JSON DATA TO THE TABLE AS ROWS.
        for (var i = 0; i < myBooks.length; i++) {
            if(myBooks[i].id_uzytkownika == '<?php echo $_SESSION["id"]?>'){
                tr = table.insertRow(-1);

                for (var j = 0; j < col.length; j++) {
                    if(j != 8){
                        var tabCell = tr.insertCell(-1);
                        tabCell.innerHTML = myBooks[i][col[j]];
                    }
                }
            }

            
        }

        // FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
        var divContainer = document.getElementById("showData2");
        divContainer.innerHTML = "";
        divContainer.appendChild(table);
    }
    //Pobieranie JSONA
    function Get(yourUrl){
      var Httpreq = new XMLHttpRequest(); // a new request
      Httpreq.open("GET",yourUrl,false);
      Httpreq.send(null);
      return Httpreq.responseText;          
    }
</script>

<script>
    CreateTableFromJSON2();
</script>