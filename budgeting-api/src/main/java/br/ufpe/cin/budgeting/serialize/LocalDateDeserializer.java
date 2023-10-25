package br.ufpe.cin.budgeting.serialize;


import br.ufpe.cin.budgeting.exception.DateFormaInvalidException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import org.springframework.http.HttpStatus;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    public static final String INVALID_FORMAT_DATE = "Invalid.format.for.date";

    protected LocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        LocalDate date = null;
        try {
            date = LocalDate.parse(jp.readValueAs(String.class));
        } catch (DateTimeParseException e) {
            throw new DateFormaInvalidException(INVALID_FORMAT_DATE, HttpStatus.BAD_REQUEST);
        }
        return date;
    }

}
