package org.thomcgn.backend.shared.model;

import lombok.Data;

import java.time.LocalDate;

/**
 *  Diese Klasse repräsentiert eine Person mit grundlegenden Informationen wie Vorname, Nachname, Geburtsdatum, E-Mail, Telefonnummer und Adresse.
 *  Sie dient als Basis für andere Klassen, die spezifische Arten von Personen darstellen, wie z.B. Patienten, Ärzte oder Mitarbeiter.
 *  Die Klasse enthält Attribute, die für die Identifikation und Kontaktaufnahme mit einer Person relevant sind, sowie Informationen über die Adresse, um die geografische Lage der Person zu erfassen.
 *  Es ist wichtig zu beachten, dass diese Klasse als allgemeine Vorlage dient und je nach Bedarf erweitert oder angepasst werden kann, um spezifische Anforderungen für verschiedene Arten von Personen zu erfüllen
 */
@Data
public class Person {
    String vorname;
    String nachname;
    LocalDate geburtsdatum;
    String email;
    String telefonnummer;

    String strasse;
    Integer hausnummer;
    String stadt;
    String plz;

}
