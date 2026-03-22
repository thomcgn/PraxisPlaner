# Patientenmodell für das Physio-Projekt

## Ziel dieses Dokuments
Dieses Dokument beschreibt, **welche Informationen ein Patient im Domänenmodell sinnvoll mitbringen kann** und **wie diese fachlich sauber getrennt werden sollten**.

Der Fokus liegt bewusst auf **Anhaltspunkten für die Modellierung** und nicht auf einer fertigen technischen Vorlage.  
So bleibt genug Raum für eigene Architekturentscheidungen, eigene Benennungen und ein eigenes Verständnis des Fachproblems.

---

## Grundprinzip
Ein gutes Patientenmodell sollte nicht einfach möglichst viele Felder sammeln, sondern **fachlich sinnvolle Verantwortlichkeiten trennen**.

Die wichtigste Leitfrage lautet:

**Welche Daten beschreiben den Menschen selbst, welche beschreiben den Patienten im Praxissystem und welche beschreiben einen konkreten Behandlungsfall?**

Genau diese Trennung verhindert, dass das Modell später unübersichtlich, schwer wartbar oder fachlich unsauber wird.

---

# 1. Trennung der Ebenen

## 1.1 Allgemeine Personendaten
Diese Ebene beschreibt den Menschen unabhängig von seiner Rolle in der Praxis.

Typische Inhalte:
- Vorname
- Nachname
- Geburtsdatum
- Telefonnummer
- E-Mail
- Adresse

Diese Informationen sind in vielen Rollen denkbar, nicht nur bei Patienten.  
Deshalb sind sie gute Kandidaten für eine allgemeine Basisklasse oder einen wiederverwendbaren Personenbaustein.

Wichtig ist dabei:
- Nur wirklich allgemeine Informationen gehören hier hinein.
- Keine versicherungsbezogenen oder behandlungsbezogenen Daten auf diese Ebene ziehen.

---

## 1.2 Patientendaten im Praxiskontext
Diese Ebene beschreibt, **wie die Praxis diesen Menschen als Patienten verwaltet**.

Typische Inhalte:
- Patientenkennung oder Patientennummer
- Aufnahmedatum in die Praxis
- aktiver oder inaktiver Status
- Versicherungsbezug
- organisatorische Hinweise
- interner Praxisstatus

Diese Ebene ist bereits fachlich spezifisch für den Praxisbetrieb.

---

## 1.3 Medizinisch-therapeutische Informationen
Diese Ebene beschreibt nicht mehr nur den Patienten als Stammdatensatz, sondern den **gesundheitlichen bzw. therapeutischen Kontext**.

Typische Inhalte:
- Hauptbeschwerde
- Diagnose
- Symptome
- Einschränkungen
- Kontraindikationen
- Allergien
- Therapieziel
- Anamnesehinweise

Hier ist Vorsicht wichtig:  
Nicht alles davon sollte dauerhaft direkt am Patienten „kleben“, weil sich solche Informationen mit der Zeit ändern können.

---

## 1.4 Konkrete Behandlungsfälle
Ein Patient kommt oft nicht nur einmal mit genau demselben Problem.  
Deshalb ist es fachlich sinnvoll, zwischen dem Patienten und einem **konkreten Behandlungsanlass** zu unterscheiden.

Ein Behandlungsfall kann unter anderem enthalten:
- Anlass der Behandlung
- Diagnose in diesem Fall
- verordnete Sitzungen
- verbleibende Sitzungen
- verweisender Arzt
- Therapieziel
- Status des Falls
- Notizen zu diesem konkreten Verlauf

Diese Trennung ist wichtig, weil ein Patient im Laufe der Zeit mehrere unterschiedliche Behandlungsfälle haben kann.

---

# 2. Welche Informationen sinnvoll zum Patienten gehören

## 2.1 Administrative Kerndaten
Diese Informationen sind für die Praxisverwaltung fast immer sinnvoll.

Dazu zählen:
- eindeutige Patientenkennung
- Registrierungsdatum
- aktiver/inaktiver Status
- Versicherungsinformationen
- interne Bemerkungen mit organisatorischem Bezug

Warum wichtig:
- Die Praxis muss Patienten eindeutig finden und verwalten können.
- Ein bloßer Name reicht in realen Systemen nicht aus.
- Spätere Funktionen wie Suche, Filter, Historie oder Abrechnung bauen darauf auf.

---

## 2.2 Versicherungsbezug
Versicherung ist im Physio-Kontext ein zentraler Bestandteil des Patientenmodells.

Sinnvolle Denkansätze:
- Art der Versicherung
- Versicherer
- Versicherungsnummer
- Tarif oder Abrechnungsrelevanz
- privat oder gesetzlich

Wichtig als Modellierungsprinzip:
- Versicherung sollte eher als eigener fachlicher Baustein betrachtet werden als nur als loses Textfeld.
- Dadurch bleibt das Modell klarer und später erweiterbar.

---

## 2.3 Kontakt- und Notfallinformationen
Neben den allgemeinen Kontaktdaten können zusätzliche praxisrelevante Kontaktinformationen sinnvoll sein.

Beispiele:
- Notfallkontakt
- Beziehung zur Kontaktperson
- alternative Erreichbarkeit
- besondere Kommunikationshinweise

Warum sinnvoll:
- Gerade bei älteren oder mobilitätseingeschränkten Patienten kann das im Alltag relevant werden.
- Es schafft Realitätsnähe im Projekt, ohne medizinisch zu tief abzudriften.

---

## 2.4 Organisatorische Praxisinformationen
Diese Informationen sind nicht medizinisch, aber für die Praxis sehr hilfreich.

Mögliche Inhalte:
- bevorzugte Terminzeiten
- Hausbesuch erforderlich oder nicht
- Mobilitätshilfe notwendig
- Sprache oder Kommunikationshinweise
- Anzahl versäumter Termine
- interne organisatorische Hinweise

Nutzen:
- Diese Daten machen das System realistischer.
- Sie eröffnen später sinnvolle Features wie Terminplanung, Warnhinweise oder Priorisierung.

---

# 3. Was nicht einfach roh in den Patienten gepackt werden sollte

## 3.1 Beschwerden als ein einziges Freitextfeld
Ein einzelnes Feld wie „Beschwerden“ wirkt zunächst bequem, ist aber fachlich oft zu ungenau.

Das Problem dabei:
- Es vermischt Symptome, Diagnose, Ursache und Therapieziel.
- Es ist schlecht auswertbar.
- Es erschwert spätere Fachlogik.
- Es lädt dazu ein, alles in unsauberen Freitext auszulagern.

Besser ist, Beschwerden gedanklich zu unterteilen.

Sinnvolle Unterscheidungen:
- Hauptbeschwerde
- Symptome
- medizinische Diagnose
- funktionelle Einschränkungen
- Therapieziel
- freie Zusatznotizen

Diese Struktur muss nicht sofort maximal detailliert umgesetzt werden, sollte aber fachlich mitgedacht werden.

---

## 3.2 Medizinische Verlaufsdaten direkt dauerhaft am Patienten
Was heute gilt, muss nicht dauerhaft für jeden zukünftigen Behandlungsfall gelten.

Beispiele für Daten, die eher fallbezogen sein können:
- aktuelle Diagnose
- aktuelle Beschwerden
- verordnete Anzahl von Sitzungen
- konkrete Behandlungsziele
- Verlaufshinweise zu einer bestimmten Verordnung

Diese Informationen gehören oft besser in einen **Behandlungsfall**, nicht dauerhaft in den allgemeinen Patientendatensatz.

---

# 4. Empfohlene fachliche Bausteine

## 4.1 Person
Zweck:
- allgemeine menschliche Stammdaten
- wiederverwendbar für andere Rollen wie Therapeut oder Mitarbeiter

Denkbare Inhalte:
- Name
- Geburtsdatum
- Kontaktdaten
- Adresse

---

## 4.2 Patient
Zweck:
- beschreibt den Menschen als Patienten im Praxissystem

Denkbare Inhalte:
- Patientenkennung
- Verknüpfung zur Versicherung
- Praxisstatus
- Registrierungsdatum
- organisatorische Hinweise
- Verweise auf medizinische oder fallbezogene Informationen

Wichtiger Hinweis:
- Der Patient ist nicht automatisch der richtige Ort für jede medizinische Detailinformation.

---

## 4.3 Versicherung
Zweck:
- bildet den abrechnungs- und verwaltungsrelevanten Versicherungsbezug ab

Denkbare Inhalte:
- Typ der Versicherung
- Anbieter
- Nummer
- tarifliche Besonderheiten
- privat/gesetzlich

Warum als eigener Baustein sinnvoll:
- klare fachliche Kapselung
- bessere Erweiterbarkeit
- weniger unübersichtliche Stammdatenfelder im Patienten

---

## 4.4 Medizinisches Profil
Zweck:
- sammelt medizinische Basisinformationen, die nicht direkt an einen einzelnen Termin gebunden sind

Denkbare Inhalte:
- Allergien
- Kontraindikationen
- allgemeine Hinweise
- relevante Vorerkrankungen, soweit im Projektrahmen sinnvoll
- funktionale Einschränkungen

Wichtige Abwägung:
- Nur so tief modellieren, wie das Projekt es wirklich braucht.
- Für ein Lernprojekt reicht meist eine reduzierte, aber klar gedachte Struktur.

---

## 4.5 Behandlungsfall
Zweck:
- beschreibt einen konkreten therapeutischen Anlass oder Verlauf

Denkbare Inhalte:
- Hauptbeschwerde
- Diagnose
- Therapieziel
- verordnende Stelle
- Beginn
- Umfang der Verordnung
- Status
- fallbezogene Notizen

Warum dieser Baustein besonders wertvoll ist:
- Er macht das Modell realistischer.
- Er trennt Stammdaten von fachlich veränderlichen Inhalten.
- Er erlaubt mehrere Behandlungsanlässe pro Patient.

---

## 4.6 Notfallkontakt
Zweck:
- zusätzlicher praxisrelevanter Kontakt mit Bezug zum Patienten

Denkbare Inhalte:
- Name
- Beziehung
- Telefonnummer
- Erreichbarkeit

---

# 5. Was als Minimalumfang sinnvoll ist

Wenn das Projekt nicht zu groß werden soll, reicht ein reduzierter, aber fachlich sauberer Umfang.

Ein sinnvoller Minimalumfang umfasst typischerweise:

## Allgemeine Basis
- Name
- Geburtsdatum
- Kontaktdaten
- Adresse

## Patientenspezifische Praxisdaten
- Patientenkennung
- Versicherungsbezug
- Registrierungsdatum
- Status
- interne Hinweise

## Medizinisch grobe Einordnung
- Hauptbeschwerde
- Diagnose
- verweisender Arzt oder verordnende Stelle
- allgemeine Notizen

Wichtig:
- Auch ein Minimalmodell sollte bereits fachlich trennen, statt alles in ein einziges Sammelobjekt zu stopfen.

---

# 6. Was einen realistischeren Umfang ausmacht

Wenn das Projekt bewusster als echte Kundensimulation gedacht ist, können zusätzliche Aspekte aufgenommen werden.

Dazu gehören zum Beispiel:
- Notfallkontakt
- bevorzugte Terminzeiten
- Hausbesuchsbedarf
- Mobilitätseinschränkungen
- Kommunikationshinweise
- mehrere Behandlungsfälle pro Patient
- Warnhinweise für Planung oder Behandlung
- organisatorische Historie

Dadurch gewinnt das Modell an Realitätsnähe, ohne dass sofort technische Komplexität künstlich erhöht werden muss.

---

# 7. Modellierungsleitlinien

## 7.1 Nicht alles in ein Objekt laden
Ein großes „Patient“-Objekt mit allen denkbaren Feldern wird schnell unklar.

Besser:
- Stammdaten trennen
- Versicherungsdaten trennen
- medizinische Basisdaten trennen
- konkrete Behandlungsfälle trennen

---

## 7.2 Fachliche Begriffe sauber wählen
Begriffe wie „Beschwerden“ sind oft zu breit.

Besser ist eine bewusstere Sprache, zum Beispiel:
- Hauptbeschwerde
- Diagnose
- Symptomatik
- Therapieziele
- Kontraindikationen
- organisatorische Hinweise

Gute Benennung verbessert fast immer auch das Design.

---

## 7.3 Nur modellieren, was fachlich nützlich ist
Nicht jede theoretisch mögliche Information ist für das Projekt relevant.

Fragen zur Auswahl:
- Wird diese Information im Praxisablauf wirklich gebraucht?
- Löst sie eine echte Regel oder Entscheidung aus?
- Macht sie spätere Funktionen sinnvoller?
- Erhöht sie die Verständlichkeit des Modells?

Wenn nicht, gehört sie wahrscheinlich nicht in die erste Version.

---

## 7.4 Zwischen dauerhaft und fallbezogen unterscheiden
Einige Informationen bleiben langfristig stabil, andere ändern sich je nach Behandlung.

Dauerhaft eher:
- Identität
- Kontakt
- Versicherung
- allgemeine organisatorische Hinweise

Fallbezogen eher:
- Diagnose
- Hauptbeschwerde
- Behandlungsziel
- Sitzungsumfang
- konkreter Verlauf

Diese Unterscheidung ist einer der wichtigsten Schritte zu einem guten Domänenmodell.

---

# 8. Praktische Empfehlung für das Projekt

Für das Physio-Projekt ist folgende Denkrichtung besonders sinnvoll:

## Der Patient sollte vor allem den Menschen im Praxissystem beschreiben
Also:
- wer die Person ist
- wie sie administrativ geführt wird
- wie sie organisatorisch behandelt wird

## Der konkrete therapeutische Anlass sollte separat gedacht werden
Also:
- warum die Person gerade kommt
- welche Diagnose aktuell relevant ist
- welche Ziele und Einschränkungen im konkreten Fall bestehen

Diese Trennung macht das Modell robuster und viel realistischer als ein einfaches Sammelobjekt.

---

# 9. Zusammenfassung

Ein gutes Patientenmodell im Physio-Projekt besteht nicht nur aus „Name, Adresse, Versicherung, Beschwerden“.

Sauber gedacht werden mindestens vier Perspektiven:

1. **Person**
   - allgemeine Stammdaten

2. **Patient**
   - Praxisverwaltung, Status, Identifikation, Organisation

3. **Versicherung / ergänzende Bausteine**
   - fachlich getrennte Verwaltungsinformationen

4. **Behandlungsfall oder medizinischer Kontext**
   - aktuelle Beschwerden, Diagnose, Ziel und Verlauf

Der wichtigste fachliche Hinweis lautet:

**Beschwerden, Diagnose und Verlauf sollten nicht gedankenlos als unsortierter Freitext direkt in den Patienten gepackt werden.**

Je klarer diese Trennung von Anfang an ist, desto realistischer, wartbarer und erweiterbarer wird das gesamte Projekt.

---

# 10. Reflexionsfragen für die eigene Modellierung

Zur eigenen Ausarbeitung können diese Fragen helfen:

- Welche Informationen beschreiben wirklich den Menschen selbst?
- Welche Informationen braucht nur die Praxisverwaltung?
- Welche Informationen gehören nur zu einem konkreten Behandlungsfall?
- Welche Daten sollen später gesucht, gefiltert oder geprüft werden können?
- Wo würde ein einziges Freitextfeld zu ungenau werden?
- Welche Teile des Modells sollten eher eigenständige fachliche Bausteine sein?

Diese Fragen sind oft wertvoller als jede fertige Vorlage, weil sie helfen, das Modell wirklich zu verstehen statt nur zu übernehmen.
