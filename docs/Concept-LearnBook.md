# Lern-Workbook: Java für ein Physio-System → KIS-Kern

## Worum es in diesem Workbook geht

Dieses Workbook baut nicht einfach eine Demo-App.  
Es führt dich Schritt für Schritt durch die Java-Konzepte, die du brauchst, um ein fachlich sauberes Physio-System aufzubauen, das später Richtung KIS wachsen kann.

Zielbild:
- Terminplanung mit echter Zeitlogik
- Heilmittelverordnung als steuerndes Fachobjekt
- Einheitenverwaltung
- Trennung von Planung und Durchführung
- Fall- und Dokumentationslogik
- spätere Erweiterung um Persistenz, API und Rechte

---

# 1. Wie du mit diesem Workbook arbeitest

Jede Phase enthält immer dieselben Bausteine:

1. **Projektkontext**  
   Warum brauchst du das Thema im Physio-System?

2. **Java-Konzepte**  
   Was musst du sprachlich und technisch verstehen?

3. **Was du aus der offiziellen Doku lernen sollst**  
   Nicht „alles lesen“, sondern gezielt.

4. **Verständnisfragen**  
   Woran du merkst, dass du das Thema wirklich verstanden hast.

5. **Projektaufgaben**  
   Was du in deinem eigenen Projekt umsetzen oder modellieren solltest.

---

# 2. Offizielle Java-Quellen, die du wirklich brauchst

Diese Quellen sind für dieses Workbook die wichtigste Basis:

## Einstieg und Sprache
- dev.java: Learn Java  
  https://dev.java/learn/

- dev.java: Classes and Objects  
  https://dev.java/learn/classes-objects/

- dev.java: Objects, Classes, Interfaces, Packages, and Inheritance  
  https://dev.java/learn/oop/

## Generics und Typsystem
- dev.java: Generics  
  https://dev.java/learn/generics/

- dev.java: Introducing Generics  
  https://dev.java/learn/introducing-generics/

- dev.java: Type Erasure  
  https://dev.java/learn/generics/type-erasure/

- dev.java: Restrictions on Generics  
  https://dev.java/learn/restriction-on-generics/

## Collections
- dev.java: The Collections Framework  
  https://dev.java/learn/api/collections-framework/

- dev.java: Choosing your Implementation of the List Interface  
  https://dev.java/learn/api/collections-framework/lists/

- Oracle JDK API / Core Libraries: Java Collections Framework  
  https://docs.oracle.com/en/java/javase/25/core/java-collections-framework.html

- Oracle API: Collection  
  https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/Collection.html

## Date and Time
- Oracle Java Tutorials: Date-Time  
  https://docs.oracle.com/javase/tutorial/datetime/

## Records / Immutable Data
- dev.java: Using Records to Model Immutable Data  
  https://dev.java/learn/using-record-to-model-immutable-data/

## Streams
- dev.java: Processing Data in Memory Using the Stream API  
  https://dev.java/learn/api/streams/map-filter-reduce/

## JDBC
- Oracle Java Tutorials: JDBC  
  https://docs.oracle.com/javase/tutorial/jdbc/

## JavaFX (optional)
- dev.java: JavaFX Fundamentals  
  https://dev.java/learn/javafx/

## Spring
- Spring Guides  
  https://spring.io/guides

---

# 3. Phase 1 – Domäne denken, bevor du Code schreibst

## Projektkontext

Bevor du an Listen, Datenbanken oder APIs denkst, musst du die Fachwelt in Begriffe zerlegen.

Für dein Projekt sind zentrale Begriffe zum Beispiel:
- Patient
- Practitioner
- Appointment
- ServiceType
- Location
- Prescription
- Encounter
- Case

Wenn du diese Begriffe unsauber denkst, hilft dir auch gutes Java nicht.

## Java-Konzepte

### 3.1 Klassen und Objekte

Du musst verstehen:
- Was ist eine Klasse?
- Was ist eine Instanz?
- Welche Daten gehören in ein Objekt?
- Welche Regeln gehören als Verhalten zum Objekt oder zu einem Service?

Fragen, die du dir stellen solltest:
- Ist `Patient` nur ein Datensack oder trägt das Objekt auch Fachverhalten?
- Ist `Appointment` einfach ein Terminblock oder kennt es seinen Status und seine Regeln?

### 3.2 Kapselung

Wichtig ist nicht nur „private Felder schreiben“, sondern:
- Wer darf Daten ändern?
- Wo wird geprüft, ob eine Änderung überhaupt erlaubt ist?

Gerade im medizinischen Kontext ist das wichtig:
- Eine durchgeführte Behandlung darf nicht wie ein Entwurf behandelt werden.
- Eine abgelaufene Verordnung darf nicht einfach neue Termine steuern.

### 3.3 Enums

Du brauchst Enums für klar definierte Zustände:
- AppointmentStatus
- PrescriptionStatus
- EncounterStatus
- Role

Ein Enum ist sinnvoll, wenn eine Menge erlaubter Werte klar begrenzt ist.

## Was du lesen solltest

Pflicht:
- Classes and Objects
- More on Classes
- OOP-Einstieg auf dev.java

## Verständnisfragen

- Kannst du erklären, warum `Patient` und `Appointment` verschiedene fachliche Verantwortungen haben?
- Kannst du begründen, warum Statuswerte als Enum und nicht als freier String modelliert werden sollten?
- Weißt du, wann Verhalten in ein Objekt gehört und wann in einen Service?

## Projektaufgaben

- Liste alle Kernbegriffe deines Systems auf.
- Formuliere zu jedem Begriff:
  - Zweck
  - wichtige Daten
  - wichtige Regeln
- Trenne bewusst:
  - Stammdaten
  - Planung
  - Behandlung
  - Dokumentation

---

# 4. Phase 2 – List<T> wirklich verstehen

## Projektkontext

Sobald du mehrere Patienten, Termine oder Verordnungen verwaltest, brauchst du Datenstrukturen.  
Die erste zentrale Struktur in fast jedem Java-Projekt ist `List<T>`.

Wenn du nur „Listen filtern“ hörst, ist das zu oberflächlich.  
Du musst verstehen:
- Was ist eine `List` überhaupt?
- Warum steht da `<T>`?
- Was unterscheidet `List`, `ArrayList`, `LinkedList`, `Collection`?

## Java-Konzepte

### 4.1 Interface vs. Implementierung

`List` ist ein Interface.  
Das bedeutet:
- Es beschreibt, **was** eine Liste kann.
- Es legt nicht fest, **wie** sie intern umgesetzt ist.

`ArrayList` und `LinkedList` sind konkrete Implementierungen.

Wichtiger Denkpunkt:
- Du programmierst möglichst gegen das Interface (`List`)
- und entscheidest bewusst über die Implementierung (`ArrayList`, `LinkedList`)

Für dein Projekt heißt das:
- Eine Terminliste ist fachlich eine `List`
- Ob du intern `ArrayList` oder etwas anderes nutzt, ist eine technische Entscheidung

### 4.2 Was eine List fachlich bedeutet

Eine Liste ist geeignet, wenn:
- Reihenfolge wichtig ist
- Du Duplikate erlauben willst
- Du Elemente über ihre Position oder Iteration verarbeiten willst

Beispiele im Projekt:
- Termine eines Tages
- Notizen eines Falls
- Verordnungen eines Patienten

Nicht jede Sammlung ist automatisch eine Liste.  
Manchmal ist eine `Map` oder ein `Set` fachlich besser.

### 4.3 Generics: Was bedeutet `List<T>`?

`T` ist ein Typparameter.

Das bedeutet:
- Die Liste ist nicht „irgendeine Liste“
- sondern eine Liste von **genau einem bestimmten Elementtyp**

Beispiele:
- `List<Patient>`
- `List<Appointment>`
- `List<ClinicalNote>`

Das bringt:
- Compile-Time-Typsicherheit
- klarere APIs
- weniger Casts
- weniger Laufzeitfehler

### 4.4 Warum Generics wichtig für dein Projekt sind

Ohne Generics würdest du unsaubere Sammlungen bauen, in denen falsche Objekte landen könnten.

In einem System mit:
- Patienten
- Verordnungen
- Terminen
- Encountern
- Rollen

ist Typsicherheit entscheidend.

Du willst fachlich verhindern, dass:
- ein Patient dort landet, wo Termine erwartet werden
- ein String eine Rolle ersetzt
- unklare „Objektlisten“ entstehen

### 4.5 Typinferenz

Du solltest verstehen, dass Java an vielen Stellen Typen herleiten kann.  
Das macht Code lesbarer, aber nur, wenn du die zugrunde liegenden Typen wirklich verstehst.

### 4.6 Type Erasure

Das ist für den Einstieg kein Alltagsthema, aber wichtig für dein mentales Modell:

Generics sind in Java weitgehend ein Compile-Time-Konzept.  
Der Compiler entfernt bestimmte Generic-Informationen bei der Übersetzung („type erasure“).

Du musst nicht alles dazu sofort meistern, aber wissen:
- Generics sind nicht dasselbe wie Templates in C++
- zur Laufzeit ist manches weniger sichtbar, als es im Quellcode aussieht

### 4.7 Einschränkungen bei Generics

Wichtige Punkte:
- primitive Typen kannst du nicht direkt als Typargument verwenden
- es gibt Einschränkungen bei Arrays von generischen Typen
- statische Kontexte und Typparameter haben Grenzen

## Was du lesen solltest

Pflicht:
- Generics
- Introducing Generics
- Type Erasure
- Restrictions on Generics
- Collections Framework
- Choosing your Implementation of the List Interface

## Verständnisfragen

- Kannst du den Unterschied zwischen `List` und `ArrayList` erklären?
- Weißt du, warum `List<Appointment>` besser ist als eine rohe Liste?
- Kannst du erklären, warum Generics Compile-Time-Sicherheit erhöhen?
- Weißt du, wann eine Liste fachlich ungeeignet wäre?

## Projektaufgaben

- Definiere, welche Sammlungen in deinem Projekt Listen sein sollen.
- Begründe, wo eine `Map` sinnvoller wäre als eine `List`.
- Beschreibe für mindestens 5 Felder im Domänenmodell, warum dort eine Sammlung gebraucht wird oder nicht.

---

# 5. Phase 3 – Collections Framework systematisch lernen

## Projektkontext

Sobald dein Projekt wächst, reicht „ich habe irgendwo Listen“ nicht mehr.  
Du musst entscheiden, welche Collection fachlich passt.

## Java-Konzepte

### 5.1 Collection-Hierarchie

Du solltest das Grundbild verstehen:
- `Collection`
- `List`
- `Set`
- `Map` (gehört fachlich dazu, ist aber nicht Untertyp von `Collection`)

### 5.2 Wann du was brauchst

#### List
Wenn Reihenfolge wichtig ist und Duplikate möglich sind.

#### Set
Wenn Duplikate fachlich nicht erlaubt sein sollen.

#### Map
Wenn du etwas über einen Schlüssel schnell finden willst.

Beispiele:
- Patient nach interner ID
- ICD-Code nach Codewert
- Verfügbarkeit nach Wochentag oder Resource-ID

### 5.3 Mutierbar vs. unveränderlich

Du solltest früh lernen:
- Muss jede Liste veränderbar sein?
- Oder ist eine unveränderliche Sicht manchmal besser?

Das schützt vor Seiteneffekten.

## Was du lesen solltest

Pflicht:
- Collections Framework (dev.java)
- Oracle Collections Framework Overview
- API-Doku zu `Collection`

Optional später:
- Collections Factory Methods

## Verständnisfragen

- Wann ist `Set` besser als `List`?
- Warum ist `Map` für Suche oft passender?
- Warum kann Unveränderlichkeit in Fachlogik hilfreich sein?

## Projektaufgaben

- Lege für folgende Dinge fachlich passende Sammlungen fest:
  - aktive Verordnungen eines Patienten
  - tägliche Termine eines Practitioners
  - ICD-Codes nach Code
  - Rollen eines Benutzers

---

# 6. Phase 4 – Zeitlogik mit der Date-Time-API

## Projektkontext

Ein Physio-System lebt von Zeitlogik:
- Termine
- Gültigkeitszeiträume
- Quartale
- Behandlungsfrequenzen
- abgelaufene Verordnungen
- Tagesansichten

Wenn du hier unsauber arbeitest, fällt das System fachlich auseinander.

## Java-Konzepte

Du musst die zentrale Java-Zeit-API verstehen:
- `LocalDate`
- `LocalTime`
- `LocalDateTime`
- `Duration`
- ggf. später `Period`

### 6.1 Wann welcher Typ sinnvoll ist

#### LocalDate
Für Daten ohne Uhrzeit:
- Ausstellungsdatum
- Geburtsdatum
- Gültig bis

#### LocalTime
Für reine Uhrzeiten:
- Öffnungszeit
- Arbeitsbeginn
- Arbeitsende

#### LocalDateTime
Für konkrete Termine:
- Behandlung am 2026-04-10 um 09:30

#### Duration
Für Dauer:
- 20 Minuten
- 30 Minuten
- 60 Minuten

### 6.2 Zeitlogik sauber denken

Fachlich wichtige Fragen:
- Überschneiden sich zwei Termine?
- Ist eine Verordnung am Termin noch gültig?
- Liegt ein Termin in einem Quartal?
- Passt die Frequenz zur Verordnung?

### 6.3 Quartal als Auswertung, nicht als Domänenkern

Wichtige Design-Regel:
- Verordnung ist nicht „pro Quartal“
- Quartal ist eine Sicht auf Termine und Verläufe

## Was du lesen solltest

Pflicht:
- Oracle Date-Time Tutorials

## Verständnisfragen

- Kannst du begründen, warum `LocalDateTime` für einen Termin, aber nicht für ein Geburtsdatum sinnvoll ist?
- Weißt du, wie du Fachregeln mit Zeiträumen statt mit Strings denkst?
- Kannst du fachlich erklären, warum Quartal keine Kerneigenschaft einer Verordnung sein sollte?

## Projektaufgaben

- Beschreibe alle Zeitfelder deines Systems.
- Ordne jedem Feld den passenden Java-Zeittyp zu.
- Erkläre für jedes Feld, warum der Typ fachlich passt.

---

# 7. Phase 5 – Verordnung als Fachobjekt

## Projektkontext

Die Heilmittelverordnung ist kein Anhang und kein „Dokument zum Hochladen“.  
Sie steuert:
- Behandlungsart
- Menge
- Zeitraum
- Frequenz
- Planbarkeit

## Java-Konzepte

### 7.1 Value Objects

Für manche fachlichen Dinge sind kleine, klar definierte Typen sinnvoll:
- Frequenz
- Zeitraum
- Einheitenzahl
- Diagnosecode
- Verordnungsstatus

Nicht alles muss ein primitiver Typ bleiben.

### 7.2 Validierungslogik

Du brauchst Regeln wie:
- Einheiten > 0
- Gültigkeitszeitraum ist sinnvoll
- Leistungstyp ist gesetzt
- nur aktive Verordnungen sind planbar

### 7.3 Immutability

Gerade bei fachlich sensiblen Daten ist Unveränderlichkeit oft hilfreich.  
Hier solltest du auch Records als Werkzeug kennenlernen.

## Was du lesen solltest

Pflicht:
- Using Records to Model Immutable Data
- Classes and Objects
- Generics (noch einmal im Hinblick auf typsichere Modellierung)

## Verständnisfragen

- Warum ist eine Verordnung mehr als ein Datensatz?
- Welche Teile einer Verordnung sind fachlich unveränderlicher als andere?
- Wann hilft dir ein Value Object mehr als ein primitiver Typ?

## Projektaufgaben

- Zerlege die Verordnung in eigenständige fachliche Bestandteile.
- Markiere, welche Teile Stammdaten, welche Zustände und welche Regeln sind.
- Entscheide, welche Teilaspekte als Value Objects gedacht werden sollten.

---

# 8. Phase 6 – Zustände modellieren

## Projektkontext

In deinem Projekt gibt es sehr viele Zustände:
- Termin geplant / bestätigt / abgesagt / No-Show / durchgeführt
- Verordnung Entwurf / aktiv / abgelaufen / abgeschlossen / storniert
- Fall offen / pausiert / abgeschlossen

Wenn du das nicht explizit modellierst, entsteht Chaos.

## Java-Konzepte

### 8.1 Zustandsmodell

Nicht jeder beliebige Übergang ist erlaubt.

Zum Beispiel:
- Von „abgeschlossen“ direkt zurück zu „Entwurf“ wäre oft fachlich unsinnig
- Eine durchgeführte Behandlung darf nicht wie ein ungeplanter Termin behandelt werden

### 8.2 Enum plus Regeln

Ein Enum allein reicht oft nicht.  
Du musst zusätzlich die erlaubten Übergänge und Konsequenzen denken.

## Was du lesen solltest

Pflicht:
- Enums im Bereich Classes and Objects
- OOP-Grundlagen
- bei Bedarf Inheritance / Polymorphism nur dort, wo es wirklich hilft

## Verständnisfragen

- Warum reicht ein String-Feld für Status nicht?
- Welche Statusübergänge sind in deinem Projekt kritisch?
- Wo brauchst du harte Fehler, wo nur Warnungen?

## Projektaufgaben

- Erstelle ein Zustandsmodell für:
  - Appointment
  - Prescription
  - Encounter
  - Case
- Formuliere erlaubte und verbotene Übergänge.

---

# 9. Phase 7 – Von Planung zu Durchführung: Appointment vs. Encounter

## Projektkontext

Ein Termin ist nicht automatisch eine Behandlung.  
Das ist eine der wichtigsten fachlichen Trennungen.

Appointment = Planung  
Encounter = tatsächliche Durchführung

## Java-Konzepte

### 9.1 Unterschiedliche Verantwortlichkeiten modellieren

Du musst erkennen:
- Welche Daten gehören zur Planung?
- Welche Daten entstehen erst bei tatsächlicher Durchführung?

### 9.2 Objektbeziehungen

Ein Encounter kann aus einem Appointment hervorgehen, ist aber nicht dasselbe Objekt mit anderem Status.

## Verständnisfragen

- Warum ist „durchgeführt“ nicht einfach nur ein Terminstatus?
- Welche Informationen entstehen erst im Encounter?

## Projektaufgaben

- Trenne Planung und Durchführung in deinem Fachmodell.
- Formuliere, welche Informationen nur im Encounter existieren dürfen.

---

# 10. Phase 8 – Fallmanagement

## Projektkontext

Mehrere Verordnungen, Termine und Behandlungen können zu einem Behandlungsfall gehören.

## Java-Konzepte

### 10.1 Beziehungen und Aggregation

Du musst verstehen:
- Welche Objekte gehören fachlich zusammen?
- Welche Objekte referenzieren sich nur lose?
- Wo liegt der fachliche Mittelpunkt?

## Verständnisfragen

- Warum ist ein Fall mehr als eine Liste von Terminen?
- Wie verhält sich ein Case zu Verordnung und Encounter?

## Projektaufgaben

- Definiere, was ein Case in deinem Projekt fachlich bedeutet.
- Bestimme, welche Objekte daran hängen.

---

# 11. Phase 9 – Daten verarbeiten: Filtern, Suchen, Gruppieren

## Projektkontext

Jetzt kommt der Punkt, den viele zu schnell mit „Listen filtern“ abtun.  
Eigentlich geht es um:
- Daten selektieren
- Sichten erzeugen
- Berichte bauen
- Konflikte finden

## Java-Konzepte

### 11.1 Iteration und Verarbeitung von Collections

Du musst sicher sein in:
- Iteration
- Selektion
- Gruppierung
- Sortierung

### 11.2 Stream API

Streams sind nicht nur „modern“, sondern nützlich für:
- Tagespläne
- offene Einheiten
- ablaufende Verordnungen
- Termine eines Practitioners
- Filter nach Zeitraum oder Status

Wichtig:
Erst Collections verstehen, dann Streams.

## Was du lesen solltest

Pflicht:
- Processing Data in Memory Using the Stream API

## Verständnisfragen

- Wann ist eine einfache Schleife klarer als ein Stream?
- Wann hilft dir ein Stream, fachliche Sichten kompakt auszudrücken?
- Kannst du fachliche Filter beschreiben, bevor du an Syntax denkst?

## Projektaufgaben

Definiere fachliche Sichten wie:
- alle aktiven Verordnungen
- alle Termine eines Tages
- alle offenen Einheiten pro Patient
- alle Verordnungen, die bald ablaufen
- alle Encounters eines Falls

---

# 12. Phase 10 – ICD-Subset und fachliche Codes

## Projektkontext

Du willst nicht den kompletten ICD-Katalog, sondern ein fachlich relevantes Subset.

## Java-Konzepte

### 12.1 Strukturierte Stammdaten

Hier brauchst du:
- klare Modellierung
- Suche
- Gruppierung
- typisierte Codes

### 12.2 Map als Nachschlage-Struktur

Ein ICD-Code ist ein sehr gutes Beispiel für eine schlüsselbasierte Suche.

## Verständnisfragen

- Warum ist ein vollständiger Katalog oft schlechter als ein relevanter Subset?
- Warum sind Codes nicht dasselbe wie Freitextdiagnosen?

## Projektaufgaben

- Definiere deine Diagnosegruppen:
  - Muskel-Skelett
  - Trauma
  - Neurologie
  - Atemtherapie
  - Kardiologie
- Lege fest, welche davon im MVP gebraucht werden.

---

# 13. Phase 11 – Dokumentation und strukturierte Daten

## Projektkontext

Spätestens mit Encountern brauchst du Dokumentation:
- Notizen
- Befunde
- Maßnahmen
- Verlauf

## Java-Konzepte

### 13.1 Records und Value Objects

Viele dokumentationsnahe Daten sind gut als kleine, klare Datentypen denkbar.

### 13.2 Trennung zwischen freiem Text und Struktur

Nicht alles sollte Freitext sein.  
Manches muss strukturiert sein, damit man es später auswerten kann.

## Verständnisfragen

- Welche Informationen in deinem System brauchen Struktur?
- Welche dürfen bewusst Freitext bleiben?

## Projektaufgaben

- Teile deine Dokumentation in:
  - strukturierte Felder
  - Freitext
  - Verlaufseinträge

---

# 14. Phase 12 – Persistenz

## Projektkontext

In-Memory reicht nur am Anfang.  
Später brauchst du Speichern, Laden und konsistente Datenhaltung.

## Java-Konzepte

### 14.1 JDBC-Grundlagen

Du musst verstehen:
- Verbindung zur Datenbank
- Abfragen
- Parameter
- Ergebnisse lesen
- Ressourcen sauber behandeln

### 14.2 Später JPA/Hibernate

Erst wenn dein Fachmodell stabil genug ist.

Wichtig:
- Nicht das Datenbankmodell die Domäne diktieren lassen
- Nicht „Entity zuerst“, sondern Fachmodell zuerst

## Was du lesen solltest

Pflicht:
- Oracle JDBC Tutorials

Später:
- Spring Data / JPA Guides

## Verständnisfragen

- Warum ist es gefährlich, zu früh alles um JPA herum zu bauen?
- Wie unterscheidet sich Fachmodell von Persistenzmodell?

## Projektaufgaben

- Trenne in deinem Design:
  - Domäne
  - Repository
  - Infrastruktur

---

# 15. Phase 13 – REST API und Spring

## Projektkontext

Wenn dein System von außen nutzbar werden soll, brauchst du APIs.

## Java-Konzepte

### 15.1 HTTP und REST

Verstehe zuerst die Grundidee:
- Ressourcen
- Requests
- Responses
- Statuscodes

### 15.2 Spring Boot

Spring ist Werkzeug, nicht Architektur.  
Du solltest es erst einsetzen, wenn du fachlich schon sauber denkst.

## Was du lesen solltest

Pflicht:
- Spring Guides

## Verständnisfragen

- Warum darf Businesslogik nicht im Controller landen?
- Welche Use Cases deines Systems eignen sich als API-Endpunkte?

## Projektaufgaben

- Formuliere API-nahe Use Cases:
  - Verordnung anlegen
  - Termin planen
  - Encounter dokumentieren
  - offene Einheiten anzeigen

---

# 16. Phase 14 – Rollen, Rechte und Audit

## Projektkontext

Ein produktionsnahes System braucht:
- Benutzer
- Rollen
- Nachvollziehbarkeit
- Änderungsverlauf

## Java-Konzepte

### 16.1 Trennung von Fachlogik und Zugriffskontrolle

Nicht alles darf überall passieren.

### 16.2 Logging und Nachvollziehbarkeit

Ein Audit-Log ist nicht einfach normales Logging.  
Es geht um fachlich relevante Änderungen.

## Verständnisfragen

- Warum ist „wer hat was geändert?“ fachlich wichtig?
- Welche Aktionen müssen auditierbar sein?

## Projektaufgaben

- Definiere Rollen:
  - Rezeption
  - Therapeut
  - Admin
- Lege fest, welche Aktionen protokolliert werden sollen.

---

# 17. Phase 15 – Architektur: Schichten und Verantwortung

## Projektkontext

Spätestens jetzt musst du lernen, wie man ein wachsendes System strukturiert.

## Java-Konzepte

### 17.1 Separation of Concerns

Trenne:
- Domäne
- Anwendungslogik
- Infrastruktur
- UI / API

### 17.2 Layered Architecture

Eine einfache und gute Startarchitektur:
- Domain Layer
- Application Layer
- Infrastructure Layer
- Interface Layer

### 17.3 Warum das für dein Projekt wichtig ist

Du willst später:
- Datenbank tauschen können
- API ergänzen können
- GUI ergänzen können
- Fachlogik wiederverwenden können

Das geht nur mit sauberer Trennung.

## Verständnisfragen

- Welche Regeln dürfen niemals im UI stecken?
- Welche Klassen sollten keine Datenbank kennen?

## Projektaufgaben

- Zeichne eine Schichtenübersicht für dein Projekt.
- Ordne alle bisherigen Konzepte einer Schicht zu.

---

# 18. Reihenfolge, in der du die Themen lernen solltest

## Zuerst
- Klassen und Objekte
- Kapselung
- Enums
- List, Collection, Interface vs. Implementierung
- Generics
- Date-Time API

## Danach
- Value Objects
- Zustände
- Listen / Maps fachlich bewusst einsetzen
- Streams für fachliche Sichten
- Records

## Dann
- Persistenzgrundlagen
- REST
- Spring Boot
- Rollen / Audit
- Architektur vertiefen

---

# 19. Woran du merkst, dass du Fortschritt machst

Du bist auf einem guten Weg, wenn du:
- fachliche Begriffe sauber trennen kannst
- `List<T>` nicht nur benutzt, sondern begründen kannst
- weißt, wann du `List`, `Set` oder `Map` brauchst
- Generics als Typsicherheitswerkzeug verstehst
- Zeitlogik bewusst modellierst
- Verordnung, Termin und Encounter klar unterscheidest
- Architektur als Schutz für Fachlogik begreifst

---

# 20. Konkreter Lernpfad für die nächsten Schritte

## Lernblock A
- Classes and Objects
- OOP Grundlagen
- Enums
- Kapselung

## Lernblock B
- Collections Framework
- List Interface
- ArrayList vs. LinkedList
- Generics
- Type Erasure grob verstehen

## Lernblock C
- Date-Time API
- fachliche Zeiträume
- Frequenz und Terminlogik

## Lernblock D
- Records
- Value Objects
- Zustände

## Lernblock E
- Streams
- fachliche Filter und Sichten

## Lernblock F
- JDBC
- Persistenzschicht

## Lernblock G
- Spring Boot
- REST
- API-Design

## Lernblock H
- Rollen
- Audit
- Schichtenarchitektur

---

# 21. Abschlussgedanke

Die eigentliche Stärke deines Projekts wird nicht sein, dass es „viel Java“ benutzt.  
Sie wird darin liegen, dass du Java benutzt, um reale Prozesse sauber abzubilden.

Die Leitfrage bleibt:

> Baue ich eine kleine Termin-App – oder einen belastbaren ambulanten Versorgungskern, der später Richtung KIS wachsen kann?
