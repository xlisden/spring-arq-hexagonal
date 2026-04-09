CREATE TABLE reviews
(
    id                 BIGSERIAL PRIMARY KEY,
    comment            TEXT,
    score              INTEGER,

    product_id         BIGINT    NOT NULL,
    product_created_at TIMESTAMP NOT NULL,
    
    CONSTRAINT fk_review_product
        FOREIGN KEY (product_id, product_created_at)
            REFERENCES products (id, created_at)
            ON DELETE CASCADE
);
