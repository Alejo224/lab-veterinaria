-- ==========================================
-- 1. TABLA DUENO (Sin llaves foráneas)
-- ==========================================
INSERT INTO dueno (nombre, telefono, correo, direccion) VALUES
('Carlos Mendoza', '3104567890', 'carlos.mendoza@email.com', 'Calle 25 # 14-30, Tuluá'),
('Laura Gómez', '3159876543', 'laura.gomez@email.com', 'Carrera 40 # 28-15, Tuluá'),
('Andrés Felipe Ramírez', '3001122334', 'andres.ramirez@email.com', 'Avenida Cali # 10-45, Tuluá'),
('Mariana Torres', '3187654321', 'mariana.torres@email.com', 'Barrio Sajonia Calle 18 # 22-10');

-- ==========================================
-- 2. TABLA MASCOTA (FK: dueno_id)
-- ==========================================
INSERT INTO mascota (nombre, especie, raza, edad, dueno_id) VALUES
('Zeus', 'Perro', 'Pastor Alemán', 5, 1),
('Luna', 'Gato', 'Siamés', 2, 1),
('Max', 'Perro', 'Golden Retriever', 3, 2),
('Copito', 'Conejo', 'Cabeza de León', 1, 3),
('Kiara', 'Gato', 'Persa', 4, 4);

-- ==========================================
-- 3. TABLA CITA (FK: mascota_id)
-- ==========================================
INSERT INTO cita (fecha, motivo, mascota_id, veterinario) VALUES
('2026-11-20', 'Dolor de estómago y falta de apetito', 1, 'Dr. Antonio Pérez'),
('2026-11-21', 'Vacunación anual y desparasitación', 2, 'Dra. Camila Ríos'),
('2026-11-22', 'Control general y limpieza dental', 3, 'Dr. Antonio Pérez'),
('2026-11-23', 'Revisión dermatológica por caída de pelo', 4, 'Dra. Sofía Castro'),
('2026-11-24', 'Otitis leve en oído derecho', 5, 'Dra. Camila Ríos');

-- ==========================================
-- 4. TABLA TRATAMIENTO (FK: cita_id)
-- ==========================================
INSERT INTO tratamiento (descripcion, dosis, cita_id) VALUES
('Omeprazol veterinario 20mg', '1 tableta cada 24 horas por 5 días', 1),
('Suero oral hidratante', '30 ml cada 8 horas durante 2 días', 1),
('Vacuna Triple Felina', 'Dosis única subcutánea', 2),
('Antiparasitario interno en pasta', '2 ml dosis única', 2),
('Profilaxis ultrasónica dental', 'Procedimiento único en consulta', 3),
('Suplemento Omega 3 y Biotina', '10 gotas diarias con el alimento por 15 días', 4),
('Gotas óticas antibióticas', '3 gotas en el oído afectado cada 12 horas por 7 días', 5);