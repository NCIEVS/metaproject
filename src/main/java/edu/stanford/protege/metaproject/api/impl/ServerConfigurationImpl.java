package edu.stanford.protege.metaproject.api.impl;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import edu.stanford.protege.metaproject.api.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public final class ServerConfigurationImpl implements ServerConfiguration, Serializable {
    private static final long serialVersionUID = -2274852449459076105L;
    private final Host host;
    private final Metaproject metaproject;
    private final AuthenticationManager authenticationManager;
    private final Map<String,String> properties;
    private final OntologyTermIdStatus termIdentifiers;

    /**
     * Package-private constructor; use builder
     *
     * @param host    Host
     * @param metaproject    Access control policy
     * @param authenticationManager Authentication manager
     * @param properties   Map of simple configuration properties
     * @param termIdentifiers  Ontology term identifier status
     */
    ServerConfigurationImpl(Host host, Metaproject metaproject, AuthenticationManager authenticationManager, Optional<Map<String,String>> properties, Optional<OntologyTermIdStatus> termIdentifiers) {
        this.host = checkNotNull(host);
        this.metaproject = checkNotNull(metaproject);
        this.authenticationManager = checkNotNull(authenticationManager);
        this.properties = (properties.isPresent() ? checkNotNull(properties.get()) : null);
        this.termIdentifiers = (termIdentifiers.isPresent() ? checkNotNull(termIdentifiers.get()) : null);
    }

    @Override
    public Host getHost() {
        return host;
    }

    @Override
    public Metaproject getMetaproject() {
        return metaproject;
    }

    @Override
    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    @Override
    public Optional<Map<String, String>> getProperties() {
        return Optional.ofNullable(properties);
    }

    @Override
    public Optional<OntologyTermIdStatus> getOntologyTermIdStatus() {
        return Optional.ofNullable(termIdentifiers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerConfigurationImpl that = (ServerConfigurationImpl) o;
        return Objects.equal(host, that.host) &&
                Objects.equal(metaproject, that.metaproject) &&
                Objects.equal(authenticationManager, that.authenticationManager) &&
                Objects.equal(properties, that.properties) &&
                Objects.equal(termIdentifiers, that.termIdentifiers);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(host, metaproject, authenticationManager, properties, termIdentifiers);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("host", host)
                .add("metaproject", metaproject)
                .add("authenticationManager", authenticationManager)
                .add("properties", properties)
                .add("termIdentifiers", termIdentifiers)
                .toString();
    }

    /**
     * Access control server configuration builder
     */
    public static class Builder {
        private Host host;
        private Metaproject metaproject;
        private AuthenticationManager authenticationManager;
        private Map<String,String> properties = new HashMap<>();
        private OntologyTermIdStatus ontologyTermIdStatus = new OntologyTermIdStatusImpl.Builder().createOntologyTermIdStatus();

        public Builder setHost(Host host) {
            this.host = host;
            return this;
        }

        public Builder setMetaproject(Metaproject metaproject) {
            this.metaproject = metaproject;
            return this;
        }

        public Builder setAuthenticationManager(AuthenticationManager authenticationManager) {
            this.authenticationManager = authenticationManager;
            return this;
        }

        public Builder setPropertyMap(Map<String,String> propertyMap) {
            this.properties = propertyMap;
            return this;
        }

        public Builder setOntologyTermIdStatus(OntologyTermIdStatus ontologyTermIdStatus) {
            this.ontologyTermIdStatus = ontologyTermIdStatus;
            return this;
        }

        public ServerConfigurationImpl createServerConfiguration() {
            return new ServerConfigurationImpl(host, metaproject, authenticationManager, Optional.ofNullable(properties), Optional.ofNullable(ontologyTermIdStatus));
        }
    }
}