CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    card_token VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE plans (
    id UUID PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE subscriptions (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id),
    plan_id UUID NOT NULL REFERENCES plans(id),
    start_date DATE NOT NULL,
    expiration_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    version BIGINT NOT NULL,
    retry_count INT DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE UNIQUE INDEX idx_unique_active_sub ON subscriptions (user_id) WHERE status = 'ACTIVE';

INSERT INTO plans (id, name, price) VALUES
('11111111-1111-1111-1111-111111111111', 'BASICO', 19.90),
('22222222-2222-2222-2222-222222222222', 'PREMIUM', 39.90),
('33333333-3333-3333-3333-333333333333', 'FAMILIA', 59.90);

INSERT INTO users (id, name, email, card_token) VALUES
('44444444-4444-4444-4444-444444444444', 'Danilo Ferreira', 'danilof@example.com', 'CARD_SUCCESS'),
('55555555-5555-5555-5555-555555555555', 'Joao da Silva', 'joaos@example.com', 'CARD_FAIL'),
('66666666-6666-6666-6666-666666666666', 'Jose da Silva', 'joses@example.com', 'CARD_2_FAILS');