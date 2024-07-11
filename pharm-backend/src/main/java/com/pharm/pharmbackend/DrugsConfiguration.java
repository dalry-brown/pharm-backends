package com.pharm.pharmbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DrugsConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(DrugsRepository drugsRepository, PurchaseRepository purchaseRepository){
        return args -> {
            ArrayList<String> paraCategory = new ArrayList<>();
            paraCategory.add("pain killer");
            Drugs paracetamol = new Drugs(
                    1,
                    "Paraceramol",
                    "Lorem ipsum dolor sit amet giikkggk gllglggj ggkgk gkgkmgkgkmgkgkoeo nkgggjkg",
                    6,
                    paraCategory,
                    5,
                    LocalDate.of(2023, Month.JUNE, 3),
                    LocalTime.of(13,45)




            );

            ArrayList<String> adderalCategory = new ArrayList<>();
            adderalCategory.add("Stimulant");
            Drugs adderal = new Drugs(
                    2,
                    "Adderal",
                    "Lorem ipsum dolor sit amet giikkggk gllglggj ggkgk gkgkmgkgkmgkgkoeo nkgggjkg",
                    5,
                    adderalCategory,
                    6,
                    LocalDate.of(2023, Month.JUNE, 3),
                    LocalTime.of(13,45)

            );
            drugsRepository.saveAll(List.of(paracetamol,adderal));

            Purchase purch = new Purchase(
                    4,
                    6,
                    adderal,
                    67,
                    LocalDate.of(2024, Month.FEBRUARY, 3),
                    LocalTime.of(23, 9)
            );
            purchaseRepository.save(purch);
        };
    }
}
