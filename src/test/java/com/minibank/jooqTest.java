package com.minibank;

import com.minibank.database.tables.records.CitiesRecord;
import com.minibank.database.tables.records.CountriesRecord;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.minibank.database.tables.Cities.CITIES;
import static com.minibank.database.tables.Countries.COUNTRIES;
import static org.assertj.core.api.Assertions.assertThat;

@JooqTest
public class jooqTest {

    @Autowired
    private DSLContext dsl;

    @Test
    @Order(0)
    void find_Countries() {
        Result<CountriesRecord> competitions = dsl
                .selectFrom(COUNTRIES)
                .fetch();

        assertThat(competitions).hasSize(3);
    }

    @Test
    @Order(1)
    void find_join() {
        List<CountryCityDTO> athletes = dsl
                .select(COUNTRIES.NAME, COUNTRIES.POPULATION, CITIES.NAME)
                .from(COUNTRIES)
                .join(CITIES).on(CITIES.COUNTRY_ID.eq(COUNTRIES.ID))
                .fetchInto(CountryCityDTO.class);
    }

    @Test
    @Order(2)
    void insert() {
        Long id = dsl.insertInto(CITIES)
                .columns(CITIES.COUNTRY_ID, CITIES.NAME)
                .values(1L, "Ivanovo")
                .returningResult(CITIES.ID)
                .fetchOneInto(Long.class);
            }


    @Test
    @Order(3)
    void delete() {
        int deletedRows = dsl
                .deleteFrom(CITIES)
                .where(CITIES.ID.eq(1000L))
                .execute();

    }

    @Test
    @Order(4)
    void update(){
        int updateRows = dsl
                .update(CITIES)
                .set(CITIES.NAME, "Ivan-ovo")
                .where(CITIES.ID.eq(1L))
                .execute();
    }

}