package it.unipd.dei.webapp.leoforfriends.resource;

import com.fasterxml.jackson.core.JsonGenerator;
import java.io.*;

/**
 * Provides resources for representing and exchanging data about the main entities of the 
 * application. It's a list of resources.
 *
 * @author leoforfriends
 * @version 1.00
 * @since 1.00
 */
 
public final class ResourceList<T extends Resource> extends Resource {

    /**
     * The list of {@code Resource}s.
     */
    private final Iterable<T> list;

    /**
     * Creates a list of {@code Resource}s.
     *
     * @param list the list of {@code Resource}s.
     */
    public ResourceList(final Iterable<T> list) {
        this.list = list;
    }

    @Override
    public final void toJSON(final OutputStream out) throws IOException {

        final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

        jg.writeStartObject();

        jg.writeFieldName("resource-list");

        jg.writeStartArray();

        jg.flush();

        boolean firstElement = true;

        for (final Resource r : list) {

            if (firstElement) {
                r.toJSON(out);
                jg.flush();

                firstElement = false;
            } else {
                jg.writeRaw(',');
                jg.flush();

                r.toJSON(out);
                jg.flush();
            }
        }

        jg.writeEndArray();

        jg.writeEndObject();

        jg.flush();

        jg.close();
    }

}