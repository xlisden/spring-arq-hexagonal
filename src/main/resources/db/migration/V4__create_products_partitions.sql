CREATE TABLE products_2025
    PARTITION OF products
        FOR VALUES FROM ('2025-01-01') TO ('2026-01-01');

CREATE TABLE products_2026
    PARTITION OF products
        FOR VALUES FROM ('2026-01-01') TO ('2027-01-01');

CREATE TABLE products_default
    PARTITION OF products
        DEFAULT;


