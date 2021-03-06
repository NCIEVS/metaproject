package edu.stanford.protege.metaproject.impl;

import com.google.common.collect.ComparisonChain;
import edu.stanford.protege.metaproject.api.*;

import javax.annotation.Nonnull;
import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Rafael Gonçalves <br>
 * Center for Biomedical Informatics Research <br>
 * Stanford University
 */
public abstract class OperationAbst implements Operation, Serializable {
    @Nonnull protected final OperationId id;
    @Nonnull protected final Name name;
    @Nonnull protected final Description description;
    @Nonnull protected final OperationType type;
    @Nonnull protected final Scope scope;

    /**
     * Constructor
     *
     * @param id   Operation identifier
     * @param name Operation name
     * @param description  Operation description
     * @param type Operation type
     * @param scope Operation scope
     */
    public OperationAbst(@Nonnull OperationId id, @Nonnull Name name, @Nonnull Description description, @Nonnull OperationType type, @Nonnull Scope scope) {
        this.id = checkNotNull(id);
        this.name = checkNotNull(name);
        this.description = checkNotNull(description);
        this.type = checkNotNull(type);
        this.scope = checkNotNull(scope);
    }

    @Override
    @Nonnull
    public OperationId getId() {
        return id;
    }

    @Override
    @Nonnull
    public Name getName() {
        return name;
    }

    @Override
    @Nonnull
    public Description getDescription() {
        return description;
    }

    @Override
    @Nonnull
    public OperationType getType() {
        return type;
    }

    @Override
    @Nonnull
    public Scope getScope() {
        return scope;
    }

    @Override
    public boolean isUser() {
        return false;
    }

    @Override
    public boolean isProject() {
        return false;
    }

    @Override
    public boolean isRole() {
        return false;
    }

    @Override
    public boolean isOperation() {
        return true;
    }

    @Override
    public int compareTo(@Nonnull Operation that) {
        return ComparisonChain.start()
                .compare(this.name.get(), that.getName().get())
                .result();
    }
}
