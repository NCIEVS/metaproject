package edu.stanford.protege.metaproject.api;

import edu.stanford.protege.metaproject.Utils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
@RunWith(MockitoJUnitRunner.class)
public class HostTest {
    private static final String toStringHead = Host.class.getSimpleName();
    private static final int port = 8080, diffPort = 8081;

    @Mock private Address hostAddress, diffHostAddress;

    private Host host, otherHost, diffHost;

    @Before
    public void setUp() {
        host = Utils.getHost(hostAddress, port);
        otherHost = Utils.getHost(hostAddress, port);
        diffHost = Utils.getHost(diffHostAddress, diffPort);
    }

    @Test
    public void testNotNull() {
        assertThat(host, is(not(equalTo(null))));
    }

    @Test
    public void testGetAddress() {
        assertThat(host.getAddress(), is(hostAddress));
    }

    @Test
    public void testGetPort() {
        assertThat(host.getPort(), is(port));
    }

    @Test
    public void testEqualToSelf() {
        assertThat(host, is(host));
    }

    @Test
    public void testEquals() {
        assertThat(host, is(otherHost));
    }

    @Test
    public void testNotEquals() {
        assertThat(host, is(not(diffHost)));
    }

    @Test
    public void testHashCode() {
        assertThat(host.hashCode(), is(otherHost.hashCode()));
    }

    @Test
    public void testToString() {
        assertThat(host.toString(), startsWith(toStringHead));
    }
}