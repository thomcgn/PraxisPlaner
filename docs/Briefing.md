# 🏥 Projekt: PraxisPlaner
## 📌 Projektziel

Entwicklung eines Systems zur Verwaltung von Patienten, Therapeuten, Ärzten und Terminen, um Doppelbuchungen zu vermeiden und den Praxisalltag zu strukturieren.

---

# 🧠 Ausgangssituation (Kundenbriefing)

Die Physiotherapiepraxis hat:

- 4 Therapeuten
- ca. 80 Patienten pro Woche

Aktuelle Probleme:

- Doppelbuchungen
- falsche Terminlängen
- fehlende Übersicht über Tagespläne
- spontane Änderungen führen zu Chaos

---

# 🎯 MVP (Minimum Viable Product)

Das System muss:

- Patienten verwalten
- Therapeuten verwalten
- Termine buchen
- Doppelbuchungen verhindern
- Tagesplan anzeigen

---

# 🏗️ Projektstruktur (High-Level)

Empfohlene Pakete:

- `model` → Domänenklassen (Patient, Termin, Therapeut)
- `service` → Geschäftslogik
- `repository` → Datenhaltung (später)

---

# 📅 ROADMAP (Sprint-basiert)

---

## 🟢 Sprint 1 – Domänenmodell verstehen & aufbauen

### 🎯 Ziel

Abbildung der realen Welt in Klassen

### 📦 Aufgaben

- Klassen erstellen:
    - `Patient`
    - `Therapeut`
    - `Termin`
- Attribute definieren:
    - Patient: Name, Telefonnummer, Geburtsdatum
    - Therapeut: Name, Spezialisierung
    - Termin: Datum, Uhrzeit, Dauer

### 🧠 Lernziele (Java)

- Klassen & Objekte
- Konstruktoren
- Getter/Setter
- Enums (z. B. für Behandlungstypen)

### ✅ Definition of Done

- Objekte lassen sich erstellen
- Testdaten im `main()` erzeugbar

---

## 🟢 Sprint 2 – Terminbuchung (Kernfunktion)

### 🎯 Ziel

Termine erstellen und verwalten

### 📦 Aufgaben

- Termin erstellen
- Terminliste verwalten (`ArrayList`)
- Termin einem Therapeuten zuweisen

### ⚠️ Regeln

- Termin hat Startzeit + Dauer
- Termin gehört zu genau einem Patienten

### 🧠 Lernziele

- Collections (`List`)
- Methodenstruktur
- Trennung von Logik und Daten

### ✅ Definition of Done

- Termine können erstellt und gespeichert werden
- Ausgabe aller Termine möglich

---

## 🟡 Sprint 3 – Konfliktlogik (REAL WORLD)

### 🎯 Ziel

Doppelbuchungen verhindern

### 📦 Aufgaben

- Prüfen:
    - Überschneiden sich Termine?
    - Ist Therapeut verfügbar?

### 🧠 Logik

Zwei Termine überschneiden sich, wenn:
StartA < EndeB && EndeA > StartB

### 🧠 Lernziele

- Zeitlogik (`LocalDateTime`)
- Vergleichslogik
- Validierung

### ✅ Definition of Done

- System verhindert Konflikte
- Fehlermeldung bei Kollision

---

## 🟡 Sprint 4 – Tagesansicht

### 🎯 Ziel

Übersicht für Praxisalltag

### 📦 Aufgaben

- Termine nach Datum filtern
- Nach Therapeut gruppieren
- Sortieren nach Uhrzeit

### 🧠 Lernziele

- Streams oder Comparator
- Daten filtern und sortieren

### ✅ Definition of Done

- Tagesplan sauber lesbar ausgegeben

---

## 🔵 Sprint 5 – Änderungen & Absagen

### 🎯 Ziel

Realistische Nutzung abbilden

### 📦 Aufgaben

- Termin verschieben
- Termin löschen
- Status hinzufügen (geplant, abgesagt)

### 🧠 Lernziele

- Zustandsmodelle
- Enum für Status
- saubere Update-Logik

### ✅ Definition of Done

- Änderungen funktionieren ohne Inkonsistenzen

---

## 🔴 Sprint 6 – Change Request vom Kunden

### 🧾 Neue Anforderungen

- Behandlungen haben unterschiedliche Dauer:

    - Massage: 30 min
    - Therapie: 60 min
- Therapeuten haben:

    - individuelle Arbeitszeiten
- Neue Funktion:

    - Warteliste für freie Slots

### 🎯 Ziel

Bestehendes System erweitern ohne Chaos

### 🧠 Lernziele

- Refactoring
- Erweiterbarkeit
- OOP sauber nutzen

### ✅ Definition of Done

- System bleibt stabil trotz neuer Features

---

## 🔵 Sprint 7 – Persistenz (Dateien)

### 🎯 Ziel

Daten speichern

### 📦 Aufgaben

- Termine in Datei speichern (z. B. JSON oder CSV)
- Beim Start laden

### 🧠 Lernziele

- File I/O
- Serialisierung
- Fehlerbehandlung

### ✅ Definition of Done

- Daten bleiben nach Neustart erhalten

---

## 🟣 Sprint 8 – Codequalität & Refactoring

### 🎯 Ziel

Von „funktioniert“ zu „gut gebaut“

### 📦 Aufgaben

- Verantwortlichkeiten trennen
- Services einführen
- Methoden verkleinern
- Tests (optional)

### 🧠 Lernziele

- Clean Code
- SOLID-Grundlagen

### ✅ Definition of Done

- Code ist lesbar, modular, erweiterbar

---

# 🚀 BONUS (optional)

## 🌐 Erweiterung 1 – REST API

- Spring Boot integrieren
- Endpunkte für Termine

## 🖥️ Erweiterung 2 – GUI

- JavaFX Oberfläche

## 📊 Erweiterung 3 – Statistiken

- Auslastung pro Therapeut
- häufigste Behandlungen

---

# 🧪 Bewertungskriterien

## Funktionalität

- erfüllt Anforderungen
- keine Doppelbuchungen

## Codequalität

- klare Struktur
- sinnvolle Klassen

## Erweiterbarkeit

- neue Anforderungen integrierbar

## Robustheit

- Fehlerfälle behandelt

---

# 💡 Wichtige Prinzipien

- ❌ Kein „alles in main()“
- ✅ Logik in Services
- ✅ Modelle spiegeln echte Welt
- ✅ Regeln sind zentral (nicht UI)

---

# 🧭 Abschlussziel

Ein System, das:

- realistisch einsetzbar wäre
- sauber erweitert werden kann
- typische Probleme echter Software löst