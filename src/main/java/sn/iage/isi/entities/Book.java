package sn.iage.isi.entities;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.PrePersist;
import lombok.experimental.SuperBuilder;

import java.util.Random;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class Book extends BaseEntity {

    @Column(nullable = false, length = 20, unique = true)
    private String isbn;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 150)
    private String author;

    @Column(name = "publication_year",nullable = false)
    public int publicationYear;

    @Column(name = "count_pages",nullable = false)
    private int countPages;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String generateIsbn() {
        // Préfixe ISBN-13 : 978 ou 979
        String[] prefixes = {"978", "979"};
        Random random = new Random();

        String prefix = prefixes[random.nextInt(2)];        // 978 ou 979
        String group = String.valueOf(random.nextInt(2));    // 0 ou 1 (groupe langue)
        String publisher = String.format("%04d", random.nextInt(10000));   // éditeur 4 chiffres
        String title    = String.format("%04d", random.nextInt(10000));    // titre   4 chiffres

        // Calcul du chiffre de contrôle (checksum ISBN-13)
        String base = prefix + group + publisher + title;   // 12 chiffres
        int checkDigit = computeIsbn13CheckDigit(base);

        String isbn = base + checkDigit;

        // Format lisible : 978-X-XXXX-XXXX-X
        return String.format("%s-%s-%s-%s-%d",
                prefix, group, publisher, title, checkDigit);
    }

    private int computeIsbn13CheckDigit(String base12) {
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(base12.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;   // alternance poids 1 et 3
        }
        int remainder = sum % 10;
        return remainder == 0 ? 0 : 10 - remainder;
    }

    @PrePersist
    private void prePersist() {
        if (this.isbn == null || this.isbn.isBlank()) {
            this.isbn = generateIsbn();
        }
    }
}
