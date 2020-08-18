# Softverski proces
## Potapanje brodova (čovek protiv računara)

Ovaj projekat se sastoji od klijentskog dela aplikacije (JavaFX aplikacija), koja pomoću soketa i JAX-WS komunicira sa serverskim delom aplikacije.
Zadatak je implementirati popularnu igricu potapanje brodova. Takođe, za komunikaciju korišćene su niti, na klijentskoj strani, postoje dve niti. 
Jedna nit služi za osluškivanje dolaznih poruka, a druga za slanje poruka. Još se koristi kreirana biblioteka koja sadrži domenske klase i klase koje
predstavljaju protokol komunikacije klijenta i servera. 

Od softverskih paterna korišćeni su Inverzija zavisnosti, Inverzija umetanja, Inverzija kontrole, "Template method pattern" i Singleton.
