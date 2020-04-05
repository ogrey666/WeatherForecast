package com.example.kurs.SerwisKlienckiPogodynkowy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SerwisKlienckiPogodynkowyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SerwisKlienckiPogodynkowyApplication.class, args);
	}

}

/*
Serwis kliencki:
	Przygotujcie nowy projekt spring boot. Dodajcie do niego zależność WEB.
	W Projekcie utwórzcie 3 pakiety, config, controller i service oraz repozytory.
	W configu utwórzcie klasę konfiguracyjną, która będzie ustawiać beany, np RestTemplate czy WetherGateway.
	W pakiecie controller utwórzcie endpoint WetherForecast.
	Ma on za zadanie przyjmować zapytania o pogodę, zapytania można parametryzować opcjonalnymi query paramami, aura i region, w zależności od nich lub od ich braku zostanie zwrócona prognoza pogody losowa, dla regionu, lub dla regionu i aury. Prognoza ma być dla człowieka, tak więc musi być ubrana w ładne słowa i opisy.
	W pakiecie service, tworzymy WeatherForecastService, który pobiera obiekt prognozy pogody z Gateway-a, i przerabia go na String zwracany przez kontroler - także ładny i czytelny dla człowieka.
	W pakiecie gateway, tworzymy ForecastGateway który łączy się z serwerem pogodowym za pomocą restTemplate (w moim kodzie, który wczoraj umieściłem jest przykład jak to wykonać) i zwraca pobrane informacje do serwisu pogodowego.
	Nie zapomnijcie o beanach/komponentach zarządzanych przez kontener.
	Pamiętajcie że jeśli chcecie aby obiekty transferowe (dto do przesyłania prognozy pomiędzy serwerami) były bezproblemowe, nie dodawajcie do nich konstruktorów, budujcie je za pomocą bezargumentowego, natywnego konstruktora i ustawiajcie setterami. Jeśli chcecie skorzystać z konstruktora, wczoraj opublikowałem kod który wyjaśnia jak to zrobić.
 */