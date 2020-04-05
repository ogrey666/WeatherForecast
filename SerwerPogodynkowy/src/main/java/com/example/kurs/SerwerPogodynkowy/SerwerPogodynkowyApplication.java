package com.example.kurs.SerwerPogodynkowy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SerwerPogodynkowyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SerwerPogodynkowyApplication.class, args);
	}

}

/*
//TODO brak obsługi poskich znaków z application.properties!
Serwer Pogodynkowy:
	Przygotujcie nowy projekt spring boot. Dodajcie do niego zależność WEB.
	W Projekcie utwórzcie 3 pakiety, config, controller i service.
	W configu utwórzcie klasę konfiguracyjną, która będzie ustawiać beany, np Serwisu pogodynki.
	W pakiecie controller utwórzcie endpoint WetherForecast.
	Ma on za zadanie przyjmować zapytania o pogodę, zapytania można parametryzować opcjonalnymi query paramami, aura i region, w zależności od nich lub od ich braku zostanie zwrócona prognoza pogody losowa, dla regionu, lub dla regionu i aury. Musi zwracać pliki json (obiekty złożone które w przeglądarce wyświetlą się jako plik json, nie prosty string)
	Kontroler ma mieć wstrzyknięty interfejs Serwisu pogodowego
	W Serwisie utwórzcie Klasę Pogodynk/Forecaster która będzie implementowała w/w interfejs i zwracała prognozę pogody zgodą z podanymi parametrami. Nie zapomnijcie jej opublikować jako beana/komponent.
 */