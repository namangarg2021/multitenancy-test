package com.dlt.tenant;

public class TenantContext {
	
	private static final ThreadLocal<String> CURRENT_TENANT = new ThreadLocal<>();
	
	public static void setCurrentTenant(String tenantId) {
		CURRENT_TENANT.set(tenantId);
	}
	
	public static String getCurrentTenantId() {
		return CURRENT_TENANT.get();
	}
	
	public static void clear() {
		CURRENT_TENANT.remove();
	}
}