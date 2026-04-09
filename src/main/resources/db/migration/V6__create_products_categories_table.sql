CREATE TABLE products_categories
(
    product_id         BIGINT    NOT NULL,
    product_created_at TIMESTAMP NOT NULL,
    category_id        BIGINT    NOT NULL,

    PRIMARY KEY (product_id, category_id),

    CONSTRAINT fk_pc_product
        FOREIGN KEY (product_id, product_created_at)
            REFERENCES products (id, created_at)
            ON DELETE CASCADE,

    CONSTRAINT fk_pc_category
        FOREIGN KEY (category_id)
            REFERENCES categories (id)
            ON DELETE CASCADE
);
