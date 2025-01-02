CREATE TABLE shared.tenant (
       tenant_id UUID,
       tenant_name VARCHAR(255) NOT NULL,
       createdAt TIMESTAMP(6),
       updatedAt TIMESTAMP(6),
       CONSTRAINT tenant_pkey PRIMARY KEY (tenant_id)
);

