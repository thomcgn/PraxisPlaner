# Therapeuten- und Ärztemodell für das Projekt

## Ziel dieses Dokuments
Dieses Dokument beschreibt, welche Informationen für **Therapeuten und Ärzte** im Domänenmodell sinnvoll sind und wie diese fachlich sauber strukturiert werden können.

Der Fokus liegt – wie beim Patientenmodell – auf **fachlichen Leitlinien und Denkstrukturen**, nicht auf fertigen Implementierungen.

---

# Grundprinzip

Therapeuten und Ärzte sind beides **Personen mit fachlicher Rolle im System**, aber:

- Therapeuten sind Teil der Praxis (interne Ressourcen)
- Ärzte sind meist externe Akteure (z. B. Überweiser)

Diese Unterscheidung ist entscheidend für das Modell.

---

# 1. Gemeinsame Basis: Person

Sowohl Therapeuten als auch Ärzte haben allgemeine personenbezogene Daten:

- Name
- Geburtsdatum
- Kontaktdaten
- Adresse

Diese gehören – wie beim Patienten – in eine gemeinsame Basisebene.

---

# 2. Therapeuten (interne Praxisrolle)

## 2.1 Rolle im System
Therapeuten sind aktive Ressourcen der Praxis.

Sie:
- führen Behandlungen durch
- haben Arbeitszeiten
- werden in Terminplanung eingebunden
- haben fachliche Spezialisierungen

---

## 2.2 Fachlich sinnvolle Informationen

### Identität im System
- eindeutige Mitarbeiterkennung
- Name
- Status (aktiv/inaktiv)

---

### Fachliche Qualifikation
- Spezialisierungen (z. B. Orthopädie, Neurologie)
- Behandlungsschwerpunkte
- Qualifikationen oder Zertifikate

Warum wichtig:
- bestimmte Behandlungen dürfen nur bestimmte Therapeuten durchführen
- spätere Logik kann darauf aufbauen

---

### Verfügbarkeit und Arbeitszeit
- Arbeitszeiten (z. B. Wochenplan)
- verfügbare Tage
- Pausen
- Abwesenheiten (Urlaub, Krankheit)

Warum wichtig:
- Terminplanung basiert darauf
- Konfliktprüfung benötigt diese Daten

---

### Organisatorische Eigenschaften
- maximale Termine pro Tag
- bevorzugte Terminlängen
- Einschränkungen (z. B. keine Hausbesuche)

---

### Interne Informationen
- Notizen
- besondere organisatorische Hinweise

---

## 2.3 Wichtige Modellierungsüberlegung

Therapeuten sollten nicht nur als „Name + Liste von Terminen“ modelliert werden.

Sondern als:
- eigenständige Ressource
- mit Regeln und Einschränkungen
- die aktiv in die Planung eingreift

---

# 3. Ärzte (externe Rolle)

## 3.1 Rolle im System
Ärzte sind in der Regel **keine internen Benutzer**, sondern externe Partner.

Sie:
- stellen Verordnungen aus
- überweisen Patienten
- sind Referenz in Behandlungsfällen

---

## 3.2 Fachlich sinnvolle Informationen

### Identität
- Name
- Praxisname
- Fachrichtung

---

### Kontaktinformationen
- Telefonnummer
- Adresse
- ggf. E-Mail oder Fax (realistisch im Kontext)

---

### Fachrichtung
- Allgemeinmedizin
- Orthopädie
- Neurologie
- etc.

Warum wichtig:
- kann für Auswertung oder Filter relevant sein
- erhöht Realitätsnähe

---

### Praxisbezug
- Praxisname
- Standort
- ggf. mehrere Ärzte pro Praxis (optional Erweiterung)

---

## 3.3 Wichtige Modellierungsüberlegung

Ärzte sollten nicht übermodelliert werden.

Fragen zur Abgrenzung:
- Muss der Arzt aktiv im System agieren?
- Oder ist er nur Referenz für Verordnungen?

Für die meisten Projekte gilt:
→ Arzt ist eher ein **referenziertes Objekt**, kein aktiver Systemnutzer

---

# 4. Beziehung zu anderen Modellen

## 4.1 Therapeut ↔ Termin
- ein Therapeut hat viele Termine
- ein Termin gehört zu genau einem Therapeuten

---

## 4.2 Patient ↔ Therapeut
- Beziehung entsteht indirekt über Termine oder Behandlungsfälle
- kein direkter dauerhafter Bezug notwendig

---

## 4.3 Patient ↔ Arzt
- Arzt ist meist Teil eines Behandlungsfalls
- nicht zwingend dauerhaft direkt am Patienten gespeichert

---

## 4.4 Arzt ↔ Behandlungsfall
- Arzt ist oft der Aussteller der Verordnung
- gehört logisch zum Behandlungsfall, nicht zwingend zum Patienten

---

# 5. Minimalmodell vs. realistisches Modell

## 5.1 Minimal (für Einstieg)

### Therapeut
- Name
- einfache Verfügbarkeit
- Liste von Terminen

### Arzt
- Name
- Fachrichtung

---

## 5.2 Realistischer Ansatz

### Therapeut
- Identität + Status
- Spezialisierungen
- Arbeitszeiten
- Verfügbarkeit
- organisatorische Einschränkungen

### Arzt
- Name
- Praxis
- Fachrichtung
- Kontaktinformationen

---

# 6. Typische Modellierungsfehler

## 6.1 Therapeut zu simpel modellieren
Nur Name + Termine reicht nicht.

Problem:
- keine Planungslogik möglich
- keine Einschränkungen modellierbar

---

## 6.2 Arzt zu komplex modellieren
Ein Arzt braucht oft kein komplexes Verhalten.

Problem:
- unnötige Komplexität
- kein echter Mehrwert im Projekt

---

## 6.3 Rollen vermischen
Therapeut und Arzt sind fachlich unterschiedlich.

Fehler:
- beide gleich behandeln
- oder Eigenschaften falsch zuordnen

---

# 7. Modellierungsleitlinien

## 7.1 Rollen klar trennen
- Therapeut = interne Ressource
- Arzt = externe Referenz

---

## 7.2 Verhalten berücksichtigen
Therapeuten haben Verhalten:
- arbeiten zu bestimmten Zeiten
- dürfen bestimmte Dinge tun

Ärzte haben meist kein Verhalten im System.

---

## 7.3 Erweiterbarkeit bedenken
Fragen:
- Wird später Planung komplexer?
- Gibt es Regeln für Spezialisierungen?
- Gibt es Einschränkungen?

Wenn ja:
→ früh sauber strukturieren

---

# 8. Zusammenfassung

Ein gutes Modell unterscheidet klar:

## Therapeut
- aktive Rolle
- Teil der Praxis
- beeinflusst Terminplanung
- hat Verfügbarkeit und Qualifikationen

## Arzt
- externe Rolle
- Referenz für Verordnungen
- liefert Kontext für Behandlungsfälle

Der wichtigste Punkt:

**Therapeuten sind aktive Systemelemente mit Regeln – Ärzte sind meist passive Referenzen.**

---

# 9. Reflexionsfragen

- Welche Informationen braucht die Terminplanung wirklich?
- Welche Eigenschaften beeinflussen Entscheidungen?
- Welche Daten sind nur „nice to have“?
- Wo wird ein Modell unnötig komplex?
- Wo fehlt Struktur für echte Logik?

Diese Fragen helfen, ein realistisches und sauberes Modell zu entwickeln.
