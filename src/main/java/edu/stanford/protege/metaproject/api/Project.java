package edu.stanford.protege.metaproject.api;

import java.util.Set;

import javax.annotation.Nonnull;
import com.google.common.base.Optional;

/**
 * A representation of a project, consisting of a unique (internal) identifier, a (display) name, and a
 * description. A project is owned by a single user; unless altered, the owner is the creator. A project may
 * also have some options associated with it.
 *
 * @author Rafael Gonçalves <br>
 * Center for Biomedical Informatics Research <br>
 * Stanford University
 */
public interface Project extends PolicyObject<ProjectId>, HasDescription, Comparable<Project> {

    /**
     * Get the owner user of the project
     *
     * @return Project owner user
     */
    @Nonnull
    UserId getOwner();
    
    @Nonnull
    String namespace();
    
    Set<ProjectId> getImports();
    
    public void addImport(ProjectId id);
    
    void removeImport(ProjectId id);
    

    /**
     * Get the options for this project
     *
     * @return Project options
     */
    @Nonnull
    Optional<ProjectOptions> getOptions();

	

}
