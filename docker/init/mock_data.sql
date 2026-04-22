-- ============================================================
-- Dados de teste — executado após 01_init.sql
-- Usuário: teste@email.com / teste123
-- ============================================================

INSERT INTO farm_user (name, phone, email, password, created_at, updated_at)
VALUES ('teste', '11999990000', 'teste@email.com',
        '$2a$10$PuXMk9tmiB0jp/Z3j8KkH.ioAQ/o1bmENE/Pr6oUHans3pD.g38ae',
        now(), now());

-- user_id = 2 (admin inserido pelo init.sql ocupa o id=1)

INSERT INTO crop_cycle (user_id, name, crop, planted_area, measurement_unit, plant_count,
                        planned_budget, target_yield, status, start_date, end_date,
                        created_at, updated_at)
VALUES
    (2, 'Tomate Cereja - Safra 2024/1',     'Tomate Cereja',   500.00, 'METRO_QUADRADO',  800, 2800.00, 4200.00, 'FINISHED',   '2024-02-15', '2024-07-10', now(), now()),
    (2, 'Alface Crespa - Verão 2023/24',    'Alface Crespa',   200.00, 'METRO_QUADRADO', 1200,  650.00, 1100.00, 'FINISHED',   '2023-11-01', '2024-01-20', now(), now()),
    (2, 'Cenoura Nantes - Inverno 2024',    'Cenoura',           0.30, 'HECTARE',         NULL, 1400.00, 2500.00, 'ACTIVE',     '2024-05-10', '2024-10-15', now(), now()),
    (2, 'Pimentão Vermelho - 2º Sem 2024',  'Pimentão',        400.00, 'METRO_QUADRADO',  600, 3200.00, 5500.00, 'PLANTING',   '2024-08-01', '2025-01-15', now(), now()),
    (2, 'Abobrinha Caipira - Primavera',    'Abobrinha',       350.00, 'METRO_QUADRADO',  500, 1800.00, 3000.00, 'HARVESTING', '2024-06-01', '2024-09-30', now(), now()),
    (2, 'Brócolis Ninja - Outono 2024',     'Brócolis',        250.00, 'METRO_QUADRADO',  900, 1200.00, 2000.00, 'PLANNING',   '2024-10-01', '2025-01-10', now(), now()),
    (2, 'Pepino Japonês - 2024/2',          'Pepino',          300.00, 'METRO_QUADRADO',  450, 1600.00, 2800.00, 'ACTIVE',     '2024-07-15', '2024-11-30', now(), now()),
    (2, 'Tomate Italiano - Safra 2023/2',   'Tomate Italiano', 600.00, 'METRO_QUADRADO',  900, 3500.00, 5800.00, 'CANCELLED',  '2023-08-01',        NULL, now(), now()),
    (2, 'Rúcula Primavera 2024',            'Rúcula',          150.00, 'METRO_QUADRADO',  NULL,  380.00,  700.00, 'PLANNING',  '2024-09-15', '2024-11-30', now(), now()),
    (2, 'Couve-flor Verônica - 2024/1',    'Couve-flor',      450.00, 'PES',              700, 2200.00, 3600.00, 'FINISHED',   '2024-03-01', '2024-07-20', now(), now());

-- crop_cycle_control para ciclos com progresso real (exclui PLANNING e CANCELLED)
-- IDs dos crop_cycles: 1–10 (serial, inserções acima em ordem)

INSERT INTO crop_cycle_control (crop_cycle_id, current_investment, current_revenue, current_roi,
                                 health_score, progress_percentage, alerts_count, last_calculated_at)
VALUES
    (1,  2800.00, 4150.00,  48.21, 95, 100.00, 0, now()),  -- Tomate Cereja    FINISHED
    (2,   650.00, 1080.00,  66.15, 92, 100.00, 0, now()),  -- Alface Crespa    FINISHED
    (3,   980.00,    0.00, -30.00, 78,  52.00, 1, now()),  -- Cenoura          ACTIVE
    (4,  1200.00,    0.00, -62.50, 85,  30.00, 0, now()),  -- Pimentão         PLANTING
    (5,  1800.00, 2400.00,  33.33, 88,  85.00, 1, now()),  -- Abobrinha        HARVESTING
    (7,   750.00,    0.00, -53.13, 82,  45.00, 0, now()),  -- Pepino           ACTIVE
    (10, 2200.00, 3520.00,  60.00, 97, 100.00, 0, now());  -- Couve-flor       FINISHED
