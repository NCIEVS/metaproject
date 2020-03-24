package edu.stanford.protege.metaproject.serialization;

import com.google.gson.*;
import com.google.common.base.Optional;
import edu.stanford.protege.metaproject.ConfigurationManager;
import edu.stanford.protege.metaproject.api.*;

import java.lang.reflect.Type;
import java.util.Iterator;

/**
 * @author Rafael Gonçalves <br>
 * Center for Biomedical Informatics Research <br>
 * Stanford University
 */
public class ProjectSerializer implements JsonDeserializer<Project>, JsonSerializer<Project> {
    private final String ID = "id", NAMESPACE = "namespace", NAME = "name", DESCRIPTION = "description", 
    		OWNER = "owner", OPTIONS = "options", IMPORTS = "imports";

    @Override
    public JsonElement serialize(Project project, Type type, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.add(ID, context.serialize(project.getId()));
        obj.add(NAMESPACE, context.serialize(project.namespace()));
        obj.add(NAME, context.serialize(project.getName()));
        obj.add(DESCRIPTION, context.serialize(project.getDescription()));
        obj.add(OWNER, context.serialize(project.getOwner()));
        obj.add(IMPORTS, context.serialize(project.getImports()));
        if(project.getOptions().isPresent()) {
            obj.add(OPTIONS, context.serialize(project.getOptions().get(), ProjectOptions.class));
        }
        return obj;
    }

    @Override
    public Project deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        PolicyFactory factory = ConfigurationManager.getFactory();
        JsonObject obj = element.getAsJsonObject();
        ProjectId projectId = factory.getProjectId(obj.getAsJsonPrimitive(ID).getAsString());
        Name projectName = factory.getName(obj.getAsJsonPrimitive(NAME).getAsString());
        String projectNamespace = factory.getProjectNamespace(obj.getAsJsonPrimitive(NAMESPACE).getAsString());
        Description projectDescription = factory.getDescription(obj.getAsJsonPrimitive(DESCRIPTION).getAsString());
        UserId owner = factory.getUserId(obj.getAsJsonPrimitive(OWNER).getAsString());
        
        ProjectOptions projectOptions = null;
        if(obj.getAsJsonObject(OPTIONS) != null) {
            projectOptions = context.deserialize(obj.getAsJsonObject(OPTIONS), ProjectOptions.class);
        } 
        Project res = factory.getProject(projectId, projectNamespace, projectName, projectDescription, owner, Optional.fromNullable(projectOptions));
        
        JsonArray array = obj.getAsJsonArray(IMPORTS);
        
        Iterator<JsonElement> it = array.iterator();
        while (it.hasNext()) {
        	res.addImport(factory.getProjectId(it.next().getAsString()));
        }
        return res;       
        
        
    }
}
