# WeatherScraperApi
<h1>Webscraping project</h1>

<h3>EER-Diagram</h3>
<h4>Tabeller:</h4>

<h6>1. 'weather'</h6>
<p>Denne tabel indeholder information om vejret for en given dato.</p>
<ul>
    <li><strong>id</strong>: Et unikt ID for hver række. Dette felt har en auto-increment værdi baseret på sekvensen 'weather_id_seq'.</li>
    <li><strong>date</strong>: Datoen for vejrdataene.</li>
    <li><strong>max_temperature</strong>: Den højeste temperatur målt på den pågældende dag.</li>
    <li><strong>min_temperature</strong>: Den laveste temperatur målt på den pågældende dag.</li>
    <li><strong>rain</strong>: Mængden af nedbør i form af regn.</li>
    <li><strong>wind</strong>: Vindhastigheden.</li>
    <li><strong>weatherdescription_id</strong>: En fremmed nøgle, der forbinder til `weather_description` tabellen for at give detaljerede vejrbeskrivelser.</li>
</ul>

<h6>2. 'weather_description'</h6>
<p>Denne tabel indeholder detaljerede beskrivelser af vejret i forskellige dele af dagen.</p>
<ul>
    <li><strong>id</strong>: Et unikt ID for hver række. Dette felt har en auto-increment værdi baseret på sekvensen 'weather_description_id_seq'.</li>
    <li><strong>afternoon</strong>: Vejrkode for eftermiddagen.</li>
    <li><strong>evening</strong>: Vejrkode for aftenen.</li>
    <li><strong>morning</strong>: Vejrkode for morgenen.</li>
    <li><strong>night</strong>: Vejrkode for natten.</li>
</ul>

<h4>Relationer:</h4>
<p><strong>Fremmednøgle Constraint</strong>: `weather` tabellen har en fremmednøgle `weatherdescription_id`, som henviser til `id` feltet i `weather_description` tabellen. Dette skaber en relation mellem de to tabeller, hvor `weather` tabellen afhænger af `weather_description` for detaljerede vejrbeskrivelser.</p>

<img width="245" alt="Screenshot 2023-09-28 at 14 21 36" src="https://github.com/TobiasTheDanish/WeatherScraperApi/assets/113049401/13653dae-91be-4523-bebf-a19c39a35534">


<h3>Links & API</h3>

<h4>Links</h4>
<p>Vi har valgt at tage udgangspunkt i <a href="https://www.yr.no/en/forecast/daily-table/2-2618425/Denmark/Capital%20Region/Copenhagen/Copenhagen">denne vejr-hjemmeside</a> da den opfyldte de forudsætninger vi havde til en webside, vi gerne ville scrape for data.</p>
<p>Hjemmesiden indholder nogle forskellige vejrsymboler, som bliver tilkoblet de forskellige dage. Vi besluttede os for at bruge en ENUM class til at håndtere disse vejrsymboler. Da hjemmesiden kun viser de aktuelle vejrsymboler de pågældende dage, så kunne vi ikke vide hvor mange forskellige symboler der er implementeret. Der var heldigvis <a href="https://hjelp.yr.no/hc/en-us/articles/203786121-Værsymbolene-på-Yr-">en underside</a> som indeholdte alle deres vejrsymboler, så vi kunne optage alt dataen derfra, så vores ENUM class repræsenterer vejrsymbolerne på nøjagtig vis.</p>
<h5>ENUM class</h5>
<img width="288" alt="Screenshot 2023-09-28 at 14 52 57" src="https://github.com/TobiasTheDanish/WeatherScraperApi/assets/113049401/8b82acf5-8ea4-4302-884c-ab7ed415ebca">

<h4>API</h4>
<p>Det var problematisk at tilgå vores valgte hjemmesides api, så vi valgte derfor at gå med den vi fik tildelt: <a href="https://vejr.eu/api.php?location=K%C3%B8benhavn&degree=C">Vejrudsigten for København.</a> Vi har valgt at bruge api'en til at dokumentere vejret lige nu, og så bruger vi Jsoup til at scrape den kommende vejrudsigt</p>

<h3>Edge cases, fejlhåndtering & potentielle forbedringer</h3>
<p>Vi stødte på en 'problem', der gjorde at databasen fyldt op med identisk data hver gang vi kørte vores program. Så vi implementeret en metode, som tjekker om alle entiteternes dato matcher med en dato nede i databasen. Hvis datoerne matcher, så returnerer metoden 'true' og så bliver rækken i tabellen overskrevet i stedet for. På den måde slipper vi for dubletter.</p>
<p>En potentiel forbedring kunne være at benytte Yr's egen API, så projektet udelukkende tog udgangspunkt i deres hjemmeside.</p>
