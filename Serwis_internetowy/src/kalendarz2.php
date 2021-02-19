<div class="container my-3">
  
  <h1>Kalendarz terminów</h1>
  <h3>Wybierz termin w którym podstawisz auto do warsztatu</h3>
  <div id="calendar"></div>
</div>
<div class="modal fade" id="event-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title">Modal title</h4>
      </div>
      <div class="modal-body">
        <form name="save-event" method="post">
          <div class="form-group">
            <label>Title</label>
            <input type="text" name="title" class="form-control" />
          </div>
          <div class="form-group">
            <label>Event start</label>
            <input type="text" name="evtStart" class="form-control col-xs-3" />
          </div>
          <div class="form-group">
            <label>Event end</label>
            <input type="text" name="evtEnd" class="form-control col-xs-3" />
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Save changes</button>
      </div>
    </div>
    <!-- /.modal-content -->
  </div>
  <!-- /.modal-dialog -->
</div>
<script src="https://unpkg.com/jquery@3.4.1/dist/jquery.min.js"></script>
<script src="https://unpkg.com/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://unpkg.com/bootstrap@4.4.1/dist/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/core/main.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/core/locales/pl.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/interaction/main.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/daygrid/main.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/bootstrap/main.min.js"></script>
<script>
    var el = document.getElementById('calendar');
    var counter = 0;
    var inJson = JSON.parse(Get('http://localhost:8081/terminy'));
    //var inJson = JSON.parse(Get('events.json'));
    var zamknieteJson = JSON.parse(Get('zamkniete.json'));
    inJson1 = inJson["Terminy_napraw"];
    for(var x in inJson1){
      inJson1[x].title = "Zajęte";
      console.log(inJson1[x].end);
      var day1 = inJson1[x].end.charAt(8);
      var day2 = inJson1[x].end.charAt(9);
      console.log(day1);
      console.log(day2);
      if(((day1 != 3 && day2 != 0) && (day1 != 3 && day2 != 1)) || (day1 != 2 && day2 != 8)){
        if(day2 < 9){
          day2 = (parseInt(day2) + 1)
        }else if(day2 == 9 && day1 == 0){
          day2 = 0;
          day1 = 1;
        }else if(day2 == 9 && day1 == 1){
          day2 = 0;
          day1 = 2;
        }else if(day2 == 9 && day1 == 2){
          day2 = 0;
          day1 = 3;
        }
      }
      inJson1[x].end = "2021-" + inJson1[x].end.charAt(5) + inJson1[x].end.charAt(6) + "-" + day1 + day2;
        
      inJson1[x].backgroundColor = "#d00000";
      counter++;
    }
    var prototype = new Date();
    var pastYear = prototype.getFullYear();
    if(prototype.getMonth() < 10){
      var pastMonth = "0" + (prototype.getMonth() + 1);
    }else{
      var pastMonth = (prototype.getMonth() + 1);
    }
    if(prototype.getDate() < 10){
      var pastDay = "0" + prototype.getDate();
    }else{
      var pastDay = prototype.getDate();
    }

    var pastDate = pastYear + '-' + pastMonth + '-' + pastDay;

    console.log(pastDate);

    inJson1[counter] = {};
    inJson1[counter].title = "Nie można zarezerwować tego terminu.. No chyba że potrafisz przenosić się w czasie";
    inJson1[counter].backgroundColor = "grey";
    inJson1[counter].start = "2020-01-01";
    inJson1[counter].end = pastDate;
    counter++;
    var poz = 0;
    for(y in zamknieteJson){
      poz = (counter + parseInt(y));
      inJson1[poz] = {};
      inJson1[poz].title = zamknieteJson[y].title;
      inJson1[poz].backgroundColor = "grey";
      inJson1[poz].start = zamknieteJson[y].start;
      inJson1[poz].end = zamknieteJson[y].end;
    }
    //var merged = Object.assign(zamknieteJson, inJson1,);
    console.log(inJson1);
    var calendar = new FullCalendar.Calendar(el, {

        plugins: ['interaction', 'dayGrid', 'bootstrap'],
        themeSystem: 'bootstrap',
        weekNumbers: false,
        eventLimit: true,
        events: inJson1,
        timeZone: 'Europe/Warsaw',
        locales: ['plLocale'],
        locale: 'pl',
        selectable: true,
        editable: false,
        select: function(selectionInfo) {
            var year = selectionInfo.start.getFullYear();
            if(selectionInfo.start.getMonth() < 10){
              var month = (parseInt(selectionInfo.start.getMonth()) + 1);
              month = '0' + month;
              
            }else{
              var month = (parseInt(selectionInfo.start.getMonth()) + 1);
            }
            

            if(selectionInfo.start.getDate() < 10){
              var day = '0' + selectionInfo.start.getDate();
            }else{
              var day = selectionInfo.start.getDate();
            }
            var uga = '<?php echo $_SESSION["imie"]?>';
            
            if (confirm('Czy na pewno chcesz zarezerwowoać ten termin?')) {
              var json = '{"Naprawa":[{"id_uzytkownika": "' + '<?php echo $_SESSION["id"]?>' + '", "marka": "' + '<?php echo $_SESSION["marka_samochodu"]?>' + '", "model": "' + '<?php echo $_SESSION["model_samochodu"]?>' + '", "rok_produkcji": "' + '<?php echo $_SESSION["rok_produkcji"]?>' + '", "data_przyjecia_naprawy": "' + year + "-" + month + "-" + day + '", "opis_usterki": "' + '<?php echo $_SESSION["opis_usterki"]?>' + '", "planowany_poczatek_naprawy": "' + year + "-" + month + "-" + day + '", "planowany_koniec_naprawy": "' + year + "-" + month + "-" + day + '"}]}';
              //alert('Dane: ' + json);
              var xhr = new XMLHttpRequest();
              
              
             
              $.post('file.php', {jsonik: json}, function(result) { 
                alert("Zarezerwowano"); 
              });

              var url = "http://localhost:8081/add";  // TUTAJ DAJ LINKA DO PRZYJECIA NAPRAWY
              
              var req = new XMLHttpRequest();
              req.open('GET', url, false);
              req.send(null);

              /*xhr.open("GET", url, true);
              xhr.setRequestHeader("Content-Type", "application/json");
              console.log('Powinno');
              xhr.onreadystatechange = function () {
                  if (xhr.readyState === 4 && xhr.status === 200) {
                    console.log("SUCCES");
                  }else{
                    console.log("ERROR");
                  }
              };
              $data = '';
              xhr.send(data);*/
              
            } else {
              // Do nothing!
              console.log('Thing was not saved to the database.');
            }
            
        },
        selectOverlap: false
    })
    calendar.render();
    

    //Pobieranie JSONA
    function Get(yourUrl){
      var Httpreq = new XMLHttpRequest(); // a new request
      Httpreq.open("GET",yourUrl,false);
      Httpreq.send(null);
      return Httpreq.responseText;          
    }
</script>
<?php
    if($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['zarezerwuj_termin']) == "Zarezerwuj termin"){
        ?>
           
        <?php
    }
?>