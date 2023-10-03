import com.minibank.database.tables.records.CitiesRecord;
import com.minibank.database.tables.records.CountriesRecord;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.minibank.database.tables.Cities;
import static com.minibank.database.tables.Countries.;
import static org.assertj.core.api.Assertions.assertThat;

@JooqTest
public class jooqTest {
}
