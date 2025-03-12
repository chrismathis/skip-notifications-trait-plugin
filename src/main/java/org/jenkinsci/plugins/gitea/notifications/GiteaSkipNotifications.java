/*
The MIT License

Copyright (c) 2019, Elasticsearch B.V.
Copyright (c) 2025, Bachmann electronic GmbH

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */
package org.jenkinsci.plugins.gitea.notifications;

import org.jenkinsci.Symbol;
import org.jenkinsci.plugin.gitea.GiteaSCMSource;
import org.jenkinsci.plugin.gitea.GiteaSCMSourceContext;
import org.jenkinsci.plugins.variant.OptionalExtension;
import org.kohsuke.stapler.DataBoundConstructor;

import jenkins.scm.api.SCMSource;
import jenkins.scm.api.trait.SCMSourceContext;
import jenkins.scm.api.trait.SCMSourceTrait;
import jenkins.scm.api.trait.SCMSourceTraitDescriptor;

/**
 * Disables notifications.
 *
 * @since 1.0.4
 */
public class GiteaSkipNotifications extends SCMSourceTrait {

    /**
     * Constructor for stapler.
     */
    @DataBoundConstructor
    public GiteaSkipNotifications() {
        // empty
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void decorateContext(SCMSourceContext<?, ?> context) {
        if (context instanceof GiteaSCMSourceContext) {
            GiteaSCMSourceContext ctx = (GiteaSCMSourceContext) context;
            ctx.withNotificationsDisabled(true);
        }
    }

    /**
     * Our {@link hudson.model.Descriptor}
     */
    @OptionalExtension(requirePlugins = {"gitea"})
    @Symbol("giteaSkipNotifications")
    public static class DescriptorImpl extends SCMSourceTraitDescriptor {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDisplayName() {
            return Messages.GiteaSkipNotifications_displayName();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Class<? extends SCMSourceContext> getContextClass() {
            return GiteaSCMSourceContext.class;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Class<? extends SCMSource> getSourceClass() {
            return GiteaSCMSource.class;
        }

    }
}
