package edu.stanford.protege.metaproject.serialization;

import com.google.gson.*;
import com.google.common.base.Optional;
import edu.stanford.protege.metaproject.ConfigurationManager;
import edu.stanford.protege.metaproject.api.PolicyFactory;
import edu.stanford.protege.metaproject.api.Host;
import edu.stanford.protege.metaproject.api.Port;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Rafael Gonçalves <br>
 * Center for Biomedical Informatics Research <br>
 * Stanford University
 */
public class HostSerializer implements JsonSerializer<Host>, JsonDeserializer<Host> {
    private final String SERVER_URI = "uri", SECONDARY_PORT = "secondaryPort";

    @Override
    public JsonElement serialize(Host host, Type type, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.add(SERVER_URI, context.serialize(host.getUri()));
        Optional<Port> port = host.getSecondaryPort();
        if (port.isPresent()) {
            obj.add(SECONDARY_PORT, context.serialize(port.get()));
        }
        return obj;
    }

    @Override
    public Host deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        PolicyFactory factory = ConfigurationManager.getFactory();
        JsonObject obj = element.getAsJsonObject();
        URI address = null;
        try {
            address = factory.getUri(obj.getAsJsonPrimitive(SERVER_URI).getAsString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Optional<Port> optionalPort = (obj.getAsJsonPrimitive(SECONDARY_PORT) != null ?
                Optional.fromNullable(factory.getPort(obj.getAsJsonPrimitive(SECONDARY_PORT).getAsInt())) :
                Optional.absent());
        return factory.getHost(address, optionalPort);
    }
}
