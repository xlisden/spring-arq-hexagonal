-- Foreign keys (performance on joins)
CREATE INDEX idx_reviews_product_id
    ON reviews (product_id);

CREATE INDEX idx_products_product_detail_id
    ON products (product_detail_id);

-- Many-to-many
CREATE INDEX idx_products_categories_product_id
    ON products_categories (product_id);

CREATE INDEX idx_products_categories_category_id
    ON products_categories (category_id);

-- Common searches
CREATE INDEX idx_products_name
    ON products (name);

CREATE INDEX idx_products_name_2025
    ON products_2025 (name);

CREATE INDEX idx_products_name_2024
    ON products_2026 (name);

CREATE INDEX idx_categories_name
    ON categories (name);
