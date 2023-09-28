# WeatherScraperApi
<h1>Webscraping project</h1>

<h3>EER-Diagram</h3>
<h3>Tabeller:</h3>

<h3>1. 'weather'</h3>
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

<h3>2. 'weather_description'</h3>
<p>Denne tabel indeholder detaljerede beskrivelser af vejret i forskellige dele af dagen.</p>
<ul>
    <li><strong>id</strong>: Et unikt ID for hver række. Dette felt har en auto-increment værdi baseret på sekvensen 'weather_description_id_seq'.</li>
    <li><strong>afternoon</strong>: Vejrkode for eftermiddagen.</li>
    <li><strong>evening</strong>: Vejrkode for aftenen.</li>
    <li><strong>morning</strong>: Vejrkode for morgenen.</li>
    <li><strong>night</strong>: Vejrkode for natten.</li>
</ul>

<h3>Relationer:</h3>
<p><strong>Fremmednøgle Constraint</strong>: `weather` tabellen har en fremmednøgle `weatherdescription_id`, som henviser til `id` feltet i `weather_description` tabellen. Dette skaber en relation mellem de to tabeller, hvor `weather` tabellen afhænger af `weather_description` for detaljerede vejrbeskrivelser.</p>

<img width="245" alt="Screenshot 2023-09-28 at 14 21 36" src="https://github.com/TobiasTheDanish/WeatherScraperApi/assets/113049401/13653dae-91be-4523-bebf-a19c39a35534">
