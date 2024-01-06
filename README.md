<h1>Schedule App</h1>

Schedule App je aplikacija koja služi korisniku da na lakši način vodi evidenciju o događajima u toku sedmice. Nakon što se registruje, korisnik ima opciju da doda svoj raspored i u njega pohrani bitne stavke za isti. Nakon što to učini, sadržaj koji je unio se prikazuje u vidu naslova rasporeda, vremena početka događaja, kao i naziva samog događaja. U slučaju da korisnik nije registrovan, sa glavne stranice ga na stranicu za registraciju vodi link. Nakon uspješne registracije, nema potrebe za ponovnim log in-om. Također ukoliko korisnik ipak ima profil, može se istim linkom vratiti na log in dio. Ukoliko je nepostojeći username, prilikom log in-a, ako se korisnik odluči za registraciju, on se prenosi skupa sa password-om, ako je unesen, u formu za registraciju. Ne postoji ograničenje za podatke korisnika osim toga što korisnik mora imati jedinstven username, minimalno 5 znakova dužine, kao i password sa 5 znakova dužine. Korisnik može raspored nazvati kako želi, ali bar jedan znak mora biti u nazivu. Prilikom kreiranje stavke rasporeda, on mora unijeti u validnom formatu jedan od radnih dana u sedmici, na osnovu kojeg se sortiraju njegovi sadržaji rasporeda. Također mora unijeti sate u validnom formatu (0-23), minute (0-59), naziv rasporeda i lokacije nemaju restrikcija osim što ne mogu biti prazan string. Aplikacija će biti implementirana u Java programskom jeziku, koristeći MySql bazu podataka. Backend dio aplikacije će biti iskorišten za idući projekat koji će biti full stack aplikacija pisana u Reactu na frontendu, Java na backendu i sa MySQL bazom podataka.

//button za switch
//na trenutnom rasporedu novi raspored

//na vrhu liste dugmica za raspored
//sortira 
