/**
 * @author Shreshth Sandilya (shreshth.4@gmail.com)
 */

package com.project.rest.providers;

import org.sakaiproject.entitybus.entityprovider.EntityProvider;
import org.sakaiproject.entitybus.entityprovider.EntityProviderManager;

/**
 * @author Shreshth Sandilya (shreshth.4@gmail.com)
 */
public abstract class AbstractRESTProvider implements EntityProvider {

    public AbstractRESTProvider(EntityProviderManager entityProviderManager) {
        this.entityProviderManager = entityProviderManager;
        try {
            init();
        } catch (Exception e) {
            throw new RuntimeException("Unable to register the provider ("+this+"): " + e, e);
        }
    }

    private EntityProviderManager entityProviderManager;
    public void setEntityProviderManager(EntityProviderManager entityProviderManager) {
        this.entityProviderManager = entityProviderManager;
    }

    public void init() throws Exception {
        entityProviderManager.registerEntityProvider(this);
    }

    public void destroy() throws Exception {
        entityProviderManager.unregisterEntityProvider(this);
    }

}
