# 🧾 Roadmap: Verordnungsmodul mit Termin- und Einheitenlogik

---

# 🎯 Ziel

Aufbau eines Verordnungsmoduls für eine Physiotherapiepraxis, das Heilmittelverordnungen strukturiert erfasst und fachlich sauber mit Terminen, Behandlungseinheiten und späteren Behandlungskontakten verknüpft.

Der Schwerpunkt liegt bewusst auf:
- manueller strukturierter Erfassung
- fachlicher Validierung
- Einheitensteuerung
- sauberer Verknüpfung mit Scheduling
- Erweiterbarkeit Richtung KIS

---

# 🧠 Produktidee

Die Verordnung ist nicht nur ein Dokument, sondern ein fachlich steuerndes Objekt.

Sie definiert unter anderem:
- welche Leistung erbracht werden darf
- wie viele Einheiten zulässig sind
- in welchem Zeitraum behandelt werden darf
- in welcher Frequenz Termine stattfinden sollen
- welche Zusatzbedingungen gelten

Das System soll daher nicht einfach Formulare speichern, sondern aus der Verordnung einen steuerbaren Behandlungsrahmen machen.

---

# 🧱 Fachlicher Kern

## Zentrale Rolle der Heilmittelverordnung
Die Heilmittelverordnung bildet die Grundlage für:
- Terminplanung
- Einheitenverbrauch
- fachliche Plausibilitätsprüfung
- spätere Dokumentation
- potenzielle Abrechnungsvorbereitung

## Grundprinzip
Nicht das gescannte Papier ist die Wahrheit, sondern die **strukturierte, bestätigte Erfassung im System**.

---

# 📦 Scope des Moduls

Das Verordnungsmodul soll folgende Aufgaben erfüllen:

- Verordnungen manuell erfassen
- Verordnungen einem Patienten zuordnen
- verordnete Leistung und Rahmenbedingungen speichern
- verfügbare und verbrauchte Einheiten verwalten
- Termine mit einer Verordnung verknüpfen
- Regelverletzungen erkennen
- Transparenz über offene, geplante und verbrauchte Einheiten schaffen

---

# 🧭 Leitprinzipien

## 1. Manuelle Erfassung vor OCR
Zuerst strukturierte Eingabe durch den Nutzer

## 2. Verordnung steuert Termine
Termine sind nicht frei schwebend, sondern an die Verordnung gekoppelt

## 3. Einheiten müssen sichtbar sein
Jederzeit muss klar sein:
- wie viele Einheiten genehmigt wurden
- wie viele verplant sind
- wie viele durchgeführt wurden
- wie viele noch offen sind

## 4. Planung und Durchführung trennen
Geplante Termine sind nicht automatisch verbrauchte Einheiten

## 5. Fachregeln explizit modellieren
Keine versteckten Regeln in UI oder Datenbank

---

# 📅 ROADMAP

---

## 🟢 Phase 1 – Verordnungserfassung als strukturierte Maske

### Ziel
Die Heilmittelverordnung wird nicht als Dokumentenanhang, sondern als strukturiertes Fachobjekt erfasst.

### Inhalte
- Patient auswählen
- Verordnung anlegen
- Ausstellungsdatum erfassen
- Gültigkeitszeitraum erfassen
- Leistungstyp erfassen
- Anzahl der verordneten Einheiten erfassen
- Frequenz erfassen
- Zusatzangaben erfassen
- optionale Bemerkungen erfassen

### Fachregeln
- Verordnung muss einem Patienten zugeordnet sein
- zentrale Pflichtfelder müssen vollständig sein
- Anzahl Einheiten muss positiv sein
- Leistungstyp muss eindeutig sein
- Gültigkeitszeitraum darf nicht widersprüchlich sein

### Ergebnis
Eine fachlich brauchbare, valide erfasste Verordnung

---

## 🟢 Phase 2 – Verordnungsstatus und Lebenszyklus

### Ziel
Verordnungen erhalten einen klaren Status und werden steuerbar.

### Inhalte
- Entwurf
- aktiv
- abgelaufen
- abgeschlossen
- storniert

### Fachregeln
- nur aktive Verordnungen dürfen Termine steuern
- abgelaufene Verordnungen dürfen keine neuen Termine mehr zulassen
- abgeschlossene Verordnungen sind fachlich beendet
- stornierte Verordnungen dürfen keine Planung mehr tragen

### Ergebnis
Verordnungen sind nicht nur Datensätze, sondern fachlich aktive Objekte mit Lebenszyklus

---

## 🟡 Phase 3 – Einheitenkonto einführen

### Ziel
Die Verordnung wird in ein nachvollziehbares Kontingentmodell überführt.

### Inhalte
- Gesamtanzahl genehmigter Einheiten
- verplante Einheiten
- durchgeführte Einheiten
- offene Einheiten
- stornierte oder verfallene Einheiten

### Fachregeln
- verplante Einheiten dürfen die genehmigte Gesamtmenge nicht überschreiten
- durchgeführte Einheiten dürfen nur aus gültigen Planungen entstehen
- offene Einheiten müssen jederzeit nachvollziehbar sein
- Stornierungen dürfen fachlich nicht automatisch als durchgeführt zählen

### Ergebnis
Klare Transparenz über Verbrauch und Restkontingent

---

## 🟡 Phase 4 – Verknüpfung mit Terminen

### Ziel
Jeder relevante Termin wird mit einer konkreten Verordnung verbunden.

### Inhalte
- Termin beim Buchen einer Verordnung zuordnen
- Zuordnung prüfen
- Übersicht aller Termine je Verordnung
- Anzeige geplanter Einheiten pro Verordnung

### Fachregeln
- ein verordnungsgebundener Termin muss auf eine gültige Verordnung referenzieren
- Termine dürfen nicht mehr Einheiten verbrauchen als zulässig
- Termine außerhalb des Gültigkeitszeitraums müssen blockiert oder markiert werden
- nicht jeder Termin muss zwingend verordnungsgebunden sein, aber die Fachregel muss explizit sein

### Ergebnis
Planung ist direkt an die Verordnung gekoppelt

---

## 🔵 Phase 5 – Frequenz- und Zeitlogik

### Ziel
Die Terminplanung soll nicht nur mengenmäßig, sondern auch zeitlich zur Verordnung passen.

### Inhalte
- Frequenz prüfen
- zeitlichen Abstand zwischen Terminen prüfen
- Gültigkeitsfenster berücksichtigen
- Terminserien fachlich plausibilisieren

### Fachregeln
- Termine sollen zur vorgegebenen Behandlungsfrequenz passen
- Verletzungen der Frequenz müssen erkannt werden
- Termine nach Ablauf der Verordnung sind nicht regulär zulässig
- das System sollte zwischen harter Sperre und Warnung unterscheiden können

### Ergebnis
Die Verordnung steuert nicht nur die Menge, sondern auch den Rhythmus der Behandlung

---

## 🔵 Phase 6 – Planung vs. Durchführung sauber trennen

### Ziel
Geplante Einheiten und tatsächlich erbrachte Einheiten werden fachlich sauber auseinandergehalten.

### Inhalte
- Termin geplant
- Termin abgesagt
- Termin No-Show
- Termin durchgeführt
- daraus resultierender Einheitenstatus

### Fachregeln
- eine geplante Einheit ist noch nicht automatisch verbraucht
- eine durchgeführte Behandlung verbraucht fachlich eine Einheit
- Absagen und No-Shows benötigen klare fachliche Behandlung
- es muss nachvollziehbar sein, warum eine Einheit offen, reserviert oder verbraucht ist

### Ergebnis
Das System bildet reale Behandlungssituationen sauber ab

---

## 🔴 Phase 7 – Verordnungsübersicht und Arbeitsansichten

### Ziel
Nutzer sollen fachlich relevante Informationen schnell sehen können.

### Inhalte
- Verordnungsübersicht je Patient
- Statusübersicht
- Übersicht offener Einheiten
- Übersicht geplanter Termine
- Warnhinweise bei bald ablaufenden Verordnungen
- Kennzeichnung kritischer Fälle

### Fachregeln
- offene Einheiten müssen sichtbar sein
- Verordnungen mit Konflikten müssen hervorgehoben werden
- Nutzer sollen erkennen können, welche Verordnung aktiv planbar ist

### Ergebnis
Hohe Transparenz für Rezeption und Behandler

---

## 🔴 Phase 8 – Validierungen und Warnsystem

### Ziel
Das System erkennt fachliche Risiken frühzeitig.

### Inhalte
- Pflichtfeldprüfung
- Ablaufwarnung
- Frequenzwarnung
- Mengenkonflikte
- doppelte oder widersprüchliche Verordnungen
- unzugeordnete Termine

### Fachregeln
- nicht jede Unstimmigkeit muss blockieren
- manche Verstöße sind Warnungen, andere harte Fehler
- Validierungen müssen nachvollziehbar sein

### Ergebnis
Mehr Sicherheit und weniger Planungsfehler

---

## 🟣 Phase 9 – Historie und Nachvollziehbarkeit

### Ziel
Änderungen an Verordnungen und deren Nutzung müssen transparent bleiben.

### Inhalte
- Änderungsverlauf
- Statusänderungen
- Änderungen an Einheiten
- Änderungen an Verknüpfungen mit Terminen
- Benutzerbezug

### Fachregeln
- relevante Änderungen dürfen nicht spurlos überschrieben werden
- Planung und Verbrauch müssen historisch nachvollziehbar bleiben
- Benutzerkontext ist wichtig

### Ergebnis
Das Modul wird revisionsnäher und produktionsfähiger

---

## ⚫ Phase 10 – Erweiterung Richtung KIS

### Ziel
Das Verordnungsmodul wird Teil eines größeren medizinischen Domänenkerns.

### Inhalte
- Verknüpfung mit Case
- Verknüpfung mit Encounter
- klinische Dokumentation je durchgeführter Einheit
- Vorbereitung auf Leistungs- und Abrechnungslogik
- optionale Integration weiterer Dokumenttypen

### Fachregeln
- Verordnung ist Teil eines größeren Behandlungskontexts
- nicht nur Planung, sondern Verlauf und Dokumentation müssen anschließbar sein

### Ergebnis
Das Modul wird vom Praxisfeature zum Baustein eines Gesundheitsinformationssystems

---

# 🔁 Empfohlene Implementierungsreihenfolge

1. Verordnung manuell erfassen
2. Statusmodell einführen
3. Einheitenkonto modellieren
4. Termine an Verordnung koppeln
5. Frequenz- und Zeitlogik ergänzen
6. Durchführung von Planung trennen
7. Arbeitsansichten bauen
8. Warnungen und Validierung erweitern
9. Historie ergänzen
10. an Case und Encounter anbinden

---

# 📊 Zentrale Sichten im System

## Für die Rezeption
- aktive Verordnungen
- offene Einheiten
- planbare Zeiträume
- Warnungen bei Ablauf
- Terminzuordnung

## Für Behandler
- verknüpfte Verordnung je Termin
- verbleibende Einheiten
- fachlicher Kontext der Behandlung

## Für Administration
- Vollständigkeit
- Nachvollziehbarkeit
- Status
- Historie

---

# 🚫 Was vermieden werden sollte

- Verordnung nur als PDF-Anhang speichern
- Termine ohne Bezug zur Verordnung planen
- Einheiten nur indirekt aus Terminen erraten
- keine Trennung zwischen geplant und durchgeführt
- keine Statuslogik
- keine Sicht auf Restkontingent
- keine Ablauf- oder Konfliktwarnungen

---

# 🧠 Fachliche Leitfrage

> Wie wird aus einer Heilmittelverordnung ein steuerbarer Behandlungsrahmen, der Planung, Durchführung und Nachvollziehbarkeit sauber miteinander verbindet?

---

# 🚀 Endziel

Ein Verordnungsmodul, das:
- strukturiert statt dokumentenzentriert arbeitet
- Einheiten transparent verwaltet
- Termine fachlich korrekt bindet
- spätere Behandlungskontakte anschließen kann
- Richtung KIS erweiterbar bleibt