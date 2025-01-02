package com.dlt.tenant;

import io.quarkus.hibernate.orm.PersistenceUnitExtension;
import io.quarkus.hibernate.orm.runtime.tenant.TenantResolver;

@PersistenceUnitExtension
public class CustomTenantResolver implements TenantResolver {

    @Override
    public String getDefaultTenantId() {
        return "tenant1";
    }
	
	@Override
	public String resolveTenantId() {
		String tenantId = TenantContext.getCurrentTenantId();
		if (tenantId == null) {
			throw new IllegalStateException("Tenant ID is not set");
		}
		return tenantId;
	}
}
