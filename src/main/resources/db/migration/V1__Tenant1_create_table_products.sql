CREATE TABLE tenant1.products (
       product_id BIGINT,
       createdAt TIMESTAMP(6) NOT NULL,
       name VARCHAR(255) NOT NULL,
       status BOOLEAN NOT NULL DEFAULT true,
       stock INTEGER NOT NULL,
       updatedAt TIMESTAMP(6) NOT NULL,
       CONSTRAINT products_pkey PRIMARY KEY (product_id)
);