package edu.stanford.protege.metaproject.api;

import edu.stanford.protege.metaproject.Utils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
@RunWith(MockitoJUnitRunner.class)
public class EntityNameTest {
    private static final String toStringHead = EntityName.class.getSimpleName();

    @Mock private EntityNamePrefix namePrefix;
    @Mock private EntityNameSuffix nameSuffix, otherNameSuffix;

    private EntityName entityName, otherEntityName, diffEntityName;

    @Before
    public void setUp() {
        entityName = Utils.getEntityName(namePrefix, nameSuffix);
        otherEntityName = Utils.getEntityName(namePrefix, nameSuffix);
        diffEntityName = Utils.getEntityName(namePrefix, otherNameSuffix);
    }

    @Test
    public void testNotNull() {
        assertThat(entityName, is(not(equalTo(null))));
    }

    @Test
    public void testGetName() {
        when(namePrefix.get()).thenReturn("prefix");
        when(nameSuffix.get()).thenReturn("suffix");
        assertThat(entityName.get(), is(namePrefix.get().concat(nameSuffix.get())));
    }

    @Test
    public void testGetPrefix() {
        assertThat(entityName.getPrefix(), is(namePrefix));
    }

    @Test
    public void testGetSuffix() {
        assertThat(entityName.getSuffix(), is(nameSuffix));
    }

    @Test
    public void testEqualToSelf() {
        assertThat(entityName, is(equalTo(entityName)));
    }

    @Test
    public void testEquals() {
        assertThat(entityName, is(equalTo(otherEntityName)));
    }

    @Test
    public void testNotEquals() {
        assertThat(entityName, is(not(equalTo(diffEntityName))));
    }

    @Test
    public void testHashCode() {
        assertThat(entityName.hashCode(), is(otherEntityName.hashCode()));
    }

    @Test
    public void testToString() {
        assertThat(entityName.toString(), startsWith(toStringHead));
    }
}