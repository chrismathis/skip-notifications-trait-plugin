package org.jenkinsci.plugins.gitea.notifications;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import org.jenkinsci.plugin.gitea.GiteaSCMSource;
import org.jenkinsci.plugin.gitea.GiteaSCMSourceContext;
import org.junit.jupiter.api.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.junit.jupiter.WithJenkins;

import jenkins.scm.api.trait.SCMSourceContext;

@WithJenkins
public class GiteaSkipNotificationsTest {

    @Test
    public void shouldDecorateContext() {
        GiteaSCMSourceContext mock = mock(GiteaSCMSourceContext.class);
        GiteaSkipNotifications trait = new GiteaSkipNotifications();
        trait.decorateContext(mock);
        verify(mock).withNotificationsDisabled(true);
    }

    @Test
    public void shouldNotDecorateContext() {
        SCMSourceContext<?, ?> mock = mock(SCMSourceContext.class);
        GiteaSkipNotifications trait = new GiteaSkipNotifications();
        trait.decorateContext(mock);
        verifyNoInteractions(mock);
    }

    @Test
    public void shouldValidateDescriptor(JenkinsRule jenkinsRule) {
        GiteaSkipNotifications.DescriptorImpl descriptor = jenkinsRule.jenkins.getDescriptorByType(GiteaSkipNotifications.DescriptorImpl.class);
        assertThat(descriptor, notNullValue());
        assertThat(descriptor.getDisplayName(), is("Skip build status notifications"));
        assertThat(descriptor.getContextClass(), is(GiteaSCMSourceContext.class));
        assertThat(descriptor.getSourceClass(), is(GiteaSCMSource.class));
    }
}
